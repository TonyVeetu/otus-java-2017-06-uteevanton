package atm.Money;

/**
 * Created by anton on 28.07.17.
 */
public class Dollar extends Money {
    private Currency dollar = Currency.DOLLAR;
    private int amountOfMoney;

    public Dollar(){}

    public Dollar(int amountOfMoney){
        this.amountOfMoney = amountOfMoney;
    }

    public Currency getCurrency(){
        return dollar;
    }
    public int getAmountOfMoney(){
        return amountOfMoney;
    }
}
