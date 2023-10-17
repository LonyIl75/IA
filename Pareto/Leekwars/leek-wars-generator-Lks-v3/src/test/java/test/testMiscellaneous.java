package test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.leekwars.generator.fight.julienStats.util.Miscellaneous;

public class testMiscellaneous {

    double beg , mid , end ;
    private static final double DELTA = 1e-15;

    final int sizeArray = 3;
    double [] arraDblSeries1_3 = new double[sizeArray];

    double [] arrDblEmpty1_0 ;
    double [] arrDblEmpty2_0  = new double[sizeArray];
    double [] arrDblZeros1_3 = new double[sizeArray];

    
    @Before
    public void setUp() throws Exception {
        beg = 0;
        end = beg+1;
        mid =  (beg+end)/2;
        arraDblSeries1_3 = new double[]{0,1,2};
        arrDblEmpty1_0 = null;
        arrDblEmpty2_0 = new double[]{};
        arrDblZeros1_3 = new double[]{0,0,0};
        

    }

    //MATH

        //MEAN2

            //DBL
                @Test
                public void test_mean2()throws Exception {
                    double expected = mid ;
                    double actual = Miscellaneous.mean2(beg, end);

                    Assert.assertEquals(expected, actual ,DELTA);
                
                }


//ARR
        //ZEROS 

            //DBL
                @Test
                public void test_zerosArrDbl_3_zeros()throws Exception {
                    double [] expected = arrDblZeros1_3 ;
                    double [] actual = Miscellaneous.zerosArrDbl(sizeArray);
                    boolean isEqual = Arrays.equals(expected,actual);
                    Assert.assertTrue(isEqual);
                
                }

        // ISEMPTY 

            //BASE
                @Test
                public void test_isEmpty_null_false()throws Exception {
                    boolean expected = true ;
                    boolean actual = Miscellaneous.isEmptyArrDbl(arrDblEmpty1_0);
                    Assert.assertEquals(expected, actual);
                
                }
                @Test
                public void test_isEmpty_empty_true()throws Exception {
                    boolean expected = true ;
                    boolean actual = Miscellaneous.isEmptyArrDbl(arrDblEmpty2_0);
                    Assert.assertEquals(expected, actual);
                
                }

            //DBL 
                @Test
                public void test_isEmpty_Dbl_zeros_true()throws Exception {
                    boolean expected = true ;
                    boolean actual = Miscellaneous.isEmptyArrDbl(arrDblZeros1_3);
                    Assert.assertEquals(expected, actual);
                
                }
        

        // ISNOTSET

            //BASE
                @Test
                public void test_isNotSet_null_true()throws Exception {
                    boolean expected = true ;
                    boolean actual = Miscellaneous.isNotSet(arrDblEmpty1_0);
                    Assert.assertEquals(expected, actual);
                
                }
                @Test
                public void test_isNotSet_empty_true()throws Exception {
                    boolean expected = true ;
                    boolean actual = Miscellaneous.isNotSet(arrDblEmpty2_0);
                    Assert.assertEquals(expected, actual);
                
                }

            //DBL
                @Test
                public void test_isNotSet_Dblzeros_false()throws Exception {
                    boolean expected = false ;
                    boolean actual = Miscellaneous.isNotSet(arrDblZeros1_3);
                    Assert.assertEquals(expected, actual);
                
                }

        //SERIES 

                    //DBL
                    @Test
                    public void test_seriesArrDbl()throws Exception {
                        double [] expected = arraDblSeries1_3 ;
                        double [] actual = Miscellaneous.seriesArrDbl(sizeArray);
                        boolean isEqual = Arrays.equals(expected,actual);
                        Assert.assertTrue(isEqual);
                    
                    }

                    @Test
                    public void test_zerosArrDbl()throws Exception {
                        double [] expected = arrDblZeros1_3 ;
                        double [] actual = Miscellaneous.zerosArrDbl(sizeArray);
                        boolean isEqual = Arrays.equals(expected,actual);
                        Assert.assertTrue(isEqual);
                        
                    }
            //CPY 

                //DBL
                @Test
                public void test_cpyArr_Dbl_nullToCpy()throws Exception {
                    double [] expected = arraDblSeries1_3 ;
                    double [] actual = Miscellaneous.cpyArr(arrDblEmpty1_0,arraDblSeries1_3);
                    boolean isEqual = Arrays.equals(expected,actual);
                    Assert.assertTrue(isEqual);
                
                }
                @Test
                public void test_cpyArr_Dbl_zerosArrToCpy()throws Exception {
                    double [] expected = arraDblSeries1_3 ;
                    double [] actual = Miscellaneous.cpyArr(arrDblZeros1_3,arraDblSeries1_3);
                    boolean isEqual = Arrays.equals(expected,actual);
                    Assert.assertTrue(isEqual);
                
                }

    //STAT 

        //EMPTY 

            //BASE 
                @Test
                public void test_emptyStatArr_null_null()throws Exception {
                    double [] expected = arrDblZeros1_3 ;
                    double [] actual = Miscellaneous.emptyStatArr(arrDblEmpty1_0 , sizeArray);
                    boolean isEqual = Arrays.equals(expected,actual);
                    Assert.assertTrue(isEqual);
                
                }
            
            //DBL
                @Test
                public void test_emptyStatArr_Dbl_zeros_zeros()throws Exception {
                    double [] expected = arrDblZeros1_3 ;
                    double [] actual = Miscellaneous.emptyStatArr(arrDblZeros1_3, sizeArray);
                    boolean isEqual = Arrays.equals(expected,actual);
                    Assert.assertTrue(isEqual);
                
                }

                @Test
                public void test_emptyStatArr_Dbl_series_zeros()throws Exception {
                    double [] expected = arrDblZeros1_3 ;
                    double [] actual = Miscellaneous.emptyStatArr(arraDblSeries1_3, sizeArray);
                    boolean isEqual = Arrays.equals(expected,actual);
                    Assert.assertTrue(isEqual);
                
                }



    




    
}
