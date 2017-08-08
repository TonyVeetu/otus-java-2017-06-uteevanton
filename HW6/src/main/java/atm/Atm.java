package atm;

import atm.Money.Evro;
import atm.Strategy.GreedyAlgorithm;
import atm.Money.Dollar;
import atm.Money.Money;
import atm.Money.Ruble;
import atm.Strategy.OptimAlgorithm;
import atm.Strategy.WithdrawAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by anton on 27.07.17.
 */
public class Atm {

    public static final Money[] typeCash = {new Ruble(), new Dollar(), new Evro()}; //TODO think должен ли я это use????

    private ArrayList<Cell> cells = new ArrayList<>(5);
    private WithdrawAlgorithm algorithm;
    private boolean isEmptyCell;
    //TODO
    private final String parol = "SuperJavaInOtus";

    private Memento memento;

    public Atm(ArrayList<Cell> cells){
        this.cells = cells;
        algorithm = new WithdrawAlgorithm(new GreedyAlgorithm(cells));
        isEmptyCell = false;

        memento = new Memento();
    }

    /**
     *
     * @param typeMoney Рубли = 0, Долары = 1, Евро = 2
     * @return сумма денег
     */
    public int getResidue(Money typeMoney){
        int Symm = 0;
        for(int j = 0; j < cells.size(); j++){
                Symm += cells.get(j).getCash(typeMoney);
        }
        return Symm;
    }

    /**
     * Пользователь хочет дать деньги вызывая этот метод!!
     *  И ячейка может быть переполненой isFull
     * @param countCash
     * @return
     */

    // ____П О Л У Ч Е Н И Е_____
    boolean getCashInRub(int countCash){
        // TODO убрать в Starategy!!!!
        if(countCash <= 0)
            return false;

        int Symm = countCash;
        int i = 4;

        while(Symm != 0){
            if( (cells.get(i).typeCash()).equals(typeCash[0].getName())){
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
     * @param count количество денег которое хочет получить пользователь!
     * @return
     */
    // ______В Ы Д А Ч А____
    boolean giveCashInRub( int count){
        if( !checkMoneyInRub(count)){
            return false;
        }
        // Если ячейка пустая, то нужно переключить алгоритм выдачи денег!
        //Правильно ли реализована логика переключения алгоритма?????????
        checkEmtyCell();
        algorithm.getMoney(count);
        return true;
    }

    private boolean checkMoneyInRub(int count){
        if(count < 0) {
            System.out.println("Error!!! It's impossible: " + count + " rub");
            return false;
        }
        int Symm = 0;
        for(int i = 0; i < cells.size(); i++){
            Symm += cells.get(i).getCash(typeCash[0]);
        }
        if(count > Symm) {
            System.out.println("Error!!! You want to much money!");
            return false;
        }
        else
            return true;
    }

    public void printState(){
        System.out.println("\t" + "All money in ATM in rub = " + this.getResidue(new Ruble()));//TODO use static value
        System.out.println("\t" + "All money in ATM in $ = " + this.getResidue(new Dollar()));
        for(int i = 0; i < cells.size(); i++){
            int[] mas = cells.get(i).getStateCell();
            System.out.print("\t" + "Cell_"+ i + " - parOfNode: " + mas[0] + ", count: " + mas[1] + ", type cash - " + cells.get(i).typeCash() + ", isEmpty: " + mas[2] + ", isFull: " + mas[3]);
            System.out.println();
        }
    }

    private void checkEmtyCell(){
        isEmptyCell = false;// На случай если алгоритм нужно переключить еще раз!!!
        for(int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getIsEmpty()) {
                isEmptyCell = true;
            }
        }
        if(isEmptyCell)
            algorithm.setAlgorithm(new OptimAlgorithm(cells));
        else
            algorithm.setAlgorithm(new GreedyAlgorithm(cells));
    }

    //Не нравиться что любой может вызвать recovery!!!!
    //Что делать!
    public void recovery(){
        cells = memento.getSavedCells();
        algorithm = memento.getSavedAlgorithm();
        isEmptyCell = memento.getSavedIsEmptyCell();
        // TODO где здесь должен быть caretaker    ????
    }

    private class Memento {
        private ArrayList<Cell> saveCells;
        private WithdrawAlgorithm saveAlgorithm;
        private boolean saveIsEmptyCell;

        public Memento(){
            saveCells = cells;
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

    }

}
