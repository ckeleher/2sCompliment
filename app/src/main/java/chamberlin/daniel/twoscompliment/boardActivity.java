package chamberlin.daniel.twoscompliment;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
    public static int boardSize = 4; //Can be 4, 6, 8, 10

    private tile[][] gameBoard;
    private ImageButton[][] textBoard = new ImageButton[boardSize][boardSize];


    //Main
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_board);

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

        /*for(int i = 0; i < gameBoard.length; i++){
            for(int j = 0; j <gameBoard[i].length; j++){
                setButton(textBoard[i][j], i, j);
            }
        }*/
        setAllButtons();
    }

    private void setButton(ImageButton b, int x, int y){
        tile t = gameBoard[x][y];
        if(t.blockType == 2){
            b.setBackgroundResource(R.mipmap.redtile);
        }
        if(t.blockType == 3){
            b.setBackgroundResource(R.mipmap.bluetile);
        }
        if(t.blockType == 1){
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


    protected void onResume(){

        super.onResume();
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
    //-------------------------------------------------------------------------------------------------------


    //Compares every row to every other row and returns a boolean for whether or not there are duplicate rows
    private boolean checkForEqualRows(tile[][] gameBoard){
        return true;
    }

    //Compares every column to every other column and returns a boolean for whether or not there are duplicate columns
    private boolean checkForEqualColumns(tile[][] gameBoard){
        return true;
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
