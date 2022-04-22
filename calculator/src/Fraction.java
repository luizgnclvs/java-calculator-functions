public class Fraction {

    public static String multiplication (String multiplier, String multiplicand) throws Exception {

        multiplier = Notation.formatNumber(multiplier);
        multiplicand = Notation.formatNumber(multiplicand);

        if (Notation.isItFractional(multiplier) == false && Notation.isItFractional(multiplicand) == false) {
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

        if (Notation.isItFractional(dividend) == false && Notation.isItFractional(divisor) == false) {
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