package uteevbkru;

import uteevbkru.dataset.UsersDataSet;
import uteevbkru.executor.LoadSaveExecutor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //ConnectionHelper.example();
        //ConnectWithStatements.example();
        //DataSetExample.example();
        /**_____My_part_programm______*/

        //TODO нужно ли проверять существование такого обьекта в BD??
        UsersDataSet user1 = new UsersDataSet(4, "Sergey", 21);
        LoadSaveExecutor.save(user1, UsersDataSet.class);

        UsersDataSet user2 = LoadSaveExecutor.load(3, UsersDataSet.class);
        System.out.println(user2.getDescription());
    }


}