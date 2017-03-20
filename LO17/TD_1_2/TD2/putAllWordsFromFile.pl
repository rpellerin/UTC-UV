#!/usr/bin/perl

use utf8;

#binmode(STDIN, ":utf8");
#binmode(STDOUT, ":utf8");

@words;
while (<>) {
    /(.*)\s+(.*\.htm)/; # $1 = word, $2 = file
    $words{$2} .= $1."\t";
}

while (($file,$wordss) = each(%words)) {
    chop $wordss;
    print $file.": ".$wordss."\n";
}

