package eu.selfhost.baumfreund22.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

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
		
		
		

		
	}


}
