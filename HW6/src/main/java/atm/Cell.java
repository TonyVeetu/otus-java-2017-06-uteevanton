package atm;

import atm.Money.Money;

/**
 * Created by anton on 27.07.17.
 */
public class Cell {
    private static final int MAX_COUNT_NOTE = 20;
    private static final int MIN_COUNT_NOTE = 0;

    private boolean isEmpty;//При true ячейка пустая и выдать деньги не может!
    private boolean isFull;//При true ячейка переполнена и положить в нее деньги нельзя!
    private int nominal;// номинал купюры
    private int countOfNote = 1;// количество купюр
    private Money typeCash;// вид денег: рубли или доллары или евро!

    public Cell(Integer value, Integer countOfNote, Money typeCash){
        if(value > 0 && countOfNote > 0) {
            nominal = value;
            this.countOfNote = countOfNote;// !! Ноль тоже учитывается!!
            this.typeCash = typeCash;
            isEmpty = false;
            isFull = false;
        }
        else{
            isEmpty = true;
            isFull = false;
            System.out.println("Error in initialization Cell");
        }
    }

    public Cell(Cell cell){
        nominal = cell.getNominal();
        countOfNote = cell.getCountOfNote();
        typeCash = cell.getTypeCash();
        isEmpty = cell.getIsEmpty();
        isFull = getIsFull();
    }

    public int getNominal(){
        return nominal;
    }

    public int getCountOfNote(){return countOfNote;}

    public Money getTypeCash(){return typeCash;}

    /**
     * @param typeCash тип валюты
     * @return
     */
    int getCash(Money typeCash){
        if((this.typeCash.getName()).equals(typeCash.getName())){
            return nominal *countOfNote;
        }
        return 0;
    }

    /**
     * Я хочу иметь возможность запрещать использовать какую-то ячейку!
     * @return
     */
    private void increaseCountOfNote(){
        if(countOfNote < MAX_COUNT_NOTE){
            countOfNote++;
            if (countOfNote > MIN_COUNT_NOTE)
                isEmpty = false;
        }
        else
            isFull = true;
    }

    private void reduceCountOfNote(){
        if(countOfNote > MIN_COUNT_NOTE){
            countOfNote--;
            if(countOfNote < MAX_COUNT_NOTE)
                isFull = false;
        }
        else {
            isEmpty = true;
        }
    }

    public boolean giveNote(){
        reduceCountOfNote();
        return !this.getIsEmpty();
    }

    boolean getNote(){
        increaseCountOfNote();
        return !this.getIsFull();
    }

    public boolean getIsEmpty(){
        return isEmpty;
    }

    boolean getIsFull(){
        return isFull;
    }

    public String typeCash(){
        return typeCash.getName();
    }

    /**
     * Необходимо произвести проверку состояния ячейки!
     * @return
     */
    int[] getStateCell() {
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
