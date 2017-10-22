package uteevbkru;

import uteevbkru.helper.SortHelper;
import uteevbkru.parallel.ParallelSort;


public class mainObject {
    public static void main(String[] args){
        ParallelSort parallelSort = new ParallelSort(8, true);
        int[] array = new int[32];
        SortHelper.fillArrayWithRandomValue(true, array, 100);
        parallelSort.sort(array);
    }

}
