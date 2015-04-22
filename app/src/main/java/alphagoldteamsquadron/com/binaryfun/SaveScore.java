package alphagoldteamsquadron.com.binaryfun;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SaveScore {
    public SaveScore(Context c, String key, int score){
        this.key = key;
        this.c = c;
        this.score = score;
        if(isHighScore()){
            prompt();
        }else{
            displayScores();
        }

    }
    private String key;
    private Context c;
    private String name;
    private int score;

    public final static String EXTRA_MESSAGE = "com.alphagoldteamsquadron.binaryfun.MESSAGE";

    public void prompt(){
        final EditText txt = new EditText(c);

        txt.setHint("AAA");

        new AlertDialog.Builder(c)
                .setTitle("New High Score!")
                .setMessage("What's your initials?")
                .setView(txt)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        name = txt.getText().toString();
                        dialog.dismiss();
                        saveScore(c);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();
    }

    public boolean isHighScore(){
        SharedPreferences saveFile = c.getSharedPreferences(key, Context.MODE_PRIVATE);
        Set set = saveFile.getStringSet(key, null);
        if(set == null || set.size()<5){
            populateScores(saveFile);
            return false;
        }
        List<String> scores = new ArrayList<>(saveFile.getStringSet(key, null));
        //Inserts score in proper place, limit of 5 places
        for(int i = 0; i < 5; i++){
            String s = scores.get(i).replaceAll("[^0-9]","");
            if(score > Integer.parseInt(s)){
                return true;
            }
        }
        return false;
    }
    public void saveScore(Context c){
        SharedPreferences saveFile = c.getSharedPreferences(key, Context.MODE_PRIVATE);
        List<String> scores = new ArrayList<>(saveFile.getStringSet(key, null));
        //Inserts score in proper place, limit of 5 places
        for(int i = 0; i < 5; i++){
            String s = scores.get(i).replaceAll("[^0-9]","");
            if(score > Integer.parseInt(s)){
                scores.add(i, name + score);
                break;
            }
        }

        //Removes smallest value
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < 6; i++) {
            String s = scores.get(i).replaceAll("[^0-9]", "");
            if (Integer.parseInt(s) <= min) {
                min = Integer.parseInt(s);
                index = i;
            }
        }
        scores.remove(index);
        //Save new high score table
        SharedPreferences.Editor editor = saveFile.edit();
        editor.putStringSet(key, new HashSet<>(scores));
        editor.apply();
        displayScores();
    }

    public void displayScores(){
        //Display scores
        Intent intent = new Intent(c, HighScores.class);
        intent.putExtra(EXTRA_MESSAGE, key);
        c.startActivity(intent);
    }

    public void populateScores(SharedPreferences saveFile){
        Set scores = new HashSet<String>();
        for(int i = 0; i < 5; i++){
            scores.add("AAA           " + i);
        }
        //Save new high score table
        SharedPreferences.Editor editor = saveFile.edit();
        editor.putStringSet(key, scores);
        editor.apply();
    }
}
