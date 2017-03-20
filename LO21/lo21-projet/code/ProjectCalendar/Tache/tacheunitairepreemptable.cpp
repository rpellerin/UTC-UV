#include "tacheunitairepreemptable.h"

void TacheUnitairePreemptable::interrompre ()
{
    if (etat!=COMMENCEE && etat!=REPRISE)
        throw TacheException("La tâche ne peut pas être interrompue.");
    else {
        etat = INTERROMPUE;
    }
}

void TacheUnitairePreemptable::reprendre ()
{
    if (etat!=INTERROMPUE)
        throw TacheException("La tâche n'est pas interrompue actuellement.");
    else {
        etat = REPRISE;
    }
}
