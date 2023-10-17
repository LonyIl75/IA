
package com.leekwars.generator.fight.julienBdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leekwars.generator.Util;
import com.leekwars.generator.attack.Attack;
import com.leekwars.generator.attack.EffectParameters;
import com.leekwars.generator.attack.weapons.Weapon;
import com.leekwars.generator.attack.weapons.Weapons;
import com.leekwars.generator.fight.julienStats.SuperLeekwars.Item.WeaponsUtil;
import com.leekwars.generator.attack.effect.Effect;
import com.leekwars.generator.maps.MaskAreaCell;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.Math;


import com.leekwars.generator.maps.Cell;
import com.leekwars.generator.attack.area.*;


import java.util.function.Predicate;

public class weaponPattern {

    int idItemWeapLS3 , idItemWeapLS4 ;
    public static boolean debug_mode =false;
    public Weapon wp ;

    
    public static final int size_pattern = 31;
    int []  degatMap= weaponPattern.init_pattern(); // tableaux binaires de 32 x32  suffisant pour stocker les zones de degats des armes et leur AOE , le max sur leekwars étant range =12 soit 24 x24 

    int []  degatMapAOE= weaponPattern.init_pattern(); //  tableaux binaires de 32 x32 
    int maxRange ;

    boolean isLS3 = true;

    boolean getIsLS3(){
        return isLS3; 
    }



    public weaponPattern(int idItemWeapLS3, int idItemWeapLS4 )throws Exception {
        this(idItemWeapLS3 , idItemWeapLS4 , WeaponsUtil.provider_weaponsUtil);
    }

    static String path = "./data";

    public static String getClassName(){
        return weaponPattern.class.getSimpleName();
    }

    public static String getFilePath(){
        return path+File.separator+getClassName()+".json";
    }


    public JSONObject getJSON(){
        


        JSONObject json2 = new JSONObject();

        json2.put("degatMap", Arrays.toString(getDegatMap() ));
        json2.put("degatMapAOE", Arrays.toString(getDegatMapAOE()));
        json2.put("maxRange", getMaxRange());
        //json2.put("id", getIdArme() );

        return json2;
            
  



    }


    


    public weaponPattern(int idItemWeapLS3, int idItemWeapLS4 ,  WeaponsUtil init) throws Exception {
        this.idItemWeapLS3 = idItemWeapLS3;
        this.idItemWeapLS4 = idItemWeapLS4;
        this.wp = WeaponsUtil.provider_weaponsUtil.getItem(this.getIdArme());
        this.maxRange = this.getRangeOfArea()+this.wp.getAttack().getMaxRange();
        initMaps();
    }
    public int getMaxRange(){
        return maxRange;
    }

    public int getRangeOfArea() {

    

        byte type = (byte)this.wp.getAttack().getArea(); 
        if( type ==Area.TYPE_SINGLE_CELL )
            return 0 ;
        else if (type == Area.TYPE_CIRCLE1 || type == Area.TYPE_AREA_PLUS_1)
            return 1;
        else if (type == Area.TYPE_CIRCLE2)
            return 2;
        else if (type == Area.TYPE_CIRCLE3)
            return 3;
        else if (type == Area.TYPE_AREA_PLUS_2)
            return 2;
        else if (type == Area.TYPE_AREA_PLUS_3)
            return 3;
        else if (type == Area.TYPE_X_1)
            return 1;
        else if (type == Area.TYPE_X_2)
            return 2;
        else if (type == Area.TYPE_X_3)
            return 3;
        else if (type == Area.TYPE_SQUARE_1)
            return 1;
        else if (type == Area.TYPE_SQUARE_2)
            return 2;
        else  if (type == Area.TYPE_LASER_LINE)
            return 0; //new AreaLaserLine(attack); //TODO 
    
        return 0;
    }

    public static int getSizePattern(){
        return size_pattern;
    }
    public Weapon getWeapon(){
        return wp;
    }
    public static int[] init_pattern( ){
        return new int[getSizePattern()];
    }

    public static int[][] init_pattern2D( ){
        return new int[getSizePattern()][getSizePattern()];
    }

    public int getIdItemWeapLS3() {
        return idItemWeapLS3;
    }

    public int getIdItemWeapLS4() {
        return idItemWeapLS4;
    }

    public int getIdArme(){
        return  getIsLS3()?getIdItemWeapLS3():getIdItemWeapLS4();
    }

