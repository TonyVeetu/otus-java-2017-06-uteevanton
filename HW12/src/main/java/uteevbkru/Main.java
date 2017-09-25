package uteevbkru;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import uteevbkru.orm.dataSet.AddressDataSet;
import uteevbkru.orm.dataSet.PhoneDataSet;
import uteevbkru.orm.dataSet.UserDataSet;
import uteevbkru.orm.dbService.DBService;
import uteevbkru.orm.dbService.DBServiceImpl;
import uteevbkru.web.AdminServlet;
import uteevbkru.web.LoginServlet;
import uteevbkru.web.TimerServlet;

import javax.xml.ws.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by anton on 14.09.17.
 */
public class Main {

    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "public_html";

    public static int COUNT_USER = 10;
    public static int RANGE_ID = 10;
    public static void main(String [] args) throws Exception{
        DBService service = DBServiceImpl.getInstance();

        fillData(service);
        makeAction(service);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new LoginServlet("anonymous")), "/login");
        context.addServlet(new ServletHolder(new AdminServlet(service)), "/admin");
        context.addServlet(TimerServlet.class, "/timer");
        

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();


    }

    public static void fillData(DBService service){
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

    private static void makeAction(DBService service) {

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
    }

}
