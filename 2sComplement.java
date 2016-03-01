
public class 2sComplement{
	
	//Global variables
	public int boardSize; //Can be 4, 6, 8, 10, etc. Anything more than 12-14 or so will probably not fit elegantly on-screen.

	//Main
	public static void main(String[] args){
		int[][] gameBoard = new int[boardSize][boardSize];
		gameBoard = loadInitialGameState(boardSize, gameBoard);

	}

	//Loads the initial state of the game board
	//Currently this function is basically just a shell to call another function
	//Decided to keep it here in case I ever want to include alternate game modes
	//which don't generate the board in the same way that generateBoardState() does.
	private int[][] loadInitialGameState(int boardSize, int[][] gameBoard){
		return generateBoardState(boardSize, gameBoard);
	}

	//Randomly generates a starting board state following the game rules
	private int[][] generateBoardState(int boardSize, int[][] gameBoard){
		Random r = new Random();

		//Decide how many cells to leave filled
		int minFill;
		int maxFill;
		if(boardsize == 4){minFill = 4; maxFill = 5;}						//There's no formula to these numbers, they just seem to be good bounds for the given board sizes
		if(boardsize == 6){minFill = 8; maxFill = 12;}
		if(boardsize == 8){minFill = 15; maxFill = 20;}
		if(boardsize == 10){minFill = 21; maxFill = 30;}
		fillAmount = r.nextInt(maxFill - minFill + 1) + minFill;

		
		//Fill board with empty tiles
		for(int i=0; i < boardSize; i++){
			for(int j=0, j < boardSize, j++){
				gameBoard[i][j] = new tile;				//need to give arguments for constructor?
			}
		}

		//Fill in the game board
		//i = column, j = row
		int[] numOneRow = new int[boardSize];
		int[] numTwoRow = new int[boardSize];
		for(int i=0; i < boardSize; i++){
			int numOne = 0;
			int numTwo = 0;
			for(int j=0, j < boardSize, j++){
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



		
	}

	//-------------------------------------------------------------------------------------------------------
	//These functions are used for the final check on whether or not the user-completed board is valid.
	//-------------------------------------------------------------------------------------------------------


	//Compares every row to every other row and returns a boolean for whether or not there are duplicate rows
	private bool checkForEqualRows(){

	}

	//Compares every column to every other column and returns a boolean for whether or not there are duplicate columns
	private bool checkForEqualColumns(){

	}

	//Checks for three consecutive tiles in a row or column
	private bool checkForThrees(int[][] gameBoard){

	}

	//Checks that the number of tiles of type 1 and type 2 are the same in a complete row
	private bool checkEqualTileNumbers(){

	}
}