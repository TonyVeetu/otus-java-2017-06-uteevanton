package uteevbkru;

import uteevbkru.DBService.DBService;
import uteevbkru.DBService.DBServiceImplHibernate;
import uteevbkru.dataset.AddressDataSet;
import uteevbkru.dataset.PhoneDataSet;
import uteevbkru.dataset.UsersDataSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        DBService db = new DBServiceImplHibernate();
        System.out.println(db.getLocalStatus());

        UsersDataSet user1 = new UsersDataSet("Ant", 24);
        UsersDataSet user2 = new UsersDataSet("Max", 20);

        AddressDataSet addr1 = new AddressDataSet("Delena");
        addr1.setUser(user1);
        AddressDataSet addr2 = new AddressDataSet("Moxovaj");
        addr2.setUser(user2);

        user1.setAddress(addr1);
        user2.setAddress(addr2);

        PhoneDataSet ph1 = new PhoneDataSet("925-193-31-23");
        PhoneDataSet ph2 = new PhoneDataSet("925-193-31-23");
        ph1.setUser(user1);
        ph2.setUser(user1);

        List<PhoneDataSet> phones = new ArrayList<>();
        phones.add(ph1);
        phones.add(ph2);

        user1.setPhones(phones);

        db.save(user1);
        db.save(user2);

        UsersDataSet user3 = db.read(1);
        System.out.println(user3.getDescription());

        db.shutdown();

    }


}