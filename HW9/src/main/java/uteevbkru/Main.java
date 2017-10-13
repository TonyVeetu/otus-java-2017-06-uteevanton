package uteevbkru;

import uteevbkru.DBService.DBService;
import uteevbkru.DBService.DBServiceImpl;
import uteevbkru.model.UserDataSet;
import uteevbkru.help.ConnectionHelper;
import java.io.IOException;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) throws IOException {

        Connection connection = ConnectionHelper.getConnection();
        DBService db = new DBServiceImpl(connection);

        UserDataSet user1 = new UserDataSet("Xatiko Matiko Vatiko", 79);
        db.save(user1);

        UserDataSet user2 = db.load(0L);
        System.out.println(user2.toString());

     }


}