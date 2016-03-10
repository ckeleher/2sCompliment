package chamberlin.daniel.twoscompliment;

import android.content.SharedPreferences;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import chamberlin.daniel.twoscompliment.tile;

/**
 * Created by Daniel on 3/1/2016.
 */
public class boardActivity extends AppCompatActivity {

    private static final String TAG = boardActivity.class.getSimpleName();

    //Global variables
    public static int boardSize = 8; //Can be 4, 6, 8, 10

    private tile[][] gameBoard;
    private ImageButton[][] textBoard;
    MediaPlayer music;
    Chronometer chronometer;


    //Main
    @Override
    protected void onCreate(Bundle savedInstanceState){
        //fetches boardSize intent and establishes proper arrays
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            boardSize = extras.getInt("size");
        }
        textBoard = new ImageButton[boardSize][boardSize];

        textBoard = new ImageButton[boardSize][boardSize];
        Log.d(TAG, "in onCreate with boardSize"+boardSize);

        if(boardSize == 4) {
            setContentView(R.layout.activity_board);
        }
        if(boardSize == 6) {
            setContentView(R.layout.activity_board_6);
        }
        if(boardSize == 8){
            setContentView(R.layout.activity_board_8);
        }

        tile[][] board = new tile[boardSize][boardSize];
        gameBoard = prepareForPlayer(loadInitialGameState(boardSize, board));


        for (int x = 0; x < boardSize; x++){
            for (int y = 0; y < boardSize; y++){
                Log.d(TAG, "gameBoard["+x+"]["+y+"].blockType = "+ gameBoard[x][y].blockType);
            }
        }

        textBoard[0][0] = (ImageButton) findViewById(R.id.t00);
        textBoard[1][0] = (ImageButton) findViewById(R.id.t10);
        textBoard[2][0] = (ImageButton) findViewById(R.id.t20);
        textBoard[3][0] = (ImageButton) findViewById(R.id.t30);
        textBoard[0][1] = (ImageButton) findViewById(R.id.t01);
        textBoard[1][1] = (ImageButton) findViewById(R.id.t11);
        textBoard[2][1] = (ImageButton) findViewById(R.id.t21);
        textBoard[3][1] = (ImageButton) findViewById(R.id.t31);
        textBoard[0][2] = (ImageButton) findViewById(R.id.t02);
        textBoard[1][2] = (ImageButton) findViewById(R.id.t12);
        textBoard[2][2] = (ImageButton) findViewById(R.id.t22);
        textBoard[3][2] = (ImageButton) findViewById(R.id.t32);
        textBoard[0][3] = (ImageButton) findViewById(R.id.t03);
        textBoard[1][3] = (ImageButton) findViewById(R.id.t13);
        textBoard[2][3] = (ImageButton) findViewById(R.id.t23);
        textBoard[3][3] = (ImageButton) findViewById(R.id.t33);

        //buttons for 6by6 board
        if(boardSize >= 6){
            textBoard[0][4] = (ImageButton) findViewById(R.id.t04);
            textBoard[0][5] = (ImageButton) findViewById(R.id.t05);
            textBoard[1][4] = (ImageButton) findViewById(R.id.t14);
            textBoard[1][5] = (ImageButton) findViewById(R.id.t15);
            textBoard[2][4] = (ImageButton) findViewById(R.id.t24);
            textBoard[2][5] = (ImageButton) findViewById(R.id.t25);
            textBoard[3][4] = (ImageButton) findViewById(R.id.t34);
            textBoard[3][5] = (ImageButton) findViewById(R.id.t35);
            textBoard[4][0] = (ImageButton) findViewById(R.id.t40);
            textBoard[4][1] = (ImageButton) findViewById(R.id.t41);
            textBoard[4][2] = (ImageButton) findViewById(R.id.t42);
            textBoard[4][3] = (ImageButton) findViewById(R.id.t43);
            textBoard[4][4] = (ImageButton) findViewById(R.id.t44);
            textBoard[4][5] = (ImageButton) findViewById(R.id.t45);
            textBoard[5][0] = (ImageButton) findViewById(R.id.t50);
            textBoard[5][1] = (ImageButton) findViewById(R.id.t51);
            textBoard[5][2] = (ImageButton) findViewById(R.id.t52);
            textBoard[5][3] = (ImageButton) findViewById(R.id.t53);
            textBoard[5][4] = (ImageButton) findViewById(R.id.t54);
            textBoard[5][5] = (ImageButton) findViewById(R.id.t55);
        }

