/*-------------------------------------------------------*/
/* pth.c exemple utilisation pthreads Posix sur Solaris */
/* ----- -- Michel Vayssade -- Avril 1999 -- */
/* > gcc -o pth pth.c -L/usr/X11R6/lib/ -lpthread */ /*-------------------------------------------------------*/
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

#define nth 3 /* nbre de threads a lancer */
#define ifer(is,mess) if (is==-1) perror(mess)

pthread_t threads[nth];

/* routine exécutée dans les threads */
void *th_fonc (void *arg) {
    int is, numero, i;
    numero = (int)arg;
    for (i=1;i<=5;i++) {
        printf("ici thread %d, i=%d\n",numero,i);
        sleep(1);
    }
    return ((void *)(numero+100));
}

main() {
    int is, i;
    void *val=0;
    /* créer les threads */
    for(i=0; i<nth; i++) {
        printf("ici main, création thread %d\n",i);
        is = pthread_create( &threads[i], NULL, th_fonc, (void *)i );
        ifer (is,"err. création thread");
    }
    /* attendre fin des threads */
    for(i=0; i<nth; i++) {
        is = pthread_join( threads[i], &val);
        ifer (is,"err. join thread");
        printf("ici main, fin thread %d\n",(int)val);
    }
}
