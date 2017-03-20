#ifndef FRACTION_H_INCLUDED
#define FRACTION_H_INCLUDED

#include <iostream>
using namespace std;

namespace MATH {

    class FractionException {
        string info;

        public:
            FractionException(const string& s):info(s){}
            const string& get_info() const {
                return info;
            }
    };

    class Fraction {
        private:
            int numerateur;
            int denominateur;

            void simplification();

        public:
            Fraction():Fraction(0,1) {}

            Fraction(int n) {
                numerateur = n; // affectation
                denominateur = 1;
                simplification();
            }

            Fraction(int n, int d):numerateur(n),denominateur(d) { // initialisation
                if (d == 0)
                    throw FractionException("Zero au dénominateur mouahahah");
                simplification();
                cout<<"Création de "<<this<<endl;
            }

            int getNumerateur() const {
                return numerateur;
            }

            int getDenominateur() const {
                return denominateur;
            }

            void setFraction(int n, int d) throw(FractionException);

            static Fraction somme(const Fraction& f1, const Fraction& f2) {
                // RETOURNE UNE COPIE DE CET OBJECT CAR CELUI-CI SERA DETRUIT
                // ON NE PEUT PAS FAIRE DE REFERENCE ICI SAUF SI ON UTILISE UN POINTEUR (NEW)
                return Fraction(f1.numerateur*f2.denominateur+f2.numerateur*f1.denominateur,f1.denominateur*f2.denominateur);
            }

            void add(const Fraction& f2) {
                this->numerateur = this->numerateur*f2.denominateur+f2.numerateur*this->denominateur;
                this->denominateur = this->denominateur*f2.denominateur;
            }

            Fraction& operator+(const Fraction& f) {
                this->add(f);
                return *this;
            }

            Fraction& operator+(int i) {
                add(Fraction(i, 1));
                return *this;
            }

            Fraction& operator++() { // préfixe
                *this + 1;
                return *this;
            }
            Fraction& operator++(int) { // postfix
                *this + 1;
                return *this;
            }

            ~Fraction() {
                cout<<"Destruction "<<this<<endl;
            }
    };
}

std::ostream& operator<<(std::ostream& io, MATH::Fraction f);

#endif // FRACTION_H_INCLUDED
