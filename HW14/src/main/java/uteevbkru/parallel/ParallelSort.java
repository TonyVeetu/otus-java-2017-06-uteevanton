package uteevbkru.parallel;

import uteevbkru.helper.SortHelper;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static uteevbkru.helper.SortHelper.testingFinalArray;

public class ParallelSort implements Sort{
    private AtomicInteger counterOfThread = new AtomicInteger(0);
    CountDownLatch latch;
    private int countOfThreads;
    private int sizeOfArray;
    int[] array;
    int[][] arrayOfArrays;
    int[] finalArray;
    private boolean isPrint = false;

    public ParallelSort(int countOfThreads, boolean isPrint){
        latch = new CountDownLatch(countOfThreads);
        if(powerOfTwo(countOfThreads) != -1) {
            this.countOfThreads = countOfThreads;
            this.isPrint = isPrint;
        }
        else
            System.out.println((char) 27 + "[31mYou must use count of " +
                    "thread equal powerOfTwo!" +
                    "( size%count_thread = 0!)" + (char)27 + "[0m");
    }

    /**
        Размер массива должен делиться на количество потоков без остатка!!!
     */
    public void sort(int[] mass){
        sizeOfArray = mass.length;
        array = mass;
        finalArray = new int[sizeOfArray];

        initArrayOfArrays(sizeOfArray);
        fillAndSortArrayOfArrays(isPrint, sizeOfArray);
        unionOfResults(arrayOfArrays, finalArray, countOfThreads);
        finalArrayTest(isPrint);

    }

    public void initArrayOfArrays(int size){
        arrayOfArrays = new int[countOfThreads][size/countOfThreads];
        for(int currentArray = 0; currentArray < arrayOfArrays.length; currentArray++) {
            arrayOfArrays[currentArray] = new int[size/countOfThreads];
        }
    }

    public void fillAndSortArrayOfArrays(boolean isPrint, int size){
        long timeStart = System.currentTimeMillis();
        for(int i = 0; i < countOfThreads; i++) {
            Thread sortThread = new SortThread(size);
            sortThread.start();
        }
        try {
            latch.await();
        }
        catch (InterruptedException e ){
            e.printStackTrace();
        }
        long timeFinish = System.currentTimeMillis();
        if(isPrint) {
            System.out.println("\n"+"Time for parallel work: "+(timeFinish-timeStart));
            System.out.println("\t" + "ArrayOfArrays:");
            SortHelper.printArrayOfArrays(arrayOfArrays);
        }
    }

    //TODO think!!!
    public void unionOfResults(int[][] arrayOfArrays, int[] outputArray, int countOfThread){
        if(countOfThread/2 == 1){
            mergeSort(arrayOfArrays[0], arrayOfArrays[1], outputArray);
        }
        else {
            int size =  sizeOfArray /countOfThread;
            int[][] reservedArray = new int[countOfThread/2][size*2];

            int j = 0;
            for(int i = 0; i < countOfThread/2; i++){
                mergeSort(arrayOfArrays[j], arrayOfArrays[++j] ,reservedArray[i]);// Занимательно, что нужно ++j, а j++ работать не будет! Что и логично!
                ++j;
            }
            unionOfResults(reservedArray, outputArray,countOfThread/2);
        }
    }

    public void finalArrayTest(boolean isPrint){
        if(isPrint)
            System.out.println("\t"+"FinalArrayTest:");
        if(!testingFinalArray(finalArray, isPrint))
            System.out.println((char) 27 + "[31m" +"\n" + "***_Error_***!!! FinalArray wasn't sorted!!" + (char)27 + "[0m");
        else
            System.out.println((char) 27 + "[33m" +"\n" + "Success! FinalArray was sorted!!"+ (char)27 + "[0m");
    }

    /**
     * @param value
     * @return 2^return value = param value or -1;
     */
    private int powerOfTwo(int value){
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

    /**
     * @param inputArray1 - length x
     * @param inputArray2 - length x
     * @param outputArray - length 2*x
     */
    public static void mergeSort(int[] inputArray1, int[] inputArray2, int[] outputArray) {
        int i=0, j=0;
        for (int k=0; k<outputArray.length; k++) {

            if (i > inputArray1.length-1) {
                int a = inputArray2[j];
                outputArray[k] = a;
                j++;
            }
            else if (j > inputArray2.length-1) {
                int a = inputArray1[i];
                outputArray[k] = a;
                i++;
            }
            else if (inputArray1[i] < inputArray2[j]) {
                int a = inputArray1[i];
                outputArray[k] = a;
                i++;
            }
            else {
                int b = inputArray2[j];
                outputArray[k] = b;
                j++;
            }
        }
    }

    private class SortThread extends Thread{
        private int size;
        public SortThread(int size){
            this.size = size;
        }
        @Override
        public void run(){
            int currentThread = counterOfThread.getAndIncrement();
            int from = (size / countOfThreads) * currentThread;
            int to = ((size / countOfThreads) * (currentThread + 1));
            arrayOfArrays[currentThread] = Arrays.copyOfRange(array, from, to);
            Arrays.sort(arrayOfArrays[currentThread]);
            latch.countDown();
        }
    }
}
