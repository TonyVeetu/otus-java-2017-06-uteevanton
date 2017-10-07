package atm.Money;

/**
 * Created by anton on 28.07.17.
 */
public class Euro extends Money {
    private int amountOfMoney;
    private Currency evro = Currency.EURO;

    public Euro(){}
    public Euro(int amountOfMoney){
        this.amountOfMoney = amountOfMoney;
    }

    public Currency getCurrency(){
        return evro;
    }
    public int getAmountOfMoney(){
        return amountOfMoney;
    }
}
