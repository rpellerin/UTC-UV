#ifndef DATEDISPO_H
#define DATEDISPO_H

#include <QDialog>
#include <QDateEdit>
#include <QDate>

namespace Ui {
class datedispo;
}

class datedispo : public QDialog
{
    Q_OBJECT

public:
    //! Constructeur de datedispo
    explicit datedispo(QWidget *parent = 0);
    //! Destructeur de datedispo
    ~datedispo();

    /**
     * @brief setDate permet d'attribuer une date au widget
     * @param d date a attribuer
     */
    void setDate(QDate d);

    /**
     * @brief getDate permet de recuperer la date du widget
     * @return la date
     */
    const QDate getDate() const;

private:
    Ui::datedispo *ui;
};

#endif // DATEDISPO_H
