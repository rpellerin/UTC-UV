#include "nouvelevenementdialog.h"
#include "ui_nouvelevenementdialog.h"

NouvelEvenementDialog::NouvelEvenementDialog(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::NouvelEvenementDialog)
{
    ui->setupUi(this);
    jour_debut = ui->jour_debut;
    jour_fin = ui->jour_fin;
    heure_debut = ui->heure_debut;
    heure_fin = ui->heure_fin;
    titre = ui->titre;
    description = ui->description;
    lieu = ui->lieu;
    tache = ui->radioButton_tache;
    evnmt = ui->radioButton_evenement;
    listetaches = ui->listeTaches;
    descriptionlab = ui->label;
    titrelab = ui->label_2;
    lieulab = ui->label_7;
    ui->listeTaches->hide();
    //setParent(new MainWindow);

}

void NouvelEvenementDialog::toggleEvenementTache(bool isEvenementChecked) {
    if (isEvenementChecked){
        listetaches->hide();
        description->show();
        descriptionlab->show();
        lieu->show();
        lieulab->show();
        titre->show();
        titrelab->show();
    }
    else{
        listetaches->show();
        description->hide();
        descriptionlab->hide();;
        lieulab -> hide();
        lieu->hide();
        titre->hide();
        titrelab->hide();
    }
}

void NouvelEvenementDialog::save(){
    QDate ddebut =jour_debut->selectedDate();
    QDate dfin = jour_fin->selectedDate();
    QTime hdebut =heure_debut->time();
    QTime hfin = heure_fin->time();
    QString descr = description->text();
    QString title = titre->text();
    QString _lieu = lieu->text();
    if (evnmt->isChecked()) this->_parent->newEvent(ddebut,_lieu,dfin,hdebut,hfin,descr,title);
    if (tache->isChecked()){
       QList<QListWidgetItem*> selected=listetaches->selectedItems();
       if (selected.size() != 0) {
           title = selected.at(0)->text();
           QString id = selected.at(0)->text();
           this->_parent->editTask(id,title,ddebut,dfin,hdebut,hfin);
       }
       else {
           this->_parent->showError("Project Calendar","Sélectionnez au moins une tâche");
           return;
       }
    }
    accept();
}

void NouvelEvenementDialog::setTaches() {
    TacheManager* tm = TacheManager::getInstance();
    for (TacheManager::iterator it = tm->begin(); it != tm->end(); ++it ){
        if (TacheUnitaire* tu = dynamic_cast<TacheUnitaire*>(*it)) { // teste si c'est une tache unitaire (préemptable ou non)
            bool continuer = true;
            if (!dynamic_cast<TacheUnitairePreemptable*>(*it)) { // c'est une unitaire simple
                EvenementManager* em = EvenementManager::getInstance();
                for (vector<Evenement*>::const_iterator itt = em->begin(); itt != em->end(); ++itt) {
                    Evenement* lala = (*itt);
                    TacheUnitaire* tmp;
                    if (lala->getTacheAssociee() != nullptr && (tmp = dynamic_cast<TacheUnitaire*>(lala->getTacheAssociee()))) {
                        if (tmp == tu) {
                            continuer = false;
                            break;
                        }
                    }
                }
                if (!continuer)
                    continue;
            }

            QListWidgetItem* item = new QListWidgetItem();
            item->setText((*it)->getTitre());
            item->setFlags(Qt::ItemIsSelectable | Qt::ItemIsUserCheckable | Qt::ItemIsEnabled | Qt::ItemIsEditable);
            this->listetaches->addItem(item);}
        }

    }

