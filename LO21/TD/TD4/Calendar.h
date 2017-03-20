#ifndef CALENDAR_h
#define CALENDAR_h

#include<string>
#include<iostream>

#include "timing.h"

using namespace std;
using namespace TIME;

class CalendarException{
    public:
        CalendarException(const string& message):info(message){}
        string getInfo() const { return info; }
    private:
        string info;
};


class Tache {
    private:
        string id, titre;
        Duree duree;
        Date disponibilite, echeance;

    public:
        Tache(const string& id="", const string& titre="", const Duree& duree=Duree(), const Date& dispo=Date(), const Date& ech=Date()):
            id(id),
            titre(titre),
            duree(duree),
            disponibilite(dispo),
            echeance(ech) {};
        const string& getId() const {return id;};
        const string& getTitre() const {return titre;};
        const Duree& getDuree() const {return duree;};
        const Date& getDisponibilite() const {return disponibilite;};
        const Date& getEcheance() const {return echeance;};
};


class TacheManager {
    private:
        Tache** taches;
        unsigned int nb;
        unsigned int nbMax;
        void addItem(Tache* t);
    public:
        TacheManager();
        void ajouterTache(const string& id, const string& t, const Duree& dur, const Date&
        dispo, const Date& deadline);
        Tache& getTache(const string& id);
        const Tache& getTache(const string& id) const {return this->getTache(id);}
        ~TacheManager();
        void operator=(const TacheManager& t);
        TacheManager(const TacheManager& autre):
            nb(autre.nb),
            nbMax(autre.nbMax),
            taches(new Tache*[autre.nbMax]) {
                for(unsigned int i = 0; i<autre.nb; i++) {addItem(autre.taches[i]);}
            }
};

class Programmation {
    private:
        Tache* t;
        Date date;
        Horaire horaire;
    public:
        Programmation(Tache* _t, Date _d, Horaire _h):
            t(_t),
            date(_d),
            horaire(_h) {};
        const Tache& getTache() const {return *t;}
        const Date& getDate() const {return date;}
        const Horaire& getHoraire() const {return horaire;}
};

#endif
