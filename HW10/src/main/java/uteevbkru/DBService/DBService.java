package uteevbkru.DBService;

import uteevbkru.dataset.DataSet;
import uteevbkru.dataset.UsersDataSet;

/**
 * Created by anton on 25.08.17.
 */
public interface DBService {
    void save(UsersDataSet user);

    UsersDataSet read(long id);

    String getLocalStatus();

    void shutdown();
}
