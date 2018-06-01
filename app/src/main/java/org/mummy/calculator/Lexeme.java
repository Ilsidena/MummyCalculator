package org.suai.calculator;

public class Lexeme {
    public Lexeme() {
        token = new char [TOKENSIZE];
    }

    enum TOKENTYPE {
        DELIMITER
        ,NUMBER
    }

    enum TOK{
        PLUS        // '+'
        ,MINUS      // '-'
        ,STAR       // '*'
        ,POWER		// '^'
        ,DIVIDE		// '/'
        ,MOD    	// '%'
        ,EQUAL		// '='
        ,SEMICOLON	// ';'
        ,LROUNDBR	// '('
        ,RROUNDBR	// ')'
        ,COMMA		// ','
        ,OCT
        ,HEX
        ,OR 		// '|'
        ,XOR 		// 'xor'
        ,AND 		// '&'
        ,RSHIFT 	// '>>'
        ,LSHIFT 	// '<<'
        ,NOT 		// '~'

    }

    private final static int TOKENSIZE = 100;

    TOKENTYPE tokenType = null;
    TOK tok = null;
    char [] token;
}
