/*The main UI will call this function to get Flou plot 
 * to display on the screen
 * 
 */

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import ij.gui.Plot;

public class FlouPlot {
	
	public FlouPlot(String path, String scaNum, String xLabel){
			FileLocation found = new FileLocation(path , scaNum);
			DataReader asciiData = null;
			try {
				asciiData = new DataReader(found.GetData());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"Data not found");
				e.printStackTrace();
			}
		
			File [] file = new File(found.GetAreaDataFolder()).listFiles();
			Flou data = new Flou(file);
		 
			Plot plot = new Plot(xLabel, xLabel,"Mean Intensity",
					asciiData.GetColumn(xLabel), data.GetMean());
	
			plot.show();
	}

}
