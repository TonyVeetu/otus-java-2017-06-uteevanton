package uteevbkru.dataset;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by anton on 18.08.17.
 */
@MappedSuperclass
public abstract class DataSet {
    @Id
    private long id;

    public DataSet(long id){
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }
}
