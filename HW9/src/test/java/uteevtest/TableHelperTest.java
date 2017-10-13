package uteevtest;

import org.junit.Assert;
import org.junit.Test;
import uteevbkru.help.TableHelper;
import uteevbkru.model.UserDataSet;

//-Xmx1024m -XX:MaxPermSize=256m

public class TableHelperTest {
    //TODO запустить тесты
    @Test
    public void getName() {
        TableHelper tableHelper = new TableHelper(UserDataSet.class);
        Assert.assertEquals(TableHelper.NAME_USER, tableHelper.getNameUser());
    }

    @Test
    public void getAge() {
        TableHelper tableHelper = new TableHelper(UserDataSet.class);
        Assert.assertEquals(TableHelper.AGE_USER, tableHelper.getAgeUser());
    }

}
