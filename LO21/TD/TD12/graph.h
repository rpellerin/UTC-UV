#if !defined(_GRAPH_H)
#define _GRAPH_H

#include<string>
#include<stdexcept>
#include <vector>
#include <list>
#include <map>
#include <set>
#include <ostream>

using namespace std;

class GraphException : public exception {
    string info;

public:
    GraphException(const string& i) throw():info(i){}
    virtual ~GraphException() throw(){}
    const char * what() const throw(){ return info.c_str(); }
};

class Graph {
    vector<list<unsigned int> > adj;
    string name;
    unsigned int nbEdges; // nb d'arcs
public:
    Graph(const string& n, unsigned int nb):nbEdges(0),name(n),
        adj(vector<list<unsigned int> >(nb,list<unsigned int>())) {}

    const string& getName() const { return name; }
    unsigned int getNbVertices() const { return adj.size(); }
    unsigned int getNbEdges() const {
        unsigned int counter = 0;
        for (list<unsigned int> list : adj) {
            counter += list.size();
        }
        return counter;
    }
    void addEdge(unsigned int i, unsigned int j) {
        if (i>=getNbVertices()||j>=getNbVertices())
            throw GraphException("i et j incorrects");
        list<unsigned int>& l = adj[i];
        l.push_back(j); // TODO check pas existant
        l.unique();
        nbEdges++;
    }

    void removeEdge(unsigned int i, unsigned int j) {
        if (i>=getNbVertices()||j>=getNbVertices())
            throw GraphException("i et j incorrects");
        list<unsigned int>& l = adj[i];
        l.remove(j);
        nbEdges--;
    }

    const list<unsigned int>& getSuccessors(unsigned int i) const {
        if (i>=getNbVertices())
            throw GraphException("i incorrect");
        return adj[i];
    }
    const list<unsigned int> getPredecessors(unsigned int i) const {
        if (i>=getNbVertices())
            throw GraphException("i incorrect");
        list<unsigned int> l;
        unsigned int index = 0;
        for (vector<list<unsigned int> >::const_iterator it = adj.begin(); it != adj.end(); ++it,index++) {
            for (unsigned int el : *it) {
                if (el == i)
                    l.push_back(index);
            }
        }
        return l;
    }
};

ostream& operator<<(ostream& f, const Graph& G) {
    f<<"Graph de nom '"<<G.getName()<<"' ayant "<<G.getNbEdges()<<" arc(s) et "<<G.getNbVertices()<< " sommet(s)."<<std::endl;
    for (unsigned int i = 0; i<G.getNbVertices(); i++) {
        f<<i<<":";
        for (list<unsigned int>::const_iterator it=G.getSuccessors(i).begin(); it!=G.getSuccessors(i).end(); ++it) {
                f<<*it<<" ";
        }
        f<<std::endl;
    }
    return f;
}

#endif

template<class Vertex>
class GraphG {
    map<Vertex,set<Vertex> > adj;
    string name;
    unsigned int nbEdges; // nb d'arcs

public:
    GraphG(const string& n):nbEdges(0),name(n),
        adj(map<Vertex,set<Vertex> >()) {}
    const string& getName() const { return name; }
    unsigned int getNbVertices() const { return adj.size(); }
    unsigned int getNbEdges() const {
        unsigned int counter = 0;
        for (typename map<Vertex,set<Vertex> >::const_iterator it = adj.begin(); it!=adj.end(); ++it) {
            counter += (*it).second.size();
        }
        return counter;
    }
    void addVertex(const Vertex& i) {
//        typename map<Vertex,set<Vertex> >::const_iterator it;
//        it = adj.find(i);
//        if (it != adj.end())
//          throw GraphException('Le vertex existe déjà.');
//        else
//          adj[i] = set<Vertex>();
        adj[i];
    }

    void addEdge(const Vertex& i, const Vertex& j) {
        (adj[i]).insert(j);
        nbEdges++;
    }

    void removeEdge(const Vertex& i, const Vertex& j) {
        set<Vertex>& s = adj[i];
        s.remove(j);
        nbEdges--;
    }

    void removeVertex(const Vertex& i) {
        adj.erase(i);
    }

    void print(ostream& f) const {
        f<<"Graph de nom '"<<getName()<<"' ayant "<<getNbEdges()<<" arc(s) et "<<getNbVertices()<< " sommet(s)."<<std::endl;
        for (typename map<Vertex, set<Vertex> >::const_iterator it = adj.begin(); it!=adj.end(); ++it) {
            // to be done
        }
    }
    class vertex_iterator : public map<Vertex, set<Vertex> >::const_iterator {
    public:
        vertex_iterator():map<Vertex, set<Vertex> >::const_iterator() {}

        vertex_iterator(typename map<Vertex, set<Vertex> >::const_iterator it): // constructeur de recopie
            map<Vertex, set<Vertex> >::const_iterator(it) {}

        const Vertex& operator*() const {
            return map<Vertex, set<Vertex> >::const_iterator::operator *().first;
        }
    };
    vertex_iterator begin_vertex() { return vertex_iterator(adj.begin()); }
    vertex_iterator end_vertex() { return vertex_iterator(adj.end()); }

    class successeur_iterator : public set<Vertex>::const_iterator {
    public:
        successeur_iterator() : set<Vertex>::const_iterator() {}

        successeur_iterator(typename set<Vertex>::const_iterator it):
            set<Vertex>::const_iterator(it) {}
    };

    successeur_iterator begin_successeur(const Vertex& v) const {
        return successeur_iterator(adj[v].begin());
    }
    successeur_iterator end_successeur(const Vertex& v) const {
        return successeur_iterator(adj[v].end());
    }
};

template<class V> ostream& operator<<(ostream& f, const GraphG<V>& G) {
    G.print(f);
    return f;
}