    public int[] getDegatMap() {
        return degatMap;
    }


    public int[] getDegatMapAOE() {
        return degatMapAOE;
    }

    public void initMaps () throws Exception {
        

        this.degatMap=init_degatMap(this.wp.getAttack());
        this.degatMapAOE=init_degatMapAOE(this.wp.getAttack());


    }
public static String toStringArrArr(int[][] arr){
    System.out.println("toString ");
    String res = "";
    for (int i =0 ; i<arr.length ; i++){
        for (int j =0 ; j<arr[i].length ; j++){
            res+=arr[i][j]+" ";
        }
    }
    res+=";";
    return res;
    }
    public static String toStringArr(int[] arr){
        System.out.println("toString ");
        String res = "";
        for (int i =0 ; i<arr.length ; i++){
                res+=arr[i]+" ";
        }
        res+=";";
        return res;
    }
    public static String toStringBinary(int nb){
        String res = "";
        String tmp = Integer.toBinaryString(nb);
        for (int j =0 ; j<getSizePattern()-tmp.length() ; j++){
            res+="0";
        }
        res+=(tmp);
        return res;
    }
    public static String toStringBinArr(int[] arr){
        System.out.println("toString ");
        String res = "";
        String tmp = "";
        for (int i =0 ; i<arr.length ; i++){
            tmp=Integer.toBinaryString(arr[i])+" ";
            for (int j =0 ; j<getSizePattern()-tmp.length() ; j++){
                res+="0";
            }
            res+=(tmp+"\n");
        }
        return res;

    }

        public static int setBitAtIndex(int nb, int index){
            int res = nb | (1 << (index-1));
            return res;
        }

        public static int unSetBitAtIndex(int nb, int index){
            int res = nb & (~(1 << (index-1)));
            return res;
        }

        public static int indexArrToindexBinArr(int index){
            if(index<0){
                index=getSizePattern()/2+index;
            }
            else{
                index=getSizePattern()/2+index;
            }
            return index<getSizePattern() ? index : -1;
        }
        public static int setBitAtIndexFromCenter(int nb, int index){
            return setBitAtIndex(nb,index);
        }


        
public static int[] coordArrToBinaryArr(int[][] _mask){

    //System.out.println("coordArr :"+toStringArrArr(_mask));
    int [] bin_arr = init_pattern( );
    int x , y;
    for (int i =0 ; i<_mask.length ; i++){
            x=indexArrToindexBinArr(_mask[i][0]);
            y=indexArrToindexBinArr(_mask[i][1]);
            bin_arr[x]=setBitAtIndexFromCenter(bin_arr[x],y);

    }
    //System.out.println("Bin_arr : "+toStringBinArr(bin_arr));
    return bin_arr;//TODO 

}
/* 

        public static int indexArrToindexBinArr(int index,int center){
            if(index<0){
                index=getSizePattern()/2+index+center;
            }
            else{
                index=getSizePattern()/2+index+center;
            }
            if(index >getSizePattern()-1){
                System.out.println("erreur index trop grand "+  index);
                return -1;
            }
            return index;
        }

public static int[] coordArrToBinaryArr( List<Cell> _mask,int center ){

    System.out.println("coordArr :"+_mask.toString());
    int [] bin_arr = init_pattern( );
    int x , y;
    for (int i =0 ; i< _mask.size() ; i++){
            x=indexArrToindexBinArr(_mask.get(i).getX(),center);
            y=indexArrToindexBinArr(_mask.get(i).getY(),center);
            bin_arr[x]=setBitAtIndexFromCenter(bin_arr[x],y);

    }
    //System.out.println("Bin_arr : "+toStringBinArr(bin_arr));
    return bin_arr;//TODO 

}*/


public static int[] _deleteCenterMaskLine( int [] notclean_line,int radius_todelete_center){
    int x ,y ;
    for (int j=0; j< radius_todelete_center ; j++){
        y=indexArrToindexBinArr(0);
        x=indexArrToindexBinArr(-j);
        notclean_line[x]=unSetBitAtIndex(notclean_line[x],y);
        x=indexArrToindexBinArr(j);
        notclean_line[x]=unSetBitAtIndex(notclean_line[x],y);
        
        x=indexArrToindexBinArr(0);
        y=indexArrToindexBinArr(-j);
        notclean_line[x]=unSetBitAtIndex(notclean_line[x],y);
        y=indexArrToindexBinArr(j);
        notclean_line[x]=unSetBitAtIndex(notclean_line[x],y);

    }
    return notclean_line;
    
}

public static int[] _deleteCenterMaskDiagonal( int [] notclean_diag,int radius_todelete_center){
    int x ,y ;
    for (int j=0; j< radius_todelete_center ; j++){
        x=indexArrToindexBinArr(-j);
        y=indexArrToindexBinArr(-j);
        notclean_diag[x]=unSetBitAtIndex(notclean_diag[x],y);
        x=indexArrToindexBinArr(j);
        notclean_diag[x]=unSetBitAtIndex(notclean_diag[x],y);
        y=indexArrToindexBinArr(j);
        notclean_diag[x]=unSetBitAtIndex(notclean_diag[x],y);
        x=indexArrToindexBinArr(-j);
        notclean_diag[x]=unSetBitAtIndex(notclean_diag[x],y);
    }
    return notclean_diag;
    
}





