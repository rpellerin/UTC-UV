#!/usr/bin/perl
#************************************************************************************************************#
#*                							LO17 - successeurs.pl                                           *#
#*                                                                                                          *#
#* @author : NOBLET Gilles, FOUQUET Yoann                                                                   *#
#* @date   : 13/10/2011                                                                                     *#
#* @revised : P. Morizet                                                                                    *#
#* @date   : 07/10/2015                                                                                     *#
#* Ce script permet de générer la liste des successeurs pour chaque lettre des mots dune liste de mots.     *#
#* Le corpus doit avoir été préalablement trié et sans doublon (sort -u nom_fichier)                        *#
#*                                                                                                          *#
#************************************************************************************************************#

# $t1 = time;
binmode(STDIN, ":utf8");
binmode(STDOUT, ":utf8");
@mots=<>;											#Extraction des mots du corpus
$size = $#mots+1;									#Nombre de mots du corpus
$begin_mot = 0;                                     #Initialisation de l'index des mots dont on veut calculer les successeurs (mot analysé)
foreach $m (@mots)									#Pour chaque mots du corpus
{													#
	$m =~ s/\n/ /;									#Remplacement du retour chariot par un espace
	$m =~ tr/A-Z/a-z/;				     			#Mise en minuscules
	@lettre=split(//,$m);							#Extraction des lettres du mot
	$rac="";										#Initialisation de la racine du mot à ''
	$begin=$begin_mot;								#Initialisation de l'index des mots du corpus qui serviront à calculer les successeurs du mot analysé
	$lettre=0;										#Initialisation du nombre de lettre étudiés à 0
	foreach (@lettre)								#Pour chaque lettre du mot analysé
	{												#
		$rac=$rac.$_;								#Ajout de la lettre à la racine du mot
		$succ=0; 									#Initialisation du nombre de lettre successeurs à 0
		$found=0;									#Initialisation d'un booléen (au moins une lettre successeur trouvée) à 0
		$i=$begin;									#Initialisation de l'index du premier mot du corpus, commençant par la première lettre du mot analysé
		%succ=();									#Initialisation du tableau associatif contenant les lettres successeurs
		while (($i<$size) && ($succ<9) && ($found<2)) #Pour chaque mot courant du corpus (à partir de begin)
		{											#	
			$elt = @mots[$i];						#Extraction du mot courant
			$elt =~ tr/A-Z/a-z/;					#Mise en minuscules
			if ($elt =~ /^$rac(.)/)					#Si le mot a la même racine que le mot analysé
			{										#
				if (!exists($succ{"$1"}))			#Si aucune racine débutant par cette lettre n'a été trouvée
				{									#
					$succ++;						#Incrémentation du nombre de lettres successeurs si besoin
					$succ{"$1"}++;					#Ajout de la lettre dans le tableau associatif
					if ($found==0)					#Si premier mot trouvé pour cette racine
					{									#
						if ($lettre==0) { $begin_mot=$i; $lettre++; }	#Modification de l'index de début de liste des mots analysés au premier mot commençant par cette lettre
						$begin=$i;					#Modification de l'index de début de liste des mots courants à comparer au mot analysé
						$found++;					#Nombre de successeurs trouvés <- 1
					}								#
				}									#
			}										#
			elsif ($found == 1)						#Sinon, si un successeur avait déjà été trouvé
			{										#
				$found++;							#Fin de la recherche pour cette racine
			}
			$i++;									#Compteur de boucle
		}											#Fin du while
		print "$succ";								#Impression du nombre de successeurs de chaque lettres
	}  												#Fin du foreach
	print "\t$m\n";									#Impression du mot
}													#

$t2 = time;
# print "Résultat retourné en ".($t2-$t1)." s\n";
