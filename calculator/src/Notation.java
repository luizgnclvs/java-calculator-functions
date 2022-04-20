public class Notation {

    public static String [] numbersNotation () {

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

    public static boolean isItNumeric (String str) {

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

    public static String formatFraction (String fraction) throws Exception {

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
            String quotient = Fractions.division(numerator, denominator)[0];

            if (quotient == "1" || quotient == "-1") {
                return quotient;
            } else { //assigns the new values of the numerator and denominator
                numerator = quotient.substring(0, quotient.indexOf("/"));
                denominator = quotient.substring(quotient.indexOf("/") + 1);
            }
        }

        return numerator + "/" + denominator;
    }

    public static boolean isItDecimal (String str) {

        if (str.matches("-?[0-9]+[,\\.][0-9]+")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isItFractional (String str) {

        String [] notation = numbersNotation();

        for (int i = 3; i < notation.length; i++) {
            if (str.matches(notation[i])) {
                return true;
            }
        }

        return false;
    }

    public static String [] typeOfFraction (double decimal) {

        String [] fractionComponents;

        //identifies if the integer part of the decimal number is other than zero - i.e. the resulting fraction will be an improper one - then it sets the correct amount of components in the array
        if (decimal >= 1) {
            //identifies if the decimal is actually a invisible denominator fraction, i.e. an integer number
            if (decimal - Calculator.roundDown(decimal) == 0) { 
                fractionComponents = new String [1];

                fractionComponents[0] = Integer.toString((int)decimal);
            } else { //improper fraction
                fractionComponents = new String [7];
            }
        } else { //proper fraction
            fractionComponents = new String [3];
        }

        return fractionComponents;
    }

    public static double convertToDecimal (String fraction) throws Exception {

        if (!isItFractional(fraction)) {
            throw new Exception("O valor inserido não é uma fração");
        } else {
            fraction = formatNumber(fraction);
        }

        Double numerator = Double.parseDouble(fraction.substring(0, fraction.indexOf("/")));
        Double denominator = Double.parseDouble(fraction.substring(fraction.indexOf("/") + 1));

        return numerator / denominator;
    }

    public static String [] convertToFraction (double decimal) {

        String [] fractionComponents = typeOfFraction(decimal);

        if (fractionComponents.length == 1) {
            return fractionComponents;
        }

        boolean negative = false;

        if (decimal < 0) {
            decimal = Calculator.absoluteValue(decimal);
            negative = true;
        }

        int integer = 0;

        if (fractionComponents.length == 7) {
            integer = (int)Calculator.roundDown(decimal);
            decimal -= integer;
        }

        String decimalStr = String.format("%f", decimal);
        //eliminates the '0.' part of the String to make its manipulation easier
        decimalStr = decimalStr.substring(decimalStr.indexOf(".") + 1);
        //eliminates the last character of the String to avoid rounding issues that may come from converting a Double variable to a String
        decimalStr = decimalStr.substring(0, decimalStr.length());

        boolean repeatingDecimals = false;

        for (int i = 1; i < 10; i++) {
            //identifies if the decimal contains any repeating decimals whitin the parameters set (i.e. 3 to 6 repeating digits)
            if (decimalStr.matches("[0-9]*" + i + "{3,6}")) {
                boolean areThereNonRepeating = false;

                int digitCount = 0, index = 0;

                //checks if there are any non-repeating decimals
                for (int j = 0; j < 10; j++) {
                    if (j == i) {
                        continue;
                    }

                    //if so, the amount of non-repeating digits is found
                    if (decimalStr.matches(".*" + j + ".*")) {
                        areThereNonRepeating = true;

                        index = decimalStr.lastIndexOf(Integer.toString(j));

                        if (index >= digitCount) {
                            digitCount = index + 1;
                        }
                    }
                }

                //array with fraction equivalents to repeating decimals from 1 to 9
                String [] repeatingFractions = new String [] {"1/9", "2/9", "1/3", "4/9", "5/9", "2/3", "7/9", "8/9", "1/1"};

                decimalStr = repeatingFractions[i - 1];

                if (areThereNonRepeating) {
                    int nonRepeating = Integer.parseInt((decimalStr.substring(0, digitCount)));

                    //assigns the amount of zeroes in the denominator according to the number of non-repeating digits
                    for (int k = 0; k < digitCount; k++) {
                        decimalStr += "0";
                    }

                    //multiplies the non-repeating decimal with the original denominator of the equivalent fraction of the repeating decimal (i.e. 1, 3 or 9)
                    nonRepeating *= Integer.parseInt(decimalStr.substring(decimalStr.indexOf("/") + 1, (decimalStr.indexOf("/") + 2)));

                    //adds the product to the numerator of the fraction to find out the new numerator
                    nonRepeating += Integer.parseInt(decimalStr.substring(0, 1));

                    //puts the new numerator and denominator together into a fraction
                    decimalStr = Integer.toString(nonRepeating) + decimalStr.substring(decimalStr.indexOf("/"));
                }
            }
        }

        int numerator, denominator;

        //separates the numerator and denominator accordingly
        if (repeatingDecimals) {
            numerator = Integer.parseInt(decimalStr.substring(0, decimalStr.indexOf("/")));
            denominator = Integer.parseInt(decimalStr.substring(decimalStr.indexOf("/") + 1));
        } else {
            numerator = Integer.parseInt(decimalStr);
            //otherwise assigns the number after the decimal point as the numerator
            denominator = (int)Calculator.toThePowerOf(10, decimalStr.length());
            //the denominator is found with the result of the base 10 raised to the power of the amount of digits after the decimal point
        }

        int gcd = Calculator.findGCD(numerator, denominator);

        numerator /= gcd;
        denominator /= gcd;

        if (fractionComponents.length == 7) {
            fractionComponents[0] = Integer.toString(numerator + integer * denominator) + "/" + Integer.toString(denominator);
            //improper vulgar fraction
            fractionComponents[1] = Integer.toString(numerator + integer * denominator);
            //numerator
            fractionComponents[2] = Integer.toString(denominator);
            //denominator
            fractionComponents[3] = Integer.toString(integer) + " + " + Integer.toString(numerator) + "/" + Integer.toString(denominator);
            //mixed number
            fractionComponents[4] = Integer.toString(integer);
            //integer part of the mixed number
            fractionComponents[5] = Integer.toString(numerator);
            //numerator of the mixed number
            fractionComponents[6] = Integer.toString(denominator);
            //denominator of the mixed number

            if (negative) {
                fractionComponents[0] = "-" + fractionComponents[0];
                fractionComponents[1] = "-" + fractionComponents[1];
                fractionComponents[3] = "-" + Integer.toString(integer) + " - " + Integer.toString(numerator) + "/" + Integer.toString(denominator);
                fractionComponents[4] = "-" + fractionComponents[4];
                fractionComponents[5] = "-" + fractionComponents[5];
            }
        } else {
            fractionComponents[0] = Integer.toString(numerator) + "/" + Integer.toString(denominator);
            //proper vulgar fraction
            fractionComponents[1] = Integer.toString(numerator);
            //numerator
            fractionComponents[2] = Integer.toString(denominator);
            //denominator

            if (negative) {
                fractionComponents[0] = "-" + fractionComponents[0];
                fractionComponents[1] = "-" + fractionComponents[1];
            }
        }

        return fractionComponents;
    }

    // * StringToNumbers method required
}