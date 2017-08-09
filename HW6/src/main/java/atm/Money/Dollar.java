package atm.Money;

/**
 * Created by anton on 28.07.17.
 */
public class Dollar extends Money {
    private final String name = " Dollar";
    private int count;

    public Dollar(){
        count = 0;
    }

    public Dollar(int count){
        this.count = count;
    }

    public String getName(){
        return name;
    }
    public int getCount(){
        return count;
    }
}
