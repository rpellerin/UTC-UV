#!/bin/bash

# Il y a une image dans un <a> qui pointe sur l'image, il faut donc faire attention à ne pas compter le <a> dans les fichiers d'origine, d'où le grep -aoE "<img...
# grep -a pour lire les fichiers binaires en tant que texte
# grep -o pour compter les occurences de mots, même si plusieurs sont sur la même ligne
# grep -E pour autoriser les regex complexes

rm output.xml -f ;  ./td1.pl BULLETINS/*.htm | perl convert.pl > output.xml && { grep "<image>" output.xml } | wc -l && echo "Images parsées en Perl" && { grep -aoE "streaming.+?\.jpg" BULLETINS/*.htm && grep -aoE "<img.*?www\.bulletins-electroniques\.com\/Resources_fm\/actualites.*?\.jpg" BULLETINS/*.htm } | wc -l && echo "Images dans les fichiers d'origine";
