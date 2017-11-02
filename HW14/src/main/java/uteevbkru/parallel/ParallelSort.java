package uteevbkru.parallel;

import uteevbkru.helper.UngradedException;
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
            testArray();
            threadPool.shutdown();
    }

    private void testArray(){
        try {
            finalArrayTest();
        }
        catch (UngradedException e){
            e.printStackTrace();
        }
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
        List<Future<Boolean>> futures = new ArrayList<>(countOfThreads);
        creationCallableThreadsInThreadPool(sizeOfArray, futures);
        checkReturnValueInCallableThread(futures);
    }

    private void creationCallableThreadsInThreadPool(int size, List<Future<Boolean>> futures){
        for(int i = 0; i < countOfThreads; i++) {
            futures.add(threadPool.submit(new SortedThread(size)));
        }

    }

    private void  checkReturnValueInCallableThread(List<Future<Boolean>> futures){
        for(Future<Boolean> future : futures){
            try {
                if (!future.get())
                    exit(0);//Всегда у Callable нужно вызывать блокирующий get()!
            }
            catch (InterruptedException | ExecutionException e ){
                e.printStackTrace();
            }
        }
    }

    private void unionOfResults(int[][] arrayOfArrays, int[] outputArray, int countOfThreads){
        if(countOfThreads/2 == 1){
            mergeSort(arrayOfArrays[0], arrayOfArrays[1], outputArray);
        }
        else {
            int[][] reservedArray = initReservedArray(countOfThreads);
            int j = 0;
            for(int i = 0; i < countOfThreads/2; i++){
                mergeSort(arrayOfArrays[j], arrayOfArrays[++j] ,reservedArray[i]);// Занимательно, что нужно ++j, а j++ работать не будет! Что и логично!
                ++j;
            }
            unionOfResults(reservedArray, outputArray,countOfThreads/2);
        }
    }

    private int[][] initReservedArray(int countOfThreads){
        int size =  sizeOfArray /countOfThreads;
        int[][] mass = new int[countOfThreads/2][size*2];
        int s = 0;
        for(int k = 0; k < countOfThreads/2; k++){
            mass[k] = new int[arrayOfArrays[s].length + arrayOfArrays[++s].length];
            s++;
        }
        return mass;
    }

    private void finalArrayTest() throws UngradedException {
        if (!testingFinalArray(finalArray))
            throw new UngradedException("Array is ungraded!");
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

    private class SortedThread implements Callable<Boolean>{
        private int size;
        SortedThread(int size){
            if(size > 0)
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
