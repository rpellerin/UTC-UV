#include "tacheunitaire.h"

using namespace std;

TacheUnitaire::TacheUnitaire(int id, QString _titre, TIME::Duree _duree, QDate _dateDisponibilite, QDate _echeance, Tache::ETAT _etat):Tache(id, _titre, _dateDisponibilite, _echeance), duree(_duree),etat(_etat)//,echeance(_echeance)
{
    if (_duree.getDureeEnHeures() > DUREE_MAX)
        throw TacheException("Durée trop élevée. Durée max possible : "+DUREE_MAX);
}

void TacheUnitaire::commencer ()
{
    if (etat!=NON_COMMENCEE)
        throw TacheException("Tâche déjà commencée.");
    else
        etat = COMMENCEE;
}

void TacheUnitaire::terminer ()
{
    if (etat==TERMINEE)
        throw TacheException("Tâche déjà terminée.");
    else
        etat = TERMINEE;
}

void TacheUnitaire::setDuree(TIME::Duree &value)
{
    if (value.getDureeEnHeures() > DUREE_MAX)
        throw TacheException("Durée trop élevée. Durée max possible : "+DUREE_MAX);
    duree = value;
}
