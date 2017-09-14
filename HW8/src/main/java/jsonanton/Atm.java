package jsonanton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 14.08.17.
 */

public class Atm {
    private String name = "ATM";
    public int a;
    public double b;
    public boolean c;
    public int[] mas = {1,2,3};
    List<Integer> list = new ArrayList<>();

    public Atm(){
        a = 1;
        b = a + 0.2;
        c = false;
        list.add(2); list.add(4); list.add(9);
     }
}
