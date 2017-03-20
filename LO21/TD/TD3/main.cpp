#include <iostream>
#include "fraction.h"

using namespace MATH;
using namespace std;

Fraction* myFunction(){
    Fraction fx(7,8);
    Fraction* pfy=new Fraction(2,3);
    return pfy;
}

int main(){
    Fraction f1(3,4);
    Fraction f2(1,6);
    Fraction* pf3=new Fraction(1,2);
    cout<<"ouverture d’un bloc\n";
    Fraction* pf6;
    {
    Fraction f4(3,8);
    cout<<f4<<endl;
    Fraction f5(4,6);
    pf6=new Fraction(1,3);
    }
    cout<<"fin d’un bloc\n";
    cout<<"debut d’une fonction\n";
    Fraction* pf7=myFunction();
    cout<<"fin d’une fonction\n";
    cout<<"desallocations controlee par l’utilisateur :\n";

    delete pf6;
    delete pf7;

    try {
        Fraction fx(1,0);
    } catch (FractionException e) {
        cout<<e.get_info()<<endl;
    }

    return 0;
}
