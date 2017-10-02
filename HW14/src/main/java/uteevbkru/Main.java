package uteevbkru;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static AtomicInteger counterOfThread = new AtomicInteger(0);
    private static int countOfThread = 16;
    private static int sizeOfMassWithRandomValue = 32;
    private static int rangeOfRandomValue = 100;

    public static void main(String... args){
        if((sizeOfMassWithRandomValue % countOfThread == 0)  ) {
            if(powerOfTwo(countOfThread) != -1) {
                parallelSortOfMassWithRandomValue(countOfThread);
                //notParallelSortOfMassWithRandomValue();
            }
            else
                System.out.println((char) 27 + "[31mYou must use count of thread equal powerOfTwo!( size%count_thread = 0!)" + (char)27 + "[0m");
        }
        else
            System.out.println((char) 27 + "[31mYou must use right size of massive( size%count_thread = 0!)" + (char)27 + "[0m");
    }

    public static void parallelSortOfMassWithRandomValue(int  countOfThread){
//+++++++++++++__Create_the_MassWithRandomVal__+++++++++
        int[] massOfRandVal = new int[sizeOfMassWithRandomValue];
        Random rand = new Random(System.currentTimeMillis());
        for(int i = 0; i < massOfRandVal.length; i++ ){
            massOfRandVal[i] = rand.nextInt(rangeOfRandomValue + 1);
        }
//+++++++_Create_the Massive_of_massivies++++++++++
        int[][] massOfMass = new int[countOfThread][sizeOfMassWithRandomValue /countOfThread];
        for(int current_mass = 0; current_mass < massOfMass.length; current_mass++){
            massOfMass[current_mass] = new int[sizeOfMassWithRandomValue /countOfThread];
        }
///++++++++++++__Create_threads_and_parallel_sorting__+++++++++
        long timeStart = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(countOfThread);
        for(int i = 0; i < countOfThread; i++) {
            Thread t = new Thread(() -> {
                int current_thread = counterOfThread.getAndIncrement();
                massOfMass[current_thread] = Arrays.copyOfRange(massOfRandVal, (sizeOfMassWithRandomValue / countOfThread) * current_thread, ((sizeOfMassWithRandomValue / countOfThread) * (current_thread + 1)));
                Arrays.sort(massOfMass[current_thread]);
                latch.countDown();
            });
            t.start();
        }
        try {
            latch.await();
        }
        catch (InterruptedException e ){
            e.printStackTrace();
        }
        long timeFinish = System.currentTimeMillis();
        System.out.println("\n"+"Time for parallel work: "+(timeFinish-timeStart));

//++++++++++++++++__Union_of_results__*++++++++++++
        int[] finalMass = new int[sizeOfMassWithRandomValue];
        unionOfResults(massOfMass, finalMass ,countOfThread);
//++++++++++__Testing_final_massive__+++++++++++
        if(!testingFinalMass(finalMass))
            System.out.println((char) 27 + "[31m***_Error_***!!! FinalMass wasn't sorted!!" + (char)27 + "[0m");
        else
            System.out.println((char) 27 + "[33mSuccess! FinalMass was sorted!!"+ (char)27 + "[0m");
    }

    public static void unionOfResults(int[][] massOfMass, int[] outputMass, int countOfThread){
        if(countOfThread/2 == 1){
            myMergeSort(massOfMass[0], massOfMass[1], outputMass);
        }
        else {
            int size =  sizeOfMassWithRandomValue/countOfThread;
            int[][] resMass = new int[countOfThread/2][size*2];
            //printMassOfMass(massOfMass);
            int j = 0;
            for(int i = 0; i < countOfThread/2; i++){
                myMergeSort(massOfMass[j], massOfMass[++j] ,resMass[i]);// Занимательно, что нужно ++j, а j++ работать не будет! Что и логично!
                ++j;
            }
            //printMassOfMass(resMass);
            unionOfResults(resMass, outputMass,countOfThread/2);
        }
    }

    public static void printMassOfMass(int[][] mas){
        for(int i = 0; i < mas.length; i++){
            for(int j = 0; j < mas[i].length; j++){
                System.out.print(mas[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("*************");
    }

    public static void printMass(int[] mas){
        for(int i = 0; i < mas.length; i++){
            System.out.print(mas[i]+"\t");
        }

        System.out.println("\n"+"*****__FINISH__******");
    }

    public static void notParallelSortOfMassWithRandomValue(){
        int[] massOfRandVal = new int[sizeOfMassWithRandomValue];
        Random rand = new Random(System.currentTimeMillis());
        for(int i = 0; i < massOfRandVal.length; i++ ){
            massOfRandVal[i] = rand.nextInt(rangeOfRandomValue + 1);
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
        System.out.println((char) 27 + "[31mInput massive is null!" + (char)27 + "[0m");
        return false;
    }

    /**
     * @param value
     * @return 2^return value = param value or -1;
     */
    public static int powerOfTwo(int value){
        if(value % 2 == 0 && value > 1) {
            return 1 + powerOfTwo(value/2);
        }
        else if(value == 1){
            return 0;
        }
        else {
            return -1;
        }
    }



}
