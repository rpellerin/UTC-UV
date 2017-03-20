#include "Calendar.h"
#include <fstream>

ostream& operator<<(ostream& fout, const Tache& t){
	fout<<t.getId()<<"\n";
	fout<<t.getTitre()<<"\n";
	fout<<t.getDuree()<<"\n";
	fout<<t.getDateDisponibilite()<<"\n";
	fout<<t.getDateEcheance()<<"\n";
	return fout;
}

ostream& operator<<(ostream& f, const Programmation& p);


TacheManager::TacheManager():taches(0),nb(0),nbMax(0){}
void TacheManager::addItem(Tache* t){
	if (nb==nbMax){
		Tache** newtab=new Tache*[nbMax+10];
		for(unsigned int i=0; i<nb; i++) newtab[i]=taches[i];
		// ou memcpy(newtab,taches,nb*sizeof(Tache*));
		nbMax+=10;
		Tache** old=taches;
		taches=newtab;
		delete[] old;
	}
	taches[nb++]=t;
}

TacheManager* TacheManager::instance = new TacheManager();

TacheManager* TacheManager::getInstance() {
    if (instance) {
        TacheManager::instance = new TacheManager();
    }
    return TacheManager::instance;
}

void TacheManager::libererInstance() {
    delete instance;
}

Tache* TacheManager::trouverTache(const string& id)const{
	for(unsigned int i=0; i<nb; i++)
		if (id==taches[i]->getId()) return taches[i];
	return 0;
}

Tache& TacheManager::ajouterTache(const string& id, const string& t, const Duree& dur, const Date& dispo, const Date& deadline){
	if (trouverTache(id)) throw CalendarException("erreur, TacheManager, tache deja existante");
	Tache* newt=new Tache(id,t,dur,dispo,deadline);
	addItem(newt);
	return *newt;
}

Tache& TacheManager::getTache(const string& id){
	Tache* t=trouverTache(id);
	if (!t) throw CalendarException("erreur, TacheManager, tache inexistante");
	return *t;
}

const Tache& TacheManager::getTache(const string& id)const{
	return const_cast<TacheManager*>(this)->getTache(id);
}

TacheManager::~TacheManager(){
	if (file!="") save(file);
	for(unsigned int i=0; i<nb; i++) delete taches[i];
	delete[] taches;
	file="";
}

TacheManager::TacheManager(const TacheManager& um):nb(um.nb),nbMax(um.nbMax), taches(new Tache*[um.nb]){
	for(unsigned int i=0; i<nb; i++) taches[i]=new Tache(*um.taches[i]);
}

TacheManager& TacheManager::operator=(const TacheManager& um){
	if (this==&um) return *this;
	this->~TacheManager();
	for(unsigned int i=0; i<um.nb; i++) addItem(new Tache(*um.taches[i]));
	return *this;
}

/*
string identificateur;
	string titre;
	Duree duree;
	Date disponibilite;
	Date echeance;
*/



void TacheManager::load(const string& f){
	if (file!=f) this->~TacheManager();
	file=f;
	ifstream fin(file.c_str()); // open file
	if (!fin) throw CalendarException("erreur, lors de l'ouverture du fichier de taches");
	char tmp[256];
	while (!fin.eof()&&fin.good()){
		fin.getline(tmp,256); // get code
		if (fin.bad()) throw CalendarException("erreur, fichier taches, lecture identificateur tache");
		string id=tmp;
		fin.getline(tmp,256); // get titre
		if (fin.bad()) throw CalendarException("erreur, fichier taches, lecture titre tache");
		string titre=tmp;

		Duree duree;
		fin>>duree;
		if (fin.bad()) throw CalendarException("erreur, fichier taches, lecture duree tache");

		Date dispo;
		fin>>dispo;
		if (fin.bad()) throw CalendarException("erreur, fichier taches, lecture date disponibilite");

		Date echeance;
		fin>>echeance;
		if (fin.bad()) throw CalendarException("erreur, fichier taches, lecture date echeance");
		while (fin.peek()=='\r') fin.ignore();
		while (fin.peek()=='\n') fin.ignore();
		std::cout<<"LOAD "<<id<<"-"<<titre<<"-"<<duree<<"-"<<dispo<<"-"<<echeance<<"\n";
		ajouterTache(id,titre,duree,dispo,echeance);
	}
	fin.close(); // close file
}

void  TacheManager::save(const string& f){
	file=f;
	ofstream fout(f.c_str(),ofstream::trunc); // toutes les taches existantes sont écrasées
	for(unsigned int i=0; i<nb; i++){
		fout<<*taches[i];
	}
	fout.close();
}
//******************************************************************************************

ProgrammationManager::ProgrammationManager():programmations(0),nb(0),nbMax(0){}
void ProgrammationManager::addItem(Programmation* t){
	if (nb==nbMax){
		Programmation** newtab=new Programmation*[nbMax+10];
		for(unsigned int i=0; i<nb; i++) newtab[i]=programmations[i];
		// ou memcpy(newtab,Programmations,nb*sizeof(Programmation*));
		nbMax+=10;
		Programmation** old=programmations;
		programmations=newtab;
		delete[] old;
	}
	programmations[nb++]=t;
}

Programmation* ProgrammationManager::trouverProgrammation(const Tache& t)const{
	for(unsigned int i=0; i<nb; i++)
		if (&t==&programmations[i]->getTache()) return programmations[i];
	return 0;
}

void ProgrammationManager::ajouterProgrammation(const Tache& t, const Date& d, const Horaire& h){
	if (trouverProgrammation(t)) throw CalendarException("erreur, ProgrammationManager, Programmation deja existante");
	Programmation* newt=new Programmation(t,d,h);
	addItem(newt);
}


ProgrammationManager::~ProgrammationManager(){
	for(unsigned int i=0; i<nb; i++) delete programmations[i];
	delete[] programmations;
}

ProgrammationManager::ProgrammationManager(const ProgrammationManager& um):nb(um.nb),nbMax(um.nbMax), programmations(new Programmation*[um.nb]){
	for(unsigned int i=0; i<nb; i++) programmations[i]=new Programmation(*um.programmations[i]);
}

ProgrammationManager& ProgrammationManager::operator=(const ProgrammationManager& um){
	if (this==&um) return *this;
	this->~ProgrammationManager();
	for(unsigned int i=0; i<um.nb; i++) addItem(new Programmation(*um.programmations[i]));
	return *this;
}

/*
	const Tache* tache;
	Date date;
	Horaire horaire;
*/

