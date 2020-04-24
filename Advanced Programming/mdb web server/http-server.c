#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include<unistd.h>
#include<sys/wait.h>
#include<sys/stat.h>
#include<arpa/inet.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<netdb.h>
//#include"http-util.h"
//extern int herr_no;
void msgSender(int errorID, int sockd)
{
	
	char buf[4096];
	char begin[100] = "HTTP/1.0 ";
	char badRErr[100] = "400 Bad Request";
	char notFoundErr[100] = "404 Not Found";
	char notImpErr[100] = "501 Not Implemented"; 
	char htmlmsg[100] = "<html><body><h1>";
	char htmlend[100] = "</h1></body></html>";
	char newline[100] = "\r\n";
	int y=0;
	int len=0;
	if(errorID == 200)
	
	{
		char valid[100] = "HTTP/1.0 200 OK\r\n";		
		send(sockd, valid, strlen(valid), 0);
		send(sockd, newline, strlen(newline), 0);
		fprintf(stderr, "200 OK\n");	

	}
	if(errorID == 400)
	{
		len = strlen(begin)+ strlen(badRErr) + (2*strlen(newline))+  strlen(htmlmsg)+strlen(badRErr)+strlen(htmlend)+strlen(newline);
		y = snprintf(buf, len, "%s%s\r\n\r\n%s%s%s\r\n", begin, badRErr, htmlmsg, badRErr, htmlend);
		send(sockd, buf, y, 0);
		fprintf(stderr, "%s\n", badRErr);
		//fclose(fp);
		close(sockd);

	}
	if (errorID == 404)
	{
		len =  strlen(begin)+ strlen(notFoundErr) + (2*strlen(newline))+strlen(htmlmsg)+strlen(notFoundErr)+strlen(htmlend)+strlen(newline);
		y = snprintf(buf, len, "%s%s\r\n\r\n%s%s%s\r\n", begin, notFoundErr, htmlmsg, notFoundErr, htmlend);
		send(sockd, buf, y, 0);
		fprintf(stderr, "%s\n", notFoundErr);
		close(sockd);
	}
	if(errorID == 501)
	{
		len =  strlen(begin)+ strlen(notImpErr) + (2*strlen(newline))+strlen(htmlmsg)+strlen(notImpErr)+strlen(htmlend)+strlen(newline);
		y = snprintf(buf, len, "%s%s\r\n\r\n%s%s%s\r\n", begin, notImpErr, htmlmsg, notImpErr, htmlend);
		send(sockd, buf, y, 0);
		fprintf(stderr, "%s\n", notImpErr);
		close(sockd);

	}

}

int mdbLinker(char *inip, int inport)
{
	int mdbsock;
	if((mdbsock = socket(AF_INET, SOCK_STREAM, 0))<0)
	{
		fprintf(stderr, "%s\n", "socket failure");
	}
	
	struct hostent *he;
	if ((he = gethostbyname(inip))==NULL)
	{
		fprintf(stderr, "gethostbyname failed");
		exit(1);
	}	
	char *mdbip = inet_ntoa(*(struct in_addr *)he->h_addr);	
	struct sockaddr_in mdbaddr;
	//char *convert = inet_ntoa(mdbaddr.sin_addr);
	memset(&mdbaddr, 0, sizeof(mdbaddr)); // must zero out the structure
	mdbaddr.sin_family = AF_INET;
	mdbaddr.sin_addr.s_addr = inet_addr(mdbip);
	mdbaddr.sin_port = htons(inport);

	if (connect(mdbsock, (struct sockaddr *) &mdbaddr, sizeof(mdbaddr)) < 0)
	{
		fprintf(stderr,"\nconnection to mdb-lookup-server failed\n");	
		exit(1);
	}

	
	return mdbsock;
}




static void die(const char *s) {
       	perror(s); 
	exit(1); 
}

