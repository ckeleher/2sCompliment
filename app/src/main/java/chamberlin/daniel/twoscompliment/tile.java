package chamberlin.daniel.twoscompliment;

import android.util.Log;

/**
 * Created by Daniel on 3/1/2016.
 */
public class tile {

    //Data fields
    public int blockType;

    public int xLoc;
    public int yLoc;

    public boolean locked;

    private static final String TAG = boardActivity.class.getSimpleName();

    //Constructor
    public tile(int givenBlockType, int givenX, int givenY){
        blockType = givenBlockType;
        xLoc = givenX;
        yLoc = givenY;
        locked = true;
    }

    //Change block state
    //1 = red
    //2 = blue
    //3 = empty
    public void cycleTileState(int Start){
        Log.d(TAG, "cyclingblockType from "+blockType);
        if(Start == 1){ this.blockType = 2; Log.d(TAG, "Start == 1"); }
        if(Start == 2){ this.blockType = 3; Log.d(TAG, "Start == 2"); }
        if(Start == 3){ this.blockType = 1; Log.d(TAG, "Start == 3"); }
        Log.d(TAG, "to "+blockType);
    }
}

