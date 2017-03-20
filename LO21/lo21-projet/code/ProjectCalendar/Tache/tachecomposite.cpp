#include "tachecomposite.h"


bool TacheComposite::isCommencee() const {
    for(vector<Tache*>::const_iterator it = taches.begin(); it != taches.end(); ++it) { // for(auto &t : taches)
        if ((*it)->isCommencee() != NON_COMMENCEE)
            return true;
    }
    return false;
}

bool TacheComposite::isTerminee() const {
    for(vector<Tache*>::const_iterator it = taches.begin(); it != taches.end(); ++it) { // for(auto &t : taches)
        if ((*it)->isCommencee() != TERMINEE)
            return false;
    }
    return true;
}

void TacheComposite::ajouterTache(Tache* t)
{
    taches.push_back(t);
}
