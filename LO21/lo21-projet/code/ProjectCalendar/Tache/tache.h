#ifndef TACHE_H
#define TACHE_H

#include <QString>
#include <QDate>
#include <vector>
#include <QTime>
#include "projet.h"
#include "timing.h"

using namespace std;

class Projet;

/** \class Tache
 * @brief Classe qui manipule les tâches qu'elles soient unitaires ou composites
 */
class Tache
{
protected:
    int id;
    QString titre;
    QDate dateDisponibilite;
    vector<Tache*> preced;
    QDate echeance;
    Projet *projet_conteneur = 0;
    Tache *tache_conteneur = 0;

    Tache(int _id, QString _titre, QDate _dateDisponibilite = QDate(), QDate _echeance = QDate()): id(_id), titre(_titre), dateDisponibilite(_dateDisponibilite), echeance(_echeance){}

public:
    enum ETAT {
        NON_COMMENCEE,
        COMMENCEE,
        INTERROMPUE,
        REPRISE,
        TERMINEE
    };
    /**
     * @brief getId retourne l'id unique d'une tâche
     * @return l'id
     */
    int getId() const { return id; }
    /**
     * @brief setId Modifie l'id d'une tâche
     * @param id le nouvel id de la tâche
     */
    void setId(int id) { this->id = id; }
    /**
     * @brief getTitre permet d'obtenir le titre de la Tache
     * @return un objet string représentant le titre de la Tache
     */
    const QString getTitre() const {return titre;}
    /**
     * @brief getDateDisponibilite permet d'obtenir la date de disponibilité de la Tache
     * @return un objet Date représentant la date de disponibilité de la Tache
     */
    const QDate getDateDisponibilite() const {return dateDisponibilite;}
    /**
     * @brief setTitre permet de modifier le titre de la Tache
     * @param t le nouveau titre
     */
    void setTitre(QString t) {titre = t;}
    /**
     * @brief setDateDisponibilite permet de changer la date de disponibilité
     * @param d la nouvelle date de disponibilité
     */
    void setDateDisponibilite(QDate d) {dateDisponibilite = d;}
    /**
     * @brief isCommencee Permet de savoir si une TacheComposite est commencée
     * @return True si elle est commencée, False sinon
     */
   virtual bool isCommencee () const =0;
    /**
     * @brief isTerminee Permet de savoir si une TacheComposite est terminée
     * @return True si elle est terminée, False sinon
     */
    virtual bool isTerminee () const =0;
    /** @brief getEcheance permet de connaitre l'échéance de la TacheComposite
     * @return l'échéance de la tache sous la forme d'un objet Date
     */
    virtual QDate getEcheance () const { return echeance; }
    /**
     * @brief setEcheance permet de modifier l'échéance d'une tâche
     * @param ec La nouvelle échéance
     */
    virtual void setEcheance (QDate ec) { echeance = ec; }

    /**
     * @brief toEtat permet de convertir une chaine de caractère en un état
     * @param s l'objet QString à convertir
     * @return l'état
     */
    static ETAT toEtat(QString s) {
        if (s == "NON_COMMENCEE") return NON_COMMENCEE;
        if (s == "COMMENCEE") return COMMENCEE;
        if (s == "INTERROMPUE") return INTERROMPUE;
        if (s == "REPRISE") return REPRISE;
        if (s == "TERMINEE") return TERMINEE;
    }

    /**
     * @brief etatToString permet de convertir un état en chaine de caractère
     * @param s l'état à convertir
     * @return la chaine de caractère
     */
    static QString etatToString(ETAT s) {
        if (s == NON_COMMENCEE) return "NON_COMMENCEE";
        if (s == COMMENCEE) return "COMMENCEE";
        if (s == INTERROMPUE) return "INTERROMPUE";
        if (s == REPRISE) return "REPRISE";
        if (s == TERMINEE) return "TERMINEE";
    }

    /**
     * @brief setProjet_conteneur change le projet_conteneur (projet contenant la tache)
     * @param value le nouveau projet contenant la tache
     */
    void setProjet_conteneur(Projet *value) { projet_conteneur = value; }

    /**
     * @brief setTache_conteneur change le tache_conteneur (potentielle tache composite contenant la tache)
     * @param value le nouveau projet contenant la tache
     */
    void setTache_conteneur(Tache *value) { tache_conteneur = value; }

    /**
     * @brief getProjet_conteneur permet de recuperer le projet_conteneur
     * @return un pointeur sur le projet contenant la tache
     */
    Projet* getProjet_conteneur() const { return projet_conteneur; }

    /**
     * @brief getTache_conteneur permet de recuperer le tache_conteneur
     * @return un pointeur sur la potentielle tache contenant la tache
     */
    Tache* getTache_conteneur() const { return tache_conteneur; }
};

/*!
 * \class TacheException
 * \brief Classe permettant de gérer les exceptions de la classe Tache et ses classes filles
 */
class TacheException
{
    QString info;

public:
    //! Constructeur à partir d'un string
    TacheException(const QString& _info):info(_info) {}
    //! Permet de récuperer la description de l'exception
    QString& get_info() { return info; }
};

#endif // TACHE_H




