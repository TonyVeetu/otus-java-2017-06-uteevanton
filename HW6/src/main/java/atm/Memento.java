package atm;

import atm.Strategy.WithdrawAlgorithm;

import java.util.ArrayList;

/**
 * Created by anton on 08.08.17.
 */
public class Memento {
    private ArrayList<Cell> saveCells;
    private WithdrawAlgorithm algorithm;
    private boolean emptyCell;

    public Memento(ArrayList<Cell> cells, WithdrawAlgorithm alg, boolean empty ){
        saveCells = cells;
        algorithm = alg;
        emptyCell = empty;
    }

    public ArrayList<Cell> getState(){
        //TODO
        return this.saveCells;
    }
}
