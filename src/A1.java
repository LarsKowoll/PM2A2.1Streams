import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class A1<T> {
	public static void main(String[] args) {
		String eingaben[] = { "Eingabe ", "Äußeres ", null, "Strassen-Feger", " ein Haus" };	
		veraendern(eingaben);
	}
	
	public static List<String> veraendern(String[] eingaben) {
		Stream<String> stringStream = Stream.of(eingaben);
		List<String> myList = stringStream
				.filter(x -> x!=null)
				.map(String::trim)
				.map(x -> x.toUpperCase())
				.map(x -> x.replace("Ä","AE").replace("Ö","OE").replace("Ü","UE").replace("ß","SS") )
                .map(x -> x = kuerzenAufAcht(x))
				.collect(Collectors.toList());

		myList.forEach(System.out::println);
		return myList;
		
	}
	
	private static String kuerzenAufAcht(String str){
        if(str.length() > 8){
            return str = str.substring(0,8);
        } 
        return str;
    }		
}
