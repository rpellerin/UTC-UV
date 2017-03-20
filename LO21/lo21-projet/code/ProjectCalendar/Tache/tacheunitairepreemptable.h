#ifndef TACHEUNITAIREPREEMPTABLE_H
#define TACHEUNITAIREPREEMPTABLE_H

#include "tacheunitaire.h"
/**
 *\class TacheUnitairePreemptable
 * @brief permet de manipuler des tâches unitaire préemptables
 */
class TacheUnitairePreemptable : public TacheUnitaire
{
    friend class TacheManager;
private:
    //! Constructeur de tache préemptable
    TacheUnitairePreemptable(int id, QString _titre, TIME::Duree _duree, QDate _dateDisponibilite = QDate(), QDate _echeance = QDate(), Tache::ETAT _etat = NON_COMMENCEE):TacheUnitaire(id, _titre, TIME::Duree(0,0), _dateDisponibilite, _echeance,_etat) {
        duree  = _duree;
    }

public:
    /**
     * @brief interrompre permet de changer l'état d'une tâche à INTERROMPUE et d'enregistrer la dâte et heure d'interruption
     */
    void interrompre ();

    /**
     * @brief isInterrompue permet de savoir si une tâche est interrompue
     * @return True si la tâche est interrompue, False sinon
     */
    bool isInterrompue () const { return etat==INTERROMPUE; }

    /**
     * @brief reprendre permet de changer l'état d'une tâche à REPRISE et d'enregistrer la dâte et heure de reprise
     */
    void reprendre ();

    /**
     * @brief isReprise permet de savoir si une tâche est reprise
     * @return True si la tâche est reprise, False sinon
     */
    bool isReprise () const { return etat==REPRISE; }

    void setDuree(TIME::Duree& value) { duree = value; }
};

#endif // TACHEUNITAIREPREEMPTABLE_H
