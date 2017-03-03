package examples.json.util.java8;

import java.util.*;

/**
 * Created by wilson on 3/2/17.
 */
public class StreamsDemo {
    public static void main(String[] args) {
        List<String> genre = new ArrayList<>(Arrays.asList("rock", "pop", "jazz", "reggae"));
        long a = genre.stream().filter(s -> s.startsWith("r")).count();
        System.out.println(a);

        genre.stream().allMatch(s -> !s.isEmpty());
        genre.stream().anyMatch(s -> s.indexOf("r") == 0);

        System.out.println(genre.stream().peek(s->System.out.println(s)).anyMatch(s -> s.indexOf("r") == 0));
        System.out.println(genre.stream().peek(s->System.out.println(s)).count());


        List<String> genre1 = new ArrayList<String>(Arrays.asList("rock", "pop", "jazz", "reggae","pop"));
        System.out.println(genre1.stream().distinct().count()); // prints 4

        System.out.println(genre.stream().filter(s -> s.length() <= 4).count());
        genre.stream().forEach(System.out::println);

        Optional<String> combinedgenre = genre.stream().reduce((b, c) -> b.concat(",").concat(c));

        int d = genre.parallelStream().reduce(0, (b, c) -> b + c.length(), (b, c) -> b + c);

        System.out.println(genre.parallelStream().reduce(3, (b, c) -> b + c.length(), (b, c) -> b + c));

        HashSet<String> genreSet = genre.stream().collect(() -> new HashSet<>(), (b, c) -> b.add(c), (b, c) -> b.addAll(c));
    }
}
