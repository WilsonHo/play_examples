package examples.json.util.java8;

import java.util.function.Function;

/**
 * Created by wilson on 3/3/17.
 */
public class ComposeExample {
    public static void main(String[] args) {
        ComposeExample ex = new ComposeExample();
        Function<Double , Double> sin = d -> ex.sin(d);
        Function<Double , Double> log = d -> ex.log(d);
        Function<Double , Double> exp = d -> ex.exp(d);
        ComposeExample compose = new ComposeExample();
        System.out.println(compose.calculate(sin.compose(log), 0.8));
        // prints log:sin:-0.22
        System.out.println(compose.calculate(sin.andThen(log), 0.8));
        // prints sin:log:-0.33
        System.out.println(compose.calculate(sin.compose(log).andThen(exp), 0.8));
        //log:sin:exp:0.80
        System.out.println(compose.calculate(sin.compose(log).compose(exp), 0.8));
        //exp:log:sin:0.71
        System.out.println(compose.calculate(sin.andThen(log).compose(exp), 0.8));
        //exp:sin:log:-0.23
        System.out.println(compose.calculate(sin.andThen(log).andThen(exp), 0.8));
        //sin:log:exp:0.71

    }

    public Double calculate(Function<Double , Double> operator, Double d) {
        return operator.apply(d);
    }

    public Double sin(Double d) {
        System.out.print("sin:");
        return Math.sin(d);
    }

    public Double log(Double d) {
        System.out.print("log:");
        return Math.log(d);
    }

    public Double exp(Double d) {
        System.out.print("exp:");
        return Math.exp(d);
    }
}
