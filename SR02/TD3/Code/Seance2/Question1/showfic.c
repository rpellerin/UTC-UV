#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/mman.h>

main() {
    int *adr;
    int fd, stdin, i;

    fd=open("titi.dat",O_RDWR,0666);
    adr = (int *) mmap(NULL,10*sizeof(int),PROT_READ, MAP_SHARED, fd, 0);
    if (adr == MAP_FAILED) {
        perror("mmap");
        exit(0);
    }
    while(1) {
        printf("Entrer un nombre (affichera le tableau): ");
        scanf("%d",&stdin);
        while (getchar()!='\n');
        if (stdin > 99 || stdin < 0) continue;
        if (stdin == 99)
            exit(0);
        for (i=0;i<10;i++)
            printf("%d:\t%d\n",i,adr[i]);
    }
    close(fd);
    return 0;
}
