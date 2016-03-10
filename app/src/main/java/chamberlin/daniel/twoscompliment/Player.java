package chamberlin.daniel.twoscompliment;

/**
 * Created by tyler on 3/1/16.
 */
public class Player implements Comparable<Player>{
    private String name;
    private int time;

    public Player(){}

    public Player(String name, int time){
        this.name = name;
        this.time = time;
    }
    public String getName(){
        return name;
    }
    public int getTime(){
        return time;
    }
    @Override
    public String toString(){
        return name + "\nTime: "+time;
    }
    public int compareTo(Player pcomp){
        int compareP = pcomp.getTime();
        return this.time-compareP;
    }
}
