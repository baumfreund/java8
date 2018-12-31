package eu.selfhost.baumfreund22.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;

public class Completable {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		
//		Asynchrone Abläufe über CompletableFuture
		
		Supplier<String> longRunningAction = () -> {
			System.out.println("Thread: "+Thread.currentThread());
			return "101";
		};
		
//		Aufruf des Suppliers
		CompletableFuture<String> step1 = CompletableFuture.supplyAsync(longRunningAction);

//		Anschließend den String des Suppliers konvertieren in Integer
		Function<String,Integer> converter = Integer::parseInt;
		CompletableFuture<Integer> step2 = step1.thenApply(converter);
		
//		Integer multiplizieren mit 0.75 und Rückgabe Ergebnis als Double
		Function<Integer,Double> multi = value -> 0.75 * value;
		CompletableFuture<Double> step3 = step2.thenApply(multi);
		
//		Ausführen
		System.out.println(step3.get());
		
		
//		=======================================================================================
		
		
		
		
		
		
	}

}
