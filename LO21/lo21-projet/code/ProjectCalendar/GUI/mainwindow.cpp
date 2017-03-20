#include <time.h>

#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <unistd.h>

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    dialog = NULL;

    this->currentWeek = 0;
    ui->setupUi(this);
    ui->tabWidget->setCurrentIndex(this->currentWeek); // force l'affichage de Agenda par défaut
    agenda_widget = ui->agenda_widget;
    agenda_widget->horizontalHeader()->setStyleSheet("background: white;");
    agenda_widget->verticalHeader()->setStyleSheet("background: white;");
    agenda_widget->verticalHeader()->setDefaultAlignment(Qt::AlignTop);

    today = QDate::currentDate();
    currentDate = QDate::currentDate();

    QString formatter = QString("d MMM yyyy");
    for (int i = -500; i < 500; i++) {
        ui->choose_week->addItem(today.addDays(i*7 - (today.dayOfWeek()-1)).toString(formatter)+" - "+today.addDays(i*7 - (today.dayOfWeek()-1) + 6).toString(formatter));
    }

    projets = ui->treeWidget;
    projets->header()->resizeSection(1,200); // aggrandit Nom
    projets->header()->resizeSection(0,180);

    tm =  TacheManager::getInstance();
    pm =  ProjetManager::getInstance();
    em = EvenementManager::getInstance();
}

void MainWindow::nextWeek() {
    currentDate = currentDate.addDays(7);
    ui->choose_week->setCurrentIndex(ui->choose_week->currentIndex()+1);
}

void MainWindow::prevWeek() {
    currentDate = currentDate.addDays(-7);
    ui->choose_week->setCurrentIndex(ui->choose_week->currentIndex()-1);
}

void MainWindow::setWeek(int num) {
    this->currentWeek = num;
    currentDate = today.addDays((num-500)*7);
    qDebug()<<"added "<<QString::number((num-500)*7)<< " jours";
    changeCurrentWeek(&currentDate, today == currentDate);
}

/**
 * @brief MainWindow::changeCurrentWeek Change la tableau (de la semaine) affiché actuellement
 * @param date
 * @param isCurrentDay
 */
void MainWindow::changeCurrentWeek(QDate* date, bool isCurrentDay) {
    int dayOfWeek = date->dayOfWeek();
    int counter = -dayOfWeek;
    QString formatter = QString("ddd d MMM");
    for (int i = 0 ; i<7; i++) {
        counter++;
        agenda_widget->horizontalHeaderItem(i)->setText(date->addDays(counter).toString(formatter));
    }
    QFont font;
    if (isCurrentDay)
        font.setBold(true);

    agenda_widget->horizontalHeaderItem(dayOfWeek-1)->setFont(font);
    displayEvents(date->addDays(-dayOfWeek+1),date->addDays(-dayOfWeek+7));
}



void MainWindow::resizeEvent(QResizeEvent *event) {
    qDebug()<<"RESIZE EVENT ";
    resizeAgendaTable();

    QMainWindow::resizeEvent(event);
}

void MainWindow::show()
{
    ui->choose_week->setCurrentIndex(500);
    displayProjetsAndTasks();
    QMainWindow::show();
    resizeAgendaTable();
}

void MainWindow::resizeAgendaTable() const {
    const int width = agenda_widget->width() - agenda_widget->verticalHeader()->width() - 20;
    unsigned int columnCount = agenda_widget->columnCount();
    for (unsigned int i = 0; i < columnCount; ++i)
        agenda_widget->setColumnWidth(i, width/columnCount);
}

void MainWindow::cellClicked(int row, int column) {
    qDebug()<<"Click cell "<<row<<" ; "<<column;
    if (agenda_widget->item(row,column)) return;
    dialog = new NouvelEvenementDialog;
    dialog->setParent(this);
    dialog->setSelectedDate(currentDate.addDays(1-currentDate.dayOfWeek()+column));
    QTime a(row,0);
    dialog->setStartTime(a);
    connect(dialog, SIGNAL(finished(int)), this, SLOT(detruireNouvelEvent()));
    dialog->setTaches(); // ajoute la liste des taches dispo dans l'UI
    dialog->show();
}


