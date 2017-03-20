#include <iostream>
#include <stdlib.h>
#include "evenement.h"
#include <log.h>


int main(){
    using namespace std;
    using namespace TIME;

    //Rdv e(Date(11,11,2013),"reunion UV",Horaire(17,30),Duree(60),"Intervenants UV","bureau");
    //std::cout<<"RDV:"<<e<<std::endl;

    Rdv* pt5= new Rdv(Date(12,11,2013),"reunion UV",Horaire(17,30),Duree(60),"Intervenants UV","bureau");
    pt5->afficher();
    delete pt5;
    Evt1j* pt6= new Rdv(Date(12,11,2013),"reunion UV",Horaire(17,30),Duree(60),"Intervenants UV","bureau");
    pt6->afficher();
    delete pt6;
    AC::Mylog la;
    la.addEvt(Date(),Horaire(1,2),"salut c cool");
    cout << "TEST\n";
    la.displayLog(std::cout);
    LogError err("error");
    cout<<err.what();

    // EXO 35
    Evt1j e1(Date(4,10,1957),"Spoutnik");
    Evt1j e2(Date(11,6,2013),"Shenzhou");
    Evt1jDur e3(Date(11,6,2013),"Lancement de Longue Marche",Horaire(17,38),Duree
    (0,10));
    Rdv e4(Date(11,4,2013),"reunion UV",Horaire(17,30),Duree(60),"Intervenants UV","bureau");
    Evt1j* pt1= &e1; Evt1j* pt2=&e2; Evt1j* pt3=&e3; Evt1j* pt4=&e4;
    Evt1j& ref1=e1; Evt1j& ref2=e2; Evt1j& ref3=e3; Evt1j& ref4=e4;
    Rdv* pt=dynamic_cast<Rdv*>(pt1); pt->afficher();
//    pt=pt2; pt->afficher();
//    pt=pt3; pt->afficher();
//    pt=pt4; pt->afficher();
//    Rdv&
//    Rdv&
//    Rdv&
//    Rdv&
//    r1=ref1;
//    r2=ref2;
//    r3=ref3;
//    r4=ref4;
//    r1.afficher();
//    r2.afficher();
//    r3.afficher();
//    r4.afficher();

    return 0;
}
