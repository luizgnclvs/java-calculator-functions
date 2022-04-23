public class Calculator {

    public static double absoluteValue (double num) {

        if (num < 0) {
            num *= -1;
        }

        return num;
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

        if (absoluteValue(exponent) - Rounding.roundDown(absoluteValue(exponent)) != 0) {
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

        return Rounding.roundRandomTieBreaker(currentIteration * 1000000.0) / 1000000.0;
    }
}