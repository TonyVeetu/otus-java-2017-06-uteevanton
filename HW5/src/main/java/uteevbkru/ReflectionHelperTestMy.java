package uteevbkru;

import org.junit.Assert;
import org.junit.Test;
import Annotations.*;

public class ReflectionHelperTestMy {
    @SuppressWarnings("ConstantConditions")
    @TestUt
    public void instantiate() {
        TestHW5My test = ReflectionHelper.instantiate(TestHW5My.class);
        Assert.assertEquals(0, test.getA());
    }

    @TestUt
    public void getFieldValue() {
        Assert.assertEquals("A", ReflectionHelper.getFieldValue(new TestHW5My(1, "A"), "s"));
        Assert.assertEquals(1, ReflectionHelper.getFieldValue(new TestHW5My(1, "B"), "a"));
    }

    @TestUt
    public void setFieldValue() {
        TestHW5My test = new TestHW5My(1, "A");
        Assert.assertEquals("A", test.getS());
        ReflectionHelper.setFieldValue(test, "s", "B");
        Assert.assertEquals("B", test.getS());
    }

    @TestUt
    public void callMethod() {
        Assert.assertEquals("A", ReflectionHelper.callMethod(new TestHW5My(1, "A"), "getS"));

        TestHW5My test = new TestHW5My(1, "A");
        ReflectionHelper.callMethod(test, "setDefault");
        Assert.assertEquals("", test.getS());
    }

}
