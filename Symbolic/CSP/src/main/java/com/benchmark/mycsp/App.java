package com.benchmark.mycsp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;

import org.apache.commons.math3.distribution.BinomialDistribution;
//import org.apache.commons.math3.random.GaussianRandomGenerator;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;


/**
 * Hello world!
 *
 */

public class App 
{
  
	public static void main( String[] args )
    {

    	if(args.length < 3 ) {
    		System.out.println("usage : timeLimit nodeLimit [strategy] ");
    		System.out.println("strategy ::  0 :fullyRandom 1: GreedyBranching + FullyRandom 2: IntStrategy 3: GreedyBranching + IntVarSearch. Defaut: IntVarSearch + FirstFail");
    		return;
    		
    	}
    	
    	 int nbVar,nbDom ,nbRelations , nbTuples ,nbSamples ;
    	 
         nbVar = 20;
         nbDom = 10;
         nbSamples=10;
        String current_dir = System.getProperty("user.dir");
        String dirTestName = new String(current_dir+File.separator+"samples"+File.separator+"Sample_var_" + nbVar + "_dom" + nbDom) ;
        String legende = new String ( "timeLimit;nodeLimit;nbVar;nbDom;nbSamples;p1;nbConstraint;p2;nbTuples;numeroReseau;nbSolution;resTime;nbDevNode;nbBacktrack;nbBackJump;nbFails;nbRestart;maxDepth");
        File fptr_dirTestName = new File(dirTestName);
        if (!fptr_dirTestName.exists()){
        	fptr_dirTestName.mkdirs();
        	System.out.println( "cmd3");
        }
        double p1 ,p2;
        p1=0.1;
        p2=0.1;
        double step_p1,step_p2;
        step_p1=0.1;
        step_p2=0.01;
        String subNamecsv = new String(args[0].substring(0,args[0].length()-1)+";"+args[1]+";"+nbVar+";"+nbDom+";"+nbSamples+";");
       
        int tailleFixedParametre =subNamecsv.length();
        ArrayList<Integer> tailleVarParametre = new ArrayList<Integer>();//{{add(tailleFixedParametre);}};
        int index =0; 
        tailleVarParametre.add(index,tailleFixedParametre);
        
        ArrayList<String> csv_tab =  new ArrayList<String>();
        for (;p1<=1;p1+=step_p1) {
        	index=1;
        	subNamecsv=subNamecsv.substring(0,tailleVarParametre.get(index-1));
        	RandomGenerator generator =  new MersenneTwister();
        	
        	BinomialDistribution bin_p1 = new BinomialDistribution(generator,(nbVar*(nbVar-1))/2, p1);
        	
        	nbRelations = bin_p1.sample();
        			
        	if(nbRelations==0)nbRelations++;
        	subNamecsv+=String.format("%.3f;%d;",p1,nbRelations);
       
        	tailleVarParametre.add (index,subNamecsv.length());
        	csv_tab = new ArrayList<String>();//****************** ICI  ***********************
        	
        	String testSubdirName = new String(File.separator+"R" + nbRelations + File.separator);
        	for(p2=0.01;p2< 1;p2+=step_p2) {
        	index=2;
        	subNamecsv=subNamecsv.substring(0,tailleVarParametre.get(index-1));
            BinomialDistribution bin_p2 = new BinomialDistribution(generator,nbDom*nbDom-1, 1-p2);
            
            nbTuples =bin_p2.sample();
           
            if(nbTuples==0)nbTuples++;
           
            subNamecsv+=String.format("%.3f;%d;",p2,nbTuples);
      	  File fptr_testSubdir = new File(dirTestName+testSubdirName);
      	  if (!fptr_testSubdir.exists()){
      		fptr_testSubdir.mkdirs();
          }
         
      	  
         
           tailleVarParametre.add(index,subNamecsv.length());
           
            for (int i = 1; i <= nbSamples; i++) {
            	 index=3;
            	 subNamecsv=subNamecsv.substring(0,tailleVarParametre.get(index-1));
            	subNamecsv+=new String(i+";");
            	String filename = new String( dirTestName+testSubdirName +"numero_" + i+"_nbTuples_"+nbTuples+ ".txt");
            	
            	 File fptr_fileSample = new File(filename);
             	  try {
						fptr_fileSample.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println( "cmd2");
					}
             	  
                 String[] bius={"-ficName",filename ,"-timeLimit", args[0],"-nodeLimit" , args[1] ,"-strategy", args[2]};//"-index",""+i,"-ordonnee",""+nbTuples };
	             
	             		try {
							new ProcessBuilder(current_dir +File.separator+"script"+File.separator+"urbcsp-32wi ",""+nbVar , ""+nbDom, ""+nbRelations ,""+nbTuples ,""+generator.nextLong(),"+1" )
							    		.redirectOutput(fptr_fileSample)
							    		.start()
							    		.waitFor();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							System.out.println( "cmd");
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        	
				
	             	
                 String tmp_stre= new Expe().execute_bis(bius);
                 //tmp_stre=tmp_stre.substring(2);
                 subNamecsv+=tmp_stre;
                 tailleVarParametre.add(index,subNamecsv.length());
                 csv_tab.add(subNamecsv);
	             	
                
            }
           
            
        }
        	String output_fileb = new String( dirTestName+testSubdirName+"output.txt");
        	
        	try (BufferedWriter writer = new BufferedWriter(new FileWriter(output_fileb, true))){
        		writer.write(legende);
    			writer.newLine();
        		  for( String csv_elem : csv_tab) {
        			writer.write(csv_elem);
        			writer.newLine();
        		}
        	}
        		catch(IOException ex){
        		  ex.printStackTrace();
        		}
        }
      
      
    }

	
}
