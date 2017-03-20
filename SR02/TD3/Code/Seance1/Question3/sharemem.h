#ifndef SHAREMEM_H_INCLUDED
#define SHAREMEM_H_INCLUDED

#include <stdio.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>

#include <unistd.h>

#define CLE 123456

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

#endif // SHAREMEM_H_INCLUDED
