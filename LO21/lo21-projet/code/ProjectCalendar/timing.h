#if !defined(CTIME)
#define CTIME

#include<iostream>
#include<iomanip>

namespace TIME {
    /*! \class TimeException
            \brief Classe permettant de gérer les exceptions des classes du namespace TIME
    */
    class TimeException{
    public:
        //! Constructeur à partir d'une string
        TimeException(const std::string& m):info(m){}
        const std::string& GetInfo() const { return info; } //<! Retourne l'information stockée dans la classe
    private:
        std::string info;
    };



    /*! \class Duree
            \brief Classe permettant de manipuler des durees
            L'utilisation de cette classe nécessite des dates valides au sens commun du terme.
            Déclenchement d'exception dans le cas contraire
    */
    class Duree{
    public:
        //! Constructeur à partir de heure et minute
        /*! \param h heure avec h>=0
            \param m minute avec 0<=m<=59
            */
        Duree(unsigned int h, unsigned int m):nb_minutes(h*60+m) {if (m>59) throw TimeException("erreur: initialisation duree invalide");}
        //! Constructeur à partir de minute
        /*! \param m minute avec m>=0
            */
        Duree(unsigned int m=0):nb_minutes(m) {}
        void setDuree(unsigned int heures, unsigned int minutes) { if (minutes>59) throw TimeException("erreur: initialisation duree invalide"); nb_minutes=heures*60+minutes; }
        void setDuree(unsigned int minutes) {
            setDuree(minutes/60,minutes%60);
        }
        unsigned int getDureeEnMinutes() const { return nb_minutes; } //<!Retourne la duree en minutes
        double getDureeEnHeures() const { return double(nb_minutes)/60; } //<!Retourne la duree en heures
        void afficher(std::ostream& f=std::cout) const { f<<std::setfill('0')<<std::setw(2)<<nb_minutes/60<<"H"<<std::setw(2)<<nb_minutes%60<<std::setfill(' '); } //<!Affiche la duree sous le format hhHmm
    private:
        unsigned int nb_minutes;
    };
}

std::ostream& operator<<(std::ostream& f, const TIME::Duree & d);
std::istream& operator>>(std::istream&, TIME::Duree&); //lecture format hhHmm

#endif

