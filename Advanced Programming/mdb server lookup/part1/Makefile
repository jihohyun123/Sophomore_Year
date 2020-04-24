
CC = gcc

INCLUDES = 

CFLAGS = -g -Wall $(INCLUDES)

LDFLAGS = -g

.PHONY: default
default: mdb-lookup-server-nc-2 mdb-lookup-server-nc-1
	
mdb-lookup-server-nc-2: mdb-lookup-server-nc-2.o

mdb-lookup-server-nc-2.o: mdb-lookup-server-nc-2.c

.PHONY: clean
clean: 
	rm -f *.o a.out core main
