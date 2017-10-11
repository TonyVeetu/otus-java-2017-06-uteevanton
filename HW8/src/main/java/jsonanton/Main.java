package jsonanton;

/**
 * Created by anton on 14.08.17.
 */

import com.google.gson.Gson;
import bilder.BuildHelper;

import javax.json.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Atm []atms = {new Atm(), new Atm()};
        Atm atm = new Atm();


        //TODO убрать первые ковычки в json строке!!!
        BuildHelper wr = new BuildHelper();
        System.out.println("toJson "+wr.toJson(atms));
        System.out.println("toJsonObject "+wr.toJsonObject(atms));


        Gson gson = new Gson();
        System.out.println(gson.toJson(atms));

    }
}
