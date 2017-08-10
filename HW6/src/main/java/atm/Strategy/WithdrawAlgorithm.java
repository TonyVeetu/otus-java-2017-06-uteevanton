package atm.Strategy;

import atm.Cell;
import atm.Money.Money;

import java.util.ArrayList;

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

    public boolean giveMoney(Money m, ArrayList<Cell> cells){
        return algorithm.giveMoney(m, cells);
    }
}
