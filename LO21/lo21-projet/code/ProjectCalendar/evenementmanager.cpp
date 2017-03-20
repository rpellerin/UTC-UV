#include "evenementmanager.h"

void EvenementManager::loadEvents(){
    events.clear();
    bool shouldOverwriteMySelfAtTheEnd = false;
    if (this->sdb == nullptr || !this->sdb->open())
        throw EvenementManagerException("Aucune base de données chargée en mémoire.");

    QSqlQuery query(*(this->sdb));
    bool res1 = query.exec("SELECT * FROM EVENEMENTS");
    if (res1) {
        QSqlRecord rec = query.record();

        int id              = rec.indexOf("evenement_id");
        int titre           = rec.indexOf("titre");
        int description     = rec.indexOf("description");
        int lieu            = rec.indexOf("lieu");
        int debut           = rec.indexOf("debut");
        int fin             = rec.indexOf("fin");
        int tache_ref       = rec.indexOf("tache_ref");

        while (query.next()) {
            QStringList _debut = query.value(debut).toString().split(" ");
            QStringList _fin = query.value(fin).toString().split(" ");

            QDate dateDebut = QDate::fromString(_debut.first(), Qt::ISODate);
            QDate dateFin = QDate::fromString(_fin.first(), Qt::ISODate);
            QTime heureDebut = QTime::fromString(_debut.last(), "hh:mm");
            QTime heureFin = QTime::fromString(_fin.last(), "hh:mm");
            Tache* t = TacheManager::getInstance()->trouverTacheFromId(query.value(tache_ref).toInt());
            if (!query.value(tache_ref).isNull() && !t) {
                shouldOverwriteMySelfAtTheEnd = true;
                continue;
            }
            qDebug()<<"Event : "<<query.value(titre).toString()<<" chargé.";
            Evenement& ev = ajouterEvenement(query.value(titre).toString(),dateDebut,heureDebut,dateFin,heureFin,query.value(lieu).toString(),query.value(description).toString(),query.value(id).toInt());
            ev.setTacheAssociee(t);
        }
    }
    this->sdb->close();
    if (shouldOverwriteMySelfAtTheEnd) saveToDB();
}

bool EvenementManager::isEvenementExistant(QString title)
{
    for (EvenementManager::iterator it = begin(); it != end(); ++it){
        if ((*it)->getTitre() == title) return true;
    }
    return false;
}

void EvenementManager::addItem(Evenement *p)
{
    events.push_back(p);
}

Evenement *EvenementManager::trouverEvenement(int id) const
{
    for(unsigned int i=0; i<events.size(); i++)
        if (id==events[i]->getId()) return events[i];
    return 0;
}

EvenementManager::~EvenementManager()
{
    qDebug()<<"EvenementManager supprimé";
    for(unsigned int i=0; i<events.size(); i++) delete events[i];
}

Evenement &EvenementManager::ajouterEvenement(const QString &m_titre, const QDate &m_dateDebut, const QTime &m_heureDebut, const QDate &m_dateFin, const QTime &m_heureFin, const QString &m_lieu, const QString &m_description,int id)
{
    if (id == -1) {
        id = events.size();
        while (trouverEvenement(++id)) {}
    }
    if (trouverEvenement(id)) throw EvenementManagerException("erreur, EvenementManager, évenement déjà existant");
    Evenement* newt=new Evenement(id, m_titre, m_lieu, m_dateDebut,m_heureDebut,m_dateFin,m_heureFin,m_description);//(id,t,dur,dispo,deadline,preempt);
    addItem(newt);
    qDebug()<<"L'évenement "<<m_titre<<" a été ajouté dans son manager.";

    return *newt;
}

bool EvenementManager::isThisTaskInThisProject(Tache* t, Projet* p) const {
    return ((t->getProjet_conteneur() == p) || (t->getTache_conteneur() && isThisTaskInThisProject(t->getTache_conteneur(), p)));
}

vector<Evenement*> EvenementManager::getFromProjet(Projet* p) const {
    vector<Evenement*> ret;
    for (vector<Evenement*>::const_iterator it = events.begin() ; it != events.end(); ++it) {
        Evenement* ev = *it;
        if (ev->getTacheAssociee()) {
            if (ev->getTacheAssociee()->getProjet_conteneur() == p)
                ret.push_back(ev);
            else if (ev->getTacheAssociee()->getTache_conteneur() && isThisTaskInThisProject(ev->getTacheAssociee()->getTache_conteneur(),p))
                ret.push_back(ev);
            qDebug()<<"trouvé event appartement au projet : "<<ev->getTitre();
         }
    }
    return ret;
}

bool EvenementManager::saveToDB() const {
    if (this->sdb == nullptr || !this->sdb->open() || !this->sdb->transaction())
        throw EvenementManagerException("Aucune base de données chargée en mémoire.");

    QSqlQuery query(*(this->sdb));
    bool res1 = query.exec("DELETE FROM EVENEMENTS");
    if (res1) {

        for (vector<Evenement*>::const_iterator it = events.begin(); it != events.end(); ++it) {
            Evenement* newt = *it;

            QSqlQuery query(*(this->sdb));
            query.prepare("INSERT INTO EVENEMENTS VALUES (:id, :titre, :description, :lieu, :debut, :fin, :tache_ref)");
            query.bindValue(":id", newt->getId());
            query.bindValue(":titre", newt->getTitre());
            query.bindValue(":description", newt->getDescription());
            query.bindValue(":lieu", newt->getLieu());
            query.bindValue(":debut", newt->getDateDebut().toString("yyyy-MM-dd")+ " " +newt->getHeureDebut().toString("hh:mm"));
            query.bindValue(":fin", newt->getDateFin().toString("yyyy-MM-dd")+ " " +newt->getHeureFin().toString("hh:mm"));
            if (newt->getTacheAssociee()!=nullptr) {
                query.bindValue(":tache_ref", newt->getTacheAssociee()->getId());
            }
            else {
                query.bindValue(":tache_ref", QVariant(QVariant::String));
            }
            if (!query.exec()) {
                this->sdb->rollback();
                qDebug() << query.lastError();
                this->sdb->close();
                throw EvenementManagerException("Erreur lors de la sauvegarde en base de données (insert failed).");
            }
        }
        if (!this->sdb->commit()) {
            this->sdb->close();
            throw EvenementManagerException("Impossible de commiter les changements en base de données.");
        }
    }
    this->sdb->close();
    return true;
}

