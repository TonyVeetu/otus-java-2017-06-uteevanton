package atm.Strategy;

import atm.Money.Money;

/**
 * Created by anton on 31.07.17.
 */
public class WithdrawAlgorithm {
    private StrategyAlgorithm algorithm;

    public WithdrawAlgorithm(StrategyAlgorithm algorithm){
        this.algorithm = algorithm;
    }

    public void setAlgorithm(StrategyAlgorithm algorithm){
        this.algorithm = algorithm;
    }

//    public boolean getMoneyInRub(int count){
//        return algorithm.giveMoneyInRub(count);
//    }

    public boolean getMoney(Money m){
        return algorithm.giveMoney(m);
    }
}
