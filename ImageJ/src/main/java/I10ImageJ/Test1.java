package I10ImageJ;
import GUI.MainGUI;
import java.awt.EventQueue;
import java.io.IOException;




public class Test1 {
	public static void main(String[] args) throws IOException  {
		
		/*	FileLocation found = new FileLocation("Z://data//2015//si12626-1//", "301182");
		FileLocation found1 = new FileLocation("Z://data//2015//si12626-1//", "301183");
		File [] file = new File(found.GetAreaDataFolder()).listFiles();
		File [] file1 = new File(found1.GetAreaDataFolder()).listFiles();
		XMD data = new XMD(file,file1);
		DataReader data1 = new DataReader(found.GetData());
		Plot plot = new Plot("th", "","Mean Intensity",
						data1.GetColumn("th"), data.GetXMD());

		plot.show();
		*/
		
        EventQueue.invokeLater(new Runnable() {
        
            @Override
            public void run() {
                MainGUI ex = null;
				try {
					ex = new MainGUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                ex.setVisible(true);
            }
        });
    	
    }
    


}