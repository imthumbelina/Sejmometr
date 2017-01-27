import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class Wydatki {
	
	public static OkHttpClient client = new OkHttpClient();
	
	float znajdzWydatki(Posel posel) throws IOException{
		
		String id=posel.getId();
		Gson gson = new Gson();
		String wydatki_posla = "https://api-v3.mojepanstwo.pl/dane/poslowie/"+id+".json?layers[]=krs&layers[]=wydatki";		
		String json = run(wydatki_posla);
		posel = gson.fromJson(json, Posel.class);
		Layers layers_posla = posel.getLayers();
		WydatkiPosla wydatki = layers_posla.getWydatkiPosla();
		Roczniki[] roczniki_posla = wydatki.getRoczniki();
		
		float sum=0;
		for(Roczniki pola : roczniki_posla) {
			for(String wydatek : pola.getPola()) {
				sum=sum+Float.parseFloat(wydatek);
			}
		}	
		
		return sum;
	}
	
	float znajdzDrobneNaprawy(Posel posel) throws IOException{
		
		String id=posel.getId();
		Gson gson = new Gson();
		String drobne_wydatki_posla = "https://api-v3.mojepanstwo.pl/dane/poslowie/"+id+".json?layers[]=krs&layers[]=wydatki";		
		String json = run(drobne_wydatki_posla);
		posel = gson.fromJson(json, Posel.class);
		
		
		Layers layers_posla = posel.getLayers();
		WydatkiPosla wydatki = layers_posla.getWydatkiPosla();
		Roczniki[] roczniki_posla = wydatki.getRoczniki();
		
		
		float sum=0;
		for(int i=0;i<roczniki_posla.length;i++){
			String[] pola = roczniki_posla[i].getPola();
			sum=sum+Float.parseFloat(pola[12]);
		}
		
		return sum;
		
	}
	
	float wydatkiWszystkichPoslow(List <Posel> wszyscyposlowie ) throws IOException{
		
		float sum=0;
		
		for(int i=0;i<wszyscyposlowie.size();i++){
			System.out.println(wszyscyposlowie.get(i).getSlug());
			sum=sum+znajdzWydatki(wszyscyposlowie.get(i));
		}
		int wszyscy = wszyscyposlowie.size();
		float iloscposlow = (float) wszyscy;
		
		return sum/iloscposlow;
	}
	
	private String run(String url) throws IOException {
	   Request request = new Request.Builder().url(url).build();
	   Response response = client.newCall(request).execute();
	   return response.body().string();
	}

}
