#include <QApplication>
#include <QWidget>
#include <QTextEdit>
#include <QPushButton>
#include <QLineEdit>
#include <QVBoxLayout>
#include <QFileDialog>
#include <QDebug>

#include "Calendar.h"
#include "tacheediteur.h"

int main(int argc, char * argv[]) {
    QApplication app(argc, argv);

    // EXO 28
//    QWidget fenetre;
//    fenetre.setFixedSize(200, 200);

//    QVBoxLayout layout(&fenetre);

//    QLineEdit identificateur(&fenetre);
//    identificateur.setFixedWidth(180);
//    //identificateur.move(10,10);
//    layout.addWidget(&identificateur);

//    QTextEdit titre(&fenetre);
//    titre.setFixedSize(180,110);
//    //titre.move(10,45);
//    layout.addWidget(&titre);

//    QPushButton save("Sauver", &fenetre);
//    save.setFixedWidth(80);
//    //save.move(10,170);
//    layout.addWidget(&save);

//    //fenetre.setLayout(&layout);

//    QString file = QFileDialog::getOpenFileName(0,QString(),"../TD7");
//    qDebug()<<file;
//    TacheManager& tm = TacheManager::getInstance();

//    try {tm.load(file);}
//    catch(...){}
//    Tache& t = tm.getTache("T1");

//    identificateur.setText(t.getId());
//    titre.setText(t.getTitre());

//    fenetre.show();

    // EXO 29
    TacheManager& m=TacheManager::getInstance();
    QString chemin = QFileDialog::getOpenFileName(0,QString(),"../TD7");
    m.load(chemin);
    Tache& t=m.getTache("T2");
    TacheEditeur fenetre(t);
    fenetre.setTacheManager(&m);
    fenetre.setFile(&chemin);
    fenetre.show();

    return app.exec();
}
