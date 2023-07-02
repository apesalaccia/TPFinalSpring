package co.com.worldoffice.utils;

public class Utility {

    public static double discount(double price, Integer discount, Integer amount) {

        double result = 0;

        if (discount != 0)
            result = (discount * price) / 100;

        return result = (price - result) * amount;

    }

}