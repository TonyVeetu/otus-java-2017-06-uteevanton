package uteevbkru;

import java.util.Arrays;
import java.util.Random;

public class Main {
    private static int CURRENT_THREAD = 0;
    private static int COUNT_OF_THREAD = 4;
    private static int SIZE = 20;
    private static int RANGE = 100;

    public static void main(String... args){
        //parallelWork();
        usialWork();
    }

    public static void parallelWork(){
        int[] massOfRandVal = new int[SIZE];
        Random rand = new Random(System.currentTimeMillis());
        for(int i = 0; i < massOfRandVal.length; i++ ){
            massOfRandVal[i] = rand.nextInt(RANGE + 1);
            System.out.print(massOfRandVal[i] + "\t");
        }
//+++++++_Create_4_Mass_++++++++++
        int[][] massOfMass = new int[COUNT_OF_THREAD][SIZE/COUNT_OF_THREAD];
        for(int cur_mass = 0; cur_mass < massOfMass.length; cur_mass++){
            massOfMass[cur_mass] = new int[COUNT_OF_THREAD];
        }

        long time1 = System.currentTimeMillis();
        for(int i = 0; i < COUNT_OF_THREAD; i++) {
            Thread t = new Thread(() -> {
                synchronized ((Object) CURRENT_THREAD) {
                    synchronized (massOfRandVal) {
                        //System.out.println();
                        //System.out.print("Thread number " + CURRENT_THREAD + ": \t" + (SIZE / COUNT_OF_THREAD) * CURRENT_THREAD + "-" + ((SIZE / COUNT_OF_THREAD) * (CURRENT_THREAD + 1)) + "\t");
                        massOfMass[CURRENT_THREAD] = Arrays.copyOfRange(massOfRandVal, (SIZE / COUNT_OF_THREAD) * CURRENT_THREAD, ((SIZE / COUNT_OF_THREAD) * (CURRENT_THREAD + 1)));
                        Arrays.sort(massOfMass[CURRENT_THREAD]);
                        for (int j = 0; j < massOfMass[CURRENT_THREAD].length; j++) {
                            //System.out.print(massOfMass[CURRENT_THREAD][j] + "\t");
                        }
                        CURRENT_THREAD++;
                    }
                }
            });
            t.start();
            try {
                t.join();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        long time2 = System.currentTimeMillis();
        System.out.println("Time for parallel work:"+(time2-time1));
//++++++++++++++++
        int[] part1 = new int[(SIZE/COUNT_OF_THREAD)*2];
        int[] part2 = new int[(SIZE/COUNT_OF_THREAD)*2];

        myMergeSort(massOfMass[0], massOfMass[1] ,part1);
        myMergeSort(massOfMass[2], massOfMass[3] ,part2);

        int[] itog = new int[SIZE];

        myMergeSort(part1, part2, itog);

        for(int i = 0; i < itog.length; i++){
            System.out.print(itog[i]+"\t");
        }
    }

    public static void usialWork(){
        int[] massOfRandVal = new int[SIZE];
        Random rand = new Random(System.currentTimeMillis());
        for(int i = 0; i < massOfRandVal.length; i++ ){
            massOfRandVal[i] = rand.nextInt(RANGE + 1);
            //System.out.print("Random massive: "+massOfRandVal[i] + "\t");
        }

        long time3 = System.currentTimeMillis();
        Arrays.sort(massOfRandVal);
        long time4 = System.currentTimeMillis();
        System.out.println("Time for usual work:"+(time4-time3));

        for(int j = 0; j < massOfRandVal.length; j++){
            int a = massOfRandVal[j];
            System.out.print(a + "\t");
        }
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
}
