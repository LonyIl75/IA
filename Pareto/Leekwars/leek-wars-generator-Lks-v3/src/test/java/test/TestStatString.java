package test;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.leekwars.generator.fight.julienStats.utilString;
import com.leekwars.generator.fight.julienStats.old.statString;


public class TestStatString {
    String delimFunction_custom,delimStorage_custom;
    String correct_entityType,  incorrect_entityType,noEntityType;
    String correct_dimensionType, incorrect_dimensionType,noDimensionType;
    String functionName,noFunctionName;
    String option, option2;


    String storageName_correct_withoutOption,storageName_correct_withOption,storageName_correct_withOptions;
    List<String> storageNameList_correct_withoutOption ,storageNameList_correct_withOption,storageNameList_correct_withOptions;


    String functionName_correct_withoutOption ,functionName_correct_withOption ,functionName_correct_withOptions;
    List<String> functionNameList_correct_withoutOption ,functionNameList_correct_withOption ,functionNameList_correct_withOptions;
    


    @Before
    public void setUp() throws Exception {

        delimFunction_custom = ";";
        delimStorage_custom = ",";

        correct_entityType = "E";
        correct_dimensionType = "D";
        incorrect_entityType = "W";
        incorrect_dimensionType = "Z";
        functionName = "functionName";
        option = "N";
        option2 = "N2";

        noFunctionName ="";
        noEntityType ="";
        noDimensionType ="";

        storageName_correct_withoutOption = utilString.concatString(delimStorage_custom,correct_entityType, functionName , correct_dimensionType);
        functionName_correct_withoutOption = utilString.concatString(delimFunction_custom,correct_entityType ,  functionName , correct_dimensionType);
        
        storageNameList_correct_withoutOption = Arrays.asList(correct_entityType,functionName ,correct_dimensionType);
        functionNameList_correct_withoutOption = Arrays.asList(correct_entityType,functionName,correct_dimensionType);

        /*List<String> functionNameList_incorrect_noFunction = Arrays.asList(correct_entityType,noFunctionName,correct_dimensionType);
        List<String> functionNameList_incorrect_noDimension = Arrays.asList(correct_entityType,functionName,noDimensionType);
        List<String> functionNameList_incorrect_noEntity = Arrays.asList(noEntityType,functionName,correct_dimensionType);

        List<String> functionNameList_incorrect_noFunction2 = Arrays.asList(correct_entityType,correct_dimensionType);
        List<String> functionNameList_incorrect_noDimension2 = Arrays.asList(correct_entityType,functionName);
        List<String> functionNameList_incorrect_noEntity2 = Arrays.asList(functionName,correct_dimensionType);*/

        //String functionName_incorrect_empty = "";

        String functionName_incorrect_entityType = utilString.concatString(delimFunction_custom  ,incorrect_entityType , functionName ,   correct_dimensionType);
        String functionName_incorrect_dimensionType = utilString.concatString(delimFunction_custom ,correct_entityType , functionName , incorrect_dimensionType);
    
        /*List<String> functionNameList_incorrect_entityType = Arrays.asList(incorrect_entityType,functionName ,correct_dimensionType);
        List<String> functionNameList_incorrect_dimensionType = Arrays.asList(correct_entityType,functionName ,incorrect_dimensionType);*/
    
        String functionName_incorrect_delimiterFunction1 =correct_entityType +delimStorage_custom+functionName+delimFunction_custom+correct_dimensionType;
        String functionName_incorrect_delimiterFunction2 = correct_entityType +delimFunction_custom+functionName+delimStorage_custom+correct_dimensionType;
        
        
    
    
        String functionName_incorrect_noFunction = utilString.concatString(delimFunction_custom,correct_entityType ,noFunctionName,correct_dimensionType);
        String functionName_incorrect_noDimension = utilString.concatString(delimFunction_custom,correct_entityType ,functionName,noDimensionType);
        String functionName_incorrect_noEntity = utilString.concatString(delimFunction_custom,noEntityType,functionName,correct_dimensionType);
    
    
    
        

        storageName_correct_withOption = utilString.concatString(delimStorage_custom,correct_entityType , option ,functionName ,  correct_dimensionType ) ;
        storageName_correct_withOptions = utilString.concatString(delimStorage_custom,correct_entityType , option , option2  ,functionName ,  correct_dimensionType) ;

        functionName_correct_withOption = utilString.concatString(delimFunction_custom,correct_entityType ,option, functionName , correct_dimensionType );
        functionName_correct_withOptions = utilString.concatString(delimFunction_custom,correct_entityType ,option, option2 , functionName , correct_dimensionType );

        storageNameList_correct_withOption = Arrays.asList(correct_entityType,option,functionName ,correct_dimensionType);
        storageNameList_correct_withOptions = Arrays.asList(correct_entityType,option,option2,functionName ,correct_dimensionType);
        functionNameList_correct_withOption = Arrays.asList(correct_entityType,option,functionName ,correct_dimensionType);
        functionNameList_correct_withOptions = Arrays.asList(correct_entityType,option,option2,functionName ,correct_dimensionType);

    /* 
        statString void_empty_default = new  statString();
        statString void_empty_custom = new  statString(delimFunction_custom,delimStorage_custom);
        statString string_storageName_default = new  statString(storageName_correct_withoutOption);
        statString list_storageName_default = new  statString(storageNameList_correct_withoutOption);
        //statString string_storageName_custom = new  statString(storageName_custom,";",",");
        statString list_storageName_custom = new  statString(storageNameList_correct_withoutOption,delimFunction_custom,delimStorage_custom);

        */

    }
    @Test
    public void getStorageName_listToString_(){
        Assert.assertEquals(storageName_correct_withoutOption,statString.getStorageName(storageNameList_correct_withoutOption,delimStorage_custom));
    }
    
