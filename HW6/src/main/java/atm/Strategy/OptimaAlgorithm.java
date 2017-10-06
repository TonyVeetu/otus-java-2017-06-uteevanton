package atm.Strategy;

import atm.Cell;
import atm.Money.Money;

import java.util.ArrayList;

/**
 * Created by anton on 31.07.17.
 */
public class OptimaAlgorithm implements StrategyAlgorithm {
    private final String nameAlg = "Optima";

    public OptimaAlgorithm(){
    }

    public boolean giveMoney(Money money, ArrayList<Cell> cellsOptima){
        // Этот алгоритм стараeтся выдавать все по разу и так в цикле!
        // Тем самым ячейки будут равномерно использоваться!!!

        int Symm = money.getAmountOfMoney();
        int i = 4;

        while(Symm != 0){
            if( cellsOptima.get(i).getCurrencyOfCell() == money.getCurrency() ){//  Запрошенная валюта!
                if(Symm >= cellsOptima.get(i).getNominal() && !cellsOptima.get(i).getIsEmpty()){
                    if(cellsOptima.get(i).giveNote()){
                        Symm -= cellsOptima.get(i).getNominal();
                        //System.out.println("Optima algorithm give: "+cellsOptima.getCurrencyOfCell(i).getNominal());
                    }
                }
            }
            if(i == 0){
                i = 4;
            }
            i--;
        }
        return true;
    }

    public String getName(){return nameAlg;}

}
