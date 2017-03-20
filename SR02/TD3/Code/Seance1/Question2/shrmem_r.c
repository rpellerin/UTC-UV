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
    shmid = shmget(CLE,100,0666); // Création d'un espace de mémoire partagé de taille 100 bytes
    adr = (int*) shmat(shmid,NULL,0); // Shell memory attachment

    copie(adr,tab2,10); // adr -> tab2
    printf("Copié mémoire partagée dans tab2\n");

    printf("Affiche tab2\n");
    affiche(tab2,10);
    shmdt(adr);
    return 0;
}