    @Test
    public void getFunctionName_listToString_(){
      Assert.assertEquals(functionName_correct_withoutOption,statString.getFullName(functionNameList_correct_withoutOption,delimFunction_custom));
    }
   
    @Test
    public void isValidDimensionType__true(){
        Assert.assertTrue(statString.isValidDimensionType(functionNameList_correct_withoutOption));
    }
    @Test
    public void isValidEntityType_entityTypeStringInvalid_false(){
        List<String> storageNameList_incorrect_entityType = Arrays.asList(incorrect_entityType,functionName ,correct_dimensionType);
        Assert.assertFalse(statString.isValidEntityType(storageNameList_incorrect_entityType));
    }
    @Test
    public void isValidDimensionType_dimensionTypeListInvalid_false(){
        List<String> storageNameList_incorrect_dimensionType = Arrays.asList(correct_entityType,functionName ,incorrect_dimensionType);
        Assert.assertFalse(statString.isValidDimensionType(storageNameList_incorrect_dimensionType));
    }


    @Test 
    public void isCorrectList_noFunctionName_false(){
        List<String> storageNameList_incorrect_noFunctionName = Arrays.asList(correct_entityType,noFunctionName,correct_dimensionType);
        Assert.assertFalse(statString.isCorrectList(storageNameList_incorrect_noFunctionName));
    }
    @Test 
    public void isValidDimensionType_noDimension_false(){
        List<String> storageNameList_incorrect_noDimension = Arrays.asList(correct_entityType,functionName,noDimensionType);
        Assert.assertFalse(statString.isValidDimensionType(storageNameList_incorrect_noDimension));
    }
    @Test 
    public void isValidEntityType_noEntity_false(){
        List<String> storageNameList_incorrect_noEntity = Arrays.asList(noEntityType,functionName,correct_dimensionType);
        Assert.assertFalse(statString.isValidEntityType(storageNameList_incorrect_noEntity));
    }
    @Test
    public void isValidDimensionType_noDimension2_false(){
        List<String> storageNameList_incorrect_noDimension2 = Arrays.asList(correct_entityType,functionName);
        Assert.assertFalse(statString.isValidDimensionType(storageNameList_incorrect_noDimension2));
    }
    @Test 
    public void isValidEntityType_noEntity2_false(){
        List<String> storageNameList_incorrect_noEntity2 = Arrays.asList(functionName,correct_dimensionType);
        Assert.assertFalse(statString.isValidEntityType(storageNameList_incorrect_noEntity2));
    }
    @Test 
    public void isCorrectList_noFunctionName2_false(){
        List<String> storageNameList_incorrect_noFunction2 = Arrays.asList(correct_entityType,correct_dimensionType);
        Assert.assertFalse(statString.isCorrectList(storageNameList_incorrect_noFunction2));
    }

    
    @Test
    public void getOptions_withoutOption_null(){
        List<String> expected = null;
        Assert.assertEquals(expected,statString.getOptions(storageNameList_correct_withoutOption));
    }
    @Test
    public void getOptions_withOption_equalOneOption(){
        List<String> expected = Arrays.asList(option);
        Assert.assertEquals(expected,statString.getOptions(storageNameList_correct_withOption));
    }
    @Test
    public void getOptions_withOptions_equalTwoOptions(){
        List<String> expected = Arrays.asList(option,option2);
        Assert.assertEquals(expected,statString.getOptions(storageNameList_correct_withOptions));
    }

