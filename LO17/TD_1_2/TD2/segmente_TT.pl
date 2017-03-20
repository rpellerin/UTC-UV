#!/usr/bin/perl
# use utf8;
binmode(STDIN, ":utf8");
binmode(STDOUT, ":utf8");
for ($i=0; $i<3; $i++) {
   if ($ARGV[0] =~ /^-[frn]$/) {
	$opt = shift(@ARGV);
	if ($opt =~ /f/) {
		$fbavard=1;
	} elsif ($opt =~ /r/) {
		$rbavard=1;
	} else {
		$nbavard=1;
	}
   }
}
while (<>) {
 	if (/<fichier>(.*)<\/fichier>/i && ($fbavard==1))
        {
		$fichier = "\t".$1;
        }
    if (/<rubrique>(.*)<\/rubrique>/i && ($rbavard==1))
        {
        $rubrique = "\t".$1;
        }
   if (/<numero>(.*)<\/numero>/i && ($nbavard==1))
        {
        $numero = "\t".$1;
        }
	if (/<titre>(.+)<\/titre>/i) {
        $_ = "$1";
		s/> />/g;
		s/<.*?>/ /g;
#		s/\t//g;
#		s/([0-9]+)[.,]/$1/g;
        s/[\[\]]/ /g;
        s/[\*\+]/ /g;
#       on intercale le numéro du fichier entre chaque mot
		s/[ \t',.;:?\/"`_!\n>&~»\$-\(\)-]*(\w+)[ \t',.;:?\/"`_!\n>&~»\$-\(\)-]*/\1$rubrique$fichier$numero\n/g;
		tr/A-Z/a-z/;
#		s/^\t.*?\n//;

		unless (/^\n/ || /^\t/) {
			print "$_";
			}
        }
	if (/<texte>(.+)<\/texte>/i) {
        $_ = "$1";
		s/> />/g;
		s/<.*?>/ /g;
#		s/\t//g;
#		s/([0-9]+)[.,]/$1/g;
        s/[\[\]]/ /g;
        s/[\*\+]/ /g;
#       on intercale le numéro du fichier entre chaque mot
		s/[ \t',.;:?\/"`_!\n>&~»\$-\(\)-]*(\w+)[ \t',.;:?\/"`_!\n>&~»\$-\(\)-]*/\1$rubrique$fichier$numero\n/g;
		tr/A-Z/a-z/;
#		s/^\t.*?\n//;

		unless (/^\n/ || /^\t/) {
			print "$_";
			}
		}
#	while (/<legendeImage>(.+)<\/legendeImage>/) #{
#        $_ = $1;
#		s/> />/g;
#		s/<.*?>/ /g;
#		s/\t//g;
#		s/([0-9]+)[.,]/$1/g;
#        s/[\[\]]/ /g;
#        s/[\*\+]/ /g;
#       on intercale le numéro du fichier entre chaque mot
#		s/[ \t',.;:?\/"`_!\n>&~»\$-\(\)-]+/$fichier\n/g;
#		tr/A-Z/a-z/;
#		s/^\t.*?\n//;

#		unless (/^\n/ || /^\t/) {
#			print "$_";
#			}
#        }

    }
