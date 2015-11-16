package I10ImageJ;
// Take path and scan number return either the folder that contains images or any data file
public class FileLocation {
	 private String m_asciiData;
	 private String m_areaDetectorData;
	 private String m_areaDetectorFolder;
	 
	public FileLocation(String filePath, String scanNum){
	
		 m_asciiData = filePath+"i10-"+scanNum+".dat";
		 m_areaDetectorFolder =  filePath+scanNum+ "-pimte-files";
		 m_areaDetectorData = filePath+scanNum+".tif";;
	 }
	
	public String GetData(){
		
		return m_asciiData;
		
	}
	public String GetAreaDataFolder(){
		
		return m_areaDetectorFolder;
		
	}
	public String GetAreaData(){
		
		return m_areaDetectorData;
		
	}
	

}
