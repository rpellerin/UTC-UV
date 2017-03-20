#ifndef TACHEMANAGER_H
#define TACHEMANAGER_H

#include <QDebug>
#include <QDate>
#include <QSqlDatabase>
#include <QSqlQuery>
#include <QSqlRecord>
#include <QSqlError>
#include <QVariant>
#include <vector>

#include "projetmanager.h"
#include "tache.h"
#include "tachecomposite.h"
#include "tacheunitaire.h"
#include "tacheunitairepreemptable.h"
#include "timing.h"

using namespace TIME;

/**
 * \class TacheManager
 * @brief Classe de type singleton permettant de manipuler le manager de taches
 * Le manager de taches controle les différentes taches
 */
class TacheManager {
private:
    QSqlDatabase* sdb;

    vector<Tache*> taches;

    TacheManager(){}
    void addItem(Tache* t);
    TacheManager(const TacheManager& um) = delete;
    TacheManager& operator=(const TacheManager& um) = delete;
    ~TacheManager();
public:
    /**
     * @brief clearAll permet de supprimer toutes les taches du TacheManager
     */
    void clearAll() { this->taches.clear(); }

    /**
     * @brief ajouterTache permet d'ajouter une tâche au TacheManager
     * @param id titre de la nouvelle tâche
     * @param dur durée de la nouvelle tâche
     * @param dispo date de disponibilité de la nouvelle tâche
     * @param deadline échéance de la nouvelle tâche
     * @param preempt tâche préemptable ou non
     * @return une référence sur la tâche ajoutée
     */
    Tache& ajouterTache(QString id = QString("-1"), const Duree dur = Duree(), QDate dispo = QDate::currentDate(), QDate deadline = QDate(), bool preempt = "true");
    /**
     * @brief trouverTache permet de trouver une tache dans le TacheManager a partir de son titre
     * @param id titre de la tache
     * @return un pointeur sur la tache cherchée
     */
    Tache* trouverTache(const QString& id) const;

    /**
     * @brief trouverTacheFromId trouverTache permet de trouver une tache dans le TacheManager a partir de son id
     * @param id id de la tache
     * @return la tache cherchée
     */
    Tache* trouverTacheFromId(int id) const;

    /**
     * @brief setDatabase permet d'assigner une base de données à l'attribut sdb
     * @param sdb la base de données à assigner
     */
    void setDatabase(QSqlDatabase* sdb) {
        this->sdb = sdb;
    }

    /**
     * @brief loadTaches permet de charger les tâches depuis la base de données SQLite
     */
    void loadTaches();

    /**
     * @brief saveToDB permet d'enregistrer la liste des taches dans le TacheManager dans la base de données
     * @return True si l'enregistrement a fonctionné, False sinon
     */
    bool saveToDB() const;

    /**
     * @brief getInstance crée un pointeur sur un objet TacheManager (singleton)
     * @return un pointeur sur la classe singleton
     */
    static TacheManager* getInstance() {static TacheManager tm; return &tm;}

    /**
     * \class iterator
     * @brief Classe permettant de parcourir les tâches de la classe TacheManager
     */
    class iterator : public vector<Tache*>::iterator {
        //! Constructeur d'iterator à partir d'un vector<Tache*>::iterator
        iterator(typename vector<Tache*>::iterator it = vector<Tache*>::iterator()):vector<Tache*>::iterator(it){}
        friend class TacheManager;
    };
    /**
     * @brief begin permet de créer un itérateur sur la 1ere tâche du tableau de TacheManager
     * @return un itérateur
     */
    iterator begin() { return iterator(taches.begin()); }
    /**
     * @brief end permet de créer un itérateur sur la derniere tâche du tableau de TacheManager
     * @return un itérateur
     */
    iterator end() { return iterator(taches.end()); }
};

/*! \class TimeException
        \brief Classe permettant de gérer les exceptions des classes du namespace TIME
*/
class TacheManagerException
{
public:
    TacheManagerException(const QString& message):info(message){} //! Constructeur à partir d'un string
    QString getInfo() const { return info; } //<! Retourne l'info contenu dans l'objet
private:
    QString info;
};

#endif // TACHEMANAGER_H
