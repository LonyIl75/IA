package com.benchmark.mycsp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.constraints.extension.Tuples;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.tools.TimeUtils;
import org.chocosolver.solver.Solver;

import org.chocosolver.solver.trace.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.chocosolver.solver.search.limits.TimeCounter;
import org.chocosolver.solver.search.loop.monitors.IMonitorSolution;
import org.chocosolver.solver.search.measure.MeasuresRecorder;
import org.testng.Assert;

import static org.chocosolver.solver.search.strategy.Search.*;

import org.chocosolver.solver.search.strategy.assignments.DecisionOperator;
import org.chocosolver.solver.search.strategy.selectors.values.*;
import org.chocosolver.solver.search.strategy.selectors.variables.*;
import org.chocosolver.solver.search.strategy.strategy.FullyRandom;
import org.chocosolver.solver.search.strategy.strategy.GreedyBranching;
import org.chocosolver.solver.search.strategy.strategy.IntStrategy;
import org.chocosolver.solver.search.SearchState;


public class Expe   extends AbstractProblem  {

	 IntVar []var;
	
	 
	 @Override
	    public void buildModel() {
		 	BufferedReader in = null;
		 	System.out.println(ficName);
			try {
				in = new BufferedReader(new FileReader(ficName));
			} catch (FileNotFoundException e1) {
				System.out.println("buildModel :" + ficName +"file not found");
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 model = new Model("Expe");
			 csvStat= new String(";");//csv vide
		
			try {
			int nbVariables = Integer.parseInt(in.readLine());
			
			int tailleDom = Integer.parseInt(in.readLine());				// la valeur max des domaines
			var = model.intVarArray("x",nbVariables,0,tailleDom-1);
			int nbConstraints = Integer.parseInt(in.readLine());			// le nombre de contraintes binaires		
			//System.out.println("nbV"+nbVariables +" nbD"+tailleDom +"nbC"+nbConstraints+" ");
			for(int k=1;k<=nbConstraints;k++) { 
				String chaine[] = in.readLine().split(";");
				IntVar portee[] = new IntVar[]{var[Integer.parseInt(chaine[0])],var[Integer.parseInt(chaine[1])]}; 
				int nbTuples = Integer.parseInt(in.readLine());				// le nombre de tuples		
				Tuples tuples = new Tuples(new int[][]{},true);
				for(int nb=1;nb<=nbTuples;nb++) { 
					chaine = in.readLine().split(";");
					int t[] = new int[]{Integer.parseInt(chaine[0]), Integer.parseInt(chaine[1])};
					tuples.add(t);
				}
				model.table(portee,tuples).post();	
			}
			in.readLine();
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}				
	
	}	
		
	 @Override
	    public void configureSearch() {
		 Solver solver = model.getSolver();
		 
		solver.limitTime(timeLimit);
			solver.limitNode(NodeLimit);

		 switch (strategy) {
			case 0:
				solver.setSearch(new FullyRandom(var,seed),intVarSearch(
		                 new FirstFail(model),
		                 new IntDomainMin(),var));
				break;
				
			case 1:
				solver.setSearch(new GreedyBranching(new FullyRandom(var,seed)),intVarSearch(
		                 new FirstFail(model),
		                 new IntDomainMin(),var));
				break;
				
			case 2:
				solver.setSearch(new IntStrategy(var,new FirstFail(model),new IntDomainMin()));
				break;
				
			case 3:
				solver.setSearch(new GreedyBranching(new IntStrategy(var,new FirstFail(model),new IntDomainMin())));
				break;
				
			default:
				solver.setSearch(intVarSearch(
		                 new FirstFail(model),
		                 new IntDomainMin(),var));
				break;
		}
			

			

	    }
	 public float IN_MILLI () {return 1000*1000f;}
			
	 @Override
	    public void solve() {

			
			Solver solver = model.getSolver();
			

			
			solver.solve(); //s arrete quand stop criterion met 

			MeasuresRecorder mMeasures = solver.ref().getMeasures();
			csvStat =String.format(
					"%d;%.3f;%d;%d;%d;%d;%d;%d;",
					(mMeasures.getFailCount()==0?mMeasures.getSolutionCount():0),
					(mMeasures.getFailCount()==0?mMeasures.getTimeCountInNanoSeconds()/ IN_MILLI ():TimeUtils.convertInMilliseconds(timeLimit)),
					(mMeasures.getFailCount()==0?mMeasures.getNodeCount():NodeLimit),
					mMeasures.getBackTrackCount(),
					mMeasures.getBackjumpCount(),
					mMeasures.getFailCount(),
					mMeasures.getRestartCount(),
					(mMeasures.getFailCount()==0?mMeasures.getMaxDepth():NodeLimit)
					);
			
			 
		
		}
}
