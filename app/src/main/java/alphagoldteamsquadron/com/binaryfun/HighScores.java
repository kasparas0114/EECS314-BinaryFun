package alphagoldteamsquadron.com.binaryfun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class HighScores extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        //Get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //Gets high scores for game
        SharedPreferences saveFile = getSharedPreferences(message, Context.MODE_PRIVATE);

        List<String> scores = new ArrayList<String>(saveFile.getStringSet(message, null));
        //List<String> names = new ArrayList<String>(saveFile.getStringSet(message+"Names", null));

        for(int i = 0; i<4; i++){
            int index = i;
            for(int j = i+1; j<5; j++) {
                if (Integer.parseInt(scores.get(j).replaceAll("[^0-9]", "")) < Integer.parseInt(scores.get(index).replaceAll("[^0-9]", ""))) {
                    index = j;
                }
            }
            String smaller = scores.get(index);
            scores.set(index, scores.get(i));
            scores.set(i, smaller);
        }
        TextView t = (TextView) findViewById(R.id.textView1);
        t.setText(scores.get(4));

        t = (TextView) findViewById(R.id.textView2);
        t.setText(scores.get(3));

        t = (TextView) findViewById(R.id.textView3);
        t.setText(scores.get(2));

        t = (TextView) findViewById(R.id.textView4);
        t.setText(scores.get(1));

        t = (TextView) findViewById(R.id.textView5);
        t.setText(scores.get(0));
    }

}
