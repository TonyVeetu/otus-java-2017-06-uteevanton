package department;

import atm.Atm;

/**
 * Created by anton on 06.08.17.
 */
public class Main {
    public static void main(String []args) {
        Department dep = new Department();

        dep.init();
        dep.printState();
        System.out.println("Money in rub: " + dep.countAllMoneyInRub());

    }
}
