package atm.Strategy;

import atm.Cell;
import atm.Money.Money;

import java.util.ArrayList;


public interface StrategyAlgorithm {
    String getName();
    boolean giveMoney(Money m, ArrayList<Cell> cells);
}
