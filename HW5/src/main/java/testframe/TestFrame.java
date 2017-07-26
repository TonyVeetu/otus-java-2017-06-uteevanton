package testframe;

import uteevbkru.ReflectionHelper;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestFrame {

    private Class[] cl;
    private String nameAnnotation;

    public TestFrame(Class[] cl){
        this.cl = cl;
    }

    public void setNameAnnotation(String name){
        nameAnnotation = name;
    }
    public void start() throws Exception{
        for (int j = 0; j < cl.length; j++) {
            ArrayList<Method> m = ReflectionHelper.findAnnotations(cl[j], nameAnnotation);
            for (int i = 0; i < m.size(); i++) {
                Object test = ReflectionHelper.instantiate(cl[j]);
                m.get(i).invoke(test);
            }
        }
    }
}
