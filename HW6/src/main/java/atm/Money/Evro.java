package atm.Money;

/**
 * Created by anton on 28.07.17.
 */
public class Evro extends Money {
    private int amountOfMoney;
    private Currency evro = Currency.EVRO;

    public Evro(){}
    public Evro(int amountOfMoney){
        this.amountOfMoney = amountOfMoney;
    }

    public Currency getCurrency(){
        return evro;
    }
    public int getAmountOfMoney(){
        return amountOfMoney;
    }
}