void MainWindow::closeEvent(QCloseEvent *event) {
    qDebug()<<"MainWindow fermée";
    QMainWindow::closeEvent(event);
    QApplication::quit();
}

void MainWindow::detruireNouvelEvent() {
    delete dialog;
    dialog = 0;
}

void MainWindow::nouveauProjet() {
    Projet& proj = pm->ajouterProjet();

    try {
        pm->saveToDB();
    } catch (ProjetManagerException e) {
        showError("Project Calendar", e.getInfo());
        tm->clearAll();
        pm->clearAll();
        pm->loadProjets();
        tm->loadTaches();
        em->clearAll();
        em->loadEvents();
        displayEvents(this->current_debut,this->current_fin);
    }
    displayProjetsAndTasks();
}


void MainWindow::onTreeWidgetItemDoubleClicked(QTreeWidgetItem * item, int column)
{
    if (column==1) { // nom
        QString id = item->text(1);
        edittitle* edittitre =  new edittitle(this);
        edittitre->setTitle(id);
        if (edittitre->exec() == QDialog::Accepted) {
            QString newTitre = edittitre->getTitle();
            if(item->text(0)=="Projet"){
                Projet* tmp = pm->trouverProjet(id);
                tmp->setNom(newTitre);
            }
            else{
                Tache* tmp = tm->trouverTache(id);
                tmp->setTitre(newTitre);
            }

        }
        delete edittitre;

    }
    if (column==2) { // date dispo
        QString id = item->text(1);
        popupdispo = new datedispo(this);
        popupdispo->setDate(QDate::fromString(item->text(2),Qt::TextDate));
        if (popupdispo->exec() == QDialog::Accepted) {
            QDate dateDisponible = popupdispo->getDate();
            if(item->text(0)=="Projet"){
                Projet* tmp = pm->trouverProjet(id);
                tmp->setDispo(dateDisponible);
            }
            else{
                Tache* tmp = tm->trouverTache(id);
                tmp->setDateDisponibilite(dateDisponible);
            }
        }
        delete popupdispo;

    }
    if (column==3 && item->text(0) != "Projet" && item->text(0) != "Tâche Composite") { //durée
        QString id = item->text(1);
        editduree* duree =  new editduree(this);
        if (duree->exec() == QDialog::Accepted) {
            TIME::Duree dur = duree->getDuree();
            TacheUnitaire* tmp = dynamic_cast<TacheUnitaire*>(tm->trouverTache(id));
            try{tmp->setDuree(dur);}catch(TacheException e){
               showError("Project Calendar", "\n Une tache unitaire non preemptable ne peut durer plus de 12h");
            }
        }
        delete duree;

    }
    if (column==4 && item->text(0) != "Projet") { // echeance
        QString id = item->text(1);
        editecheance* echeance =  new editecheance(this);
        echeance->setDate(QDate::fromString(item->text(4),Qt::TextDate));
        if (echeance->exec() == QDialog::Accepted) {
            QDate dateEcheance = echeance->getDate();
            Tache* tmp = tm->trouverTache(id);
            tmp->setEcheance(dateEcheance);
        }
        delete echeance;

    }
    if (column==5 && (item->text(0) == "Tâche unitaire" || item->text(0) == "Tâche unitaire préemptable") && item->text(0) != "Tâche Composite") { // etat
        QString id = item->text(1);
        editetat* etat =  new editetat(this);
        TacheUnitaire* tmp = dynamic_cast<TacheUnitaire*>(tm->trouverTache(id));
        etat->setTache(tmp);
        if (etat->exec() == QDialog::Accepted) {
            // do nothing
        }
        delete etat;
    }
    try {
        tm->saveToDB();
        pm->saveToDB();
    }catch (ProjetManagerException e) {
        showError("Project Calendar", e.getInfo()+"\nLes noms de projets doivent être uniques.");
        tm->clearAll();
        pm->clearAll();
        pm->loadProjets();
        tm->loadTaches();
        em->clearAll();
        em->loadEvents();
        displayEvents(this->current_debut,this->current_fin);
    }
    catch (TacheManagerException e) {
        showError("Project Calendar", e.getInfo()+"\nDeux tâches contenues dans la même tache ou le même projet ne peuvent avoir le même nom.");
        tm->clearAll();
        pm->clearAll();
        pm->loadProjets();
        tm->loadTaches();
        em->clearAll();
        em->loadEvents();
        displayEvents(this->current_debut,this->current_fin);
    }

    displayProjetsAndTasks();
}

