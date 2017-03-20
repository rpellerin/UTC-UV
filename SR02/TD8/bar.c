/* bar.c exemple X11 * appelle routines definies dans barx.c *
> gcc -o bar bar.c barx.o -L/usr/X11R6/lib/ -lX11 */
#include <stdio.h>
#include <errno.h>
#include <stdlib.h>
char buf[20];
/* ----------------------------------------------------- */
void *b_fonc (void * arg) {
  int is, numero, i,j, m1;
  numero = (int)arg;
  m1 = 20;
  i = m1;
  printf("numero= %d, i=%d \n",numero,i);
  drawstr (30, 125, "_0_", 3);
  drawrec (100,100,100+m1*10,30);
  for (j=1;j<=m1;j++) {
    printf("num %d j=%d\n",numero,j);
    fillrec (100,102,100+j*10,26,"yellow");
  }
  flushdis ();
  return ( (void *)(numero+100) );
}

int liretty (char *prompt, char *buffer) {
  int i;
  printf("\n%s",prompt);
  i = scanf ("%s",buffer);
  return strlen(buffer);
}

main () {
  int nlu, is, i=0;
  initrec();
  /* creer rectangle rouge */
  is = (int)b_fonc( (void *)i );
  printf("is= %d\n",is);
  nlu = liretty("sortir ?",buf);
  printf("--fin--\n");
  detruitrec();
  /* detruire la fenetre rectangle */
  exit(EXIT_SUCCESS);
}
