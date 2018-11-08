import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author Lars Kowoll, Philip Zirfaß
 * @version 11/2018
 * 
 */

public class A1<T> {
	
	public static void main(String[] args) {
		String[] eingaben = { "Eingabe ", "Äußeres ", null, "Strassen-Feger", " ein Haus" };
		A1<String> stringVeraenderer = new A1<String>();
		List<String> liste = stringVeraenderer.veraendern(eingaben);
		liste.forEach(System.out::println);
	}
	
	/**
	 * Verändert Eingaben, die in einem Array übergeben werden.
	 * 
	 * @param eingaben	Array, das Eingaben enthält
	 * @return Liste, die veränderte Eingaben enthält
	 */
	public List<T> veraendern(String[] eingaben) {
		Stream<String> stringStream = Stream.of(eingaben);
		List<String> myList = stringStream
				.filter(x -> x!=null)
				.map(String::trim)
				.map(x -> x.toUpperCase())
				.map(x -> x.replace("Ä","AE").replace("Ö","OE").replace("Ü","UE").replace("ß","SS") )
                .map(x -> x = kuerzenAufAcht(x))
				.collect(Collectors.toList());
		
		return (List<T>) myList;
		
	}
	
	/**
	 * Kürzt String auf 8 Zeichen.
	 * 
	 * @param str	String, der auf 8 Zeichen gekürzt wird
	 * @return gekürzter String
	 */
	private String kuerzenAufAcht(String str){
        if(str.length() > 8){
            return str = str.substring(0,8);
        } 
        return str;
    }
}
