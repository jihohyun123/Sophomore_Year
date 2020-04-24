#include<stdio.h>
#include<stdlib.h>
#include<sys/wait.h>
#include<sys/types.h>
#include<unistd.h>

static void die(const char *s)
{
	perror(s);
	exit(1);
}

int main(int argc, char **argv)
{
	if (argc != 1) 
	{
		fprintf(stderr, "usage: %s\n", argv[0]);
		exit(1);

	}
	char portnum[40];
	char *msg = "port number: ";

	while(fputs(msg, stdout) && fgets(portnum, 40, stdin) != NULL)
	{       
		int k=0;		
		while((k=waitpid((pid_t) -1, NULL, WNOHANG))&&k>0)
		{
			fprintf(stderr, "[pid=%d] ", k);
			fprintf(stderr, "mdb-lookup-server terminated\n");
		}
		
		if((portnum[0])!='\n')
		{				
			int myport = atoi(portnum);
			if(myport<10000||myport>64000)
			{
				fprintf(stderr, "port number outside range, please try again\n");
	
			}		
			else
			{
				pid_t pid = fork();	
			
				if (pid<0)
				{
					die("fork failed");

				}
				else if (pid == 0)
				{
					fprintf(stderr, "[pid=%d] ", (int) getpid());
					fprintf(stderr, "mdb-lookup-server started on port %s", portnum);
					execl("./mdb-lookup-server-nc.sh", "mdb-lookup-server-nc.sh", portnum, (char *)0);
					die("execl failed");
				}
	
			}
	
		}
	
	
	}
		
	return 0;
	

}






