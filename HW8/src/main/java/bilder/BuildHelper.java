package bilder;

import javax.json.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static bilder.help.*;

public class BuildHelper {

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

    public static void addObjectToArrayBuilder(JsonArrayBuilder arrayBuilder, Object obj) {
        if (isNullObject(obj)) {
            arrayBuilder.addNull();
        } else if (isStringObject(obj)) {
            arrayBuilder.add(obj.toString());
        } else if (isDecimalNumberObject(obj)) {
            arrayBuilder.add(new BigDecimal(obj.toString()));
        } else if (isIntegerNumberObject(obj)) {
            arrayBuilder.add(new BigInteger(obj.toString()));
        } else if (isBooleanObject(obj)) {
            arrayBuilder.add((boolean) obj);
        } else if (isArrayObject(obj)) {
            arrayBuilder.add(fillArrayBuilder(arrayBuilder, obj));//Json.createArrayBuilder()
        } else {
            arrayBuilder.add(fillObjectBuilder(Json.createObjectBuilder(), obj));
        }
    }

    public static void addObjectToObjectBuilder(JsonObjectBuilder objectBuilder, String name, Object obj) {
        if (isNullObject(obj)) {
            objectBuilder.addNull(name);
        } else if (isStringObject(obj)) {
            objectBuilder.add(name, obj.toString());
        } else if (isDecimalNumberObject(obj)) {
            objectBuilder.add(name, new BigDecimal(obj.toString()));
        } else if (isIntegerNumberObject(obj)) {
            objectBuilder.add(name, new BigInteger(obj.toString()));
        } else if (isBooleanObject(obj)) {
            objectBuilder.add(name, (boolean) obj);
        } else if (isArrayObject(obj)) {
            objectBuilder.add(name, fillArrayBuilder(Json.createArrayBuilder(), obj));
        } else {
            objectBuilder.add(name, fillObjectBuilder(Json.createObjectBuilder(), obj));
        }
    }

    public JsonArray toJson(Object object){
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        addObjectToArrayBuilder(arrayBuilder, object);
        return arrayBuilder.build();
    }

    public JsonObject toJsonObject(Object object){
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        if(isArrayObject(object)){
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            fillArrayBuilder(arrayBuilder, object);
            objectBuilder.add("d",arrayBuilder);
        }
        else {
            fillObjectBuilder(objectBuilder, object);
            return objectBuilder.build();
        }
        return null;
    }
}