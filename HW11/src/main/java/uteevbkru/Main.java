package uteevbkru;

import uteevbkru.orm.dataSet.AddressDataSet;
import uteevbkru.orm.dataSet.PhoneDataSet;
import uteevbkru.orm.dataSet.UserDataSet;
import uteevbkru.orm.dbService.DBService;
import uteevbkru.orm.dbService.DBServiceImpl;

import javax.xml.ws.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by anton on 14.09.17.
 */
public class Main {
    public static int COUNT_USER = 30;
    public static int RANGE_ID = 10;
    public static void main(String [] args){
        fillData();
        makeAction();
    }

    public static void fillData(){
        DBService service = DBServiceImpl.getInstance();

        for (int i = 0; i < COUNT_USER; i++) {
            UserDataSet user = new UserDataSet("Anton", i);

            PhoneDataSet phone1 = new PhoneDataSet();
            phone1.setPhone("123"+i);
            phone1.setUser(user);

            PhoneDataSet phone2 = new PhoneDataSet();
            phone2.setPhone("456"+i);
            phone2.setUser(user);

            AddressDataSet address = new AddressDataSet();
            address.setAddress("Moscow_"+i);
            address.setUser(user);

            List<PhoneDataSet> phones = Arrays.asList(phone1, phone2);

            user.setAddress(address);
            user.setPhones(phones);

            service.save(user);
        }
    }

    private static void makeAction() {
        DBService service = DBServiceImpl.getInstance();

        Random random = new Random(System.currentTimeMillis());
        int count = COUNT_USER;
        while(count > 0) {
            Long id = (long) (random.nextInt(RANGE_ID) + 1);
            System.out.println();
            UserDataSet user = service.get(id);
            System.out.println(user.toString());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count--;
        }
        int[] mass = service.getCache().getCacheInfo();
        System.out.println("hit | miss | size | maxsize | idle | life | eternal");
        for(int elem : mass){
            System.out.print(elem+" | ");
        }
        System.out.println();
        service.close();
    }

}
