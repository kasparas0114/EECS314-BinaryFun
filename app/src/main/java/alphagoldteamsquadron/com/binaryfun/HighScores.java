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

        List<String> scores = new ArrayList<>(saveFile.getStringSet(message, null));

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
        TextView tName = (TextView) findViewById(R.id.textViewName0);
        TextView tScore = (TextView) findViewById(R.id.textViewScore0);
        tName.setText(scores.get(4).replaceAll("[^A-Za-z]", ""));
        tScore.setText(scores.get(4).replaceAll("[^0-9]", ""));

        tName = (TextView) findViewById(R.id.textViewName1);
        tScore = (TextView) findViewById(R.id.textViewScore1);
        tName.setText(scores.get(3).replaceAll("[^A-Za-z]", ""));
        tScore.setText(scores.get(3).replaceAll("[^0-9]", ""));

        tName = (TextView) findViewById(R.id.textViewName2);
        tScore = (TextView) findViewById(R.id.textViewScore2);
        tName.setText(scores.get(2).replaceAll("[^A-Za-z]", ""));
        tScore.setText(scores.get(2).replaceAll("[^0-9]", ""));

        tName = (TextView) findViewById(R.id.textViewName3);
        tScore = (TextView) findViewById(R.id.textViewScore3);
        tName.setText(scores.get(1).replaceAll("[^A-Za-z]", ""));
        tScore.setText(scores.get(1).replaceAll("[^0-9]", ""));

        tName = (TextView) findViewById(R.id.textViewName4);
        tScore = (TextView) findViewById(R.id.textViewScore4);
        tName.setText(scores.get(0).replaceAll("[^A-Za-z]", ""));
        tScore.setText(scores.get(0).replaceAll("[^0-9]", ""));
    }

}
