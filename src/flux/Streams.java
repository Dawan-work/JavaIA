package flux;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {
    public static void main(String[] args) {
        String[] months = DateFormatSymbols.getInstance().getMonths();
        Stream<String> monthsStream = Arrays.stream(months); // Flux
        System.out.println("--- Lambda ---");
        monthsStream.forEach(m -> System.out.println(m)); // Foreach final operation
        System.out.println("--- Method Reference ---");
        Arrays.stream(months).forEach(System.out::println);

        // Stream used to change collection type
        List<String> monthList = Arrays.stream(months).toList(); // Collect final operation

        System.out.println("--- To uppercase before print ---");
        monthList.stream() // Stream<String> (in lower case)
                .map(String::toUpperCase) // Stream<String> (in upper case)
                .forEach(System.out::println);

        System.out.println("--- Month with more 4 characters ---");
        monthList.stream()
                .filter(month -> month.length() == 4)
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("--- Month Blank String Count ---");
        System.out.println("Blank string count : " + monthList.stream()
                .filter(String::isBlank)
                .count()); // Count final operation

        /* Will not work on Immutable List
        monthList.stream()
                .filter(String::isBlank)
                .findAny() // If at least one result and return it in Optional
                .ifPresent(monthList::remove);*/

        System.out.println("--- Replacing List without blank ---");
        monthList = monthList.stream()
                .filter(s -> !s.isBlank())
                .toList();

        System.out.println("--- Months with length <= 5 au format 'm1,m2,m3' ---");
        System.out.println(monthList.stream().filter(m -> m.length() <= 5).collect(Collectors.joining(",")));

        System.out.println("--- List containing at least one element with special char(s) ---");
        System.out.println(monthList.stream().anyMatch(month -> !month.matches("[a-z]*")));
        System.out.println(monthList.stream()
                .filter(month -> !month.matches("[a-z]*"))
                .collect(Collectors.joining(",")));

        System.out.println("--- Total characters ---");
        System.out.println(
        monthList.stream()
                .map(String::length)
                //.reduce(0, (currentResult, currentValue) -> currentResult + currentValue)
                .reduce(0, Integer::sum));

        System.out.println("--- Last 3 sorted by length ---");
        monthList.stream()
                /*.sorted((currentMonth, nextMonth) -> {
                    int currentMontLength = currentMonth.length(),
                            nextMonthLength = nextMonth.length();
                    return currentMontLength - nextMonthLength;
                })*/
                .sorted(Comparator.comparingInt(String::length))
                .skip(monthList.size() - 3)
                .forEach(System.out::println);

        System.out.println("--- First half of the list ---");
        monthList.subList(0, monthList.size() / 2).forEach(System.out::println);
    }
}
