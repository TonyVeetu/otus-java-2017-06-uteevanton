package uteevbkru.orm.dataSet;

import uteevbkru.orm.dataSet.AddressDataSet;
import uteevbkru.orm.dataSet.DataSet;
import uteevbkru.orm.dataSet.PhoneDataSet;

import javax.persistence.*;
import java.util.List;

/**
 * Created by anton on 18.08.17.
 */
@Entity
@Table(name = "users_hw10")
public class UserDataSet extends DataSet {
    @Column(name = "name")
    private  String name;
    @Column
    private int age;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private AddressDataSet address;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneDataSet> phones;

    public UserDataSet(){}

    public UserDataSet(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){return name;}
    public int getAge(){return age;}
    public AddressDataSet getAddress(){return address;}
    public List<PhoneDataSet> getPhones() {
        return phones;
    }


    public void setName(String name){this.name = name;}
    public void setAge(int age){this.age = age;}
    public void setAddress(AddressDataSet address){this.address = address;}
    public void setPhones(List<PhoneDataSet> phones) {
        this.phones = phones;
    }
    public void addPhone(PhoneDataSet phone){
        if(phone != null)
            phones.add(phone);
    }

    @Override
    public String toString(){
        String phs = new String();
        for(PhoneDataSet ph : phones)
            phs += ph.getPhone() + "; ";
        return "Id: " + this.getId() + ", name: " + this.getName() + ", age: " + this.getAge() + ", street: " + address.getAddress() + ", phones: " + phs;
    }
}
