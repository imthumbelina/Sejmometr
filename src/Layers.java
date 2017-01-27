import java.awt.Window.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

public class Layers {
	private WydatkiPosla wydatki;
	private JsonElement wyjazdy;
	private LinkedList<Map<String,String>> wyjazdy2;
	
	WydatkiPosla getWydatkiPosla(){
		return wydatki;
	}
	 public void setWydatki (WydatkiPosla wydatki)
	   {
	       this.wydatki = wydatki;
	   }
	
	  LinkedList<Map<String,String>> getPodroze(){
		  return wyjazdy2;
	  }
	  
	  @SuppressWarnings("unchecked")
	  public void zaladujPodroze(){
		  try{
			  if(wyjazdy == null){
				  throw new NullPointerException("Blad");
			  }
			  if(wyjazdy.isJsonObject()){
				  this.wyjazdy2 = new LinkedList<>();
			  }
			  else{
				  this.wyjazdy2 = new Gson().fromJson(this.wyjazdy, LinkedList.class);				  
			  }
		  }
		  catch(Exception e){
			  System.out.println("NULL");
		  }
	  }
	 
}
