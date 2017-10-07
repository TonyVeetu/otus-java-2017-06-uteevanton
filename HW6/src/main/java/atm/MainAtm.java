package atm;

import atm.Money.*;
import atm.Strategy.WithdrawAlgorithm;

import java.util.ArrayList;

public class MainAtm {
    public static void main(String[] args) {

        Cell cell1 = new Cell(1, 10, Currency.RUBLE);
        Cell cell2 = new Cell(5, 10, Currency.RUBLE);
        Cell cell3 = new Cell(10, 10, Currency.RUBLE);
        Cell cell4 = new Cell(25, 10, Currency.RUBLE);
        Cell cell5 = new Cell(50, 10, Currency.RUBLE);

        ArrayList<Cell> cells = new ArrayList<>(5);

        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);
        cells.add(cell4);
        cells.add(cell5);

        Atm atm = new Atm(cells);
        atm.printState();

        WithdrawAlgorithm algorithm = atm.getAlgorithm();
        System.out.println(algorithm.getName());
        atm.giveCash(new Ruble(500));
        atm.giveCash(new Ruble(50));
        atm.printState();
        WithdrawAlgorithm algorithm1 = atm.getAlgorithm();
        System.out.println(algorithm1.getName());

    }

}

