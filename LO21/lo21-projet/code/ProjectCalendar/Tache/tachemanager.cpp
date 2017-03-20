#include "tachemanager.h"

void TacheManager::addItem(Tache* t){
    taches.push_back(t);
}

Tache* TacheManager::trouverTache(const QString& id)const{
    for(unsigned int i=0; i<taches.size(); i++)
        if (id==taches[i]->getTitre()) return taches[i];
    return 0;
}

Tache* TacheManager::trouverTacheFromId(const int id)const{
    for(unsigned int i=0; i<taches.size(); i++)
        if (id==taches[i]->getId()) return taches[i];
    return 0;
}

Tache& TacheManager::ajouterTache(QString id, const Duree dur, QDate dispo, QDate deadline, bool preempt){
    Tache* newt = nullptr;

    int id_int = taches.size();

    while (trouverTacheFromId(++id_int)) {}
    if (id == "composite") {
        int num = taches.size();
        do {
            num++;
            id = QString("Nouvelle tache composite %1").arg(num);
        } while (trouverTache(id));
        newt=new TacheComposite(id_int,id,dispo,deadline);
    }
    if (id == "unitaire") {
        int num = taches.size();
        do {
            num++;
            id = QString("Nouvelle tache unitaire %1").arg(num);
        } while (trouverTache(id));
        newt=new TacheUnitaire(id_int,id,dur,dispo,deadline);
    }
    if (id == "preemptable") {
        int num = taches.size();
        do {
            num++;
            id = QString("Nouvelle tache preemptable %1").arg(num);
        } while (trouverTache(id));
        newt=new TacheUnitairePreemptable(id_int,id,dur,dispo,deadline);
    }
    if (trouverTache(id)) throw TacheManagerException("erreur, TacheManager, tache deja existante");
    if (newt == nullptr)
        newt=new TacheUnitaire(id_int,id,dur,dispo,deadline);

    addItem(newt);

    return *newt;
}

TacheManager::~TacheManager(){
    qDebug()<<"TacheManager supprimé";
    for(unsigned int i=0; i<taches.size(); i++) delete taches[i];
}

void TacheManager::loadTaches(){
    taches.clear();
    if (this->sdb == nullptr || !this->sdb->open())
        throw new TacheManagerException("Aucune base de données chargée en mémoire.");

    QSqlQuery query(*(this->sdb));
    bool res1 = query.exec("SELECT * FROM TACHES ORDER BY tache_id ASC");
    if (res1) {
        QSqlRecord rec = query.record();

        int tache_id = rec.indexOf("tache_id");
        int nom = rec.indexOf("nom");
        int projet_conteneur = rec.indexOf("projet_conteneur");
        int tache_conteneur = rec.indexOf("tache_conteneur");
        int date_disponibilite = rec.indexOf("date_disponibilite");
        int is_composite = rec.indexOf("is_composite");
        int is_preemptable = rec.indexOf("is_preemptable");
        int duree = rec.indexOf("duree");
        int etat = rec.indexOf("etat");
        int echeance = rec.indexOf("echeance");

        while (query.next()) {
            qDebug()<<"Tache : "<<query.value(nom).toString()<<" chargée.";
            Tache* t;
            if (query.value(is_composite).toString() == "t")
                t = new TacheComposite(query.value(tache_id).toInt(),
                                       query.value(nom).toString(),
                                       query.value(date_disponibilite).toDate(),
                                       query.value(echeance).toDate());
            else if (query.value(is_preemptable).toString() == "t")
                t = new TacheUnitairePreemptable(
                            query.value(tache_id).toInt(),
                            query.value(nom).toString(),
                            TIME::Duree(query.value(duree).toInt()/60,query.value(duree).toInt()%60),
                            query.value(date_disponibilite).toDate(),
                            query.value(echeance).toDate(),
                            Tache::toEtat(query.value(etat).toString()));
            else
                t = new TacheUnitaire(
                            query.value(tache_id).toInt(),
                            query.value(nom).toString(),
                            TIME::Duree(query.value(duree).toInt()/60,query.value(duree).toInt()%60),
                            query.value(date_disponibilite).toDate(),
                            query.value(echeance).toDate(),
                            Tache::toEtat(query.value(etat).toString()));
            this->addItem(t);
            Projet* p_cont;
            ProjetManager* pm = ProjetManager::getInstance();
            TacheComposite* t_cont;
            if (!query.value(projet_conteneur).isNull() && (p_cont = pm->trouverProjet(query.value(projet_conteneur).toString()))) {
                t->setProjet_conteneur(p_cont);
                p_cont->ajouterTache(t);
            }
            else if (!query.value(tache_conteneur).isNull() && (t_cont = dynamic_cast<TacheComposite*>(trouverTacheFromId(query.value(tache_conteneur).toInt())))) {
                t->setTache_conteneur(t_cont);
                t_cont->ajouterTache(t);
            }
        }
    }

    sdb->close();
}

bool TacheManager::saveToDB() const {
    qDebug()<<"Sauvegarde des tâches en base de données";
    if (this->sdb == nullptr || !this->sdb->open() || !this->sdb->transaction())
        throw TacheManagerException("Aucune base de données chargée en mémoire.");

    QSqlQuery query(*(this->sdb));
    bool res1 = query.exec("DELETE FROM TACHES");
    if (res1) {

        for (vector<Tache*>::const_iterator it = taches.begin(); it != taches.end(); ++it) {
            Tache* newt = *it;

            TacheComposite* tc = dynamic_cast<TacheComposite*>(*it);
            TacheUnitaire* tu = dynamic_cast<TacheUnitaire*>(*it);
            TacheUnitairePreemptable* tp = dynamic_cast<TacheUnitairePreemptable*>(*it);

            QSqlQuery query(*(this->sdb));
            query.prepare("INSERT INTO TACHES VALUES (:tache_id, :nom, :projet_conteneur, :tache_conteneur, :date_disponibilite, :is_composite, :is_preemptable, :duree, :etat, :echeance)");
            query.bindValue(":tache_id", newt->getId());
            query.bindValue(":nom", newt->getTitre());
            query.bindValue(":projet_conteneur", newt->getProjet_conteneur()?newt->getProjet_conteneur()->getNom():QVariant(QVariant::String));
            query.bindValue(":tache_conteneur", newt->getTache_conteneur()?newt->getTache_conteneur()->getId():QVariant(QVariant::String));
            query.bindValue(":date_disponibilite", newt->getDateDisponibilite().isNull()?QVariant(QVariant::String):newt->getDateDisponibilite().toString("yyyy-MM-dd"));
            query.bindValue(":is_composite", tc?"t":"f");
            query.bindValue(":is_preemptable", tp?"t":tc?QVariant(QVariant::String):"f");
            query.bindValue(":duree", tu?tu->getDuree().getDureeEnMinutes():QVariant(QVariant::String));
            query.bindValue(":etat", tu?Tache::etatToString(tu->getEtat()):QVariant(QVariant::String));
            query.bindValue(":echeance", newt->getEcheance().isNull()?QVariant(QVariant::String):newt->getEcheance().toString("yyyy-MM-dd"));

            if (!query.exec()) {
                this->sdb->rollback();
                qDebug() << query.lastError();
                this->sdb->close();
                throw TacheManagerException("Erreur lors de la sauvegarde en base de données (insert failed).");
            }
        }
        if (!this->sdb->commit()) {
            this->sdb->close();
            throw TacheManagerException("Impossible de commiter les changements en base de données.");
        }
    }
    this->sdb->close();
    return true;
}



