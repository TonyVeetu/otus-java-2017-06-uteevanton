package uteevbkru;

import testframe.TestFrame;
import testframe.TestFrameAnotherLogic;


public class Main {
    public static void main(String... args) throws Exception {
        Class cl1[] = {my.class, yours.class};
        TestFrameAnotherLogic testF = new TestFrameAnotherLogic(cl1);
        testF.start();
    }
}
