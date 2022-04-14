public class Calculator {

    public static boolean isNumeric (String str) {
        boolean numeric = false;

        String number = "-?[0-9]+";
        String decimalNum = number + "[,\\.]?[0-9]+";
        String bar = "[./]";
        
        if (str.matches(number) || //matches with any integer number, positive or not
            str.matches(decimalNum) || //matches with any decimal number, positive or not
            str.matches(number + bar + number) || //matches with any fraction with both numerator and denominator as integers, whether they be positive or not
            str.matches(decimalNum + bar + number) || //matches with any fraction with a decimal numerator and integer denominator, whether they be positive or not
            str.matches(number + bar + decimalNum) || //matches with any fraction with a integer numerator and decimal denominator, whether they be positive or not
            str.matches(decimalNum + bar + decimalNum)) { //matches with any fraction with both numerator and denominator as decimals, whether they be positive or not
            numeric = true;
        }
        return str != null && numeric;
    }

    public static int countOccurences(String str, char searchedChar, int index) {
        if (index >= str.length()) {
            return 0;
        }
        
        int count;
        if (str.charAt(index) == searchedChar) {
            count = 1;
        } else {
             count = 0;
        }
        return count + countOccurences(str, searchedChar, index + 1);
    }
    
    public static String formatNumber (String str) {
        boolean negative = false;
        if (countOccurences(str, '-', 0) % 2 != 0) {            
            negative = true;
        }

        while (countOccurences(str, '-', 0) > 0) {           
            StringBuilder sb = new StringBuilder(str);
            sb.deleteCharAt(str.indexOf('-'));
            str = sb.toString();
        }

        boolean fraction = false;
        if (str.matches("-?[0-9]+[,\\.]?[0-9]*./-?[0-9]+[,\\.]?[0-9]*")) {
            fraction = true;
            String numerator = str.substring(0, str.indexOf("/"));
            String denominator = str.substring(str.indexOf("/") + 1);
            numerator = formatNumber(numerator);
            denominator = formatNumber(denominator);
            str = numerator + "/" + denominator;
        }

        boolean decimal = false;
        if (str.matches("-?[0-9]+[,\\.][0-9]+")) {
            decimal = true;
            if (str.substring(str.indexOf("[,\\.]") + 1).matches("[0]+.[1-9]") == false) {
                String divide = ".";
                if (str.matches("-?[0-9]+,[0-9]+")) {
                    divide = ",";
                }
                str = str.substring(0, str.indexOf(divide));
                decimal = false;
            }    
        }

        if (negative) {
            str = "-" + str;
        }
        return str;
    }

    public static double toThePowerOf (double base, double exponent) {
        double power = 1;

        if (absoluteValue(exponent) - Math.floor(absoluteValue(exponent)) != 0) {
            String fraction = convertToFraction(exponent);
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

    public static String convertToFraction(double n) {
        String fraction = "";        

        if (n < 0) {
            n = absoluteValue(n);
            fraction = "-";
        }

        if (n - Math.floor(n) == 0) {
            fraction += Integer.toString((int)n) + "/1";
        } else {
            int whole = (int)Math.floor(n);
            double decimal = n - whole;

            String decimalStr = Double.toString(decimal);
            decimalStr = decimalStr.substring(decimalStr.indexOf(".") + 1);
            if (decimalStr.length() > 6) {
                decimalStr = decimalStr.substring(0, 6);

                if (decimalStr.toCharArray()[1] == decimalStr.toCharArray()[2] &&
                    decimalStr.toCharArray()[1] == decimalStr.toCharArray()[3] &&
                    decimalStr.toCharArray()[1] == decimalStr.toCharArray()[4]) {
                    decimalStr = decimalStr.substring(0, 2);
                }
            }

            int numerator = Integer.parseInt(decimalStr);

            int exponent = decimalStr.length();
            int denominator = (int)toThePowerOf(10, exponent);

            int gcd = findGCD(numerator, denominator);
            numerator /= gcd;
            denominator /= gcd;
            numerator += (denominator * whole);

            fraction += Integer.toString(numerator) + "/" + Integer.toString(denominator);            
        }

        return fraction;
    }

    public static void main(String[] args) {

        
    }
}
