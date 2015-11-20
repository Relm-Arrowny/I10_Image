import java.awt.EventQueue;
import java.io.IOException;
import ij.plugin.PlugIn;

public class I10_PlugIn implements PlugIn {
	public void run(String arg) {
		EventQueue.invokeLater(new Runnable() {
	        
	        @Override
	        public void run() {
	            SimpleEx ex = null;
				try {
					ex = new SimpleEx();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            ex.setVisible(true);
	        }
	    });
	}
	
}
