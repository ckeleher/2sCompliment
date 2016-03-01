public class tile{

	//Data fields
	public int blockType;

	//Constructor
	public tile(int givenBlockType){
		blockType = 1;
	}

	//Change block state
	//1 = empty
	//2 = red
	//3 = blue
	public void cycleTileState(int blockType){
		if(blockType == 1) blockType = 2;
		if(blockType == 2) blocktype = 3;
		if(blockType == 3) blocktype = 1;
		}
	}
}

