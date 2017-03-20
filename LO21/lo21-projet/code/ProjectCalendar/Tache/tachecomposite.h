#ifndef TACHECOMPOSITE_H
#define TACHECOMPOSITE_H

#include "tache.h"

/*!
 * \class TacheComposite
 * \brief Classe permettant de manipuler les taches composites (=un ensemble de taches)
 * Elle hérite de la classe Tache
 */
class TacheComposite : public Tache
{
    friend class TacheManager;
private:
    vector<Tache*> taches;
    //! Constructeur de la classe TacheComposite
      TacheComposite(int id = int(), QString _titre = QString(), QDate _dateDisponibilite = QDate(), QDate _echeance = QDate()) : Tache(id, _titre, _dateDisponibilite, _echeance){}

public:

    /**
     * @brief isCommencee Permet de savoir si une TacheComposite est commencée
     * @return True si elle est commencée, False sinon
     */
    virtual bool isCommencee() const override;

    /**
     * @brief isTerminee Permet de savoir si une TacheComposite est terminée
     * @return True si elle est terminée, False sinon
     */
    virtual bool isTerminee() const override;

    /**
     * @brief getTaches permet de connaitre l'ensemble des taches composant la TacheComposite
     * @return un vecteur de pointeur de Tache sur les taches qui composent la TacheComposite
     */
    vector<Tache*> getTaches() const { return taches;  }

    /**
     * @brief ajouterTache permet d'ajouter une tache à la tache composite
     * @param t un pointeur sur la nouvelle tache à ajouter
     */
    void ajouterTache(Tache* t);
};

#endif // TACHECOMPOSITE_H
