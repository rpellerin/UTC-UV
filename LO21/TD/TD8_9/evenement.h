#if !defined(_EVENEMENT_H)
#define _EVENEMENT_H
#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include "timing.h"

namespace TIME{
    class Evt {
        private:
            std::string sujet;
            Date date;
        public:
            Evt(const std::string& s, const Date& d):sujet(s),date(d){}
            void afficher(std::ostream& f= std::cout) const {
                f<<toString();
            }
            const std::string& getDescription() const { return sujet; }
            const Date& getDate() const { return date; }
            virtual ~Evt(){}
            virtual Evt* clone() const=0;
            virtual std::string toString() const=0;
            virtual const bool operator<(const Evt& ev) const {
                return this->date < ev.date;
            }
    };

    class EvtPj : public Evt {
        private:
            Date debut;
            Date fin;
        public:
            EvtPj(Date& debut, Date& fin, std::string& s):debut(debut),fin(fin),Evt(s,Date(1,1,0)) {}
            virtual Evt* clone() const override {
                return new EvtPj(*this);
            }
            virtual std::string toString() const override {
                std::stringstream f;
                f<<" *** EVT *** \nDate deb="<<debut<<" date fin="<<fin<<" sujet="<<getDescription()<<std::endl;
                return f.str();
            }

    };

    class Evt1j : public Evt {
        private:
            //Date date;
        public:
            Evt1j(const Date& d, const std::string& s):Evt(s,d){
                std::cout<<"Construction d'un Evt1"<<std::endl;
            }

            //const Date& getDate() const { return date; }
            virtual ~Evt1j() {
                std::cout<<"Destruction d'un Evt1"<<std::endl;
            }
            virtual Evt1j* clone() const override {
                return new Evt1j(*this);
            }
            virtual std::string toString() const override {
                std::stringstream f;
                f<<" *** EVT *** \nDate="<<getDate()<<" sujet="<<getDescription()<<std::endl;
                return f.str();
            }
    };
    class Evt1jDur : public Evt1j {
        private:
            Horaire horaire;
            Duree duree;
        public:
            Evt1jDur(const Date& d, const std::string& s, const Horaire& h, const Duree& du):Evt1j(d,s),horaire(h),duree(du){
                std::cout<<"Construction d'un Evt1Dur"<<std::endl;
            }
            const Horaire& getHoraire() const { return horaire; }
            const Duree& getDuree() const { return duree; }
            virtual ~Evt1jDur() override {
                std::cout<<"Destruction d'un Evt1Dur"<<std::endl;
            }
            virtual Evt1jDur* clone() const override {
                return new Evt1jDur(*this);
            }
            virtual std::string toString() const override {
                std::stringstream f;
                f<<" *** EVT *** \nDuree="<<duree<<" sujet="<<getDescription()<<" horaire="<<horaire<<std::endl;
                return f.str();
            }
            virtual const bool operator<(Evt& e) const {
                if (Evt::operator <(e)) return true;
                if (e < *this) return false;
                const Evt1jDur* pt = dynamic_cast<Evt1jDur*>(&e);
                if (pt != NULL && this->horaire < pt->horaire)
                    return true;
                return false;
            }
    };
    class Rdv : public Evt1jDur {
        private:
            std::string lieu;
            std::string personnes;
        public:
            Rdv(const Date& date, const std::string& sujet, const Horaire& debut, const Duree& duree, const std::string l, const std::string& p):
                Evt1jDur(date,sujet,debut,duree),
                lieu(l),
                personnes(p) {
                std::cout<<"Construction d'un Rdv"<<std::endl;
            }
            const std::string& getLieu() const { return lieu; }
            const std::string& getPersonnes() const { return personnes; }
            ~Rdv() override {
                std::cout<<"Destruction d'un Rdv"<<std::endl;
            }
            Rdv(const Rdv& rdv):Rdv(rdv.getDate(),rdv.getDescription(), rdv.getHoraire(), rdv.getDuree(), rdv.getLieu(), rdv.getPersonnes()) {}
            const Rdv& operator=(const Rdv& rdv) {
                this->lieu = rdv.lieu;
                this->personnes = rdv.personnes;

                //Evt1jDur *x = this;
                //x->horaire   = rdv.getHoraire();
                //x->duree     = rdv.getDuree();
                // IMPOSSIBLE CAR horaire et duree sont private et on n'est pas dans la classe Evt1jDur
                *this = rdv; // appelle le constructeur de recopie par défaut
            }
            virtual Rdv* clone() const override {
                return new Rdv(*this);
            }
            virtual std::string toString() const override {
                std::stringstream f;
                f<<" *** EVT *** \nDuree="<<getDuree()<<" sujet="<<getDescription()<<" horaire="<<getHoraire()
                <<" lieu="<<lieu<<" personnes="<<personnes<<std::endl;
                return f.str();
            }
    };
    class Agenda {
        //Evt** tab;
        //int nb, nbMax;
        typedef std::vector<Evt*> contEvt;
        contEvt evts;
        Agenda(const Agenda&);
        Agenda& operator=(const Agenda&);
    public :
        //Agenda(int nb, int nbMax) : nb(nb), nbMax(nbMax) {
        //    tab = new Evt*[10];
        //}
        class iterator {
            contEvt::iterator it;

        public:
            iterator(contEvt::iterator i):it(i) {}
            Evt& operator*() { return **it; }
            iterator& operator++() { ++it; return *this; }
            iterator& operator--() { --it; return *this; }
            bool operator!=(iterator i) { i.it!=it; }
        };
        iterator begin() { return iterator(evts.begin()); }
        iterator end() { return iterator(evts.end()); }
        Agenda(){ evts.reserve(10); }
        Agenda& operator<<(const Evt& e) {
//            if(nb==nbMax) {
//                Evt** newtab=new Evt*[nbMax+10];
//                for(unsigned int i=0; i<nb; i++) {
//                    newtab[i] = tab[i];
//                }
//                nbMax+=10;
//                Evt** old=tab;
//                tab=newtab;
//                delete[] old;
//            }
//            tab[nb++] = &e;
            Evt* nevt = e.clone();
            evts.push_back(nevt);
            return *this;
        }

        void afficher(std::ostream& f= std::cout) const {
//            for (int i = 0; i < nb; i++) {
//                tab[i]->afficher();
//            }
            for(contEvt::const_iterator it=evts.begin(); it!=evts.cend(); ++it)
                (*it)->afficher();
        }
    };

    std::ostream& operator<<(std::ostream& f, const Evt& j) {
        j.afficher(f);
        return f;
    }
}
#endif
