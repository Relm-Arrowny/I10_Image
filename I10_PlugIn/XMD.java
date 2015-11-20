import java.io.File;


public class XMD {
	private Flou m_pLight;
	private Flou m_nLight;
	public XMD(File [] fileP, File [] fileN){

		m_pLight = new Flou(fileP);
		m_nLight = new Flou(fileN);
	}
	
	public double [] GetXMD(){
		double [] XMD = new double [m_pLight.GetMean().length];
		for (int i = 0; i < m_pLight.GetMean().length; i++){
			XMD[i] = m_pLight.GetMean()[i]-m_nLight.GetMean()[i];
		}
		return XMD;
	}
	public double [] GetPLight(){
		return m_pLight.GetMean();
	}
	
	public double [] GetNLight(){
		return m_nLight.GetMean();
	}

}
