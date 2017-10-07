package atm.Strategy;

import atm.Atm;
import atm.Cell;
import atm.Money.Money;

import java.util.ArrayList;

/**
 * Created by anton on 31.07.17.
 */
public class OptimaAlgorithm implements StrategyAlgorithm {
    private final String nameAlgorithm = "Optima";

    public OptimaAlgorithm(){
    }

    /**
     * Этот алгоритм выдает все по разу и так в цикле!
     * Тем самым ячейки будут равномерно использоваться!!!
     */
    public boolean giveMoney(Money money, ArrayList<Cell> cellsOptima){

        int Symm = money.getAmountOfMoney();
        int i = Atm.COUNT_OF_CELLS_IN_ATM - 1;

        while(Symm != 0){
            if( cellsOptima.get(i).getCurrencyOfCell() == money.getCurrency() ){
                if(Symm >= cellsOptima.get(i).getNominal() && !cellsOptima.get(i).getIsEmpty()){
                    if(cellsOptima.get(i).giveNote()){
                        Symm -= cellsOptima.get(i).getNominal();
                        //System.out.println("Optima algorithm give: "+cellsOptima.get(i).getNominal()
                        //                                   + ", Symm :"+ Symm + ", i = " + i);
                    }
                }
            }
            if(i == 0){
                i = Atm.COUNT_OF_CELLS_IN_ATM - 1;
            }
            i--;
        }
        return true;
    }

    public String getName(){return nameAlgorithm;}

}
