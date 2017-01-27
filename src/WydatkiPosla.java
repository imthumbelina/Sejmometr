import com.google.gson.annotations.SerializedName;

public class WydatkiPosla {
	
	@SerializedName("liczba_pol")
	private int liczbapol;
	private Roczniki[] roczniki;
	private Punkty[] punkty;
	

	 public void setliczbapol (int liczbapol){
		 this.liczbapol = liczbapol;
	 }
	 
	int getLiczbePol(){
		return liczbapol;
	}
	
	Roczniki[] getRoczniki(){
		return roczniki;
	}
	
	Punkty[] getPunkty(){
		return punkty;
	}
	
	int get2(){
		return 2;
	}
}
