import java.util.Scanner;

public class Keypad {

    public static void main(String[] args) {

        try (Scanner read = new Scanner(System.in)) {
            String num = read.nextLine();

            System.out.println(Notation.formatNumber(num));
        }
    }
}