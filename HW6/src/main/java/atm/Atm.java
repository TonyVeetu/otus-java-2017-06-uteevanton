package atm;

import atm.Money.*;
import atm.Strategy.GreedyAlgorithm;
import atm.Strategy.OptimaAlgorithm;
import atm.Strategy.WithdrawAlgorithm;
import atm.memento.Memento;

import java.util.*;


public class Atm {
    public static final int COUNT_OF_CELLS_IN_ATM = 5;

    private ArrayList<Cell> cellsAtm = new ArrayList<>(COUNT_OF_CELLS_IN_ATM);
    private WithdrawAlgorithm algorithm;
    private boolean isEmptyCell;
    private Memento memento;
    private final String uniqueID = UUID.randomUUID().toString();

    public Atm(ArrayList<Cell> cells){
        cellsAtm.addAll(cells);
        algorithm = new WithdrawAlgorithm(new GreedyAlgorithm());
        isEmptyCell = false;
        memento = new Memento(this.cellsAtm, algorithm, isEmptyCell);// Удобно что это внутренний класс!
    }

    public String getUniqueID(){
        return uniqueID;
    }

    public WithdrawAlgorithm getAlgorithm(){
        return algorithm;
    }
    /**
     * Получить остаток!
     * @param money
     * @return количество денег
     */
    public int getResidue(Money money){
        int Symm = 0;
        for(Cell cell : cellsAtm){
            Symm += cell.getCash(money);
        }
        return Symm;
    }

    public int getFreeSpaceForAddingMoney(Money money){
        int Symm = 0;
        for(Cell cell : cellsAtm){
            Symm += cell.getFreeSpaceForMoney(money);
        }
        return Symm;
    }

    /**
     * Пользователь хочет дать деньги вызывая этот метод!!
     * И ячейка может быть переполненой isFull
     */
    public boolean getCash(Money money){
        if(money.getAmountOfMoney() <= 0)
            return false;

        if(!checkFreePlaceInCells(money)) {
            System.out.println("Atm "+ getUniqueID() + " don't have enough space. " +
                    "You want to get: " + money.getAmountOfMoney() + " "+ money.getCurrency());
            return false;
        }

        int Symm = money.getAmountOfMoney();
        int i = cellsAtm.size() - 1;
        while(Symm != 0){
            if(cellsAtm.get(i).getCurrencyOfCell() == money.getCurrency()){
                if(Symm >= cellsAtm.get(i).getNominal() && !cellsAtm.get(i).getIsFull()){
                    if(cellsAtm.get(i).getNote()){
                        Symm -= cellsAtm.get(i).getNominal();
                    }
                }
                if(Symm >= cellsAtm.get(i).getNominal() && !cellsAtm.get(i).getIsFull()){
                    i++;
                }
            }
            if(i == 0){
                i = cellsAtm.size() - 1;
            }
            i--;
        }
        return true;
    }

    /**Проверяет достаточно ли места в банкомате, чтобы положить в него деньги!
     *
     * @param money
     * @return
     */
    private boolean checkFreePlaceInCells(Money money){
        int maxSym = 0;
        for(Cell cell : cellsAtm) {
            if(cell.getCurrencyOfCell() == money.getCurrency()){
                maxSym += (Cell.MAX_COUNT_NOTE - cell.getCountOfNote())*cell.getNominal();
            }
        }
        if(maxSym < money.getAmountOfMoney())
            return false;
        return true;
    }


    /**
     * Пользователь получает деньги вызывая этот метод!!
     * Если какая-то ячейка пустая, то нужно переключить алгоритм выдачи денег!
     */
    public boolean giveCash(Money money){
        if( !checkBalance(money)){
            return false;
        }
        System.out.println("__****__I want to GIVE " + money.getAmountOfMoney() + " " + money.getCurrency() + "!__***___");

        checkEmptyCell();
        switchAlgorithm();
        algorithm.giveMoney(money, cellsAtm);
        return true;
    }

    /**Проверяет достаточно ли денег в банкомате!
     *
     * @param money
     * @return
     */
    private boolean checkBalance(Money money){
        if(money.getAmountOfMoney() < 0) {
            System.out.println("Error!!! It's impossible: " + money.getAmountOfMoney() + " " + money.getCurrency());
            return false;
        }
        int Symm = 0;
        for(int i = 0; i < cellsAtm.size(); i++){
            Symm += cellsAtm.get(i).getCash(money);
        }
        if(money.getAmountOfMoney() > Symm) {
            System.out.println("********__Error!!! You want to much money: " + money.getAmountOfMoney() + " " + money.getCurrency() + "___*****");
            return false;
        }
        else
            return true;
    }

    public void printState(){
        System.out.println((char) 27 + "[33mAtm and atm's cells state : " + (char)27 + "[0m");
        System.out.println("\t" + "Amount of money in ATM in ruble = " + this.getResidue(new Ruble())
                                +", Maximum money for adding in Atm = " + this.getFreeSpaceForAddingMoney( new Ruble()));
        System.out.println("\t" + "Amount of money in ATM in dollar = " + this.getResidue(new Dollar())
                                +", Maximum money for adding in Atm = " + this.getFreeSpaceForAddingMoney( new Dollar()));
        System.out.println("\t" + "Amount of money in ATM in euro = " + this.getResidue(new Euro())
                                +", Maximum money for adding in Atm = " + this.getFreeSpaceForAddingMoney( new Euro()));
        for(int i = 0; i < cellsAtm.size(); i++){
            int[] mas = cellsAtm.get(i).getStateCell();
            System.out.print("\t" + "Cell_"+ i + " - parOfNode: " + mas[0] + ", count: " + mas[1] + ", currency - " + cellsAtm.get(i).getCurrencyOfCell() + ", isEmpty: " + mas[2] + ", isFull: " + mas[3]);
            System.out.println();
        }
    }

    private void checkEmptyCell(){
        isEmptyCell = false;// На случай если алгоритм нужно переключить еще раз!!!
        for(int i = 0; i < cellsAtm.size(); i++) {
            if (cellsAtm.get(i).getIsEmpty()) {
                isEmptyCell = true;
                System.out.println("EmptyCell");
            }
        }
    }

    private void switchAlgorithm(){
        if(isEmptyCell) {
            algorithm.setAlgorithm(new OptimaAlgorithm());
            System.out.println("OPTIMA");
        }
        else
            algorithm.setAlgorithm(new GreedyAlgorithm());
    }

    public void recovery(boolean printState){
        cellsAtm.clear();
        cellsAtm.addAll(memento.getSavedCells());
        algorithm = memento.getSavedAlgorithm();
        isEmptyCell = memento.getSavedIsEmptyCell();
        if(printState) {
            System.out.println("Recovery is going for atm: " + getUniqueID());
            printState();
        }
    }

}
