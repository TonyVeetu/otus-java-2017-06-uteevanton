package atm;

import atm.Money.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Cell cell1 = new Cell(1, 10, Currency.RUBLE);
        Cell cell2 = new Cell(5, 10, Currency.RUBLE);
        Cell cell3 = new Cell(10, 10, Currency.RUBLE);
        Cell cell4 = new Cell(25, 10, Currency.RUBLE);
        Cell cell5 = new Cell(50, 10, Currency.DOLLAR);

        ArrayList<Cell> cells = new ArrayList<>(5);

        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);
        cells.add(cell4);
        cells.add(cell5);

        Atm atm = new Atm(cells);
        atm.printState();

//        int count1 = 545;
//        System.out.println("__****__I want to GIVE "+ count1 +" ruble!__***___");
//        atm.giveCash(new Ruble(545));
//        atm.printState();
//
//        int count4 = 100;
//        System.out.println("__****__I want to GIVE " + count4 + " ruble!__***___");
//        atm.giveCash(new Ruble(count4));
//        atm.printState();

        int count2 = 10;
        System.out.println("__****__I want to GET " + count2 + " ruble!__***___");
        atm.getCash(new Ruble(count2));
        atm.printState();

//        int count3 = 250;
//        System.out.println("__****__I want to GIVE " + count3 + " ruble!__***___");
//        atm.giveCash(new Ruble(count3));
//        atm.printState();
    }

}

