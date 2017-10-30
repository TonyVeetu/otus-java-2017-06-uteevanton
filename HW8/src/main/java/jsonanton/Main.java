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

        Atm []atms = {new Atm(), new Atm()};
        System.out.println("toJson " + "\n" + buildHelper.toJson(atms));
        System.out.println("toGson " + "\n" + gson.toJson(atms));

        int[] b = {1,3};
        System.out.println("toJson " + "\n" + buildHelper.toJson(b));
        System.out.println("toGson " + "\n" + gson.toJson(b) + "\n" + "\n");

        String[] f = new String[2];
        f[0] = new String("Anton");
        f[1] = new String("Alena");
        System.out.println("toJson " + "\n" + buildHelper.toJson(f));
        System.out.println("toGson " + "\n" + gson.toJson(f) + "\n" + "\n");


        //String a = "dsf";
        //System.out.println("toJson " + "\n" + buildHelper.toJson(a));
        //System.out.println("toGson " + "\n" + gson.toJson(a) + "\n" + "\n");

//        Integer integer = new Integer(5);
//        System.out.println("toJson " + "\n" + buildHelper.toJson(integer));
//        System.out.println("toGson " + "\n" + gson.toJson(integer) + "\n" + "\n");



    }
}
