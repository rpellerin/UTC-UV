#include <stdio.h>
#include <sys/times.h>
#include <unistd.h>

#define maxsize 5000

double A[maxsize][maxsize], X[maxsize], Y[maxsize], D;

double B[maxsize][maxsize], C[maxsize][maxsize];

clock_t tbegin, tend;
struct tms tmsbufbegin, tmsbufend;
double tickspsec;

void initaxy(int size) {
    int i, j;
    for (i = 0; i < size; i++)
        for (j = 0; j < size; j++) {
            A[i][j] = B[i][j] = (double) (i+j);
            C[i][j] = 0.0;
        }
}

void sumdiac(int size) {
    int i;
    D = 0.0;
    for (i = 0; i < size; i++)
        D += C[i][i];
    printf("\nSomme : %f\n",D);
}
