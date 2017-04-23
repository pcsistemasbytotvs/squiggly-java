grammar SquigglyExpression;

// Parser Rules ---------------------------------------------------------------

parse
    : expression_list EOF
    ;

expression_list
    : expression (',' expression)*
    ;

expression
    : negated_expression
    | field_list (nested_expression|empty_nested_expression)
    | dot_path (nested_expression|empty_nested_expression)
    | dot_path
    | field
    | deep
    ;

field_list
    : '(' field ('|' field)* ')'
    | field
    ;

negated_expression
    : '-' field
    | '-' dot_path
    ;

nested_expression
    : LSQUIGGLY expression_list RSQUIGGLY
    ;

empty_nested_expression
    : LSQUIGGLY RSQUIGGLY
    ;

deep
    : WILDCARD_DEEP
    ;

dot_path
    : field ('.' field)+
    ;

field
    : exact_field
    | regex_field
    | wildcard_shallow_field
    | wildcard_field
    ;

exact_field
    : IDENTIFIER
    ;

regex_field
    : '~' regex_pattern '~' regex_flag*
    | '/' regex_pattern '/' regex_flag*
    ;

regex_pattern
    : ('.' | '|' | ',' | LSQUIGGLY | RSQUIGGLY | '-' | REGEX_CHAR  | IDENTIFIER | WILDCARD_SHALLOW)+
    ;

regex_flag
    : 'i'
    ;

wildcard_field
   : IDENTIFIER wildcard_char
   | IDENTIFIER (wildcard_char IDENTIFIER)+ wildcard_char?
   | wildcard_char IDENTIFIER
   | wildcard_char (IDENTIFIER wildcard_char)+ IDENTIFIER?
   ;

wildcard_char
   : WILDCARD_SHALLOW
   | '?'
   ;

wildcard_shallow_field
    : WILDCARD_SHALLOW
    ;


// Lexer Tokens ---------------------------------------------------------------

fragment DIGIT
    : [0-9]
    ;

fragment LETTER
    : [a-zA-Z]
    ;

fragment VALID_FIELD_CHAR
    : (LETTER | DIGIT | '$' | '_')
    ;

LSQUIGGLY
    : '{'
    ;

RSQUIGGLY
    : '}'
    ;

IDENTIFIER
    : VALID_FIELD_CHAR (VALID_FIELD_CHAR)*
    ;

WILDCARD_SHALLOW
    : '*'
    ;

WILDCARD_DEEP
    : '**'
    ;

REGEX_CHAR
    : ~('~' | '/')
    ;