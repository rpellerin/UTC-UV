#include<stdio.h>

int main() {
    int i = 0;
    init_semaphore();
    printf("%d\n",i++);
    val_sem(2,1);
    printf("%d\n",i++);
    P(2);
    printf("%d\n",i++);
    sleep(10);
    printf("%d\n",i++);
    V(2);
    printf("%d\n",i++);
    detruire_semaphore();
    printf("%d\n",i++);
    return 0;
}
