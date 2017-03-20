#ifndef EDITECHEANCE_H
#define EDITECHEANCE_H

#include <QDialog>

namespace Ui {
class editecheance;
}

class editecheance : public QDialog
{
    Q_OBJECT

public:
    //! Constructeur de editecheance
    explicit editecheance(QWidget *parent = 0);
    //! Destructeur de editecheance
    ~editecheance();

    /**
     * @brief setDate permer d'attribuer une date au widget
     * @param d la date a attribuer
     */
    void setDate(QDate d);

    /**
     * @brief getDate permet de recuperer la date du widget
     * @return la date
     */
    const QDate getDate() const;

private:
    Ui::editecheance *ui;
};

#endif // EDITECHEANCE_H
