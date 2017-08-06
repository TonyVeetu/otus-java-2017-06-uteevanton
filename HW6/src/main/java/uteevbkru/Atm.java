package uteevbkru;

import uteevbkru.Money.Dollar;
import uteevbkru.Money.Evro;
import uteevbkru.Money.Money;
import uteevbkru.Money.Ruble;
import uteevbkru.Strategy.GreedyAlgorithm;
import uteevbkru.Strategy.OptimAlgorithm;
import uteevbkru.Strategy.WithdrawAlgorithm;

import java.util.ArrayList;

/**
 * Created by anton on 27.07.17.
 */
public class Atm {

    public static final Money[] typeCash = {new Ruble(), new Dollar(), new Evro()}; //TODO think должен ли я это use????

    private ArrayList<Cell> cells = new ArrayList<>(5);
    private WithdrawAlgorithm algorithm;
    private boolean emptyCell;

    Atm(ArrayList<Cell> cells){
        this.cells = cells;
        //TODO sort
        //Arrays.sort(cells);
        algorithm = new WithdrawAlgorithm(new GreedyAlgorithm(cells));
        emptyCell = false;
    }

    /**
     *
     * @param typeMoney Рубли = 0, Долары = 1, Евро = 2
     * @return сумма денег
     */
    int getResidue(Money typeMoney){
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
        //Правильно ли это?????????
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

    void printState(){
        System.out.println("\t" + "All money in ATM in rub = " + this.getResidue(new Ruble()));//TODO use static value
        System.out.println("\t" + "All money in ATM in $ = " + this.getResidue(new Dollar()));
        for(int i = 0; i < cells.size(); i++){
            int[] mas = cells.get(i).getStateCell();
            System.out.print("\t" + "Cell_"+ i + " - parOfNode: " + mas[0] + ", count: " + mas[1] + ", type cash - " + cells.get(i).typeCash() + ", isEmpty: " + mas[2] + ", isFull: " + mas[3]);
            System.out.println();
        }
    }

    private void checkEmtyCell(){
        emptyCell = false;// На случай если алгоритм нужно переключить еще раз!!!
        for(int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getIsEmpty()) {
                emptyCell = true;
            }
        }
        if(emptyCell)
            algorithm.setAlgorithm(new OptimAlgorithm(cells));
        else
            algorithm.setAlgorithm(new GreedyAlgorithm(cells));
    }
}
