#include "datedispo.h"
#include "ui_datedispo.h"

datedispo::datedispo(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::datedispo)
{
    ui->setupUi(this);
}

void datedispo::setDate(QDate d) {
   ui->dateEdit->setDate(d);
}

const QDate datedispo::getDate() const {
    return (ui->dateEdit->date());
}

datedispo::~datedispo()
{
    delete ui;
}
