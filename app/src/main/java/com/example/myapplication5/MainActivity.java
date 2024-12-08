package com.example.myapplication5;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.Expression;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    TextView textTV;
    String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("text", text);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        text = savedInstanceState.getString("text");
        textTV.setText(text);
    }

    private void initTextView()
    {
        textTV = (TextView)findViewById(R.id.resultTextView);
    }

    private void setText(String value)
    {
        text = text + value;
        textTV.setText(text);
    }
    public void ACOnClick(View view)
    {
        text = "";
        setText(" ");
    }

    private Expression expression = new Expression();

    public void buttonOnClick(View view) {
        String buttonValue = ((Button) view).getText().toString();
        if(buttonValue.equals("x")){
            buttonValue = "*";
        }else if(buttonValue.equals(",")){
            buttonValue = ".";
        }else if(buttonValue.equals("x^2")){
            buttonValue = "^2";
        }else if(buttonValue.equals("x^3")){
            buttonValue = "^3";
        }else if(buttonValue.equals("x!")){
            buttonValue = "!";
        }else if(buttonValue.equals("+/-")){
            text = "-("+ text + ")";
            textTV.setText(text);
            expression.setExpressionString(text);
            return;
        }else if(buttonValue.equals("sqrt(x)")){
            text = "sqrt(" + text + ")";
            textTV.setText(text);
            expression.setExpressionString(text);
            return;
        }else if(buttonValue.equals("log10")){
            text = "log10(" + text + ")";
            textTV.setText(text);
            expression.setExpressionString(text);
            return;
        }else if(buttonValue.equals("%")){
            String lastNumber = text.substring(text.length()-1,text.length());
            double lastNumberDouble = Double.parseDouble(lastNumber);
            lastNumberDouble /= 100;
            text = text.substring(0,text.length()-1) + String.valueOf(lastNumberDouble);
            textTV.setText(text);
            expression.setExpressionString(text);
            return;
        }
        text = text + buttonValue;
        textTV.setText(text);
        expression.setExpressionString(text);
    }

    public void calculateResult2(View view){
        double result = expression.calculate();
        text = String.valueOf(result);
        textTV.setText(text);
        expression.setExpressionString(text);
    }
}