package MuonDecay;

/**
 * Describe the numeric equivalent of a muon decay experiment
 * 
 * @author cjcode975
 */

public class Experiment{
    
    private final double maxT;
    private final int nDecays;
    private final DecayTimeGenerator decayTimeGen;
    
    /**
     * Define the experiment parameters 
     * @param halfLife muon half life
     * @param numDec number of decays to be observed
     * @param Tmax maximum considered decay time
     */
    public Experiment(double halfLife, int numDec, double Tmax){
        nDecays = numDec;
        decayTimeGen = new DecayTimeGenerator(halfLife); 
        maxT = Tmax;
    }
    
    /**
     * Run the experiment without background noise, plotting the decay times
     * if requested
     * @param plot if the decay times should be plotted
     * @return simulated decay times from the experiment
     */
    public double[] runE(boolean plot){
        double results[] = decayTimeGen.nextNT(nDecays, maxT);
        if (plot){ DataAnalysis.plotData(results, maxT/50); }        
        return results;
    }       
    
    /**
     * Run the experiment with background noise, plotting the decay times
     * if requested
     * @param plot if the decay times should be plotted
     * @return simulated decay times from the experiment
     */
    public double[] runEWithBackground(boolean plot){
        double results[] = decayTimeGen.nextNTb(nDecays, maxT);
        if (plot){ DataAnalysis.plotData(results, maxT/50); }    
        return results;
    }
}
