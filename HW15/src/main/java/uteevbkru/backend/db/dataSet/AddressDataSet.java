package uteevbkru.backend.db.dataSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by anton on 27.08.17.
 */
@Entity
@Table(name="address_hw10")
public class AddressDataSet extends DataSet {

    @Column
    private String street;

    @OneToOne
    private UserDataSet user;

    public AddressDataSet(){}

    public AddressDataSet(String street){
        this.street = street;
    }

    public void setAddress(String street){this.street = street;}
    public String getAddress(){return street;}

    public void setUser(UserDataSet user){ this.user = user;}
    public UserDataSet getUser(){return user;}
}
