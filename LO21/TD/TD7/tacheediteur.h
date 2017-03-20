#ifndef TACHEEDITEUR_H
#define TACHEEDITEUR_H

#include <QWidget>
#include <QVBoxLayout>
#include <QHBoxLayout>
#include <QLineEdit>
#include <QTextEdit>
#include <QSpinBox>
#include <QDateEdit>
#include <QLabel>
#include <QCheckBox>
#include <QPushButton>
#include <QMessageBox>

#include "Calendar.h"

class TacheEditeur : public QWidget
{
    Q_OBJECT

private:
    Tache& t;
    TacheManager* tm;
    QString* file;

    // Widgets
    QVBoxLayout* mainLayout;
      QWidget* w1;
        QHBoxLayout* w1Layout;
          QLabel* id;
          QLineEdit* id_text;
          QCheckBox* id_check;
      QWidget* w2;
        QHBoxLayout* w2Layout;
          QLabel* titre;
          QTextEdit* titre_text;
      QWidget* w3;
        QHBoxLayout* w3Layout;
          QLabel* dispo_label;
          QDateEdit* dispo;
          QLabel* echeance_label;
          QDateEdit* echeance;
          QLabel* duree_label;
          QSpinBox* duree_h;
          QSpinBox* duree_m;
      QWidget* w4;
        QHBoxLayout* w4Layout;
          QPushButton* annuler;
          QPushButton* sauver;


public:
    explicit TacheEditeur(Tache& t, QWidget *parent = 0);
    void setTacheManager(TacheManager* _tm) {
        this->tm = _tm;
    }
    void setFile(QString* file) {
        this->file = file;
    }

signals:

public slots:
          void sauvegarder();
          void enableSauverButton();
};

#endif // TACHEEDITEUR_H
