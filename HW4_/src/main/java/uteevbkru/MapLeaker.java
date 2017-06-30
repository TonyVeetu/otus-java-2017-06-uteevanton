package uteevbkru;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by anton on 26.06.17.
 */
public class MapLeaker {
    public ExecutorService exec = Executors.newFixedThreadPool(5);
    public Map<Task, TaskStatus> taskStatus
            = Collections.synchronizedMap(new HashMap<Task, TaskStatus>());
    private Random random = new Random();

    private enum TaskStatus { NOT_STARTED, STARTED, FINISHED };

    private class Task implements Runnable {
        private int[] numbers = new int[random.nextInt(200)];

        public void run() {
            //System.out.println("B"+ " ");

            int[] temp = new int[random.nextInt(10000)];
            taskStatus.put(this, TaskStatus.STARTED);
            doSomeWork();
            taskStatus.put(this, TaskStatus.FINISHED);
        }

    }
    public Task newTask() {
        Task t = new Task();
        //System.out.print("A" + " ");
        taskStatus.put(t, TaskStatus.NOT_STARTED);
        exec.execute(t);
        return t;
    }

    void doSomeWork(){
        int []mers = new int[1_000];
        for(int i = 0; i < mers.length; i++){
            mers[i] = i;
        }
    }

}
