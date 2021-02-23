package MuonDecay;

/**
 * Class to generate random decay times for muon particles
 * 
 * @author cjcode975
 */

import java.util.Random;

public class DecayTimeGenerator {
    
    //Half life for the muons
    private final double lambda;
        
    public DecayTimeGenerator(double halfLife){
        lambda = Math.log(2)/halfLife;
    }
      
    /**
     * Calculate the value of the exponential pdf at time
     * @param time time
     * @return probability of a decay at this time
     */
    private double evalExp(double time){
        return lambda*Math.pow(Math.E, (-1*time*lambda));        
    }
    
    /**
     * Generate one decay time based on the exponential pdf. All values 
     * will lie below a given cutoff time
     * @param maxT cutoff time
     * @return one random decay time
     */
    private double nextT(double maxT){ 
    
        double x1=0;
        double y1=0; 
        double y2=0;
        
        double fMax = this.evalExp(0);
        Random gen = new Random();
        
        //Turn a uniformly distributed randum number, into one distributed by
        //the decay pdf
        do {
            x1 = maxT*gen.nextDouble(); 
            y1 = this.evalExp(x1); //evaluate pdf at x1
            y2 = fMax*gen.nextDouble(); 
        }
        while (y2>y1); //if y2>y1, discard x1 and repeat. else, return x1.
        
        return x1;    
    }
    
    /**
     * Generate the next n decay times
     * @param n number of decays to calculate
     * @param maxT maximum length of decay allowed
     * @return array of decay times
     */
    protected double[] nextNT(int n, double maxT){
        double times[] = new double[n];
        for (int i=0; i<n; i++){
            times[i] = this.nextT(maxT);
        }
        return times;
    }
    
    /**
     * Generate one decay time based on the exponential pdf with added background
     * noise. Time will lie below a given cutoff
     * @param maxT cutoff time
     * @return one simulated decay time
     */
    private double nextTb(double maxT){
        double x1=0;
        double y1=0; 
        double y2=0;
        
        double fMax = this.evalExp(0);
        Random gen = new Random();
        
        //Turn a uniformly distributed randum number, into one distributed by
        //the decay pdf
        do {
            x1 = maxT*gen.nextDouble();            
            y1 = this.evalExp(x1)+0.1*fMax; //adds the 'background noise' by adding 0.1*fMax to the evaluation of the exponential pdf.
            y2 = 1.1*fMax*gen.nextDouble();
        }
        while (y2>y1); //if y2>y1, discard x1 and repeat. else, return x1 
        
        return x1;
    }
    
    /**
     * Generate the next n decay times, with background noise included
     * @param n number of decays to calculate
     * @param maxT maximum length of decay allowed
     * @return array of decay times
     */
    protected double[] nextNTb(int n, double maxT){
        double times[] = new double[n];
        for (int i=0; i<n; i++){
            times[i] = this.nextTb(maxT);
        }
        return times;
    }
}