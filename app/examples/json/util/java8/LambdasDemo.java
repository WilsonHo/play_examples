package examples.json.util.java8;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.function.*;

/**
 * Created by wilson on 3/3/17.
 */
public class LambdasDemo {

    static Function<String, String> function1 = x -> x.toUpperCase();
    static Function<String, String> function2 = x -> x.toLowerCase();

    public static void convertString(Function<String, String> function){
        System.out.println(function.apply("StRaNgE"));
    }

    static Supplier<String> supplier1 = () -> "String1";
    static Supplier<String> supplier2 = () -> "String2";

    public static void printSuppliedString(Supplier<String> supplier){
        System.out.println(supplier.get());
    }


    static Consumer<String> consumer1 = x -> System.out.println(x);
    static Consumer<String> consumer2 = x -> System.out.println(x.toLowerCase());
    public static void consumeString(Consumer<String> consumer, String x) {
        consumer.accept(x);
    }


    static Predicate<Double> predicate1 = x -> x > 10;
    static Predicate<Double> predicate2 = x -> x < -10;
    public static boolean testValue(Predicate<Double> predicate, Double d){
        return predicate.test(d);
    }
    public static void main(String[] args) {
        convertString(function1);// prints STRANGE
        convertString(function2);// prints strange

        printSuppliedString(supplier1);
        printSuppliedString(supplier2);

        consumeString(consumer1, "StringA");// prints StringA
        consumeString(consumer2,"StringA");// prints stringa

        System.out.println(testValue(predicate1,new Double(9)));// prints false
        System.out.println(testValue(predicate2,new Double(-20)));// prints true

    }
}
