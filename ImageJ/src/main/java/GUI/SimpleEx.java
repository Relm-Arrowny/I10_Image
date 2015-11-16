package GUI; 
import I10ImageJ.*;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import ij.gui.Plot;

public class SimpleEx extends JFrame{
	Button m_quit, m_readData;
	TextField m_scanNum, m_dataPath, m_colName;
	Panel panelDisplay;
	public SimpleEx() throws IOException{
		m_quit = new Button("Quit");
		m_readData = new Button("1D Scan");
		m_scanNum = new TextField( "Scan Number");
		m_dataPath = new TextField( "Z://data//2015//si12626-1//"/*"Data Directory"*/);
		m_colName = new TextField ("X Aixs Scanable Name");
		
		
		InitUI();
	}
	private void InitUI() throws IOException{

		
		m_quit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				System.out.println("Button pressed");
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
				Flou data = new Flou(file);
				 
				Plot plot = new Plot(m_colName.getText(), "Mean Intensity", asciiData.GetColName(0),
								asciiData.GetColumn(m_colName.getText()), data.GetMean());

		/*		 for (int n = 0; n <asciiData.GetColSize(); n++){
					 System.out.println(asciiData.GetColName(n));
				 }
		*/		 plot.show();
		//		System.exit(0);
			}
		});
		

		//Set up button panel
		Panel panelButtons = new Panel(new GridLayout(2,1));
		panelButtons.add(m_readData);
		panelButtons.add(m_quit);
		
		Panel panelDisplay = new Panel(new BorderLayout(3, 1));
		panelDisplay.add(m_dataPath, BorderLayout.NORTH);
		panelDisplay.add(m_scanNum,BorderLayout.CENTER);
		panelDisplay.add(m_colName,BorderLayout.SOUTH);
		
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
