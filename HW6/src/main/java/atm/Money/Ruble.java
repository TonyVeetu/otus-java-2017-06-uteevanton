package atm.Money;

/**
 * Created by anton on 28.07.17.
 */
public class Ruble extends Money {
    private Currency ruble = Currency.RUBLE;
    private int amountOfMoney = 0;

    public Ruble(){}

    public Ruble(int amountOfMoney){
        this.amountOfMoney = amountOfMoney;
    }

    public Currency getCurrency(){
        return ruble;
    }

    public int getAmountOfMoney(){
        return amountOfMoney;
    }
}
