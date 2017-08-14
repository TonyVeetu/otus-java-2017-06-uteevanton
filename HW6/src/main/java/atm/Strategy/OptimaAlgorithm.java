package atm.Strategy;

import atm.Atm;
import atm.Cell;
import atm.Money.Dollar;
import atm.Money.Money;

import java.util.ArrayList;

/**
 * Created by anton on 31.07.17.
 */
public class OptimaAlgorithm implements StrategyAlgorithm {
    private final String nameAlg = "Optima";

    public OptimaAlgorithm(){
    }

    public boolean giveMoney(Money m, ArrayList<Cell> cellsOptima){
        // Этот алгоритм стараeтся выдавать все по разу и так в цикле!
        // Тем самым ячейки будут равномерно использоваться!!!

        int Symm = m.getCount();
        int i = 4;

        while(Symm != 0){
            if( (cellsOptima.get(i).typeCash()).equals(m.getName())){//  Запрошенная валюта!
                if(Symm >= cellsOptima.get(i).getNominal() && !cellsOptima.get(i).getIsEmpty()){
                    if(cellsOptima.get(i).giveNote()){
                        Symm -= cellsOptima.get(i).getNominal();
                        //System.out.println("Optima algorithm give: "+cellsOptima.get(i).getNominal());
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
