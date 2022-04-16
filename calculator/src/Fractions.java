public class Fractions {

    public static String division (String dividend, String divisor) {

        String numerator1, numerator2, denominator1, denominator2;

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
            numerator2 = "1";
            denominator2 = divisor;
        }

        int finalNumerator = Integer.parseInt(numerator1) * Integer.parseInt(denominator2);
        int finalDenominator = Integer.parseInt(denominator1) * Integer.parseInt(numerator2);

        int gcd = Calculator.findGCD(finalNumerator, finalDenominator);

        finalNumerator /= gcd;
        finalDenominator /= gcd;

        return Integer.toString(finalNumerator) + "/" + Integer.toString(finalDenominator);
    }
}
