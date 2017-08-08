package atm.Strategy;

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

    public boolean getMoney(int count){
        return algorithm.giveMoney(count);
    }
}