int main(int argc, char **argv)
{
	
	if(argc!=5)//switch back to 5 later
	{
		fprintf(stderr, "%s\n", "usage: http-client <server_host> <web_root> <mdb-lookup-host> <mdb-lookup-port>");
		exit(1);
	}
	unsigned short port = atoi(argv[1]);
	char *filepath = argv[2];
	char *mdbaddress = argv[3];
	unsigned short mdbport = atoi(argv[4]);	
		
	int mdbsock = mdbLinker(mdbaddress, mdbport);
	int sock;
	if((sock = socket(AF_INET, SOCK_STREAM, 0))<0)
	{
		fprintf(stderr, "%s\n", "socket failure");
	}
	
	struct sockaddr_in servaddr;
	memset(&servaddr, 0, sizeof(servaddr));
	servaddr.sin_family = AF_INET;
	servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
	servaddr.sin_port = htons(port);
	if (bind(sock, (struct sockaddr *) &servaddr, sizeof(servaddr)) < 0)
		die("bind failed");
	// Start listening for incoming connections
	if (listen(sock, 5 /* queue size for connection requests */ ) < 0)
		die("listen failed");	
	
	int r;
	FILE *mdbOut = fdopen(mdbsock, "rb");
	
	
	while(1)
	{
		int clntsock;
		socklen_t clntlen;
		struct sockaddr_in clntaddr;
		FILE *fp;
		unsigned int filesuffix = 0;
	
		FILE *clientMsg;
		char inbuffer[4096]="";
		char newline[100] = "\r\n";
		clntlen = sizeof(clntaddr);
		if ((clntsock = accept(sock, (struct sockaddr *) &clntaddr, &clntlen)) < 0)
			die("accept failed");//switch this to just closing 
	
		clientMsg = fdopen(clntsock, "rb");
		
		fprintf(stderr, "%s ", inet_ntoa(clntaddr.sin_addr));
		
		fgets(inbuffer, sizeof(inbuffer), clientMsg);
		if(inbuffer==NULL)
		{
			msgSender(400, clntsock);
			fclose(clientMsg);
			fflush(mdbOut);
		//	free(status);
			continue;
			
		}
		char *request = inbuffer;		
		char *token_separators = "\t \r\n"; // tab, space, new line
		char *method = strtok(request, token_separators);	
		char *requestURI = strtok(NULL, token_separators);
		char fullpath[1024];
		char *httpVersion = strtok(NULL, token_separators);
		



		fprintf(stderr, "\"%s %s %s\" ", method, requestURI, httpVersion);
 //	
		if((method==NULL)||(requestURI==NULL)||(httpVersion==NULL))
		{
			msgSender(400,clntsock);
			fclose(clientMsg);
			//fflush(mdbOut);	
			continue;


		}

		if(fullpath==NULL)
		{
			msgSender(400,clntsock);
			fclose(clientMsg);
			continue;


		}	
		char *mdb = "/mdb-lookup";
		char *mdbkey = "?key=";
		const char *form = 
			"<h1>mdb-lookup</h1>\n"
			"<p>\n"
			"<form method=GET action=/mdb-lookup>\n"
			"lookup: <input type=text name=key>\n"
			"<input type=submit>\n"
			"</form>\n"
			"<p>\n";
	
		int first = 0;
		if(strncmp(requestURI, mdb, 11)==0)//&& strstr(requestURI, mdbkey)!=0)
		{
			if(strstr(requestURI, mdbkey)!=0)//error check this;
			{
				char key[4096];
				strcpy(key, requestURI+16);
			//	fprintf(stderr, "\nKEY: %s is key\n", key);
//				if(key[strlen(key)]=='\0')
//				{
				key[strlen(key)]='\n';
				key[strlen(key) + 1]='\0';
//				}
			//	fprintf(stderr, "\nsearching for: %s after add nl\n", key);
				int j = (int) send(mdbsock, key, strlen(key), 0);
			//	fprintf(stderr, "\nBYTES SENT: %d\n", j);
				char buf[4096];
				int now;
				char *tableheader="<p><table border="">\r\n"
						"<tbody><tr><td>";
				
				char *colored = "</td></tr><tr><td bgcolor=\"blue\">";	
				char *endline = "</td></tr></tbody></table>\r\n"
					"</p>\r\n";
		
				char *defaultline = "</td></tr><tr><td>";
				char *sendbuf;
				msgSender(200, clntsock);
				//send(clntsock, form, strlen(form), 0);
				while(fgets(buf, 4096, mdbOut))
				{
					if(buf[0]=='\n')
					{
						//send(clntsock, form, strlen(form), 0);
						if(first == 0)
						{
							send(clntsock, form, strlen(form), 0);
							break;
						}
						send(clntsock, endline, strlen(endline), 0);
						break;	
							
					}
					if(first == 0)
					{
						sendbuf = malloc(4096);
						strcpy(sendbuf, form);// strlen(form));
						strcat(sendbuf, tableheader);//, strlen(form));
						strncat(sendbuf, buf, strlen(buf));//, strlen(buf));
						strcat(sendbuf, newline);//, strlen(newline));
						send(clntsock, sendbuf, strlen(sendbuf), 0);
						first++;
						free(sendbuf);
					}
					if (first%2!=0)
					{
						sendbuf = malloc(4096);
						strcpy(sendbuf, colored);//, strlen(colored));
						strncat(sendbuf, buf, strlen(buf));//, strlen(buf));
						strcat(sendbuf, newline);//, strlen(newline));
						send(clntsock, sendbuf, strlen(sendbuf), 0);
						first++;
						free(sendbuf);
					}
					else if((first%2==0))
					{
						sendbuf = malloc(4096);
						strcpy(sendbuf, defaultline);//, strlen(defaultline));
						strncat(sendbuf, buf, strlen(buf));//, strlen(buf));
						strcat(sendbuf, newline);//, strlen(newline));
						send(clntsock,sendbuf, strlen(sendbuf),0);
						first++;
						free(sendbuf);
					}
						//fwrite(buf, strlen(buf), 1, stdout);		
				}
			//	send(clntsock, endline, strlen(endline), 0);
				//fprintf(stderr, "got all bytes :)");
	
			}
			else
			{
				msgSender(200, clntsock);
				send(clntsock, form, strlen(form),0);
				fclose(clientMsg);
				close(clntsock);
				fflush(mdbOut);
				continue;
			}
			//send(clntsock, form, strlen(form), 0);
			fclose(clientMsg);
			close(clntsock);
		//	fflush(mdbOut);
			continue;
		}
	
			
	
		//if(filepath)	
		strcpy(fullpath, filepath);
		strcat(fullpath, requestURI);
		//fprintf(stderr, "full filepath: %s", fullpath);
		//TEST 1:
		char get[10] = "GET";
		//fprintf(stderr, "%s", method);
		if(strcmp(method, get)!=0)//!=
		{
		//	fprintf(stderr,"%s", "501");	
			msgSender(501, clntsock);
			fclose(clientMsg);
			continue;
		}
		
	
		if(requestURI[0]!='/')
		{
		//	fprintf(stderr,"yo mama");
			msgSender(400, clntsock);
			fclose(clientMsg);
			continue;
		}

	
	
		//TEST 2:
		char URIBuf[4096];
		char *defaultEnd = "index.html";
		char *defaultSlash = "/index.html";
		int len = strlen(requestURI);
		char storage[4096];
		FILE *filepointer;
		
		struct stat status;
		//status = malloc(sizeof(struct stat));
		//fprintf(stderr, "%s", fullpath);
		
		int statt = stat(fullpath, &status);
		//fprintf(stderr, "\nchecking whether stat is successful or not:%d", statt);
		int dirNot = S_ISDIR((&status)->st_mode);
		//fprintf(stderr, "\ndirNot: %d", dirNot);

		//fprintf(stderr, "full path: %s", fullpath);	
		//fprintf(stderr, "full path: %s\n DIRECTORY? %d", fullpath, dirNot);	
		
		if(statt==0 && (fullpath[strlen(fullpath)-1] != '/')&&(S_ISDIR((&status)->st_mode)!=0))
		{	
			strcat(fullpath, defaultSlash);
			//fprintf(stderr, "\nthis is directory without a slash");
			//fprintf(stderr,"\nappended URI: %s\n", fullpath);
		
		}

		if (statt==0 && (fullpath[strlen(fullpath)-1] == '/')&&(S_ISDIR((&status)->st_mode)!=0))//	
		{
			//fprintf(stderr, "\nYEY");
			//strcat(fullpath, requestURI);
			strcat(fullpath, defaultEnd);
			//fprintf(stderr,"\nappended URI: %s\n", fullpath);
		}
			//free(status);
		
		int i = strlen(fullpath)-3;
		int k = 0;
		char lastBuffer[1024] = "";
		while(fullpath[i])
		{
			lastBuffer[k]=fullpath[i];
			k++;
			i++;
		}	
		
		char last = fullpath[strlen(fullpath)-1];
		char bad[10] = "/..";
		char bad2[10] = "/../";
		if(strstr(lastBuffer, bad)||strstr(fullpath, bad2))
		{
			msgSender(400, clntsock);
			fclose(clientMsg);
			continue;
	
		}

		
		
		
		//TEST 3:
		
				

		char *http1 = "HTTP/1.0";
		char *http2 = "HTTP/1.1";
			
		if((strcmp(httpVersion, http1)!=0)&&(strcmp(httpVersion, http2)!=0))
		{
			msgSender(501, clntsock);
			fclose(clientMsg);
		//	free(status);
			continue;
		}

		
		filepointer = fopen(fullpath, "rb");
		if(filepointer==NULL)
		{
			msgSender(404, clntsock);
			fclose(clientMsg);
		//	free(status);
			continue;
		}
	
		msgSender(200, clntsock);	
		int j;
		while(j=fread(storage, 1, sizeof(storage), filepointer))
		{
			send(clntsock, storage, j, 0);

		}
		//send(clntsock, newline, strlen(newline), 0);
		//free(status);
		fclose(clientMsg);
		fclose(filepointer);
		close(clntsock);

	}
	//close(clntsock);
	//fclose(clientMsg);
	fclose(mdbOut);
	close(mdbsock);
	close(sock);



}
