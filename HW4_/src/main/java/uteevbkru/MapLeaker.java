package uteevbkru;

import java.lang.management.GarbageCollectorMXBean;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.management.GarbageCollectionNotificationInfo;
import org.apache.log4j.Logger;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;

/**
 * Created by anton on 26.06.17.
 */
public class MapLeaker {

    public ExecutorService exec = Executors.newFixedThreadPool(5);
    public Map<Task, TaskStatus> taskStatus
            = Collections.synchronizedMap(new HashMap<Task, TaskStatus>());

    private static final Logger log = Logger.getLogger(MapLeaker.class);
    private enum TaskStatus { NOT_STARTED, STARTED, FINISHED };

    private class Task implements Runnable {
        public void run() {
            taskStatus.put(this, TaskStatus.STARTED);
            doSomeWork();
            taskStatus.put(this, TaskStatus.FINISHED);
        }
    }
    public Task newTask() {
        Task t = new Task();
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

    public static void installGCMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcbean : gcbeans) {
            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            System.out.println(gcbean.getName());

            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());

                    long duration = info.getGcInfo().getDuration();
                    String gctype = info.getGcAction();
                    try {
                        Thread.sleep(1_000);
                        System.out.println(gctype + ": - "
                                + info.getGcInfo().getId() + ", "
                                + info.getGcName()
                                + " (from " + info.getGcCause() + ") " + duration + " milliseconds");
                        log.info(gctype + ": - "
                                + info.getGcInfo().getId() + ", "
                                + info.getGcName()
                                + " (from " + info.getGcCause() + ") " + duration + " milliseconds");
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            };

            emitter.addNotificationListener(listener, null, null);
        }
    }
}
