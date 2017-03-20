#ifndef CALENDAR_h
#define CALENDAR_h
#include <QString>
#include <QDate>
#include <QTextStream>
using namespace std;

class CalendarException{
public:
    CalendarException(const QString& message):info(message){}
    QString getInfo() const { return info; }
private:
    QString info;
};


/*! \class Duree
        \brief Classe permettant de manipuler des durees
        L'utilisation de cette classe n?cessite des dates valides au sens commun du terme.
        D?clenchement d'exception dans le cas contraire
*/
class Duree{
public:
    //! Constructeur ? partir de heure et minute
    /*! \param h heure avec h>=0
        \param m minute avec 0<=m<=59
        */
    Duree(unsigned int h, unsigned int m):nb_minutes(h*60+m) {if (m>59) throw CalendarException("erreur: initialisation duree invalide");}
    //! Constructeur ? partir de minute
    /*! \param m minute avec m>=0
        */
    Duree(unsigned int m=0):nb_minutes(m) {}
    void setDuree(unsigned int minutes) { nb_minutes=minutes; }
    void setDuree(unsigned int heures, unsigned int minutes) { if (heures==0 && minutes>59) throw CalendarException("erreur: initialisation duree invalide"); nb_minutes=heures*60+minutes; }
    unsigned int getDureeEnMinutes() const { return nb_minutes; } //<!Retourne la duree en minutes
    double getDureeEnHeures() const { return double(nb_minutes)/60; } //<!Retourne la duree en heures
    unsigned int getMinute() const { return nb_minutes%60; }
    unsigned int getHeure() const { return nb_minutes/60; }
    void afficher(QTextStream& f) const; //<!Affiche la duree sous le format hhHmm
private:
    unsigned int nb_minutes;
};

QTextStream& operator<<(QTextStream& f, const Duree & d);
QTextStream& operator>>(QTextStream&, Duree&); //lecture format hhHmm



class Tache {
    QString identificateur;
    QString titre;
    Duree duree;
    QDate disponibilite;
    QDate echeance;
    bool preemptive;
    Tache(const QString& id, const QString& t, const Duree& dur, const QDate& dispo, const QDate& deadline, bool preempt=false):
            identificateur(id),titre(t),duree(dur),disponibilite(dispo),echeance(deadline),preemptive(preempt){}
	Tache(const Tache& t);
	Tache& operator=(const Tache&);
	friend class TacheManager;
public:
    QString getId() const { return identificateur; }
    void setId(const QString& str);
    QString getTitre() const { return titre; }
    void setTitre(const QString& str) { titre=str; }
    Duree getDuree() const { return duree; }
    void setDuree(const Duree& d) { duree=d; }
    QDate getDateDisponibilite() const {  return disponibilite; }
    QDate getDateEcheance() const {  return echeance; }
    void setDatesDisponibiliteEcheance(const QDate& disp, const QDate& e) {
        if (e<disp) throw CalendarException("erreur Tâche : date echéance < date disponibilité");
        disponibilite=disp; echeance=e;
    }
    bool isPreemptive() const { return preemptive; }
    void setPreemptive() { preemptive=true; }
    void setNonPreemptive() { preemptive=false; }
};

QTextStream& operator<<(QTextStream& f, const Tache& t);

class TacheManager {
private:
	Tache** taches;
	unsigned int nb;
	unsigned int nbMax;
	void addItem(Tache* t);
    Tache* trouverTache(const QString& id) const;
    QString file;
	TacheManager();
	~TacheManager();
	TacheManager(const TacheManager& um);
	TacheManager& operator=(const TacheManager& um);
	struct Handler{
		TacheManager* instance;
		Handler():instance(0){}
		// destructeur appelé à la fin du programme
		~Handler(){ if (instance) delete instance; }
	};
	static Handler handler;
public:
    Tache& ajouterTache(const QString& id, const QString& t, const Duree& dur, const QDate& dispo, const QDate& deadline, bool preempt=false);
    Tache& getTache(const QString& id);
    bool isTacheExistante(const QString& id) const { return trouverTache(id)!=0; }
    const Tache& getTache(const QString& code) const;
    void load(const QString& f);
    void save(const QString& f);
	static TacheManager& getInstance();
	static void libererInstance();

