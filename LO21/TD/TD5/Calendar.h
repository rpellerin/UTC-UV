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
    friend class TacheManager;
	string identificateur;
	string titre;
	Duree duree;
	Date disponibilite;
	Date echeance;
	Tache(const string& id, const string& t, const Duree& dur, const Date& dispo, const Date& deadline):
			identificateur(id),titre(t),duree(dur),disponibilite(dispo),echeance(deadline){}
    Tache(Tache const & o):Tache(o.identificateur, o.titre, o.duree, o.disponibilite, o.echeance) {}
    void operator=(Tache const &);
public:
	string getId() const { return identificateur; }
	string getTitre() const { return titre; }
	Duree getDuree() const { return duree; }
	Date getDateDisponibilite() const {  return disponibilite; }
	Date getDateEcheance() const {  return echeance; }
};

class TacheManager {
private:
    static TacheManager *instance;
	Tache** taches;
	unsigned int nb;
	unsigned int nbMax;
	void addItem(Tache* t);
	Tache* trouverTache(const string& id) const;
	string file;
	TacheManager();
	~TacheManager();
public:
    class Iterator {
            int counter;
            Tache** taches;
        public:
            Iterator(Tache** t, int nb):counter(nb),taches(t) {}
            bool isDone() {
                return counter == 0;
            }

            void next() {
                counter--;
                taches++;
            }
            Tache& current() {
                cout <<counter<<endl;
                return **taches;
            }
    };
    class iterator {
            int counter;
            Tache** taches;
        public:
            iterator(Tache** t, int nb):counter(nb),taches(t) {}
            Tache& current() const {
                cout <<counter<<endl;
                return **taches;
            }
            TacheManager::iterator& operator++ () {
                counter--;
                taches++;
            }
            bool operator!=(const Tache& t) {
              return !(&current() == &t);
            }

    };
    friend class TacheManager::Iterator;
    TacheManager::Iterator getIterator() { return TacheManager::Iterator(taches, nb); }
    static TacheManager* getInstance();
    static void libererInstance();
//    TacheManager::iterator& begin() { return TacheManager::iterator(taches, nb); }
//    TacheManager::iterator& end() { return TacheManager::iterator(taches, nb); }
	TacheManager(const TacheManager& um);
	TacheManager& operator=(const TacheManager& um);
	Tache& ajouterTache(const string& id, const string& t, const Duree& dur, const Date& dispo, const Date& deadline);
	Tache& getTache(const string& id);
	const Tache& getTache(const string& code) const;
	void load(const string& f);
	void save(const string& f);
};

class Programmation {
	const Tache* tache;
	Date date;
	Horaire horaire;
public:
	Programmation(const Tache& t, const Date& d, const Horaire& h):tache(&t), date(d), horaire(h){}
	const Tache& getTache() const { return *tache; }
	Date getDate() const { return date; }
	Horaire getHoraire() const { return horaire; }
};

class ProgrammationManager {
private:
	Programmation** programmations;
	unsigned int nb;
	unsigned int nbMax;
	void addItem(Programmation* t);
	Programmation* trouverProgrammation(const Tache& t) const;
public:
	ProgrammationManager();
	~ProgrammationManager();
	ProgrammationManager(const ProgrammationManager& um);
	ProgrammationManager& operator=(const ProgrammationManager& um);
	void ajouterProgrammation(const Tache& t, const Date& d, const Horaire& h);
};

ostream& operator<<(ostream& f, const Tache& t);
ostream& operator<<(ostream& f, const Programmation& p);
//ostream& operator<<(ostream& f, const TacheManager::iterator& i) {
//    cout<<i;
//}

#endif
