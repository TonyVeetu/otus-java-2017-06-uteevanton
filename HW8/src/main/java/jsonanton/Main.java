package jsonanton;

import bilder.BuildHelper;
import com.google.gson.Gson;

public class Main {

    public static void main(String[] args){
        BuildHelper buildHelper = new BuildHelper();
        Gson gson = new Gson();

        Atm atm = new Atm();
        System.out.println("toJson " + "\n" + buildHelper.toJson(atm));
        System.out.println("toGson " + "\n" + gson.toJson(atm) + "\n" + "\n");

    }
}
