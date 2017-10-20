package uteevbkru.backend.db.dbService;

import uteevbkru.backend.cache.CacheEngine;
import uteevbkru.backend.db.dataSet.UserDataSet;

public interface DBService {

    void saveUser(UserDataSet user);

    UserDataSet getUser(long id);

    int getUserId(String login);

    CacheEngine getCache();

    void close();
}
