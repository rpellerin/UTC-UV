#include "projet.h"
void Projet::ajouterTache(Tache* t)
{
    unsigned int i = 0;
    while (i<taches.size()){
        if(t && t->getTitre() == taches[i]->getTitre()) throw ProjetException("erreur ajouterProjet");
        i++;
    }
    taches.push_back(t);
}

Projet::~Projet()
{

}

QDate Projet::getEcheance() const
{
    QDate max;
    for(std::vector<Tache*>::const_iterator it = taches.begin(); it != taches.end(); ++it)
    {
        Tache* t = *it;
        if (!max.isValid() || max < t->getEcheance()) {
            max = t->getEcheance();
        }
    }
    return max;
}

