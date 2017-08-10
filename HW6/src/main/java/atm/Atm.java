package atm;

import atm.Money.Evro;
import atm.Strategy.GreedyAlgorithm;
import atm.Money.Dollar;
import atm.Money.Money;
import atm.Money.Ruble;
import atm.Strategy.OptimAlgorithm;
import atm.Strategy.WithdrawAlgorithm;

import java.util.*;
import java.util.UUID.*;

/**
 * Created by anton on 27.07.17.
 */
public class Atm {
    public static final Money[] typeCash = {new Ruble(), new Dollar(), new Evro()}; //TODO think должен ли я это use????

    private ArrayList<Cell> cells = new ArrayList<>(5);
    private ArrayList<Cell> reservCells = new ArrayList<>(5);
    private WithdrawAlgorithm algorithm;
    private boolean isEmptyCell;
    private final String pass = "SuperJavaInOtus";
    private Memento2 memento;
    private final String uniqueID = UUID.randomUUID().toString();

    public Atm(ArrayList<Cell> cells){
        //TODO sort
        this.cells = cells;
        reservCells.dd(new Cell(1, 2, new Dollar()));
        reservCells.addAll(cells);
        algorithm = new WithdrawAlgorithm(new GreedyAlgorithm(cells));
        isEmptyCell = false;

        //TODO не работает!!
        System.out.println("!!!!!!!!!!!!!!Create memento in atm " + getUniqueID());
        memento = new Memento2(cells, algorithm, isEmptyCell);// Удобно что это внутренний класс!
        //memento = new Memento2();// Удобно что это внутренний класс!
        //memento.initArray(cells);
    }

    public String getUniqueID(){
        return uniqueID;
    }

    public int getResidue(Money typeMoney){
        int Symm = 0;
        for(int j = 0; j < cells.size(); j++){
                Symm += cells.get(j).getCash(typeMoney);
        }
        return Symm;
    }

    /**
     * Пользователь хочет дать деньги вызывая этот метод!!
     * И ячейка может быть переполненой isFull
     */

    // ____П О Л У Ч Е Н И Е_____
//    boolean getCashInRub(int countCash){
//        if(countCash <= 0)
//            return false;
//
//        int Symm = countCash;
//        int i = 4;
//
//        while(Symm != 0){
//            if( (cells.get(i).typeCash()).equals(typeCash[0].getName())){
//                if(Symm >= cells.get(i).getNominal() && !cells.get(i).getIsFull()){
//                    if(cells.get(i).getNote()){
//                        Symm -= cells.get(i).getNominal();
//                        //System.out.println("get: "+cells.get(i).getNominal());
//                    }
//                }
//                if(Symm >= cells.get(i).getNominal() && !cells.get(i).getIsFull()){
//                    i++;
//                }
//            }
//            if(i == 0){
//                i = 4;
//            }
//            i--;
//        }
//        return true;
//    }

