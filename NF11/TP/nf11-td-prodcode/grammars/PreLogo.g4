grammar PreLogo; 

@header {
  package prelogoparsing;
}

INT : '0' | [1-9][0-9]* ;
ID : [a-z] + ;
WS : [ \t\r\n]+ -> skip ;
COULEUR : '#'('0' | [1-9][0-9]*) ;

prestat : 
   impt +
   commande +
;  
impt :
	'import' ID
;
commande :
	ID '(' INT INT (COULEUR)? ')' ('nb = 'INT)?
;