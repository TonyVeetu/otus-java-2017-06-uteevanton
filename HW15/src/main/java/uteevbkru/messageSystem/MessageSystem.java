package uteevbkru.messageSystem;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class MessageSystem {
    private static final int DEFAULT_STEP_TIME = 10;

    private final Map<Address, ConcurrentLinkedQueue<Message>> messagesMap = new HashMap<>();
    //private final Map<Address, Addressee> addresseeMap = new HashMap<>();
    private List<Addressee> addresseeList;

    public MessageSystem(List<Addressee> list){
        addresseeList = list;
        for(Addressee addressee : addresseeList){
            messagesMap.put(addressee.getAddress(), new ConcurrentLinkedQueue<>());
        }
    }

    public void sendMessage(Message message) {
        System.out.println("**************************");
        System.out.println("**************************");
        messagesMap.get(message.getTo()).add(message);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void startThreadForCheckMessage() {
        for(Addressee addressee : addresseeList){
            new Thread(() -> {
                while (true) {
                   workWithAddresseeMessageQueue(addressee);
                }
            }).start();
        }
    }

    private void workWithAddresseeMessageQueue(Addressee addressee){
        ConcurrentLinkedQueue<Message> queue = messagesMap.get(addressee.getAddress());
        long timeStart = System.currentTimeMillis();
        while (!queue.isEmpty()) {
            Message message = queue.poll();
            message.exec(addressee);
        }
        try {
            long timeFinish = System.currentTimeMillis();
            long workTime = timeFinish - timeStart;
            long sleepTime = DEFAULT_STEP_TIME - workTime;
            if(sleepTime < DEFAULT_STEP_TIME)
              Thread.sleep(MessageSystem.DEFAULT_STEP_TIME - sleepTime);
            else
                System.out.println("Error Message System is full!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
