import java.util.Scanner;

public class Keypad {

    public static void main(String[] args) {

        try (Scanner read = new Scanner(System.in)) {
            Double num = read.nextDouble();

            String [] fraction = Notation.convertToFraction(num);

            for (int i = 0; i < fraction.length; i++) {
                System.out.print(fraction[i] + " ");
            }
        }
    }
}