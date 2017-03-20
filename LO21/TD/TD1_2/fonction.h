#ifndef FONCTION_H_INCLUDED
#define FONCTION_H_INCLUDED

#include "essai.h"

void exerciceA();

namespace bonjour1 {
    void bonjour();
}

namespace bonjour2 {
    void bonjour();
};

inline int fct(int x){ std::cout<<"1:"<<x<<"\n"; return 0; }
inline int fct(float y){ std::cout<<"2:"<<y<<"\n"; return 0; }
inline int fct(int x, float y){ std::cout<<"3:"<<x<<y<<"\n"; return 0; }
inline float fct(float x, int y){ std::cout<<"4:"<<x<<y<<"\n"; return 3.14; }

void inverse(int *a, int *b);

void inverse(int& a, int& b);

struct essai {
    int n;
    float x;
};

void raz(struct essai *a);

void raz(struct essai& a);

struct point {
    int x;
    int y;
    int z;
};

struct personne {
    char nom[30];
    unsigned int age;
};

void raz(struct personne);

void affiche_struct(const struct personne* p);

void affiche_tab(const struct personne tab[], int nb);

void init_struct(struct personne* p);

#endif // FONCTION_H_INCLUDED
