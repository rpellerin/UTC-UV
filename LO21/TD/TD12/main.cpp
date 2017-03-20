//#include <QCoreApplication>

#include <iostream>
#include "graph.h"

int main(int argc, char *argv[])
{
    //QCoreApplication a(argc, argv);

    Graph g("Mon joli graphe",5);
    g.addEdge(1,2);
    g.addEdge(1,3);
    g.addEdge(1,4);
    g.addEdge(4,2);
    std::cout<<g<<std::endl;
    //std::cout<<"Les succésseurs de 1 sont : "<<g.getSuccessors(1)<<std::endl;

    GraphG<int> g2("Un graphe de type vertex");
    g2.addVertex(1);
    g2.addVertex(2);
    g2.addVertex(3);
    std::cout<<g2<<std::endl;
    return 0;//a.exec();
}
