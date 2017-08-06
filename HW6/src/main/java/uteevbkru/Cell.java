package uteevbkru;

import uteevbkru.Money.Money;

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

    Cell(Integer value, Integer countOfNote, Money typeCash){
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

    public int getNominal(){
        return nominal;
    }

    /**
     * Я хочу иметь возможность запрещать использовать какую-то ячейку!
     * @return
     */
    private void increaseCountOfNote(){
        if(countOfNote < MAX_COUNT_NOTE){
            countOfNote++;
            //!!!смешно!!!
            if (countOfNote > MIN_COUNT_NOTE)
                isEmpty = false;
        }
        else
            isFull = true;
    }

    private void reduceCountOfNote(){
        if(countOfNote > MIN_COUNT_NOTE){
            countOfNote--;
            //!!!смешно!!!
            if(countOfNote < MAX_COUNT_NOTE)
                isFull = false;
        }
        else {
            isEmpty = true;
        }

        //TODO change algorithm!!!!!
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

            //TODO  сделать красиво!!!
//            if(typeCash.getName().equals(new Ruble().getName()))
//                mas[4] = 0;
//            if(typeCash.getName().equals(new Dollar().getName()))
//                mas[4] = 1;
//            if(typeCash.getName().equals(new Evro().getName()))
//                mas[4] = 2;
            return mas;
    }

}
