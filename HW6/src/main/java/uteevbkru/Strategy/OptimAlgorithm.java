package uteevbkru.Strategy;

import uteevbkru.Atm;
import uteevbkru.Cell;

import java.util.ArrayList;

/**
 * Created by anton on 31.07.17.
 */
public class OptimAlgorithm implements StrategyAlgorithm {
    private ArrayList<Cell> cells = new ArrayList<>(5);

    public OptimAlgorithm(ArrayList<Cell> cells){
        this.cells = cells;
    }

    public boolean giveMoney(int count){
        // Этот алгоритм стараться выдавать все по разу и так в цикле!
        // Тем самым ячейки будут равномерно истрачены!!!

        int Symm = count;
        int i = 4;
        //TODO выдавать ту валюту которую запросили!

        while(Symm != 0){
            if( (cells.get(i).typeCash()).equals(Atm.typeCash[0].getName())){//  Запрошенная валюта!
                if(Symm >= cells.get(i).getNominal() && !cells.get(i).getIsEmpty()){
                    if(cells.get(i).giveNote()){
                        Symm -= cells.get(i).getNominal();
                        System.out.println("Optim algorithm give: "+cells.get(i).getNominal());
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
}
