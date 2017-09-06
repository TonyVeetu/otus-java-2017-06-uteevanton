package uteevbkru;

import uteevbkru.DBService.DBService;
import uteevbkru.DBService.DBServiceImpl;
import uteevbkru.dataset.UsersDataSet;
import uteevbkru.main.ConnectionHelper;
import uteevbkru.main.TableHelper;

import java.io.IOException;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) throws IOException {

//        Connection connection = ConnectionHelper.getConnection();
//        DBService db = new DBServiceImpl(connection, UsersDataSet.class);
//
//        //UsersDataSet user11 = new UsersDataSet(5, "Xan", 25);
//        //db.save(user11);
//        UsersDataSet user11 = db.load(5);
//        System.out.println(user11.toString());

        //Итог 27 августа
        //+//1. getconnection использую один раз = исправил!
        //-//2. У меня не получилось сделать проверку if(result.next()){...} пишет cannot resolve method next
        //+//3. Где увидел там поборол NPE!!
        //+//4. Маппинг имения поля к имени столбца таблицы задается через аннотацию! - Мне это пока не требуется use!
        //-//5. Не подлючил log4 в проект!

        TableHelper.getNameUser();
     }


}