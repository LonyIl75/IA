package test;


import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.leekwars.generator.fight.julienStats.util.MySet;

public class testMySet {


    int sizeofInt = 32 ;
    int sizeofLong = 64 ;

    int sizeofInt_less_1 = sizeofInt-1 ;

    ArrayList<Integer> arr_even_less_1 = new ArrayList<Integer>(sizeofInt_less_1);
    ArrayList<Integer> arr_odd_less_1 = new ArrayList<Integer>(sizeofInt_less_1);

    int odd_int , even_int;

    ArrayList<Integer> arr_even_1 = new ArrayList<Integer>(sizeofInt);
    ArrayList<Integer> arr_odd_1 = new ArrayList<Integer>(sizeofInt);
    MySet set_even_less_1 , set_odd_less_1;


    @Before
    public void setUp() throws Exception {
        

        for(int i =0;i<sizeofInt_less_1;i+=2){
            arr_even_less_1.add(i);
        }

        for(int i =1;i<sizeofInt_less_1;i+=2){
            arr_odd_less_1.add(i);
        }
        for(int i =1;i<sizeofInt_less_1;i+=2){
            odd_int = odd_int | (1<<i);
        }

        for(int i =0;i<sizeofInt;i+=2){
            arr_even_1.add(i);
        }

        for(int i =1;i<sizeofInt;i+=2){
            arr_odd_1.add(i);
        }

        set_even_less_1 = new MySet(arr_even_less_1 ,sizeofInt_less_1);
        set_odd_less_1 = new MySet(arr_odd_less_1 ,sizeofInt_less_1);


    }

    
    @Test
    public void sizeof_sizeOfInt_(){
        Assert.assertEquals(sizeofInt, MySet.sizeof("int"));
        Assert.assertEquals(sizeofInt, MySet.sizeof("Integer"));
    }

    @Test
    public void sizeof_sizeOfLong_(){
        Assert.assertEquals(sizeofLong, MySet.sizeof("long"));
    }


    
    @Test
    public void sizeof_sizeOfInvalid_(){
        Assert.assertEquals(0, MySet.sizeof("string"));
    }

    @Test
    public void block_index_(){
        int sz_int =MySet.sizeof("int")  ; 
        Assert.assertEquals(0, MySet.block_index(0));
        Assert.assertEquals(0, MySet.block_index(1));
        Assert.assertEquals(0, MySet.block_index(sizeofInt_less_1));
        Assert.assertEquals(1, MySet.block_index(sz_int));
        Assert.assertEquals(2, MySet.block_index(sz_int*2));
    }

    @Test
    public void column_index_(){
        int sz_int =MySet.sizeof("int")  ; 
        Assert.assertEquals(0, MySet.column_index(0));
        Assert.assertEquals(1, MySet.column_index(1));
        Assert.assertEquals(sizeofInt_less_1, MySet.column_index(sizeofInt_less_1));
        Assert.assertEquals(0, MySet.column_index(sz_int));
        Assert.assertEquals(sz_int-1, MySet.column_index(sz_int*2-1));
    }

    @Test 
    public void df_constructor(){
        MySet s = new MySet(0);
        Assert.assertEquals(0, s.set.length);
        s = new MySet(1);
        Assert.assertEquals(1, s.set.length);
        s = new MySet(32);
        Assert.assertEquals(1, s.set.length);
        s = new MySet(33);
        Assert.assertEquals(2, s.set.length);
    }

    @Test 
    public void arr_constructor_less_1(){
        MySet set_even_less_12 = new MySet(set_even_less_1);
        
        for ( int e : arr_even_less_1){
            Assert.assertTrue(set_even_less_12.isIn(e));
        }
        for ( int e : arr_odd_less_1){
            Assert.assertFalse(set_even_less_12.isIn(e));
        }

        MySet set_odd_less_12 = new MySet(set_odd_less_1);
        
        for ( int e : arr_even_less_1){
            Assert.assertFalse(set_odd_less_12.isIn(e));
        }
        for ( int e : arr_odd_less_1){
            Assert.assertTrue(set_odd_less_12.isIn(e));
        }




    }

