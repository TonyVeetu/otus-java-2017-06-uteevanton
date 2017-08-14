package department;

import .*;
import java.util.ArrayList;

/**
 * Created by anton on 06.08.17.
 */
public class Department {


    private ArrayList<Atm> atms;

    public Department(ArrayList<Atm> atms){
        this.atms = atms;
    }

    public Department(){
        atms = new ArrayList<>();
    }

    public void addAtm(Atm a){
        atms.add(a);
    }

    public Atm getAtm(int a){
        if(a <= atms.size())
            return atms.get(a);
        else {
            new IndexOutOfBoundsException();
            return null;
        }
    }

}
