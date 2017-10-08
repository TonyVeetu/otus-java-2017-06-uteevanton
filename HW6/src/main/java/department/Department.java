package department;

import atm.Atm;
import atm.Cell;
import atm.Money.Currency;
import atm.Money.Dollar;
import atm.Money.Money;
import atm.Money.Ruble;

import java.util.ArrayList;

public class Department {

    private ArrayList<Atm> atms = new ArrayList<>();

    public Department(ArrayList<Atm> atms){
        this.atms = atms;
    }

    public int countAllMoneyInRub(){
        Ruble ruble = new Ruble();
        int Sym = 0;
        for(int i = 0; i < atms.size(); i++){
            Sym += atms.get(i).getResidue(ruble);
        }
        return Sym;
    }

    public int countOfMoney(Money money){
        int Sym = 0;
        for(int i = 0; i < atms.size(); i++){
            Sym += atms.get(i).getResidue(money);
        }
        return Sym;
    }

    public boolean getCash(Money money, int numberOfAtm){
        return  atms.get(numberOfAtm).getCash(money);
    }

    public boolean giveCash(Money money, int numberOfAtm){
        return  atms.get(numberOfAtm).giveCash(money);
    }

    public void printState(){
        for(int i = 0; i < atms.size(); i++) {
            atms.get(i).printState();
        }
    }

    public void recoveryAtms(boolean printState){
        System.out.println("Recovery Atms is starting!");
        for(int i = 0; i < atms.size(); i++)
            atms.get(i).recovery(printState);
    }
}
