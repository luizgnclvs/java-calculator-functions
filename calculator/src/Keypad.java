import java.util.Scanner;

public class Keypad {

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);

        String str = read.nextLine();

        String num = Notation.formatNumber(str);

        System.out.println(num);
    }
}