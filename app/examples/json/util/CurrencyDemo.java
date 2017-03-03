package examples.json.util;

import java.util.Currency;

/**
 * Created by wilson on 2/28/17.
 */
public class CurrencyDemo {
    public static void main(String[] args) {
        // create a currency with eur code
        Currency curr = Currency.getInstance("EUR");

        // get currency code and print it
        String curCode = curr.getCurrencyCode();
        System.out.println("CurrencyDemo Code is = " + curCode);
    }
}
