package MuonDecay;

/*
* Class to plot data arrays as histograms and to print them to text files.
*/

import ptolemy.plot.*;
import Jama.Matrix;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataAnalysis {
    
    /**
     * Plot the decay data as a histogram
     * @param data list of decay times
     * @param binWidth width of bins to be used, controlling the fineness of
     * the histogram
     */
    public static void plotData(double data[],double binWidth){
        
        Histogram graph  = new Histogram();
        
        graph.setTitle("Histogram of Decay Time Readings");
        graph.setXLabel("Time (s)");//basic unit is the Second, axis is scaled by a factor, located on the graph by the axis.
        graph.setYLabel("Number of readings");//axis also scaled, factor given next to axis.
               
        graph.setBinWidth(binWidth);
        graph.setBinOffset(binWidth/2);//sets it so that the first bin starts at 0.
        graph.setBars(0.9, 0);//bars are drawn with a width of 0.9*binWidth with no offset.
        
        for (int i=0; i<data.length; i++){//adds all of the points to the graph, automatically sorting them into bins
            graph.addPoint(0, data[i]);
        }
        
        PlotFrame frame = new PlotFrame("Decay Times", graph);
        frame.setSize(800, 600);
        frame.setVisible(true);//displays graphs.
    }
    
    /**
     * Print generated data to file
     * @param FileName file name to print to, will be opened as a txt file
     * @param data data to be saved
     */
    public static void saveData(String FileName, double data[]){
        try {        
            BufferedWriter bw = new BufferedWriter(new FileWriter(FileName+".txt"));
            bw.write(Arrays.toString(data)+"\n");
        } catch (IOException ex) {
            System.out.println("Failed to sve data to file");
        }
    }        
}