    public static int[] _launchLine(Attack attack) {
        int [] notclean_line = init_pattern( );
        if(debug_mode)System.out.println("Launch Line : maxRange : "+attack.getMaxRange()+" minRange : "+attack.getMinRange());
        notclean_line = coordArrToBinaryArr(MaskAreaCell.generatePlusMask(attack.getMaxRange()));
        if(debug_mode)System.out.println("Bin_arr  avant Delete : "+toStringBinArr(notclean_line));
        int [] clean_line = _deleteCenterMaskLine(notclean_line,attack.getMinRange()); //(tmp_res,Attack.LAUNCH_TYPE_LINE,attack.getMinRange());
        if(debug_mode)System.out.println("Bin_arr  apres Delete: "+toStringBinArr(clean_line));
        return clean_line;
    }

    public static int[] _launchDiagonal(Attack attack) {
        int [] notclean_diag = init_pattern( );
        if(debug_mode)System.out.println("Launch Diagonal : maxRange : "+attack.getMaxRange()+" minRange : "+attack.getMinRange()/2);
        notclean_diag = coordArrToBinaryArr(MaskAreaCell.generateXMask(attack.getMaxRange()/2));
        if(debug_mode)System.out.println("Bin_arr  avant Delete : "+toStringBinArr(notclean_diag));
        int [] clean_diag = _deleteCenterMaskDiagonal(notclean_diag,attack.getMinRange()/2); 
        if(debug_mode)System.out.println("Bin_arr apres Delete: "+toStringBinArr(clean_diag));
        return clean_diag;
    }

    public static int[] addMask(int [] mask1 , int[] mask2){
        int [] res = init_pattern( );
        for (int i =0 ; i<getSizePattern() ; i++){
            res[i]=mask1[i]|mask2[i];
        }
        return res;
    }
    public static int[] addPattern(int [] pattern1 , int[] pattern2){
        return addMask(pattern1,pattern2);
    }

    public static int[] _launchStar(Attack attack) {
        if(debug_mode)System.out.println("Launch Star : maxRange : "+attack.getMaxRange()+" minRange : "+attack.getMinRange());
        int [] clean_line = init_pattern( );
        clean_line=  _launchLine(attack);
       // int [][] res = _deleteCenterMask(tmp_res,attack.attack.getMinRange()); 
        int [] clean_diag = init_pattern( );
        clean_diag= _launchDiagonal(attack);
        //res = _deleteCenterMask(tmp_res,attack.attack.getMinRange()); //enleve (attack.getMinRange()-1)/3 sur diagonale sans retenu 8-1 =7 => 7/3 = 2    
        int []clean_star=  init_pattern( );
        clean_star = addPattern(clean_line,clean_diag);
        if(debug_mode)System.out.println("Bin_arr : "+toStringBinArr(clean_star));
        return clean_star;
    }

    public static int[] negateBinaryArr(int [] mask){
        int [] res = init_pattern( );
        for (int i =0 ; i<getSizePattern() ; i++){
            res[i]=~mask[i];
        }
        return res;
    }

    public static int[] negatePattern(int [] pattern){
        return negateBinaryArr(pattern);
    }

    public static int[] _launchStarInverted(Attack attack) {
        int [] clean_star = init_pattern( );
        clean_star =_launchStar(attack);
        int [] clean_starInverted = negatePattern(clean_star);
        return clean_starInverted ;

    }

