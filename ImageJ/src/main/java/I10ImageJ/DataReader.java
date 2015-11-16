package I10ImageJ;
/*
Read the data and cut off everything until keyword is reached
Take the first line break them into scanable and store in an Array: m_colName
Take the rest and store them in float ArrayList  0 represent the first column and so on,
to access the data use GetXXX functions
Data loading is part of the constructor: DataRead(file name, keyword (&END)),
if no keyword is provided the default "&END" will be used

GetColumn (int column)			Return double array with the entire column data
GetColumn (String column)		Use the name of the column instead
GetColData(int column, int row)	Return a data point
GetColName (int column)			Return a string containing data label
GetRowSize ()					Return number of row in the data (number of data point)
GetColSize()					Return number of column in the data (number of motor/scanable)
*/
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataReader {
	// create Array list to store data
	private ArrayList<String> m_colName;
	private List<List<Float>> m_dataArrays;
	
	//Constructors
	// With default search query of "&END" 
	public DataReader(String filePath) throws IOException{
		String searchQuery = "&END";
		DataRead(filePath, searchQuery);
		
	}
	
	// User defined search phrase
	public DataReader(String filePath, String searchQuery) throws IOException{
		DataRead(filePath, searchQuery);	
	}

	//Main Reading data program automatically called when object is first created
	public void DataRead(String filePath, String searchQuery) throws IOException{
		
	        searchQuery = searchQuery.trim();
	        BufferedReader br = null;
	        m_colName = new ArrayList<String>();
	        m_dataArrays = new ArrayList<List<Float>>();

	        try{
	            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
	            String line;
	            // start looking for keyword
	            while ((line = br.readLine()) != null){
	            	
	                if (line.contains(searchQuery)){
	        
	                	line = br.readLine();
	                	Pattern pattern = Pattern.compile("\\w+");
	                	Matcher matcher = pattern.matcher(line);
	                	// once keyword is found store the first line after as column labels
	                	while (matcher.find()) {
	         
	                	    m_colName.add(matcher.group());
	                	    m_dataArrays.add(new ArrayList<Float>());
	                	    
	                	}

	                	//loop thorough the rest of the lines and stores all the data one roll at a time
	                	while ((line = br.readLine()) != null){
	                		int temp = 0;
	            	        Scanner scanner = new Scanner(line);
	            	        while(scanner.hasNextFloat()){
	            	        	m_dataArrays.get(temp).add(scanner.nextFloat());
	            	        	temp++;
	            	        }

	            	        scanner.close();		            
	                		
	                	}
	                	
	                }
	            }
	        }
	        finally
	        {
	            try
	            {
	                if (br != null)
	                    br.close();
	            }
	            catch (Exception e)
	            {
	                System.err.println("Exception while closing bufferedreader " + e.toString());
	            }
	        }

	    }
   
	// Return the correspondent column number for an given column name
	public int GetColNum(String phrase){
		
		for (int n = 0; n < m_colName.size(); n++){
			if(phrase.equals(m_colName.get(n))){
				return n;
			}
		}
		
		System.out.println("No match for: " +phrase);
		return -1;
    	
    }
	
	// Return any column
	public double [] GetColumn (int column){
		double tArray [] = new double [m_dataArrays.get(column).size()];
		try{
			for(int i = 0; i < m_dataArrays.get(column).size(); ++i) {
			    tArray[i] = m_dataArrays.get(column).get(i);
			}
		
		
		}catch(ArithmeticException e){
			System.out.println("Arithmetic Exception!!");
		}
		
		return tArray;
	}
	// Overloaded function to takes String argument
	public double [] GetColumn (String phrase){
		 int column = GetColNum(phrase);
		return GetColumn(column);
	}
	
	
	// Return a given data point with the data
	public double GetColData(int column, int row){
		return m_dataArrays.get(column).get(row);
	}
	
	// Get the name of the column
	public String GetColName (int column){
		
		return m_colName.get(column);
	}
	
	
	
	//Get the number of row
	public int GetRowSize (){
		
		return m_dataArrays.get(0).size();
	}
	
	//Get the number of column
	public int GetColSize(){
		return m_colName.size();
	}
		
}
	
