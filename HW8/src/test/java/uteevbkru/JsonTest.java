package uteevbkru;

import com.google.gson.Gson;
import nickBobrov.BildHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by anton on 16.08.17.
 */
public class JsonTest {

    private Gson gson;
    private BildHelper wr;

    @Before
    public void before(){
        gson = new Gson();
        wr = new BildHelper();
    }

    @Test
    public void test(){
        Integer i = new Integer(5);
        Assert.assertEquals(gson.toJson(i), wr.toJson(i));
    }
}
