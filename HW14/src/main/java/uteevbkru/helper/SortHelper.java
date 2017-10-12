package uteevbkru.helper;

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
