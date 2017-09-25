package uteevbkru;

import java.util.Arrays;
import java.util.Random;

public class Main {
    private static Integer COUNTER_OF_THREAD = new Integer(0);
    private static int COUNT_OF_THREAD = 4;
    private static int SIZE = 1_000_000;
    private static int RANGE = 100;

    public static void main(String... args){
        if(SIZE % COUNT_OF_THREAD == 0) {
            parallelWork();
            //usualWork();
        }
        else
            System.out.println((char) 27 + "[31mYou must enter right size!( size%count_thread = 0!)" + (char)27 + "[0m");
    }

    public static void parallelWork(){
        int[] massOfRandVal = new int[SIZE];
        Random rand = new Random(System.currentTimeMillis());
        for(int i = 0; i < massOfRandVal.length; i++ ){
            massOfRandVal[i] = rand.nextInt(RANGE + 1);
        }
//+++++++_Create_4_Mass_++++++++++
        int[][] massOfMass = new int[COUNT_OF_THREAD][SIZE/COUNT_OF_THREAD];
        for(int cur_mass = 0; cur_mass < massOfMass.length; cur_mass++){
            massOfMass[cur_mass] = new int[COUNT_OF_THREAD];
        }

        long time1 = System.currentTimeMillis();

        Thread th1 = new Thread();
        Thread th2 = new Thread();
        Thread th3 = new Thread();
        Thread th4 = new Thread();
        Thread[] mas = new Thread[4];

        mas[0] = th1;
        mas[1] = th2;
        mas[2] = th3;
        mas[3] = th4;

        for(int i = 0; i < COUNT_OF_THREAD; i++) {
            mas[i] = new Thread(() -> {
                int current_thread = 0;
                synchronized (COUNTER_OF_THREAD) {// Я же не могу быть уверенным, что в этот момент другой поток не схватит COUNTER_OF_THREAD!
                    current_thread = COUNTER_OF_THREAD;
                    COUNTER_OF_THREAD = current_thread + 1;
                }
                massOfMass[current_thread] = Arrays.copyOfRange(massOfRandVal, (SIZE / COUNT_OF_THREAD) * current_thread, ((SIZE / COUNT_OF_THREAD) * (current_thread + 1)));
                Arrays.sort(massOfMass[current_thread]);
            });
            mas[i].start();
        }

        for(int i = 0; i < COUNT_OF_THREAD; i++) {
            try {
                mas[i].join();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        long time2 = System.currentTimeMillis();
        System.out.println("\n"+"Time for parallel work:"+(time2-time1));
//++++++++++++++++
        int[] part1 = new int[(SIZE/COUNT_OF_THREAD)*2];
        int[] part2 = new int[(SIZE/COUNT_OF_THREAD)*2];
        myMergeSort(massOfMass[0], massOfMass[1] ,part1);
        myMergeSort(massOfMass[2], massOfMass[3] ,part2);

        int[] finalMass = new int[SIZE];
        myMergeSort(part1, part2, finalMass);

        if(!testingFinalMass(finalMass))
            System.out.println((char) 27 + "[31m***_Error_***!!! FinalMass wasn't sorted!!" + (char)27 + "[0m");
        else
            System.out.println((char) 27 + "[33mSuccess! FinalMass was sorted!!"+ (char)27 + "[0m");
    }

    public static void usualWork(){
        int[] massOfRandVal = new int[SIZE];
        Random rand = new Random(System.currentTimeMillis());
        for(int i = 0; i < massOfRandVal.length; i++ ){
            massOfRandVal[i] = rand.nextInt(RANGE + 1);
        }

        long time3 = System.currentTimeMillis();
        Arrays.sort(massOfRandVal);
        long time4 = System.currentTimeMillis();
        System.out.println("Time for usual work:"+(time4-time3));


        if(!testingFinalMass(massOfRandVal))
            System.out.println((char) 27 + "[31m***_Error_***!!! FinalMass wasn't sorted!!" + (char)27 + "[0m");
        else
            System.out.println((char) 27 + "[33mSuccess! FinalMass was sorted!!"+ (char)27 + "[0m");
    }

    /**
     * @param a1 input mass, length x
     * @param a2 input mass, length x
     * @param a3 output mass,  length 2*x
     */
    public static void myMergeSort(int[] a1, int[] a2, int[] a3) {
        int i=0, j=0;
        for (int k=0; k<a3.length; k++) {

            if (i > a1.length-1) {
                int a = a2[j];
                a3[k] = a;
                j++;
            }
            else if (j > a2.length-1) {
                int a = a1[i];
                a3[k] = a;
                i++;
            }
            else if (a1[i] < a2[j]) {
                int a = a1[i];
                a3[k] = a;
                i++;
            }
            else {
                int b = a2[j];
                a3[k] = b;
                j++;
            }
        }
    }

    public static boolean testingFinalMass(int[] mass){
        if( mass != null){
            for(int i = 0; i < mass.length - 1; i++){
                if(mass[i] > mass[i+1])
                    return false;
            }
            return true;
        }
        return false;
    }
}
