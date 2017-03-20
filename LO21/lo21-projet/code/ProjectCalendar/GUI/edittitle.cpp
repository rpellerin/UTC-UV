#include "edittitle.h"
#include "ui_edittitle.h"

edittitle::edittitle(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::edittitle)
{
    ui->setupUi(this);
}
void edittitle::setTitle(QString t) {
  ui->lineEdit->setText(t);
}

const QString edittitle::getTitle() const {
    return (ui->lineEdit->text());
}
edittitle::~edittitle()
{
    delete ui;
}
