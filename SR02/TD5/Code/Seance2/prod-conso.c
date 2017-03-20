#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>

#include <unistd.h>

int shmid;
int *adr;

int const SEM_PLACES_LIBRES = 0;
int const SEM_PLACES_OCCUPEES = 1;
int const MUTEX = 2;

int main() {
    int i;
    shmid = shmget(IPC_PRIVATE,5*sizeof(int),IPC_CREAT|IPC_EXCL|0666); // Création d'un espace de mémoire partagé de taille 5 entiers

    adr = (int*) shmat(shmid,NULL,0); // Shell memory attachment

    init_semaphore();
    val_sem(SEM_PLACES_LIBRES,5);
    val_sem(SEM_PLACES_OCCUPEES,0);
    //val_sem(MUTEX,1); // Utilise si plusieurs producteurs, sinon inutile
    srand(time(NULL));

    switch (fork()) {
        case 0:
            printf("%d Fils créé\n",getpid()); // producteur
            int tmp;
            for (i = 0; i<10; i++) {
                P(SEM_PLACES_LIBRES);
                //P(MUTEX);
                tmp = (rand()%50) + 1;
                adr[i%5] = tmp;
                printf("Ajouté: %d\n",tmp);
                //V(MUTEX);
                V(SEM_PLACES_OCCUPEES);
            }
            shmdt(adr); // détache le segment
            break;
        case -1:
            printf("Erreur\n");
            exit(0);
        default:
            printf("%d Père créé.\n",getpid()); // consommateur
            for (i = 0; i<10; i++) {
                P(SEM_PLACES_OCCUPEES);
                //P(MUTEX);
                printf("Consommé: %d\n",adr[i%5]);
                //V(MUTEX);
                V(SEM_PLACES_LIBRES);
            }
            shmdt(adr); // détache le segment
            shmctl(shmid,IPC_RMID,0); // Shell memory control : permet de supprimer la mémoire partagée
            detruire_semaphore();
    }
    return 0;
}
