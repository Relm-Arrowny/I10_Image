package GUI; 
import I10ImageJ.*;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import ij.gui.Plot;

public class SimpleEx extends JFrame{
	Button m_quit, m_readData, m_showImg;
	JTextField m_scanNum, m_dataPath, m_colName, m_ImgNum;
	Panel panelDisplay;
	Flou data;
	public SimpleEx() throws IOException{
		m_quit = new Button("Quit");
		m_showImg = new Button("Show Image");
		m_readData = new Button("1D Scan");
		m_scanNum = new JTextField("301182");//"Scan Number");
		m_dataPath = new JTextField( "Z://data//2015//si12626-1//"/*"Data Directory"*/);
		m_colName = new JTextField ("th");//X Aixs Scanable Name");
		m_ImgNum = new JTextField("Image Number");	
		InitUI();
	}
	private void InitUI() throws IOException{

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
		m_quit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		});
		m_readData.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				System.out.println(m_dataPath.getText());
				FileLocation found = new FileLocation(m_dataPath.getText() , m_scanNum.getText());
				DataReader asciiData = null;
				try {
					asciiData = new DataReader(found.GetData());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				File [] file = new File(found.GetAreaDataFolder()).listFiles();
				data = new Flou(file);
				 
				Plot plot = new Plot(m_colName.getText(), m_colName.getText(),"Mean Intensity",
								asciiData.GetColumn(m_colName.getText()), data.GetMean());

				plot.show();

			}
		});
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
		
		
		Panel panelDisplay = new Panel(new BorderLayout(3, 2));
		panelDisplay.add(m_dataPath, BorderLayout.NORTH);
		panelDisplay.add(m_scanNum,BorderLayout.CENTER);
		m_colName.setColumns(14);
		panelDisplay.add(m_colName,BorderLayout.EAST);
		panelDisplay.add(m_ImgNum,BorderLayout.SOUTH);
		
		//Set up the text input panel
		setLayout(new BorderLayout());
		add(panelDisplay, BorderLayout.NORTH);
		add(panelButtons, BorderLayout.SOUTH);
		setTitle("Scan: "+ m_scanNum.getText() );
		setSize(300, 300);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
