public class Calculator {

    public static String[] numbersNotation () {
        String number = "-?[0-9]+";
        String decimalNum = number + "[,\\.]?[0-9]+";

        String [] numbers = new String [6];
        numbers[0] = number; //matches with any integer number, positive or not
        numbers[1] = decimalNum; //matches with any decimal number, positive or not
        numbers[2] = number + "/" + number; //matches with any fraction with both numerator and denominator as integers, whether they be positive or not
        numbers[3] = decimalNum + "/" + number; //matches with any fraction with a decimal numerator and integer denominator, whether they be positive or not
        numbers[4] = number + "/" + decimalNum; //matches with any fraction with a integer numerator and decimal denominator, whether they be positive or not
        numbers[5] = decimalNum + "/" + decimalNum; //matches with any fraction with both numerator and denominator as decimals, whether they be positive or not

        return numbers;
    }
    public static boolean isNumeric (String str) {
        boolean numeric = false;

        String[] numbers = numbersNotation();
        
        if (str != null) {
            for (int i = 0; i < numbers.length; i++) {
                if (str.matches(numbers[i])) {
                    numeric = true;
                    break;
                }
            }
        }
        return numeric;
    }

    public static String formatNumber (String str) {
        String[] numbers = numbersNotation();

        if (str.matches(numbers[0]) || str.matches(numbers[1])) {
            boolean negative = false;

            if (str.matches("-.*")) {
                negative = true;
                str = str.substring(str.indexOf("-") + 1);
            }

            if (str.matches("[0]+.+")) {
                int index = 0, indexStart = str.length();
                for (int i = 1; i < 10; i++) {
                    String match = Integer.toString(i);
                    if (str.matches(".*" + match + ".*")) {
                        index = str.indexOf(match);
                        if (index <= indexStart) {
                            indexStart = index;
                        }
                    }
                }
                str = str.substring(indexStart);
            }

            if (str.matches(numbers[1])) {
                if (str.matches(".*,.*")) {
                    StringBuilder strb = new StringBuilder(str);
                    strb.replace(str.indexOf(","), str.indexOf(",") + 1, ".");
                    str = strb.toString();
                }

                if (str.substring(str.indexOf(".") + 1).matches("[0]+")) {
                    str = str.substring(0, str.indexOf("."));
                }

                if (str.matches("[0-9]+\\.[0]*[^0]+[0]+")) {
                    int index = str.lastIndexOf(".[1-9]");
                    str = str.substring(0, index + 1);

                }
            }

            if (negative) {
                str = "-" + str;
            }
        }

        for (int i = 2; i < numbers.length; i++) {
            if (str.matches(numbers[i])) {
                String numerator = str.substring(0, str.indexOf("/"));
                String denominator = str.substring(str.indexOf("/") + 1);

                numerator = formatNumber(numerator);
                denominator = formatNumber(denominator);

                if (denominator.matches("-.*")) {
                    denominator = denominator.substring(denominator.indexOf("-") + 1);
                    if (numerator.matches("-.*")) {
                        numerator = numerator.substring(numerator.indexOf("-") + 1);
                    } else {
                        numerator = "-" + numerator;
                    }
                }

                str = numerator + "/" + denominator;
            }
        }

        return str;
    }
        /*if (countOccurences(str, '-', 0) % 2 != 0) {            
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
        }*/

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

        System.out.println(formatNumber("33.2000/-54"));
    }
}
