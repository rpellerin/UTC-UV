#include<stdio.h>
#include<fcntl.h>

main() {
    int i,fd, taille = 10;
    int tab2[taille];

    fd=open("titi.dat",O_RDWR,0666);
    read(fd,tab2,taille*sizeof(int));
    close(fd);

    for (i=0;i<taille;i++)
        printf("%d\n",tab2[i]);

    return 0;
}
