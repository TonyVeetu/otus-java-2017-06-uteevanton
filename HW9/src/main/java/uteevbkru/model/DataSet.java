package uteevbkru.model;

import javax.persistence.*;

/**
 * Created by anton on 18.08.17.
 */
@MappedSuperclass
public abstract class DataSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId(){
        return id;
    }
    public void setId(long id){
        if(id >= 0)
            this.id = id;
    }

}
