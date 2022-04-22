import java.util.Scanner;

public class Keypad {

    public static void main(String[] args) throws Exception {

        //tests
        try (Scanner read = new Scanner(System.in)) {
            do {
                System.out.print("\nOperação: ");
                int operation = read.nextInt();

                if (operation == 1) {
                    String str = read.next();

                    System.out.println("\nNotation.isItNumeric => " + Notation.isItNumeric(str));
                } else if (operation == 2) {
                    String number = read.next();

                    System.out.println("\nNotation.formatNumber => " + Notation.formatNumber(number));
                } else if (operation == 3) {
                    String number = read.next();

                    System.out.println("\nNotation.isItDecimal => " + Notation.isItDecimal(number));
                } else if (operation == 4) {
                    String number = read.next();

                    System.out.println("\nNotation.isItFractional => " + Notation.isItFractional(number));
                } else if (operation == 5) {
                    double decimal = read.nextDouble();

                    System.out.println("FractionNotation.typeOfFraction => " + FractionNotation.typeOfFraction(decimal).length);

                    for (int i = 0; i < FractionNotation.typeOfFraction(decimal).length; i++) {
                        System.out.print(FractionNotation.typeOfFraction(decimal)[i] + "\t");
                    }
                } else if (operation == 6) {
                    String fraction = read.next();

                    System.out.println("FractionNotation.stringToArray =>");

                    for (int i = 0; i < FractionNotation.stringToArray(fraction).length; i++) {
                        System.out.print(FractionNotation.stringToArray(fraction)[i] + "\t");
                    }
                } else if (operation == 7) {
                    String fraction = read.next();

                    System.out.println("FractionNotation.simplifyFraction => " + FractionNotation.simplifyFraction(fraction));
                } else if (operation == 8) {
                    String fraction = read.next();

                    System.out.println("FractionNotation.convertToDecimal => " + FractionNotation.convertToDecimal(fraction));
                } else if (operation == 9) {
                    double decimal = read.nextDouble();

                    System.out.println("FractionNotation.convertToFraction => " + FractionNotation.convertToFraction(decimal));
                } else if (operation == 10) {
                    String multiplier = read.next();
                    String multiplicand = read.next();

                    System.out.println("Fraction.multiplication => " + Fraction.multiplication(multiplier, multiplicand));
                } else if (operation == 11) {
                    String dividend = read.next();
                    String divisor = read.next();

                    System.out.println("Fraction.division => " + Fraction.division(dividend, divisor));
                } else if (operation == 12) {
                    double num = read.nextDouble();

                    System.out.println("Calculator.absoluteValue => " + Calculator.absoluteValue(num));
                } else if (operation == 13) {
                    double decimal = read.nextDouble();

                    System.out.println("Calculator.round => " + Calculator.round(decimal));
                } else if (operation == 14) {
                    double decimal = read.nextDouble();

                    System.out.println("Calculator.roundDown => " + Calculator.roundDown(decimal));
                } else if (operation == 15) {
                    double decimal = read.nextDouble();

                    System.out.println("Calculator.roundUp => " + Calculator.roundUp(decimal));
                } else if (operation == 16) {
                    double decimal = read.nextDouble();
                    int digits = read.nextInt();

                    System.out.print("Calculator.roundWithPrecision => " + Calculator.roundWithPrecision(decimal, digits));
                } else if (operation == 17) {
                    int x = read.nextInt();
                    int y = read.nextInt();

                    System.out.println("Calculator.findGCD => " + Calculator.findGCD(x, y));
                } else if (operation == 18) {
                    int x = read.nextInt();
                    int y = read.nextInt();

                    System.out.print("Calculator.findLCM => " + Calculator.findLCM(x, y));
                } else if (operation == 19) {
                    double base = read.nextDouble();
                    double exponent = read.nextDouble();

                    System.out.println("Calculator.toThePowerOf => " + Calculator.toThePowerOf(base, exponent));
                } else if (operation == 20) {
                    double a = read.nextDouble();
                    double n = read.nextDouble();

                    System.out.println("Calculator.nthRootOf => " + Calculator.nthRootOf(a, n));
                } else {
                    break;
                }
            } while (true);
        }
    }
}