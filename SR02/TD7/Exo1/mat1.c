#include "common.c"

void mulabc(int size) {
    int i, j, k;
    for (i = 0; i < size; i++)
        for (j = 0; j < size; j++) {
            for (k = 0; k < size; k++) {
                C[i][j] += A[i][k] * B[k][j];
                printf("%d\t%d\t%d\n",&C[i][j], &A[i][k], &B[k][j]);
            }
        }
}

int main() {
    int size;
    printf("Size ? ");
    scanf("%d",&size);
    initaxy(size);

    // CALCUL
    tbegin = times(&tmsbufbegin);
    mulabc(size);
    tend = times(&tmsbufend);
    tickspsec = sysconf(_SC_CLK_TCK);
    printf("Temps : %f",(tend-tbegin)/tickspsec);

    sumdiac(size);
    return 0;
}
