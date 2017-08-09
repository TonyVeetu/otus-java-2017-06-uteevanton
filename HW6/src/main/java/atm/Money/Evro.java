package atm.Money;

/**
 * Created by anton on 28.07.17.
 */
public class Evro extends Money {
    private final String name = " Evro";
    private int count;

    public Evro(){
        count = 0;
    }

    public String getName(){
        return name;
    }
    public int getCount(){
        return count;
    }
}
