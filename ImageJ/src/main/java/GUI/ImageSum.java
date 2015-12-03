package GUI;
import I10ImageJ.FileLocation;
import I10ImageJ.Flou;
import ij.ImagePlus;
import ij.io.Opener;
import ij.process.LUT;

import java.io.File;

public class ImageSum {
	
	public ImageSum (String pathP, String scanNumP, String bgScanNum){
		
		FileLocation found = new FileLocation(pathP, scanNumP);
		String found1 = "file:///"+ pathP + "snapped-data//pimte//" + bgScanNum +".tif";
		File [] file = new File(found.GetAreaDataFolder()).listFiles();

		Flou data = new Flou(file, found1);//"Z:\\data\\2015\\si12958-1\\snapped-data\\pimte\\307976.tif");
		ImagePlus image = data.GetImageSum();
		LUT lut = Opener.openLut("file:///C://Program Files//ImageJ//luts//16_Colors.lut");
		image.setLut(lut);
		image.show();

	}
}
