public class Notation {

    public static String[] numbersNotation () {

        String number = "-?[0-9]+";
        String decimal = number + "[,\\.]?[0-9]+";

        String [] numbers = new String [6];

        numbers[0] = number; //matches with any integer number, positive or not
        numbers[1] = decimal; //matches with any decimal number, positive or not
        numbers[2] = number + "/" + number; //matches with any fraction with both numerator and denominator as integers, whether they be positive or not
        numbers[3] = decimal + "/" + number; //matches with any fraction with a decimal numerator and integer denominator, whether they be positive or not
        numbers[4] = number + "/" + decimal; //matches with any fraction with a integer numerator and decimal denominator, whether they be positive or not
        numbers[5] = decimal + "/" + decimal; //matches with any fraction with both numerator and denominator as decimals, whether they be positive or not

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

    public static String formatNumber (String str) throws NumberFormatException {

        if (!isNumeric(str)) {
            throw new NumberFormatException("O valor inserido não é um número.");
        }

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

                if (str.substring(str.indexOf(".") + 1).matches("[0]+")) { //cu
                    str = str.substring(0, str.indexOf("."));
                }

                if (str.matches("[0-9]+\\.[0]*[^0]+[0]+")) { //cu
                    int index = 0, indexEnd = 0;
                    for (int i = 1; i < 10; i++) {
                        String match = Integer.toString(i);
                        if (str.matches(".*" + match + ".*")) {
                            index = str.lastIndexOf(match);
                            if (index >= indexEnd) {
                                indexEnd = index;
                            }
                        }
                    }                    
                    str = str.substring(0, indexEnd + 1);
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

    public static String convertToFraction(double n) {

        String fraction = "";

        if (n < 0) {
            n = Calculator.absoluteValue(n);
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
            int denominator = (int)Calculator.toThePowerOf(10, exponent);

            int gcd = Calculator.findGCD(numerator, denominator);
            numerator /= gcd;
            denominator /= gcd;
            numerator += (denominator * whole);

            fraction += Integer.toString(numerator) + "/" + Integer.toString(denominator);            
        }

        return fraction;
    }
}
