#include "evenement.h"

Evenement::Evenement(int id, const QString& m_titre, const QString& m_lieu, const QDate& m_dateDebut, const QTime& m_heureDebut, const QDate& m_dateFin, const QTime& m_heureFin, const QString& m_description): titre(m_titre), lieu(m_lieu), description(m_description),id(id){
    if (m_dateDebut > m_dateFin || (m_dateDebut == m_dateFin && m_heureDebut > m_heureFin)){throw EvenementException("Date/Heure non valide!");}
    dateDebut = m_dateDebut;
    heureDebut = m_heureDebut;
    dateFin =  m_dateFin;
    heureFin = m_heureFin;



}

Evenement::~Evenement()
{

}


