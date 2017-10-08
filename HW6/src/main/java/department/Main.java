package department;

import atm.Atm;
import atm.Cell;
import atm.Money.Currency;
import atm.Money.Ruble;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 06.08.17.
 */
public class Main {
    public static void main(String []args) {


        Department dep = new Department(creationArrayListOfAtms());

        dep.printState();
        System.out.println("Amount of money in all atms : " + dep.countAllMoneyInRub());

        dep.giveCash(new Ruble(450), 0);
        dep.giveCash(new Ruble(4500), 1);
        System.out.println("Amount of money in all atms : " + dep.countAllMoneyInRub());
        dep.printState();

        dep.recoveryAtms(false);
        dep.printState();
    }

    public static ArrayList<Atm> creationArrayListOfAtms(){

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

        Atm atm1 = new Atm(cells);


        Cell cell11 = new Cell(5, 10, Currency.RUBLE);
        Cell cell12 = new Cell(10, 10, Currency.RUBLE);
        Cell cell13 = new Cell(50, 10, Currency.RUBLE);
        Cell cell14 = new Cell(100, 10, Currency.RUBLE);
        Cell cell15 = new Cell(1000, 10, Currency.RUBLE);

        ArrayList<Cell> cells2 = new ArrayList<>(5);
        cells2.add(cell11);
        cells2.add(cell12);
        cells2.add(cell13);
        cells2.add(cell14);
        cells2.add(cell15);

        Atm atm2 = new Atm(cells2);

        ArrayList<Atm> atms = new ArrayList<>();
        atms.add(atm1);
        atms.add(atm2);
        return  atms;
    }
}
