#include "sharemem.h"

int tab2[10];
int shmid;
int *adr;

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
    shmid = shmget(CLE,100,IPC_CREAT|0666); // Création d'un espace de mémoire partagé de taille 100 bytes
    adr = (int*) shmat(shmid,NULL,0); // Shell memory attachment

    remplit(tab2,10,3);
    printf("Remplit tab2 avec 2\n");

    printf("Affiche tab2\n");
    affiche(tab2,10);

    copie(tab2,adr,10); // tab2 -> adr
    printf("Copie tab2 dans mémoire partagée\n");

    printf("Attente de 30s...\n");
    sleep(30);

    shmdt(adr); // détache le segment
    shmctl(shmid,IPC_RMID,0); // Shell memory control : permet de supprimer la mémoire partagée
    printf("Segment détruit.\n");
    return 0;
}
