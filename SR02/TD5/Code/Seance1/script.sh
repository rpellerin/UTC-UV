#!/bin/sh

gcc -c sem_pv.c -o sem_pv.o && ar rvs libsempv.a sem_pv.o && nm -s libsempv.a && gcc -o sem1 sem1.c -L. -lsempv && ./sem1 || echo "Erreur"