package nickBobrov;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static nickBobrov.help.*;

/**
 * Created by anton on 16.08.17.
 */
public class BildHelper {


    public static JsonArrayBuilder fillArrayBuilder(JsonArrayBuilder builder, Object array) {
        if (array instanceof Collection) {
            //TODO!!
            Collection collection = (Collection) array;
            for (Object obj : collection) {
                addObjectToArrayBuilder(builder, obj);
            }
        } else {
            int length = Array.getLength(array);
            for (int i = 0; i < length; i++) {
                Object obj = Array.get(array, i);
                addObjectToArrayBuilder(builder, obj);
            }
        }
        return builder;
    }


    @SuppressWarnings("unchecked")
    public static JsonObjectBuilder fillObjectBuilder(JsonObjectBuilder builder, Object object) {
        if (object instanceof Map) {
            Map map = (Map) object;
            Set<Map.Entry> entrySet = map.entrySet();
            for (Map.Entry entry : entrySet) {
                addObjectToObjectBuilder(builder, entry.getKey().toString(), entry.getValue());
            }
        } else {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                try {
                    Object value = field.get(object);
                    addObjectToObjectBuilder(builder, name, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder;
    }

    public static void addObjectToArrayBuilder(JsonArrayBuilder builder, Object obj) {
        if (isNullObject(obj)) {
            builder.addNull();
        } else if (isStringObject(obj)) {
            builder.add(obj.toString());
        } else if (isDecimalNumberObject(obj)) {
            builder.add(new BigDecimal(obj.toString()));
        } else if (isIntegerNumberObject(obj)) {
            builder.add(new BigInteger(obj.toString()));
        } else if (isBooleanObject(obj)) {
            builder.add((boolean) obj);
        } else if (isArrayObject(obj)) {
            builder.add(fillArrayBuilder(Json.createArrayBuilder(), obj));
        } else {
            builder.add(fillObjectBuilder(Json.createObjectBuilder(), obj));
        }
    }

    public static void addObjectToObjectBuilder(JsonObjectBuilder builder, String name, Object obj) {
        if (isNullObject(obj)) {
            // Not add null field like in gson
        } else if (isStringObject(obj)) {
            builder.add(name, obj.toString());
        } else if (isDecimalNumberObject(obj)) {
            builder.add(name, new BigDecimal(obj.toString()));
        } else if (isIntegerNumberObject(obj)) {
            builder.add(name, new BigInteger(obj.toString()));
        } else if (isBooleanObject(obj)) {
            builder.add(name, (boolean) obj);
        } else if (isArrayObject(obj)) {
            builder.add(name, fillArrayBuilder(Json.createArrayBuilder(), obj));
        } else {
            builder.add(name, fillObjectBuilder(Json.createObjectBuilder(), obj));
        }
    }

    public JsonArray toJson(Object object){
        JsonArrayBuilder js = Json.createArrayBuilder();
        addObjectToArrayBuilder(js, object);
        return js.build();
    }
}
