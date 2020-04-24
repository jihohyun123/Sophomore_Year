This file should contain:
  
   -Ji Ho Hyun
   -jh3888
   -5
   -description for each part

---------------------------------------------------------------------------

Part 1a:
--------
The shell runs as instructed by the assignment. I did not remove the pipe
if ctrl_c halts the script.



---------------------------------------------------------------------------

Part 1b:
--------
Below is the output of running ps ajxfww from another terminal while
mdb-lookup-server-nc-1 is running. Included are the parent and child processes
of the program.

 PPID   PID  PGID   SID TTY      TPGID STAT   UID   TIME COMMAND
    1 32274 32274 32274 ?           -1 Ss       0   4:30 /usr/sbin/sshd -D
32274  8736  8736  8736 ?           -1 Ss       0   0:00  \_ sshd: jh3888 [priv]
 8736  8786  8736  8736 ?           -1 S     3258   0:00  |   \_ sshd: jh3888@pts/107
 8786  8793  8793  8793 pts/107   9857 Ss    3258   0:00  |       \_ -bash
 8793  9857  9857  8793 pts/107   9857 S+    3258   0:00  |           \_ ./mdb-lookup-server-nc-1 10431
 9857  9858  9857  8793 pts/107   9857 S+    3258   0:00  |               \_ /bin/sh ./mdb-lookup-server-nc.sh 10431
 9858  9861  9857  8793 pts/107   9857 S+    3258   0:00  |                   \_ cat mypipe-9858
 9858  9862  9857  8793 pts/107   9857 S+    3258   0:00  |                   \_ nc -l 10431
 9858  9863  9857  8793 pts/107   9857 S+    3258   0:00  |                   \_ /bin/sh /home/jae/cs3157-pub/bin/mdb-lookup-cs3157
 9863  9864  9857  8793 pts/107   9857 S+    3258   0:00  |                       \_ /home/jae/cs3157-pub/bin/mdb-lookup /home/jae/cs3157-pub/bin/mdb-cs3157


---------------------------------------------------------------------------

Part 1c:
--------
My mdb-lookup-nc-2 runs as directed by the lab. For invalid portnumbers
(outside the range set outside for general use or non-valid characters) the
program simply prints an error message and prints the next prompt. If the
input is exceptionally long, the message will loop several times before 
printing the next message, but this is undefined behavior, and the 
program doesn't crash or leak memory.


---------------------------------------------------------------------------






