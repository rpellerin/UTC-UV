#include <QApplication>
#include <QDebug>
#include <QDir>
#include <QtSql/QSqlDatabase>
#include <QtSql/QSqlQuery>
#include <QSqlError>
#include <QSqlRecord>
#include <QVariant>
#include <QList>

#include "GUI/mainwindow.h"
#include "Tache/tachemanager.h"
#include "projet.h"
#include "projetmanager.h"
#include "evenementmanager.h"

int main(int argc, char *argv[])
{
    QApplication app(argc, argv);
    MainWindow fenetre;

    TacheManager* tm = TacheManager::getInstance();
    ProjetManager* pm = ProjetManager::getInstance();
    EvenementManager* em = EvenementManager::getInstance();

    // Creates directory if non existent
    QDir directory(QDir::homePath()+QDir::separator()+".projectCalendar");
    directory.mkdir(directory.path());

    // Connect to DB
    QSqlDatabase sdb = QSqlDatabase::addDatabase("QSQLITE");
    sdb.setDatabaseName(directory.absolutePath()+QDir::separator()+"projets_taches.sqlite");

    if (sdb.open()) {
        QSqlQuery query1(sdb);
        bool res1 = query1.exec("SELECT name FROM sqlite_master WHERE type='table' AND name='PROJETS'");

        QSqlQuery query2(sdb);
        bool res2 = query2.exec("SELECT name FROM sqlite_master WHERE type='table' AND name='TACHES'");

        QSqlQuery query3(sdb);
        bool res3 = query3.exec("SELECT name FROM sqlite_master WHERE type='table' AND name='EVENEMENTS'");

        QSqlQuery query4(sdb);
        bool res4 = query4.exec("SELECT name FROM sqlite_master WHERE type='table' AND name='TACHES_PRECEDENTES'");

        if (!res1 || !res2 || !res3 || !res4 ||
                !query1.next() || !query2.next() || !query3.next() || !query4.next()) {
            query1.clear();
            query2.clear();
            qDebug()<<"Invalid DB. Creating new one...";
            fenetre.showError("Project Calendar", "La base de donnée locale actuelle est vide ou invalide. Elle va être recréée. Vous êtes susceptible de perdre des données.");
            QFile sql_ldd(":/assets/TABLES.sql");
            if (!sql_ldd.open(QIODevice::ReadOnly | QIODevice::Text)) {
                fenetre.showError("Project Calendar", "Erreur lors de la lecture de TABLES.sql");
                sdb.close();
                app.exit(0);
                return 0;
            }
            QTextStream in(&sql_ldd);
            QString sql = in.readAll();
            QStringList sqlStatements = sql.split(';', QString::SkipEmptyParts);
            int successCount = 0;

            foreach(const QString& statement, sqlStatements) {
                if (statement.trimmed() != "") {
                    QSqlQuery query(sdb);
                    if (query.exec(statement))
                        successCount++;
                    else
                        qDebug() << "Failed:" << statement << "\nReason:" << query.lastError();
                }
            }
            qDebug()<<"Successfully";
        }
        query1.clear();
        query2.clear();
    }
    else {
        fenetre.showError("Project Calendar", "Impossible de se connecter à la base de donnée locale");
        app.exit(0);
        return 0;
    }
    sdb.close();

    try {
        pm->setDatabase(&sdb);
        pm->loadProjets();

        tm->setDatabase(&sdb);
        tm->loadTaches();

        em->setDatabase(&sdb);
        em->loadEvents();
    } catch (EvenementManagerException& e) {
        fenetre.showError("Project Calendar", e.getInfo());
        app.exit(0);
        return 0;
    }

    fenetre.show();

    return app.exec();
}
