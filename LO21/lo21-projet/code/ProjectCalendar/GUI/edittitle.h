#ifndef EDITTITLE_H
#define EDITTITLE_H

#include <QDialog>
#include <QString>

namespace Ui {
class edittitle;
}

class edittitle : public QDialog
{
    Q_OBJECT

public:
    //! Constructeur de edittitle
    explicit edittitle(QWidget *parent = 0);
    //! Destructeur de edittitle
    ~edittitle();

    /**
     * @brief setTitle permet d'attribuer un texte au widget
     * @param t le titre attribué
     */
    void setTitle(QString t);

    /**
     * @brief getTitle permet de recuperer le texte du widget
     * @return le texte représentant le titre
     */
    const QString getTitle() const;

private:
    Ui::edittitle *ui;
};

#endif // EDITTITLE_H