    @Test
    public void getEntityType_correct_equalEntityType(){
        String expected = correct_entityType;
        Assert.assertEquals(expected,statString.getEntityType(storageNameList_correct_withoutOption));
    }
    @Test 
    public void getEntityType_incorrect_null(){
        List<String> storageNameList_incorrect_entityType = Arrays.asList(incorrect_entityType,functionName ,correct_dimensionType);
        String expected = null;
        Assert.assertEquals(expected,statString.getEntityType(storageNameList_incorrect_entityType));
    }
    @Test
    public void getEntityType_noEntity_null(){
        List<String> storageNameList_incorrect_noEntity = Arrays.asList(noEntityType,functionName,correct_dimensionType);
        String expected = null;
        Assert.assertEquals(expected,statString.getEntityType(storageNameList_incorrect_noEntity));
    }
    @Test
    public void getEntityType_noEntity2_null(){
        List<String> storageNameList_incorrect_noEntity2 = Arrays.asList(functionName,correct_dimensionType);
        String expected = null;
        Assert.assertEquals(expected,statString.getEntityType(storageNameList_incorrect_noEntity2));
    }

    




    @Test
    public void getDimensionType_correct_equalDimensionType(){
        String expected = correct_dimensionType;
        Assert.assertEquals(expected,statString.getDimensionType(storageNameList_correct_withoutOption));
    }
    @Test 
    public void getDimensionType_incorrect_null(){
        List<String> storageNameList_incorrect_dimensionType = Arrays.asList(correct_entityType,functionName ,incorrect_dimensionType);
        String expected = null;
        Assert.assertEquals(expected,statString.getDimensionType(storageNameList_incorrect_dimensionType));
    }
    @Test
    public void getDimensionType_noDimension_null(){
        List<String> storageNameList_incorrect_noDimension = Arrays.asList(correct_entityType,functionName,noDimensionType);
        String expected = null;
        Assert.assertEquals(expected,statString.getDimensionType(storageNameList_incorrect_noDimension));
    }
    @Test
    public void getDimensionType_noDimension2_null(){
        List<String> storageNameList_incorrect_noDimension2 = Arrays.asList(correct_entityType,functionName);
        String expected = null;
        Assert.assertEquals(expected,statString.getDimensionType(storageNameList_incorrect_noDimension2));
    }


    @Test
    public void getFunctionName_correct_equalFunctionName(){
        String expected = functionName;
        Assert.assertEquals(expected,statString.getFunctionName(storageNameList_correct_withoutOption));
    }

    @Test
    public void getFunctionName_noFunction_null(){
        List<String> storageNameList_incorrect_noFunction = Arrays.asList(correct_entityType,noFunctionName,correct_dimensionType);
        String expected = null;
        Assert.assertEquals(expected,statString.getFunctionName(storageNameList_incorrect_noFunction));
    }

    @Test
    public void getFunctionName_noFunction2_null(){
        List<String> storageNameList_incorrect_noFunction2 = Arrays.asList(correct_entityType,correct_dimensionType);
        String expected = null;
        Assert.assertEquals(expected,statString.getFunctionName(storageNameList_incorrect_noFunction2));
    }
    
    
    @Test
    public void getIndexOfFunctionName_correct_equalOne(){
        Integer expected = 1;
        Assert.assertEquals(expected,statString.getIndexOfFunctionName(storageNameList_correct_withoutOption));
    }
    @Test
    public void getIndexOfFunctionName_correct_equalTwo(){
        Integer expected = 2;
        Assert.assertEquals(expected,statString.getIndexOfFunctionName(storageNameList_correct_withOption));
    }


    @Test
    public void getIndexOfEntityType_correct_equalZero(){
        Integer expected = 0;
        Assert.assertEquals(expected,statString.getIndexOfEntityType(storageNameList_correct_withoutOption));
    }
    @Test
    public void getIndexOfDimensionType_correct_equalTwo(){
        Integer expected = 2;
        Assert.assertEquals(expected,statString.getIndexOfDimensionType(storageNameList_correct_withoutOption));
    }
    @Test
    public void getIndexOfOptions_correct_equalNull(){
        Integer expected = null;
        Assert.assertEquals(expected,statString.getIndexOfOptions(storageNameList_correct_withoutOption));
    }

