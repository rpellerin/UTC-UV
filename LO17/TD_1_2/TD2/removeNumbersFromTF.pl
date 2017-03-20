#!/usr/bin/perl

use utf8;

#binmode(STDIN, ":utf8");
#binmode(STDOUT, ":utf8");

# Usage: perl % tf.txt

while (<>) {
    /(\d+)\s+(.*)/;
    if ($_ =~ /\d+\s\d+\s.*/) {
        # do nothing
    }
    else {
        print $_;
    }
}
