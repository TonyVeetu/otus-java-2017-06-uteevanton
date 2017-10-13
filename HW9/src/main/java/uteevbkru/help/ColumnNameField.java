package uteevbkru.help;

import java.lang.reflect.Field;

/**
 * Created by yas on 13.10.17.
 */
public class ColumnNameField {
    private String name;
    private Field field;

    public ColumnNameField(String name, Field field) {
        this.name = name;
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
