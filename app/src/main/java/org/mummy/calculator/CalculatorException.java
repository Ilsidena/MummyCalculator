package org.suai.calculator;

public class CalculatorException extends RuntimeException {
    final static String [] errors = {"Синтаксическая ошибка"
            ,"Ожидается ')'"
            ,"Ожидается '('"};

    public CalculatorException (int errorNum) {
        super(errors[errorNum]);
    }

    public CalculatorException () {
        super();
    }
}