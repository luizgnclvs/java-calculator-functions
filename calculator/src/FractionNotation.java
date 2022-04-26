public class FractionNotation {

    public static String [] typeOfFraction (double decimal) {

        String [] components;

        //identifies if the integer part of the decimal number is other than zero - i.e. the resulting fraction will be an improper one - then it sets the correct amount of components in the array
        if (Calculator.absoluteValue(decimal) >= 1) {
            //identifies if the decimal is actually a invisible denominator fraction, i.e. an integer number
            if (Calculator.absoluteValue(decimal) - Rounding.roundDown(Calculator.absoluteValue(decimal)) == 0) { 
                components = new String [4];

                components[1] = Integer.toString((int)Calculator.absoluteValue(decimal));
                components[2] = "1";
                components[3] = "+";

                components[0] = components[1] + "/1";

                if (decimal < 0) {
                    components[0] = "-(" + components[0] + ")";
                    components[3] = "-";
                }
            } else { //improper fraction
                components = new String [7];
            }
        } else { //proper fraction
            components = new String [4];
        }

        return components;
    }

    public static String [] stringToArray (String fraction) throws Exception {

        if (!Notation.isItFractional(fraction)) {
            if (Notation.isItDecimal(fraction)) {
                fraction = convertToFraction(Double.parseDouble(fraction));
            } else {
                if (Notation.isItNumeric(fraction)) {
                    fraction += "/1";
                } else {
                    throw new Exception("O valor inserido não é um número.");
                }
            }
        }

        fraction = Notation.formatNumber(fraction);

        boolean negative = false;

        if (fraction.matches("-.*")) {
            negative = true;

            StringBuilder str = new StringBuilder(fraction);

            //if the fraction is negative, then the '-', '(' and ')' characters are removed to facilitate manipulation of the String
            str.deleteCharAt(str.indexOf("-"));
            str.deleteCharAt(str.indexOf("("));
            str.deleteCharAt(str.indexOf(")"));

            fraction = str.toString();
        }

        double numerator = Double.parseDouble(fraction.substring(0, fraction.indexOf("/")));
        double denominador = Double.parseDouble(fraction.substring(fraction.indexOf("/") + 1));

        String [] fractionComponents = typeOfFraction(numerator / denominador);

        fractionComponents[0] = fraction;
        //vulgar fraction, which is proper if {numerator < denominator} and improper if otherwise
        fractionComponents[1] = fraction.substring(0, fraction.indexOf("/"));
        //numerator
        fractionComponents[2] = fraction.substring(fraction.indexOf("/") + 1);
        //denominator

        //sign of the fraction
        if (negative) {
            fractionComponents[0] = "-(" + fraction + ")";
            fractionComponents[3] = "-";
        } else {
            fractionComponents[3] = "+";
        }

        //if the fraction in question is, in fact, improper then it can be denoted as a mixed number {integer ± numerator/denominator}
        if (fractionComponents.length == 7) {
            fractionComponents[5] = Integer.toString(Integer.parseInt(fractionComponents[1]) / Integer.parseInt(fractionComponents[2]));
            //integer part of the mixed number
            fractionComponents[6] = Integer.toString(Integer.parseInt(fractionComponents[1]) % Integer.parseInt(fractionComponents[2]));
            //numerator of the mixed number
            
            fractionComponents[4] = fractionComponents[5] + " + " + fractionComponents[6] + "/" + fractionComponents[2];
            //mixed number

            if (negative) {
                fractionComponents[4] = "-(" + fractionComponents[4] + ")";
            }
        }

        return fractionComponents;
    }

    public static String simplifyFraction (String fraction) throws Exception {

        if (!Notation.isItFractional(fraction)) {
            throw new Exception("O valor inserido não é uma fração.");
        }

        fraction = Notation.formatNumber(fraction);

        int numerator = Integer.parseInt(stringToArray(fraction)[1]);
        int denominator = Integer.parseInt(stringToArray(fraction)[2]);

        int gcd = Calculator.findGCD(numerator, denominator); 

        numerator /= gcd;
        denominator /= gcd;

        if (stringToArray(fraction)[3].matches("-")) {
            return "-(" + numerator + "/" + denominator + ")";
        } else {
            return numerator + "/" + denominator;
        }
    }

    public static String [] equivalentFractions (String fraction1, String fraction2) throws Exception {

        if (!Notation.isItFractional(fraction1) && !Notation.isItFractional(fraction2)) {
            throw new Exception("Nenhum dos valores inserido é uma fração");
        }

        fraction1 = Notation.formatNumber(fraction1);
        fraction2 = Notation.formatNumber(fraction2);

        int denominator = Calculator.findLCM(Integer.parseInt(stringToArray(fraction1)[2]), Integer.parseInt(stringToArray(fraction2)[2]));
        int numerator1 = (denominator / Integer.parseInt(stringToArray(fraction1)[2])) * Integer.parseInt(stringToArray(fraction1)[1]);
        int numerator2 = (denominator / Integer.parseInt(stringToArray(fraction2)[2])) * Integer.parseInt(stringToArray(fraction2)[1]);

        if (stringToArray(fraction1)[3] == "-") {
            fraction1 = "-(" + numerator1 + "/" + denominator + ")";
        } else {
            fraction1 = numerator1 + "/" + denominator;
        }

        if (stringToArray(fraction2)[3] == "-") {
            fraction2 = "-(" + numerator2 + "/" + denominator + ")";
        } else {
            fraction2 = numerator2 + "/" + denominator;
        }

        String [] equivalentFractions = new String [5];

        equivalentFractions[0] = Integer.toString(denominator);

        if (stringToArray(fraction1)[3] == "-") {
            equivalentFractions[1] = "-";
        } else {
            equivalentFractions[1] = "+";
        }

        equivalentFractions[2] = Integer.toString(numerator1);

        if (stringToArray(fraction2)[3] == "-") {
            equivalentFractions[3] = "-";
        } else {
            equivalentFractions[3] = "+";
        }

        equivalentFractions[4] = Integer.toString(numerator2);

        return equivalentFractions;
    }

    public static double convertToDecimal (String fraction) throws Exception {

        if (!Notation.isItFractional(fraction)) {
            throw new Exception("O valor inserido não é uma fração");
        } else {
            fraction = Notation.formatNumber(fraction);
        }

        double numerator = Double.parseDouble(stringToArray(fraction)[1]);
        double denominator = Double.parseDouble(stringToArray(fraction)[2]);

        if (stringToArray(fraction)[3].matches("-")) {
            return -(numerator / denominator);
        } else {
            return numerator / denominator;
        }
    }

    public static String convertToFraction (double decimal) throws Exception {

        if (typeOfFraction(decimal)[2] == "1") {
            return typeOfFraction(decimal)[0];
        }

        boolean negative = false;

        if (decimal < 0) {
            decimal *= -1;
            negative = true;
        }

        int integer = 0;

        if (typeOfFraction(decimal).length == 7) {
            integer = (int)Rounding.roundDown(decimal);
            decimal -= integer;
        }

        String decimalStr = String.format("%f", decimal);
        //eliminates the '0.' part of the String to make its manipulation easier
        decimalStr = decimalStr.substring(decimalStr.indexOf(".") + 1);
        //eliminates the last character of the String to avoid rounding issues that may come from converting a Double variable to a String
        decimalStr = decimalStr.substring(0, decimalStr.length() - 1);

        //eliminates any 'extra' zeroes that may come from converting double to String
        if (decimalStr.matches("[^0]+0+")) {
            int index = 0, endIndex = 0;

            for (int i = 1; i < 10; i++) {
                if (decimalStr.matches(".*" + i + ".*")) {
                    index = decimalStr.lastIndexOf(Integer.toString(i));

                    if (index >= endIndex) {
                        endIndex = index + 1;
                    }
                }
            }

            decimalStr = decimalStr.substring(0, endIndex);
        }

        boolean repeatingDecimals = false;

        for (int i = 1; i < 10; i++) {
            //identifies if the decimal contains any repeating decimals whitin the parameters set (i.e. 3 to 6 repeating digits)
            if (decimalStr.matches("[0-9]*" + i + "{3,6}")) {
                repeatingDecimals = true;

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

                if (areThereNonRepeating) {
                    int nonRepeating = Integer.parseInt((decimalStr.substring(0, digitCount)));

                    decimalStr = repeatingFractions[i - 1];

                    //assigns the amount of zeroes in the denominator according to the number of non-repeating digits
                    for (int k = 0; k < digitCount; k++) {
                        decimalStr += "0";
                    }

                    //multiplies the non-repeating decimal with the original denominator of the equivalent fraction of the repeating decimal (i.e. 1, 3 or 9)
                    if (i == 9) {
                        nonRepeating *= 1;
                    } else if (i == 3 || i == 6) {
                        nonRepeating *= 3;
                    } else {
                        nonRepeating *= 9;
                    }

                    //adds the product to the numerator of the fraction to find out the new numerator
                    nonRepeating += Integer.parseInt(decimalStr.substring(0, 1));

                    //puts the new numerator and denominator together into a fraction
                    decimalStr = Integer.toString(nonRepeating) + decimalStr.substring(decimalStr.indexOf("/"));
                } else {
                    decimalStr = repeatingFractions[i - 1];
                }

                break;
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

        if (integer > 0) {
            //improper fraction
            numerator += integer * denominator;
        }

        if (negative) {
            return  "-(" + numerator + "/" + denominator + ")";
        } else {
            return numerator + "/" + denominator;
        }
    }
}