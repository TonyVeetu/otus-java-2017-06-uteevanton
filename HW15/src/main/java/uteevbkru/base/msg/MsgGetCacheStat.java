package uteevbkru.base.msg;

import uteevbkru.backend.db.dbService.DBService;
import uteevbkru.messageSystem.Address;
import uteevbkru.messageSystem.MessageSystem;

/**
 * Created by anton on 03.10.17.
 */
public class MsgGetCacheStat extends MsgToDB {
    private final MessageSystem messageSystem;

    public MsgGetCacheStat(MessageSystem messageSystem, Address from, Address to) {
        super(from, to);
        this.messageSystem = messageSystem;
    }

    @Override
    public void exec(DBService dbService) {
        int[] cacheStatistic = dbService.getCache().getCacheStatistic();
        messageSystem.sendMessage(new MsgGetCacheStatAnswer(getTo(), getFrom(), cacheStatistic));
    }
}
