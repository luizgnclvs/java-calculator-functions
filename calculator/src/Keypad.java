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
                } else {
                    break;
                }
            } while (true);
        }
    }
}