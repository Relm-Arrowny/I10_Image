import java.awt.*;
import java.io.IOException;
import GUI.SimpleEx;
public class Plugin {

	void run(java.lang.String arg){
		   
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
