public class Calculator {

    public static double absoluteValue (double number) {

        if (number < 0) {
            number *= -1;
        }

        return number;
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

    public static double nthRootOf (double radicand, double degree) throws Exception {
        //Newton's method

        //generates a random first guess that kickstarts the process of finding the root
        double firstGuess = Math.random() % 10;
        //defines the level of accuracy of the root by the amount of zeroes between the decimal point and the '1'
        double accuracy = 0.000001;
        //initializes the difference between the two roots with INTMAX
        double diff = 2147483647;
        //denotes current value of the root
        double root = 0.0;

        //does the proccess again and again until it reaches the desired accuracy
        while (diff > accuracy) {
            //calculates the current root value using Newton's method
            root = ((degree - 1.0) * firstGuess + radicand / toThePowerOf(firstGuess, degree - 1)) / degree;
            diff = absoluteValue(root - firstGuess);
            firstGuess = root;
        }

        return Rounding.roundRandomTieBreaker(root * 1000000.0) / 1000000.0;
    }
}