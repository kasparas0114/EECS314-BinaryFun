package alphagoldteamsquadron.com.binaryfun;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;


public class BooleanFun extends Activity {

    private Chronometer chronometer;
    private Button[] buttonBooleans;
    private Button[] buttonBoolOps;
    private ColorStateList defaultButtonTextColors;
    private enum boolOpType {OR,AND,NAND,NOR,XOR,XNOR}
    private static final String SCORE_VALUE = "BooleanFunValues";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boolean_fun);
        chronometer = (Chronometer)findViewById(R.id.BoolChronometer);
        defaultButtonTextColors = ((Button) findViewById(R.id.BoolStartEndButton)).getTextColors();
        initialize();
    }

    //Initialize the activity when created
    public void initialize() {

        //Disable buttons
        buttonBooleans = new Button[7];

        buttonBooleans[0] = (Button) findViewById(R.id.BoolButton0);
        buttonBooleans[0].setClickable(false);
        buttonBooleans[1] = (Button) findViewById(R.id.BoolButton1);
        buttonBooleans[1].setClickable(false);
        buttonBooleans[2] = (Button) findViewById(R.id.BoolButton2);
        buttonBooleans[2].setClickable(false);
        buttonBooleans[3] = (Button) findViewById(R.id.BoolButton3);
        buttonBooleans[3].setClickable(false);
        buttonBooleans[4] = (Button) findViewById(R.id.BoolButton4);
        buttonBooleans[4].setClickable(false);
        buttonBooleans[5] = (Button) findViewById(R.id.BoolButton5);
        buttonBooleans[5].setClickable(false);
        buttonBooleans[6] = (Button) findViewById(R.id.BoolButton6);
        buttonBooleans[6].setClickable(false);

        buttonBoolOps = new Button[3];
        buttonBoolOps[0] = (Button) findViewById(R.id.BoolOpButton0);
        buttonBoolOps[0].setClickable(false);
        buttonBoolOps[1] = (Button) findViewById(R.id.BoolOpButton1);
        buttonBoolOps[1].setClickable(false);
        buttonBoolOps[2] = (Button) findViewById(R.id.BoolOpButton2);
        buttonBoolOps[2].setClickable(false);

    }

    //Start/Stop BooleanFun game, triggered by start/end toggleButton
    public void startBoolGame(View view) {
        boolean on = ((ToggleButton) view).isChecked();
        TextView winLossText = (TextView)findViewById(R.id.BoolWinLossLabel);
        if(on) {
            winLossText.setText("");
            createBoolTree();
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
        } else
        {

            chronometer.stop();
            lockButtons();
            if(checkWinConditions()) { // check winning conditions here
                //handle win event
                winLossText.setText("You Won! Great job!");
                int score = generateScore(
                        200000 - ((int)(SystemClock.elapsedRealtime()-chronometer.getBase())));
                new SaveScore(this, SCORE_VALUE, score);
            }
            else {
                //handle loss
                winLossText.setText("Not quite. Try another Round?");
            }
        }
    }

    //switch the boolean shown on the button, triggered by a BoolButton click
    public void boolButtonClicked(View view) {
        Button clickedButton = (Button) view;
        if(clickedButton.getText() == null) {
            clickedButton.setText("0");
        } else {
            switch (clickedButton.getText().toString()) {
                case "":
                    clickedButton.setText("0");
                    break;
                case "0":
                    clickedButton.setText("1");
                    break;
                case "1":
                    clickedButton.setText("0");
                    break;
                default:
                    throw new RuntimeException(
                            "The ButtonText on the clicked button is an unspecified string");
            }
        }
    }

    //switch the boolean operator shown on the button, triggered by a BoolOpButton Click
    public void boolOpButtonClicked(View view) {
        Button clickedButton = (Button) view;
        if(clickedButton.getText() == null) {
            clickedButton.setText("XNOR");
        } else {
            switch(clickedButton.getText().toString())
            {
                case "":
                    clickedButton.setText("XNOR");
                    break;
                case "XNOR":
                    clickedButton.setText("XOR");
                    break;
                case "XOR":
                    clickedButton.setText("AND");
                    break;
                case "AND":
                    clickedButton.setText("OR");
                    break;
                case "OR":
                    clickedButton.setText("NAND");
                    break;
                case "NAND":
                    clickedButton.setText("NOR");
                    break;
                case "NOR":
                    clickedButton.setText("XNOR");
                    break;
                default:
                    throw new IllegalArgumentException(
                            "The ButtonText on the clicked button is an unspecified string");
            }
        }
    }

    //Creates the boolean tree needed to start the game
    public void createBoolTree() {

        //Bool Tree looks like:
        // 0 1 2 3
        //  4   5
        //    6
        boolean[] boolTree = new boolean[7];

        //Bool op Tree looks like
        // 0 1
        //  2
        boolOpType[] boolOpTree = new boolOpType[3];

        //generate first four boolean vals
        Random myRand = new Random();

        for(int i = 0; i < 4 ;i++) {
            boolTree[i] = myRand.nextBoolean();
        }

        //generate booleanOps
        for(int i = 0 ;i < boolOpTree.length; i++) {
            boolOpTree[i] = null;

            switch (myRand.nextInt(6)) {
                case 0:
                    boolOpTree[i] = boolOpType.AND;
                    break;
                case 1:
                    boolOpTree[i] = boolOpType.OR;
                    break;
                case 2:
                    boolOpTree[i] = boolOpType.NOR;
                    break;
                case 3:
                    boolOpTree[i] = boolOpType.NAND;
                    break;
                case 4:
                    boolOpTree[i] = boolOpType.XOR;
                    break;
                case 5:
                    boolOpTree[i] = boolOpType.XNOR;
                    break;
            }
        }

        boolTree[4] = performBoolOp(boolTree[0], boolOpTree[0], boolTree[1]);
        boolTree[5] = performBoolOp(boolTree[2], boolOpTree[1], boolTree[3]);
        boolTree[6] = performBoolOp(boolTree[4], boolOpTree[2], boolTree[5]);

        setupButtonText(boolTree,boolOpTree);

    }

    //Sets up the buttons to the right states right before the game starts
    public void setupButtonText(boolean[] boolTree, boolOpType[] boolOpTree) {

        for(int i=0 ; i < boolTree.length ; i++){
            buttonBooleans[i].setClickable(false);
            buttonBooleans[i].setTextColor(defaultButtonTextColors);
            if(boolTree[i]){
                buttonBooleans[i].setText("1");
            }
            else {
                buttonBooleans[i].setText("0");
            }
        }

        for(int i = 0 ; i < boolOpTree.length ; i++){
            buttonBoolOps[i].setClickable(false);
            buttonBoolOps[i].setTextColor(defaultButtonTextColors);
            switch(boolOpTree[i]) {
               case XNOR:
                   buttonBoolOps[i].setText("XNOR");
                   break;
               case XOR:
                   buttonBoolOps[i].setText("XOR");
                   break;
               case AND:
                   buttonBoolOps[i].setText("AND");
                   break;
               case OR:
                   buttonBoolOps[i].setText("OR");
                   break;
               case NAND:
                   buttonBoolOps[i].setText("NAND");
                   break;
               case NOR:
                   buttonBoolOps[i].setText("NOR");
                   break;
               default:
                   throw new IllegalArgumentException("BoolOp Cannot Be null");
           }
        }

        //Now that we have our tree, we need to set some things to null to allow the player
        //to guess the answer
        Random myRand = new Random();
        //We'll take 2 random aspects away from each triad of the tree
        int toTakeFromTriad1 = 2;
        int toTakeFromTriad2 = 2;
        int toTakeFromTriad3 = 2;


        // bool and op tree (not taking last bool answer)
        // 0 1 2 3
        //  6   7
        //  4   5
        //    8

        //Take numbers/ operators away from the first triad
        while(toTakeFromTriad3 > 0)
        {
            int generatedNum = myRand.nextInt(3);
            switch(generatedNum) {
                case 0:
                    if(!buttonBooleans[4].getText().toString().equals("")) {
                        toTakeFromTriad3--;
                        toTakeFromTriad1--;
                        buttonBooleans[4].setText("");
                        buttonBooleans[4].setClickable(true);
                        buttonBooleans[4].setTextColor(
                                getResources().getColor(android.R.color.holo_green_dark));
                    }
                    break;
                case 1:
                    if(!buttonBooleans[5].getText().toString().equals(""))
                    {
                        toTakeFromTriad3--;
                        toTakeFromTriad2--;
                        buttonBooleans[5].setText("");
                        buttonBooleans[5].setClickable(true);
                        buttonBooleans[5].setTextColor(
                                getResources().getColor(android.R.color.holo_green_dark));
                    }
                    break;
                case 2:
                    if(!buttonBoolOps[2].getText().toString().equals(""))
                    {
                        toTakeFromTriad3--;
                        buttonBoolOps[2].setText("");
                        buttonBoolOps[2].setClickable(true);
                        buttonBoolOps[2].setTextColor(
                                getResources().getColor(android.R.color.holo_green_dark));
                    }
                    break;
                default:
                    throw new RuntimeException(
                            "generated num should not be less than 0 or greater than 2");
            }
        }

        //long and complicated/real nasty loop to take away portions of the tree for the user to
        //fill in
        while(toTakeFromTriad2 > 0 || toTakeFromTriad1 > 0 ) {
            int generatedNum = myRand.nextInt(8);
            boolean found = false;

            switch(generatedNum) {
                case 0:
                case 1:
                    if(toTakeFromTriad1 > 0 &&
                            !buttonBooleans[generatedNum].getText().toString().equals("")) {
                        toTakeFromTriad1--;
                        found = true;
                    }
                    break;
                case 6:
                    if (toTakeFromTriad1 > 0 &&
                            !buttonBoolOps[generatedNum-6].getText().toString().equals("")) {
                        toTakeFromTriad1--;
                        found = true;
                    }
                    break;
                case 2:
                case 3:
                    if (toTakeFromTriad2 > 0 &&
                            !buttonBooleans[generatedNum].getText().toString().equals("")) {
                        toTakeFromTriad2--;
                        found = true;
                    }
                    break;
                case 7:
                    if (toTakeFromTriad2 > 0 &&
                            !buttonBoolOps[generatedNum-6].getText().toString().equals("")) {
                        toTakeFromTriad2--;
                        found = true;
                    }
                    break;

                case 4:
                case 5:
                    //already taken care of in previous loop
                    break;
                default:
                    throw new RuntimeException(
                            "generated num should not be less than 0 or greater than 8");
            }

            if(found) {
                if(generatedNum >= 6){
                    buttonBoolOps[generatedNum-6].setText("");
                    buttonBoolOps[generatedNum-6].setClickable(true);
                    buttonBoolOps[generatedNum-6].setTextColor(
                            getResources().getColor(android.R.color.holo_green_dark));

                }
                else {
                    buttonBooleans[generatedNum].setText("");
                    buttonBooleans[generatedNum].setClickable(true);
                    buttonBooleans[generatedNum].setTextColor(
                            getResources().getColor(android.R.color.holo_green_dark));
                }
            }
        }
    }

    //performs the boolean operator specified on the two boolean operands
    public boolean performBoolOp(boolean firstBool, boolOpType boolOp, boolean secondBool) {
        switch(boolOp) {
            case XNOR:
                return !(firstBool ^ secondBool);
            case XOR:
                return firstBool ^ secondBool;
            case AND:
                return firstBool && secondBool;
            case OR:
                return firstBool || secondBool;
            case NAND:
                return !(firstBool && secondBool);
            case NOR:
                return !(firstBool || secondBool);
            default:
                throw new IllegalArgumentException("BoolOp Cannot Be null");
        }
    }

    //performs the boolean operator specified on the two boolean operands
    public boolean performBoolOp(String firstBool, String boolOp, String secondBool) {
        return performBoolOp(
                intStringToBool(firstBool),
                boolOpStringToEnum(boolOp),
                intStringToBool(secondBool)
        );
    }

    //Generates the score for the game
    public int generateScore(int baseScore) {
        for(int i = 0 ; i < buttonBoolOps.length; i++) {
            switch(buttonBoolOps[i].getText().toString())
            {
                case "XNOR":
                    baseScore += 1000;
                    break;
                case "XOR":
                    baseScore += 750;
                    break;
                case "AND":
                    baseScore += 250;
                    break;
                case "OR":
                    baseScore += 100;
                    break;
                case "NAND":
                    baseScore += 500;
                    break;
                case "NOR":
                    baseScore += 250;
                    break;
                default:
                    throw new IllegalArgumentException("BoolOp cannot be null");
            }
        }
        return baseScore;
    }

    //Check if the player has won the game
    public boolean checkWinConditions() {
        for (Button buttonBoolean : buttonBooleans) {
            if (buttonBoolean.getText() == null ||
                    buttonBoolean.getText().toString().equals("")) {
                return false; //if any of the buttons have blank text on them player cannot win
            }
        }

        for (Button buttonBoolOp : buttonBoolOps) {
            if (buttonBoolOp.getText() == null ||
                    buttonBoolOp.getText().toString().equals("")) {
                return false; //if any of the buttons have blank text on them player cannot win
            }
        }
        //check first triad
        if(intStringToBool(buttonBooleans[4].getText().toString()) != performBoolOp(
                buttonBooleans[0].getText().toString(),
                buttonBoolOps[0].getText().toString(),
                buttonBooleans[1].getText().toString()
        )) {
            return false;
        }
        else if(intStringToBool(buttonBooleans[5].getText().toString()) != performBoolOp(
                buttonBooleans[2].getText().toString(),
                buttonBoolOps[1].getText().toString(),
                buttonBooleans[3].getText().toString()
        )) {
            return false;
        }
        else {
            return intStringToBool(buttonBooleans[6].getText().toString()) == performBoolOp(
                    buttonBooleans[4].getText().toString(),
                    buttonBoolOps[2].getText().toString(),
                    buttonBooleans[5].getText().toString()
            );
        }
    }

    //convert a boolean specified as either a 0 or a 1
    //to a boolean type
    public boolean intStringToBool(String boolString) {
        switch(boolString) {
            case "0":
                return false;
            case "1":
                return true;
            default:
                throw new IllegalArgumentException("String should be either 0 or 1");
        }
    }

    //Convert an operator as a string to an enum type
    public boolOpType boolOpStringToEnum(String boolOpString) {
        switch(boolOpString)
        {
            case "XNOR":
                return boolOpType.XNOR;
            case "XOR":
                return boolOpType.XOR;
            case "AND":
                return boolOpType.AND;
            case "OR":
                return boolOpType.OR;
            case "NAND":
                return boolOpType.NAND;
            case "NOR":
                return boolOpType.NOR;
            default:
                throw new IllegalArgumentException("BoolOp cannot be null");
        }
    }

    //Set buttons to be nonClickable and have default text Color
    public void lockButtons() {
        for (Button buttonBoolean : buttonBooleans) {
            buttonBoolean.setClickable(false);
            buttonBoolean.setTextColor(defaultButtonTextColors);
        }
        for (Button buttonBoolOp : buttonBoolOps) {
            buttonBoolOp.setClickable(false);
            buttonBoolOp.setTextColor(defaultButtonTextColors);
        }
    }
}