        if(boardSize >= 8){
            textBoard[0][6] = (ImageButton) findViewById(R.id.t06);
            textBoard[0][7] = (ImageButton) findViewById(R.id.t07);
            textBoard[1][6] = (ImageButton) findViewById(R.id.t16);
            textBoard[1][7] = (ImageButton) findViewById(R.id.t17);
            textBoard[2][6] = (ImageButton) findViewById(R.id.t26);
            textBoard[2][7] = (ImageButton) findViewById(R.id.t27);
            textBoard[3][6] = (ImageButton) findViewById(R.id.t36);
            textBoard[3][7] = (ImageButton) findViewById(R.id.t37);
            textBoard[4][6] = (ImageButton) findViewById(R.id.t46);
            textBoard[4][7] = (ImageButton) findViewById(R.id.t47);
            textBoard[5][6] = (ImageButton) findViewById(R.id.t56);
            textBoard[5][7] = (ImageButton) findViewById(R.id.t57);
            textBoard[6][0] = (ImageButton) findViewById(R.id.t60);
            textBoard[6][1] = (ImageButton) findViewById(R.id.t61);
            textBoard[6][2] = (ImageButton) findViewById(R.id.t62);
            textBoard[6][3] = (ImageButton) findViewById(R.id.t63);
            textBoard[6][4] = (ImageButton) findViewById(R.id.t64);
            textBoard[6][5] = (ImageButton) findViewById(R.id.t65);
            textBoard[6][6] = (ImageButton) findViewById(R.id.t66);
            textBoard[6][7] = (ImageButton) findViewById(R.id.t67);
            textBoard[7][0] = (ImageButton) findViewById(R.id.t70);
            textBoard[7][1] = (ImageButton) findViewById(R.id.t71);
            textBoard[7][2] = (ImageButton) findViewById(R.id.t72);
            textBoard[7][3] = (ImageButton) findViewById(R.id.t73);
            textBoard[7][4] = (ImageButton) findViewById(R.id.t74);
            textBoard[7][5] = (ImageButton) findViewById(R.id.t75);
            textBoard[7][6] = (ImageButton) findViewById(R.id.t76);
            textBoard[7][7] = (ImageButton) findViewById(R.id.t77);
        }

        /*for(int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j <gameBoard[i].length; j++){
                setButton(textBoard[i][j], i, j);
            }
        }*/

