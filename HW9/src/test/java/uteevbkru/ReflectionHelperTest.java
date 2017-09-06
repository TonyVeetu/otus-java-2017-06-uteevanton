package uteevbkru;

import org.junit.Assert;
import org.junit.Test;

public class ReflectionHelperTest {
    @SuppressWarnings("ConstantConditions")
    @Test
    public void instantiate() {
        TestHW5 testHW5 = ReflectionHelper.instantiate(TestHW5.class);
        Assert.assertEquals(0, testHW5.getA());
    }

//    @Test
//    public void getFieldValue() {
//        Assert.assertEquals("A", ReflectionHelper.getFieldValue(new TestClass(1, "A"), "s"));
//        Assert.assertEquals(1, ReflectionHelper.getFieldValue(new TestClass(1, "B"), "a"));
//    }
//
//    @Test
//    public void setFieldValue() {
//        TestClass test = new TestClass(1, "A");
//        Assert.assertEquals("A", test.getS());
//        ReflectionHelper.setFieldValue(test, "s", "B");
//        Assert.assertEquals("B", test.getS());
//    }
//
//    @Test
//    public void callMethod() {
//        Assert.assertEquals("A", ReflectionHelper.callMethod(new TestClass(1, "A"), "getS"));
//
//        TestClass test = new TestClass(1, "A");
//        ReflectionHelper.callMethod(test, "setDefault");
//        Assert.assertEquals("", test.getS());
//    }

}
