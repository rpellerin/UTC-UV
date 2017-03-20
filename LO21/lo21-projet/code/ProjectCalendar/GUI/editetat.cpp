#include <QDebug>

#include "editetat.h"
#include "ui_editetat.h"

editetat::editetat(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::editetat)
{
    ui->setupUi(this);
}

editetat::~editetat()
{
    delete ui;
}

void editetat::commencertache()
{
    T->commencer();
    accept();
}

void editetat::interrompretache()
{
    if (tp->isInterrompue())
        tp->reprendre();
    else
        tp->interrompre();
    accept();
}

void editetat::terminertache()
{
    T->terminer();
    accept();
}

void editetat::setTache(TacheUnitaire* T){
    this->T = T;
    ui->interrompre->hide();
    qDebug()<<"ok";
    if (this->tp = dynamic_cast<TacheUnitairePreemptable*>(this->T)) { // unitaire preemptable
        qDebug()<<Tache::etatToString(tp->getEtat());
        if (!tp->isTerminee() && !tp->isNonCommencee())
            ui->interrompre->show();
        if (tp->isInterrompue())
            ui->interrompre->setText("Reprendre");
        else if (tp->isCommencee() || tp->isReprise())
            ui->interrompre->setText("Interrompre");
    }
    if (this->T->isCommencee())
        ui->commencer->hide();
    else
        ui->commencer->show();
    if (this->T->isTerminee()) {
        ui->terminer->hide();
        ui->dejaterminee->show();
    }else{
        ui->dejaterminee->hide();
        ui->terminer->show();
    }
}



