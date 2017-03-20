#!/usr/bin/perl

use utf8;

#binmode(STDIN, ":utf8");
#binmode(STDOUT, ":utf8");

# Usage: perl % df.txt

sub log10 {
    my $n = shift;
    return log($n)/log(10);
}

while (<>) {
    /(\d+)\s+(.*)/;
    print $2."\t".log10(326/$1)."\n";
}
