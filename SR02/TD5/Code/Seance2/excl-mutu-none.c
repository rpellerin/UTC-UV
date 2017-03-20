#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>

#include <unistd.h>

int shmid;
int *adr;
pid_t son;
int var_locale;

void afaire(int isFather) {
    int j;
    for (j = 0; j<100;j++) {
        P(0);
            var_locale = *adr;
            usleep((rand()%81) + 20);
            var_locale++;
            *adr = var_locale;
        V(0);
        usleep((rand()%81) + 20);
        if (isFather == 1) printf("It %d:\t%d\n",j,*adr);
    }
}

int main() {
    shmid = shmget(IPC_PRIVATE,sizeof(int),IPC_CREAT|IPC_EXCL|0666); // Création d'un espace de mémoire partagé de la taille de 1 int

    adr = (int*) shmat(shmid,NULL,0); // Shell memory attachment
    *adr = 0;

    init_semaphore();
    val_sem(0,1);

    srand(time(NULL));
    switch (son = fork()) {
        case 0:
            printf("%d Fils créé\n",getpid());
            afaire(0);
            shmdt(adr); // détache le segment
            break;
        case -1:
            printf("Erreur\n");
            exit(0);
        default:
            printf("%d Père créé.\n",getpid());
            afaire(1);
            wait();
            printf("Valeur finale affichée par le père après fin du fils:\t%d\n",*adr);
            shmdt(adr); // détache le segment
            shmctl(shmid,IPC_RMID,0); // Shell memory control : permet de supprimer la mémoire partagée
            detruire_semaphore();
    }
    return 0;
}
