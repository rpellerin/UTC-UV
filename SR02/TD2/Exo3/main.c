#include <stdio.h>
#include <stdlib.h>
#include <setjmp.h>
#include <signal.h>

jmp_buf jb;

void signal_recu(int num) { // appelé si violation mémoire
    longjmp(jb,1); // retourne l'execution après l'appel setjmp
}

int probe(int *addr) {
    int status;
    status = setjmp(jb); // sauvegarde le contexte de pile et d'environnement
    if (status == 0) {
        printf("%d\n",*addr);
        return 1;
    }
    else
        return 0;
}

int main()
{
    int i, index;
    unsigned int* ia1;
    int tab[1000];

    struct sigaction recu;
    recu.sa_handler = &signal_recu;
    sigaction(SIGSEGV, &recu, NULL);

    index = 0;
    while(1) {
        ia1 = &tab[index];
        i = probe(ia1);
        if (i == 0) {
            printf("probe fail at index=%d ia1=%p\n", index, ia1);
            break;
        }
        index++;
    }
    return 0;
}
