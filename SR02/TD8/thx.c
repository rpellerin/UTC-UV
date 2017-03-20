// Main :
// - initialisations
// - création de 3 threads exécutant la fonction th_fonc
// - attente de la fin des threads
// th_fonc :
// si thread numéro i :
// - écrire numéro dans fenêtre
// - tracer un rectangle vide
// - boucle de longueur mi :
//   printf message
//   k = compteur_global
//   th_wait()
//   faire progresser rectangle rempli
//   k++;
//   compteur_global = k;
// à la fin de thread i :
// - protéger par mutex : total = total + mi
// - renvoyer mi comme valeur de retour de th_fonc

/* thx.c exemple threads+X11 *
appelle routines definies dans thw.c et barx.c *
> gcc -c barx.c *
> gcc -c thw.c *
> gcc -o thx thx.c thw.o barx.o -L/usr/X11R6/lib/ -lX11 -lpthread */
/* main lance des threads qui chacun: *
fait progresser un rectangle horizontal, */
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

#define nth 3 /* nbre de threads a lancer */
#define ifer(is,mess) if (is==-1) perror(mess)

char buf[20]; // string pour récupérer l'input à la question "sortir?" à la fin du prog
pthread_t threads[nth]; // tableau qui contient les threads
pthread_mutex_t mutex; // MUTEX COMMUN A TOUS LES THREADS
pthread_cond_t event;  // CONDITION DE MUTEX

/* routine exécutée dans les threads */
void *th_fonc (void *arg) {
    int m1; // On stocke la longueur de la barre
    int y; // posiition de la barre
    int is; // ????
    int numero; // numéro du thread
    int i; // numéro de l'itération
    char* couleur = (char*) malloc(sizeof(char)*15);
    numero = (int)arg;

    printf("ici thread %d, i=%d\n",numero);
    is = pthread_mutex_lock(&mutex);
    switch (numero) {
      case 0:
        m1 = 20;
        y = 100;
        couleur = "yellow";
        drawstr (30, 125, "_0_", 3);
        break;
      case 1:
        m1 = 35;
        y = 130;
        couleur = "white";
        drawstr (30, 155, "_1_", 3);
        break;
      case 2:
        m1 = 40;
        y = 160;
        couleur = "green";
        drawstr (30, 185, "_2_", 3);
    }
    drawrec (100,y,100+m1*10,30);
    flushdis ();
    is = pthread_mutex_unlock(&mutex);   

    for (i=0;i<=m1;i++) {
        is = pthread_mutex_lock(&mutex);

        printf("th %d j=%d\n",numero,i);
        fillrec (100,y+2,100+i*10,26,couleur);

        is = pthread_mutex_unlock(&mutex);
        sleep(1);
    }
    return ((void *)(numero)+101);
}

int liretty (char *prompt, char *buffer) {
  int i;
  printf("\n%s",prompt);
  i = scanf ("%s",buffer);
  return strlen(buffer);
}

int main() { // OK
  int nlu; // longueur de l'input à la question "sortir ?"
  int is; // code de retour pour chaque thread
  int i=0; // numéro des threads
  void *val=0; // contient la valeur de retour des threads

  initrec(); // creer rectangle rouge
  is = pthread_mutex_init(&mutex, NULL); // initialise le mutex

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
      printf("ici main, fin thread j=%d val=%d\n",i,(int)val);
  }
  
  printf("is= %d\n",is);
  nlu = liretty("sortir ?",buf);
  printf("--fin--\n");
  detruitrec();
  /* detruire la fenetre rectangle */
  exit(EXIT_SUCCESS);
}