        setAllButtons();
        music = MediaPlayer.create(this, R.raw.broke_for_free_night_owl);
        music.setLooping(true);

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        boolean musicOn = settings.getBoolean("musicOn",true);
        if(musicOn){
            music.start();
        }
        chronometer = (Chronometer)findViewById(R.id.chronometer);
        chronometer.start();
    }

    private void setButton(ImageButton b, int x, int y){

        tile t = gameBoard[x][y];
        if(t.blockType == 1){
            b.setBackgroundResource(R.mipmap.redtile);
        }
        if(t.blockType == 2){
            b.setBackgroundResource(R.mipmap.bluetile);
        }
        if(t.blockType == 3){
           b.setBackgroundResource(R.mipmap.emptytile);
        }

        checkFull(gameBoard);
    }

    private void setAllButtons (){
        for(int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j <gameBoard[i].length; j++){
                setButton(textBoard[i][j], i, j);
            }
        }
    }

    //when used, reset the board by setting all unlocked tiles to empty
    //used as a catch-all 'undo' button
    private void resetBoard (tile[][] gameBoard){
        for(int x = 0; x<boardSize; x++){
            for(int y = 0; y<boardSize; y++){
                if(gameBoard[x][y].locked == false){
                    gameBoard[x][y].blockType = 3;
                }
            }
        }
    }

    private void checkFull(tile[][] gameBoard){
        //check if board is full of reds or blues
        boolean full = true;
        String[] msg = new String[2];
        TextView error = (TextView) findViewById(R.id.errorText);
        //cycle through board to find any empty tiles
        for(int x = 0; x<boardSize; x++){
            for(int y = 0; y<boardSize; y++){
                if(gameBoard[x][y].blockType == 3){
                    full = false;
                }
            }
        }
        if(full == true){
            msg = checkForCompletion(gameBoard);
            if(msg[0] == "true"){
                //player has won
            }
            else{
                //player has lost
            }
            //make text show error or success message

            error.setText(msg[1]);
            error.setVisibility(View.VISIBLE);
        }
        else{
            error.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPause(){
        pauseMusic();
        super.onPause();
    }

    @Override
    public void onResume(){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        boolean musicOn = settings.getBoolean("musicOn",true);
        if(musicOn) {
            startMusic();
        }
        super.onResume();
    }

    @Override
    public void onDestroy(){
        music.stop();
        music.release();
        super.onDestroy();
    }

    private void pauseMusic(){
        if(music.isPlaying())
            music.pause();
    }
    private void startMusic(){
        if(!music.isPlaying()) {
            music.start();
        }
    }

    //Loads the initial state of the game board
    //Currently this function is basically just a shell to call another function
    //Decided to keep it here in case I ever want to include alternate game modes
    //which don't generate the board in the same way that generateBoardState() does.
    private tile[][] loadInitialGameState(int boardSize, tile[][] gameBoard){
        return generateBoardState(boardSize, gameBoard);
        //return generateTestBoard(boardSize, gameBoard);
    }

    //Randomly generates a starting board state following the game rules
    private static tile[][] generateBoardState(int boardSize, tile[][] gameBoard){
        //used for deciding how many tiles to leave on the board
        Random r = new Random();

        //Fill board with empty tiles
        for(int i=0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                gameBoard[i][j] = new tile(0, j, i);				//tiles created as empty
            }
        }

        //Fill in the game board
        //i = column, j = row
        int[] numOneRow = new int[boardSize];
        int[] numTwoRow = new int[boardSize];
        for(int i=0; i < boardSize; i++){
            int numOne = 0;
            int numTwo = 0;
            for(int j=0; j < boardSize; j++){
                if(i == 0 || i == 1){																			//If this is one of the first two columns, don't need to check rows for threes
                    if(numOne < boardSize / 2){																	//Check that the column does not already have the max number of type 1 tiles
                        if(numTwo < boardSize / 2){																//Check that the column does not already have the max number of type 2 tiles
                            if(j >= 2){
                                if(gameBoard[i][j-1].blockType == 1 && gameBoard[i][j-2].blockType == 1){		//Check for a pair of type 1 tiles preceding the current tile space
                                    gameBoard[i][j].blockType = 2;
                                    numTwo++;
                                    numTwoRow[j]++;
                                }else if(gameBoard[i][j-1].blockType == 2 && gameBoard[i][j-2].blockType == 2){	//Check for a pair of type 2 tiles preceding the current tile space
                                    gameBoard[i][j].blockType = 1;
                                    numOne++;
                                    numOneRow[j]++;
                                }else{
                                    gameBoard[i][j].blockType = r.nextInt(2) + 1;								//If no preceding rule checks trigger, add a block of random type
                                    if(gameBoard[i][j].blockType == 1){
                                        numOne++;
                                        numOneRow[j]++;
                                    }
                                    if(gameBoard[i][j].blockType == 2){
                                        numTwo++;
                                        numTwoRow[j]++;
                                    }
                                }
                            }else{
                                gameBoard[i][j].blockType = r.nextInt(2) + 1;									//If this is one of the first pair of tiles in a column,
                                if(gameBoard[i][j].blockType == 1){ 											//none of the above rule checks can trigger, so just fill random
                                    numOne++;
                                    numOneRow[j]++;
                                }
                                if(gameBoard[i][j].blockType == 2){
                                    numTwo++;
                                    numTwoRow[j]++;
                                }
                            }
                        }else{
                            gameBoard[i][j].blockType = 1;
                            numOne++;
                            numOneRow[j]++;
                        }
                    }else{
                        gameBoard[i][j].blockType = 2;
                        numTwo++;
                        numTwoRow[j]++;
                    }
                }else{																							//If this is the 3rd+ column, checking for threes in rows is required
                    if(numOne < boardSize / 2){
                        if(numTwo < boardSize / 2){
                            if(numOneRow[j] < boardSize / 2){
                                if(numTwoRow[j] < boardSize / 2){
                                    if(gameBoard[i-1][j].blockType == 1 && gameBoard[i-2][j].blockType == 1){			//PROBLEM: what happens if this check deems that this j must be 2,
                                        if(j >= 2){																		//but the two previous column entries deem that it must be 1?
                                            if(gameBoard[i][j-1].blockType == 2 && gameBoard[i][j-2].blockType == 2){	//SOLUTION: move back one j entry and flip the tile type
                                                gameBoard[i][j-1].blockType = 1;
                                                gameBoard[i][j].blockType = 2;
                                                numOne++;
                                                numOneRow[j]++;
                                            }else{
                                                gameBoard[i][j].blockType = 2;														//Most of the code from this point on is copy-paste of code from earlier
                                                numTwo++;																			//in this nested loop that needs to be here more than once for different scenarios.
                                                numTwoRow[j]++;
                                            }
                                        }else{
                                            gameBoard[i][j].blockType = 2;
                                            numTwo++;
                                            numTwoRow[j]++;
                                        }
                                    }else if(gameBoard[i-1][j].blockType == 2 && gameBoard[i-2][j].blockType == 2){
                                        if(j >= 2){
                                            if(gameBoard[i][j-1].blockType == 1 && gameBoard[i][j-2].blockType == 1){
                                                gameBoard[i][j-1].blockType = 2;
                                                gameBoard[i][j].blockType = 1;
                                                numTwo++;
                                                numTwoRow[j]++;
                                            }else{
                                                gameBoard[i][j].blockType = 1;
                                                numOne++;
                                                numOneRow[j]++;
                                            }
                                        }else{
                                            gameBoard[i][j].blockType = 1;
                                            numOne++;
                                            numOneRow[j]++;
                                        }
                                    }else{
                                        if(j >= 2){
                                            if(gameBoard[i][j-1].blockType == 1 && gameBoard[i][j-2].blockType == 1){
                                                gameBoard[i][j].blockType = 2;
                                                numTwo++;
                                                numTwoRow[j]++;
                                            }else if(gameBoard[i][j-1].blockType == 2 && gameBoard[i][j-2].blockType == 2){
                                                gameBoard[i][j].blockType = 1;
                                                numOne++;
                                                numOneRow[j]++;
                                            }else{
                                                gameBoard[i][j].blockType = r.nextInt(2) + 1;
                                                if(gameBoard[i][j].blockType == 1){
                                                    numOne++;
                                                    numOneRow[j]++;
                                                }
                                                if(gameBoard[i][j].blockType == 2){
                                                    numTwo++;
                                                    numTwoRow[j]++;
                                                }
                                            }
                                        }else{
                                            gameBoard[i][j].blockType = r.nextInt(2) + 1;
                                            if(gameBoard[i][j].blockType == 1){
                                                numOne++;
                                                numOneRow[j]++;
                                            }
                                            if(gameBoard[i][j].blockType == 2){
                                                numTwo++;
                                                numTwoRow[j]++;
                                            }
                                        }
                                    }
                                }else{
                                    gameBoard[i][j].blockType = 1;
                                    numOne++;
                                    numOneRow[j]++;
                                }
                            }else{
                                gameBoard[i][j].blockType = 2;
                                numTwo++;
                                numTwoRow[j]++;
                            }
                        }else{
                            gameBoard[i][j].blockType = 1;
                            numOne++;
                            numOneRow[j]++;
                        }
                    }else{
                        gameBoard[i][j].blockType = 2;
                        numTwo++;
                        numTwoRow[j]++;
                    }
                }
            }
        }
        //if the below if statement evaluates to true, that means our board succeeded
        if(checkForEqualRows(gameBoard) == true && checkForEqualColumns(gameBoard) == true && checkForThrees(gameBoard) == true && checkEqualTileNumbers(gameBoard) == true){
            return gameBoard;
        }
        //else create a new board
        else {
            return generateBoardState(boardSize, gameBoard);
        }
    }

    //removes all tiles except for a select few, locks them so that
    // they cannot be changed by the player
    // and then returns the board
    private tile[][] prepareForPlayer(tile[][] gameBoard){
        Random r = new Random();
        //Decide how many cells to leave filled
        int minFill = 0;
        int maxFill = 0;
        int fillAmount;
        if(boardSize == 4){minFill = 4; maxFill = 5;}						//There's no formula to these numbers, they just seem to be good bounds for the given board sizes
        if(boardSize == 6){minFill = 8; maxFill = 12;}
        if(boardSize == 8){minFill = 15; maxFill = 20;}
        if(boardSize == 10){minFill = 21; maxFill = 30;}
        fillAmount = r.nextInt(maxFill - minFill + 1) + minFill;

        int currentFill = boardSize*boardSize;
        boolean done = false;

        //cycle through gameBoard until tiles are removed except for fillAmount

        while(done == false) {
            for (int x = 0; x < boardSize; x++) {
                for (int y = 0; y < boardSize; y++) {
                    if (currentFill > fillAmount) {
                        //used for deciding how many tiles to leave on the board
                        Random rn = new Random();
                        //change tile to empty and substract from startingFill
                        //20% chance to change
                        if(rn.nextInt(10)+1>8) {
                            gameBoard[x][y].blockType = 3;
                            currentFill--;
                            //unlock tiles set to empty
                            gameBoard[x][y].locked = false;
                        }
                    } else {
                        done = true;
                    }
                }
            }
        }

        return gameBoard;
    }

    private tile[] convertToOneD(tile[][] gameBoard){
        int vert = gameBoard.length;
        int total = 0;
        //determine length of new 1d array
        for(int i = 0; i < vert; i++){
            for(int j = 0; j < gameBoard[i].length; j++){
                total++;
                Log.d(TAG, "total = "+ total);
            }
        }
        tile[] newArray = new tile[total];
        int k = 0;
        for(int x = 0; x < vert; x++){
            for(int y = 0; y < gameBoard[x].length; y++){
                newArray[k] = gameBoard[x][y];
                k++;
            }
        }
        return newArray;
    }

    public void clickButton(View v){
        ImageButton btn = (ImageButton) v;
        tile t;
        for(int x = 0; x < boardSize; x++){
            for(int y = 0; y < boardSize; y++){
                if(btn == (ImageButton) textBoard[x][y]){
                    t = gameBoard[x][y];
                    if(t.locked==false) {
                        t.cycleTileState(t.blockType);
                        setButton(btn, x, y);
                    }
                    else{
                        //tell the player that the button is locked
                    }
                }
            }
        }

    }


    //-------------------------------------------------------------------------------------------------------
    //These functions are used for the final check on whether or not the user-completed board is valid.
    //They are also used for checking if the initial board is valid.
    //IMPORTANT: these functions all make the assumption that they are being passed a completely filled-in
    //           game board (i.e. every tile has a blockType value of 1 or 2. Passing an incomplete board.
    //           Passing them an incomplete board will result in serious errors.
    //-------------------------------------------------------------------------------------------------------

    //Checks all rules to see if game is correctly completed
    //returns a string array of size 2 that contains
    //[0] is either 'true' or 'false'
    //[1] is the error message or success message to display
    private static String[] checkForCompletion(tile[][] gameBoard){
        String[] results = new String[2];

        if(checkForThrees(gameBoard) == false){
            results[0] = "false";
            results[1] = "There cannot three red or blue in sequence";
            return results;
        }

        if(checkEqualTileNumbers(gameBoard) == false){
            results[0] = "false";
            results[1] = "There cannot be unequal red and blues in a row or column";
            return results;
        }


        if(checkForEqualRows(gameBoard) == false || checkForEqualColumns(gameBoard) == false){
            results[0] = "false";
            results[1] = "There cannot be duplicate rows or columns";
            return results;
        }

        results[0] = "true";
        results[1] = "Succes!";
        return results;
    }


    //Compares every row to every other row and returns a boolean for whether or not there are duplicate rows
    //True indicates that the board PASSES this test, showing that there are no equal rows
    //TRUE = PASSED; Board is good to go
    private static boolean checkForEqualRows(tile[][] gameBoard){
        boolean exactCopy = true;
        String[] rows;
        int[] blocks;
        rows = new String[boardSize];
        blocks = new int[boardSize];
        //create boardSize number of strings to represent each row
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                blocks[j] = gameBoard[i][j].blockType;
            }
            rows[i] = Arrays.toString(blocks);
            Log.d(TAG, "rows of concat arrays"+rows[i]);
        }
        //compare those strings to find exact copy row
        for(int x = 0; x < boardSize; x++){
            for(int y = 0; y < boardSize; y++){
                //make sure rows are not compared to themselves
                if(x != y) {
                    if (rows[x].equals(rows[y])) {
                        exactCopy = false;
                        Log.d(TAG, "" + rows[x] + " is equal to " + rows[y]);
                        break;
                    }
                }
            }
        }
        return exactCopy;
    }

    //Compares every column to every other column and returns a boolean for whether or not there are duplicate columns
    //True indicates that the board PASSES this test, showing that there are no equal columns
    //TRUE = PASSED; Board is good to go
    private static boolean checkForEqualColumns(tile[][] gameBoard){
        boolean exactCopy = true;
        String[] columns;
        int[] blocks;
        columns = new String[boardSize];
        blocks = new int[boardSize];
        //create boardSize number of strings to represent each row
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                blocks[j] = gameBoard[j][i].blockType;
            }
            columns[i] = Arrays.toString(blocks);
            Log.d(TAG, "columns of concat arrays"+columns[i]);
        }
        //compare those strings to find exact copy row
        for(int x = 0; x < boardSize; x++){
            for(int y = 0; y < boardSize; y++){
                //make sure rows are not compared to themselves
                if(x != y) {
                    if (columns[x].equals(columns[y])) {
                        exactCopy = false;
                        Log.d(TAG, "" + columns[x] + " is equal to " + columns[y]);
                        break;
                    }
                }
            }
        }
        return exactCopy;
    }

    //Checks for three consecutive tiles in a row or column
    //True indicates that the board PASSES this test, showing that there are no threes
    //TRUE = PASSED; Board is good to go
    private static boolean checkForThrees(tile[][] gameBoard){
        boolean threes = true;

        //checking rows for threes
        for(int i=0; i < boardSize; i++){
            for(int j=0; j < boardSize-2; j++){
                if(gameBoard[i][j].blockType == 1 && gameBoard[i][j+1].blockType == 1 && gameBoard[i][j+2].blockType == 1){
                    threes = false;
                    Log.d(TAG, "group of three"+gameBoard[i][j].blockType+gameBoard[i][j+1].blockType+gameBoard[i][j+2].blockType);
                }else if(gameBoard[i][j].blockType == 2 && gameBoard[i][j+1].blockType == 2 && gameBoard[i][j+2].blockType == 2){
                    threes = false;
                    Log.d(TAG, "group of three"+gameBoard[i][j].blockType+gameBoard[i][j+1].blockType+gameBoard[i][j+2].blockType);
                }
            }
        }

        //checking columns for threes
        for(int i=0; i < boardSize-2; i++){
            for(int j=0; j < boardSize; j++){
                if(gameBoard[j][i].blockType == 1 && gameBoard[j][i+1].blockType == 1 && gameBoard[j][i+2].blockType == 1){
                    Log.d(TAG, "group of three"+gameBoard[j][i].blockType+gameBoard[j][i+1].blockType+gameBoard[j][i+2].blockType);
                    threes = false;
                }else if(gameBoard[j][i].blockType == 2 && gameBoard[j][i+1].blockType == 2 && gameBoard[j][i+2].blockType == 2){
                    Log.d(TAG, "group of three"+gameBoard[j][i].blockType+gameBoard[j][i+1].blockType+gameBoard[j][i+2].blockType);
                    threes = false;
                }
            }
        }
        return threes;
    }

    //Checks that the number of tiles of type 1 and type 2 are the same in a complete row
    //True indicates that the board PASSES this test, showing that there are an equal number
    //of blockTypes 1 and 2 in each row and column
    //TRUE = PASSED; Board is good to go
    private static boolean checkEqualTileNumbers(tile[][] gameBoard){
        int tileOnes;
        int tileTwos;
        boolean tileTypesEqual = true;

        //check rows
        for(int i=0; i < boardSize; i++){
            tileOnes = 0;
            tileTwos = 0;
            for(int j = 0; j < boardSize; j++){
                if(gameBoard[i][j].blockType == 1){ tileOnes++; }
                if(gameBoard[i][j].blockType == 2){ tileTwos++; }
            }
            if(tileOnes != tileTwos){
                tileTypesEqual = false;
            }
        }

        //check columns
        for(int i=0; i < boardSize; i++){
            tileOnes = 0;
            tileTwos = 0;
            for(int j = 0; j < boardSize; j++){
                if(gameBoard[j][i].blockType == 1){ tileOnes++; }
                if(gameBoard[j][i].blockType == 2){ tileTwos++; }
            }
            if(tileOnes != tileTwos){
                tileTypesEqual = false;
            }
        }
        return tileTypesEqual;
    }
}
