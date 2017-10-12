package uteevbkru.parallel;

import uteevbkru.helper.SortHelper;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static uteevbkru.helper.SortHelper.testingFinalArray;

public class ParallelSort {
    private AtomicInteger counterOfThread = new AtomicInteger(0);
    CountDownLatch latch;
    private int countOfThreads;
    private int sizeOfArrayWithRandomValue;
    private int maxRandomValue;
    private int[] arrayWithRandomValue;
    int[][] arrayOfArrays;
    int[] finalArray;

    public ParallelSort(int countOfThreads, int maxRandomValue, int sizeOfArrayWithRandomValue){
        this.maxRandomValue = maxRandomValue;
        latch = new CountDownLatch(countOfThreads);

        if((sizeOfArrayWithRandomValue % countOfThreads == 0)){
            if(powerOfTwo(countOfThreads) != -1) {
                this.countOfThreads = countOfThreads;
                this.sizeOfArrayWithRandomValue = sizeOfArrayWithRandomValue;
                arrayWithRandomValue = new int[this.sizeOfArrayWithRandomValue];
                finalArray = new int[sizeOfArrayWithRandomValue];
            }
            else
                System.out.println((char) 27 + "[31mYou must use count of thread equal powerOfTwo!( size%count_thread = 0!)" + (char)27 + "[0m");
        }
        else
            System.out.println((char) 27 + "[31mYou must use right size of array( size%count_thread = 0!)" + (char)27 + "[0m");
    }

    public void fillArray(boolean isPrint){
        Random rand = new Random(System.currentTimeMillis());
        for(int i = 0; i < arrayWithRandomValue.length; i++ ){
            arrayWithRandomValue[i] = rand.nextInt(maxRandomValue + 1);
        }
        if(isPrint){
            System.out.println("\t"+"fillArray:");
            SortHelper.printArray(arrayWithRandomValue);
        }
    }

    public void initArrayOfArrays(){
        arrayOfArrays = new int[countOfThreads][sizeOfArrayWithRandomValue /countOfThreads];
        for(int currentArray = 0; currentArray < arrayOfArrays.length; currentArray++) {
            arrayOfArrays[currentArray] = new int[sizeOfArrayWithRandomValue / countOfThreads];
        }
    }

    public void fillAndSortArrayOfArrays(boolean isPrint){
        long timeStart = System.currentTimeMillis();
        for(int i = 0; i < countOfThreads; i++) {
            Thread sortThread = new SortThread();
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

    //TODO меня смущает эта функция потому что, 1. нужны входные параметры 2. количество входных параметров три - это много!
    //Но что сделать не придумал! Дмитрий, помогите советом! =)
    public void unionOfResults(int[][] arrayOfArrays, int[] outputArray, int countOfThread){
        if(countOfThread/2 == 1){
            mergeSort(arrayOfArrays[0], arrayOfArrays[1], outputArray);
        }
        else {
            int size =  sizeOfArrayWithRandomValue /countOfThread;
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

    public int[] getFinalArray(){
        return finalArray;
    }

    public int[][] getArrayOfArrays(){
        return arrayOfArrays;
    }

    public int getCountOfThreads(){
        return countOfThreads;
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
        @Override
        public void run(){
            int currentThread = counterOfThread.getAndIncrement();
            int from = (sizeOfArrayWithRandomValue / countOfThreads) * currentThread;
            int to = ((sizeOfArrayWithRandomValue / countOfThreads) * (currentThread + 1));
            arrayOfArrays[currentThread] = Arrays.copyOfRange(arrayWithRandomValue, from, to);
            Arrays.sort(arrayOfArrays[currentThread]);
            latch.countDown();
        }
    }
}
