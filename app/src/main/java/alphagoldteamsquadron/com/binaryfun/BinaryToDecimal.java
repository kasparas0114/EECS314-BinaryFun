package alphagoldteamsquadron.com.binaryfun;

import android.app.Activity;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.ToggleButton;


public class BinaryToDecimal extends Activity {

    private int value = 0;
    private int current = 0;
    private int roundCount = 1;
    private Chronometer chronometer;
    //Name of minigame goes here
    public static final String SCORE_VALUE = "BinaryToDecimalValues";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_to_decimal);

        TextView valueText = (TextView)findViewById(R.id.textViewTargetValue);
        valueText.setText(Integer.toString(value));

        chronometer = (Chronometer)findViewById(R.id.chronometer);

        initialize();
    }

    public void initialize(){
        //Disable buttons
        Button b = (Button) findViewById(R.id.toggleButton1);
        b.setEnabled(false);
        b = (Button) findViewById(R.id.toggleButton2);
        b.setEnabled(false);
        b = (Button) findViewById(R.id.toggleButton4);
        b.setEnabled(false);
        b = (Button) findViewById(R.id.toggleButton8);
        b.setEnabled(false);
        b = (Button) findViewById(R.id.toggleButton16);
        b.setEnabled(false);
        b = (Button) findViewById(R.id.toggleButton32);
        b.setEnabled(false);
        b = (Button) findViewById(R.id.toggleButton64);
        b.setEnabled(false);
        b = (Button) findViewById(R.id.toggleButton128);
        b.setEnabled(false);
    }

    public void startBinaryGame(View view) {
        roundCount = 1;
        roundReset();
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        Button start = (Button) findViewById(R.id.StartButton);
        start.setEnabled(false);
    }

    public void roundReset(){
        value = (int)(Math.random() * 256);
        TextView valueText = (TextView)findViewById(R.id.textViewTargetValue);
        valueText.setText(Integer.toString(value));

        //Enable buttons and set to false
        ToggleButton b = (ToggleButton) findViewById(R.id.toggleButton1);
        b.setEnabled(true);
        b.setChecked(false);
        b = (ToggleButton) findViewById(R.id.toggleButton2);
        b.setEnabled(true);
        b.setChecked(false);
        b = (ToggleButton) findViewById(R.id.toggleButton4);
        b.setEnabled(true);
        b.setChecked(false);
        b = (ToggleButton) findViewById(R.id.toggleButton8);
        b.setEnabled(true);
        b.setChecked(false);
        b = (ToggleButton) findViewById(R.id.toggleButton16);
        b.setEnabled(true);
        b.setChecked(false);
        b = (ToggleButton) findViewById(R.id.toggleButton32);
        b.setEnabled(true);
        b.setChecked(false);
        b = (ToggleButton) findViewById(R.id.toggleButton64);
        b.setEnabled(true);
        b.setChecked(false);
        b = (ToggleButton) findViewById(R.id.toggleButton128);
        b.setEnabled(true);
        b.setChecked(false);

        TextView roundText = (TextView) findViewById(R.id.textViewRoundValue);
        roundText.setText(Integer.toString(roundCount) + " of 5");

        TextView currentText = (TextView) findViewById(R.id.textViewCurrentValue);
        currentText.setText("0");
        current = 0;
    }

    public void onToggleClicked(View view) {
        boolean on = ((ToggleButton) view).isChecked();
        
        if (on) {
            //toggleButton is 1
            switch (view.getId()) {
                case R.id.toggleButton128:
                    current += 128;
                    break;
                case R.id.toggleButton64:
                    current += 64;
                    break;
                case R.id.toggleButton32:
                    current += 32;
                    break;
                case R.id.toggleButton16:
                    current += 16;
                    break;
                case R.id.toggleButton8:
                    current += 8;
                    break;
                case R.id.toggleButton4:
                    current += 4;
                    break;
                case R.id.toggleButton2:
                    current += 2;
                    break;
                case R.id.toggleButton1:
                    current += 1;
                    break;
            }
        } else {
            //toggleButton is 0
            switch (view.getId()) {
                case R.id.toggleButton128:
                    current -= 128;
                    break;
                case R.id.toggleButton64:
                    current -= 64;
                    break;
                case R.id.toggleButton32:
                    current -= 32;
                    break;
                case R.id.toggleButton16:
                    current -= 16;
                    break;
                case R.id.toggleButton8:
                    current -= 8;
                    break;
                case R.id.toggleButton4:
                    current -= 4;
                    break;
                case R.id.toggleButton2:
                    current -= 2;
                    break;
                case R.id.toggleButton1:
                    current -= 1;
                    break;
            }
        }

        TextView currentText = (TextView) findViewById(R.id.textViewCurrentValue);
        currentText.setText(Integer.toString(current));

        if(current == value) {
            win();
        }
    }

    public void win(){

        if(roundCount == 1){
            chronometer.stop();
            TextView currentText = (TextView) findViewById(R.id.textViewCurrentValue);
            currentText.setText("You win!");
            Button start = (Button) findViewById(R.id.StartButton);
            start.setEnabled(true);
            int score = 20000 - ((int)(SystemClock.elapsedRealtime()-chronometer.getBase()));
            new SaveScore(this, SCORE_VALUE, score);

        }else {
            roundCount++;
            roundReset();

        }
    }



}
