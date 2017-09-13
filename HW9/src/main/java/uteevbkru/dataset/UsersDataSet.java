package uteevbkru.dataset;

import javax.persistence.*;

/**
 * Created by anton on 18.08.17.
 */

@Table(name="users_hw9")
public class UsersDataSet extends DataSet {
    @Column(name = "users_hw9_name", nullable = false, updatable = true)
    private  String name = "";
    @Column(name = "users_hw9_age", nullable = false, updatable = true)
    private int age = 0;

    public UsersDataSet(){
    }

    public UsersDataSet(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){return name;}

    public int getAge(){return age;};

    @Override
    public String toString(){
        return "Id: " + this.getId() +
                ", name:" + this.getName() +
                ", age: " + this.getAge();
    }
}
