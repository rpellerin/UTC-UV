grammar Logo; 

@header {
  package logoparsing;
}

INT : '0' | [1-9][0-9]* ;
WS : [ \t\r\n]+ -> skip ;
BOOLEAN : '<' | '>'| '<=' | '>=' | '==' | '!=' ;
VAR : '"'[a-z]+ ;
USE : ':'[a-z]+ ; 
NAME : [a-z]+ ;

programme : functions? liste_instructions 
;
liste_instructions :
  (instruction)+
;
functions :
	(function)+
;

function :
	'pour' NAME (USE)* liste_instructions 'fin'
;
instruction :
    'av' exp # av
  | 'td' exp # td
  | 'tg' exp # tg
  | 'lc' # lc
  | 'bc' # bc
  | 've' # ve
  | 're' exp # re
  | 'fpos' '[' exp exp ']' # fpos
  | 'fcc' exp # fcc
  | 'repete' atom '[' liste_instructions ']' #repete
  | 'donne' VAR exp # donne
  | 'si' booleanExpr '[' liste_instructions ']' '[' liste_instructions ']'? # ifElse
  | 'tantque' booleanExpr '[' liste_instructions ']' # tantQue
  | NAME exp* #proc
  | 'rends' exp #return
;

booleanExpr : exp BOOLEAN exp;

exp :
	  exp ('*' | '/') exp # mutl
	| exp ('+' | '-') exp # sum
	| 'hasard' exp # haz
	| atom # aroule
	| USE # var
	| NAME exp* #fun
;

atom :
	INT # int
	| '(' exp ')' # parenthese
	| 'loop' # loop
;