package uteevbkru;

import java.util.Collections;
import java.util.Comparator;

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
         * TEST 3 - copy
         */
        TonyVettuList<Integer> copyedMas = new TonyVettuList<>();
        for(int i = 0; i < 12; i++) {
            copyedMas.add(i);
        }
        Collections.copy(copyedMas, mas);
        System.out.println();
        System.out.println("***___Copied mas___***");
        printTony(copyedMas);
        /**
         * TEST 4 - sort
         */
        Collections.sort(mas, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        printTony(mas);
    }

    private static boolean printTony(TonyVettuList<Integer> a){
        if(a.equals(null)) {
            System.out.println("printTony: Mas = null");
            return false;
        }
        if(a.size() == 0){
            System.out.println("printTony: Mas size = 0");
            return false;
        }
        System.out.println();
        for(int i = 0; i < a.size(); i++){
            System.out.print(a.get(i) + " ");
        }
        return true;
    }
}
