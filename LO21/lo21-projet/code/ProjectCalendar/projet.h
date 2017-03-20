#ifndef PROJET_H
#define PROJET_H

#include <QString>
#include <vector>

#include "Tache/tache.h"

using namespace std;

class Tache;

/**
 * \class Projet
 * @brief Un projet (regroupe zéro ou plusieurs tâches/groupes de tâches)
 */
class Projet
{
    QString nom;
    QDate dateDisponibilite;
    vector<Tache*> taches;
public:

    //! Constructeur de la classe Projet
    Projet(QString m_nom, QDate m_dispo): nom(m_nom), dateDisponibilite(m_dispo){}

    /**
     * @brief ajouterTache permet d'ajouter une tache à un projet
     * @param t la tache à ajouter
     */
    void ajouterTache(Tache *t);

    /**
     * @brief getEcheance permet de connaitre l'échéance du projet
     * @return la date d'échéance du projet
     */
    QDate getEcheance () const ;

    //! Destructeur de Projet
    ~Projet();

    //!accesseurs en lecture
    QString getNom() const {return nom;}
    QDate getDispo() const {return dateDisponibilite;}

    //!accesseurs en ecriture
    void setNom(const QString& m_nom){nom = m_nom;}
    void setDispo(const QDate& m_dateDispo){dateDisponibilite  = m_dateDispo;}
};
/**
 * @class ProjetException
 * @brief Classe permettant de gérer les exceptions de la classe Projet
 */

class ProjetException
{
    const QString& info;

public:
    ProjetException(const QString& _info):info(_info){} //! Constructeur à partir d'un string
    const QString& get_info() {return info;}//<! Retourne l'info contenu dans l'objet
};
/**
 * @brief operator << permet de passer un projet dans un flux
 * @param io flux
 * @param p projet à passer dans le flux
 * @return le flux
 */

#endif // PROJET_H
