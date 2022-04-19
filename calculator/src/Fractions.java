public class Fractions {

    // ! tests due
    public static String [] multiplication (String multiplier, String multiplicand) {

        if (!(Notation.isItFractional(multiplier) && Notation.isItFractional(multiplicand))) {
            throw new NumberFormatException("O valor inserido não é uma fração");
        }

        multiplier = Notation.formatNumber(multiplier);
        multiplicand = Notation.formatNumber(multiplicand);

        boolean negative = false;

        if (multiplier.matches("-.*") ^ multiplicand.matches("-.*")) {
            negative = true;
        }

        if (multiplier.matches("-.*")) {
            multiplier = multiplier.substring(1);
        }

        if (multiplicand.matches("-.*")) {
            multiplicand = multiplicand.substring(1);
        }

        String [] factor1 = Notation.typeOfFraction(Notation.convertToDecimal(multiplier));
        String [] factor2 = Notation.typeOfFraction(Notation.convertToDecimal(multiplicand));

        int numerator1, numerator2, denominator1, denominator2;

        if (factor1.length == 1) {
            numerator1 = Integer.parseInt(factor1[0]);
            denominator1 = 1;
        } else {
            numerator1 = Integer.parseInt(multiplier.substring(0, multiplier.indexOf("/")));
            denominator1 = Integer.parseInt(multiplier.substring(multiplier.indexOf("/") + 1));
        }

        if (factor2.length == 1) {
            numerator2 = Integer.parseInt(factor2[0]);
            denominator2 = 1;
        } else {
            numerator2 = Integer.parseInt(multiplicand.substring(0, multiplicand.indexOf("/")));
            denominator2 = Integer.parseInt(multiplicand.substring(multiplicand.indexOf("/") + 1));
        }

        int finalNumerator = numerator1 * numerator2;
        int finalDenominator = denominator1 * denominator2;

        int gcd = Calculator.findGCD(finalNumerator, finalDenominator);

        finalNumerator /= gcd;
        finalDenominator /= gcd;

        String [] fractionComponents = Notation.typeOfFraction((double)finalNumerator / (double)finalDenominator);

        if (fractionComponents.length == 1) {
            if (negative) {
                fractionComponents[0] = "-" + fractionComponents[0];
            }

            return fractionComponents;
        } else if (fractionComponents.length == 3) {
            fractionComponents[0] = Integer.toString(finalNumerator) + "/" + Integer.toString(finalDenominator);
            //proper vulgar fraction
            fractionComponents[1] = Integer.toString(finalNumerator);
            //numerator
            fractionComponents[2] = Integer.toString(finalDenominator);
            //denominator

            if (negative) {
                fractionComponents[0] = "-" + fractionComponents[0];
                fractionComponents[1] = "-" + fractionComponents[1];
            }
        } else {
            int integer = finalNumerator / finalDenominator;

            finalNumerator = finalNumerator % finalDenominator;

            fractionComponents[0] = Integer.toString(finalNumerator + integer * finalDenominator) + "/" + Integer.toString(finalDenominator);
            //improper vulgar fraction
            fractionComponents[1] = Integer.toString(finalNumerator + integer * finalDenominator);
            //numerator
            fractionComponents[2] = Integer.toString(finalDenominator);
            //denominator
            fractionComponents[3] = Integer.toString(integer) + " + " + Integer.toString(finalNumerator) + "/" + Integer.toString(finalDenominator);
            //mixed number
            fractionComponents[4] = Integer.toString(integer);
            //integer part of the mixed number
            fractionComponents[5] = Integer.toString(finalNumerator);
            //numerator of the mixed number
            fractionComponents[6] = Integer.toString(finalDenominator);
            //denominator of the mixed number

            if (negative) {
                fractionComponents[0] = "-" + fractionComponents[0];
                fractionComponents[1] = "-" + fractionComponents[1];
                fractionComponents[3] = "-" + Integer.toString(integer) + " - " + Integer.toString(finalNumerator) + "/" + Integer.toString(finalDenominator);
                fractionComponents[4] = "-" + fractionComponents[4];
                fractionComponents[5] = "-" + fractionComponents[5];
            }
        }

        return fractionComponents;
    }

    // * modifications due
    public static String division (String dividend, String divisor) {

        String numerator1, numerator2, denominator1, denominator2;

        boolean negative = false;

        if (dividend.matches("-.*") ^ divisor.matches("-.*")) {
            negative = true;
        }

        if (dividend.matches("-.*")) {
            dividend = dividend.substring(dividend.indexOf("-") + 1);
        }

        if (divisor.matches("-.*")) {
            divisor = divisor.substring(divisor.indexOf("-") + 1);
        }

        if (dividend.matches(".*/.*")) {
            numerator1 = dividend.substring(0, dividend.indexOf("/"));
            denominator1 = dividend.substring(dividend.indexOf("/") + 1);
        } else {
            numerator1 = dividend;
            denominator1 = "1";
        }

        if (divisor.matches(".*/.*")) {
            numerator2 = divisor.substring(0, divisor.indexOf("/"));
            denominator2 = divisor.substring(divisor.indexOf("/") + 1);
        } else {
            numerator2 = divisor;
            denominator2 = "1";
        }

        int finalNumerator = Integer.parseInt(numerator1) * Integer.parseInt(denominator2);
        int finalDenominator = Integer.parseInt(denominator1) * Integer.parseInt(numerator2);

        int gcd = Calculator.findGCD(finalNumerator, finalDenominator);

        finalNumerator /= gcd;
        finalDenominator /= gcd;

        if (finalNumerator == finalDenominator) {
            if (negative) {
                return "-1";
            } else {
                return "1";
            }
        } else {
            if (negative) {
                return "-" + Integer.toString(finalNumerator) + "/" + Integer.toString(finalDenominator);
            } else {
                return Integer.toString(finalNumerator) + "/" + Integer.toString(finalDenominator);
            }
        }
    }
}