package com.leekwars.generator.fight.julienStats.util;

import java.util.ArrayList;

public class UtilBin {
    

    public static int clearBit(int row , int indx_col){
        return row & ~(1 << indx_col);
    }

    public static int setBit(int row , int indx_col){
        return row | (1 << indx_col);
    }

  
    public static int log2(int row ){
        return (int)(Math.log(row)/ Math.log(2));//commence a 2^0 => 1
    }
    public static ArrayList<Integer> getBitsSet(int row ){
        ArrayList<Integer> res = new  ArrayList<Integer>();
        int indx = 0;
        while(row!=0 ){
            indx = log2(row );
            res.add(indx);
            row = clearBit(row,indx);
        }
        return res;
    }

    public static boolean testBit(int row , int indx_col){
        return (row & (1 << indx_col)) != 0;
    }

    public static int orBit(int row , int row2 ){
        return row |  row2;
    }

    public static int andBit(int row , int row2 ){
        return row &  row2;
    }

    public static int xorBit(int row , int row2 ){
        return row ^  row2;
    }

    public static int not(int row ){
        int l = ~row;
        return ~row;
    }

}
