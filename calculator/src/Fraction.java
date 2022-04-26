public class Fraction {

    public static String additionOrSubtraction (String term1, String term2) throws Exception {

        term1 = Notation.formatNumber(term1);
        term2 = Notation.formatNumber(term2);

        if (!Notation.isItFractional(term1) && !Notation.isItFractional(term2)) {
            throw new Exception("Nenhum dos valores inserido é uma fração");
        }

        if (!Notation.isItFractional(term1)) {
            term1 = FractionNotation.convertToFraction(Double.parseDouble(term1));
        }

        if (!Notation.isItFractional(term2)) {
            term2 = FractionNotation.convertToFraction(Double.parseDouble(term2));
        }

        int denominator = Integer.parseInt(FractionNotation.equivalentFractions(term1, term2)[0]);
        int numerator1 = Integer.parseInt(FractionNotation.equivalentFractions(term1, term2)[2]);
        int numerator2 = Integer.parseInt(FractionNotation.equivalentFractions(term1, term2)[4]);

        boolean negative = false;

        int finalNumerator;

        if (FractionNotation.stringToArray(term1)[3] != FractionNotation.stringToArray(term2)[3]) {
            if (numerator1 > numerator2) {
                finalNumerator = numerator1 - numerator2;

                if (FractionNotation.stringToArray(term1)[3] == "-") {
                    negative = true;
                }
            } else {
                finalNumerator = numerator2 - numerator1;

                if (FractionNotation.stringToArray(term2)[3] == "-") {
                    negative = true;
                }
            }
        } else {
            finalNumerator = numerator1 + numerator2;

            if (FractionNotation.stringToArray(term1)[3] == "-") {
                negative = true;
            }
        }

        if (negative) {
            return "-(" + finalNumerator + "/" + denominator + ")";
        } else {
            return finalNumerator + "/" + denominator;
        }
    }

    public static String multiplication (String multiplier, String multiplicand) throws Exception {

        multiplier = Notation.formatNumber(multiplier);
        multiplicand = Notation.formatNumber(multiplicand);

        if (!Notation.isItFractional(multiplier) && !Notation.isItFractional(multiplicand)) {
            throw new Exception("Nenhum dos valores inserido é uma fração");
        }

        boolean negative = false;

        if (FractionNotation.stringToArray(multiplier)[3] != FractionNotation.stringToArray(multiplicand)[3])  {
            negative = true;
        }

        int numerator = Integer.parseInt(FractionNotation.stringToArray(multiplier)[1]) * Integer.parseInt(FractionNotation.stringToArray(multiplicand)[1]);
        int denominator = Integer.parseInt(FractionNotation.stringToArray(multiplier)[2]) * Integer.parseInt(FractionNotation.stringToArray(multiplicand)[2]);

        int gcd = Calculator.findGCD(numerator, denominator);

        numerator /= gcd;
        denominator /= gcd;

        if (numerator == denominator) {
            if (negative) {
                return "-1";
            } else {
                return "1";
            }
        } else {
            if (negative) {
                return "-(" + numerator + "/" + denominator + ")";
            } else {
                return numerator + "/" + denominator;
            }
        }
    }

    public static String division (String dividend, String divisor) throws Exception {

        dividend = Notation.formatNumber(dividend);
        divisor = Notation.formatNumber(divisor);

        if (!Notation.isItFractional(dividend) && !Notation.isItFractional(divisor)) {
            throw new Exception("Nenhum dos valores inserido é uma fração");
        }

        if (Notation.isItDecimal(divisor)) {
            divisor = FractionNotation.convertToFraction(Double.parseDouble(divisor));
        } else {
            if (!Notation.isItFractional(divisor)) {
                divisor += "/1";
            }
        }

        divisor = FractionNotation.stringToArray(divisor)[2] + "/" + FractionNotation.stringToArray(divisor)[1];

        return multiplication(dividend, divisor);
    }
}