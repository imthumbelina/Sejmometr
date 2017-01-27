import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class Parlament {

	private List <Posel> wszyscyposlowie;
	
	public Parlament(List <Posel> wszyscyposlowie){
		this.wszyscyposlowie = wszyscyposlowie;
	}	
	Posel znajdzPosla(String name){			
		for(int i=0;i<wszyscyposlowie.size();i++){
			DanePosel dane = wszyscyposlowie.get(i).getDanePosel();
			if(dane.getLudzieNazwa().equals(name)){
				System.out.println("znalazlem");
				System.out.println(dane.getLudzieNazwa());
				return wszyscyposlowie.get(i);				
			}		
		}
		System.out.println("nie ma");
		return null;
	}
	List <Posel> getParlament(){
		return wszyscyposlowie;
	}
}
