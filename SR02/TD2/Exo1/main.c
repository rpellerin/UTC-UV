#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>

#define DUREE 5

pid_t son;

void nouveau_gestionnaire(int num) {
    kill(son,SIGUSR1);
    alarm(DUREE);
}

void signal_recu_par_le_fils(int num) {
    int r = rand();
    printf("Temperature mesuree : %ld°C\n",r%31 + 10);
}

int main() {
    switch (son = fork()) {
        case 0:
            printf("Fils créé\n");

            srand(time(NULL));
            struct sigaction recu;
            recu.sa_handler = &signal_recu_par_le_fils;
            sigaction(SIGUSR1, &recu, NULL);
            while(1) {
                pause();
            }
            break;
        case -1:
            printf("Erreur\n");
        default:
            printf("Père créé.\n");

            struct sigaction alar;
            alar.sa_handler = &nouveau_gestionnaire;
            sigaction(SIGALRM, &alar, NULL);
            alarm(DUREE);
            while (1) {
                sleep(1);
                printf("-");
                fflush(stdout); // Pour afficher le tiret
            }
    }
    return 0;
}
