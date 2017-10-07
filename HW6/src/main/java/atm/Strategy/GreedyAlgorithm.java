package atm.Strategy;

import atm.Atm;
import atm.Cell;
import atm.Money.Money;

import java.util.ArrayList;

public class GreedyAlgorithm  implements StrategyAlgorithm {
    private final String nameAlgorithm = "Greedy";

    public GreedyAlgorithm(){

    }

    public boolean giveMoney(Money money, ArrayList<Cell> cellsGreed){
        int Symm = money.getAmountOfMoney();
        int i = Atm.COUNT_OF_CELLS_IN_ATM - 1;

        while(Symm != 0){
            if(cellsGreed.get(i).getCurrencyOfCell() == money.getCurrency()){
                if(Symm >= cellsGreed.get(i).getNominal() && !cellsGreed.get(i).getIsEmpty()){
                    if(cellsGreed.get(i).giveNote()){
                        Symm -= cellsGreed.get(i).getNominal();
                        //System.out.println("Greedy algorithm give: "+cellsGreed.get(i).getNominal());
                    }
                }
                if(Symm >= cellsGreed.get(i).getNominal() && !cellsGreed.get(i).getIsEmpty()){
                    i++;// Для того что бы вычесть максимум раз одну и туже купюру!
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
