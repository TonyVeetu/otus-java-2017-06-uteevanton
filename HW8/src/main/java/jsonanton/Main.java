package jsonanton;

/**
 * Created by anton on 14.08.17.
 */

import com.google.gson.Gson;
import bilder.BuildHelper;

import java.io.*;

/**
 * Задача в том, чтобы сделать свой сериализатор на основе json!
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        BuildHelper wr = new BuildHelper();
        Gson gson = new Gson();

        Atm atm = new Atm();
        System.out.println("toJson " + "\n" + wr.toJson(atm));
        System.out.println("toJson1 "+ "\n" + wr.toJson1(atm));
        System.out.println("toGson " + "\n" + gson.toJson(atm));
        System.out.println("toJson2 "+ "\n" + wr.toJson2(atm));
        //В этом случаи toJson1 работает правильно(результат полностью совпадает с gson). А toJson2 работает не правильно!

        //А toJson всегда работает не правильно, потому что в нем всегда есть дополнительные квадратные скобки!

        Atm []atms = {new Atm(), new Atm()};
        System.out.println("toJson " + "\n" + wr.toJson(atms));
        System.out.println("toJson1 "+ "\n" + wr.toJson1(atms));
        System.out.println("toGson " + "\n" + gson.toJson(atms));
        System.out.println("toJson2 "+ "\n" + wr.toJson2(atms));
        //А в этом случаи toJson1 работает НЕ правильно, появляются лишние фигурные скобки!! А toJson2 работает правильно(результат полностью совпадает с gson)!
        //А как toJson1 и toJson2 обьеденить в одну функцию я понять не могу!
        //Библеотеку gson смотрел все равно идея не появилось!!
        //Прошу подсказать!
    }
}
