#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/mman.h>

main() {
    int *adr;
    int fd, stdin;

    fd=open("titi.dat",O_RDWR,0666);
    adr = (int *) mmap(NULL,10*sizeof(int),PROT_READ|PROT_WRITE, MAP_SHARED, fd, 0);
    if (adr == MAP_FAILED) {
        perror("mmap");
        exit(0);
    }
    while(1) {
        printf("Entrer un nombre (modifiera le tableau): ");
        scanf("%d",&stdin);
        while (getchar()!='\n');
        if (stdin > 99 || stdin < 0) continue;
        if (stdin == 99)
            exit(0);
        adr[stdin]++;
        printf("Modifié!\n");
    }
    close(fd);
    return 0;
}
