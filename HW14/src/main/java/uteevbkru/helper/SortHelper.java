package uteevbkru.helper;

import java.util.List;
import java.util.Random;

public class SortHelper {

    public static void printArrayOfArrays(int[][] array){
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                System.out.print(array[i][j]+"\t");
            }
            System.out.println();
        }
    }

    public static void printArray(int[] array){
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i]+"\t");
        }
    }

    public static void printList(List<Integer> array){
        for(int i = 0; i < array.size(); i++){
            System.out.print(array.get(i)+"\t");
        }
    }

    public static void fillListWithRandomValue(boolean isPrint, List<Integer> arrayWithRandomValue,int size, int maxRandomValue){
        Random rand = new Random(System.currentTimeMillis());
        for(int i = 0; i < size; i++ ){
            arrayWithRandomValue.add(rand.nextInt(maxRandomValue + 1));
        }
        if(isPrint){
            System.out.println("\t"+"fillArray:");
            SortHelper.printList(arrayWithRandomValue);
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
}
