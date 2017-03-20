#!/usr/bin/perl

#$balise = shift(@ARGV);
while (<>) {
	chop;
#	($mot,$fic,$rub,$art) = split (/\t/);
    ($mot,$rub,$fic,$num) = split (/\t/);
#	$tabindex{$mot} = #$tabindex{$mot}."\t".$fic."\t".$rub."\t".$art;
	$tabindex{$mot} = $tabindex{$mot}."\t".$rub."\t".$fic."\t".$num;
	}
@cleindex = keys %tabindex ;
foreach (@cleindex) {
	print $_."\t".$tabindex{$_}."\n";
	}
