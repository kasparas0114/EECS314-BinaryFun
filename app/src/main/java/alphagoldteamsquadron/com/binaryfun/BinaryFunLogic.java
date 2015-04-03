package alphagoldteamsquadron.com.binaryfun;

import android.os.SystemClock;

/**
 * Created by timlax on 4/2/15.
 */
public class BinaryFunLogic
{
    //The running total that should display in the box
    private long timeElapsed;

    //Last time the getTimeElapsed method is created
    private long timeOfLastCall;

    private boolean gamePaused;

    public BinaryFunLogic()
    {
        //Setup here
    }

    public void startGame()
    {
        timeOfLastCall = SystemClock.uptimeMillis();
        timeElapsed = 0L;

    }

    //unpause game
    public String getTimeElapsed()
    {
        if(!gamePaused) {
            long tempTimeLastCall = SystemClock.uptimeMillis();
            timeElapsed += SystemClock.uptimeMillis() - timeOfLastCall;
            timeOfLastCall = tempTimeLastCall; //comment
            return null;
            //TODO return formattedString;
        } else
        {
            gamePaused = false;
            timeOfLastCall = SystemClock.uptimeMillis();
            return null;
            //TODO return formmatted return timeElapsed;
        }
    }

    //Pauses game
    public String pauseGame()
    {
        gamePaused = true;//Stop timer and handle other things
        timeOfLastCall = 0;
        return null;
    }

    private String formatTimeString()
    {
        return null;
    }
}
