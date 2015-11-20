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
import ij.IJ;
import ij.ImagePlus;
import java.io.File;
import java.util.ArrayList;
 
public class Flou{
	
	private ArrayList<ImagePlus> m_Stack; 
	//create dynamic array to hold ImagePlus
	public Flou(File[] files){
		m_Stack = new ArrayList<ImagePlus>(); 
		LoadImage(files);
	}
	// This will load every images in the folder into the array
	
	public void LoadImage(File[] files){
		int totalNumFile = files.length;
		int counter = 0;
		for (File file : files){
				IJ.showProgress(counter++, totalNumFile);
				ImagePlus image = IJ.openImage(file.getAbsolutePath());
				m_Stack.add(image);
				IJ.showStatus("1");
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
}


