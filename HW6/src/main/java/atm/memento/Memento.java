package atm.memento;

import atm.Cell;
import atm.Strategy.GreedyAlgorithm;
import atm.Strategy.WithdrawAlgorithm;

import java.util.ArrayList;

public class Memento {
    private ArrayList<Cell> saveCells = new ArrayList<>();
    private WithdrawAlgorithm saveAlgorithm = new WithdrawAlgorithm(new GreedyAlgorithm());
    private boolean saveIsEmptyCell = false;

    public Memento(ArrayList<Cell> cells, WithdrawAlgorithm algorithm, boolean isEmptyCell){
        // Чтобы избежать копирования ссылки!!
        for(Cell cell : cells) {
            saveCells.add(new Cell(cell));
        }
        saveAlgorithm = algorithm;
        saveIsEmptyCell = isEmptyCell;
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
        System.out.println((char) 27 + "[36mState Memento" + (char)27 + "[0m");
        for(int i = 0; i < saveCells.size(); i++){
            int[] mas = saveCells.get(i).getStateCell();
            System.out.print("\t" + "Cell_"+ i + " - parOfNode: " + mas[0]
                    + ", count: " + mas[1]
                    + ", currencyOfCell - " + saveCells.get(i).getCurrencyOfCell()
                    + ", isEmpty: " + mas[2]
                    + ", isFull: " + mas[3]);
            System.out.println();
        }
        System.out.println("\t" + "Algorithm: " + saveAlgorithm.getName());
    }
}
