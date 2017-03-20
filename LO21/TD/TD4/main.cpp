#include <iostream>

#include "Calendar.h"

using namespace std;

int main()
{
    Duree d(1,0);
    Date dispo, ech;
    Tache t("RDV", "AVEC M. X", d, dispo, ech);
    ech.setDate(1,2,1992);
    cout << ech << endl;
    TacheManager tm;
    try {
        cout<<"====AJOUT 1"<<endl;
        tm.ajouterTache("1","Une tache",d,dispo,ech);
        cout<<"====AJOUT 1"<<endl;
        tm.ajouterTache("1","Une tache",d,dispo,ech);
    }
    catch (...) {
        cout << "Erreur lors de l'ajout d'une des tâches." << endl;
    }
    try {
        cout<<"====AJOUT 2"<<endl;
        tm.ajouterTache("2","Une tache",d,dispo,ech);
        cout<<"====AJOUT 3"<<endl;
        tm.ajouterTache("3","Une tache",d,dispo,ech);
        cout<<"====AJOUT 4"<<endl;
        tm.ajouterTache("4","Une tache",d,dispo,ech);
        cout<<"====AJOUT 4"<<endl;
        tm.ajouterTache("4","Une tache",d,dispo,ech);
    }
    catch (...) {
        cout << "Erreur lors de l'ajout d'une des tâches." << endl;
    }
    return 0;
}
