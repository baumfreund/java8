package eu.selfhost.baumfreund22.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JButton;

import eu.selfhost.baumfreund22.java8.objects.Person;

public class Lambda {

	public static void main(String[] args) {

//		SAM Typ = Interface mit nur einer abstrakten Methode - Ausnahme Methoden von Object (z.B. bei Comparator s.u.) erlaubt	
		Runnable runnable = () -> System.out.println("Runnable as Lambda");
		runnable.run();
		
		Comparator<String> compi = (final String str1, final String str2) -> {
			System.out.println("Vergleiche: \""+str1+"\" mit \""+str2+"\"");
			return Integer.compare(str1.length(), str2.length());
		};
		
		System.out.println(compi.compare("Tom", "Hase"));
		
//		Durch Type Inference (Typ Ableitung) kann der Datentyp weggelassen werden. Wird nur ein Parameter übergeben, können
//		auch die Klammern weggelassen werden
		Comparator<String> compiTypeInference = (str1, str2) -> {
			System.out.println("Vergleiche: \""+str1+"\" mit \""+str2+"\"");
			return Integer.compare(str1.length(), str2.length());
		};
		
		System.out.println(compiTypeInference.compare("Hase", "Tom"));
	
		
//		JButton.addActionListener als Lambda und mit Type Inference
		JButton button = new JButton();
		button.addActionListener(e -> System.out.println("Button clicked"));
		button.doClick();
		
//		Auch möglich
		button.addActionListener((e) -> { System.out.println("Button extended clicked");});
		button.doClick();
		
		
		
//		Methodenreferenz
		List<String> names = Arrays.asList("Hugo", "Egon", "Mad", "Tom");
		
//		names.forEach(currentName -> System.out.println(currentName));
		
		names.forEach(System.out::println);
		
		
//		Streams
		List<Person> personen = createPersonen();
		personen.stream().filter(Person::isAdult).collect(Collectors.toList()).forEach(e -> System.out.println(e.getName()));
		
//		Liste Erwachsene
		List<Person> erwachsene = new LinkedList<>();
		personen.stream().filter(Person::isAdult).collect(Collectors.toList()).forEach(erwachsene::add);
		
//		Parallel Stream
		Map<String, List<Person>> nachGeschlecht = personen.stream().filter(Person::isAdult).collect(Collectors.toList()).parallelStream().collect(Collectors.groupingBy(Person::getSex));
		
		
		
		
	}

	private static List<Person> createPersonen() {
		LinkedList<Person> liste = new LinkedList<Person>();
		liste.add(new Person("Tom", 37, "m"));
		liste.add(new Person("Kerstin", 16, "w"));
		return liste;
	}

}
