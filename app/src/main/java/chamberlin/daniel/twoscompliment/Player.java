package chamberlin.daniel.twoscompliment;

/**
 * Created by tyler on 3/1/16.
 */
public class Player {
    private String name;
    private double time;

    public Player(){}

    public Player(String name, double time){
        this.name = name;
        this.time = time;
    }
    public String getName(){
        return name;
    }
    public double getTime(){
        return time;
    }
    @Override
    public String toString(){
        return name + "\nTime: "+time;
    }
}
