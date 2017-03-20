#ifndef EDITETAT_H
#define EDITETAT_H

#include <QDialog>
#include <QStringList>
#include "Tache/tache.h"
#include "Tache/tacheunitaire.h"
#include "Tache/tacheunitairepreemptable.h"

namespace Ui {
class editetat;
}

class editetat : public QDialog
{
    Q_OBJECT

public:
    explicit editetat(QWidget *parent = 0);
    ~editetat();
    void setTache(TacheUnitaire* T);

private:
    Ui::editetat *ui;
    TacheUnitaire* T;
    TacheUnitairePreemptable* tp;
public Q_SLOTS:
    void commencertache();
    void interrompretache();
    void terminertache();
};

#endif // EDITETAT_H
