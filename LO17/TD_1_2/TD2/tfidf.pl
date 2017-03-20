#!/usr/bin/perl
#

use utf8;

#binmode(STDIN, ":utf8");
#binmode(STDOUT, ":utf8");

# Usage: perl % idf.txt tf.txt

@mots;
while (<>) {
    if ($ARGV =~ m/idf/) {
        /(.*?)\s+(\d+(\.\d+)?)/;
        $mots{$1} = $2;
    }
    elsif ($ARGV =~ m/tf/) {
        /(\d+)\s+(.*?)\s+(.*)/;
        $nb = $1;
        $mot = $2;
        $file = $3;
        $tfidf = $nb * ($mots{$mot});
        print $file."\t".$mot."\t".$tfidf."\n";
    }
}
