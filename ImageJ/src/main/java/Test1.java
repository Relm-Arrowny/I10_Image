import java.awt.Color;
import java.io.File;
import java.io.IOException;
import ij.gui.Plot;



public class Test1 {

    public static void main() throws IOException  {
		 FileLocation found = new FileLocation("C:\\Users\\wvx67826\\Desktop\\Area detector\\" , "304697");
		 System.out.println(found.GetAreaData());
		 System.out.println(found.GetData());
		 DataReader asciiData = new DataReader(found.GetData());
		 File [] file = new File(found.GetAreaData()).listFiles();
		 Flou data = new Flou(file);

		 
		 Plot plot = new Plot(asciiData.GetColName(0), asciiData.GetColName(0), asciiData.GetColName(0),
				  asciiData.GetColumn(0), data.GetMax());
		 plot.setColor(Color.red);
		 plot.addPoints(asciiData.GetColumn(0), data.GetMax(),1);
		 plot.addPoints(asciiData.GetColumn(0), data.GetMin(),2);
		 plot.setColor(Color.blue);
	
		 
		 plot.show();
    
    }
    


}