    public static int[] _launchDiagonalInverted(Attack attack) {
        int [] clean_diag = init_pattern( );
        clean_diag=_launchDiagonal(attack);
        int [] clean_diagInverted = negatePattern(clean_diag);
        return clean_diagInverted ;
    }

    public static int[] _launchLineInverted(Attack attack) {
        int [] clean_line = init_pattern( );
        clean_line = _launchLine(attack);
        int [] clean_lineInverted = negatePattern(clean_line);
        return  clean_lineInverted ;
    }
    public static int[] _launchCircle(Attack attack) {
        int []  notclean_circle = init_pattern( );
        if(debug_mode)System.out.println("Launch Circle : maxRange : "+attack.getMaxRange()+" minRange : "+attack.getMinRange());
        notclean_circle = coordArrToBinaryArr(MaskAreaCell.generateCircleMask(attack.getMinRange(),attack.getMaxRange()) );
        int [] clean_circle =notclean_circle; //int [] res = _deleteCenterMaskDiagonal(tmp_res,attack.getMinRange()); 
        if(debug_mode)System.out.println("Bin_arr : "+toStringBinArr(clean_circle));
        return clean_circle;
    }



    public static int[] getLaunchArea( Attack attack ){

    byte launchType = attack.getLaunchType() ;
    if (launchType == Attack.LAUNCH_TYPE_LINE)
        return _launchLine(attack);
    else if (launchType == Attack.LAUNCH_TYPE_DIAGONAL)
        return _launchDiagonal(attack);
    else if (launchType ==Attack.LAUNCH_TYPE_STAR )
        return _launchStar(attack);
    else if (launchType == Attack.LAUNCH_TYPE_STAR_INVERTED)
        return _launchStarInverted(attack);
    else if (launchType == Attack.LAUNCH_TYPE_DIAGONAL_INVERTED)
        return _launchDiagonalInverted(attack);
    else if (launchType == Attack.LAUNCH_TYPE_LINE_INVERTED)
        return _launchLineInverted(attack);
    else if (launchType == Attack.LAUNCH_TYPE_CIRCLE)
        return _launchCircle(attack);
    return null;
}

public static int getCoordCentre(){
    return getSizePattern()/2;
}



public static int[] getPatternCenterAt2(int[] notcenter , int centrex , int centrey){ //TODO comment 
    int [] res = init_pattern( );
    //int i =-centrex ;
    int x =0;
    int shift_vert , shift_hz;

    shift_hz=centrey-getCoordCentre();
    shift_vert= centrex-getCoordCentre() ;
   
    boolean vert , hz;
    if(centrex==getCoordCentre() && centrey==getCoordCentre()) return notcenter;
    vert = (centrex<getCoordCentre()); // commence a la shift_vert row de notcenter si true alors commence la copie a partir de 0 row de not center  sinon a partir de la centrex-getSizePattern()/2 row de notcenter 
    hz = (centrey<getCoordCentre()); // si true alors  shift << hz si false alors >> hz 
    /* 
    int i = (vert?0:shift_vert);
    int j = (vert?-shift_vert:0);
    for (int k = 0; (vert?k+j:i+k)<getSizePattern() ; k++){
        if (hz){
                res[k+j]=(notcenter[i+k]<<shift_hz); // au dessous en partant de 0 et au dessus jusqu'a centerx 
            }else{
                res[k+j]=(notcenter[i+k]>>shift_hz); // au dessous en partant de 0 et au dessus jusqu'a centerx 
            }
        }
    */
    //System.out.println("BIN ARR " + toStringBinArr(notcenter));
    
    if( vert){
        int i = 0;
        int j = -shift_vert;
        if (hz){
            for (int k = 0; k+j<getSizePattern() ; k++){
                res[k+j]=(notcenter[i+k]<<shift_hz); // au dessous en partant de 0 et au dessus jusqu'a centerx 
            }
        }  
        else{
            for (int k = 0; k+j<getSizePattern() ; k++){
                res[k+j]=(notcenter[i+k]>>-shift_hz); // au dessous en partant de 0 et au dessus jusqu'a centerx 
            }
        }

    }else{
        int i = shift_vert;
        int j = 0;
     
        if (hz){
            for (int k = 0; i+k<getSizePattern() ; k++){
                res[k+j]=(notcenter[i+k]<<shift_hz); // au dessous en partant de 0 et au dessus jusqu'a centerx 
            }
        }  
        else{
            for (int k = 0; i+k<getSizePattern() ; k++){
                res[k+j]=(notcenter[i+k]>>-shift_hz); // au dessous en partant de 0 et au dessus jusqu'a centerx 
            }
        }

    }
    
    /*for (; i<centrex ; i++){
            x=indexArrToindexBinArr(i);
            res[i+centrex]=(notcenter[x]<<(getSizePattern()/2-centrey)); // au dessous en partant de 0 et au dessus jusqu'a centerx 
    
        }else{

            for (i=centrex-getSizePattern()/2; i<centrex ; i++){
                x=indexArrToindexBinArr(i-centrex);
                res[i]=(notcenter[x]>>i); // au dessous en partant de  
            }

            for (i=centrex; i<getSizePattern() ; i++){
            x=indexArrToindexBinArr(i-centrex);
            res[i]=(notcenter[x]>>(centrey-getSizePattern()/2)); // au dessous en partant de 
        }

    }
    for (; i<getSizePattern() ; i++){
        res[i]=(notcenter[i]>>centrey);   
    }*/

    return res;
}

public static int[][] _getArea(Attack attack, byte type) {
  
    if( type ==Area.TYPE_SINGLE_CELL )
        return getNeutreAOE2D() ;
    else if (type == Area.TYPE_CIRCLE1 || type == Area.TYPE_AREA_PLUS_1)
        return MaskAreaCell.generateCircleMask(0, 1);
    else if (type == Area.TYPE_CIRCLE2)
        return MaskAreaCell.generateCircleMask(0, 2);
    else if (type == Area.TYPE_CIRCLE3)
        return MaskAreaCell.generateCircleMask(0, 3);
    else if (type == Area.TYPE_AREA_PLUS_2)
        return MaskAreaCell.generatePlusMask(2);
    else if (type == Area.TYPE_AREA_PLUS_3)
        return MaskAreaCell.generatePlusMask(3);
    else if (type == Area.TYPE_X_1)
        return MaskAreaCell.generateXMask(1);
    else if (type == Area.TYPE_X_2)
        return MaskAreaCell.generateXMask(2);
    else if (type == Area.TYPE_X_3)
        return MaskAreaCell.generateXMask(3);
    else if (type == Area.TYPE_SQUARE_1)
        return MaskAreaCell.generateSquareMask(1);
    else if (type == Area.TYPE_SQUARE_2)
        return MaskAreaCell.generateSquareMask(2);
    else  if (type == Area.TYPE_LASER_LINE)
        return null ; //new AreaLaserLine(attack); //TODO 

    return null;
}


public static int[] getAOEArea( Attack attack ){
    //Area launchArea = Area.getArea(attack,(byte) attack.getArea());
    //List<Cell> cells_area = launchArea._getArea();
    byte area_type = (byte)attack.getArea(); 
    int [][] area = init_pattern2D();
    if( area_type != Area.TYPE_LASER_LINE )area = _getArea(attack,  area_type);
    int [] res = null;
    if( area_type != Area.TYPE_LASER_LINE )res = coordArrToBinaryArr(area);
    //else res = _launchLine(attack);
    return res;

}


