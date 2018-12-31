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
		
//		Keine Prüfung mehr auf null erforderlich mit Optional - Reduzierung von NPE, da Optional behandelt werden müssen
//		NullObject-Pattern dadurch zumindest teilweise nicht mehr erforderlich
		Optional<Person> hugo = findPerson("Hugo");
		if(hugo.isPresent()) {
			System.out.println("Hugo ist "+hugo.get().getAge()+" Jahre alt.");
		} else {
			System.out.println("Hugo wurde nicht gefunden");
		}
		
		
		
//		hugo.orElse(other)
//		hugo.orElseGet(other)
//		hugo.orElseThrow(exceptionSupplier)
		
		
//		Für primitive Typen existierten die Optional: OptionalInt, OptionalLong, OptionalDouble

		
	}

	
	
	private static Optional<Person> findPerson(String name) {
		List<Person> toms = Streams.createPersonen().stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());
		return toms.stream().findFirst();
	}
}
