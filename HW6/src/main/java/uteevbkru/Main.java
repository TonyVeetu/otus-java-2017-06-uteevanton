package uteevbkru;


import uteevbkru.Money.Dollar;
import uteevbkru.Money.Ruble;

import java.util.ArrayList;

/**
 * Created by tully.
 */
public class Main {
    public static void main(String[] args) {


        Cell cell1 = new Cell(1, 10, new Ruble());
        Cell cell2 = new Cell(5, 10, new Ruble());
        Cell cell3 = new Cell(10, 10, new Ruble());
        Cell cell4 = new Cell(25, 20, new Ruble());

        //Cell cell5 = new Cell(50, 10, new Ruble());
        Cell cell5 = new Cell(50, 10, new Dollar());

        ArrayList<Cell> cells = new ArrayList<>(5);

        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);
        cells.add(cell4);
        cells.add(cell5);

        Atm atm = new Atm(cells);
        atm.printState();

        int count1 = 545;
        System.out.println("__****__I want to GIVE "+ count1 +" ruble!__***___");
        atm.giveCashInRub(count1);
        atm.printState();

        int count4 = 100;
        System.out.println("__****__I want to GIVE " + count4 + " ruble!__***___");
        atm.giveCashInRub(count4);
        atm.printState();

        int count2 = 548;
        System.out.println("__****__I want to GET " + count2 + " ruble!__***___");
        atm.getCashInRub(count2);
        atm.printState();

        int count3 = 250;
        System.out.println("__****__I want to GIVE " + count3 + " ruble!__***___");
        atm.giveCashInRub(count3);
        atm.printState();
    }

}

