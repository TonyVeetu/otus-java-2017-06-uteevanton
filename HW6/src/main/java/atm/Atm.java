package atm;

import atm.Money.*;
import atm.Strategy.GreedyAlgorithm;
import atm.Strategy.OptimaAlgorithm;
import atm.Strategy.WithdrawAlgorithm;

import java.util.*;

/**
 * Created by anton on 27.07.17.
 */
public class Atm {
    private static final int COUNT_OF_CELLS_IN_ATM = 5;
    private ArrayList<Cell> cellsAtm = new ArrayList<>(COUNT_OF_CELLS_IN_ATM);
    private WithdrawAlgorithm algorithm;
    private boolean isEmptyCell;
    private final String pass = "SuperJavaInOtus";
    private Memento2 memento;
    private final String uniqueID = UUID.randomUUID().toString();

    public Atm(ArrayList<Cell> cells){
        cellsAtm.addAll(cells);
        algorithm = new WithdrawAlgorithm(new GreedyAlgorithm());
        isEmptyCell = false;
        memento = new Memento2(this.cellsAtm, algorithm, isEmptyCell);// Удобно что это внутренний класс!
    }

    public String getUniqueID(){
        return uniqueID;
    }

    public int getResidue(Money money){
        int Symm = 0;
        for(int j = 0; j < cellsAtm.size(); j++){
            Symm += cellsAtm.get(j).getCash(money);
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
            System.out.println("Atm "+ getUniqueID() + " don't have enough space. You want to getCurrencyOfCell: " + money.getAmountOfMoney());
            return false;
        }

        int Symm = money.getAmountOfMoney();
        //int i = 4;
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
     */
    public boolean giveCash( Money money){
        if( !checkBalance(money)){
            return false;
        }
        System.out.println("__****__I want to GIVE " + money.getAmountOfMoney() + "currency:" + money.getCurrency() + "!__***___");
        // Если ячейка пустая, то нужно переключить алгоритм выдачи денег!
        //TODO think!!//Правильно ли реализована логика переключения алгоритма?????????
        checkEmptyCell();
        algorithm.giveMoney(money, cellsAtm);
        return true;
    }

    // Проверяет достаточно ли денег в банкомате!
    private boolean checkBalance(Money m){
        if(m.getAmountOfMoney() < 0) {
            System.out.println("Error!!! It's impossible: " + m.getAmountOfMoney() + " " + m.getCurrency());
            return false;
        }
        int Symm = 0;
        for(int i = 0; i < cellsAtm.size(); i++){
            Symm += cellsAtm.get(i).getCash(m);
        }
        if(m.getAmountOfMoney() > Symm) {
            System.out.println("********__Error!!! You want to much money: " + m.getAmountOfMoney() + " currency:" + m.getCurrency() + "___*****");
            return false;
        }
        else
            return true;
    }


    public void printState(){
        System.out.println((char) 27 + "[33mState atm and atm's cells: " + (char)27 + "[0m");
        System.out.println("\t" + "All money in ATM in rub = " + this.getResidue(new Ruble()));
        System.out.println("\t" + "All money in ATM in $ = " + this.getResidue(new Dollar()));
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
                isEmptyCell = true;//Будет true только если есть пустой Cell
            }
        }
        if(isEmptyCell)//Если есть пустой Cell, то нужно переключить алгоритм!!
            algorithm.setAlgorithm(new OptimaAlgorithm());
        else
            algorithm.setAlgorithm(new GreedyAlgorithm());
    }


    //Не нравиться то, что любой может вызвать recovery!!!!
    //Что делать?
    //Хотелось бы какую-то защиту от злоумыщленников! Ничего кроме простого пароля на ум не приходит!!
    //Или не париться??
    //TODO что делать??
    public void recovery(String pass){
        if(pass.equals(this.pass)){
            System.out.println("Recovery is going for atm: " + getUniqueID());
            cellsAtm.clear();
            //memento.printStateMemento();
            cellsAtm.addAll(memento.getSavedCells());
            algorithm = memento.getSavedAlgorithm();
            isEmptyCell = memento.getSavedIsEmptyCell();
            //TODO где здесь должен быть caretaker    ????
            //Или метод этот можно засчитать за caretaker?
            printState();
        }
        else
            System.out.println("Can not recovery atm " + getUniqueID());
    }

}
