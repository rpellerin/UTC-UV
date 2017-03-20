#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QTableWidget>
#include <QLineEdit>
#include <QDate>
#include <string>
#include <QString>
#include <QMessageBox>
#include <QTreeWidget>
#include <QDebug>
#include <QDateEdit>
#include <QFileDialog>
#include <QFile>
#include <QXmlStreamWriter>

#include "nouvelevenementdialog.h"
#include "datedispo.h"
#include "projet.h"
#include "projetmanager.h"
#include "Tache/tachemanager.h"
#include "evenementmanager.h"
#include "edittitle.h"
#include "editecheance.h"
#include "timing.h"
#include "editduree.h"
#include "editetat.h"

class NouvelEvenementDialog;

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

    private:
        int currentWeek;
        QDate current_debut; // premier jour de la semaine actuellement affichée
        QDate current_fin; // dernier jour de la semaine actuellement affichée

        // Model
        TacheManager* tm = nullptr;
        ProjetManager* pm = nullptr;
        EvenementManager* em = nullptr;

        // Widgets
        Ui::MainWindow *ui;
        QTableWidget* agenda_widget;
        NouvelEvenementDialog* dialog;
        QDate today;
        QDate currentDate;

        QTreeWidget* projets;
        datedispo* popupdispo;

        // Fonctions
        void resizeEvent(QResizeEvent *event) override;
        void closeEvent(QCloseEvent *event) override;
        void changeCurrentWeek(QDate* date, bool isCurrentDay);
        void resizeAgendaTable() const;
        void attachChildTask(QTreeWidgetItem* item, Tache* t) const;
        void displayEvents(QDate debut, QDate fin);
        void displayProjetsAndTasks() const;

    public:
        /**
         * @brief editTask permet d'ajouter un evenement associé à une tache dans l'EvenementManager
         * @param id titre de la tache
         * @param title titre de l'evenement
         * @param ddebut date de début
         * @param dfin date de fin
         * @param hdebut heure de début
         * @param hfin heure de fin
         */
        void editTask(QString id, QString title, QDate ddebut, QDate dfin, QTime hdebut, QTime hfin);

        /**
         * @brief newEvent permet d'ajouter un evenement simple a l'EvenementManager
         * @param ddebut date de début
         * @param lieu lieu de l'evenement
         * @param dfin date de fin
         * @param hdebut heure de début
         * @param hfin heure de fin
         * @param descr description de l'evenement
         * @param title titre de l'evenement
         */
        void newEvent(QDate ddebut, QString lieu, QDate dfin, QTime hdebut, QTime hfin, QString descr, QString title);

        //! Constructeur de MainWindow
        explicit MainWindow(QWidget *parent = 0);

        /**
         * @brief showError affiche un message d'erreur
         * @param titre titre de l'erreur
         * @param description description de l'erreur
         */
        void showError(QString titre, QString description) {
            QMessageBox::warning(this, titre, description);
        }


        ~MainWindow();



public Q_SLOTS:
        /**
         * @brief show affiche le calendrier avec les evenements/projets enregistrés
         */
        void show();
        /**
         * @brief cellClicked enregistre un evenement dans la case sélectionnée
         * @param row ligne de la case
         * @param column colonne de la case
         */
        void cellClicked(int row, int column);
        /**
         * @brief nextWeek avance d'une semaine
         */
        void nextWeek();
        /**
         * @brief prevWeek recule d'une semaine
         */
        void prevWeek();
        /**
         * @brief setWeek permet de choisir une semaine
         */
        void setWeek(int num);
        /**
         * @brief detruireNouvelEvent ferme le widget NouvelEvenementDialog
         */
        void detruireNouvelEvent();

        /**
         * @brief nouveauProjet permet d'ajouter un nouveau projet a l'agenda (et dans le ProjetManager)
         */
        void nouveauProjet();
        /**
         * @brief onTreeWidgetItemDoubleClicked modifie l'attribut selectionné
         * @param item objet selectionné
         * @param column colonne à modifier
         */
        void onTreeWidgetItemDoubleClicked(QTreeWidgetItem * item, int column);
        /**
         * @brief nouvelleTacheComposite permet d'ajouter une tache composite à l'agenda (et dans le TacheManager)
         */
        void nouvelleTacheComposite();
        /**
         * @brief nouvelleTacheUnitaire permet d'ajouter une tache unitaire à l'agenda (et dans le TacheManager)
         */
        void nouvelleTacheUnitaire();
        /**
         * @brief nouvelleTacheUnitairePreemptable permet d'ajouter une tache unitaire preemptable à l'agenda (et dans le TacheManager)
         */
        void nouvelleTacheUnitairePreemptable();
        /**
         * @brief exportXML permet d'exporter la semaine en XML
         */
        void exportXML(Projet* p = nullptr);
        /**
         * @brief exportProjetXML permet d'exporter l'arbre de projets en XML
         */
        void exportProjetXML();
        /**
         * @brief deleteProjets permet de supprimer tous les projets
         */
        void deleteProjets();
        /**
         * @brief deleteEvents permet de supprimer tous les evenements
         */
        void deleteEvents();
};

#endif // MAINWINDOW_H
