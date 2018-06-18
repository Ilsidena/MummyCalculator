package org.mummy.calculator;

import java.util.Arrays;

public class Calculator {
    private char[] input = null;
    private Lexeme lexeme = null;
    private int index;
    private int tokenSize;

    /*public Calculator(char[] input) {
        this.input = input;
        lexeme = new Lexeme();
        index = 0;
    }*/

    public int getResult(String inputString) {
        input = inputString.toCharArray();
        lexeme = new Lexeme();
        Int result = new Int();

        getToken();
        or(result);

        return result.value;
    }

    private boolean isEnd() {
        return index >= input.length;
    }

    private boolean isSpase() {
        return (input[index] == ' ' ||
                input[index] == '\t' ||
                input[index] == '\n');
    }

    private boolean isDigit() {
        if (!isEnd ())
            return Character.isDigit(input[index]);
        else
            return false;
    }

    private boolean isAlpha() {
        if (!isEnd())
            return ((input[index] >= 'a' && input[index] <= 'z') ||
                    (input[index] >= 'A' && input[index] <= 'Z'));
        else
            return false;
    }

    private void getToken() {
        if (isEnd()) {
            return;
        }

        while (isSpase()) {
            index++;
        }

        lexeme.token = new char [100];

        switch (input[index]) {
            case '+':
                lexeme.token[0] = '+';
                lexeme.tok = Lexeme.TOK.PLUS;
                lexeme.tokenType = Lexeme.TOKENTYPE.DELIMITER;
                index++;
                return;
            case '-':
                lexeme.token[0] = '-';
                lexeme.tok = Lexeme.TOK.MINUS;
                lexeme.tokenType = Lexeme.TOKENTYPE.DELIMITER;
                index++;
                return;
            case '*':
                lexeme.token[0] = '*';
                lexeme.tok = Lexeme.TOK.STAR;
                lexeme.tokenType = Lexeme.TOKENTYPE.DELIMITER;
                index++;
                return;
            case '/':
                lexeme.token[0] = '/';
                lexeme.tok = Lexeme.TOK.DIVIDE;
                lexeme.tokenType = Lexeme.TOKENTYPE.DELIMITER;
                index++;
                return;
            case 'm':
                lexeme.token[0] = 'm';
                lexeme.tok = Lexeme.TOK.MOD;
                lexeme.tokenType = Lexeme.TOKENTYPE.DELIMITER;
                index += 3;
                return;
            case '^':
                lexeme.token[0] = '^';
                lexeme.tok = Lexeme.TOK.POWER;
                lexeme.tokenType = Lexeme.TOKENTYPE.DELIMITER;
                index++;
                return;
            case ';':
                lexeme.token[0] = ';';
                lexeme.tok = Lexeme.TOK.SEMICOLON;
                lexeme.tokenType = Lexeme.TOKENTYPE.DELIMITER;
                index++;
                return;
            case '(':
                lexeme.token[0] = '(';
                lexeme.tok = Lexeme.TOK.LROUNDBR;
                lexeme.tokenType = Lexeme.TOKENTYPE.DELIMITER;
                index++;
                return;
            case ')':
                lexeme.token[0] = ')';
                lexeme.tok = Lexeme.TOK.RROUNDBR;
                lexeme.tokenType = Lexeme.TOKENTYPE.DELIMITER;
                index++;
                return;
            case ',':
                lexeme.token[0] = ',';
                lexeme.tok = Lexeme.TOK.COMMA;
                lexeme.tokenType = Lexeme.TOKENTYPE.DELIMITER;
                index++;
                return;
            case '<':
                lexeme.token[0] = '<';
                lexeme.tok = Lexeme.TOK.LSHIFT;
                lexeme.tokenType = Lexeme.TOKENTYPE.DELIMITER;
                index += 2;
                return;
            case '>':
                lexeme.token[0] = '>';
                lexeme.tok = Lexeme.TOK.RSHIFT;
                lexeme.tokenType = Lexeme.TOKENTYPE.DELIMITER;
                index += 2;
                return;
            case '~':
                lexeme.token[0] = '~';
                lexeme.tok = Lexeme.TOK.NOT;
                lexeme.tokenType = Lexeme.TOKENTYPE.DELIMITER;
                index++;
                return;
            case '&':
                lexeme.token[0] = '&';
                lexeme.tok = Lexeme.TOK.AND;
                lexeme.tokenType = Lexeme.TOKENTYPE.DELIMITER;
                index++;
                return;
            case '|':
                lexeme.token[0] = '|';
                lexeme.tok = Lexeme.TOK.OR;
                lexeme.tokenType = Lexeme.TOKENTYPE.DELIMITER;
                index++;
                return;
            case 'x':
                lexeme.token[0] = 'x';
                lexeme.tok = Lexeme.TOK.XOR;
                lexeme.tokenType = Lexeme.TOKENTYPE.DELIMITER;
                index += 3;
                return;
        }

        if (isDigit()) {
            for (tokenSize = 0; isAlpha() || isDigit(); tokenSize++, index++)
                lexeme.token[tokenSize] = input[index];

            if (lexeme.token[0] == '0') {
                int tmp;

                if (lexeme.token[1] == 'x' || lexeme.token[1] == 'X')
                    tmp = Integer.parseInt(Arrays.toString(lexeme.token), 16);
                else
                    tmp = Integer.parseInt(Arrays.toString(lexeme.token), 8);

                lexeme.token = (Integer.toString(tmp)).toCharArray();
            }

            lexeme.tokenType = Lexeme.TOKENTYPE.NUMBER;
        }
    }

