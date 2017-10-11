package uteevbkru.backend.db.dbService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uteevbkru.base.MessageSystemContext;
import uteevbkru.backend.cache.CacheEngine;
import uteevbkru.backend.cache.MyElement;
import uteevbkru.backend.cache.SoftCacheEngineImpl;
import uteevbkru.backend.db.dao.UserDAO;
import uteevbkru.backend.db.dataSet.UserDataSet;
import uteevbkru.messageSystem.Address;
import uteevbkru.messageSystem.Addressee;


/**
 * Created by anton on 27.08.17.
 */
@Service
public class DBServiceImpl implements DBService, Addressee {
    private UserDAO userDAO;
    private final CacheEngine<Long, UserDataSet> cache;
    private final Address address;
    private final MessageSystemContext context;

    @Autowired
    public DBServiceImpl(MessageSystemContext context, Address address) {
        this.context = context;
        this.address = address;
        userDAO = new UserDAO();
        cache = new SoftCacheEngineImpl<>(100, 1000, 0);
    }

    @Override
    public Address getAddress() {
        return address;
    }
    
    public void init() {
        context.getMessageSystem().addAddressee(this);
    }

    @Override
    public void saveUser(UserDataSet user){
        if (user.getId() != -1) {//TODO think -1 ли?
            System.out.println("DBService.saveUser: user.getId = "+ user.getId());
            cache.evict(user.getId());
        }
        userDAO.saveUser(user);
    }

    @Override
    public UserDataSet getUser(long id){
        UserDataSet cached = cache.get(id);
        if (cached == null) {
            cached = userDAO.getUser(id);
            cache.put(new MyElement<>(id, cached));
        }
        return cached;
    }

    @Override
    public int getUserId(String login){
        //TODO
        return -1;
    }

    @Override
    public CacheEngine getCache() {
        return cache;
    }

    @Override
    public void close(){
        userDAO.close();
    }

}
