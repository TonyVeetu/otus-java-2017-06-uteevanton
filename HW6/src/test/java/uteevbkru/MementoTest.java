package uteevbkru;

import atm.Atm;
import atm.Cell;
import atm.Money.Currency;
import atm.Money.Dollar;
import atm.Money.Euro;
import atm.Money.Ruble;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by anton on 11.08.17.
 */
public class MementoTest {
    @Test
    public void mementoTest() {
        System.out.println("\t\t" + "mementoTest: ");
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

        atm.getCash(new Ruble(200));
        atm.getCash(new Dollar(50));

        atm.recovery(false);

        Assert.assertTrue(410 == atm.getResidue(new Ruble()));
        Assert.assertTrue(500 == atm.getResidue(new Dollar()));
        Assert.assertTrue(0 == atm.getResidue(new Euro()));

    }
}
