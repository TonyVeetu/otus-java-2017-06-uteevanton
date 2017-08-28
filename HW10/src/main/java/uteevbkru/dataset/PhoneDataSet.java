package uteevbkru.dataset;

import javax.persistence.*;

/**
 * Created by anton on 27.08.17.
 */
@Entity
@Table(name="phone_hw10")
public class PhoneDataSet extends DataSet {

    @Column
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UsersDataSet user;

    public PhoneDataSet(){}
    public PhoneDataSet(String phone){this.phone = phone;}

    public void setPhone(String phone){this.phone = phone;}
    public String getPhone(){return phone;}

    public void setUser(UsersDataSet user){ this.user = user;}
    public UsersDataSet getUser(){return user;}
}
