public class Notation {

    public static String [] numbersNotation () {

        String [] notation = new String [7];

        notation[0] = "-?0+[,\\.]?0*";
        //matches with any amount of zeroes
        notation[1] = "-?[0-9]+";
        //matches with any integer number, positive or not
        notation[2] = "-?[0-9]+[,\\.][0-9]+";
        //matches with any decimal number, positive or not
        notation[3] = "-?[0-9]+/-?[0-9]+|-?\\([0-9]+/[0-9]+\\)";
        //matches with any fraction with both integer numerator and denominator, whether they be positive or not
        notation[4] = "-?[0-9]+[,\\.][0-9]+/-?[0-9]+|-?\\([0-9]+[,\\.][0-9]+/[0-9]+\\)";
        //matches with any fraction with a decimal numerator and integer denominator, whether they be positive or not
        notation[5] = "-?[0-9]+/-?[0-9]+[,\\.][0-9]+|-?\\([0-9]+/[0-9]+[,\\.][0-9]+\\)";
        //matches with any fraction with a integer numerator and decimal denominator, whether they be positive or not
        notation[6] = "-?[0-9]+[,\\.][0-9]+/-?[0-9]+[,\\.][0-9]+|-?\\([0-9]+[,\\.][0-9]+/[0-9]+[,\\.][0-9]+\\)";
        //matches with any fraction with both decimal numerator and denominator, whether they be positive or not

        return notation;
    }

    public static boolean isItNumeric (String str) {

        if (str != null) {
            for (int i = 0; i < numbersNotation().length; i++) {
                if (str.matches(numbersNotation()[i])) {
                    return true;
                }
            }
        }

        return false;
    }

    public static String formatNumber (String number) throws Exception {

        if (!isItNumeric(number)) {
            throw new Exception("O valor inserido não é um número.");
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
                if (integer.matches(".*" + i + ".*")) {
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
            boolean negative = false;

            if (decimal.matches("-.*")) {
                negative = true;
            }

            decimal = decimal.substring(decimal.indexOf(".") - 1);

            if (negative) {
                decimal = "-" + decimal;
            }
        }

        //identifies if there are any trailing zeroes and, if so, erase them
        if (decimal.matches("-?[0-9]+\\.[0-9]*[^0][0]+")) {
            int index = 0, endIndex = 0;

            //identifies which digit comes right before the trailing zero(es)
            for (int i = 1; i < 10; i++) {
                if (decimal.matches(".*" + i + ".*")) {
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

    public static String formatFraction (String fraction) throws Exception {

        //deletes parentheses to make for easier manipulation
        if (fraction.matches(".*\\(.*\\)")) {
            StringBuilder str = new StringBuilder(fraction);

            str.deleteCharAt(str.indexOf("("));
            str.deleteCharAt(str.indexOf(")"));

            fraction = str.toString();
        }

        //separates the numerator and denominator of the fraction into different variables for easier manipulation
        String numerator = fraction.substring(0, fraction.indexOf("/"));
        String denominator = fraction.substring(fraction.indexOf("/") + 1);

        numerator = formatNumber(numerator);
        denominator = formatNumber(denominator);

        //identifies if the denominator is equal to zero and, if so, throws exception on the grounds that fractions with denominator = 0 are undefined
        if (denominator.matches("0")) {
            throw new Exception("Fração indefinida. {denominador = 0}");
        }

        //identifies if the numerator is equal to zero and, if so, returns "0" as all fraction with numerator = 0 are themselves equal to "0"
        if (numerator.matches("0")) {
            return "0";
        }

        boolean negative = false;

        //identifies if the denominator is a negative number
        if (denominator.matches("-.*")) {
            denominator = denominator.substring(1);

            negative = true;

            //if both the numerator and denominator are negative - i.e. the fraction is NOT negative, then the minus sign from the numerator is also removed
            if (numerator.matches("-.*")) {
                numerator = numerator.substring(1);

                negative = false;
            }
        } else { //identifies if only the numerator is a negative number
            if (numerator.matches("-.*")) {
                numerator = numerator.substring(1);

                negative = true;
            }
        }

        //identifies if either or both the numerator or denominator are decimals and, if so, converts the decimal(s) to fraction(s)
        if (numerator.matches(numbersNotation()[2]) || denominator.matches(numbersNotation()[2])) {
            if (numerator.matches(numbersNotation()[2])) {
                numerator = FractionNotation.convertToFraction(Double.parseDouble(numerator));
            }

            if (denominator.matches(numbersNotation()[2])) {
                denominator = FractionNotation.convertToFraction(Double.parseDouble(denominator));
            }

            //makes the division between the two new fractions
            String quotient = Fraction.division(numerator, denominator);

            if (quotient == "1" || quotient == "-1") {
                return quotient;
            } else { //assigns the new values of the numerator and denominator
                numerator = FractionNotation.stringToArray(quotient)[1];
                denominator = FractionNotation.stringToArray(quotient)[2];
            }
        }

        int gcd = Calculator.findGCD(Integer.parseInt(numerator), Integer.parseInt(denominator));

        numerator = Integer.toString((Integer.parseInt(numerator) / gcd));
        denominator = Integer.toString((Integer.parseInt(denominator) / gcd));

        if (negative) {
            return "-(" + numerator + "/" + denominator + ")";
        } else {
            return numerator + "/" + denominator;
        }
    }

    public static boolean isItDecimal (String number) throws Exception {

        if (isItNumeric(number)) {
            if (number.matches(numbersNotation()[2])) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new Exception("O valor inserido não é um número");
        }
    }

    public static boolean isItFractional (String number) throws Exception {

        if (isItNumeric(number)) {
            for (int i = 3; i < numbersNotation().length; i++) {
                if (number.matches(numbersNotation()[i])) {
                    return true;
                }
            }

            return false;
        } else {
            throw new Exception("O valor inserido não é um número.");
        }
    }

    // * StringToNumbers method required
}