	/* This is the main GUI part, it will setup simple buttons and text
	 * box. 
	 */


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
	Button m_quit, m_readData, m_showImg;
	JTextField m_scanNum, m_dataPath, m_colName, m_ImgNum;
	Panel panelDisplay;
	JCheckBox m_xmcd;
	Flou data;
	
	//Setup the buttons name and start the UI
	public MainGUI() throws IOException{
		m_quit = new Button("Quit");
		m_showImg = new Button("Show Image");
		m_readData = new Button("1D Scan");
		m_scanNum = new JTextField("307922");//"Scan Number");
		m_dataPath = new JTextField( "Z://data//2015//si12958-1//"/*"Data Directory"*/);
		m_colName = new JTextField ("pgm_energy");//X Aixs Scanable Name");
		m_ImgNum = new JTextField("Image Number");
		m_xmcd = new JCheckBox("XMCD");

		
		InitUI();
	}
	
	//The Main UI
	private void InitUI() throws IOException{

		//Set up Keyboarder listener to test when a text box is clicked
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
		//Add show image button to show a single image
		m_showImg.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				data.GetImage(Integer.parseInt(m_ImgNum.getText())).show();
			}
		});
		

		//Set up button panel
		Panel panelButtons = new Panel(new GridLayout(3,1));
		panelButtons.add(m_readData);
		panelButtons.add(m_showImg);
		panelButtons.add(m_quit);
		
		//Set up text panel
		Panel panelDisplay = new Panel(new BorderLayout(3, 2));
		panelDisplay.add(m_dataPath, BorderLayout.NORTH);
		panelDisplay.add(m_scanNum,BorderLayout.CENTER);
		m_colName.setColumns(5);
		panelDisplay.add(m_xmcd,BorderLayout.EAST);
		panelDisplay.add(m_colName,BorderLayout.WEST);
		panelDisplay.add(m_ImgNum,BorderLayout.SOUTH);
		
		//Set up the text input panel
		setLayout(new BorderLayout());
		add(panelDisplay, BorderLayout.NORTH);
		add(panelButtons, BorderLayout.SOUTH);
		setTitle("I10 Area Detector Beta" );
		setSize(300, 300);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
