package uteevbkru.DBService;

import uteevbkru.dataset.DataSet;
import uteevbkru.dataset.UsersDataSet;

import javax.xml.crypto.Data;

/**
 * Created by anton on 25.08.17.
 */
public interface DBService {
    void save(UsersDataSet user);
    UsersDataSet load(Long id);
}
