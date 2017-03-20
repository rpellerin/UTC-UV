#include <iostream>
#include<stdlib.h>

#include "fonction.h"

using namespace std;

int main()
{
    //exerciceA();
    double x = 3.14;
    //std::cout<<x<<std::endl;
    double y;
    //std::cout<<y<<std::endl;
    y = 3.14;
    //std::cout<<y<<std::endl;
    const double pi = 3.14;
    //std::cout<<pi<<"\n";

    //bonjour1::bonjour();

    //bonjour2::bonjour();

    int a = 1;
    int b = 2;
    int& refa = a;
    int& refb = b;
    std::cout<<a<<b<<std::endl;
    inverse(&a,&b);
    std::cout<<a<<b<<endl;
    std::cout<<refa<<refb<<endl;
    inverse(refa,refb);
    std::cout<<refa<<refb<<endl;

    struct essai es;
    es.n = 2;
    es.x = 3;

    struct essai* pes = &es;
    struct essai& refes = es;

    cout<<endl<<es.n<<es.x<<endl;
    raz(pes);
    cout<<es.n<<es.x<<endl;
    es.n = 2;
    es.x = 3;
    cout<<es.n<<es.x<<endl;
    raz(refes);
    cout<<es.n<<es.x<<endl;
    return 0;
}
