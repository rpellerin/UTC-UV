#include <QCoreApplication>
#include <iostream>
#include "contener.h"
#include "stack.h"

int main(int argc, char *argv[])
{
    //QCoreApplication a(argc, argv);

    //return a.exec();

    using namespace TD;
    vector<int> cont(0);
//    std::cout<<cont.size()<<std::endl;

    cont.push_back(11234);
    cont.push_back(21234);
    cont.push_back(31234);

//    AO::Stack<int> s = AO::Stack<int>();
//    s.push(1);
//    std::cout<<s.top()<<std::endl;
//    AC::Stack<int> s2 = AC::Stack<int>();
//    s2.push(2);
//    std::cout<<s2.top()<<std::endl;

     vector<int> contt(0);
    for ( vector<int>::Const_Iterator it = contt.beginconst(); it != contt.endconst(); it++) {
        std::cout<<"ITERATION "<<std::endl;
        std::cout<<*it<<std::endl;
    }
    return 0;
}
