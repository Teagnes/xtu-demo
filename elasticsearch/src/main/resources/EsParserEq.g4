grammar EsParserEq;

expr:
    expr OPT expr EOF # OP
    | ID             # KEY
    | VARIABLE            # VAL
    ;

ID : [a-zA-Z]+ ;
INT : [0-9]+ ;
NEWLINE : '\r'? '\n' ;
OPT : ('=');
VARIABLE : (CHAR+|'_')(CHAR|INT|'_')+;
CHAR:[a-zA-Z]+;
//STRING:'\'' .*? '\'' ;
WS :  [ \t\r\n]+ -> skip ;


