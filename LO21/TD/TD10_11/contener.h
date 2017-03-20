#if !defined(_Contener_T_H)
#define _Contener_T_H

#include<string>
#include<stdexcept>

namespace TD {
    class ContenerException : public std::exception {
        protected :
            std::string info;
        public:
            ContenerException(const std::string& i="") throw() :info(i){}
            const char * what() const throw() { return info.c_str(); }
            ~ContenerException()throw(){}
    };

    template <class T>
    class Contener {
    protected:
        unsigned int taille;
        unsigned int tailleMax;
    public:
        Contener(unsigned int n):tailleMax(n),taille(n){}
        unsigned int size()const { return tailleMax; }
        bool empty()const {return taille == 0; }
        virtual T& element(unsigned int i)=0;
        virtual const T& element(unsigned int i)const=0;
        virtual T& front();
        virtual const T& front()const;
        virtual T& back();
        virtual const T& back()const;
        virtual void push_back(const T& x)=0;
        virtual void pop_back()=0;
        virtual void clear();
    };

    template <class T>
    const T& Contener<T>::back() const {
        if (!empty()) return element(taille-1);
        throw ContenerException("Erreur dans la fonction front");
    }

    template <class T>
    T& Contener<T>::back() {
        if (!empty()) return element(taille-1);
        throw ContenerException("Erreur dans la fonction front");
    }

    template <class T>
    const T& Contener<T>::front() const {
        if (!empty()) return element(0);
        throw ContenerException("Erreur dans la fonction front");
    }

    template <class T>
    T& Contener<T>::front() {
        if (!empty()) return element(0);
        throw ContenerException("Erreur dans la fonction front");
    }

    template <class T>
    void Contener<T>::clear() {
        while(!empty())
            pop_back();
    }

    template <class T>
    class vector : public Contener<T> {
        T* tab;
        public:
            vector(unsigned int taille = 0, const T& element = T()):
                Contener<T>(taille),
                tab(new T[taille])
            {
                for (int i = 0; i < taille; ++i) {
                    tab[i] = element;
                }
            }
            ~vector() {
                delete[] tab;
            }
            T& element(unsigned int i) { return tab[i]; }
            const T& element(unsigned int i) const { return tab[i]; }
            void push_back(const T& x) {
                if (this->taille >= this->tailleMax) {
                    T* newtab = new T[this->tailleMax + 10];
                    for (int i = 0; i < this->taille ; i++) {
                        newtab[i] = tab[i];
                    }
                    T* old = tab;
                    tab = newtab;
                    delete[] old;
                    this->tailleMax += 10;
                }
                tab[this->taille++] = x;
            }

            void pop_back() {
                this->taille--;
                // on ne fait pas de delete pour ne pas perdre la mémoire allouée
            }
            const T& operator[](unsigned int i) const {
                return tab[i];
            }
            T& operator[](unsigned int i) {
                return tab[i];
            }
            class Iterator {
                T* el;
            public:
                Iterator(T* tab):el(tab){}
                T& operator*() const {
                    return *el;
                }
                Iterator& operator++() { // ++iterator
                    el++; return *this;
                }
                Iterator operator++(int) { // iterator++
                    Iterator temp = *this;
                    el++;
                    return temp;
                }

                bool operator==(const Iterator& it) const {
                    return it.el == el;
                }
                bool operator!=(const Iterator& it) const {
                    return it.el != el;
                }
            };
            class Const_Iterator {
                const T* el;
            public:
                Const_Iterator(T* tab):el(tab){}
                const T& operator*() const {
                    return *el;
                }
                Const_Iterator& operator++() { // ++iterator
                    el++; return *this;
                }
                Const_Iterator operator++(int) { // iterator++
                    Const_Iterator temp = *this;
                    el++;
                    return temp;
                }

                bool operator==(const Const_Iterator& it) const {
                    return it.el == el;
                }
                bool operator!=(const Const_Iterator& it) const {
                    return it.el != el;
                }
            };
            Iterator begin() {
                std::cout<<"begin non const"<<std::endl;
                return Iterator(tab);
            }
            Iterator end() {
                return Iterator(tab+this->taille);
            }
            Const_Iterator beginconst()  {
                std::cout<<"begin const"<<std::endl;
                return Const_Iterator(tab);
            }
            Const_Iterator endconst()  {
                return Const_Iterator(tab+this->taille);
            }

    };
}

#endif
