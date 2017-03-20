#include <iomanip>
#include "timing.h"
#include <ctime>

using namespace TIME;

std::ostream& operator<<(std::ostream& f, const Duree & d){ d.afficher(f); return f; }
std::istream& operator>>(std::istream& flot, TIME::Duree& duree){
	unsigned int h,m;
	bool ok=true;
	flot>>h;
	if (flot) while (flot.peek()==' ') flot.ignore(); // passe les espaces
	else ok=false;

	if (!flot) ok=false;

	if(flot.peek()=='H') {
		flot.ignore();
		flot>>m;
		if (!flot) ok=false;
	}
	else {
		ok=false;
	}
	if (ok) duree=Duree(h,m); else flot.clear(std::ios::failbit);
	return flot;
}
