#include <fcntl.h>

main() {
    int tab[10] = {11,22,33,44,55,66,77,88,99,1000};
    int fd;
    fd=open("titi.dat",O_RDWR|O_CREAT|O_TRUNC,0666);
    write (fd,tab,10*sizeof(int));
    close(fd);
    return 0;
}
