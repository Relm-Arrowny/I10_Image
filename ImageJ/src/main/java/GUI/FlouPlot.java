package GUI;

import java.io.File;
import java.io.IOException;

import I10ImageJ.DataReader;
import I10ImageJ.FileLocation;
import I10ImageJ.Flou;
import ij.gui.Plot;

public class FlouPlot {
	
	public FlouPlot(String path, String scaNum, String xLabel){
			FileLocation found = new FileLocation(path , scaNum);
			DataReader asciiData = null;
			try {
				asciiData = new DataReader(found.GetData());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			File [] file = new File(found.GetAreaDataFolder()).listFiles();
			Flou data = new Flou(file);
		 
			Plot plot = new Plot(xLabel, xLabel,"Mean Intensity",
					asciiData.GetColumn(xLabel), data.GetMean());
	
			plot.show();
	}

}
