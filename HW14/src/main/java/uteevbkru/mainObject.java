package uteevbkru;

import uteevbkru.helper.SortHelper;
import uteevbkru.parallel.ParallelSort;

import java.util.ArrayList;
import java.util.List;


public class mainObject {
    public static void main(String[] args){
        boolean isPrint = true;

        List<Integer> array = new ArrayList<>();
        SortHelper.fillListWithRandomValue(isPrint, array, 8, 100);

        ParallelSort parallelSort = new ParallelSort(4, isPrint);
        parallelSort.sort(array);
    }

}
