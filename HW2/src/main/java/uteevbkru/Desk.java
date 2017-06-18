package uteevbkru;

/**
 * Created by anton on 15.06.17.
 */
import java.lang.*;
import java.util.function.Supplier;


public class Desk {

    private int size;
    private Object[] array;

    Desk(){
        size = 1_000_000;
    }
    Desk(int size){
        this.size = size;
        array = new Object[size];
    }

    void prepare(){
        array = new Object[size];
    }

    void measure(Supplier<Object> supplier, String name) throws InterruptedException {
            long mem = getMemChanges(() -> {
                int i = 0;
                while (i < size) {
                    array[i] = supplier.get();
                    i++;
                }
            });
            System.out.println(name + " size: " + Math.round((double) mem / size));
            System.out.println(mem);
    }

    private static long getMemChanges(Runnable create) throws InterruptedException{
        Runtime runtime = Runtime.getRuntime();
        System.gc();
        Thread.sleep(1_000);
        long memBefore = runtime.totalMemory() - runtime.freeMemory();
        create.run();
        System.gc();
        Thread.sleep(1_000);
        long memAfter = runtime.totalMemory() - runtime.freeMemory();
        return memAfter - memBefore;
    }

    void clean() throws  InterruptedException{
        array = new Object[size];
        System.gc();
        Thread.sleep(1000);
    }
}
