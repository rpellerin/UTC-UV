#include "projetmanager.h"

void ProjetManager::loadProjets(){
    projets.clear();
    if (this->sdb == nullptr || !this->sdb->open())
        throw ProjetManagerException("Aucune base de données chargée en mémoire.");

    QSqlQuery query(*(this->sdb));
    bool res1 = query.exec("SELECT * FROM PROJETS");
    if (res1) {
        QSqlRecord rec = query.record();

        int nom                 = rec.indexOf("nom");
        int date_disponibilite  = rec.indexOf("date_disponibilite");

        while (query.next()) {
            qDebug()<<"Projet : "<<query.value(nom).toString()<<" chargé.";
            ajouterProjet(query.value(nom).toString(),query.value(date_disponibilite).toDate());
        }
    }
    this->sdb->close();
}

void ProjetManager::addItem(Projet *p)
{
    projets.push_back(p);
}

Projet* ProjetManager::trouverProjet(const QString &id) const
{
    for(unsigned int i=0; i<projets.size(); i++)
        if (id==projets[i]->getNom()) return projets[i];
    return 0;
}


ProjetManager::~ProjetManager()
{
    qDebug()<<"ProjetManager supprimé";
    for(unsigned int i=0; i<projets.size(); i++) delete projets[i];
}

Projet &ProjetManager::ajouterProjet(QString id, QDate dispo)
{
    if (id == "-1") {
        int num = projets.size();
        do {
            num++;
            id = QString("Nouveau projet %1").arg(num);
        } while (trouverProjet(id));
    }
    else if (trouverProjet(id)) throw ProjetManagerException("erreur, ProjetManager, projet deja existant");
    Projet* newt=new Projet(id, dispo);//(id,t,dur,dispo,deadline,preempt);
    addItem(newt);
    return *newt;
}

bool ProjetManager::saveToDB() const {
    qDebug()<<"Sauvegarde des projets en base de données";
    if (this->sdb == nullptr || !this->sdb->open() || !this->sdb->transaction())
        throw ProjetManagerException("Aucune base de données chargée en mémoire.");

    QSqlQuery query(*(this->sdb));
    bool res1 = query.exec("DELETE FROM PROJETS");
    if (res1) {

        for (vector<Projet*>::const_iterator it = projets.begin(); it != projets.end(); ++it) {
            Projet* newt = *it;

            QSqlQuery query(*(this->sdb));
            query.prepare("INSERT INTO PROJETS VALUES (:nom, :date_disponibilite)");
            query.bindValue(":nom", newt->getNom());
            query.bindValue(":date_disponibilite", newt->getDispo().isNull()?QVariant(QVariant::String):newt->getDispo().toString("yyyy-MM-dd"));

            if (!query.exec()) {
                this->sdb->rollback();
                qDebug() << query.lastError();
                this->sdb->close();
                throw ProjetManagerException("Erreur lors de la sauvegarde en base de données (insert failed).");
            }
        }
        if (!this->sdb->commit()) {
            this->sdb->close();
            throw ProjetManagerException("Impossible de commiter les changements en base de données.");
        }
    }
    this->sdb->close();
    return true;
}
