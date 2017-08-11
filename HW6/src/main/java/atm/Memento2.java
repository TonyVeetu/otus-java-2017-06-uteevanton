package atm;

import atm.Strategy.GreedyAlgorithm;
import atm.Strategy.StrategyAlgorithm;
import atm.Strategy.WithdrawAlgorithm;

import java.util.ArrayList;

/**
 * Created by anton on 08.08.17.
 */
public class Memento2 {
    private ArrayList<Cell> saveCells = new ArrayList<>();
    private WithdrawAlgorithm saveAlgorithm = new WithdrawAlgorithm(new GreedyAlgorithm());
    private boolean saveIsEmptyCell = false;

    Memento2(ArrayList<Cell> cells, WithdrawAlgorithm algorithm, boolean isEmptyCell){
        // Чтобы избежать копирования ссылки!!
        for(Cell cell : cells) {
            saveCells.add(new Cell(cell));
        }
        saveAlgorithm = algorithm;
        saveIsEmptyCell = isEmptyCell;
        printStateMemento();
    }

    public ArrayList<Cell> getSavedCells(){
        return saveCells;
    }

    public WithdrawAlgorithm getSavedAlgorithm(){
        return saveAlgorithm;
    }

    public boolean getSavedIsEmptyCell(){
        return saveIsEmptyCell;
    }

    public void printStateMemento(){
        System.out.println((char) 27 + "[36mState Memento2" + (char)27 + "[0m");
        for(int i = 0; i < saveCells.size(); i++){
            int[] mas = saveCells.get(i).getStateCell();
            System.out.print("\t" + "Cell_"+ i + " - parOfNode: " + mas[0] + ", count: " + mas[1] + ", type cash - " + saveCells.get(i).typeCash() + ", isEmpty: " + mas[2] + ", isFull: " + mas[3]);
            System.out.println();
        }
        System.out.println("\t" + "Algorithm: " + saveAlgorithm.getName());
    }
}
