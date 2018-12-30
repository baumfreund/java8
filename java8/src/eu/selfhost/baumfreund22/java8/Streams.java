package eu.selfhost.baumfreund22.java8;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import eu.selfhost.baumfreund22.java8.objects.Person;

public class Streams {

	public static void main(String[] args) {

//		Methodenreferenz
		List<String> names = Arrays.asList("Hugo", "Egon", "Mad", "Tom");
		
//		names.forEach(currentName -> System.out.println(currentName));
		
		names.forEach(System.out::println);
		
		List<String> myList =
			    Arrays.asList("a1", "a2", "b1", "c2", "c1");

			myList
			    .stream()
			    .filter(s -> s.startsWith("c"))
			    .map(String::toUpperCase)
			    .sorted()
			    .forEach(System.out::println);
		
		
//		Streams
		List<Person> personen = createPersonen();
		personen.stream().filter(Person::isAdult).collect(Collectors.toList()).forEach(e -> System.out.println(e.getName()));
		
//		Liste Erwachsene
		List<Person> erwachsene = new LinkedList<>();
		personen.stream().filter(Person::isAdult).collect(Collectors.toList()).forEach(erwachsene::add);
		
//		Alter der Personen die über 18 sind rausfiltern; Namen aller Personen rausfiltern
		Stream<Integer> ages = personen.stream().map(person -> person.getAge()).filter(age -> age > 18);
		Stream<String> nameStream = personen.stream().map(person -> person.getName());
		
		ages.forEach(System.out::println);
		nameStream.forEach(System.out::println);
		
//		Anzahl der Personen über 18
		int anzahl = (int) personen.stream().map(person -> person.getAge()).filter(age -> age > 18).count();
		
//		Das Predicate kann auch erzeugt und übergeben werden
		Predicate<Person> personFilter = person -> person.getAge() > 18;
		List<Person> erwachsene2 = personen.stream().filter(personFilter).collect(Collectors.toList());
		
		
//		Parallel Stream
		Map<String, List<Person>> nachGeschlecht = personen.stream().collect(Collectors.toList()).parallelStream().collect(Collectors.groupingBy(Person::getSex));
		nachGeschlecht.get("m").forEach(u -> System.out.println(u.getName()));
		
//		Streams erzeugen
		Stream<String> stringStream = Stream.of("Hans", "Anja", "Thomas");
		Stream<Integer> intStream = Stream.of(1,2,40,60,100,200);
		
		intStream.filter(e -> e > 40).collect(Collectors.toList()).forEach(e -> System.out.println("Zahl: "+e));
//		stringStream.forEach(e -> System.out.println(e));
		
//		Verwendung von IntStream
		IntStream range = IntStream.range(0, 100);
		range.forEach(e -> System.out.println(e));
		
//		Primitiv-Streams können in Object umgewandelt werden - eine Neuinitialisierung ist erforderlich,
//		da es sich bei foreach um eine terminierende aktion handelt
//		range.boxed();
		range = IntStream.range(0, 100);
		Stream<Integer> intAsObjStream = range.mapToObj(e -> Integer.valueOf(e));
		intAsObjStream.forEach(e -> System.out.println(e));
		
//		Auch möglich LongStream, DoubleStream
		LongStream longStream = LongStream.range(100, 200);
//		range macht vermutlich bei Double keinen sinn
		DoubleStream doubleStream = DoubleStream.of(10.4, 3.2, 6.5);
		
//		Namen als komma getrennten String
		String kommanames = stringStream.sorted().collect(Collectors.joining(","));
		System.out.println(kommanames);
		
//		Unendliche Streams
		IntStream iterStream = IntStream.iterate(0, e -> e +1);
		AtomicInteger atomicInt = new AtomicInteger(0);
		
		intStream = Stream.generate(atomicInt::getAndIncrement);
		
		int[] intArray = iterStream.limit(10).toArray();
		Object[] atomicIntArray = intStream.limit(10).toArray();
		
		System.out.println("IntStream: "+Arrays.toString(intArray));
		System.out.println("AtomicInteger: "+Arrays.toString(atomicIntArray));
//		atomicIntArray ist ein Object[] mit Integer Objekten
		System.out.println("Datentyp AtomicInteger: "+atomicIntArray[0].getClass().getTypeName());
		
		
		
		
		
	}

	

	public static List<Person> createPersonen() {
		LinkedList<Person> liste = new LinkedList<Person>();
		liste.add(new Person("Tom", 37, "m"));
		liste.add(new Person("Kerstin", 16, "w"));
		return liste;
	}
}