    /* 
    byte area_type = (byte)attack.getArea(); 
    int [][] area = init_pattern2D();
    if( area_type != Area.TYPE_LASER_LINE )area = _getArea(attack,  area_type);
    int [] res = null;
    if( area_type != Area.TYPE_LASER_LINE )res = coordArrToBinaryArr(area,centre);
    //else res = _launchLine(attack);
    return res;
    */
public static int[] getAOECenterArea( Attack attack , int centrex , int centrey){

    int[] notcenter = getAOEArea(attack);
    int[] center = null;
    if(notcenter!=null)center=getPatternCenterAt(notcenter , centrex,centrey);
    return center;


}





    public static int[] init_degatMap( Attack _attack )  {
      

        int[] degatMap = new int[32];
        degatMap = getLaunchArea(_attack);

   

        

        return degatMap;



    }

    public static boolean isSet(int nb , int pos ){
        //System.out.println("Is set " + pos +"bit in :"+ toStringBinary(nb)+" answer : "+ ((nb & (1 << pos)) != 0) + "");

        return (nb & (1 << pos)) != 0;
    }

    public static int  getBit(int nb , int pos ){
 
        return (int)(nb & (1 << pos)) ;
    }

    //(nb, pos) -> { return (nb & (1 << pos)) != 0; };


    public static int neighborsSize =4  ;

