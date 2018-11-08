import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author Lars Kowoll, Philip Zirfa�
 * @version 11/2018
 * 
 */

public class A1<T> {
	
	public static void main(String[] args) {
		String[] eingaben = { "Eingabe ", "�u�eres ", null, "Strassen-Feger", " ein Haus" };
		A1<String> stringVeraenderer = new A1<String>();
		List<String> liste = stringVeraenderer.veraendern(eingaben);
		liste.forEach(System.out::println);
	}
	
	/**
	 * Ver�ndert Eingaben, die in einem Array �bergeben werden.
	 * 
	 * @param eingaben	Array, das Eingaben enth�lt
	 * @return Liste, die ver�nderte Eingaben enth�lt
	 */
	public List<T> veraendern(String[] eingaben) {
		Stream<String> stringStream = Stream.of(eingaben);
		List<String> myList = stringStream
				.filter(x -> x!=null)
				.map(String::trim)
				.map(x -> x.toUpperCase())
				.map(x -> x.replace("�","AE").replace("�","OE").replace("�","UE").replace("�","SS") )
                .map(x -> x = kuerzenAufAcht(x))
				.collect(Collectors.toList());
		
		return (List<T>) myList;
		
	}
	
	/**
	 * K�rzt String auf 8 Zeichen.
	 * 
	 * @param str	String, der auf 8 Zeichen gek�rzt wird
	 * @return gek�rzter String
	 */
	private String kuerzenAufAcht(String str){
        if(str.length() > 8){
            return str = str.substring(0,8);
        } 
        return str;
    }
}
