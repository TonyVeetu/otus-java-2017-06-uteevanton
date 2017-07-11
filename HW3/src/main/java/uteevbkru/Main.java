package uteevbkru;

//import java.lang.management.ManagementFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * VM options -Xmx512m -Xms512m
 * <p>
 * Runtime runtime = Runtime.getRuntime();
 * long mem = runtime.totalMemory() - runtime.freeMemory();
 * <p>
 * System.gc()
 * <p>
 * jconsole, connect to pid
 */
//@SuppressWarnings({"RedundantStringConstructorCall", "InfiniteLoopStatement"})
public class Main {
    public static void main(String... args){


        /**
         * TEST 1 - add(int  index, int value)
         */
        TonyVettuList<Integer> mas = new TonyVettuList<>();
        mas.add(2);
        mas.add(4);
        mas.add(5);
        mas.add(-1);
        mas.add(1);
        mas.add(1,23);
        mas.add(45);
        mas.add(3,23);
        printTony(mas);
        /**
         * TEST 2 - addAll
         */
        TonyVettuList<Integer> masNew = new TonyVettuList<>();
        masNew.add(25);
        masNew.add(41);
        masNew.add(7);
        Integer []in = {1,2,3};
        Collections.addAll(mas, in);
        printTony(mas);
        /**
         * TEST 3 - sort
         */
        Collections.sort(mas, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println();
        System.out.println("______*****SORT******______");
        for(int i = 0; i < mas.size(); i++){
            System.out.print(mas.get(i) + " ");
        }

    }

    private static void printTony(TonyVettuList<Integer> a){
        System.out.println();
        for(int i = 0; i < a.size(); i++){
            System.out.print(a.get(i) + " ");
        }
    }
}