void MainWindow::nouvelleTacheComposite()
{
    QList<QTreeWidgetItem*> selected = projets->selectedItems();
    if (selected.size() != 0 && ((selected.at(0)->text(0) == "Projet") || (dynamic_cast<TacheComposite*>(tm->trouverTache(selected.at(0)->text(1)))))) {
        Tache& tach = tm->ajouterTache("composite");
        if (selected.at(0)->text(0) == "Projet") {
            QString id = selected.at(0)->text(1);
            Projet* proj = pm->trouverProjet(id);
            try{proj->ajouterTache(&tach);}catch(ProjetException e){qDebug()<<e.get_info();}
            tach.setProjet_conteneur(proj);
        }
        else {
            TacheComposite* task = dynamic_cast<TacheComposite*>(tm->trouverTache(selected.at(0)->text(1)));
            task->ajouterTache(&tach);
            tach.setTache_conteneur(task);
        }

        try {
            tm->saveToDB();
        } catch (TacheManagerException e) {
            showError("Project Calendar", e.getInfo());
            tm->clearAll();
            pm->clearAll();
            pm->loadProjets();
            tm->loadTaches();

            em->clearAll();
            em->loadEvents();
            displayEvents(this->current_debut,this->current_fin);
        }
        displayProjetsAndTasks();
    }
}

void MainWindow::nouvelleTacheUnitaire()
{
    QList<QTreeWidgetItem*> selected = projets->selectedItems();
    if (selected.size() != 0 && (selected.at(0)->text(0) == "Projet" || dynamic_cast<TacheComposite*>(tm->trouverTache(selected.at(0)->text(1))))) {
        Tache& tach = tm->ajouterTache("unitaire");
        if (selected.at(0)->text(0) == "Projet") {
            QString id = selected.at(0)->text(1);
            Projet* proj = pm->trouverProjet(id);
            try{proj->ajouterTache(&tach);}catch(ProjetException e){qDebug()<<e.get_info();}
            tach.setProjet_conteneur(proj);
        }
        else {
            TacheComposite* task = dynamic_cast<TacheComposite*>(tm->trouverTache(selected.at(0)->text(1)));
            task->ajouterTache(&tach);
            tach.setTache_conteneur(task);
        }
        try {
            tm->saveToDB();
        } catch (TacheManagerException e) {
            showError("Project Calendar", e.getInfo());
            tm->clearAll();
            pm->clearAll();
            pm->loadProjets();
            tm->loadTaches();
            em->clearAll();
            em->loadEvents();
            displayEvents(this->current_debut,this->current_fin);
        }
        displayProjetsAndTasks();
    }

}

void MainWindow::nouvelleTacheUnitairePreemptable()
{
    QList<QTreeWidgetItem*> selected = projets->selectedItems();
    if (selected.size() != 0 && (selected.at(0)->text(0) == "Projet" || dynamic_cast<TacheComposite*>(tm->trouverTache(selected.at(0)->text(1))))) {
        Tache& tach = tm->ajouterTache("preemptable");
        if (selected.at(0)->text(0) == "Projet") {
            QString id = selected.at(0)->text(1);
            Projet* proj = pm->trouverProjet(id);
            try{proj->ajouterTache(&tach);}catch(ProjetException e){qDebug()<<e.get_info();}
            tach.setProjet_conteneur(proj);
        }
        else {
            TacheComposite* task = dynamic_cast<TacheComposite*>(tm->trouverTache(selected.at(0)->text(1)));
            task->ajouterTache(&tach);
            tach.setTache_conteneur(task);
        }

        try {
            tm->saveToDB();
        } catch (TacheManagerException e) {
            showError("Project Calendar", e.getInfo());
            tm->clearAll();
            pm->clearAll();
            pm->loadProjets();
            tm->loadTaches();
            em->clearAll();
            em->loadEvents();
            displayEvents(this->current_debut,this->current_fin);
        }
        displayProjetsAndTasks();
    }

}

