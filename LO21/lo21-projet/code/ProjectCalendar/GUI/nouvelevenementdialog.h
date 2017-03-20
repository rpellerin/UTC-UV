#ifndef NOUVELEVENEMENTDIALOG_H
#define NOUVELEVENEMENTDIALOG_H

#include <QDebug>
#include <QDate>
#include <QCalendarWidget>
#include <QTimeEdit>
#include <QDialog>
#include <QTextEdit>
#include <QRadioButton>
#include <QListWidget>
#include <QListWidgetItem>
#include <QLabel>

#include "mainwindow.h"
class MainWindow;

namespace Ui {
class NouvelEvenementDialog;
}

class NouvelEvenementDialog : public QDialog
{
    Q_OBJECT
private:
    Ui::NouvelEvenementDialog *ui;
    QCalendarWidget* jour_debut;
    QCalendarWidget* jour_fin;
    QTimeEdit* heure_debut;
    QTimeEdit* heure_fin;
    QLineEdit* description;
    QLineEdit* lieu;
    QLineEdit* titre;
    QRadioButton* tache;
    QRadioButton* evnmt;
    QListWidget* listetaches;
    QLabel* descriptionlab;
    QLabel* titrelab;
    QLabel* lieulab;


    MainWindow* _parent;


public Q_SLOTS:
    /**
     * @brief toggleEvenementTache permet de savoir quelle buttonbox est cochée
     * @param isEvenementChecked
     */
    void toggleEvenementTache(bool isEvenementChecked);

    /**
     * @brief save permet d'enregistrer l'ajout d'un evenement a l'agenda
     */
    void save();
public:
    //! Constructeur de NouvelEvenementDialog
    explicit NouvelEvenementDialog(QWidget *parent = 0);

    /**
     * @brief setParent asigne un parent au widget
     * @param p la mainwindow parent
     */
    void setParent(MainWindow* p) { this->_parent = p; }

    /**
     * @brief setSelectedDate permet d'attribuer une date aux attributs jour_debut et jour_fin
     * @param date date à asssigner
     */
    void setSelectedDate(const QDate& date) {
        jour_debut->setSelectedDate(date);
        jour_fin->setSelectedDate(date);
    }

    /**
     * @brief setStartTime permet d'attribuer un horaire aux attributs heure_debut et heure_fin
     * @param h l'horaire à assigner
     */
    void setStartTime(QTime& h) {
        heure_debut->setTime(h);
        heure_fin->setTime(h.addSecs(3600));
        if (h.hour() == 23)
            jour_fin->setSelectedDate(jour_fin->selectedDate().addDays(1));
    }
    /**
     * @brief setTaches permet d'afficher les taches du TacheManager dans la liste du widget
     */
    void setTaches();

    //! Destructeur de NouvelEvenementDialog
    ~NouvelEvenementDialog() {
        qDebug()<<"Dialog detruit";
        delete ui;
    }
};

#endif // NOUVELEVENEMENTDIALOG_H
