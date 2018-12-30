package eu.selfhost.baumfreund22.java8;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import eu.selfhost.baumfreund22.java8.objects.Person;

public class CompareAndOptional {

	public static void main(String[] args) {

		Comparator<Person> compi = Comparator.comparing(Person::getName);
		List<Person> personen = Streams.createPersonen();
				
		personen.sort(compi);
		personen.stream().forEach(person -> System.out.println(person.getName()));
		
//		Comparatoren lassen sich auch verbinden
		Comparator<Person> compiAge = Comparator.comparing(Person::getAge);
		compi.thenComparing(compiAge);
		
		Optional<Person> tom = findPerson("Tom");
		if(tom.isPresent()) {
			System.out.println("Es gibt Tom!");
		}
		
		Optional<Person> hugo = findPerson("Hugo");
		if(hugo.isPresent() == false) {
			System.out.println("Hugo existiert nicht!");
		}
		
//		Für primitive Typen existierten die Optional: OptionalInt, OptionalLong, OptionalDouble
//		test

		
	}

	
	
	private static Optional<Person> findPerson(String name) {
		List<Person> toms = Streams.createPersonen().stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());
		return toms.stream().findFirst();
	}
}
