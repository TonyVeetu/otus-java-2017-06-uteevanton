package uteevbkru.DBService;

import uteevbkru.model.UserDataSet;

/**
 * Created by anton on 25.08.17.
 */
public interface DBService {
    void save(UserDataSet user);
    UserDataSet load(Long id);
}