    @Test
    public void getIndexOfOptions_correct_equalOne(){
        Integer expected = 1;
        Assert.assertEquals(expected,statString.getIndexOfOptions(storageNameList_correct_withOption));
    }

    @Test
    public void isCorrectFunctionName_correct_true(){
        Assert.assertTrue(statString.isCorrectFunctionName(functionName_correct_withoutOption,delimFunction_custom));
    }

    @Test
    public void isCorrectStorageName_correct_true(){
        Assert.assertTrue(statString.isCorrectFunctionName(storageName_correct_withoutOption,delimStorage_custom));
    }

       
       
  
    @Test
    public void isCorrectStorageName_incorrectET_false(){
        String storageName_incorrect_entityType = utilString.concatString(delimStorage_custom,incorrect_entityType , functionName , correct_dimensionType);
        Assert.assertFalse(statString.isCorrectStorageName(storageName_incorrect_entityType,delimStorage_custom));
    }
    @Test
    public void isCorrectStorageName_incorrectDT_false(){
        String storageName_incorrect_dimensionType = utilString.concatString(delimStorage_custom,correct_entityType , functionName ,   incorrect_dimensionType);
        Assert.assertFalse(statString.isCorrectStorageName(storageName_incorrect_dimensionType,delimStorage_custom));
    }
    @Test
    public void isCorrectStorageName_noDimension1_false(){
        String storageName_incorrect_noDimension1 = utilString.concatString(delimStorage_custom,correct_entityType ,functionName,noDimensionType);
        Assert.assertFalse(statString.isCorrectStorageName(storageName_incorrect_noDimension1,delimStorage_custom));
    }
    @Test
    public void isCorrectStorageName_noDimension2_false(){
        String storageName_incorrect_noDimension2 = utilString.concatString(delimStorage_custom,correct_entityType ,functionName);
        Assert.assertFalse(statString.isCorrectStorageName(storageName_incorrect_noDimension2,delimStorage_custom));
    }

    @Test
    public void isCorrectStorageName_empty_false(){
        String storageName_incorrect_empty = "";
        Assert.assertFalse(statString.isCorrectStorageName(storageName_incorrect_empty,delimStorage_custom));
    }

    @Test
    public void isCorrectStorageName_noFunction1_false(){
        String storageName_incorrect_noFunction1 = utilString.concatString(delimStorage_custom,correct_entityType ,noFunctionName,correct_dimensionType);
        Assert.assertFalse(statString.isCorrectStorageName(storageName_incorrect_noFunction1,delimStorage_custom));
    }
    @Test
    public void isCorrectStorageName_noFunction2_false(){
        String storageName_incorrect_noFunction2 = utilString.concatString(delimStorage_custom,correct_entityType ,correct_dimensionType);
        Assert.assertFalse(statString.isCorrectStorageName(storageName_incorrect_noFunction2,delimStorage_custom));
    }


    @Test
    public void isCorrectStorageName_noEntity1_false(){
        String storageName_incorrect_noEntity1 = utilString.concatString(delimStorage_custom, noEntityType,functionName,correct_dimensionType);
        Assert.assertFalse(statString.isCorrectStorageName(storageName_incorrect_noEntity1,delimStorage_custom));
    }
    @Test
    public void isCorrectStorageName_noEntity2_false(){
        String storageName_incorrect_noEntity2 = utilString.concatString(delimStorage_custom, functionName,correct_dimensionType);
        Assert.assertFalse(statString.isCorrectStorageName(storageName_incorrect_noEntity2,delimStorage_custom));
    }



    @Test
    public void isCorrectStorageName_incorrectDelim1_false(){
        String storageName_incorrect_delimiterStorage1 = correct_entityType +delimFunction_custom+functionName+delimStorage_custom+correct_dimensionType;
        Assert.assertFalse(statString.isCorrectStorageName(storageName_incorrect_delimiterStorage1,delimStorage_custom));
    }

    @Test
    public void isCorrectStorageName_incorrectDelim2_false(){
        String storageName_incorrect_delimiterStorage2 = correct_entityType +delimStorage_custom+functionName+delimFunction_custom+correct_dimensionType;
        Assert.assertFalse(statString.isCorrectStorageName(storageName_incorrect_delimiterStorage2,delimStorage_custom));
    }



    
    





/* 
    @Test
    public void isCorrectFunctionName_incorrect_false(){
        Assert.assertFalse(statString.isCorrectFunctionName(functionName_incorrect_withoutOption,delimFunction_custom));
    }
*/

    


    
  





}
