#ifndef STACK
#define STACK

#include <contener.h>

using namespace TD;

namespace AC { // adaptateur de classe
    template <class T>
    class Stack : private vector<T> {
    public:
        Stack():vector<T>(5){}

        bool empty() const {
            return vector<T>::empty();
        }
        void push(const T& x) {
            vector<T>::push_back(x);
        }

        void pop() {
            vector<T>::pop_back();
        }

        unsigned int size()const {
            return vector<T>::size();
        }
        T& top() {
            return vector<T>::back();
        }

        const T& top()const {
            return vector<T>::back();
        }
        void clear() {
            vector<T>::empty();
        }
    };
}

namespace AO { // adaptateur d'objet
    template <class T>
    class Stack {
        vector<T> v;
    public:
        Stack():v(vector<T>(5)) {}
        bool empty() const {
            return v.empty();
        }
        void push(const T& x) {
            v.push_back(x);
        }

        void pop() {
            v.pop_back();
        }

        unsigned int size()const {
            return v.size();
        }
        T& top() {
            return v.back();
        }

        const T& top()const {
            return v.back();
        }
        void clear() {
            v.empty();
        }
    };
}
#endif // STACK

