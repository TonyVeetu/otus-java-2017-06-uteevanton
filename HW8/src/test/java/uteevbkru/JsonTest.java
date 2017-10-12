package uteevbkru;

import com.google.gson.Gson;
import bilder.BuildHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by anton on 16.08.17.
 */
public class JsonTest {

    private Gson gson;
    private BuildHelper wr;

    @Before
    public void before(){
        gson = new Gson();
        wr = new BuildHelper();
    }

    @Test
    public void toJsonAndGsonTest(){
        Integer i = new Integer(5);
        Assert.assertEquals(gson.toJson(i), wr.toJson1(i));
    }
}
