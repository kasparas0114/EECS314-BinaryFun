package alphagoldteamsquadron.com.binaryfun;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {
    //Convention for keys is to put company name as prefix
    public final static String EXTRA_MESSAGE = "com.alphagoldteamsquadron.binaryfun.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Ran when button is clicked
    public void startGame(View view) {
        Intent intent = new Intent(this, BinaryToDecimal.class);
        String message = "Bananas!!!";
        //This is how we pass data to new activities
        intent.putExtra(EXTRA_MESSAGE, message);
        //Starts binaryToDecimal activity
        startActivity(intent);
    }

    //Ran when button is clicked
    public void startBinaryAdderGame(View view) {
        Intent intent = new Intent(this, BinaryAdder.class);
        String message = "Bananas!!!";
        //This is how we pass data to new activities
        intent.putExtra(EXTRA_MESSAGE, message);
        //Starts binaryToDecimal activity
        startActivity(intent);
    }
}
