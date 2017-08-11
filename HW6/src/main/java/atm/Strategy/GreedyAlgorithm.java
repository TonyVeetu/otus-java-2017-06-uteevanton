package atm.Strategy;

import atm.Atm;
import atm.Cell;
import atm.Money.Dollar;
import atm.Money.Money;

import java.util.ArrayList;

/**
 * Created by anton on 31.07.17.
 */
public class GreedyAlgorithm  implements StrategyAlgorithm {
    private final String nameAlg = "Greedy";

    public GreedyAlgorithm(){

    }

    public boolean giveMoney(Money m, ArrayList<Cell> cellsGreed){
        int Symm = m.getCount();
        int i = 4;

        while(Symm != 0){
            if( (cellsGreed.get(i).typeCash()).equals(m.getName())){
                if(Symm >= cellsGreed.get(i).getNominal() && !cellsGreed.get(i).getIsEmpty()){
                    if(cellsGreed.get(i).giveNote()){
                        Symm -= cellsGreed.get(i).getNominal();
                        System.out.println("Greedy algorithm give: "+cellsGreed.get(i).getNominal());
                    }
                }
                if(Symm >= cellsGreed.get(i).getNominal() && !cellsGreed.get(i).getIsEmpty()){
                    i++;// Для того что бы вычитать мах раз одну и туже купюру!
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
