package uteevbkru;

import testframe.TestFrame;


public class Main {
    public static void main(String... args) throws Exception {

        Class cl[] = {my.class, ReflectionHelperTestMy.class};

        TestFrame testF = new TestFrame(cl);
        testF.setNameAnnotation("TestUt");
        testF.start();
    }
}
