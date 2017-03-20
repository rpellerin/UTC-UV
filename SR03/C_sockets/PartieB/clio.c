#include <stdio.h> // for printf(), fprintf()
#include <stdlib.h> // for atoi(), exit()

#include <unistd.h> // for close()
#include <string.h> // for memset()
#include <strings.h> // for bzero()

#include <netdb.h> // for gethostbyname()
#include <sys/socket.h> // for socket(), connect(), send(), recv()
#include <arpa/inet.h> // for sockaddr_in, inet_addr()

#include "iniobj.h"

int main(int argc, char *argv[]) {
  printf("Client running\n");

  int sd;
  struct sockaddr_in saddrcli, saddrser;
  struct hostent* hid;
  sd = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
  bzero(&saddrcli, sizeof(saddrcli));
  saddrcli.sin_family = AF_INET;
  saddrcli.sin_port = 0;
  saddrcli.sin_addr.s_addr = htonl(INADDR_ANY);
  bind(sd, (const struct sockaddr*) &saddrcli, sizeof(saddrcli));
  bzero(&saddrser, sizeof(saddrser));
  saddrser.sin_family = AF_INET;
  saddrser.sin_port = htons(atoi(argv[2]));
  hid = gethostbyname(argv[1]);
  bcopy(hid->h_addr, &(saddrser.sin_addr.s_addr), hid->h_length);
  connect(sd, (struct sockaddr *) &saddrser, sizeof(saddrser));
  int i;
  for (i = 0; i < tablen; i++) {
    send(sd, &tabObj[i], sizeof(tabObj[i]), 0);
  }
  printf("Three obj sent.\n");
  obj endObj = {"over","finish",-1,123,456.7};
  send(sd, &endObj, sizeof(endObj), 0);
  printf("End obj sent.\n");
  close(sd);

  return 0;
}
