import com.google.gson.annotations.SerializedName;

public class DanePosel {
	@SerializedName("ludzie.id")
	private String ludzieId;
	@SerializedName("poslowie.liczba_przelotow")
	private int poslowieLiczbaPrzelotow;
	@SerializedName("ludzie.nazwa")
	private String ludzieNazwa;
	@SerializedName("poslowie.liczba_wyjazdow")
	private int poslowieLiczbaWyjazdow;
	
	
	String getLudzieid(){
		return ludzieId;
	}
	
	int getLiczbaPrzelotow(){
		return poslowieLiczbaPrzelotow;
	}
	String getLudzieNazwa(){
		return ludzieNazwa;
	}
	int getLiczbaWyjazdow(){
		return poslowieLiczbaWyjazdow;
	}
}
