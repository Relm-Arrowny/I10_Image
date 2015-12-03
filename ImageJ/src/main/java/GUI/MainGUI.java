	/* This is the main GUI part, it will setup simple buttons and text
	 * box. 
	 */

package GUI; 
import I10ImageJ.*;

import java.awt.BorderLayout;
import java.awt.Button;

import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.Panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	// Setting up all the display items
	Button m_quit, m_readData, m_showImg, m_imageSum;
	JTextField m_scanNum, m_dataPath, m_colName, m_imageNum, m_bgScanNum;
	Panel panelDisplay;
	JCheckBox m_xmcd;
	Flou data;
	
	//Setup the buttons name and start the UI
	public MainGUI() throws IOException{
		m_quit = new Button("Quit");
		m_showImg = new Button("Show Image");
		m_readData = new Button("1D Scan");
		m_imageSum = new Button("Images Sum");// sum all the image into one scan with background subtracted
		
		m_scanNum = new JTextField("Scan Number");//"Scan Number"307979);
		m_dataPath = new JTextField( "Z://data//2015//si12958-1//");/*"Data Directory"*/
		m_colName = new JTextField ("pgm_energy");//X Aixs Scanable Name");
		m_imageNum = new JTextField("Scan Number"); 
		m_bgScanNum = new JTextField("Background Scan Number");//"Back Gound Scan number");"307976"
				
		m_xmcd = new JCheckBox("XMCD");
		
		

		
		InitUI();
	}
	
	//The Main UI
	private void InitUI() throws IOException{

		//Set up Keyboard listener to test when a text box is clicked
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
	    .addPropertyChangeListener("permanentFocusOwner", new PropertyChangeListener()
	{
	    public void propertyChange(final PropertyChangeEvent e)
	    {
	    	if (e.getNewValue() instanceof JTextField)
	    	{
	    		SwingUtilities.invokeLater(new Runnable()
	    		{
	    			public void run()
	    			{
	    				JTextField textField = (JTextField)e.getNewValue();
	    				textField.selectAll();
	    			}
	    		});

	    	}
	    }
	});
		// Add Quit botton
		m_quit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		});
		
		//Add 1d scan button, it will either do xmcd/flou depending on if the xmcd box is chicked
		m_readData.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				if(m_xmcd.isSelected()){
					String [] splited = m_scanNum.getText().split("\\s+");
					try{
						XMDPlot xmcdPlot= new XMDPlot(m_dataPath.getText(), splited[0], m_dataPath.getText(), 
							splited[1], m_colName.getText());
					}
					catch(ArrayIndexOutOfBoundsException e){
						
						JOptionPane.showMessageDialog(null,"Two scans are required");
						e.printStackTrace();
						
					}
					
				}
				
				else{
					FlouPlot flouPlot = new FlouPlot(m_dataPath.getText(), m_scanNum.getText(),m_colName.getText());
				}

			}
		});
		//Add 2d scan sum button
		m_imageSum.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				ImageSum imageSum = new ImageSum(m_dataPath.getText(), m_scanNum.getText(),m_bgScanNum.getText());
			}
		});
		
		//Add show image button to show a single image
		m_showImg.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				SingleImage image = new SingleImage(m_dataPath.getText(), m_imageNum.getText(),
						m_bgScanNum.getText());
			}
		});
		

		//Set up button panel
		Panel panelButtons = new Panel(new BorderLayout(3,3));
		panelButtons.add(m_readData, BorderLayout.NORTH);
		panelButtons.add(m_imageSum, BorderLayout.EAST);
		panelButtons.add(m_showImg, BorderLayout.CENTER);
		panelButtons.add(m_quit,BorderLayout.SOUTH);
		
		Panel panelCentre = new Panel(new BorderLayout(3,3));
		panelCentre.add(m_bgScanNum, BorderLayout.NORTH);
		
		//Set up text panel
		Panel panelDisplay = new Panel(new BorderLayout(3, 3));
		panelDisplay.add(m_dataPath, BorderLayout.NORTH);
		panelDisplay.add(m_scanNum,BorderLayout.CENTER);
		m_colName.setColumns(5);
		panelDisplay.add(m_xmcd,BorderLayout.EAST);
		panelDisplay.add(m_colName,BorderLayout.WEST);
		panelDisplay.add(m_imageNum,BorderLayout.SOUTH);
	
		
		
		
		//Set up the text input panel
		setLayout(new BorderLayout());
		add(panelDisplay, BorderLayout.NORTH);
		add(panelCentre, BorderLayout.CENTER);
		add(panelButtons, BorderLayout.SOUTH);
		setTitle("I10 Area Detector Beta" );
		setSize(300, 300);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
