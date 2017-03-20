CONFIG += c++11

QT += widgets
QT += sql

SOURCES += \
    main.cpp \
    projet.cpp \
    evenement.cpp \
    Tache/tachecomposite.cpp \
    Tache/tacheunitaire.cpp \
    Tache/tacheunitairepreemptable.cpp \
    timing.cpp \
    GUI/mainwindow.cpp \
    GUI/nouvelevenementdialog.cpp \
    Tache/tachemanager.cpp \
    GUI/datedispo.cpp \
    projetmanager.cpp \
    evenementmanager.cpp \
    GUI/edittitle.cpp \
    GUI/editecheance.cpp \
    GUI/editduree.cpp \
    GUI/editetat.cpp

HEADERS += \
    projet.h \
    evenement.h \
    Tache/tache.h \
    Tache/tachecomposite.h \
    Tache/tacheunitaire.h \
    Tache/tacheunitairepreemptable.h \
    timing.h \
    GUI/mainwindow.h \
    GUI/nouvelevenementdialog.h \
    Tache/tachemanager.h \
    GUI/datedispo.h \
    projetmanager.h \
    evenementmanager.h \
    GUI/edittitle.h \
    GUI/editecheance.h \
    GUI/editduree.h \
    GUI/editetat.h

FORMS += \
    GUI/mainwindow.ui \
    GUI/nouvelevenementdialog.ui \
    GUI/datedispo.ui \
    GUI/edittitle.ui \
    GUI/editecheance.ui \
    GUI/editduree.ui \
    GUI/editetat.ui

RESOURCES = application.qrc
