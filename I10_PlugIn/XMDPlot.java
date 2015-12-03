import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import java.awt.*;
import ij.gui.*;

public class XMDPlot{
	
	public XMDPlot(String pathP, String scaNumP, String pathN, String scaNumN, String xLabel){

		FileLocation foundP = new FileLocation(pathP , scaNumP);
		FileLocation foundN = new FileLocation(pathN , scaNumN);
		DataReader asciiData = null;

		try {
			asciiData = new DataReader(foundP.GetData());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Data not found");
			e.printStackTrace();
		}

		File [] fileP = new File(foundP.GetAreaDataFolder()).listFiles();
		File [] fileN = new File(foundN.GetAreaDataFolder()).listFiles();
		
		XMD data = new XMD(fileP, fileN);
	 
		Plot plot = new Plot(xLabel, xLabel,"Mean Intensity",
				asciiData.GetColumn(xLabel), data.GetXMD());
		Plot plot2 = new Plot(xLabel, xLabel,"Mean Intensity",
				asciiData.GetColumn(xLabel), data.GetPLight());
		plot2.setColor(Color.red);
		plot2.addPoints(asciiData.GetColumn(xLabel), data.GetNLight(), PlotWindow.X);
		plot2.addPoints(asciiData.GetColumn(xLabel), data.GetNLight(), PlotWindow.LINE);
		plot.show();
		plot2.show();

	}


}