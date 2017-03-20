#include "tacheediteur.h"

TacheEditeur::TacheEditeur(Tache &t, QWidget *parent) : t(t), QWidget(parent)
{
    mainLayout = new QVBoxLayout(this);
    w1 = new QWidget();
    mainLayout->addWidget(w1);
    w1Layout = new QHBoxLayout(w1);
    id = new QLabel("identificateur");
    id_text = new QLineEdit(t.getId());
    id_check = new QCheckBox("préemptive");
    id_check->setChecked(t.isPreemptive());
    w1Layout->addWidget(id);
    w1Layout->addWidget(id_text);
    w1Layout->addWidget(id_check);

    w2 = new QWidget;
    mainLayout->addWidget(w2);
    w2Layout = new QHBoxLayout(w2);
    titre = new QLabel("titre");
    titre_text = new QTextEdit(t.getTitre());
    w2Layout->addWidget(titre);
    w2Layout->addWidget(titre_text);

    w3 = new QWidget;
    mainLayout->addWidget(w3);
    w3Layout = new QHBoxLayout(w3);
    dispo_label = new QLabel("disponibilite");
    dispo = new QDateEdit(t.getDateDisponibilite());
    dispo->setMaximumDate(QDate::currentDate().addMonths(1));
    echeance_label = new QLabel("échéance");
    echeance = new QDateEdit(t.getDateEcheance());
    echeance->setMaximumDate(QDate::currentDate().addMonths(1));
    duree_label = new QLabel("durée");
    duree_h = new QSpinBox;
    duree_h->setRange(0, 24);
    duree_h->setSuffix(" heure(s)");
    duree_h->setValue(t.getDuree().getHeure());
    duree_m = new QSpinBox;
    duree_m->setRange(0, 59);
    duree_m->setSuffix("minute(s)");
    duree_m->setValue(t.getDuree().getMinute());
    w3Layout->addWidget(dispo_label);
    w3Layout->addWidget(dispo);
    w3Layout->addWidget(echeance_label);
    w3Layout->addWidget(echeance);
    w3Layout->addWidget(duree_label);
    w3Layout->addWidget(duree_h);
    w3Layout->addWidget(duree_m);

    w4 = new QWidget;
    mainLayout->addWidget(w4);
    w4Layout = new QHBoxLayout(w4);
    annuler = new QPushButton("Annuler");
    sauver = new QPushButton("Sauver");
    sauver->setEnabled(false);
    w4Layout->addWidget(annuler);
    w4Layout->addWidget(sauver);

    QObject::connect(annuler,SIGNAL(clicked()),this,SLOT(close()));
    QObject::connect(sauver,SIGNAL(clicked()),this,SLOT(sauvegarder()));

    QObject::connect(id_text,SIGNAL(textChanged(QString)),this,SLOT(enableSauverButton()));
    QObject::connect(id_check,SIGNAL(stateChanged(int)),this,SLOT(enableSauverButton()));
    QObject::connect(titre_text,SIGNAL(textChanged()),this,SLOT(enableSauverButton()));
    QObject::connect(dispo,SIGNAL(dateChanged(QDate)),this,SLOT(enableSauverButton()));
    QObject::connect(echeance,SIGNAL(dateChanged(QDate)),this,SLOT(enableSauverButton()));
    QObject::connect(duree_h,SIGNAL(valueChanged(int)),this,SLOT(enableSauverButton()));
    QObject::connect(duree_m,SIGNAL(valueChanged(int)),this,SLOT(enableSauverButton()));

}

void TacheEditeur::sauvegarder() {
    if (tm && tm->isTacheExistante(id_text->text()) && QString::compare(t.getId(),id_text->text(), Qt::CaseSensitive) != 0)
        QMessageBox::warning(this,"Attention","Id déja existant");
    else {
        if (QString::compare(t.getId(),id_text->text(), Qt::CaseSensitive) != 0)
            t.setId(id_text->text());
        id_check->isChecked()?t.setPreemptive():t.setNonPreemptive();
        t.setTitre(titre_text->toPlainText());
        t.setDatesDisponibiliteEcheance(dispo->date(),echeance->date());
        t.setDuree(Duree(duree_h->value(),duree_m->value()));
        tm->save(*file);
        QMessageBox::information(this,"Sauvegardé !","Tache sauvée !");
    }
}

void TacheEditeur::enableSauverButton() {
    sauver->setEnabled(true);
}
