package uteevbkru.model;

import javax.persistence.*;

@Table(name="users_hw9")
public class UserDataSet extends DataSet {
    @Column(name = "name", nullable = false, updatable = true)
    private  String name = "";
    @Column(name = "age", nullable = false, updatable = true)
    private int age = 0;

    public UserDataSet(){
    }

    public UserDataSet(String name, int age){
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
