package com.leekwars.generator.fight.julienStats.SuperLeekwars.Map;

import java.lang.reflect.Method;
import java.util.ArrayList;

import com.leekwars.generator.fight.julienStats.lazyComputing.LazyFunction;
import com.leekwars.generator.fight.julienStats.old.Ask;
import com.leekwars.generator.fight.julienStats.util.Miscellaneous;

public class myCell implements Ask {

    int [] cell ;

    public static String getClassNameCell(){
        return "Cell";
    }

    @Override
    public LazyFunction geAttribut(String attribut) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'geAttribut'");
    }

   /*  abstract class LazyCell  extends  LazyFunction<enumCell , myCell> {
        @Override
        public void update(Object obj) {
           
        }
    }
    @Override 
    public LazyFunction geAttribut(String attribut){

            if( attribut.equals("x") ){
                return new LazyCell () {
                    @Override 
                    public double exec(Object obj) {
                        return ((myCell)obj).getX();
                    }
                };
            }
            else if (attribut.equals("y")){
                return new LazyCell () {
                    @Override 
                    public double exec(Object obj) {
                        return ((myCell)obj).getY();
                    }
                };
            }
            else{
                return new LazyCell (){
                    @Override 
                    public double exec(Object obj) {
                        return Double.NaN;
                    }
                };
                
            }

    }*/

    public myCell(int x , int y){
        this.cell = initCell();
        this.cell[0]=x;
        this.cell[1]=y;
    }
    public myCell(int[] cell){
        this.cell = Miscellaneous.cpyArr(this.cell , cell);
    }
    public myCell(){
        this.cell = getDefaultCell();
    }
    public int[] getDefaultCell(){
        int[] cell  = initCell();
        cell[0]=0;
        cell[1]=0;
        return cell;
    }
    public static int[] initCell(){
        int[] cell  = new int[2];
        return cell;
    }


    public static int getSizeCell(){
        return 2;
    }
    public int getX(){
        return cell[0];
    }
    public  int getY(){
        return cell[1];
    }

    public void setX( int x){
        cell[0]=x;
    }
    public void setY( int y){
        cell[1]=y;
    }

   

    
    
}