    boolean getCash(Money m){
        // TODO есть два метода get/giveCash которые очень похожи!!
        //Вопрос, неявляется ли это антипаттерном??
        //Можно ли этого избежать??
        if(m.getCount() <= 0)
            return false;

        int Symm = m.getCount();
        int i = 4;

        while(Symm != 0){
            if( (cells.get(i).typeCash()).equals(m.getName())){
                if(Symm >= cells.get(i).getNominal() && !cells.get(i).getIsFull()){
                    if(cells.get(i).getNote()){
                        Symm -= cells.get(i).getNominal();
                        //System.out.println("get: "+cells.get(i).getNominal());
                    }
                }
                if(Symm >= cells.get(i).getNominal() && !cells.get(i).getIsFull()){
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
    // ______В Ы Д А Ч А____
//    boolean giveCashInRub( int count){
//        if( !checkMoneyInRub(count)){
//            return false;
//        }
//        // Если ячейка пустая, то нужно переключить алгоритм выдачи денег!
//        //Правильно ли реализована логика переключения алгоритма?????????
//        //TODO
//        checkEmptyCell();
//        algorithm.getMoney(count);
//        return true;
//    }

    public boolean giveCash( Money m){
        if( !checkMoney(m)){
            return false;
        }
        System.out.println("__****__I want to GIVE " + m.getCount() + m.getName() + "!__***___");
        // Если ячейка пустая, то нужно переключить алгоритм выдачи денег!
        //TODO think!!//Правильно ли реализована логика переключения алгоритма?????????
        checkEmptyCell();
        algorithm.getMoney(m);
        return true;
    }


//    private boolean checkMoneyInRub(int count){
//        if(count < 0) {
//            System.out.println("Error!!! It's impossible: " + count + " rub");
//            return false;
//        }
//        int Symm = 0;
//        for(int i = 0; i < cells.size(); i++){
//            Symm += cells.get(i).getCash(typeCash[0]);
//        }
//        if(count > Symm) {
//            System.out.println("Error!!! You want to much money!");
//            return false;
//        }
//        else
//            return true;
//    }

    private boolean checkMoney(Money m){
        if(m.getCount() < 0) {
            System.out.println("Error!!! It's impossible: " + m.getCount() + " " + m.getName());
            return false;
        }
        int Symm = 0;
        for(int i = 0; i < cells.size(); i++){
            Symm += cells.get(i).getCash(m);
        }
        if(m.getCount() > Symm) {
            System.out.println("********__Error!!! You want to much money: " + m.getCount() + " " + m.getName() + "___*****");
            return false;
        }
        else
            return true;
    }


    public void printState(){
        System.out.println((char) 27 + "[33mState atm and atm's cells: " + (char)27 + "[0m");
        System.out.println("\t" + "All money in ATM in rub = " + this.getResidue(new Ruble()));
        System.out.println("\t" + "All money in ATM in $ = " + this.getResidue(new Dollar()));
        for(int i = 0; i < cells.size(); i++){
            int[] mas = cells.get(i).getStateCell();
            System.out.print("\t" + "Cell_"+ i + " - parOfNode: " + mas[0] + ", count: " + mas[1] + ", type cash - " + cells.get(i).typeCash() + ", isEmpty: " + mas[2] + ", isFull: " + mas[3]);
            System.out.println();
        }
    }

    private void checkEmptyCell(){
        isEmptyCell = false;// На случай если алгоритм нужно переключить еще раз!!!
        for(int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getIsEmpty()) {
                isEmptyCell = true;//Будет true только если есть пустой Cell
            }
        }
        if(isEmptyCell)//Если есть пустой Cell, то нужно переключить алгоритм!!
            algorithm.setAlgorithm(new OptimAlgorithm(cells));
        else
            algorithm.setAlgorithm(new GreedyAlgorithm(cells));
    }

    //Не нравиться то, что любой может вызвать recovery!!!!
    //Что делать?
    //Хотелось бы какую-то защиту от злоумыщленников! Ничего кроме простого пароля на ум не приходит!!
    //Или не париться??
    public void recovery(String pass){
        if(pass.equals(this.pass)){
            System.out.println("Recovery is going for atm: " + getUniqueID());
            cells.clear();
            memento.printStateMemento();

            //cells.addAll(memento.getSavedCells());
            cells.addAll(reservCells);

            algorithm = memento.getSavedAlgorithm();
            isEmptyCell = memento.getSavedIsEmptyCell();
            //TODO где здесь должен быть caretaker    ????
            //Или метод этот можно засчитать за caretaker?
            printState();
        }
        else
            System.out.println("Can not recovery atm " + getUniqueID());
    }

//    private class Memento {
//        private final ArrayList<Cell> saveCells;
//        private WithdrawAlgorithm saveAlgorithm;
//        private boolean saveIsEmptyCell;
//
//        Memento(){
//            saveCells = cells;
//            saveAlgorithm = algorithm;
//            saveIsEmptyCell = isEmptyCell;
//            printStateMemento();
//        }
//
//        public ArrayList<Cell> getSavedCells(){
//            return saveCells;
//        }
//
//        public WithdrawAlgorithm getSavedAlgorithm(){
//            return saveAlgorithm;
//        }
//
//        public boolean getSavedIsEmptyCell(){
//            return saveIsEmptyCell;
//        }
//
//        public void printStateMemento(){
//            System.out.println("Memento2");
//            for(int i = 0; i < saveCells.size(); i++){
//                int[] mas = saveCells.get(i).getStateCell();
//                System.out.print("\t" + "Cell_"+ i + " - parOfNode: " + mas[0] + ", count: " + mas[1] + ", type cash - " + saveCells.get(i).typeCash() + ", isEmpty: " + mas[2] + ", isFull: " + mas[3]);
//                System.out.println();
//            }
//        }
//    }

}
