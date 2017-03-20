#include "Calendar.h"

TacheManager::TacheManager() {
    nb = 0;
    nbMax = 1;
    taches = new Tache*[nbMax];
}

void TacheManager::addItem(Tache* t) {
    if (nb == nbMax) {
        int old_nbMax = nbMax;
        nbMax *= 2;
        Tache** tmp = new Tache*[nbMax];

        for (int i = 0; i < old_nbMax ; i++) {
            tmp[i] = taches[i];
            taches[i] = NULL;
        }
        Tache** toDelete = taches;
        delete [] toDelete;
        taches = tmp;
    }
    taches[nb] = t;
    nb++;
}

void TacheManager::ajouterTache(const string& id, const string& t, const Duree& dur, const Date& dispo, const Date& deadline) {
    bool tacheExisteDeja = false;
    try {
        Tache& tmp = getTache(id);
        tacheExisteDeja = true;
    }
    catch (CalendarException e) {}
    if (tacheExisteDeja) throw CalendarException("Tache existante avec le même identificateur.");
    Tache *new_tache = new Tache(id,t,dur,dispo,deadline);
    addItem(new_tache);

}

Tache& TacheManager::getTache(const string& id) {
    for (unsigned int i = 0; i < nb ; i++) {
        if (taches[i]->getId() == id) {
            return *taches[i];
        }
    }
    throw CalendarException("Tache non existante.");
}

TacheManager::~TacheManager() {
    for (unsigned int i = 0; i < nb; i++) {
        delete taches[i];
    }
    delete[] taches;
}

void TacheManager::operator=(const TacheManager& t) {
    nb = t.nb;
    nbMax = t.nbMax;
    taches = new Tache*[t.nbMax];
    for (unsigned int i = 0; i<t.nb; i++) {
        addItem(t.taches[i]);
    }
}
