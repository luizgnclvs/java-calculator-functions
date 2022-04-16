public class Calculator {

    public static double toThePowerOf (double base, double exponent) {

        double power = 1;

        if (absoluteValue(exponent) - Math.floor(absoluteValue(exponent)) != 0) {
            String fraction = Notation.convertToFraction(exponent);

            double numerator = Double.parseDouble(fraction.substring(0, fraction.indexOf("/")));
            double denominator = Double.parseDouble(fraction.substring(fraction.indexOf("/") + 1));

            power = nthRootOf(toThePowerOf(base, numerator), denominator);
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

    public static double nthRootOf (double a, double n) {

        double guess = Math.random() % 10;
        double accuracy = 0.000001;
        double diffRoots = 2147483647;
        double currentIteration = 0.0;
        
        while (diffRoots > accuracy) {
            currentIteration = ((n - 1.0) * guess + a / toThePowerOf(guess, n - 1)) / n;
            diffRoots = absoluteValue(currentIteration - guess);
            guess = currentIteration;
        }

        return Math.round(currentIteration * 1000000.0) / 1000000.0;
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

    public static double absoluteValue (double n) {

        if (n < 0) {
            n *= -1;
        }

        return n;
    }

    public static double roundDown (double n) {

        String num = Double.toString(n);

        num = num.substring(0, num.indexOf("."));

        n = Double.parseDouble(num);

        return n;
    }
}