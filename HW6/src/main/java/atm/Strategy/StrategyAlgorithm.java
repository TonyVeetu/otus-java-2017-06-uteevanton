package atm.Strategy;

import atm.Money.Money;

/**
 * Created by anton on 31.07.17.
 */
@FunctionalInterface
public interface StrategyAlgorithm {
    //boolean giveMoneyInRub(int count);
    boolean giveMoney(Money m);
    //Я хочу use лямбду и хочу чтобы в алгоритме можно было выбирать тип денег!
}
