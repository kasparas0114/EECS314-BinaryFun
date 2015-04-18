package alphagoldteamsquadron.com.binaryfun;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.text.DecimalFormat;


/**
 * Created by Drew Mitchell on 4/15/15.
 */
public class BinaryAdder extends Activity {

    private int valueA = 0;
    private int valueB = 0;
    private int current = 0;

    private int[] buttonIDs = {R.id.toggleButton1, R.id.toggleButton2, R.id.toggleButton4,
            R.id.toggleButton8, R.id.toggleButton16, R.id.toggleButton32, R.id.toggleButton64,
            R.id.toggleButton128, R.id.toggleButton256};

    /** Array of toggle buttons. LITTLE ENDIAN (e.g. index 0 is the smallest bit) */
    private ToggleButton[] toggles = new ToggleButton[buttonIDs.length];

    private int[] textViewA_IDs = {R.id.labelA1, R.id.labelA2, R.id.labelA4, R.id.labelA8,
            R.id.labelA16, R.id.labelA32, R.id.labelA64, R.id.labelA128};

    private TextView[] textViewsA = new TextView[textViewA_IDs.length];

    private int[] textViewB_IDs = {R.id.labelB1, R.id.labelB2, R.id.labelB4, R.id.labelB8,
            R.id.labelB16, R.id.labelB32, R.id.labelB64, R.id.labelB128};

    private TextView[] textViewsB = new TextView[textViewB_IDs.length];




    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_binary_adder);

        // Collect all buttons into the array
        for (int i=0; i<buttonIDs.length; i++)
            toggles[i] = (ToggleButton) findViewById(buttonIDs[i]);

        // Collect all the TextViews for value A into the array
        for (int i=0; i<textViewA_IDs.length; i++)
            textViewsA[i] = (TextView) findViewById(textViewA_IDs[i]);

        // Collect all the TextViews for value A into the array
        for (int i=0; i<textViewB_IDs.length; i++)
            textViewsB[i] = (TextView) findViewById(textViewB_IDs[i]);


        chronometer = (Chronometer)findViewById(R.id.chronometer);


        initialize();
    }

    public void initialize(){
        enableToggles(false);
    }

    public void startBinaryGame(View view) {
        valueA = (int)(Math.random() * 256);
        valueB = (int)(Math.random() * 256);

        updateAddend(textViewsA, valueA);
        updateAddend(textViewsB, valueB);

        enableToggles(true);
        zeroToggles();



        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();


        // Clear the "you won" message
        TextView currentText = (TextView) findViewById(R.id.statusText);
        currentText.setText("");
    }
    public void onToggleClicked(View view) {
        boolean on = ((ToggleButton) view).isChecked();

        if (on) {
            //toggleButton is 1
            switch (view.getId()) {
                case R.id.toggleButton256:
                    current += 256;
                    break;
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
                case R.id.toggleButton256:
                    current -= 256;
                    break;
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

        if(valueA + valueB == current) {
            win();
        }
    }

    private void win(){
        chronometer.stop();
        TextView currentText = (TextView) findViewById(R.id.statusText);
        enableToggles(false);
        currentText.setText("You win!");
    }


    /** Update the text views (bits) for a specific array (number) to the specified value. */
    private void updateAddend(TextView[] bits, int value) {
        // Convert decimal value to 8-bit binary number
        String binary = String.format("%8s", Integer.toBinaryString(value)).replace(' ', '0');

        // Both the passed in value and the text views should have the same number of bits
        assert(bits.length == binary.length());

        // Set each bit of the text views
        for (int i=0; i<bits.length; i++) {
            bits[i].setText(String.valueOf(binary.charAt(bits.length - 1 - i)));
        }
    }

    /** Set all the toggles to enabled or disabled, according to parameter. */
    private void enableToggles(boolean enabled) {
        for (ToggleButton button : toggles) {
            button.setEnabled(enabled);
        }
    }

    /** Set all the toggles to zero. */
    private void zeroToggles() {
        for (ToggleButton button : toggles) {
            button.setChecked(false);
        }
        current = 0;
    }
}
