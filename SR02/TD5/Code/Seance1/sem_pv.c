#include "sem_pv.h"

#define CLE 123

int semid = -2; // id de l'ensemble de sémaphores

int init_semaphore(void) {
    if (semid != -2 && semid != -1) {
        fprintf(stderr, "Déjà créé.\n");
        return -1;
    }
    else if((semid = semget(IPC_PRIVATE, N_SEM, 0666)) == -1) {// crée un/des sémaphore(s), alternative : semget(CLE, N_SEM, IPC_CREAT|IPC_EXCL|0666)
        fprintf(stderr, "Erreur lors de la création du groupe de sémaphores.\n");
        return -2; // Erreur récupérable avec errno
    }
    union semun bla;
    short val[1] = {0};
    bla.array = val;
    semctl(semid,0,SETALL,bla);
    return 0;
}

int detruire_semaphore(void) {
    if (semid == -2 || semid == -1) {
        fprintf(stderr, "Le groupe de sémaphores ne peut être détruit, il n'a jamais été créé.\n");
        return -1;
    }

    return semctl(semid,0,IPC_RMID); // supprime completement le groupe de sémaphores, le deuxieme argument est ignoré
}

int val_sem(int sem, int val) {
    if (semid == -2 || semid == -1) {
        fprintf(stderr, "Le groupe de sémaphores n'existe pas.\n");
        return -1;
    }
    else if (sem < 0 || sem >= N_SEM) {
        fprintf(stderr, "Mauvais numéro de sémaphore.\n");
        return -2;
    }

    union semun bla;
    bla.val = val;
    return semctl(semid,sem,SETVAL,bla);
}

int P(int sem) {
    if(sem<0 || sem>N_SEM) return -2;
    if(semid==-2 || semid==-1) return -1;
    struct sembuf sops[1];
    sops[0].sem_num = sem; // sémaphore sur lequel on agit
    sops[0].sem_op = -1; // >0 incrémentation de la valeur, <0 decremente de la valeur, =0 opération (on attend)
    sops[0].sem_flg = 0; // IPC_NOWAIT, SEM_UNDO ou 0 (ne veut rien dire)
    return semop(semid, sops, 1); // 1 = nb de cases du tableau sops
}

int V(int sem) {
    if(sem<0 || sem>N_SEM) return -2;
    if(semid==-2 || semid==-1) return -1;
    struct sembuf sops[1] = {{sem, 1, 0}};
    return semop(semid, sops, 1); // 1 = nb de cases du tableau sops
}
