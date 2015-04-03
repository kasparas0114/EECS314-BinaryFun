package alphagoldteamsquadron.com.binaryfun;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.ToggleButton;


public class BinaryToDecimal extends Activity {
    
    private static int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_binary_to_decimal);

        // Get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Create the text view
        /*TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        // Set the text view as the activity layout
        setContentView(textView);*/
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
    }
}
