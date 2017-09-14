package jsonanton;

/**
 * Created by anton on 14.08.17.
 */

import com.google.gson.Gson;
import nickBobrov.BildHelper;

import javax.json.*;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import static nickBobrov.BildHelper.*;

public class Main {

    private static final String NAME1 = "jsonTony.json";
    private static final String NAME2 = "gson.json";

    public static JsonArrayBuilder js_ctruct = Json.createArrayBuilder();


    public static void main(String[] args) throws FileNotFoundException {
        Atm []atms = {new Atm(), new Atm()};
        Atm atm = new Atm();

        //reflectionTreeAnother(js_ctruct, atms);

        //TODO убрать первые ковычки в json строке!!!

        BildHelper wr = new BildHelper();
        System.out.println(wr.toJson(atms));

        BildHelper.addObjectToArrayBuilder(js_ctruct, atms);

        try( JsonWriter writer = Json.createWriter(new FileWriter(NAME1)) ){
            writer.write(js_ctruct.build());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Gson gson = new Gson();
        System.out.println(gson.toJson(atms));


    }

    private static String writeToString(JsonObject jsonst) {
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter)) {
            jsonWriter.writeObject(jsonst);
        }

        return stWriter.toString();
    }

    private static void navigateTree(JsonValue tree, String key) {
        if (key != null)
            System.out.print("Key " + key + ": ");
        switch (tree.getValueType()) {
            case OBJECT:
                System.out.println("OBJECT");
                JsonObject object = (JsonObject) tree;
                for (String name : object.keySet())
                    navigateTree(object.get(name), name);
                break;
            case ARRAY:
                System.out.println("ARRAY");
                JsonArray array = (JsonArray) tree;
                for (JsonValue val : array)
                    navigateTree(val, null);
                break;
            case STRING:
                JsonString st = (JsonString) tree;
                System.out.println("STRING " + st.getString());
                break;
            case NUMBER:
                JsonNumber num = (JsonNumber) tree;
                System.out.println("NUMBER " + num.toString());
                break;
            case TRUE:
            case FALSE:
            case NULL:
                System.out.println(tree.getValueType().toString());
                break;
        }
    }

    private static void reflectionTreeAnother(JsonObjectBuilder js, Object obj) {
        int length = Array.getLength(obj);
        for(int i = 0; i  <length; i++) {
            System.out.println("*****___" + i + "___*****");
            Object object = Array.get(obj, i);
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                try {
                    Object val = field.get(object);
                    System.out.println(name + " ; " + val.getClass());
                    addJOB(js, name, val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void addJOB(JsonObjectBuilder js, String name, Object obj){
        if(obj.getClass() == int.class || obj.getClass() == Integer.class){
            System.out.println("Catch int");
            js.add(name, new Integer(obj.toString()));
        }
        else if(obj.getClass() == Double.class || obj.getClass() == double.class){
            System.out.println("Catch double");
            js.add(name, new Double(obj.toString()));
        }
        else if(obj.getClass() == boolean.class || obj.getClass() == Boolean.class) {
            System.out.println("Catch bool");
            js.add(name, new Boolean(obj.toString()));
        }
        else if(obj.getClass() == int[].class)
            System.out.println("Catch massiv");
            //TODO!!!
            //for(int i = 0; i < Array.getLength(obj); i++){
            //    System.out.println(Array.getLength(obj));
            //    Array.get(obj, i);
            //}
            //reflectionTreeAnother(js, obj);
    }

}
