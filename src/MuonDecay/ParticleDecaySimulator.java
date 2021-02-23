package MuonDecay;

/**
 * Program to simulate muon decay in an experiment, both with and without
 * background noise.
 * 
 * Main class gives an interface for using the background methods which do
 * the calculations - the user is prompted to input all the necessary information
 * for the simulation to run - the half-life of the muons under consideration,
 * the intended number of muon decays to observe, and a maximum time a single 
 * muon decay is allowed to take without being ignored
 * 
 * With the supplied information, two simulations will then be run - one 
 * without background noise, and one with background noise - and the results 
 * outputted as histograms
 * 
 * @author cjcode975
 */

import java.io.Console;

public class ParticleDecaySimulator {
    
    /**
     * Check if an inputted String can be parsed as a double
     * @param input test string
     * @return if input is a string representation of a double
     */
    public static boolean isDouble(String input){
        double parsed;
        try{ parsed = Double.parseDouble(input.trim()); }
        catch(NumberFormatException nfe){ return false; }
        return true;
    }
    
    /**
     * Check if an inputted String can be parsed as an int
     * @param input test string
     * @return if input is a string representation of an int
     */
    public static boolean isInt(String input){
        int parsed;
        try{ parsed = Integer.parseInt(input.trim()); }
        catch(NumberFormatException nfe){ return false; }
        return true;
    }

    public static void main(String[] args) {
        
        Console input = System.console();
        
        double HL, maxT;
        int numD;
        
	//Request a value for the muon half-life. This must be a double
        String hLIn;
        do{
            hLIn = input.readLine("Enter the particle Half Life as a double:");
        } while(!ParticleDecaySimulator.isDouble(hLIn));
        HL = Double.parseDouble(hLIn.trim()); //Successfully inputted particle Half-Life
        
	//Request a value for the number of muon decys to observe. This must be an int
        String nD;
        do{
            nD = input.readLine("Enter the number of decays to be recorded as an int:");
        } while(!ParticleDecaySimulator.isInt(nD));
        numD = Integer.parseInt(nD.trim()); //Successfully inputted number of decays to be recorded
        
        //Request a value for the maximum observation time. This must be a double
	String mT;
        do{
            mT = input.readLine("Enter the maximum time to be recorded as a double:");
        } while(!ParticleDecaySimulator.isDouble(mT));
        maxT = Double.parseDouble(mT.trim()); //Successfully inputted maximum time.
        
        //Create a new experiment with the information
        Experiment a = new Experiment(HL, numD, maxT);
        double results1[] = a.runE(true); //simulates decay times without the background, plotting the associated Histogram
        double results2[] = a.runEWithBackground(true); //simulates the background with the background, plotting the associated Histogram       
                
    }
}
