package uteevbkru;

public class Main {
    public static void main(String... args){

        MapLeaker ml = new MapLeaker();
        MapLeaker.installGCMonitoring();
        while( true ){
            ml.newTask();
        }
    }



}
