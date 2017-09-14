package uteevbkru;

import org.junit.Assert;
import org.junit.Test;

public class TableHelperTest {

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
