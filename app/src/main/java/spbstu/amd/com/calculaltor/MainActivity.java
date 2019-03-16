package spbstu.amd.com.calculaltor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_left_bracket;
    private Button btn_right_bracket;
    private Button btn_Add;
    private Button btn_Sub;
    private Button btn_Mul;
    private Button btn_Div;
    private Button btn_eq;
    private Button btn_EC;
    private Button btn_clear;
    private EditText expression;
    private ParseExpression exprForExaluation;
    private String res;
    private void initButton(){
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 =  findViewById(R.id.btn_9);
        btn_Add =  findViewById(R.id.btn_Add);
        btn_Div =  findViewById(R.id.btn_div);
        btn_Sub =  findViewById(R.id.btn_Sub);
        btn_Mul =  findViewById(R.id.btn_Mul);
        btn_eq =  findViewById(R.id.btn_calc);
        //btn_dec =  findViewById(R.id.btn_dec);
        btn_clear = findViewById(R.id.btn_clear);
        expression =  findViewById(R.id.edText1);
        btn_left_bracket = findViewById(R.id.btn_left_bracket);
        btn_right_bracket = findViewById(R.id.btn_right_bracket);
        btn_EC = findViewById(R.id.btn_ec);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        res = "";
        exprForExaluation = new ParseExpression();
        setContentView(R.layout.activity_main);
        initButton();
        btn_0.setOnClickListener((View v)-> expression.setText(expression.getText()+"0"));
        btn_1.setOnClickListener((View v)-> expression.setText(expression.getText()+"1"));
        btn_2.setOnClickListener((View v)-> expression.setText(expression.getText()+"2"));
        btn_3.setOnClickListener((View v)-> expression.setText(expression.getText()+"3"));
        btn_4.setOnClickListener((View v)-> expression.setText(expression.getText()+"4"));
        btn_5.setOnClickListener((View v)-> expression.setText(expression.getText()+"5"));
        btn_6.setOnClickListener((View v)-> expression.setText(expression.getText()+"6"));
        btn_7.setOnClickListener((View v)-> expression.setText(expression.getText()+"7"));
        btn_8.setOnClickListener((View v)-> expression.setText(expression.getText()+"8"));
        btn_9.setOnClickListener((View v)-> expression.setText(expression.getText()+"9"));
        //btn_dec.setOnClickListener((View v)-> expression.setText(expression.getText()+"."));
        btn_left_bracket.setOnClickListener((View v)-> expression.setText(expression.getText()+"("));
        btn_right_bracket.setOnClickListener((View v)-> expression.setText(expression.getText()+")"));

        btn_EC.setOnClickListener((View v) -> {
            String str = expression.getText().toString();
            if (!str.isEmpty()) {
                str = str.substring(0, str.length() - 1);
                expression.setText(str);
            }
        });
        btn_Add.setOnClickListener((View v)->expression.setText(expression.getText()+"+"));
        btn_Div.setOnClickListener((View v)->expression.setText(expression.getText()+"/"));
        btn_Sub.setOnClickListener((View v)->expression.setText(expression.getText()+"-"));
        btn_Mul.setOnClickListener((View v)->expression.setText(expression.getText()+"*"));

        btn_clear.setOnClickListener((View v)->expression.setText(""));
        btn_eq.setOnClickListener((View v)-> {
            if (!res.isEmpty()) {
                res = "";
            }
            try {
                String polishNotation = exprForExaluation.parseToPolishNotation(expression.getText().toString());
                res = EvaluationExpression.calculation(polishNotation);
                expression.setText(expression.getText()+" = " + res);
            }
            catch (SyntaxException e){
                expression.setText(/*expression.getText() + */e.toString());
            }
            catch (IllegalArgumentException e){
                expression.setText(/*expression.getText()+*/ "can't divide by zero :(");
            }

        });
    }
}
