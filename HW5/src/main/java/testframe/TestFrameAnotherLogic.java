package testframe;

import uteevbkru.ReflectionHelper;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by anton on 05.08.17.
 *
 * Проблема в том, что все Object должны иметь beforeTest, test, AfterTest!
 * А если это не так,//TODO как быть???????
 * // Тоже самое только другими словами!!
 * //В случаи когда есть два метода с анн test, но у одного из них нет анн beforeTest!
 * //как я могу понять у какого именно??????
 */
public class TestFrameAnotherLogic {

    private Class[] cl;
    private String annBefore = "BeforeTestUt";
    private String annAfter = "AfterTestUt";
    private String annTest = "TestUt";

    private Object []object = new Object[MAX_OBJECTS];
    private int countOfOb = 0;
    private final static int MAX_OBJECTS = 20;

    public TestFrameAnotherLogic(Class[] cl){
        this.cl = cl;
    }

    public void start() throws Exception{
        for (int j = 0; j < cl.length; j++) {
            System.out.println("Info: " + cl[j].getName());
            creatObject(j);
        }
    }

    private void creatObject(int klass) throws Exception{
        ArrayList<Method> m = ReflectionHelper.findAnnotations(cl[klass], annTest);
        // m - это количество методов имеющих аннотацию Test для klass!
        for (int i = 0; i < m.size(); i++) {
            object[countOfOb] = ReflectionHelper.instantiate(cl[klass]);
            doAnnotationBefore(klass, countOfOb, i);
            doAnnotationTest(klass, countOfOb, i);
            doAnnotationAfter(klass,countOfOb, i);
            if(countOfOb < MAX_OBJECTS)
                countOfOb++;
            else
                System.out.println("To mach Object!!");
        }
    }
    private void doAnnotationBefore(int klass, int obj, int meth) throws Exception{
        ArrayList<Method> m = ReflectionHelper.findAnnotations(cl[klass], annBefore);
        m.get(meth).invoke(object[obj]);
    }

    private void doAnnotationTest(int klass, int obj, int meth) throws Exception{
        ArrayList<Method> m = ReflectionHelper.findAnnotations(cl[klass], annTest);
        m.get(meth).invoke(object[obj]);
    }

    private void doAnnotationAfter(int klass, int obj, int meth) throws Exception{
        ArrayList<Method> m = ReflectionHelper.findAnnotations(cl[klass], annAfter);
        m.get(meth).invoke(object[obj]);
    }

}
