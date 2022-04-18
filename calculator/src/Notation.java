public class Notation {

    public static String[] numbersNotation () {

        String integer = "-?[0-9]+";
        String decimal = integer + "[,\\.][0-9]+";

        String [] notation = new String [7];

        notation[0] = "-?0+[,\\.]?0*";
        //matches with any amount of zeroes
        notation[1] = integer;
        //matches with any integer number, positive or not
        notation[2] = decimal;
        //matches with any decimal number, positive or not
        notation[3] = integer + "/" + integer;
        //matches with any fraction with both numerator and denominator as integers, whether they be positive or not
        notation[4] = decimal + "/" + integer;
        //matches with any fraction with a decimal numerator and integer denominator, whether they be positive or not
        notation[5] = integer + "/" + decimal;
        //matches with any fraction with a integer numerator and decimal denominator, whether they be positive or not
        notation[6] = decimal + "/" + decimal;
        //matches with any fraction with both numerator and denominator as decimals, whether they be positive or not

        return notation;
    }

    public static boolean isNumeric (String str) {

        String [] notation = numbersNotation();

        if (str != null) {
            for (int i = 0; i < notation.length; i++) {
                if (str.matches(notation[i])) {
                    return true;
                }
            }
        }

        return false;
    }

    public static String formatNumber (String number) {

        if (!isNumeric(number)) {
            return "O valor inserido não é um número.";
        }

        String [] notation = numbersNotation();

        if (number.matches(notation[0])) {
            return "0";
        } else if (number.matches(notation[1])) {
            return formatInteger(number);
        } else if (number.matches(notation[2])) {
            return formatDecimal(number);
        } else {
            return formatFraction(number);
        }
    }

    public static String formatInteger (String integer) {

        boolean negative = false;

        //removes the minus sign to make String manipulation easier
        if (integer.matches("-.*")) {
            negative = true;
            integer = integer.substring(1);
        }

        //identifies if there are any leading zeroes and, if so, erase them
        if (integer.matches("[0]+[1-9]+.*")) {
            int index = 0, beginIndex = integer.length();

            //identifies which digit first comes right after the leading zero(es)
            for (int i = 1; i <= 9; i++) {
                if (integer.matches(".*" + Integer.toString(i) + ".*")) {
                    index = integer.indexOf(Integer.toString(i));

                    if (index <= beginIndex) {
                        beginIndex = index;
                    }
                }
            }

            integer = integer.substring(beginIndex);
        }

        //adds the minus sign to the number again
        if (negative) {
            integer = "-" + integer;
        }

        return integer;
    }

    public static String formatDecimal (String decimal) {

        decimal = formatInteger(decimal);

        //identifies if the decimal point is a comma and, if so, replaces it with a dot
        if (decimal.matches(".*,.*")) {
            StringBuilder str = new StringBuilder(decimal);

            str.replace(decimal.indexOf(","), decimal.indexOf(",") + 1, ".");
            decimal = str.toString();
        }

        //identifies if the numbers after the decimal point are all zeroes
        if (decimal.substring(decimal.indexOf(".") + 1).matches("[0]+")) {
            decimal = decimal.substring(0, decimal.indexOf("."));
        }

        //identifies if, in the case of decimals between -1 and 1, there are other leading zeroes other than just the one and, if so, erases them
        if (decimal.matches("-?0+\\..*")) {
            decimal = decimal.substring(decimal.indexOf(".") - 1);
        }

        //identifies if there are any trailing zeroes and, if so, erase them
        if (decimal.matches("-?[0-9]+\\.[0]*[^0]+[0]+")) {
            int index = 0, endIndex = 0;

            //identifies which digit comes right before the trailing zero(es)
            for (int i = 1; i < 10; i++) {
                if (decimal.matches(".*" + Integer.toString(i) + ".*")) {
                    index = decimal.lastIndexOf(Integer.toString(i));

                    if (index >= endIndex) {
                        endIndex = index;
                    }
                }
            }

            decimal = decimal.substring(0, endIndex + 1);
        }

        return decimal;
    }

    public static String formatFraction (String fraction) {

        String [] notation = numbersNotation();

        //separates the numerator and denominator of the fraction into different variables for easier manipulation
        String numerator = fraction.substring(0, fraction.indexOf("/"));
        String denominator = fraction.substring(fraction.indexOf("/") + 1);

        numerator = formatNumber(numerator);
        denominator = formatNumber(denominator);

        //identifies if the denominator is a negative number
        if (denominator.matches("-.*")) {
            denominator = denominator.substring(denominator.indexOf("-") + 1);

            //if both the numerator and denominator are negative - i.e. the fraction is NOT negative, then the minus sign from the numerator is also removed
            if (numerator.matches("-.*")) {
                numerator = numerator.substring(numerator.indexOf("-") + 1);
            } else { //otherwise, the minus sign is transferred to the numerator
                numerator = "-" + numerator;
            }
        }

        //identifies if either or both the numerator or denominator are decimals and, if so, converts the decimal(s) to fraction(s)
        if (numerator.matches(notation[2]) || denominator.matches(notation[2])) {
            if (numerator.matches(notation[2])) {
                numerator = convertToFraction(Double.parseDouble(numerator))[1];
            }

            if (denominator.matches(notation[2])) {
                denominator = convertToFraction(Double.parseDouble(denominator))[2];
            }

            //makes the division between the two new fractions
            String quotient = Fractions.division(numerator, denominator);

            if (quotient == "1" || quotient == "-1") {
                return quotient;
            } else { //assigns the new values of the numerator and denominator
                numerator = quotient.substring(0, quotient.indexOf("/"));
                denominator = quotient.substring(quotient.indexOf("/") + 1);
            }
        }

        return numerator + "/" + denominator;
    }

    public static String [] convertToFraction (double num) {

        String [] components = new String [7];

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
                components[0] = fraction + Integer.toString(numerator + integer * denominator) + "/" + Integer.toString(denominator);
                //vulgar fraction (improper)
                components[1] = fraction + Integer.toString(numerator + integer * denominator);
                //numerator
                components[2] = Integer.toString(denominator);
                //denominator
                components[3] = fraction + Integer.toString(integer) + " × " + Integer.toString(numerator) + "/" + Integer.toString(denominator);
                //mixed number
                components[4] = Integer.toString(integer);
                //integer part of the mixeed number
                components[5] = Integer.toString(numerator);
                //numerator of the mixed number
                components[6] = Integer.toString(denominator);
                //denominator of the mixed number
            } else {
                components[0] = fraction + Integer.toString(numerator) + "/" + Integer.toString(denominator);
                //vulgar fraction (proper)
                components[1] = fraction + Integer.toString(numerator);
                //numerator
                components[2] = Integer.toString(denominator);
                //denominator
                components[3] = "";
                components[4] = "";
                components[5] = "";
                components[6] = "";
            }
        }

        return components;
    }
}