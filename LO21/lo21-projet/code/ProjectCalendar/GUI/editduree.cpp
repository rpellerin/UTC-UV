#include "editduree.h"
#include "ui_editduree.h"

editduree::editduree(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::editduree)
{
    ui->setupUi(this);
}

editduree::~editduree()
{
    delete ui;
}

const TIME::Duree& editduree::getDuree() const
{
    QString minute = ui->minute->text();
    QString heure = ui->heure->text();
    bool ok;
    int Iheure = heure.toInt(&ok, 10);
    int Iminute = minute.toInt(&ok, 10);
    TIME::Duree dur(Iheure, Iminute);
    return dur;
}


void editduree::setDuree(QString _heure, QString _minute)
{
    ui->heure->setText(_heure);
    ui->minute->setText(_minute);
}
