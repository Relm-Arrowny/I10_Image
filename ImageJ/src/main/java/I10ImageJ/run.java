package I10ImageJ;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import ij.gui.Plot;
import javax.swing.*;

public class run{
	 /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

	public static void main(String[] args) throws IOException{
		 
		 FileLocation found = new FileLocation("C:\\Users\\wvx67826\\Desktop\\Area detector\\" , "304697");

		 DataReader asciiData = new DataReader(found.GetData());
		 File [] file = new File(found.GetAreaDataFolder()).listFiles();
		 Flou data = new Flou(file);
		 data.GetImagesList().get(5).show();
		 System.out.println(data.GetImagesList().size());
		 
		 Plot plot = new Plot(asciiData.GetColName(0), asciiData.GetColName(0), asciiData.GetColName(0),
				  asciiData.GetColumn("dummy"), data.GetMean());

/*		 for (int n = 0; n <asciiData.GetColSize(); n++){
			 System.out.println(asciiData.GetColName(n));
		 }
*/		 plot.show();
	     //Schedule a job for the event-dispatching thread:
	     //creating and showing this application's GUI.
	     javax.swing.SwingUtilities.invokeLater(new Runnable() {
	    	 public void run() {
	    		 createAndShowGUI();
	    	 }
	     });
	}
		 
}