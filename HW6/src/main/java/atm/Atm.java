package atm;

import atm.Money.Evro;
import atm.Strategy.GreedyAlgorithm;
import atm.Money.Dollar;
import atm.Money.Money;
import atm.Money.Ruble;
import atm.Strategy.OptimaAlgorithm;
import atm.Strategy.WithdrawAlgorithm;

import java.util.*;

/**
 * Created by anton on 27.07.17.
 */
public class Atm {
    public static final Money[] typeCash = {new Ruble(), new Dollar(), new Evro()}; //TODO think должен ли я это use????

    private ArrayList<Cell> cellsAtm = new ArrayList<>(5);
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
    boolean getCash(Money m){
        // TODO есть два метода get/giveCash которые очень похожи!!
        //Вопрос, неявляется ли это антипаттерном??
        //Можно ли этого избежать??
        if(m.getCount() <= 0)
            return false;

        int Symm = m.getCount();
        int i = 4;

        while(Symm != 0){
            if( (cellsAtm.get(i).typeCash()).equals(m.getName())){
                if(Symm >= cellsAtm.get(i).getNominal() && !cellsAtm.get(i).getIsFull()){
                    if(cellsAtm.get(i).getNote()){
                        Symm -= cellsAtm.get(i).getNominal();
                        //System.out.println("get: "+cellsAtm.get(i).getNominal());
                    }
                }
                if(Symm >= cellsAtm.get(i).getNominal() && !cellsAtm.get(i).getIsFull()){
                    i++;
                }
            }
            if(i == 0){
                i = 4;
            }
            i--;
        }
        return true;
    }

    /**
     * Пользователь получает деньги вызывая этот метод!!
     */

    public boolean giveCash( Money m){
        if( !checkMoney(m)){
            return false;
        }
        System.out.println("__****__I want to GIVE " + m.getCount() + m.getName() + "!__***___");
        // Если ячейка пустая, то нужно переключить алгоритм выдачи денег!
        //TODO think!!//Правильно ли реализована логика переключения алгоритма?????????
        checkEmptyCell();
        algorithm.giveMoney(m, cellsAtm);
        return true;
    }

    private boolean checkMoney(Money m){
        if(m.getCount() < 0) {
            System.out.println("Error!!! It's impossible: " + m.getCount() + " " + m.getName());
            return false;
        }
        int Symm = 0;
        for(int i = 0; i < cellsAtm.size(); i++){
            Symm += cellsAtm.get(i).getCash(m);
        }
        if(m.getCount() > Symm) {
            System.out.println("********__Error!!! You want to much money: " + m.getCount() + " " + m.getName() + "___*****");
            return false;
        }
        else
            return true;
    }


    public void printState(){
        System.out.println((char) 27 + "[33mState atm and atm's cellsAtm: " + (char)27 + "[0m");
        System.out.println("\t" + "All money in ATM in rub = " + this.getResidue(new Ruble()));
        System.out.println("\t" + "All money in ATM in $ = " + this.getResidue(new Dollar()));
        for(int i = 0; i < cellsAtm.size(); i++){
            int[] mas = cellsAtm.get(i).getStateCell();
            System.out.print("\t" + "Cell_"+ i + " - parOfNode: " + mas[0] + ", count: " + mas[1] + ", type cash - " + cellsAtm.get(i).typeCash() + ", isEmpty: " + mas[2] + ", isFull: " + mas[3]);
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
