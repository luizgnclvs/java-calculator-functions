import java.util.Random;

public class Calculator {
    
    public static double toThePowerOf (double base, double exponent) {
        double power = 1;

        String exponentStr = Double.toString(exponent);
        exponentStr = exponentStr.substring(exponentStr.indexOf(".") + 1);
        int decimal = Integer.parseInt(exponentStr);

        if (decimal > 0) {
            String fraction = convertToFraction(exponent);
            double numerator = Double.parseDouble(fraction.substring(0, fraction.indexOf("/")));
            double denominator = Double.parseDouble(fraction.substring(fraction.indexOf("/") + 1));

            power = nthRootOf(toThePowerOf(base, numerator), denominator);
        } else {
            if (exponent < 0) {
                for (int i = 0; i < Math.abs(exponent); i++) {
                    power /= base;
                }
            } else {
                for (int i = 0; i < exponent; i++) {
                    power *= base;
                }
            }
        }

        return power;
    }

    public static double nthRootOf (double a, double n) {
        double guess = Math.random() % 10;
        double accuracy = 0.000001;
        double diffRoots = 2147483647;
        double currentIteration = 0.0;
        while (diffRoots > accuracy) {
            currentIteration = ((n - 1.0) * guess + a / toThePowerOf(guess, n - 1)) / n;
            diffRoots = Math.abs(currentIteration - guess);
            guess = currentIteration;
        }

        return Math.round(currentIteration * 10000000000000000.0) / 10000000000000000.0;
    }

    public static int findGCD (double x, double y) {
        int gcd = 1;
        
        for (int i = 1; i <= x && i <= y; i++) {
            if (x % i == 0 && y % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    public static String convertToFraction(double n) {
        int whole = (int)Math.floor(n);
        double decimal = n - whole;

        String decimalStr = Double.toString(decimal);
        decimalStr = decimalStr.substring(2);
        int numerator = Integer.parseInt(decimalStr);

        int power = decimalStr.length();
        int denominator = (int)toThePowerOf(10, power);
        
        int gcd = findGCD(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
        numerator += (denominator * whole);

        return Integer.toString(numerator) + "/" + Integer.toString(denominator);
    }

    public static void main(String[] args) {
        
        double x = nthRootOf(2, 4);
        double y = toThePowerOf(2, 1/4.0);
        System.out.print(x + " " + y);
    }
}
