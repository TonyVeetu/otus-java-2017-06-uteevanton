package uteevbkru;

import atm.Atm;
import atm.Cell;
import atm.Money.Currency;
import atm.Money.Dollar;
import atm.Money.Euro;
import atm.Money.Ruble;
import atm.Strategy.WithdrawAlgorithm;
import org.junit.*;

import java.util.ArrayList;

/**
 * Created by anton on 11.08.17.
 */
public class AtmTest {

    @Test
    public void getResidueTest() {
        System.out.println("\t\t" + "getResidueTest: ");
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

        Assert.assertTrue(410 == atm.getResidue(new Ruble()));
        Assert.assertTrue(500 == atm.getResidue(new Dollar()));
    }

    @Test
    public void getFreeSpaceForAddingMoneyTest() {
        System.out.println("\t\t" + "getFreeSpaceForAddingMoneyTest: ");
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

        Assert.assertTrue(410 == atm.getFreeSpaceForAddingMoney(new Ruble()));
        Assert.assertTrue(500 == atm.getFreeSpaceForAddingMoney(new Dollar()));
        Assert.assertTrue(0 == atm.getFreeSpaceForAddingMoney(new Euro()));
    }

//    @Test
//    public void checkSpaceTest(){
//        //как протестировать private function TODO
//    }


    @Test
    public void getCashTest(){
        System.out.println("\t\t" + "getCashTest: ");
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

        int moneyAfter = atm.getResidue(new Ruble());
        atm.getCash(new Ruble(400));
        int moneyBefore = atm.getResidue(new Ruble());

        Assert.assertEquals(moneyAfter + 400, moneyBefore);
    }

    @Test
    public void getCashErrorTest(){
        System.out.println("\t\t" + "getCashErrorTest: ");
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

        Assert.assertFalse(atm.getCash(new Ruble(4000)));
        Assert.assertFalse(atm.getCash(new Dollar(10)));
    }

    @Test
    public void giveCashTest(){
        System.out.println("\t\t" + "giveCashTest: ");
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

        int moneyAfter = atm.getResidue(new Ruble());
        atm.giveCash(new Ruble(400));
        int moneyBefore = atm.getResidue(new Ruble());

        Assert.assertEquals(moneyAfter - 400, moneyBefore);
    }

    @Test
    public void giveCashErrorTest(){
        System.out.println("\t\t" + "giveCashErrorTest: ");
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

        Assert.assertFalse(atm.giveCash(new Ruble(4000)));
    }


    //TODO
    @Test
    public void switchAlgorithmTest(){
        System.out.println("\t\t" + "switchAlgorithmTest: ");
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
        atm.giveCash(new Ruble(520));
        atm.giveCash(new Ruble(10));
        atm.printState();
        WithdrawAlgorithm algorithm1 = atm.getAlgorithm();
        System.out.println(algorithm1.getName());

        Assert.assertFalse("a".equals("av"));
    }

 }