    @Test 
    public void arr_eraseBit(){
        MySet set_even_less_12 = new MySet(set_even_less_1);
        
        for ( int e : arr_even_less_1){
            Assert.assertTrue(set_even_less_12.isIn(e));
        }

        for ( int e : arr_even_less_1){
            set_even_less_12.eraseBit(e);
        }


        for ( int e : arr_even_less_1){
            Assert.assertFalse(set_even_less_12.isIn(e)); // = 0 
        }

    }

    @Test 
    public void getOne(){
        MySet one_less_1 = MySet.getOne(sizeofInt_less_1);
        for ( int e : arr_even_less_1){
            Assert.assertTrue(one_less_1.isIn(e));
        }
        for ( int e : arr_odd_less_1){
            Assert.assertTrue(one_less_1.isIn(e));
        }

    }
    @Test
    public void orSet(){

        MySet res = MySet.orSet (set_even_less_1,set_odd_less_1);

        for ( int e : arr_even_less_1){
            Assert.assertTrue(res.isIn(e));
        }
        for ( int e : arr_odd_less_1){
            Assert.assertTrue(res.isIn(e));
        }


    }

    @Test
    public void andSet(){
        MySet res = MySet.andSet (set_even_less_1,set_odd_less_1);

        for ( int e : arr_even_less_1){
            Assert.assertFalse(res.isIn(e));
        }
        for ( int e : arr_odd_less_1){
            Assert.assertFalse(res.isIn(e));
        }
    }


    @Test
    public void xorSet(){
        MySet res = MySet.xorSet (set_even_less_1,set_odd_less_1);

        for ( int e : arr_even_less_1){
            Assert.assertTrue(res.isIn(e));
        }
        for ( int e : arr_odd_less_1){
            Assert.assertTrue(res.isIn(e));
        }
    }
    @Test
    public void equalsSet(){
        MySet set_even_less_1_bis = new MySet(arr_even_less_1 ,sizeofInt_less_1);
        Assert.assertTrue(set_even_less_1_bis.equals(set_even_less_1));

    }
    @Test
    public void opsSet(){
        MySet res_and = MySet.andSet (set_even_less_1,set_odd_less_1);
        MySet res_or = MySet.orSet (set_even_less_1,set_odd_less_1);
        MySet res_xor = MySet.xorSet (set_even_less_1,set_odd_less_1);

        MySet res_and_bis = new MySet(set_even_less_1);
        MySet res_or_bis = new MySet(set_even_less_1);
        MySet res_xor_bis = new MySet(set_even_less_1);

        res_and_bis.andSet(set_odd_less_1);
        res_or_bis.orSet(set_odd_less_1);
        res_xor_bis.xorSet(set_odd_less_1);

        Assert.assertTrue(res_and_bis.equals(res_and));
        Assert.assertTrue(res_or_bis.equals(res_or));
        Assert.assertTrue(res_xor_bis.equals(res_xor));


    }


    @Test 
    public void addElement(){
        MySet s = new MySet(sizeofInt);
        Assert.assertEquals(0,s.AddElement(0));
        Assert.assertEquals( 0, s.AddElement(sizeofInt-1));
        Assert.assertEquals(1,s.AddElement(sizeofInt));

    }

    @Test
    public void getBitSet(){
        ArrayList<Integer> arr_even_1_test = set_even_less_1.getBitsSet( );
        
        for ( int e : arr_even_less_1){
            Assert.assertTrue(arr_even_1_test.contains(e));
        }
        ArrayList<Integer> arr_odd_1_test = set_odd_less_1.getBitsSet( );

        for ( int e : arr_odd_less_1){
            Assert.assertTrue(arr_odd_1_test.contains(e));
        }

    }





}
