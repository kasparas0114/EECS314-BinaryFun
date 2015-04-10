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
    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_binary_to_decimal);

        // Get the message from the intent
       // Intent intent = getIntent();
        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

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
        value = (int)(Math.random() * 256);
        TextView valueText = (TextView)findViewById(R.id.textViewTargetValue);
        valueText.setText(Integer.toString(value));
        //Enable buttons
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

        TextView currentText = (TextView) findViewById(R.id.textViewCurrentValue);
        currentText.setText("0");
        current = 0;

        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        //TODO change the start button somehow
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
        chronometer.stop();
        TextView currentText = (TextView) findViewById(R.id.textViewCurrentValue);
        currentText.setText("You win");
    }
}
