#!/usr/bin/perl

use utf8;

#binmode(STDIN, ":utf8");
#binmode(STDOUT, ":utf8");

# Usage: perl % tfidf.txt

@words;
while (<>) {
    /(.*?)\s(.*?)\s(\d+(\.\d+)?)/;
    if ($3 lt '0.79189260882435') {
        $words{$2} = 1;
    }
}

while (($key,$value) = each(%words)) {
    print $key."\n";
}
