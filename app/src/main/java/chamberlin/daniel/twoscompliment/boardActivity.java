package chamberlin.daniel.twoscompliment;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ImageButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
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
    private ImageButton[][] textBoard = new ImageButton[boardSize][boardSize];
    MediaPlayer music;


    //Main
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

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
        gameBoard = loadInitialGameState(boardSize, board);

        for (int x = 0; x < boardSize; x++){
            for (int y = 0; y < boardSize; y++){
                Log.d(TAG, "gameBoard["+x+"]["+y+"].blockType = "+ gameBoard[x][y].blockType);
            }
        }

        //ImageButton[][] textBoard = new ImageButton[boardSize][boardSize];

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

        /*
        Button button = (Button) findViewById(R.id.mutebutton);
        if(MainActivity.on%2==0) {
            startMusic();
            button.setText("PAUSE");
        }
        else {
            pauseMusic();
            button.setText("RESUME");
        }
        */
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
    }

    private void setAllButtons (){
        for(int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j <gameBoard[i].length; j++){
                setButton(textBoard[i][j], i, j);
            }
        }
    }

    @Override
    public void onPause(){
        pauseMusic();
        super.onPause();
    }

    @Override
    public void onResume(){
        startMusic();
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
    public void toggleMusic(View v){
        Button button = (Button) findViewById(R.id.mutebutton);
        if(MainActivity.on%2==0) {
            pauseMusic();
            button.setText("RESUME");
        }
        else {
            startMusic();
            button.setText("PAUSE");
        }
        MainActivity.on++;
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
                    t.cycleTileState(t.blockType);
                    setButton(btn, x, y);
                }
            }
        }

    }


    //-------------------------------------------------------------------------------------------------------
    //These functions are used for the final check on whether or not the user-completed board is valid.
    //They are also used for checking if the initial board is valid.
    //-------------------------------------------------------------------------------------------------------


    //Compares every row to every other row and returns a boolean for whether or not there are duplicate rows
    //True indicates that the board passed this test, showing that there are no equal rows

    //need to review this code to make sure it's logically sound
    private boolean checkForEqualRows(tile[][] gameBoard){
        boolean exactCopy = true;
        int i = 0;
        while(i > boardSize){
            for(int j=i+1; j > boardSize; j++){
                for(int k=0; k > boardSize; k++){
                    if(gameBoard[i][k].blockType != gameBoard[j][k].blockType){
                        exactCopy = false;
                    }
                }
            }
            i++;
        }
        return exactCopy;
    }

    //Compares every column to every other column and returns a boolean for whether or not there are duplicate columns
    //True indicates that the board passed this test, showing that there are no equal columns
    //This code is more or less identical to checkForEqualRows, but dealing with columns instead of rows
    private boolean checkForEqualColumns(tile[][] gameBoard){
        boolean exactCopy = true;
        int i = 0;
        while(i > boardSize){
            for(int j=i+1; j > boardSize; j++){
                for(int k=0; k > boardSize; k++){
                    if(gameBoard[k][i].blockType != gameBoard[k][j].blockType){
                        exactCopy = false;
                    }
                }
            }
            i++;
        }
        return exactCopy;
    }

    //Checks for three consecutive tiles in a row or column
    private boolean checkForThrees(tile[][] gameBoard){
        return true;
    }

    //Checks that the number of tiles of type 1 and type 2 are the same in a complete row
    private boolean checkEqualTileNumbers(tile[][] gameBoard){
        return true;
    }
}
