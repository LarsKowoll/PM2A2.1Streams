import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class A1<T> {
	public static void main(String[] args) {
		String eingaben[] = { "Eingabe ", "Äußeres ", null, "Strassen-Feger", " ein Haus" };
		Stream<String> stringStream = Stream.of(eingaben);
		List<String> myList = stringStream
				.filter(x -> x!=null)
				.map(String::trim)
				.map(x -> x.toUpperCase())
				
				.collect(Collectors.toList());

		myList.forEach(System.out::println);
	}
		
}
