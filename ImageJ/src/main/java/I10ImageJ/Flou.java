/*x-ray fluorescence Class for area detector
 This class takes a File type for folder location and
 read all the images into memory 
 Reading of images is part of the constructor a File type is required
----------------------------------------------------------
GetImage(int)			return an ImagePlus in the array
GetImagesList()			return the entire arraylist of ImagePlus   
GetMean()				return a double array contain Images mean intensity
GetMax()				return a double array contain Images maximum intensity
GetMin()				return a double array contain Images minimum intensity
GetX()					return a double array increment from 0 to array size
 */
package I10ImageJ;
import ij.IJ;
import ij.ImagePlus;
import ij.plugin.ImageCalculator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import javax.swing.JOptionPane;
 
public class Flou{
	
	private ArrayList<ImagePlus> m_Stack; 
	//create dynamic array to hold ImagePlus
	
	public Flou(File[] files){
		m_Stack = new ArrayList<ImagePlus>(); 
		LoadImage(files);
	}
	
	public Flou(File[] files, String backgroud){
		m_Stack = new ArrayList<ImagePlus>(); 
		LoadImage(files);
		ImagePlus image = IJ.openImage(backgroud);
		for (int i = 0; i<m_Stack.size(); i++){
			ImageCalculator ic = new ImageCalculator();
			m_Stack.set(i, ic.run("Subtract create", m_Stack.get(i), image)) ;
			
		}
	}
	// This will load every images in the folder into the array
	
	public void LoadImage(File[] files){
		Arrays.sort(files);

		for (File file : files){

				if (file.exists()){
					ImagePlus image = IJ.openImage(file.getAbsolutePath());
					m_Stack.add(image);

				}
				else{
					JOptionPane.showMessageDialog(null,"Data not found");
					throw new EmptyStackException();
				}

			}
		}
		
    // Return an image
	public ImagePlus GetImage(int arg){
		
		return m_Stack.get(arg);		
	}
	
    public ArrayList<ImagePlus> GetImagesList(){
    	return m_Stack;
    }
	// Cutting image stack into 1D data
	//Getting x axis currently only generate an number array stating from zero
	public double [] GetX(){
		double [] x = new double [m_Stack.size()];
		for (int k = 0; k < m_Stack.size(); k++){
			x[k] = k;

		}
		return x;
	}
	
	// Get Image mean and return it as an array
	public double [] GetMean(){

		double [] mean = new double [m_Stack.size()];
		for (int k = 0; k < m_Stack.size(); k++){
			mean[k] = m_Stack.get(k).getStatistics().mean;

		}
		return mean;
	}
	
	// Get Image area and return it as an array
	public double [] GetArea(){

		double [] area = new double [m_Stack.size()];
		for (int k = 0; k < m_Stack.size(); k++){
			area[k] = m_Stack.get(k).getStatistics().area;
		}
		return area;
	}

	// Get Image max and return it as an array
	public double [] GetMax(){

		double [] max = new double [m_Stack.size()];
		for (int k = 0; k < m_Stack.size(); k++){
			max[k] = m_Stack.get(k).getStatistics().max;

		}
		return max;
	}

	// Get Image max and return it as an array	
	public double [] GetMin(){

		double [] min = new double [m_Stack.size()];
		for (int k = 0; k < m_Stack.size(); k++){
			min[k] = m_Stack.get(k).getStatistics().min;

		}
		return min;
	}	
	public ImagePlus GetImageSum(){

		ImageCalculator ic = new ImageCalculator();
		ImagePlus imp3 = m_Stack.get(0);
		for (int i = 1; i<m_Stack.size(); i++){
			imp3 = ic.run("Subtract create", m_Stack.get(i), imp3);
		}

		
		return imp3;
	}	
}


