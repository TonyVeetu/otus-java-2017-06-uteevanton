package uteevbkru;

import bilder.BuildHelper;
import com.google.gson.Gson;
import jsonanton.Atm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JsonTest {

    private Gson gson;
    private BuildHelper buildHelper;

    @Before
    public void before(){
        gson = new Gson();
        buildHelper = new BuildHelper();
    }

    @Test
    public void integerTest(){
        Integer i = new Integer(5);
        Assert.assertEquals(gson.toJson(i), buildHelper.toJson(i));
    }

    @Test
    public void doubleTest(){
        Double d = new Double(5.2);
        Assert.assertEquals(gson.toJson(d), buildHelper.toJson(d));
    }

    @Test
    public void nullTest(){
        String d = null;
        Assert.assertEquals(gson.toJson(d), buildHelper.toJson(d));
    }

    @Test
    public void stringTest(){
        String d = "Alena";
        Assert.assertEquals(gson.toJson(d), buildHelper.toJson(d));
    }

    @Test
    public void arrayTest(){
        int[] d = {1,3};
        Assert.assertEquals(gson.toJson(d), buildHelper.toJson(d));
    }

    @Test
    public void arrayStringTest(){
        String[] f = new String[2];
        f[0] = new String("Anton");
        f[1] = new String("Alena");
        Assert.assertEquals(gson.toJson(f), buildHelper.toJson(f));
    }

    @Test
    public void classTest(){
        Atm atm = new Atm();
        Assert.assertEquals(gson.toJson(atm), buildHelper.toJson(atm));
    }

    @Test
    public void arrayOfClassObjectTest(){
        Atm a1 = new Atm();
        Atm a2 = new Atm();
        Atm[] atm = {a1,a2};
        Assert.assertEquals(gson.toJson(atm), buildHelper.toJson(atm));
    }

    @Test
    public void booleanTest(){
        Boolean b = true;
        Assert.assertEquals(gson.toJson(b), buildHelper.toJson(b));
    }


}
