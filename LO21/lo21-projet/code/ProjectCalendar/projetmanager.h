#ifndef PROJETMANAGER_H
#define PROJETMANAGER_H


#include <QDebug>
#include <QDate>
#include <QSqlDatabase>
#include <QString>
#include <QSqlQuery>
#include <QSqlError>
#include <QSqlRecord>
#include <vector>

#include "projet.h"
#include "Tache/tachemanager.h"


using namespace TIME;

class TacheManager;

/**
 * \class ProjetManager
 * @brief Classe de type singleton permettant de manipuler le manager de Projets
 * Le manager de Projets controle les différentes Projets
 */
class ProjetManager {
private:
    QSqlDatabase* sdb;

    vector<Projet*> projets;

    ProjetManager(){}
    void addItem(Projet* p);
    ProjetManager(const ProjetManager& um) = delete;
    ProjetManager& operator=(const ProjetManager& um) = delete;
    ~ProjetManager();
public:
    /**
     * @brief clearAll permet de supprimer tous les projets du ProjetManager
     */
    void clearAll() { this->projets.clear(); }

    /**
     * @brief ajouterProjet permet d'ajouter un nouveau projet au ProjetManager
     * @param id titre du nouveau projet
     * @param dispo date de disponibilité de la nouvelle tâche
     * @return une référence sur le projet ajouté
     */
    Projet& ajouterProjet(QString id = QString("-1"), QDate dispo = QDate::currentDate());

    /**
     * @brief getInstance crée un pointeur sur un objet ProjetManager (singleton)
     * @return un pointeur sur la classe singleton
     */
    static ProjetManager* getInstance() {static ProjetManager pm; return &pm;}

    /**
     * @brief trouverProjet permet de trouver un projet dans le ProjetManager
     * @param id le titre du projet
     * @return un pointeur sur le projet recherché
     */
    Projet* trouverProjet(const QString& id) const;

    /**
     * @brief setDatabase permet d'assigner une base de donnée à l'attribut sdb
     * @param sdb la base de donnée à assigner
     */
    void setDatabase(QSqlDatabase* sdb) {
        this->sdb = sdb;
    }

    /**
     * @brief loadProjets permet de charger les projets depuis la base de données SQLite
     */
    void loadProjets();

    /**
     * @brief saveToDB permet d'enregistrer les projets du ProjetManager dans la base de données
     * @return True si ça a fonctionné, False sinon
     */
    bool saveToDB() const;

    /**
     * \class iterator
     * @brief Classe permettant de parcourir les tâches de la classe ProjetManager
     */
    class iterator : public vector<Projet*>::iterator {
        iterator():vector<Projet*>::iterator(){}
        iterator(typename vector<Projet*>::iterator it) : vector<Projet*>::iterator(it){};
        friend class ProjetManager;
    };

    /**
     * @brief begin permet de créer un itérateur sur le 1er projet du tableau de ProjetManager
     * @return un itérateur
     */
    iterator begin() { return iterator(projets.begin()); }

    /**
     * @brief end permet de créer un itérateur sur le dernier projet du tableau de ProjetManager
     * @return un itérateur
     */
    iterator end() { return iterator(projets.end()); }
};

/*! \class TimeException
        \brief Classe permettant de gérer les exceptions des classes du namespace TIME
*/
class ProjetManagerException
{
public:
    ProjetManagerException(const QString& message):info(message){} //! Constructeur à partir d'un string
    QString getInfo() const { return info; } //<! Retourne l'info contenu dans l'objet
private:
    QString info;
};
#endif // PROJETMANAGER_H
