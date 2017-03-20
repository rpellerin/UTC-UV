#include "common.c"

void mulabc(int size) {
    int colonneB, ligneA, k;

    for (colonneB = 0; colonneB < size; colonneB++) {
        for (ligneA = 0; ligneA < size; ligneA++) {
            X[ligneA] = B[ligneA][colonneB];
        }
        for (ligneA = 0; ligneA < size; ligneA++)
            for(k = 0; k < size; k++) {
                C[ligneA][colonneB] += A[ligneA][k] * X[k];
                printf("%d\t%d\t%d\n",&C[ligneA][colonneB], &A[ligneA][k], &X[k]);
                // Ce programme est plus rapide car les adresses de X[k] sont moins éparpillées que celles de B[k][j] dans mat1.c
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
