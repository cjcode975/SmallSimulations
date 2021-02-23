package MuonDecay;

//main class to test classes.

public class Test {

    public static void main(String[] args) {
        DecayTimeGenerator dtGen = new DecayTimeGenerator(0.0000022); //creates a new instance of DTGenertor with the muon half-life
        Experiment experiment = new Experiment(dtGen, 1000, 0.00003); //new experiment
        double results[] = experiment.runE(true); //runs experiment without background noise, plotting reults
        double results2[] = experiment.runEWithBackground(true); //runs experiment with background noise plotting results
        DataAnalysis.saveData("results1.txt", results);
        DataAnalysis.saveData("results2.txt", results2);        
    }
}