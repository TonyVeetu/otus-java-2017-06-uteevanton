package uteevbkru.orm.dbService;

import uteevbkru.cache.CacheEngine;
import uteevbkru.orm.dataSet.UserDataSet;

/**
 * Created by anton on 25.08.17.
 */
public interface DBService {
    void save(UserDataSet user);

    UserDataSet get(long id);

    CacheEngine getCache();

    void close();
}
