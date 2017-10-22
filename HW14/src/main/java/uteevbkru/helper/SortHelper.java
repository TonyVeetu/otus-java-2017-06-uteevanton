package uteevbkru.helper;

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

    public static void fillArrayWithRandomValue(boolean isPrint, int[] arrayWithRandomValue, int maxRandomValue){
        Random rand = new Random(System.currentTimeMillis());
        for(int i = 0; i < arrayWithRandomValue.length; i++ ){
            arrayWithRandomValue[i] = rand.nextInt(maxRandomValue + 1);
        }
        if(isPrint){
            System.out.println("\t"+"fillArray:");
            SortHelper.printArray(arrayWithRandomValue);
        }
    }

    public static boolean testingFinalArray(int[] array, boolean isPrint){
        if(isPrint)
            printArray(array);
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
