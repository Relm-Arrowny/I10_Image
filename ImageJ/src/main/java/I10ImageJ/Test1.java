package I10ImageJ;
import java.awt.EventQueue;
import java.io.IOException;
import GUI.SimpleEx;


public class Test1 {
	public static void main(String[] args) throws IOException  {


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
                System.out.println("1231");
            }
        });
    	
    }
    


}