    public static int getSizeNeighbors(){
        return weaponPattern.neighborsSize;
    }

    
    public static int[][] getNeutreAOE2D(){
        return new int[][]{{0,0}};
    }
    public static int[]getNeutreAOE(){
        return coordArrToBinaryArr(getNeutreAOE2D());
    }
    public static boolean isEqualIntArr(int[] arr1 , int[] arr2){
        if(arr1==null && arr2==null)return true;
        if(arr1==null || arr2==null)return false;
        if(arr1.length!=arr2.length)return false;
        for (int i = 0; i < arr1.length; i++) {
            if(arr1[i]!=arr2[i])return false;
        }
        return true;
    }

    public static boolean isAOENeutre(int[] AOE){
        if(AOE==null)return true;
        if( isEqualIntArr(AOE,getNeutreAOE()) )return true;
        return false;
    }



    public static int[] getPatternCenterAt(int[] center , int centrex , int centrey){

        int [] res = init_pattern( );
        //int i =-centrex ;
        int i,j;
        int shift_vert , shift_hz;
    
        shift_hz=getCoordCentre()-centrey;
        shift_vert= getCoordCentre()-centrex ;
       
        boolean vert , hz;
        if(centrex==getCoordCentre() && centrey==getCoordCentre()) return center;
        vert = (centrex<getCoordCentre()); //add 
        hz = (centrey<getCoordCentre()); 
    
        if(vert){
            j=0;
            i=shift_vert;  
                for (int k = 0; k+i<getSizePattern() ; k++){
                    if (hz){
                    res[k+j]=(center[i+k]>>shift_hz); // au dessous en partant de 0 et au dessus jusqu'a centerx 
                }
                else{
                    res[k+j]=(center[i+k]<<-shift_hz);
                }
            }
    
        }
        else{
            j=-shift_vert;
            i=0;
            for (int k = 0; k+j<getSizePattern() ; k++){
                if (hz){
                    res[k+j]=(center[i+k]>>shift_hz); // au dessous en partant de 0 et au dessus jusqu'a centerx 
                }
                else{
                    res[k+j]=(center[i+k]<<-shift_hz);
                }
            }
        }
    
        //System.out.println("BIN ARR " + toStringBinArr(center)+"\n\n\n");
        //System.out.println("BIN ARR " + toStringBinArr(res));
        return res ;
    
    
    }
    

    public static List<Integer> bitsWherePred(int nb ){
        List<Integer> res = new ArrayList<>();
        
            for (int i = 0; i < getSizePattern(); i++) {
                if(isSet( nb , i)){
                    res.add(i);
                }
            }
            return res ;
            }


    public static int [][] areaToBinIndexs ( Attack attack ,byte type  ){
        int[][] _mask =  _getArea(attack,  type );
        int [][] res = new int [_mask.length][2];
        for (int i =0 ; i<_mask.length ; i++){
            res[i][0]=indexArrToindexBinArr(_mask[i][0]);
            res[i][1]=indexArrToindexBinArr(_mask[i][1]);
        }
        return res;
    
    }
    
    public static int[][] shiftIndexs(int[][] _mask , int cy ,int cx ){
        int [][] res = new int [_mask.length][2];
        for (int i =0 ; i<_mask.length ; i++){
            res[i][0]=_mask[i][0]+cx;
            res[i][1]=_mask[i][1]+cy;
        }
        return res;
    
    }


    public static boolean unsetInNeighbors(int[] nb , int[][] _neighbors  ){
        int x , y ;
        //System.out.println("BIN: " +toStringBinArr(nb));
        for (int i =0 ; i<_neighbors.length ; i++){
            y=_neighbors[i][1];
            x=_neighbors[i][0];
            if(x!=-1  && y!=-1 && !isSet( nb[x] , y)){
                //System.out.println("BIN2: " +toStringBinary(nb[x]));
                return true;
            }
        }
        return false;

}



        public static List<Integer> bitsWherePred(  int[] nb ,  Attack attack ,byte type  ,int i ){

            int[][] _mask =  areaToBinIndexs( attack,  type );
            List<Integer> res = new ArrayList<>();;//int[][] res = init_pattern2D();
            int [][] already_satisfied = init_pattern2D();
            int [][] toSat = new int[getSizePattern()][2];
            //for (int i = 0; i < getSizePattern(); i++) {
                for (int j = 0; j < getSizePattern(); j++) {
                   if(isSet( nb[i] , j)){
                    /*if(i==20){
                        System.out.println("kl");
                    }*/
                    toSat=shiftIndexs( _mask ,getCoordCentre()-i,getCoordCentre()-j );
                        if(unsetInNeighbors(nb, toSat)){
                            res.add(j);
                        }
                    }
            //   }
            }


            return res;
    }


