package examples.json.util.java8;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;

/**
 * Created by wilson on 3/3/17.
 */
public class Example {

    public int add(int a, int b) {
        return a + b;
    }

    public static int mul(int a, int b) {
        return a * b;
    }

    public String lower(String a) {
        return a.toLowerCase();
    }

    public void printDate(Date date) {
        System.out.println(date);
    }

    public void oper(IntBinaryOperator operator, int a, int b) {
        System.out.println(operator.applyAsInt(a, b));
    }

    public void operS(Function<String, String> stringOperator, String a) {
        System.out.println(stringOperator.apply(a));
    }

    public GregorianCalendar operC(Supplier<GregorianCalendar> supplier) {
        return supplier.get();
    }

    public static void main(String[] args) {
        Example ex = new Example();
        ex.oper((a, b) -> Example.mul(a, b), 1, 1);
        ex.oper(Example::mul, 1, 2);

        ex.oper((a, b) -> ex.add(a, b), 1, 2);
        ex.oper((a, b) -> ex.add(a, b), 1, 2);

        ex.operS(s -> s.toLowerCase(), "STRING");
        ex.operS(String::toLowerCase, "STRING");

        ex.operC(() -> {
            return new GregorianCalendar();
        });
        ex.operC(GregorianCalendar::new);

    }

}
