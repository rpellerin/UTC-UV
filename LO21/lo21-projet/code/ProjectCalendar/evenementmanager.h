#ifndef EVENEMENTMANAGER_H
#define EVENEMENTMANAGER_H


#include <QDebug>
#include <QDate>
#include <QSqlDatabase>
#include <QSqlQuery>
#include <QSqlError>
#include <QSqlRecord>
#include <QString>

#include "Tache/tachemanager.h"
#include "evenement.h"
#include "timing.h"


using namespace TIME;

/**
 * \class ProjetManager
 * @brief Classe de type singleton permettant de manipuler le manager de Projets
 * Le manager de Projets controle les différentes Projets
 */
class EvenementManager {
private:
    QSqlDatabase* sdb;

    vector<Evenement*> events;

    EvenementManager(){}
    void addItem(Evenement* p);
    Evenement* trouverEvenement(int id) const;
    EvenementManager(const EvenementManager& um) = delete;
    EvenementManager& operator=(const EvenementManager& um) = delete;
    ~EvenementManager();
public:
    /**
     * @brief clearAll permet de supprimer tous les evenement de l'EvenementManager
     */
    void clearAll() { this->events.clear(); }

    /**
     * @brief ajouterEvenement permet d'ajouter un evenement au TacheManager
     * @param m_titre QString représentant le titre de l'evenement
     * @param m_dateDebut QDate représentant la date de début
     * @param m_heureDebut QTime représentant l'heure de début
     * @param m_dateFin QDate représentant la date de fin
     * @param m_heureFin QTime représentant l'heure de fin
     * @param m_lieu QString représentant le lieu de l'evenement
     * @param m_description QString représentant la description de l'evenement
     * @param id entier représentant la clé primaire pour la base de donnée
     * @return une référence sur l'évenement ajouté
     */
    Evenement& ajouterEvenement(const QString &m_titre, const QDate &m_dateDebut, const QTime &m_heureDebut, const QDate &m_dateFin, const QTime &m_heureFin, const QString &m_lieu = QString(), const QString &m_description = QString(), int id = -1);

    /**
     * @brief getInstance crée un pointeur sur un objet ProjetManager (singleton)
     * @return un pointeur sur la classe singleton
     */
    static EvenementManager* getInstance() {static EvenementManager pm; return &pm;}

    /**
     * @brief setDatabase permet d'aassigner une base de données à m'attribut sdb
     * @param sdb la base de donnée à assigner
     */
    void setDatabase(QSqlDatabase* sdb) {
        this->sdb = sdb;
    }

    /**
     * @brief saveToDB permet d'enregistrer les evenement de l'EvenementManager dans la base de données
     * @return True si ça a fonctionné, False sinon
     */
    bool saveToDB() const;

    /**
     * @brief isThisTaskInThisProject permet de savoir si la tache passée en parametre est contenue dans le projet
     * @param t la tache
     * @param p le projet
     * @return True si la tache est dans le projet, False sinon
     */
    bool isThisTaskInThisProject(Tache* t, Projet* p) const;

    /**
     * @brief getFromProjet permet de recuperer des evenements liés à des taches d'un projet
     * @param p le projet
     * @return un vecteur de pointeurs sur Evenement
     */
    vector<Evenement*> getFromProjet(Projet* p) const;

    /**
     * @brief loadEvents permet de charger les évènements depuis la base de données SQLite
     */
    void loadEvents();

    /**
     * @brief getFromPeriod permet d'isoler les evenements contenus dans une certaine période
     * @param debut date de début de la période
     * @param fin date de fin de la période
     * @return un vector contenant des pointeurs sur des objets evenements
     */
    vector<Evenement*> getFromPeriod(QDate& debut, QDate& fin) {
        vector<Evenement*> ret;
        for (vector<Evenement*>::const_iterator it = events.begin() ; it != events.end(); ++it) {
            if (!((*it)->getDateDebut() > fin || (*it)->getDateFin() < debut)) {
                ret.push_back(*it);
                qDebug()<<"trouvé event dans la periode : "<<(*it)->getTitre();
             }
        }
        return ret;
    }

    bool isEvenementExistant(QString title);

    class iterator : public vector<Evenement*>::iterator {
    public:
        friend class EvenementManager;
        iterator( typename vector<Evenement*>::iterator it = vector<Evenement*>::iterator()): vector<Evenement*>::iterator(it) {}
    };
    iterator begin() {return iterator(events.begin());}
    iterator end() {return iterator(events.end());}

};

/*! \class TimeException
        \brief Classe permettant de gérer les exceptions des classes du namespace TIME
*/
class EvenementManagerException
{
public:
    EvenementManagerException(const QString& message):info(message){} //! Constructeur à partir d'un string
    QString getInfo() const { return info; } //<! Retourne l'info contenu dans l'objet
private:
    QString info;
};
#endif // EVENEMENTMANAGER_H