void MainWindow::displayEvents(QDate debut, QDate fin) {
    this->current_debut = debut;
    this->current_fin = fin;
    QColor color(130,130,255);
    agenda_widget->clearContents();
    if (this->em) {
        qDebug()<<"Mise à jour des events de la vue de la fenêtre principale";
        vector<Evenement*> v = em->getFromPeriod(debut,fin);
        Evenement* ev;
        for (vector<Evenement*>::const_iterator it = v.begin(); it != v.end(); ++it) {
            color.setRgb(((color.red()+rand())%150)+106,((color.green()+rand())%150)+106,((color.blue()+rand())%150)+106); // que des couleurs claires
            ev = *it;
            int start_colonne = debut.daysTo(ev->getDateDebut());
            int end_colonne = debut.daysTo(ev->getDateFin());
            int start_ligne = ev->getHeureDebut().hour();
            int end_ligne = ev->getHeureFin().hour()-1;

            int i = start_colonne>=0?start_colonne:0;
            int i_end = end_colonne<=6?end_colonne:agenda_widget->columnCount()-1;

            int j = start_colonne>=0?start_ligne:0;
            int j_end = end_colonne<=6?end_ligne:agenda_widget->rowCount()-1;
            int k = j;
            for (i;i<=i_end;i++) {
                for (k; k<agenda_widget->rowCount()&&(i!=i_end||k<=j_end); k++) {
                    //qDebug()<<"i:"<<i<<"\t k:"<<k;
                    QTableWidgetItem* item;
                    if ((item = agenda_widget->item(k,i)) == nullptr) {
                        agenda_widget->setItem(k,i, new QTableWidgetItem(ev->getTitre()));
                        agenda_widget->item(k,i)->setBackgroundColor(color);
                        //agenda_widget->item(k,i)->setFlags(agenda_widget->item(k,i)->flags() ^ Qt::ItemIsEditable);
                    }
                    else {
                        item->setText(item->text()+" ; "+ev->getTitre());
                        QColor tmp = item->backgroundColor();
                        tmp.setRgb((tmp.red()+color.red())/2,(tmp.green()+color.green())/2,(tmp.blue()+color.blue())/2);
                        item->setBackgroundColor(tmp);
                    }
                }
                k = 0;
            }
        }
    }

}

void MainWindow::attachChildTask(QTreeWidgetItem* item, Tache* t) const {
    for (TacheManager::iterator it = tm->begin(); it != tm->end(); ++it) {
        QTreeWidgetItem* tache_item = new QTreeWidgetItem();
        Tache* tmp = *it;
        if (!tmp->getTache_conteneur() || tmp->getTache_conteneur()->getId() != t->getId()) {
            delete tache_item;
            continue;
        }

        tache_item->setText(1,tmp->getTitre());
        tache_item->setText(2,tmp->getDateDisponibilite().toString());
        if (TacheComposite* tc = dynamic_cast<TacheComposite*>(*it)) {
            tache_item->setFlags(Qt::ItemIsSelectable | Qt::ItemIsUserCheckable | Qt::ItemIsEnabled | Qt::ItemIsEditable);
            tache_item->setText(0,"Tâche composite");
        } else {
            TacheUnitaire* tu = dynamic_cast<TacheUnitaire*>(*it);
            tache_item->setText(5,Tache::etatToString(tu->getEtat()).replace("_"," "));
            if (TacheUnitairePreemptable* tp = dynamic_cast<TacheUnitairePreemptable*>(*it))
                tache_item->setText(0,"Tâche unitaire préemptable");
            else
                tache_item->setText(0,"Tâche unitaire");

            tache_item->setText(3,QString::number((dynamic_cast<TacheUnitaire*>(*it))->getDuree().getDureeEnMinutes())+"min");
            tache_item->setFlags(Qt::ItemNeverHasChildren |Qt::ItemIsSelectable | Qt::ItemIsUserCheckable | Qt::ItemIsEnabled | Qt::ItemIsEditable);
        }
        tache_item->setText(4,tmp->getEcheance().toString());
        item->addChild(tache_item);
        item->setExpanded(true);

        attachChildTask(tache_item, tmp);
    }
}

