package uteevbkru;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static AtomicInteger counterOfThread = new AtomicInteger(0);
    private static final int COUNT_OF_THREADS = 16;
    private static final int SIZE_OF_ARRAY_WITH_RANDOM_VALUE = 32;
    private static final int RANGE_OF_RANDOM_VALUE = 100;

    public static void main(String... args){
        if((SIZE_OF_ARRAY_WITH_RANDOM_VALUE % COUNT_OF_THREADS == 0)  ) {
            if(powerOfTwo(COUNT_OF_THREADS) != -1) {
                parallelSortOfArrayWithRandomValue(COUNT_OF_THREADS);
                //notParallelSortOfArrayWithRandomValue();
            }
            else
                System.out.println((char) 27 + "[31mYou must use count of thread equal powerOfTwo!( size%count_thread = 0!)" + (char)27 + "[0m");
        }
        else
            System.out.println((char) 27 + "[31mYou must use right size of array( size%count_thread = 0!)" + (char)27 + "[0m");
    }

    public static void parallelSortOfArrayWithRandomValue(int countOfThread){
//+++++++++++++__Create_the_ArrayWithRandomValue__+++++++++
        int[] arrayWithRandomValue = new int[SIZE_OF_ARRAY_WITH_RANDOM_VALUE];
        Random rand = new Random(System.currentTimeMillis());
        for(int i = 0; i < arrayWithRandomValue.length; i++ ){
            arrayWithRandomValue[i] = rand.nextInt(RANGE_OF_RANDOM_VALUE + 1);
        }
//+++++++_Create_the_Array_of_arrays_++++++++++
        int[][] arrayOfArrays = new int[countOfThread][SIZE_OF_ARRAY_WITH_RANDOM_VALUE /countOfThread];
        for(int currentArray = 0; currentArray < arrayOfArrays.length; currentArray++){
            arrayOfArrays[currentArray] = new int[SIZE_OF_ARRAY_WITH_RANDOM_VALUE /countOfThread];
        }
///++++++++++++__Create_threads_and_parallel_sorting__+++++++++
        long timeStart = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(countOfThread);
        for(int i = 0; i < countOfThread; i++) {
            Thread t = new Thread(() -> {
                int currentThread = counterOfThread.getAndIncrement();
                arrayOfArrays[currentThread] = Arrays.copyOfRange(arrayWithRandomValue, (SIZE_OF_ARRAY_WITH_RANDOM_VALUE / countOfThread) * currentThread, ((SIZE_OF_ARRAY_WITH_RANDOM_VALUE / countOfThread) * (currentThread + 1)));
                Arrays.sort(arrayOfArrays[currentThread]);
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
        int[] finalArray = new int[SIZE_OF_ARRAY_WITH_RANDOM_VALUE];
        unionOfResults(arrayOfArrays, finalArray ,countOfThread);
//++++++++++__Testing_final_array__+++++++++++
        if(!testingFinalArray(finalArray))
            System.out.println((char) 27 + "[31m***_Error_***!!! FinalArray wasn't sorted!!" + (char)27 + "[0m");
        else
            System.out.println((char) 27 + "[33mSuccess! FinalArray was sorted!!"+ (char)27 + "[0m");
    }

    public static void unionOfResults(int[][] arrayOfArrays, int[] outputArray, int countOfThread){
        if(countOfThread/2 == 1){
            mergeSort(arrayOfArrays[0], arrayOfArrays[1], outputArray);
        }
        else {
            int size =  SIZE_OF_ARRAY_WITH_RANDOM_VALUE /countOfThread;
            int[][] reservedArray = new int[countOfThread/2][size*2];

            int j = 0;
            for(int i = 0; i < countOfThread/2; i++){
                mergeSort(arrayOfArrays[j], arrayOfArrays[++j] ,reservedArray[i]);// Занимательно, что нужно ++j, а j++ работать не будет! Что и логично!
                ++j;
            }
            unionOfResults(reservedArray, outputArray,countOfThread/2);
        }
    }

    public static void printArrayOfArrays(int[][] array){
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                System.out.print(array[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("*************");
    }

    public static void printArray(int[] array){
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i]+"\t");
        }
        System.out.println("\n"+"*****__FINISH__******");
    }

    public static void notParallelSortOfArrayWithRandomValue(){
        int[] arrayOfRandomValue = new int[SIZE_OF_ARRAY_WITH_RANDOM_VALUE];
        Random rand = new Random(System.currentTimeMillis());
        for(int i = 0; i < arrayOfRandomValue.length; i++ ){
            arrayOfRandomValue[i] = rand.nextInt(RANGE_OF_RANDOM_VALUE + 1);
        }

        long timeStart = System.currentTimeMillis();
        Arrays.sort(arrayOfRandomValue);
        long timeFinish = System.currentTimeMillis();
        System.out.println("Time for usual work:"+(timeFinish-timeStart));

        if(!testingFinalArray(arrayOfRandomValue))
            System.out.println((char) 27 + "[31m***_Error_***!!! FinalArray wasn't sorted!!" + (char)27 + "[0m");
        else
            System.out.println((char) 27 + "[33mSuccess! FinalArray was sorted!!"+ (char)27 + "[0m");
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

    public static boolean testingFinalArray(int[] array){
        if( array != null){
            for(int i = 0; i < array.length - 1; i++){
                if(array[i] > array[i+1])
                    return false;
            }
            return true;
        }
        System.out.println((char) 27 + "[31mInput array is null!" + (char)27 + "[0m");
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
