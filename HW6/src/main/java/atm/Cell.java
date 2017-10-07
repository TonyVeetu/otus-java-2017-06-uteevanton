package atm;

import atm.Money.Currency;
import atm.Money.Money;

/**
 * Created by anton on 27.07.17.
 */
public class Cell {
    public static final int MAX_COUNT_NOTE = 20;
    public static final int MIN_COUNT_NOTE = 0;

    private boolean isEmpty;//При true ячейка пустая и выдать деньги не может!
    private boolean isFull;//При true ячейка переполнена и положить в нее деньги нельзя!
    private int nominal;// номинал купюры
    private int countOfNote;// количество купюр
    private Currency currency;// валюта

    public Cell(int nominal, int countOfNote, Currency currency){
        //TODO use Nominal!
        if(nominal > 0 && countOfNote >= MIN_COUNT_NOTE && countOfNote <= MAX_COUNT_NOTE) {
            this.nominal = nominal;
            this.countOfNote = countOfNote;// !! Ноль тоже учитывается!!
            this.currency = currency;
            isEmpty = false;
            isFull = false;
        }
        else{
            isEmpty = true;
            isFull = false;
            System.out.println("Error in initialization of Cells");
        }
    }

    public Cell(Cell cell){
        nominal = cell.getNominal();
        countOfNote = cell.getCountOfNote();
        currency = cell.getCurrencyOfCell();
        isEmpty = cell.getIsEmpty();
        isFull = getIsFull();
    }

    public int getNominal(){
        return nominal;
    }

    public int getCountOfNote(){return countOfNote;}

    public Currency getCurrencyOfCell(){return currency;}

    /**
     * @param money тип валюты
     * @return
     */
    public int getCash(Money money){
        if(this.currency == money.getCurrency()){
            return nominal *countOfNote;
        }
        return 0;
    }

    public int getFreeSpaceForMoney(Money money){
        if(this.currency == money.getCurrency()){
            return nominal * (Cell.MAX_COUNT_NOTE  - countOfNote);
        }
        return 0;
    }

    /**
     * Я хочу иметь возможность запрещать использовать какую-то ячейку!
     * @return
     */
    private void increaseCountOfNote(){
        if(!isFull)
            countOfNote++;
    }

    private void reduceCountOfNote(){
        if(!isEmpty)
            countOfNote--;
    }

    public boolean giveNote(){
        if(!getIsEmpty())
            reduceCountOfNote();
        return !isEmpty;
    }

    boolean getNote(){
        if(!getIsFull())
            increaseCountOfNote();
        return !isFull;
    }

    public boolean getIsEmpty(){
        return isEmpty = ((countOfNote == MIN_COUNT_NOTE) ? true : false);
    }

    boolean getIsFull(){
        return isFull = countOfNote == MAX_COUNT_NOTE ? true : false;
    }

    /**
     * Необходимо произвести проверку состояния ячейки!
     * @return
     */
    public int[] getStateCell(){
        if (countOfNote == MAX_COUNT_NOTE)
            isFull = true;
        if (countOfNote == MIN_COUNT_NOTE)
            isEmpty = true;

            int[] mas = new int[4];//5
            mas[0] = this.nominal;
            mas[1] = countOfNote;
            mas[2] = isEmpty ? 1 : 0;
            mas[3] = isFull ? 1 : 0;
            return mas;
    }

}
