package atm.Strategy;

import atm.Cell;
import atm.Money.Money;

import java.util.ArrayList;

/**
 * Created by anton on 31.07.17.
 */
@FunctionalInterface
public interface StrategyAlgorithm {
    boolean giveMoney(Money m, ArrayList<Cell> cells);
    //Я хочу use лямбду и хочу чтобы в алгоритме можно было выбирать тип денег!
}
