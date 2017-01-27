import java.io.IOException;
import java.util.List;

public class Parser {	
	String[] args;

	public Parser(String[] args){
		this.args = args;
	}
	
	public void parse() throws IOException{
		
		
		
		try{
		
		if(args.length<2){
			System.out.println("Podano za mało argumentów");
			return;
		}
		
		System.out.println(args[1]);
		if(!args[1].equals("7") && !args[1].equals("8")){
			System.out.println("Informacje dostępne są dla kadencji 7 i 8");
			return;
		}
		
		if(args[1].equals("7") || args[1].equals("8")){
			System.out.println("Wybrano kadencję"+args[1]);
			
			Parlament parlament = new ParlamentBuilder().build(Integer.parseInt(args[1]));
			List <Posel> poslowie = parlament.getParlament();
			Wydatki wydatki_poslow = new Wydatki();
			Podroze podroze_poslow = new Podroze();
			
		
			
			switch(args[0]){
			
				case "SredniaWydatkow":
					requireArgs(2);
					System.out.println(wydatki_poslow.wydatkiWszystkichPoslow(poslowie));
					break;
					
				case "NajwiecejPodrozy":
					requireArgs(2);			
					System.out.println(podroze_poslow.najwiecejPodrozy(poslowie));
					break;
					
				case "NajdrozszaPodroz":
					requireArgs(2);
					System.out.println(podroze_poslow.najdrozszaPodroz(poslowie));
					break;
					
				case "NajdluzejZaGranica":
					requireArgs(2);
					System.out.println(podroze_poslow.najwiecejZaGranica(poslowie));
					break;
					
				case "OdwiedzoneWlochy":
					requireArgs(2);
					List <String> wloscyposlowie = podroze_poslow.odwiedzoneWlochy(poslowie);
					for(String posel : wloscyposlowie){
						System.out.println(posel);
					}
					break;
					
				case "WydatkiPosla":
					
					requireArgs(4);
					Posel szukany_posel = parlament.znajdzPosla(args[2]+" "+args[3]);
					
					if(szukany_posel!=null){
						System.out.println(wydatki_poslow.znajdzWydatki(szukany_posel));
					}
					break;
				
				case "DrobneNaprawy":
					requireArgs(4);
					Posel szukany_posel2 = parlament.znajdzPosla(args[2]+" "+args[3]);
					if(szukany_posel2!=null){
						System.out.println(wydatki_poslow.znajdzDrobneNaprawy(szukany_posel2));
					}
					break;
				
				default:
					throw new InvalidInput("Bledne dane");
					
			}
		}
		}
		catch(InvalidInput i ){
			System.out.println(i.getMessage());
				
		}
				
	
	}
		
	
	public int argsCount(){
		return args.length;
	}
	
	public void requireArgs (int i) throws InvalidInput{
		if (args.length!=i)	throw new InvalidInput("Wymagana ilosc argumentow to"+Integer.toString(i));
	}

	                 
	
}
