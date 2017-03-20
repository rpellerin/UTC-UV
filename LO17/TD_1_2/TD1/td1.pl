#!/usr/bin/perl

use utf8;
binmode(STDOUT, ":utf8");

# HTML AND BODY

@htmls;
while (<>) {
  # FILENAME
  $fichier = $ARGV;
  $fichier=~s/.*\///g;
  s/\n//;
  if (!defined(@htmls{$fichier})) {
    $htmls{$fichier} = $_;
  }
  else {
    $htmls{$fichier} .= $_;
  }
}

print "<corpus>\n";
while (($fichier,$html) = each(%htmls)) {
  print "<bulletin>\n";
  print '<fichier>'.$fichier."</fichier>\n"; # si on donne *.htm en param, argv[0] contient le premier, [1] un autre, etc
  $body = $html;
  $body=~/<body.*?>(.*)<\/body>/sg;
  $body = $1;


  # NUMERO

  $body=~/<span class="style32">BE France (\d+).*?<\/span>/;
  $numero = $1;
  print '<numero>'.$numero."</numero>\n";


  # DATE

  $body=~/<span class="style42">.*?(\d{1,2}\/\d{1,2}\/\d{4})<\/span>/;
  $date = $1;
  print '<date>'.$date."</date>\n";


  # RUBRIQUE AND TITRE

  $body=~/<p class="style96"><span class="style42">(.+)<br><\/span><span class="style17">(.*?)<\/span><\/p>/;
  $rubrique = $1;
  $titre = $2;
  print '<rubrique>'.$rubrique."</rubrique>\n";
  print '<titre>'.$titre."</titre>\n";


  # TEXTE

  $body =~ /<td.*?class="FWExtra2".*?>.*?<span class="style95">(.*?)<td.*?class="FWExtra2"/s;
  $texte = $1;
  $texte =~ s/<br \/>\n?<\/span><div style="text-align: center"><img.*?class="style95"><br \/>//gs;
  $texte =~ s/<.*?>//g;
  $texte =~ s/^\s+|\s+$|\n//g;
  print "<texte>".$texte."</texte>\n";
  # (.*?) makes it lazy (cf http://forums.phpfreaks.com/topic/265751-how-does-it-work/) instead of greedy


  # IMAGES WITH URL

  #@array = ($body =~ /(www\.bulletins-electroniques\.com\/Resources_fm\/actualites.*?\.jpg)/g);
  #print join "--\n", @array;
  print "<images>\n";
  pos($body) = 0; # reset cursor position cause /g leave the cursor in the middle of nowhere
  while ($body =~ /(www\.bulletins-electroniques\.com\/Resources_fm\/actualites.*?\.jpg).*?<strong>(.*?)<\/strong>|(streaming.+?\.jpg)/sg) {
      print "<image>".$1.$3."</image>\n";
      print "<legende>".$2."</legende>\n";
  }
  print "</images>\n";


  # CONTACT

  print "<contact>";
  $body =~ /Pour en savoir plus, contacts :.*?<p class="style44"><span class="style85">(.*?)<\/span>/s;
  $contact = $1;
  $contact =~ s/<.*?>//gs;
  print $contact;
  print "</contact>\n";
  print "</bulletin>\n";

  if (!defined($fichier) || !defined($numero) || !defined($date) || !defined($rubrique) || !defined($titre) || !defined($texte) || !defined($contact)) {
    print STDERR "Missing one field";
    exit -1;
  }
  $fichier = $numero = $date = $rubrique = $titre = $texte = $contact = undef;
}
print "</corpus>\n";
