#ifndef EDITDUREE_H
#define EDITDUREE_H

#include <QDialog>
#include "timing.h"

namespace Ui {
class editduree;
}

class editduree : public QDialog
{
    Q_OBJECT

public:
    //! Constructeur de editduree
    explicit editduree(QWidget *parent = 0);
    //! Destructeur de editduree
    ~editduree();

    /**
     * @brief setDuree permet d'assigner une durée au widget
     * @param _heure heures a transformer en durée
     * @param _minute minutes a transformer en durée
     */
    void setDuree(QString _heure, QString _minute);

    /**
     * @brief getDuree permet de recuperer la durée du widget
     * @return une durée
     */
    const TIME::Duree& getDuree() const;

private:
    Ui::editduree *ui;
};

#endif // EDITDUREE_H
