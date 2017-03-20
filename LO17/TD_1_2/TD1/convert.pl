#!/usr/bin/perl

while (<>)
	{
	s/&nbsp;/ /g;
    s/&ecirc;/ê/g;
#   &gt;
    s/&ouml;/ö/g;
    s/&ucirc;/û/g;
    s/&egrave;/è/g;
    s/&icirc;/î/g;
#    &lt;
    s/&eacute;/é/g;
    s/&quot;/"/g;
    s/&agrave;/à/g;
    s/&acirc;/â/g;
    s/&iuml;/ï/g;
    s/&ocirc;/ô/g;
    s/&ccedil;/ç/g;
    s/&euml;/ë/g;
    s/&ugrave;/ù/g;
    s/&szlig;/ß/g;
    s/&uuml;/ü/g;
#   && document.layers && layer != '';
    s/&amp;/et/g;
    s/&deg;/°/g;
    s/&ograve;/ò/g;
    s/&ecirc;/ê/g;
    s/&#338;/Œ/g;
    s/&#8211/—/g;
    s/&#21;/--/g;
	print;
	}
