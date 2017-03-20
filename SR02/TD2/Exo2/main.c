#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>

char current_char;
char end_char;
unsigned short counter = 1;

pid_t other; // Le père référencera son fils et le fils référencera son père

void signal_recu(int num) {
    int i;
    for (i = 0; i < counter; i++) {
        printf("%c", current_char++);
        fflush(stdout); // Pour afficher
        if (current_char > end_char)
            break;
    }
    kill(other,SIGUSR1);
    if (current_char > end_char)
        _exit(0);
    counter++;
}

int main() {
    switch (other = fork()) { // PID du fils
        case 0:
            //printf("Fils créé\n");
            other = getppid(); // PID du père
            current_char = 'a';
            end_char = 'z';
            break;
        case -1:
            printf("Erreur\n");
            return 1;
        default:
            //printf("Père créé.\n");
            current_char = 'A';
            end_char = 'Z';
    }
    struct sigaction recu;
    recu.sa_handler = &signal_recu;
    sigaction(SIGUSR1, &recu, NULL);
    if (current_char == 'a')
        kill(getpid(),SIGUSR1); // Fils s'auto lance
    while(1)
        pause();
    return 0;
}
