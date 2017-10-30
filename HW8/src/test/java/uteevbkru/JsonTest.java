package uteevbkru;

import bilder.BuildHelper;
import com.google.gson.Gson;
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
    public void buildHelperGsonTest(){
        Integer i = new Integer(5);
       // Assert.assertEquals(gson.toJson(i), buildHelper.toJson(i));
    }
}
