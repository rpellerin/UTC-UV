# Rendu

**Dimanche 14 juin 23h59 (fuseau horaire de Paris)** à Taha Arbaoui (taha.arbaoui@utc.fr).

Livrable :

* Code source : l’ensemble du code source du projet. Attention, ne pas fournir d’exécutable ou de fichier
objet.
* Documentation : une documentation complète en html générée avec Doxygen.
* Video de présentation : une courte video de présentation dans laquelle vous filmerez votre logiciel afin
d’en démontrer le bon fonctionnement (maximum 5 min). Pour réaliser cette video, vous pourrez vous servir
des logiciels CamStudio (Windows), Jing (Windows, Mac OS), RecordMyDesktop (Linux). Ces logiciels sont
mentionnés uniquement à titre d’exemple.
* Rapport : Un rapport en format .pdf composé de 2 parties :
  * la description de votre architecture;
  * une argumentation détaillée où vous montrez que votre architecture permet facilement des évolutions.

Vous pouvez ajouter en annexe de ce rapport des instructions à destination de votre correcteur si nécessaire
(présentation des livrables, instructions de compilation, . . . ). Ce rapport ne devra pas dépasser 10 pages
(annexes comprises).


# LaTeX

## Installer LaTeX

```bash
sudo apt-get update
sudo apt-get install texlive-full
```

## Comment compiler le rapport (fichiers .tex)

```bash
cd rapport/
pdflatex Report # Ne pas écrire l'extention .tex
pdflatex Report # Lancer la même commande deux fois est important pour les références, etc.
```


# Git

## Installer Git

```bash
sudo apt-get update
sudo apt-get install git
```

## Comment envoyer son code après modification

```bash
git status
git add -A
git commit -m "Message pour les autres personnes"
git push origin master
```

## Comment récupérer les modifications faites par d'autres personnes

```bash
git pull origin master
git log
```


# Doxygen

## Installer Doxygen

```bash
sudo apt-get update
sudo apt-get install doxygen doxygen-gui doxygen-doc 
```

## Générer la documentation

```bash
cd documentation/
doxygen Doxyfile.conf
```


# Compiler le projet

Au préalable, installer Qt : https://wiki.qt.io/Install_Qt_5_on_Ubuntu

```bash
QMAKE=`find / -path "*Qt/*gcc*/bin/qmake" -print0 2>/dev/null` # Peut être un peu long
cd code/
mkdir -p build
cd build/
$QMAKE ../ProjectCalendar/ProjectCalendar.pro -r -spec linux-g++ CONFIG+=debug
make clean
make
```