void MainWindow::displayProjetsAndTasks() const {
    this->projets->clear();
    qDebug()<<"Mise à jour des taches et projets de la vue de la fenêtre principale";
    for (ProjetManager::iterator it = pm->begin(); it != pm->end(); ++it ){
        QTreeWidgetItem* item = new QTreeWidgetItem();
        item->setText(0,"Projet");
        item->setText(1,(*it)->getNom());
        item->setText(2,(*it)->getDispo().toString());
        item->setText(4,(*it)->getEcheance().toString());
        item->setFlags(Qt::ItemIsSelectable | Qt::ItemIsUserCheckable | Qt::ItemIsEnabled | Qt::ItemIsEditable);
        this->projets->addTopLevelItem(item);

        for (TacheManager::iterator itt = tm->begin(); itt != tm->end(); ++itt) {
            QTreeWidgetItem* tache_item = new QTreeWidgetItem();
            Tache* t = *itt;
            if (!t->getProjet_conteneur() ||t->getProjet_conteneur()->getNom() != (*it)->getNom()) {
                delete tache_item;
                continue;
            }

            tache_item->setText(1,t->getTitre());
            tache_item->setText(2,t->getDateDisponibilite().toString());
            tache_item->setText(4,t->getEcheance().toString());
            if (TacheComposite* tc = dynamic_cast<TacheComposite*>(*itt)) {
                tache_item->setText(0,"Tâche composite");
                tache_item->setFlags(Qt::ItemIsSelectable | Qt::ItemIsUserCheckable | Qt::ItemIsEnabled | Qt::ItemIsEditable);
            } else {
                tache_item->setText(5,Tache::etatToString(dynamic_cast<TacheUnitaire*>(*itt)->getEtat()).replace("_"," "));
                if (TacheUnitairePreemptable* tp = dynamic_cast<TacheUnitairePreemptable*>(*itt))
                    tache_item->setText(0,"Tâche unitaire préemptable");
                else
                    tache_item->setText(0,"Tâche unitaire");

                tache_item->setText(3,QString::number((dynamic_cast<TacheUnitaire*>(*itt))->getDuree().getDureeEnMinutes())+"min");
                tache_item->setFlags(Qt::ItemNeverHasChildren | Qt::ItemIsSelectable |Qt::ItemIsUserCheckable | Qt::ItemIsEnabled | Qt::ItemIsEditable);
            }
            item->addChild(tache_item);
            item->setExpanded(true);
            attachChildTask(tache_item, *itt);
        }
    }
}

void MainWindow::editTask(QString id, QString title, QDate ddebut, QDate dfin, QTime hdebut, QTime hfin)
{
    try {
        Evenement& evnt = em->ajouterEvenement(title,ddebut,hdebut,dfin,hfin);
        Tache* tmp = tm->trouverTache(id);
        evnt.setTacheAssociee(tmp);
        em->saveToDB();
    }catch (EvenementException e){
        showError("Project Calendar", "Les dates/heures doivent être valides (début >= fin)");
        em->clearAll();
        em->loadEvents();
    }
    catch (EvenementManagerException e){
        showError("Project Calendar", e.getInfo()+"\nAssurez-vous d'avoir bien rempli tous les champs.");
        em->clearAll();
        em->loadEvents();
    }

    displayEvents(this->current_debut,this->current_fin);
}

