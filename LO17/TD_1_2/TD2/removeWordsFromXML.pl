#!/usr/bin/perl

while (<>) {
	if (/<texte>/ || /<titre>/) {
		tr/A-Z/a-z/;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])premier([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])dire([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])technologies([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])pôle([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])institut([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])cet([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])personnes([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])résume([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])secteur([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])énergie([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])international([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])alors([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])cours([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])ans([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])niveau([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])avant([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])innovation([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])même([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])directeur([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])notamment([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])cela([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])précise([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])y([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])un([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])nouvelles([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])formation([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])collaboration([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])sous([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])chercheurs([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])ont([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])après([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])temps([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])faire([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])permettre([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])la([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])avons([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])si([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])ainsi([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])dit([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])vient([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])au([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])industrie([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])celle([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])travaux([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])aujourd([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])en([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])développement([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])compte([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])s([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])enfin([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])laboratoire([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])tout([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])mois([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])son([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])technologie([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])équipes([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])plusieurs([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])type([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])déjà([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])pour([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])effet([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])que([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])recherche([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])été([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])nous([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])paris([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])près([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])grand([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])et([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])à([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])celui([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])autres([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])français([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])nouvelle([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])sur([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])leurs([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])le([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])entreprise([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])plus([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])dans([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])on([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])systèmes([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])particulier([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])jusqu([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])compétitivité([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])pourrait([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])d([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])cadre([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])industriels([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])explique([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])qu([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])pays([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])beaucoup([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])permis([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])était([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])france([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])ci([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])vont([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])aussi([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])nouveaux([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])hui([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])l([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])sa([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])ne([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])produit([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])marché([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])avec([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])les([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])a([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])très([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])différentes([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])être([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])mais([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])domaines([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])environnement([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])autour([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])rappelle([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])tous([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])mieux([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])rappelons([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])laquelle([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])europe([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])monde([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])ses([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])scientifique([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])résultats([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])qui([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])elle([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])étant([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])se([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])dès([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])différents([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])elles([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])grâce([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])contexte([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])deux([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])occasion([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])cette([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])fait([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])donc([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])groupe([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])fois([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])objectif([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])programme([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])comme([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])ecole([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])sont([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])lors([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])domaine([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])projets([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])de([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])avoir([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])autant([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])trois([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])première([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])non([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])université([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])dont([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])mettre([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])dernier([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])va([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])voire([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])afin([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])chez([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])aux([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])pas([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])développer([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])sein([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])est([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])certains([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])or([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])quelques([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])applications([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])peut([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])sera([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])ces([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])peu([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])sans([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])sciences([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])n([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])pouvoir([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])centre([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])encore([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])équipe([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])t([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])entre([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])ce([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])projet([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])moins([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])lui([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])une([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])des([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])ils([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])entreprises([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])depuis([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])c([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])notre([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])place([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])année([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])autre([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])soit([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])exemple([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])ensemble([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])bien([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])permet([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])années([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])forme([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])mise([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])du([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])par([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])terme([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])chaque([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])scientifiques([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])il([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])ou([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])partenaires([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])agit([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])où([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])leur([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])cnrs([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])également([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])grande([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])édition([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		s/([ \t',.;:?\/"`+*^\(\)\[\]<>-])travail([ \t',.;:?\/"`+*^\(\)\[\]<>-])/$1$2/g;
		}
	print $_
	}