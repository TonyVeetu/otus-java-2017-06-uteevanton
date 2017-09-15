package uteevbkru.orm.dbService;

import uteevbkru.cache.CacheEngine;
import uteevbkru.cache.MyElement;
import uteevbkru.cache.SoftCacheEngineImpl;
import uteevbkru.orm.dao.UserDAO;
import uteevbkru.orm.dataSet.UserDataSet;


/**
 * Created by anton on 27.08.17.
 */
public class DBServiceImpl implements DBService {

    private static volatile DBService instance;

    private UserDAO userDAO;
    private final CacheEngine<Long, UserDataSet> cache;

    public DBServiceImpl() {
        userDAO = new UserDAO();
        cache = new SoftCacheEngineImpl<>(100, 1000, 0);
    }

    public static DBService getInstance() {
        if (instance == null) {
            synchronized (DBServiceImpl.class) {
                if (instance == null) {
                    instance = new DBServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void save(UserDataSet user){
        if (user.getId() != -1) {//TODO think -1 ли?
            System.out.println("DBService.save: user.getId = "+ user.getId());
            cache.evict(user.getId());
        }
        userDAO.saveUser(user);
    }

    @Override
    public UserDataSet get(long id){
        UserDataSet cached = cache.get(id);
        if (cached == null) {
            cached = userDAO.getUser(id);
            cache.put(new MyElement<>(id, cached));
        }
        return cached;
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
