package atm.Money;

/**
 * Created by anton on 28.07.17.
 */
public class Ruble extends Money {
    private final String name = " rub";
    private int count;

    public Ruble(){
        count = 0;
    }

    public Ruble(int count){
        this.count = count;
    }

    public String getName(){
        return name;
    }

    public int getCount(){
        return count;
    }
}
