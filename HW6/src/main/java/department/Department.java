package department;

import atm.Atm;
import atm.Cell;
import atm.Money.Dollar;
import atm.Money.Ruble;

import java.util.ArrayList;

/**
 * Created by anton on 06.08.17.
 */
public class Department {

    private ArrayList<Atm> atms;

    public Department(ArrayList<Atm> atms){
        this.atms = atms;
    }

    public Department(){
        atms = new ArrayList<>();
    }

    public void addAtm(Atm a){
        atms.add(a);
    }

    public Atm getAtm(int a){
        if(a <= atms.size())
            return atms.get(a);
        else {
            new IndexOutOfBoundsException();
            return null;
        }
    }


    public int countAllMoneyInRub(){
        int Sym = 0;
        for(int i = 0; i < atms.size(); i++){
            Sym += atms.get(i).getResidue(Atm.typeCash[0]);
        }
        return Sym;
    }

    public void init(){
        Cell cell1 = new Cell(1, 10, new Ruble());
        Cell cell2 = new Cell(5, 10, new Ruble());
        Cell cell3 = new Cell(10, 10, new Ruble());
        Cell cell4 = new Cell(25, 10, new Ruble());
        Cell cell5 = new Cell(50, 10, new Ruble());

        ArrayList<Cell> cells = new ArrayList<>(5);
        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);
        cells.add(cell4);
        cells.add(cell5);
        Atm atm1 = new Atm(cells);

        this.addAtm(atm1);

        Cell cell11 = new Cell(5, 10, new Ruble());
        Cell cell12 = new Cell(10, 10, new Ruble());
        Cell cell13 = new Cell(50, 10, new Ruble());
        Cell cell14 = new Cell(100, 10, new Ruble());
        Cell cell15 = new Cell(1000, 10, new Ruble());

        ArrayList<Cell> cells2 = new ArrayList<>(5);
        cells2.add(cell11);
        cells2.add(cell12);
        cells2.add(cell13);
        cells2.add(cell14);
        cells2.add(cell15);

        Atm atm2 = new Atm(cells2);
        this.addAtm(atm2);
    }

    public void printState(){
        for(int i = 0; i < atms.size(); i++) {
            atms.get(i).printState();
        }
    }
}
