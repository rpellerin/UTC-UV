#if !defined(LOG_H)
#define LOG_H
#include "timing.h"
#include<iostream>
#include <exception>
#include <evenement.h>

using namespace TIME;

class Log {
    public:
        virtual void addEvt(const TIME::Date& d, const TIME::Horaire& h, const std::string& s)=0;
        virtual void displayLog(std::ostream& f) const=0;
};

class LogError : public std::exception {
    std::string info;
public:
    LogError(const std::string& s) : exception(), info(s){}
    const char* what() const throw() {
        return this->info.c_str();
    }
    ~LogError() throw(){}
};

namespace AC { // Adaptater de Classe
    class Mylog : public Log, private Agenda {
    public:
        // MyLog():Log(),Agenda() {} // useless
        virtual void addEvt(const TIME::Date& d, const TIME::Horaire& h, const std::string& s) override {
            Evt1jDur a(d,s,h,Duree(0));
            *this<<(Evt1jDur(d,s,h,Duree(0)));
        }
        virtual void displayLog(std::ostream& f) const override {
            Agenda::afficher(f);
        }
    };
}

namespace AO { // Adaptateur d'objet
    class MyLog : public Log {
    private:
            Agenda A;
    public:
            void addEvt(const TIME::Date& d, const Horaire& h, const std::string& s ) {
                Evt1jDur e(d,s,h,Duree(0));
                A<<e;
            }
            void displayLog(std::ostream &f) const {
                A.afficher(f);
            }
    };
}

#endif