    private void or (Int result) {
        Int argument = new Int ();
        char operator;
        xor(result);

        while ((operator = lexeme.token[0]) == '|') {
            getToken();
            xor(argument);
            calculate(operator, result, argument);
        }
    }

    private void xor (Int result) {
        Int argument = new Int ();
        char operator;
        and(result);

        while ((operator = lexeme.token[0]) == 'x') {
            getToken();
            and(argument);
            calculate(operator, result, argument);
        }
    }

    private void and (Int result) {
        Int argument = new Int ();
        char operator;
        shift(result);

        while ((operator = lexeme.token[0]) == '&') {
            getToken();
            shift(argument);
            calculate(operator, result, argument);
        }
    }

    private void shift (Int result) {
        Int argument = new Int ();
        char operator;
        add(result);

        while ((operator = lexeme.token[0]) == '<' || operator == '>') {
            getToken();
            add(argument);
            calculate(operator, result, argument);
        }
    }

    private void add (Int result) {
        Int argument = new Int ();
        char operator;
        mul(result);

        while ((operator = lexeme.token[0]) == '+' || operator == '-'){
            getToken();
            mul(argument);
            calculate(operator, result, argument);
        }
    }

    private void mul (Int result) {
        Int argument = new Int ();
        char operator;
        pow(result);

        while ((operator = lexeme.token[0]) == '*' || operator == '/'){
            getToken();
            pow(argument);
            calculate(operator, result, argument);
        }
    }

    private void pow (Int result) {
        Int argument = new Int ();
        unary(result);

        while (lexeme.token[0] == '^') {
            getToken();
            unary(argument);
            calculate('^', result, argument);
        }
    }

    private void unary (Int result) {
        char operator = 0;

        if ((lexeme.tokenType == Lexeme.TOKENTYPE.DELIMITER) &&
                lexeme.token[0] == '+'   ||
                lexeme.token[0] == '-'  ||
                lexeme.token[0] == '-') {
            operator = lexeme.token[0];
            getToken();
        }

        if ((lexeme.tok == Lexeme.TOK.LROUNDBR) &&
                (lexeme.tokenType == Lexeme.TOKENTYPE.DELIMITER)) {
            getToken();
            or(result);

            if (lexeme.tok != Lexeme.TOK.RROUNDBR)
                throw new CalculatorException(1);

            getToken();
        }
        else {
            switch (lexeme.tokenType) {
                case NUMBER:
                    result.value = Integer.parseInt(getLexeme());
                    getToken();
                    break;
                default:
                    throw new CalculatorException(0);
            }
        }

        if (operator == '-')
            result.value = -result.value;

        if (operator == '~')
            result.value = ~result.value;

        if (lexeme.tok == Lexeme.TOK.MOD) {
            getToken();
            Int argument = new Int ();

            if ((lexeme.tok == Lexeme.TOK.LROUNDBR) &&
                    (lexeme.tokenType == Lexeme.TOKENTYPE.DELIMITER)) {
                getToken();
                or(argument);

                if (lexeme.tok != Lexeme.TOK.RROUNDBR)
                    throw new CalculatorException(1);

                calculate('m', result, argument);
                getToken();
            }
            else {
                throw new CalculatorException(2);
            }
        }
    }

    private void calculate (char operation, Int result, Int argument) {
        int tmp;

        switch (operation)
        {
            case '-':
                result.value -= argument.value;
                break;
            case '+':
                result.value += argument.value;
                break;
            case '*':
                result.value *= argument.value;
                break;
            case '/':
                result.value /= argument.value;
                break;
            case '^':
                tmp = result.value;

                if (argument.value == 0) {
                    result.value = 1;
                    break;
                }

                for (int i = argument.value - 1; i > 0; --i)
                    result.value = result.value * tmp;

                break;
            case 'x':
                result.value ^= argument.value;
                break;
            case '&':
                result.value &= argument.value;
                break;
            case '|':
                result.value |= argument.value;
                break;
            case '<':
                result.value <<= argument.value;
                break;
            case '>':
                result.value >>= argument.value;
                break;
            case 'm':
                result.value %= argument.value;
        }
    }

    private String getLexeme () {
        StringBuilder builder = new StringBuilder ();

        for (int i = 0; i < tokenSize; i++ ) {
            builder.append(lexeme.token[i]);
        }

        return builder.toString();
    }
}