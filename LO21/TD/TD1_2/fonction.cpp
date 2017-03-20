#include<iostream>
#include<string>
#include "fonction.h"

using namespace std;

void exerciceA(){
    cout<<"Donnez le rayon entier d'un cercle: ";
    int r;
    cin>>r;
    double p, s;
    const float PI = 3.14159;
    p=2*PI*r;
    s=PI*r*r;
    cout<<"le cercle de rayon "<<r<<" a un perimetre de "<<p<<" et une surface de "<<s<<"\n";
}

void bonjour1::bonjour() {
cout<<"nichao\n";
}

void bonjour2::bonjour() {
cout<<"hello\n";
}

void exercice_surcharge(){
    int i=3,j=15;
    float x=3.14159,y=1.414;
    char c='A';
    double z=3.14159265;
    fct(i); //appel 1
    fct(x); //appel 2
    fct(i,y); //appel 3
    fct(x,j); //appel 4
    fct(c); //appel 5
    fct(i,(float)j); //appel 6
    fct((float)i,c); //appel 7
    fct(i,z); //appel 8
    fct((float)z,z); //appel 9
}

void inverse(int *a, int *b) {
    int tmp = *a;
    *a = *b;
    *b = tmp;
}

void inverse(int& a, int& b) {
    int tmp = a;
    a = b;
    b = tmp;
}

void raz(struct essai *a) {
    a->n = 0;
    a->x = 0;
}

void raz(struct essai& a) {
    a.n = 0;
    a.x = 0;
}

void init(point* pt, int _x, int _y, int _z) {
    pt->x=_x; pt->y=_y; pt->z=_z;
}
void init(point* pt, int _x, int _y) {
    init(pt, _x, _y, 0);
}
void init(point* pt, int _x) {
    init(pt, _x, 0, 0);
}
void init(point* pt) {
    init(pt, 0, 0, 0);
}
void essai_init() {
    point p;
    init(&p);
    init(&p,1);
    init(&p,1,2);
    init(&p,1,2,3);
}

void essai_alloc(){
    int* pt_int = new int();
    double* pt_double = new double[100];
    delete(pt_int);
    delete[](pt_double);
    //pt_int=(int*)malloc(sizeof(int));
    //pt_double=(double*)malloc(sizeof(double)*100);
    //free(pt_int);
    //free(pt_double);
}

void raz(struct personne p) {
    p.nom[0] = 0;
    p.age = 0;
}

void affiche_struct(const struct personne* p) {
    cout<<p->nom<<" "<<p->age<<endl;
}

void affiche_tab(const struct personne* tab[], int nb) {
    for (int i = 0; i < nb; i++) {
        affiche_struct(tab[i]);
    }
}

void init_struct(struct personne* p) {

}
