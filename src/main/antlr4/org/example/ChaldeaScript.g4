grammar ChaldeaScript;

// ==================== REGLAS SINTÁCTICAS ====================

program
    : statement* EOF
    ;

statement
    : varDecl
    | chantStmt
    | battleStmt
    | grailStmt
    | skillDecl
    | skillCall
    | assignStmt
    ;

// Declaración de variable: summon x = 5;
varDecl
    : SUMMON ID ASSIGN expr SEMI
    ;

// Asignación: x = 10;
assignStmt
    : ID ASSIGN expr SEMI
    ;

// Imprimir: chant "hola";
chantStmt
    : CHANT expr SEMI
    ;

// Condicional: battle (condicion) { } retreat { }
battleStmt
    : BATTLE LPAREN expr RPAREN LBRACE statement* RBRACE
      (RETREAT LBRACE statement* RBRACE)?
    ;

// Ciclo: grail (condicion) { }
grailStmt
    : GRAIL LPAREN expr RPAREN LBRACE statement* RBRACE
    ;

// Declaración de función: skill nombre() { }
skillDecl
    : SKILL ID LPAREN paramList? RPAREN LBRACE statement* RBRACE
    ;

// Llamada a función: nombre();
skillCall
    : ID LPAREN argList? RPAREN SEMI
    ;

paramList
    : ID (COMMA ID)*
    ;

argList
    : expr (COMMA expr)*
    ;

// ==================== EXPRESIONES ====================

expr
    : expr (MULT | DIV) expr        # mulDiv
    | expr (PLUS | MINUS) expr      # addSub
    | expr (EQ | NEQ | LT | GT | LE | GE) expr  # comparison
    | expr (AND | OR) expr          # logical
    | NOT expr                      # notExpr
    | LPAREN expr RPAREN            # parens
    | NUMBER                        # number
    | STRING                        # string
    | BOOL                          # boolean
    | ID                            # identifier
    ;

// ==================== TOKENS (ANÁLISIS LÉXICO) ====================

// Palabras reservadas - Fate/GO themed
SUMMON   : 'summon';
CHANT    : 'chant';
BATTLE   : 'battle';
RETREAT  : 'retreat';
GRAIL    : 'grail';
SKILL    : 'skill';

// Clases de Servants como tipos
SABER      : 'Saber';
ARCHER     : 'Archer';
LANCER     : 'Lancer';
RIDER      : 'Rider';
CASTER     : 'Caster';
ASSASSIN   : 'Assassin';
BERSERKER  : 'Berserker';
RULER      : 'Ruler';
AVENGER    : 'Avenger';

// Valores booleanos temáticos
BOOL     : 'noble' | 'fallen';   // true | false

// Operadores aritméticos
PLUS     : '+';
MINUS    : '-';
MULT     : '*';
DIV      : '/';

// Operadores de comparación
EQ       : '==';
NEQ      : '!=';
LT       : '<';
GT       : '>';
LE       : '<=';
GE       : '>=';

// Operadores lógicos
AND      : '&&';
OR       : '||';
NOT      : '!';

// Asignación
ASSIGN   : '=';

// Delimitadores
LPAREN   : '(';
RPAREN   : ')';
LBRACE   : '{';
RBRACE   : '}';
SEMI     : ';';
COMMA    : ',';

// Literales
NUMBER   : [0-9]+ ('.' [0-9]+)?;
STRING   : '"' (~["\r\n])* '"';
ID       : [a-zA-Z_][a-zA-Z_0-9]*;

// Ignorar espacios y comentarios
WS       : [ \t\r\n]+ -> skip;
COMMENT  : '//' ~[\r\n]* -> skip;
MCOMMENT : '/*' .*? '*/' -> skip;