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
                    System.out.println(Notation.isItFractional(str));
                } else if (operation == 2) {
                    double decimal = read.nextDouble();

                    String [] fraction = Notation.typeOfFraction(decimal);

                    System.out.println(fraction.length);

                    if (fraction.length == 1) {
                        System.out.println(fraction[0]);
                    }
                } else if (operation == 3) {
                    String fraction = read.next();
                    System.out.println(Notation.convertToDecimal(fraction));
                } else if (operation == 4) {
                    String multiplier = read.next();
                    String multiplicand = read.next();

                    String [] product = Fractions.multiplication(multiplier, multiplicand);

                    for (int i = 0; i < product.length; i++) {
                        System.out.print(product[i] + " | ");
                    }
                } else if (operation == 5) {
                    double decimal = read.nextDouble();

                    String [] fraction = Notation.convertToFraction(decimal);

                    for (int i = 0; i < fraction.length; i++) {
                        System.out.print(fraction[i] + " | ");
                    }
                } else if (operation == 6) {
                    String multiplier = read.next();
                    String multiplicand = read.next();

                    String [] product = Fractions.division(multiplier, multiplicand);

                    for (int i = 0; i < product.length; i++) {
                        System.out.print(product[i] + " | ");
                    }
                } else if (operation == 7) {
                    int x = read.nextInt();
                    int y = read.nextInt();

                    System.out.print(Calculator.findLCM(x, y));
                } else if (operation == 8) {
                    double decimal = read.nextDouble();
                    int digits = read.nextInt();

                    System.out.print(Calculator.roundWithPrecision(decimal, digits));
                } else if (operation == 9) {
                    String fraction = read.next();

                    String [] fractionComponents = Notation.fractionStringToArray(fraction);

                    for (int i = 0; i < fractionComponents.length; i++) {
                        System.out.print(fractionComponents[i] + " | ");
                    }
                } else if (operation == 10) {
                    String fraction = read.next();

                    System.out.print(Notation.simplifyFraction(fraction));
                } else {
                    break;
                }
            } while (true);
        }
    }
}