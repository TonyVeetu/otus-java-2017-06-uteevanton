package testframe;

import uteevbkru.ReflectionHelper;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestFrame {

    private Class[] cl;
    private String annBefore = "BeforeTestUt";
    private String annAfter = "AfterTestUt";
    private String annTest = "TestUt";

    private Object []object = new Object[MAX_OBJECTS];
    private int countOfOb = 0;
    private final static int MAX_OBJECTS = 20;

    public TestFrame(Class[] cl){
        this.cl = cl;
    }

    //Создать О который имеет аннтотацию "test"
    //Вызвать у этого О метод с аннототацией "beforetest"
    //.... "test"!
    //.... "aftertest"!

    public void start() throws Exception{
        creatObject();
        doAnnotationBefore();
        doAnnotationTest();
        doAnnotationAfter();
    }

    private void creatObject() throws Exception{
        for (int j = 0; j < cl.length; j++) {
            ArrayList<Method> m = ReflectionHelper.findAnnotations(cl[j], annTest);
            for (int i = 0; i < m.size(); i++) {
                object[countOfOb] = ReflectionHelper.instantiate(cl[j]);
                if(countOfOb < MAX_OBJECTS)
                    countOfOb++;
                else
                    System.out.println("To mach Object!!");
                //TODO логика на случай если все имеют 3 аннотации!! beforeTest, Test, afterTest!!
                //TODO  если это не так, то нужно менять логику!!!! -> создал О и вызвал сразу  нужные методы!!
            }
        }
    }
    private void doAnnotationBefore() throws Exception{
        for (int j = 0; j < cl.length; j++) {
            ArrayList<Method> m = ReflectionHelper.findAnnotations(cl[j], annBefore);
            for (int i = 0; i < m.size(); i++) {
                m.get(i).invoke(object[(j*m.size()) +i]);
            }
        }
    }

    private void doAnnotationTest() throws Exception{
        for (int j = 0; j < cl.length; j++) {
            ArrayList<Method> m = ReflectionHelper.findAnnotations(cl[j], annTest);
            for (int i = 0; i < m.size(); i++) {
                m.get(i).invoke(object[(j*m.size()) +i]);
            }
        }
    }
    
    private void doAnnotationAfter() throws Exception{
        for (int j = 0; j < cl.length; j++) {
            ArrayList<Method> m = ReflectionHelper.findAnnotations(cl[j], annAfter);
            for (int i = 0; i < m.size(); i++) {
                m.get(i).invoke(object[(j*m.size()) +i]);
            }
        }
    }


}
