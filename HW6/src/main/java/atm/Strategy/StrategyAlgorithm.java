package atm.Strategy;

import atm.Cell;
import atm.Money.Money;

import java.util.ArrayList;

/**
 * Created by anton on 31.07.17.
 */
public interface StrategyAlgorithm {
    String getName();
    boolean giveMoney(Money m, ArrayList<Cell> cells);
}
