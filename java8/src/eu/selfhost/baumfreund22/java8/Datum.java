package eu.selfhost.baumfreund22.java8;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.Locale;
import java.util.stream.Stream;

public class Datum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Instant ist vergleichbar mit Date
		Instant abfahrt = Instant.parse("2018-12-30T09:00:00Z");
		Instant geplanteAnkunft = abfahrt.plus(Duration.ofHours(5));
		Instant hotelAnkunft = geplanteAnkunft.plus(Duration.ofMinutes(30));
		
		Stream<Instant> datum = Stream.of(abfahrt, geplanteAnkunft, hotelAnkunft);
		datum.forEach(e -> System.out.println(e));
		
//		Duration = für Zeiten bis maximale Einheit Stunden. Für längere Zeiträume Period verwenden
		Duration reisedauer = Duration.between(hotelAnkunft, abfahrt);
		System.out.println("Reisedauer: "+reisedauer);
	
		Instant now = Instant.now();
		System.out.println(now);
		
		
//		LocalDate, LocalTime und LocalDateTime
		LocalDate localDate = LocalDate.of(2018, 10, 1);

//		Letzter Tag des Monats
		System.out.println("Letzter Tag des Monats: "+localDate.with(TemporalAdjusters.lastDayOfMonth()));
		
		localDate = localDate.minus(2, ChronoUnit.DAYS);
		System.out.println(localDate);
		
		
//		Period = Equivalent zu Duration allerdins ab Tage aufwärts
		Period periode1 = Period.ofYears(5);
		System.out.println("Heute in 5 Jahren: "+LocalDate.now().plus(periode1));
		
		
	}

}
