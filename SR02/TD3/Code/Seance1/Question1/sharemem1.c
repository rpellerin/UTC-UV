#include "sharemem.h"

int tab1[10], tab2[10];
int shmid;
int *adr;
pid_t son;
int status;

void affiche(int* tab, int nb) {
    int i;
     for (i = 0; i < nb; i++) {
        printf("indice %d: %d\n",i,tab[i]);
    }
}

void remplit(int* tab, int nb, int val) {
    int i;
     for (i = 0; i < nb; i++) {
        tab[i] = val;
    }
}

void copie(int* src, int* dst, int nb) {
    int i;
    for (i = 0; i < nb; i++) {
        dst[i] = src[i];
    }
}

int main() {
    remplit(tab1,10,1);
    remplit(tab2,10,1);

    shmid = shmget(IPC_PRIVATE,100,IPC_CREAT|0666); // Création d'un espace de mémoire partagé de taille 100 bytes

    adr = (int*) shmat(shmid,NULL,0); // Shell memory attachment

    switch (son = fork()) {
        case 0:
            printf("Fils créé\n");

            remplit(tab2,10,2);
            printf("Fils a remplit tab2 avec 2\n");

            printf("Fils affiche tab2\n");
            affiche(tab2,10);

            copie(tab2,adr,10); // tab2 -> adr
            printf("Fils a copié tab2 dans mémoire partagée\n");
            shmdt(adr); // détache le segment
            break;
        case -1:
            printf("Erreur\n");
            break;
        default:
            printf("Père créé.\n");
            usleep(500000); // 1/2 seconde

            printf("Père affiche tab2\n");
            affiche(tab2,10);

            usleep(500000); // 1/2 seconde

            copie(adr,tab2,10); // adr -> tab2
            printf("Père a copié mémoire partagée dans tab2\n");

            printf("Père affiche tab2\n");
            affiche(tab2,10);

            wait(&status); // récupère le code de retour de fils dans status

            shmdt(adr); // détache le segment
            shmctl(shmid,IPC_RMID,0); // Shell memory control : permet de supprimer la mémoire partagée
    }

    return 0;
}
