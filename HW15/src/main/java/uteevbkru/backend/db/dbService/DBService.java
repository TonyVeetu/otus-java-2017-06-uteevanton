package uteevbkru.backend.db.dbService;

import uteevbkru.backend.cache.CacheEngine;
import uteevbkru.backend.db.dataSet.UserDataSet;

/**
 * Created by anton on 25.08.17.
 */
public interface DBService {
    void init();

    void saveUser(UserDataSet user);

    UserDataSet getUser(long id);

    int getUserId(String login);

    CacheEngine getCache();

    void close();
}
