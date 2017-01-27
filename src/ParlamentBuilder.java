import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ParlamentBuilder {
	
	public static OkHttpClient client = new OkHttpClient();

	Parlament build(int kadencja) throws IOException {
	
		Gson gson = new Gson();
		String nastepnaStrona = "https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]="+Integer.toString(kadencja);
		List<Posel> wszyscy = new ArrayList<Posel>();
		Poslowie aktualni = null;
	
		while(nastepnaStrona != null) {
			String json = run(nastepnaStrona);
			aktualni = gson.fromJson(json, Poslowie.class);
			wszyscy.addAll(aktualni.getDataObject());
			Links nastepny = aktualni.getLinks();	
			nastepnaStrona = nastepny.getNext();
		}
		
		return new Parlament(wszyscy);

	}
	
	 private static String run(String url) throws IOException {
	        Request request = new Request.Builder().url(url).build();
	        Response response = client.newCall(request).execute();
	        return response.body().string();
	    }
	
}
