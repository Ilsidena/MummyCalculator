package org.mummy.mummycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mummy.calculator.Calculator;
import org.mummy.calculator.CalculatorException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Calculator calculator = new Calculator ();

        final TextView textWindow = (TextView) findViewById(R.id.textWindow);

        Button buttonLeftShift = (Button) findViewById(R.id.lshift);
        Button buttonRightShift = (Button) findViewById(R.id.rshift);
        Button buttonOr = (Button) findViewById(R.id.or);
        Button buttonXor = (Button) findViewById(R.id.xor);
        Button buttonAnd = (Button) findViewById(R.id.and);
        Button buttonNot = (Button) findViewById(R.id.not);

        Button buttonMod = (Button) findViewById(R.id.mod);
        Button buttonDivide = (Button) findViewById(R.id.div);
        Button buttonMultiply = (Button) findViewById(R.id.mul);
        Button buttonSubmit = (Button) findViewById(R.id.sub);
        Button buttonAdd = (Button) findViewById(R.id.add);
        Button buttonPower = (Button) findViewById(R.id.pow);

        Button buttonA = (Button) findViewById(R.id.a);
        Button buttonB = (Button) findViewById(R.id.b);
        Button button7 = (Button) findViewById(R.id.seven);
        Button button8 = (Button) findViewById(R.id.eight);
        Button button9 = (Button) findViewById(R.id.nine);
        Button buttonClearElement = (Button) findViewById(R.id.ce);

        Button buttonC = (Button) findViewById(R.id.c);
        Button buttonD = (Button) findViewById(R.id.d);
        Button button4 = (Button) findViewById(R.id.four);
        Button button5 = (Button) findViewById(R.id.five);
        Button button6 = (Button) findViewById(R.id.ce);
        Button buttonClear = (Button) findViewById(R.id.clear);

        Button buttonE = (Button) findViewById(R.id.e);
        Button buttonF = (Button) findViewById(R.id.f);
        Button button3 = (Button) findViewById(R.id.three);
        Button button2 = (Button) findViewById(R.id.two);
        Button button1 = (Button) findViewById(R.id.one);
        Button buttonResult = (Button) findViewById(R.id.result);

        Button buttonLeftBracket = (Button) findViewById(R.id.lbracket);
        Button buttonRightBracket = (Button) findViewById(R.id.rbracket);
        Button button0 = (Button) findViewById(R.id.zero);
        Button buttonSign = (Button) findViewById(R.id.sign);
        Button buttonPoint = (Button) findViewById(R.id.point);

        View.OnClickListener listener = new View.OnClickListener() {
            private StringBuilder input = new StringBuilder();

            @Override
            public void onClick (View view) {

                switch (view.getId()) {
                    case R.id.lshift:
                        input.append(" << ");
                        textWindow.setText(input.toString());
                        break;
                    case R.id.rshift:
                        input.append(" >> ");
                        textWindow.setText(input.toString());
                        break;
                    case R.id.or:
                        input.append(" | ");
                        textWindow.setText(input.toString());
                        break;
                    case R.id.xor:
                        input.append(" xor ");
                        textWindow.setText(input.toString());
                        break;
                    case R.id.and:
                        input.append(" & ");
                        textWindow.setText(input.toString());
                        break;
                    case R.id.not:
                        input.append(" ~");
                        textWindow.setText(input.toString());
                        break;
                    case R.id.mod:
                        input.append(" mod (");
                        textWindow.setText(input.toString());
                        break;
                    case R.id.div:
                        input.append(" / ");
                        textWindow.setText(input.toString());
                        break;
                    case R.id.mul:
                        input.append(" * ");
                        textWindow.setText(input.toString());
                        break;
                    case R.id.add:
                        input.append(" + ");
                        textWindow.setText(input.toString());
                        break;
                    case R.id.sub:
                        input.append(" - ");
                        textWindow.setText(input.toString());
                        break;
                    case R.id.pow:
                        input.append("^(");
                        textWindow.setText(input.toString());
                        break;
                    case R.id.lbracket:
                        input.append("(");
                        textWindow.setText(input.toString());
                        break;
                    case R.id.rbracket:
                        input.append(")");
                        textWindow.setText(input.toString());
                        break;
                    case R.id.sign:
                        if (input.length() == 0)
                            input.append("(-");
                        else if (input.charAt(input.length() - 1) == ' ')
                            input.append("(-");
                        else if (input.charAt(input.length() - 1) == '-') {
                            input.deleteCharAt(input.length() - 1);
                            input.deleteCharAt(input.length() - 1);
                        }

                        textWindow.setText(input.toString());
                        break;
                    case R.id.ce:
                        input.deleteCharAt(input.length() - 1);
                        textWindow.setText(input.toString());
                        break;
                    case R.id.clear:
                        input = new StringBuilder();
                        textWindow.setText(input.toString());
                        break;
                    case R.id.result:
                        //String inputString = input.toString();
                        Calculator calculator = new Calculator ();
                        try {
                            int result = calculator.getResult(input.toString());
                            textWindow.setText(Integer.toString(result));
                            input = new StringBuilder();
                            input.append(result);
                        }
                        catch(CalculatorException exception) {
                            System.out.println(exception.getMessage());
                        }
                        break;
                    case R.id.point:
                        input.append('.');
                        textWindow.setText(input.toString());
                        break;
                    case R.id.zero:
                        input.append('0');
                        textWindow.setText(input.toString());
                        break;
                    case R.id.one:
                        input.append('1');
                        textWindow.setText(input.toString());
                        break;
                    case R.id.two:
                        input.append('2');
                        textWindow.setText(input.toString());
                        break;
                    case R.id.three:
                        input.append('3');
                        textWindow.setText(input.toString());
                        break;
                    case R.id.four:
                        input.append('4');
                        textWindow.setText(input.toString());
                        break;
                    case R.id.five:
                        input.append('5');
                        textWindow.setText(input.toString());
                        break;
                    case R.id.six:
                        input.append('6');
                        textWindow.setText(input.toString());
                        break;
                    case R.id.seven:
                        input.append('7');
                        textWindow.setText(input.toString());
                        break;
                    case R.id.eight:
                        input.append('8');
                        textWindow.setText(input.toString());
                        break;
                    case R.id.nine:
                        input.append('9');
                        textWindow.setText(input.toString());
                        break;
                    case R.id.a:
                        input.append('A');
                        textWindow.setText(input.toString());
                        break;
                    case R.id.b:
                        input.append('B');
                        textWindow.setText(input.toString());
                        break;
                    case R.id.c:
                        input.append('C');
                        textWindow.setText(input.toString());
                        break;
                    case R.id.d:
                        input.append('D');
                        textWindow.setText(input.toString());
                        break;
                    case R.id.e:
                        input.append('E');
                        textWindow.setText(input.toString());
                        break;
                    case R.id.f:
                        input.append('F');
                        textWindow.setText(input.toString());
                        break;
                }

            }
        };

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttonA.setOnClickListener(listener);
        buttonB.setOnClickListener(listener);
        buttonC.setOnClickListener(listener);
        buttonD.setOnClickListener(listener);
        buttonE.setOnClickListener(listener);
        buttonF.setOnClickListener(listener);

        buttonOr.setOnClickListener(listener);
        buttonXor.setOnClickListener(listener);
        buttonAnd.setOnClickListener(listener);
        buttonLeftShift.setOnClickListener(listener);
        buttonRightShift.setOnClickListener(listener);
        buttonAdd.setOnClickListener(listener);
        buttonSubmit.setOnClickListener(listener);
        buttonMultiply.setOnClickListener(listener);
        buttonDivide.setOnClickListener(listener);
        buttonPower.setOnClickListener(listener);
        buttonSign.setOnClickListener(listener);
        buttonMod.setOnClickListener(listener);
        buttonNot.setOnClickListener(listener);
        buttonRightBracket.setOnClickListener(listener);
        buttonLeftBracket.setOnClickListener(listener);

        buttonClear.setOnClickListener(listener);
        buttonClearElement.setOnClickListener(listener);
        buttonResult.setOnClickListener(listener);
        buttonPoint.setOnClickListener(listener);
    }
}
