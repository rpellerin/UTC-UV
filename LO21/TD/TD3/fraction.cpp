#include "fraction.h"

using namespace MATH;

void Fraction::setFraction(int n, int d) throw(FractionException){
    if (d == 0)
        throw FractionException("Zero au dénominateur mouahahah");
    else {
        denominateur = d;
        numerateur = n;
        simplification();
    }
}

void Fraction::simplification() {
    // si le numerateur est 0, le denominateur prend la valeur 1
    if (numerateur == 0) {
        denominateur = 1;
        return;
    }
    /* un denominateur ne devrait pas être 0;
    si c’est le cas, on sort de la méthode */
    if (denominateur == 0)
        return;
    /* utilisation de l’algorithme d’Euclide pour trouver le Plus Grand Commun
    Denominateur (PGCD) entre le numerateur et le denominateur */
    int a = numerateur, b = denominateur;
    // on ne travaille qu’avec des valeurs positives...
    if (a < 0)
        a = -a;
    if (b < 0)
        b = -b;

    while(a != b) {
        if (a > b)
            a = a - b;
        else
            b = b - a;
    }
    // on divise le numerateur et le denominateur par le PGCD=a
    numerateur /= a;
    denominateur /= a;
    // si le denominateur est négatif, on fait passer le signe - au denominateur
    if (denominateur<0) {
        denominateur=-denominateur;
        numerateur=-numerateur;
    }
}

std::ostream& operator<<(std::ostream& io, MATH::Fraction f) {
  return io<<f.getNumerateur()<<"/"<<f.getDenominateur();
}
