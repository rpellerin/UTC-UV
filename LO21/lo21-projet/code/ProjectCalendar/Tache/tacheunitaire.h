#ifndef TACHEUNITAIRE_H
#define TACHEUNITAIRE_H

#include <QDate>

#include "tache.h"

/**
 *\class TacheUnitaire
 * @brief permet de manipuler des tâches unitaires
 */
class TacheUnitaire : public Tache
{
    friend class TacheManager;
private:
    static const int DUREE_MAX = 12;

protected:
    TIME::Duree duree;
    ETAT etat;
    //! Constructeur de TacheUnitaire
    TacheUnitaire(int id, QString _titre, TIME::Duree _duree, QDate _dateDisponibilite = QDate(), QDate _echeance = QDate(), Tache::ETAT _etat = NON_COMMENCEE);

public:
    /**
     * @brief getEtat permet de connaitre l'état de la tache
     * @return l'état de la tâche (NON_COMMENCEE,COMMENCEE,INTERROMPUE,REPRISE,TERMINEE)
     */
    Tache::ETAT getEtat () const { return etat; }

    /**
     * @brief isNonCommencee permet de savoir si une tâche est commencée ou non
     * @return True si la tâche n'est pas commencée, False sinon
     */
    bool isNonCommencee () const { return etat==NON_COMMENCEE; }

    /**
     * @brief commencer permet de changer l'état d'une tache unitaire de NON_COMMENCEE à COMMENCEE
     */
    void commencer ();

    /**
     * @brief isCommencee permet de savoir si la tâche a déjà été commencée
     * @return Faux si la tâche n'a JAMAIS été commencée, Vrai sinon
     */
    bool isCommencee() const override { return etat!=NON_COMMENCEE; }

    /**
     * @brief terminer permet de changer l'état d'une tache unitaire à TERMINEE
     */
    void terminer ();

    /**
     * @brief isTerminee permet de savoir si la tâche est terminée
     * @return True si la tâche est terminée, False sinon
     */
    bool isTerminee() const override { return etat==TERMINEE; }

    /**
     *@brief Destructeur de la classe TacheUnitaire
     * Permet de détruire les objets Date utilisés
     */
    ~TacheUnitaire() {}

    /**
     * @brief getDuree permet de connaitre la durée d'une tâche unitaire
     * @return une durée
     */
    TIME::Duree getDuree() const { return duree; }

    /**
     * @brief setDuree permet de changer la durée d'une tâche
     * @param value nouvelle durée
     */
    virtual void setDuree(TIME::Duree& value);
};

#endif // TACHEUNITAIRE_H
