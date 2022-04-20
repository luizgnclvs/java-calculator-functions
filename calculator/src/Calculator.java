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

    // * round with precision

    public static int findGCD (double x, double y) {

        int gcd = 1;

        for (int i = 1; i <= x && i <= y; i++) {
            if (x % i == 0 && y % i == 0) {
                gcd = i;
            }
        }

        return gcd;
    }

    // * find LCM

    public static double toThePowerOf (double base, double exponent) {

        double power = 1;

        if (absoluteValue(exponent) - Math.floor(absoluteValue(exponent)) != 0) {
            String fraction = Notation.convertToFraction(exponent)[0];

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

        return round(currentIteration * 1000000.0) / 1000000.0;
    }
}