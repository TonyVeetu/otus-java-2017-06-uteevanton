package uteevbkru.DBService;

import uteevbkru.model.UserDataSet;

public interface DBService {
    void save(UserDataSet user);
    UserDataSet load(Long id);
}
