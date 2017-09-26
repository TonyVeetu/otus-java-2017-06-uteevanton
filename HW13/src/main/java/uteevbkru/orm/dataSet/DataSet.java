package uteevbkru.orm.dataSet;

import javax.persistence.*;

/**
 * Created by anton on 18.08.17.
 */
@MappedSuperclass
public abstract class DataSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = -1;


    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }
}
