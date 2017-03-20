#include "editecheance.h"
#include "ui_editecheance.h"

editecheance::editecheance(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::editecheance)
{
    ui->setupUi(this);
}

editecheance::~editecheance()
{
    delete ui;
}

void editecheance::setDate(QDate d) {
   ui->dateEdit->setDate(d);
}

const QDate editecheance::getDate() const {
    return (ui->dateEdit->date());
}