	class Iterator {
		friend class TacheManager;
		Tache** currentTache;
		unsigned int nbRemain;
		Iterator(Tache** u, unsigned nb):currentTache(u),nbRemain(nb){}
	public:
		Iterator():nbRemain(0),currentTache(0){}
		bool isDone() const { return nbRemain==0; }
		void next() { 
			if (isDone()) 
				throw CalendarException("error, next on an iterator which is done"); 
			nbRemain--; 
			currentTache++; 
		}
		Tache& current() const { 
			if (isDone()) 
				throw CalendarException("error, indirection on an iterator which is done"); 
			return **currentTache; 
		}
	};
	Iterator getIterator() { 
		return Iterator(taches,nb); 
	}


	class ConstIterator {
		friend class TacheManager;
		Tache** currentTache;
		unsigned int nbRemain;
		ConstIterator(Tache** u, unsigned nb):currentTache(u),nbRemain(nb){}
	public:
		ConstIterator():nbRemain(0),currentTache(0){}
		bool isDone() const { return nbRemain==0; }
		void next() { 
			if (isDone()) 
				throw CalendarException("error, next on an iterator which is done"); 
			nbRemain--; 
			currentTache++; 
		}
		const Tache& current() const { 
			if (isDone()) 
				throw CalendarException("error, indirection on an iterator which is done"); 
			return **currentTache; 
		}
	};
	ConstIterator getIterator() const{ 
		return ConstIterator(taches,nb); 
	}

	class iterator {
		Tache** current;
		iterator(Tache** u):current(u){}
		friend class TacheManager;
	public:
        iterator():current(0){}
		Tache& operator*() const { return **current; }
		bool operator!=(iterator it) const { return current!=it.current; }
		iterator& operator++(){ ++current; return *this; }
	};
	iterator begin() { return iterator(taches); }
	iterator end() { return iterator(taches+nb); }

	class const_iterator {
		Tache** current;
		const_iterator(Tache** u):current(u){}
		friend class TacheManager;
	public:
		const_iterator():current(0){};
		Tache& operator*() const { return **current; }
		bool operator!=(const_iterator it) const { return current!=it.current; }
		const_iterator& operator++(){ ++current; return *this; }
	};
	const_iterator begin() const { return const_iterator(taches); }
	const_iterator end() const { return const_iterator(taches+nb); }

	class DisponibiliteFilterIterator {
		friend class TacheManager;
		Tache** currentTache;
		unsigned int nbRemain;
        QDate dispo;
        DisponibiliteFilterIterator(Tache** u, unsigned nb, const QDate& d):currentTache(u),nbRemain(nb),dispo(d){
            while(nbRemain>0 && dispo<(*currentTache)->getDateDisponibilite()){
				nbRemain--; currentTache++;
			}
		}
	public:
		DisponibiliteFilterIterator():nbRemain(0),currentTache(0){}
		bool isDone() const { return nbRemain==0; }
		void next() { 
			if (isDone()) 
				throw CalendarException("error, next on an iterator which is done"); 
			do {
				nbRemain--; currentTache++;
            }while(nbRemain>0 && dispo<(*currentTache)->getDateDisponibilite());
		}
		Tache& current() const { 
			if (isDone()) 
				throw CalendarException("error, indirection on an iterator which is done"); 
			return **currentTache; 
		}
	};
    DisponibiliteFilterIterator getDisponibiliteFilterIterator(const QDate& d) {
		return DisponibiliteFilterIterator(taches,nb,d); 
	}
};

class Programmation {
	const Tache* tache;
    QDate date;
    QTime horaire;
public:
    Programmation(const Tache& t, const QDate& d, const QTime& h):tache(&t), date(d), horaire(h){}
	const Tache& getTache() const { return *tache; }
    QDate getDate() const { return date; }
    QTime getHoraire() const { return horaire; }
};

class ProgrammationManager {
private:
	Programmation** programmations;
	unsigned int nb;
	unsigned int nbMax;
	void addItem(Programmation* t);
	Programmation* trouverProgrammation(const Tache& t) const;
public:
	ProgrammationManager();
	~ProgrammationManager();
	ProgrammationManager(const ProgrammationManager& um);
	ProgrammationManager& operator=(const ProgrammationManager& um);
    void ajouterProgrammation(const Tache& t, const QDate& d, const QTime& h);
};

#endif
