package uteevbkru;

import Annotations.AfterTestUt;
import Annotations.BeforeTestUt;
import Annotations.TestUt;

/**
 * Created by anton on 06.08.17.
 */
public class yours {

    private int a;
    private int b;

    @BeforeTestUt
    public void before(){
        a = 0;
        System.out.println("ann before " + a);
    }
    @TestUt
    public void myfunc(){
        a -= 1;
        System.out.println("ann test " + a);
    }
    @AfterTestUt
    public void after(){
        a -= 2;
        System.out.println("ann after " + a);
    }

    @BeforeTestUt
    public void before1(){
        b = -100;
        System.out.println("ann before " + b);
    }
    @TestUt
    public void myfunc1(){
        b -= 8;
        System.out.println("ann test " + b);
    }
    @AfterTestUt
    public void after1(){
        b -= 9;
        System.out.println("ann after " + b);
    }
}