void MainWindow::newEvent(QDate ddebut, QString lieu, QDate dfin, QTime hdebut, QTime hfin, QString descr, QString title)
{
    try{
        em->ajouterEvenement(title,ddebut,hdebut,dfin,hfin,lieu,descr);
        em->saveToDB();
    } catch (EvenementException e){
        showError("Project Calendar", "Les dates/heures doivent être valides (début >= fin)");
        em->clearAll();
        em->loadEvents();
    }
    catch (EvenementManagerException e) {
        showError("Project Calendar", e.getInfo()+"\nAssurez-vous d'avoir bien rempli tous les champs.");
        em->clearAll();
        em->loadEvents();
    }
    displayEvents(this->current_debut,this->current_fin);
}

void MainWindow::exportXML(Projet* p) {
    vector<Evenement*> events;
    if (p) {
        qDebug()<<"Export depuis un projet";
        events = em->getFromProjet(p);
    }
    else
        events = em->getFromPeriod(this->current_debut,this->current_fin);

    QString filename = QFileDialog::getSaveFileName(this,"Exporter la semaine en XML",QDir::homePath(), "XML files (*.xml)");
    if (filename != "") {
        QFile newfile(filename);
        if (!newfile.open(QIODevice::WriteOnly | QIODevice::Text)) {
            showError("Project Calendar", "Impossible d'ouvrir le fichier de destination");
            return;
        }
        QXmlStreamWriter stream(&newfile);
        stream.setAutoFormatting(true);
        stream.writeStartDocument();
        stream.writeStartElement("evenements");

        for (vector<Evenement*>::const_iterator it = events.begin(); it != events.end(); ++it){
            Evenement* ev = *it;
            stream.writeStartElement("evenement");
            stream.writeAttribute("id", (QString::number(ev->getId())));
            stream.writeTextElement("titre",ev->getTitre());
            stream.writeTextElement("description",ev->getDescription());
            stream.writeTextElement("lieu",ev->getLieu());
                stream.writeStartElement("debut");
                    stream.writeTextElement("jour",ev->getDateDebut().toString("yyyy-MM-dd"));
                    stream.writeTextElement("heure",ev->getHeureDebut().toString("hh:mm"));
                stream.writeEndElement();
                stream.writeStartElement("fin");
                    stream.writeTextElement("jour",ev->getDateFin().toString("yyyy-MM-dd"));
                    stream.writeTextElement("heure",ev->getHeureFin().toString("hh:mm"));
                stream.writeEndElement();
            stream.writeEndElement();
        }
        stream.writeEndElement();
        stream.writeEndDocument();
        newfile.close();
        QMessageBox::information(this, "Project Calendar", "Exporté !");
    }
    else
        showError("Project Calendar", "Opération annulée");
}

void MainWindow::exportProjetXML() {
    QList<QTreeWidgetItem*> selected = projets->selectedItems();
    qDebug()<<"ok";
    if (selected.size() != 0 && selected.at(0)->text(0) == "Projet") {
        qDebug()<<"ok1";
        QString id = selected.at(0)->text(1);
        qDebug()<<id;
        Projet* proj = pm->trouverProjet(id);
        exportXML(proj);
    }
    else {
        showError("Project Calendar","Selectionnez d'abord un projet dont vous voulez exporter les programmations");
        return;
    }
}

void MainWindow::deleteProjets() {
    tm->clearAll();
    tm->saveToDB();

    pm->clearAll();
    pm->saveToDB();

    em->clearAll();
    em->loadEvents();

    displayEvents(this->current_debut,this->current_fin);
    displayProjetsAndTasks();
}

void MainWindow::deleteEvents() {
    em->clearAll();
    em->saveToDB();

    displayEvents(this->current_debut,this->current_fin);
}

MainWindow::~MainWindow()
{
    qDebug()<<"MainWindow detruite";
    delete dialog;
    delete ui;
}
