#ifndef EVENEMENT_H
#define EVENEMENT_H

#include <string>
#include <QString>
#include <QTime>
#include <QDate>

#include <Tache/tache.h>

using namespace std;

/**
 *\class Evenement
 * @brief Un événement de l'agenda (classique ou tâche)
 */
class Evenement
{
    int id;
    QString titre;
    QString lieu;
    QDate dateDebut;
    QTime heureDebut;
    QDate dateFin;
    QTime heureFin;
    QString description;
    Tache* tache_ref = nullptr;
public:
  //! Constructeur d'Evenement
    Evenement(int id, const QString& m_titre, const QString& m_lieu, const QDate& m_dateDebut, const QTime& m_heureDebut, const QDate& m_dateFin, const QTime& heureFin, const QString& m_description);

    /**
     * @brief Evenement constructeur de recopie d'Evenement
     * @param autre evenement à recopier
     */
    Evenement(const Evenement& autre);

    /**
     * @brief Destructeur d'Evenement
     */
    ~Evenement();

    //!accesseurs en lecture
    int getId() const { return id; }
    QString getTitre() const {return titre;}
    QString getLieu() const {return lieu;}
    QDate getDateDebut() const {return dateDebut;}
    QTime getHeureDebut() const {return heureDebut;}
    QDate getDateFin() const {return dateFin;}
    QTime getHeureFin() const {return heureFin;}
    QString getDescription() const {return description;}
    Tache* getTacheAssociee() const {return tache_ref;}

    //!accesseurs en ecriture
    void setId(int it) { this->id = id; }
    void setTitre(const QString& m_titre){titre = m_titre;}
    void setLieu(const QString& m_lieu){lieu = m_lieu;}
    void setDateDebut(QDate m_dateDebut){dateDebut = m_dateDebut;}
    void setHeureDebut(QTime m_heureDebut){heureDebut = m_heureDebut;}
    void setDateFin(QDate m_dateFin){dateFin = m_dateFin;}
    void setHeureFin(QTime m_heureFin){heureFin = m_heureFin;}
    void setDescription(const QString& m_description){description = m_description;}
    void setTacheAssociee(Tache* t) {this->tache_ref = t;}
};
/**
 * @brief The EvenementException class permet de recuperer les exceptions de la tache Evenement
 */
class EvenementException
{
    const QString& info;
public:
    EvenementException(const QString& _info):info(_info){} //! Constructeur à partir d'un string
    const QString& get_info() {return info;}//<! Retourne l'info contenu dans l'objet
};



#endif // EVENEMENT_H
