package alphagoldteamsquadron.com.binaryfun;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Spinner;
import android.widget.TextView;

public class BinaryToHex extends Activity implements AdapterView.OnItemSelectedListener {

    private int value = 0;
    private int current = 0;
    private int roundCount = 1;
    private Chronometer chronometer;
    //Name of minigame goes here
    public static final String SCORE_VALUE = "BinaryToHexValues";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary_to_hex);

        TextView valueText = (TextView)findViewById(R.id.textViewTargetValue);
        valueText.setText(Integer.toString(value));

        chronometer = (Chronometer)findViewById(R.id.chronometer);

        initialize();
    }

    private void initializeListeners() {
        Spinner s = (Spinner)findViewById(R.id.spinner1);
        s.setOnItemSelectedListener(this);
        s = (Spinner)findViewById(R.id.spinner2);
        s.setOnItemSelectedListener(this);
        /*s = (Spinner)findViewById(R.id.spinner3);
        s.setOnItemSelectedListener(this);
        s = (Spinner)findViewById(R.id.spinner4);
        s.setOnItemSelectedListener(this);*/
    }

    public void initialize(){
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.hex_array, R.layout.spinner_item);
        //disable spinners
        Spinner s = (Spinner) findViewById(R.id.spinner1);
        s.setEnabled(false);
        s.setAdapter(adapter);
        s = (Spinner) findViewById(R.id.spinner2);
        s.setEnabled(false);
        s.setAdapter(adapter);
        /*s = (Spinner) findViewById(R.id.spinner3);
        s.setEnabled(false);
        s.setAdapter(adapter);
        s = (Spinner) findViewById(R.id.spinner4);
        s.setEnabled(false);
        s.setAdapter(adapter);*/
    }

    public void startGame(View view) {
        roundCount = 1;
        roundReset();
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        Button start = (Button) findViewById(R.id.StartButton);
        start.setEnabled(false);
        initializeListeners();
    }

    public void roundReset() {
        value = (int)(Math.random() * 256);
        TextView valueText = (TextView)findViewById(R.id.textViewTargetValue);
        valueText.setText(Integer.toString(value));

        //Enable spinners and set to 0
        Spinner s = (Spinner) findViewById(R.id.spinner1);
        s.setEnabled(true);
        s.setSelection(0);
        s = (Spinner) findViewById(R.id.spinner2);
        s.setEnabled(true);
        s.setSelection(0);
        /*s = (Spinner) findViewById(R.id.spinner3);
        s.setEnabled(true);
        s.setSelection(0);
        s = (Spinner) findViewById(R.id.spinner4);
        s.setEnabled(true);
        s.setSelection(0);*/

        TextView roundText = (TextView) findViewById(R.id.textViewRoundValue);
        roundText.setText(Integer.toString(roundCount) + " of 5");

        TextView currentText = (TextView) findViewById(R.id.textViewCurrentValue);
        currentText.setText("0");
        current = 0;
    }

    private int getHexValue(String hex) {
        if(hex.equals("0")) {
            return 0;
        } else if(hex.equals("1")) {
            return 1;
        } else if(hex.equals("2")) {
            return 2;
        } else if(hex.equals("3")) {
            return 3;
        } else if(hex.equals("4")) {
            return 4;
        } else if(hex.equals("5")) {
            return 5;
        } else if(hex.equals("6")) {
            return 6;
        } else if(hex.equals("7")) {
            return 7;
        } else if(hex.equals("8")) {
            return 8;
        } else if(hex.equals("9")) {
            return 9;
        } else if(hex.equals("A")) {
            return 10;
        } else if(hex.equals("B")) {
            return 11;
        } else if(hex.equals("C")) {
            return 12;
        } else if(hex.equals("D")) {
            return 13;
        } else if(hex.equals("E")) {
            return 14;
        } else if(hex.equals("F")) {
            return 15;
        } else {
            //error
            return -1;
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Spinner s1 = (Spinner)findViewById(R.id.spinner1);
        int spinner1Value = getHexValue(s1.getSelectedItem().toString());
        Spinner s2 = (Spinner)findViewById(R.id.spinner2);
        int spinner2Value = getHexValue(s2.getSelectedItem().toString());
        /*Spinner s3 = (Spinner)findViewById(R.id.spinner3);
        int spinner3Value = getHexValue(s3.getSelectedItem().toString());
        Spinner s4 = (Spinner)findViewById(R.id.spinner4);
        int spinner4Value = getHexValue(s4.getSelectedItem().toString());*/

        current = spinner1Value + spinner2Value * 16; //+ spinner3Value * 16 * 16 + spinner4Value * 16 * 16 * 16;

        TextView currentText = (TextView) findViewById(R.id.textViewCurrentValue);
        currentText.setText(Integer.toString(current));

        if(current == value) {
            win();
        }
    }

    public void onNothingSelected(AdapterView parent) {
        // Do nothing.
    }

    public void win() {
        if(roundCount == 1) {
            chronometer.stop();
            TextView currentText = (TextView) findViewById(R.id.textViewCurrentValue);
            currentText.setText("You win!");
            Button start = (Button) findViewById(R.id.StartButton);
            start.setEnabled(true);
            int score = 200000 - ((int)(SystemClock.elapsedRealtime()-chronometer.getBase()));
            new SaveScore(this, SCORE_VALUE, score);

        }else {
            roundCount++;
            roundReset();

        }
    }
}