    public static int[] init_degatMapAOE( Attack _attack ) {

        
        int[] _degatMap = new int[32];
        int[] AOE = getAOEArea(_attack);
        int[]tmp_AOE = new int[32];
        int x , y ;
        List<Integer> idx_bits = new ArrayList<>();
        _degatMap = getLaunchArea(_attack);

        int[] res = Arrays.copyOf(_degatMap, _degatMap.length);

        if(debug_mode)System.out.println("Bin_arr DEB  :  \n"+toStringBinArr(_degatMap));

        if(isAOENeutre(AOE))return _degatMap;
        for (int i = 0; i < getSizePattern(); i++) {
            x=i;
            idx_bits.clear();

                if(i>0 && _degatMap[i-1]==0){
                    idx_bits = bitsWherePred(_degatMap[x] );
                    for (int j = 0; j < idx_bits.size(); j++) {
                        y=idx_bits.get(j);
                        tmp_AOE = getPatternCenterAt(AOE , x,y);
                        res = addPattern(res , tmp_AOE);
                    }
                    //System.out.println("Bin_arr 0 avant : "+toStringBinArr(_degatMap)+"\n\n AOE "+toStringBinArr(tmp_AOE));
                    //pour tous les bits k de degatMap[i] non nul centrer AOE sur ce bit (centrex=i , centrey=k)
                        //pour tous les nombre d'indexe j non nul de AOE les unir avec les nombres de degatMap[j]


                }else if(i < getSizePattern() -1 && _degatMap[i+1]==0){
                    idx_bits = bitsWherePred(_degatMap[x] );
                    for (int j = 0; j < idx_bits.size(); j++) {
                        y=idx_bits.get(j);
                        tmp_AOE = getPatternCenterAt(AOE , x,y);
                        res = addPattern(res , tmp_AOE);
                    }
                    //System.out.println("Bin_arr 0 apres : "+toStringBinArr(_degatMap)+"\n\n AOE "+toStringBinArr(tmp_AOE));
                    //pour tous les bits k de degatMap[i] non nul centrer AOE sur ce bit (centrex=i , centrey=k) 
                        //pour tous les nombre d'indexe j non nul de AOE les unir avec les nombres de degatMap[j]
                }
            else {
                idx_bits = bitsWherePred(_degatMap , _attack ,(byte)_attack.getArea(),  x ); //int[] nb ,  Attack attack ,byte type  ,int i
                for (int j = 0; j < idx_bits.size(); j++) {
                    y=idx_bits.get(j);
                    tmp_AOE = getPatternCenterAt(AOE , x,y);
                    res = addPattern(res , tmp_AOE);
                }
                //System.out.println("Bin_arr 0 dans int : "+toStringBinArr(_degatMap)+"\n\n AOE "+toStringBinArr(tmp_AOE));
                //pour tous les bits k de degatMap[i] non nul dont le bit precedent ou suivant est  nul centrer AOE sur ce bit (centrex=i , centrey=k) 
                    //pour tous les nombre d'indexe j non nul de AOE les unir avec les nombres de degatMap[j]
            }

            
        }
       
        if(debug_mode ) System.out.println("Bin_arr RES: \n"+toStringBinArr(res));
        return res;


    }



    public static int addFilterWeapon(char[][] degatMapIdsArme , int[] mapDegat1 , int posArme){
        int _tmp = 0;
        int nbCellCoverByArme =0;
        for (int i = 0; i < weaponPattern.getSizePattern(); i++) {
            for (int j = 0; j < weaponPattern.getSizePattern(); j++) {
                _tmp= weaponPattern.getBit(mapDegat1[i],j );
                degatMapIdsArme[i][j]+=Math.pow(2,posArme)*_tmp;
                if(_tmp==1)nbCellCoverByArme++;
        }
    }
    return nbCellCoverByArme;
    }

    public static String toVarFilter(int [] map ,String variableName){
        String _var = "int[] "+variableName+" = new int[]{";
        for (int i = 0; i < getSizePattern(); i++) {
            _var+=map[i]+",";
        }
        _var.substring(0, _var.length()-1);
        _var+="};";
        return _var;

    }










    
}
