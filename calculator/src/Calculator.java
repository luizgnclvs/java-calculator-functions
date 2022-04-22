public class Calculator {

    public static double absoluteValue (double num) {

        if (num < 0) {
            num *= -1;
        }

        return num;
    }

    public static double round (double decimal) {

        double decimalPoint = absoluteValue(decimal) - roundDown(absoluteValue(decimal));

        if (decimalPoint < 0.5) {
            return roundDown(decimal);
        } else {
            return roundUp(decimal);
        }
    }

    public static double roundDown (double decimal) {

        if (decimal < 0) {
            decimal = roundUp(absoluteValue(decimal)) * -1;
        } else {
            String integer = String.format("%f", decimal);

            integer = integer.substring(0, integer.indexOf("."));
    
            decimal = Double.parseDouble(integer);
        }

        return decimal;
    }

    public static double roundUp (double decimal) {

        if (decimal < 0) {
            return decimal = roundDown(absoluteValue(decimal)) * -1;
        } else {
            return decimal = roundDown(decimal) + 1;
        }
    }

    public static double roundWithPrecision (double decimal, int digits) throws Exception {

        if (digits < 0) {
            throw new Exception("Índice inválido.");
        }

        if (digits == 0) {
            return round(decimal);
        } else {
            return round(decimal * toThePowerOf(10, digits)) / toThePowerOf(10, digits);
        }
    }

    public static int findGCD (int x, int y) {

        int gcd = 1;

        for (int i = 1; i <= x && i <= y; i++) {
            if (x % i == 0 && y % i == 0) {
                gcd = i;
            }
        }

        return gcd;
    }

    public static int findLCM (int x, int y) {

        int lcm = 1, i; 

        if (x > y) {
            i = x;
        } else {
            i = y;
        }

        while (true) {
            if (i % x == 0 && i % y == 0) {
                lcm = i;
                break;
            } else {
                i++;
            }
        }

        return lcm;
    }

    public static double toThePowerOf (double base, double exponent) throws Exception {

        double power = 1;

        if (absoluteValue(exponent) - roundDown(absoluteValue(exponent)) != 0) {
            String numerator = FractionNotation.stringToArray(Double.toString(exponent))[1];
            String denominator = FractionNotation.stringToArray(Double.toString(exponent))[2];

            power = nthRootOf(toThePowerOf(base, Double.parseDouble(numerator)), Double.parseDouble(denominator));
        } else {
            if (exponent < 0) {
                for (int i = 0; i < absoluteValue(exponent); i++) {
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

    public static double nthRootOf (double a, double n) throws Exception {

        double guess = Math.random() % 10;
        double accuracy = 0.000001;
        double diffRoots = 2147483647;
        double currentIteration = 0.0;
        
        while (diffRoots > accuracy) {
            currentIteration = ((n - 1.0) * guess + a / toThePowerOf(guess, n - 1)) / n;
            diffRoots = absoluteValue(currentIteration - guess);
            guess = currentIteration;
        }

        return round(currentIteration * 1000000.0) / 1000000.0;
    }
}