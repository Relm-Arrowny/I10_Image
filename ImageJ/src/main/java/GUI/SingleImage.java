package GUI;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.ImageCalculator;

public class SingleImage {
	
	public SingleImage(String pathP, String image){
		String found = "file:///"+ pathP + "snapped-data//pimte//" + image +".tif";
		ImagePlus tempImage = IJ.openImage(found);
		tempImage.show();
	}
	public SingleImage(String pathP,String image, String background){
		String found = "file:///"+ pathP + "snapped-data//pimte//" + image +".tif";
		String found1 = "file:///"+ pathP + "snapped-data//pimte//" + background +".tif";
		ImagePlus tempImage = IJ.openImage(found);
		ImagePlus tempImage1 = IJ.openImage(found1);
		ImageCalculator ic = new ImageCalculator();
		ic.run("Subtract create", tempImage, tempImage1).show() ;
		
		
	}

}
