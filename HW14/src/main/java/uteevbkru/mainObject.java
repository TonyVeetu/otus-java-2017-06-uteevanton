package uteevbkru;

import uteevbkru.parallel.ParallelSort;


public class mainObject {
    public static void main(String[] args){
        boolean isPrint = true;
        ParallelSort sort = new ParallelSort(8, 100, 32);
        sort.fillArray(isPrint);
        sort.initArrayOfArrays();
        sort.fillAndSortArrayOfArrays(isPrint);
        sort.unionOfResults(sort.getArrayOfArrays(), sort.getFinalArray(), sort.getCountOfThreads());
        sort.finalArrayTest(isPrint);
    }

}
