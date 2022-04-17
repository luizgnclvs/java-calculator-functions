import java.util.Scanner;

public class Keypad {

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);

        Double num = read.nextDouble();

        System.out.println(Notation.convertToFraction(num));
    }
}