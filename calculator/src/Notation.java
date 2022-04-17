import java.io.Console;

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

    public static String formatNumber (String num) throws NumberFormatException {

        if (!isNumeric(num)) {
            throw new NumberFormatException("O valor inserido não é um número.");
        }

        String[] numbers = numbersNotation();

        if (num.matches(numbers[0]) || num.matches(numbers[1])) {
            boolean negative = false;

            if (num.matches("-.*")) {
                negative = true;
                num = num.substring(num.indexOf("-") + 1);
            }

            if (num.matches("[0]+[1-9]+[,\\.]?.*")) {
                int index = 0, indexStart = num.length();

                for (int i = 1; i < 10; i++) {
                    if (num.matches(".*" + Integer.toString(i) + ".*")) {
                        index = num.indexOf(Integer.toString(i));

                        if (index <= indexStart) {
                            indexStart = index;
                        }
                    }
                }

                num = num.substring(indexStart);
            }

            if (num.matches(numbers[1])) {
                if (num.matches(".*,.*")) {
                    StringBuilder strb = new StringBuilder(num);

                    strb.replace(num.indexOf(","), num.indexOf(",") + 1, ".");
                    num = strb.toString();
                }

                if (num.substring(num.indexOf(".") + 1).matches("[0]+")) {
                    num = num.substring(0, num.indexOf("."));
                }

                if (num.matches("[0-9]+\\.[0]*[^0]+[0]+")) {
                    int index = 0, indexEnd = 0;

                    for (int i = 1; i < 10; i++) {
                        if (num.matches(".*" + Integer.toString(i) + ".*")) {
                            index = num.lastIndexOf(Integer.toString(i));

                            if (index >= indexEnd) {
                                indexEnd = index;
                            }
                        }
                    }

                    num = num.substring(0, indexEnd + 1);
                }
            }

            if (negative) {
                num = "-" + num;
            }
        }

        for (int i = 2; i < numbers.length; i++) {
            if (num.matches(numbers[i])) {
                String numerator = num.substring(0, num.indexOf("/"));
                String denominator = num.substring(num.indexOf("/") + 1);

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

                if (numerator.matches(numbers[1]) || denominator.matches(numbers[1])) {
                    if (numerator.matches(numbers[1])) {
                        numerator = convertToFraction(Double.parseDouble(numerator));
                    }

                    if (denominator.matches(numbers[1])) {
                        denominator = convertToFraction(Double.parseDouble(denominator));
                    }

                    String fraction = Fractions.division(numerator, denominator);

                    if (fraction == "1" || fraction == "-1") {
                        num = fraction;
                        break;
                    } else {
                        numerator = fraction.substring(0, fraction.indexOf("/"));
                        denominator = fraction.substring(fraction.indexOf("/") + 1);
                    }
                }

                num = numerator + "/" + denominator;

                break;
            }
        }

        return num;
    }

    public static String convertToFraction (double num) {

        String fraction = "";

        if (num < 0) {
            num = Calculator.absoluteValue(num);
            fraction = "-";
        }

        if (num - Calculator.roundDown(num) == 0) {
            fraction += Integer.toString((int)num) + "/1 = " + Integer.toString((int)num);
        } else {
            int integer = (int)Calculator.roundDown(num);
            double decimal = num - integer;

            String decimalStr = String.format("%f", decimal);
            decimalStr = decimalStr.substring(decimalStr.indexOf(".") + 1);
            decimalStr = decimalStr.substring(0, 5);

            for (int i = 1; i < 10; i++) {
                if (decimalStr.matches("[0-9]*" + i + "{3,6}")) {
                    int houseCount = 0, index = 0;

                    for (int j = 0; j < 10; j++) {
                        if (j == i) {
                            continue;
                        }

                        if (decimalStr.matches(".*" + Integer.toString(j) + ".*")) {
                            index = decimalStr.lastIndexOf(Integer.toString(j));

                            if (index >= houseCount) {
                                houseCount = index + 1;
                            }
                        }   
                    }

                    int nonRepeating = Integer.parseInt((decimalStr.substring(0, houseCount)));

                    String [] repeatingDecimals = new String [] {"1/9", "2/9", "1/3", "4/9", "5/9", "2/3", "7/9", "8/9", "1"};

                    decimalStr = repeatingDecimals[i - 1];

                    for (int k = 0; k < houseCount; k++) {
                        decimalStr += "0";
                    }

                    nonRepeating *= Integer.parseInt(decimalStr.substring(decimalStr.indexOf("/") + 1, (decimalStr.indexOf("/") + 2)));

                    nonRepeating += Integer.parseInt(decimalStr.substring(0, 1));

                    decimalStr = Integer.toString(nonRepeating) + decimalStr.substring(decimalStr.indexOf("/"));
                }
            }

            int numerator, denominator;

            if (decimalStr.matches(".*/.*")) {
                numerator = Integer.parseInt(decimalStr.substring(0, decimalStr.indexOf("/")));
                denominator = Integer.parseInt(decimalStr.substring(decimalStr.indexOf("/") + 1));
            } else {
                numerator = Integer.parseInt(decimalStr);
                denominator = (int)Calculator.toThePowerOf(10, decimalStr.length());
            }

            int gcd = Calculator.findGCD(numerator, denominator);

            numerator /= gcd;
            denominator /= gcd;

            if (integer > 0) {
                fraction += Integer.toString(numerator + integer * denominator) + "/" + Integer.toString(denominator) + " = " + Integer.toString(integer) + " × " + Integer.toString(numerator) + "/" + Integer.toString(denominator);
            } else {
                fraction += Integer.toString(numerator) + "/" + Integer.toString(denominator);
            }
        }

        return fraction;
    }
}