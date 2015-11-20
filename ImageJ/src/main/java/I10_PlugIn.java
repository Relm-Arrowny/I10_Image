import java.awt.EventQueue;
import java.io.IOException;
import ij.plugin.PlugIn;
import GUI.MainGUI;

public class I10_PlugIn implements PlugIn {
	public void run(String arg) {
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
