package examples.json.util.java8;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Created by wilson on 3/3/17.
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        Map<String, Integer> authorBooks = new HashMap<String, Integer>();
        authorBooks.put("Robert Ludlum", 27);
        authorBooks.put("Clive Cussler", 50);
        authorBooks.put("Tom Clancy", 17);

        authorBooks.computeIfAbsent("Agatha Christie", b -> b.length());
        System.out.println(authorBooks);

        authorBooks.computeIfPresent("Tom Clancy", (a, b) -> b + 1);
        System.out.println(authorBooks);

        System.out.println(authorBooks.getOrDefault("AuthorA", 0));

        authorBooks.merge("AuthorB", 1, (a, b) -> a + b);
        System.out.println(authorBooks.get("AuthorB"));// 1
        authorBooks.merge("AuthorB", 1, (a, b) -> a + b);
        System.out.println(authorBooks.get("AuthorB"));//2
        authorBooks.merge("AuthorB", 1, (a, b) -> null);
        System.out.println(authorBooks.get("AuthorB"));//2

        System.out.println(authorBooks);
        authorBooks.merge("AuthorB", 9, (a, b) -> null);
        System.out.println(authorBooks);


        System.out.println(authorBooks.putIfAbsent("AuthorC", 2));//null
        System.out.println(authorBooks.putIfAbsent("AuthorC", 2));//2
        System.out.println(authorBooks);

        authorBooks.remove("AuthorC", 3);
        System.out.println(authorBooks);

        authorBooks.remove("AuthorC", 2);
        System.out.println(authorBooks);


        BiFunction<String, Integer, Integer> demo = (String a, Integer b) -> {
            if (a.startsWith("A")) {
                return a.length() + b;
            }
            return 100;
        };
        authorBooks.replaceAll(demo);
        System.out.println(authorBooks);


    }

    @FunctionalInterface
    interface Function2<A, B, C> {
        public C apply(A a, B b);
    }
}
