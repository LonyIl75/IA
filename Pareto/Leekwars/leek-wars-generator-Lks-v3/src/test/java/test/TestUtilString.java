package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.leekwars.generator.fight.julienStats.utilString;



public class TestUtilString {



    @Test
    public void StringAtPosDelimiter_singletonCase_equalEntireString() {
        String str = "1";
        String delimiter = ",";
        int pos = 0;
        String  result = utilString.getStringAtPosDelimiter(str, pos, delimiter);
        String expected = str;
        Assert.assertEquals(expected, result);
    
}



@Test
public void StringAtPosDelimiter_negativePosition_equalIlastString() {
    String str = "1,2,3";
    String delimiter = ",";
    int pos = -1 ;
    String  result = utilString.getStringAtPosDelimiter(str, pos, delimiter);
    String expected =  "3";
    Assert.assertEquals(expected ,result );
}


@Test
public void StringAtPosDelimiter_pastEndposPosition_equalNull() {
    String str = "1";
    String delimiter = ",";
    int pos = 1;
    String result = utilString.getStringAtPosDelimiter(str, pos, delimiter);
    String expected = null;
    Assert.assertEquals(expected ,result );
}

@Test
public void StringAtPosDelimiter_pastEndNegPosition_equalNull() {
    String str = "1";
    String delimiter = ",";
    int pos = -1;
    String expected = null;
    String result  = utilString.getStringAtPosDelimiter(str, pos, delimiter);
    Assert.assertEquals(expected ,result );
}




@Test
public void StringAtPosDelimiter_emptyCase() {
    String str = "";
    String delimiter = ",";
    int pos = 0;
    String expected  = "";
    String result = utilString.getStringAtPosDelimiter(str, pos, delimiter);
    Assert.assertEquals(result, expected);

}

    @Test
    public void StringAtPosDelimiter_generalCase() {
        String str = "1,2,3";
        String delimiter = ",";
        int pos = 0;
        String result = utilString.getStringAtPosDelimiter(str, pos, delimiter);
        String expected = "1";
        Assert.assertEquals(result, expected);
        pos = 1;
        result = utilString.getStringAtPosDelimiter(str, pos, delimiter);
        expected = "2";
        Assert.assertEquals(result,  expected);
        pos = 2;
        result = utilString.getStringAtPosDelimiter(str, pos, delimiter);
        expected = "3";
        Assert.assertEquals(result,  expected);
    
}

@Test 
public void GetIndexString_posCase() {
    String str = "123";
    int index = 0;
    int result = 0;
    Assert.assertEquals(result, utilString.getIndexString(str, index));
    index = 1;
    result = 1;
    Assert.assertEquals(result, utilString.getIndexString(str, index));
    index = 2;
    result = 2;
    Assert.assertEquals(result, utilString.getIndexString(str, index));
}
@Test 
public void GetIndexString_negCase() {
    String str = "123";
    int index = -1;
    int result = 2;
    Assert.assertEquals(result, utilString.getIndexString(str, index));
    index = -2;
    result = 1;
    Assert.assertEquals(result, utilString.getIndexString(str, index));
    index = -3;
    result = 0;
    Assert.assertEquals(result, utilString.getIndexString(str, index));
}


}
