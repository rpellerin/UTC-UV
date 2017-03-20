#include <sys/uio.h>
#include <fcntl.h>

#define maxsize 5000

double A[maxsize][maxsize], X[maxsize], Y[maxsize], S;

double B[maxsize][maxsize], C[maxsize][maxsize];

void initaxy(int size) {
    int i, j;
    for (i = 0; i < size; i++)
        for (j = 0; j < size; j++) {
            A[i][j] = B[j][i] = (double) (1000*i+j);
            C[i][j] = 0.0;
        }
}

void wrscat(int size) {
    int i, j;
    int fd = open("mat.dat",O_RDWR|O_CREAT|O_TRUNC,0666);
    printf("%d\n",fd);
    struct iovec iov[0];
    for (j = 0; j < size; j++) {
        for (i = 0; i < size; i++) {
            X[i] = A[i][j];
        }
        iov[0].iov_base = X;
        iov[0].iov_len = size*sizeof(double);
        printf("RETOUR %d\n,",writev(fd, iov, 1));
    }
    close(fd);
}

void rdscat(int size) {
    int i, j;
    int fd = open("mat.dat",O_RDONLY,0666);
    printf("READ %d\n",fd);
    struct iovec iov[size];
    for (i = 0; i < size; i++) {
        iov[i].iov_base = C[i];
        iov[i].iov_len = size*sizeof(double);
    }
    readv(fd, iov, size);
    close(fd);
}

void diffbc(int size) {
    int i, j;
    S = 0.0;
    for (i = 0; i < size; i++)
        for (j = 0; j < size; j++) {
            S += B[i][j] - C[i][j];
            printf("%d\t%d\n",B[i][j], C[i][j]);
        }
}

int main ( int arc, char **argv ) {
    int size;
    if (!argv[1]) {
        size = 3;
    }
    else
        size = atoi(argv[1]);

    initaxy(size);
    wrscat(size);
    rdscat(size);
    diffbc(size);
    printf("S = %f",S);
    return 0;
}
