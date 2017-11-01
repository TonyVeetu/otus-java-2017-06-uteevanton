package uteevbkru.parallel;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.exit;
import static uteevbkru.helper.SortHelper.testingFinalArray;

public class ParallelSort implements Sort{
    private ExecutorService threadPool;
    private AtomicInteger counterOfThread = new AtomicInteger(0);
    private int countOfThreads;
    private int sizeOfArray;
    private int[] array;
    private int[][] arrayOfArrays;
    private int[] finalArray;

    public ParallelSort(int countOfThreads){
        threadPool = Executors.newFixedThreadPool(countOfThreads);

        if(powerOfTwo(countOfThreads) != -1)
            this.countOfThreads = countOfThreads;
        else
            System.out.println((char) 27 + "[31mYou must use count of " +
                    "thread equal powerOfTwo!" +
                    "( size%count_thread = 0!)" + (char)27 + "[0m");
    }

    @Override
    public void sort(List<Integer> list) {
            sizeOfArray = list.size();
            array = list.stream().mapToInt(i -> i).toArray();
            finalArray = new int[sizeOfArray];

            initArrayOfArrays(sizeOfArray);
            sortArrayOfArrays();
            unionOfResults(arrayOfArrays, finalArray, countOfThreads);
            finalArrayTest();
            threadPool.shutdown();
    }

    private void initArrayOfArrays(int size){
        arrayOfArrays = new int[countOfThreads][size/countOfThreads];
        for(int currentArray = 0; currentArray < arrayOfArrays.length; currentArray++) {
            if(currentArray == countOfThreads -1)
                arrayOfArrays[currentArray] = new int[size - (size / countOfThreads) * currentArray];//Последний подмассив длинее всех!
            else
                arrayOfArrays[currentArray] = new int[size/countOfThreads];
        }
    }

    private void sortArrayOfArrays(){
        try {
            fillAndSortArrayOfArrays(sizeOfArray);
        }
        catch (InterruptedException | ExecutionException e ){
            e.printStackTrace();
        }
    }

    private void fillAndSortArrayOfArrays(int size) throws  InterruptedException, ExecutionException{
        List<Future<Boolean>> futures = new ArrayList<>(countOfThreads);
        for(int i = 0; i < countOfThreads; i++) {
            futures.add(threadPool.submit(new SortThread(size)));
        }
        for(Future<Boolean> future : futures){
            if(!future.get()) {
                exit(0);
            }
        }
    }

    private void unionOfResults(int[][] arrayOfArrays, int[] outputArray, int countOfThread){
        if(countOfThread/2 == 1){
            mergeSort(arrayOfArrays[0], arrayOfArrays[1], outputArray);
        }
        else {
            int size =  sizeOfArray /countOfThread;
            int[][] reservedArray = new int[countOfThread/2][size*2];
            int s = 0;
            for(int k = 0; k < countOfThread/2; k++){
                reservedArray[k] = new int[arrayOfArrays[s].length + arrayOfArrays[++s].length];
                s++;
            }
            int j = 0;
            for(int i = 0; i < countOfThread/2; i++){
                mergeSort(arrayOfArrays[j], arrayOfArrays[++j] ,reservedArray[i]);// Занимательно, что нужно ++j, а j++ работать не будет! Что и логично!
                ++j;
            }
            unionOfResults(reservedArray, outputArray,countOfThread/2);
        }
    }

    private void finalArrayTest(){
        if (testingFinalArray(finalArray)) {
            System.out.println((char) 27 + "[33m" +"\n" + "Success! FinalArray was sorted!!"+ (char)27 + "[0m");
        } else {
            System.out.println((char) 27 + "[31m" + "\n" + "***_Error_***!!! FinalArray wasn't sorted!!" + (char) 27 + "[0m");
        }
    }

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

    private void mergeSort(int[] input1, int[] input2, int[] output) {
        int index = 0;
        int index1 = 0;
        int index2 = 0;

        while (index < output.length) {
            boolean isBreak = false;

            if (index1 == input1.length) {
                System.arraycopy(input2, index2, output, index, input2.length - index2);
                isBreak = true;
            }
            if (index2 == input2.length) {
                System.arraycopy(input1, index1, output, index, input1.length - index1);
                isBreak = true;
            }
            if (isBreak)
                break;
            if (input1[index1] < input2[index2]) {
                output[index] = input1[index1];
                index1++;
            } else {
                output[index] = input2[index2];
                index2++;
            }
            index++;
        }
    }

    private class SortThread implements Callable<Boolean>{
        private int size;
        SortThread(int size){
            this.size = size;
        }

        @Override
        public Boolean call(){
            int currentThread = counterOfThread.getAndIncrement();
            int from, to;
            from = (size / countOfThreads) * currentThread;
            if(currentThread != countOfThreads - 1)
                to = ((size / countOfThreads) * (currentThread + 1));
            else
                to = size;
            arrayOfArrays[currentThread] = Arrays.copyOfRange(array, from, to);
            Arrays.sort(arrayOfArrays[currentThread]);
            return true;
        }
    }

}
