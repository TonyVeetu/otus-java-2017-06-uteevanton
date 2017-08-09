package atm.Strategy;

import atm.Atm;
import atm.Cell;
import atm.Money.Money;

import java.util.ArrayList;

/**
 * Created by anton on 31.07.17.
 */
public class GreedyAlgorithm  implements StrategyAlgorithm {
    private ArrayList<Cell> cells = new ArrayList<>(5);

    public GreedyAlgorithm(ArrayList<Cell> cells){
        this.cells = cells;
    }

//    public boolean giveMoneyInRub(int count){
//        int Symm = count;
//        int i = 4;
//
//        while(Symm != 0){
//            if( (cells.get(i).typeCash()).equals(Atm.typeCash[0].getName())){
//                if(Symm >= cells.get(i).getNominal() && !cells.get(i).getIsEmpty()){
//                    if(cells.get(i).giveNote()){
//                        Symm -= cells.get(i).getNominal();
//                        System.out.println("Greedy algorithm give: "+cells.get(i).getNominal());
//                    }
//                }
//                if(Symm >= cells.get(i).getNominal() && !cells.get(i).getIsEmpty()){
//                    i++;// Для того что бы вычитать мах раз одну и туже купюру!
//                }
//            }
//            if(i == 0){
//                i = 4;
//            }
//            i--;
//        }
//        return true;
//    }


    public boolean giveMoney(Money m){
        int Symm = m.getCount();
        int i = 4;

        while(Symm != 0){
            if( (cells.get(i).typeCash()).equals(m.getName())){
                if(Symm >= cells.get(i).getNominal() && !cells.get(i).getIsEmpty()){
                    if(cells.get(i).giveNote()){
                        Symm -= cells.get(i).getNominal();
                        System.out.println("Greedy algorithm give: "+cells.get(i).getNominal());
                    }
                }
                if(Symm >= cells.get(i).getNominal() && !cells.get(i).getIsEmpty()){
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
}
