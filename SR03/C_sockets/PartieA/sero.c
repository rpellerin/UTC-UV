#include <stdio.h> // For printf() and fprintf()
#include <stdlib.h> // For atoi() and exit()

#include <unistd.h> // For close()
#include <string.h> // For memset()
#include <strings.h> // For bzero

#include <sys/socket.h> // For socket(), bind(), connect()
#include <arpa/inet.h> // For sockaddr_in(), inet_ntoa()

#include <sys/wait.h> // For waitpid()

#include "defobj.h"

#define MAXPENDING 5 // Maximum outstanding connection requests

// USAGE: %s <Sever Port>

void showObj(obj* o) {
    printf("Received:\n%s,%s,%d,%d,%f\n",o->id, o->description, o->ii, o->jj, o->dd);
}

int main(int argc, char *argv[]) {
  printf("Server running\n");

  int sd, sds; // Socket descriptors for server and client
  struct sockaddr_in saddr; // Local address
  obj object; // object being read

  // Creates socket for incoming connections
  // - DOMAIN (AF_INET, AF_INET6, etc)
  // - TYPE (SOCK_STREAM for TCP, SOCK_DGRAM for UDP)
  // - PROTOCOL (TCP or UDP)
  // Returns -1 on error, OR the file descriptor
  sd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
  if (sd == -1) {
    perror("socket()");
    exit(-1);
  }

  bzero(&saddr, sizeof(saddr)); // Zeroes out the whole structure

  // Some configuration
  saddr.sin_family = AF_INET; // Internet address family
  saddr.sin_port = htons(atoi(argv[1])); // atoi = string to int, htons turns its argument into BIG ENDIAN if necessary ('s' is for short)
  saddr.sin_addr.s_addr = htonl(INADDR_ANY); // htonl is like htons, but 'l' is for long; ANY incoming interface

  // Bind to the local address
  // Returns 0 on success, -1 on error
  if (bind(sd, (const struct sockaddr*) &saddr, sizeof(saddr)) == -1) {
      perror("bind()");
      exit(-1);
  }

  // Starts listenning
  // Returns 0 on success, -1 on error
  if (listen(sd, MAXPENDING) == -1) {
    perror("listen()");
    exit(-1);
  }
  father:
  // Accept an incoming client
  // Returns -1 on error (or a negative integer)
  sds = accept(sd, 0, 0);
  if (sds < 0) {
    perror("accept()");
    exit(-1);
  }

  // Fork
  int status;
  pid_t son = fork();
  if (son == -1) {
    perror("fork()");
    exit(-1);
  }
  // FATHER
  if (son > 0) {
    waitpid(son, &status, 0);
    if (status != EXIT_SUCCESS) {
        goto father;
    }
    else {
        printf("Shutting down the server, last object received.\n");
    }
  }
  // CHILD
  else {
    int recvMsgSize = sizeof(object); // Size of received message
    bzero(&object, recvMsgSize);
    do {
      printf("Receiving...\n");
      if ((recvMsgSize = recv(sds, &object, recvMsgSize, 0)) < 0) {
        perror("Error recv() son");
        exit(-1);
      }
      printf("Received %d bytes.\n", recvMsgSize);
      if (recvMsgSize == 0) {
        close(sds);
        printf("End of transmission.\n");
        exit(1); // end of transmission whereas we didn't receive object.id == -1
      }
      showObj(&object);
    } while(object.ii != -1);
    close(sds);
    exit(EXIT_SUCCESS);
  }

  return 0;
}

