import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class Podroze {
	
	public static OkHttpClient client = new OkHttpClient();

	//posła/posłanki, który wykonał najwięcej podróży zagranicznych
	
	int najwiecejPodrozy(List <Posel> wszyscyposlowie){
		
		int max=-1;
		String name=null;
		
		for(Posel posel : wszyscyposlowie){
			DanePosel dane_posel = posel.getDanePosel();
			if(dane_posel.getLiczbaWyjazdow()>max){
				max= dane_posel.getLiczbaWyjazdow();
				name = dane_posel.getLudzieNazwa();
			}
		}
		System.out.println(name);
		return max;
		
	}
	
	//posła/posłanki, który odbył najdroższą podróż zagraniczną
	
	String najdrozszaPodroz(List <Posel> wszyscyposlowie) throws IOException{
		
		String id;
		float najdrozsza=-1;
		String name=null;
		
		for(Posel posel : wszyscyposlowie){
			
			id = posel.getId();
			
			Gson gson = new Gson();
			String wydatki_posla = "https://api-v3.mojepanstwo.pl/dane/poslowie/"+id+".json?layers[]=krs&layers[]=wyjazdy";		
			String json = run(wydatki_posla);
			posel = gson.fromJson(json, Posel.class);
			
			Layers posel_layers = posel.getLayers();
			DanePosel posel_dane = posel.getDanePosel();
			posel_layers.zaladujPodroze();
			LinkedList<Map<String,String>> wyjazdy=posel_layers.getPodroze();
			
			if(!wyjazdy.isEmpty()){
				for(int i=0;i<wyjazdy.size();i++){
					if(Float.parseFloat(wyjazdy.get(i).get("koszt_suma"))>najdrozsza){
						najdrozsza = Float.parseFloat(wyjazdy.get(i).get("koszt_suma"));
						name = posel_dane.getLudzieNazwa();
					}
				}
			}
			
		}
		System.out.println(najdrozsza);
		return name;
		
	}
	
	String najwiecejZaGranica(List <Posel> wszyscyposlowie) throws IOException{
		
		String id;
		float najdluzej=-1;
		String name=null;
		int suma = 0;
		
	for(Posel posel : wszyscyposlowie){
			
			id = posel.getId();
			
			Gson gson = new Gson();
			String wydatki_posla = "https://api-v3.mojepanstwo.pl/dane/poslowie/"+id+".json?layers[]=krs&layers[]=wyjazdy";		
			String json = run(wydatki_posla);
			posel = gson.fromJson(json, Posel.class);
			
			Layers posel_layers = posel.getLayers();
			DanePosel posel_dane = posel.getDanePosel();
			posel_layers.zaladujPodroze();
			LinkedList<Map<String,String>> wyjazdy=posel_layers.getPodroze();
			
			if(!wyjazdy.isEmpty()){
				for(int i=0;i<wyjazdy.size();i++){
					suma = suma + Integer.parseInt(wyjazdy.get(i).get("liczba_dni"));
					
				}
			}
			
			if(suma> najdluzej){
				najdluzej = suma;
				name = posel_dane.getLudzieNazwa();
			}
			suma=0;
			
		}
		
		System.out.println("ilosc dni to"+ najdluzej);
		return name;
		
	}
	
	//listę wszystkich posłów, którzy odwiedzili Włochy
	
	List <String> odwiedzoneWlochy(List <Posel> wszyscyposlowie) throws IOException{
		
		List <String> poslowie = new LinkedList <String>();
		String id;
		boolean odwiedzone=false;
		
			for(Posel posel : wszyscyposlowie){
			
				id = posel.getId();
				
				Gson gson = new Gson();
				String wydatki_posla = "https://api-v3.mojepanstwo.pl/dane/poslowie/"+id+".json?layers[]=krs&layers[]=wyjazdy";		
				String json = run(wydatki_posla);
				posel = gson.fromJson(json, Posel.class);
				
				Layers posel_layers = posel.getLayers();
				DanePosel posel_dane = posel.getDanePosel();
				posel_layers.zaladujPodroze();
				LinkedList<Map<String,String>> wyjazdy=posel_layers.getPodroze();
				
				if(!wyjazdy.isEmpty()){
					for(int i=0;i<wyjazdy.size() && odwiedzone!=true;i++){
						if(wyjazdy.get(i).get("kraj").equals("W\u0142ochy")){
							poslowie.add(posel_dane.getLudzieNazwa());
							
							odwiedzone=true;
						}
					}
				}
				odwiedzone = false;
			}
		
		
		
		
		return poslowie;
		
	}
	
	
	 private static String run(String url) throws IOException {
	        Request request = new Request.Builder().url(url).build();
	        Response response = client.newCall(request).execute();
	        return response.body().string();
	    }
	
}
