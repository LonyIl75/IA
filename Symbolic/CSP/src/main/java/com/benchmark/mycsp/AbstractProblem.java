package com.benchmark.mycsp;


import org.chocosolver.parser.IParser;
import org.chocosolver.parser.SetUpException;
import org.chocosolver.solver.Model;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import static java.lang.Runtime.getRuntime;

import java.util.List;

/**
 * A class that provides a pattern to declare a model and solve it. <br/>
 *
 * @author Charles Prud'homme
 * @since 31/03/11
 */
public abstract class AbstractProblem implements IParser {

    /**
     * A seed for random purpose
     */
    @Option(name = "-seed", usage = "Seed for Shuffle propagation engine.", required = false)
    protected long seed = 29091981;
    @Option(name = "-ficName", usage = "(String)Nom du fichier sample .", required = true)
    String ficName ;
    @Option(name = "-timeLimit", usage = "(String)Duree time out .", required = false)
	 String timeLimit = "10m" ;
    @Option(name = "-nodeLimit", usage = "(Integer)LimitNode .", required = true)
	 Integer NodeLimit = 50 ; 
    @Option(name = "-strategy", usage = "(Integer)Strategy code ", required = false)
	 Integer strategy = -1;

	 

    

    /**
     * Declared problem
     */
    protected Model model;
    protected String csvStat;
    private boolean userInterruption = true;

    @Override
    public boolean setUp(String... args) throws SetUpException{
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java " + this.getClass() + " [options...]");
            parser.printUsage(System.err);
            return false;
        }
        return true;
    }

    /**
     * @return the current model
     */
    @Override
    public Model getModel() {
        return model;
    }
    
   
    public String getCsvStat() {
        return csvStat;
    }

    

    /**
     * Call search configuration
     */
    @Override
    public void configureSearch() {
    }

    @Override
    public void tearDown() {
    }


    private boolean userInterruption() {
        return userInterruption;
    }

    /**
     * Main method: from argument reading to resolution.
     * <ul>
     * <li>read program arguments</li>
     * <li>build the model</li>
     * <li>configure the search</li>
     * <li>launch the resolution</li>
     * </ul>
     *
     * @param args
     *            list of arguments to pass to the problem
     */
    public final void execute(String... args) {
        try {
            this.setUp(args);
        } catch (SetUpException e) {
            return;
        }
        this.buildModel();
        this.configureSearch();

        Thread statOnKill = new Thread() {
            public void run() {
                if (userInterruption()) {
                    System.out.println(model.getSolver().getMeasures().toString());
                }
            }
        };

        getRuntime().addShutdownHook(statOnKill);

        this.solve();
        userInterruption = false;
        getRuntime().removeShutdownHook(statOnKill);
    }

    
    public final String execute_bis(String... args) {
        try {
            this.setUp(args);
        } catch (SetUpException e) {
            return "";
        }
        this.buildModel();
        this.configureSearch();

        Thread statOnKill = new Thread() {
            public void run() {
                if (userInterruption()) {
                    System.out.println(model.getSolver().getMeasures().toString());
                }
            }
        };

        getRuntime().addShutdownHook(statOnKill);

        this.solve();
        userInterruption = false;
        getRuntime().removeShutdownHook(statOnKill);
        return this.getCsvStat();
    }

    
    
    @Override
    public Thread actionOnKill() {
        return new Thread(() -> {
        });
    }
}
