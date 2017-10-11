package uteevbkru.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uteevbkru.base.MessageSystemContext;
import uteevbkru.base.msg.MsgGetCacheStat;
import uteevbkru.base.msg.MsgGetUserId;
import uteevbkru.messageSystem.Address;
import uteevbkru.messageSystem.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anton on 03.10.17.
 */
@Service
public class FrontendServiceImpl implements FrontendService {
    private final Address address;
    private final MessageSystemContext context;

    private final Map<Integer, String> users = new HashMap<>();
    private int[] cacheStatistic = new int[7];//TODO на что заменить 7!

    @Autowired
    public FrontendServiceImpl(MessageSystemContext context, Address address) {
        this.context = context;
        this.address = address;
    }

    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    public void handleRequest(String login) {
        Message message = new MsgGetUserId(context.getMessageSystem(), getAddress(), context.getDbAddress(), login);
        context.getMessageSystem().sendMessage(message);
    }

    public void addUser(int id, String name) {
        users.put(id, name);
        System.out.println("User: " + name + " has id: " + id);
    }

    public void getCacheStatistic(){
        Message message = new MsgGetCacheStat(context.getMessageSystem(), getAddress(), context.getDbAddress());
        context.getMessageSystem().sendMessage(message);
    }

    public void setCacheStatistic(int[] mas){
        if(cacheStatistic.length < mas.length) {
            for (int i = 0; i < cacheStatistic.length; i++) {
                cacheStatistic[i] = mas[i];
            }
        }
        //TODO
        //cacheStatistic = Arrays.copyOf(mas, mas.length);
    }

    public int[] copyCacheStatistic(int[] mas){
        for(int i = 0; i < mas.length; i++){
            mas[i] = cacheStatistic[i];
        }
        return mas;
    }
}
