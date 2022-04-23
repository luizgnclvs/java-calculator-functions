import java.util.Scanner;

public class Keypad {

    public static void menu () throws Exception {

        String [] operations = new String [] {"Sair", "\'Notation.isItNumeric\'", "\'Notation.formatNumber\'", "\'Notation.isItDecimal\'", "\'Notation.isItFractional\'", "\'FractionNotation.typeOfFraction\'", "\'FractionNotation.stringToArray\'", "\'FractionNotation.simplifyFraction\'", "\'FractionNotation.convertToDecimal\'", "\'FractionNotation.convertToFraction\'", "\'Fraction.multiplication\'", "\'Fraction.division\'", "\'Calculator.absoluteValue\'", "\'Rounding.directedRounding\'", "\'Rounding.roundToNearestInt\'", "\'Rounding.roundRandomTieBreaker\'", "\'Rounding.roundWithPrecision\'", "\'Calculator.findGCD\'", "\'Calculator.findLCM\'", "\'Calculator.toThePowerOf\'", "\'Calculator.nthRootOf\'"};

        System.out.printf("\n%s \n%s" , "Olá, gostaria fazer alguma operação matemática?", "Insira o número correspondente à operação desejada.\n");

        for (int i = 0; i < operations.length; i++) {
            System.out.printf("\n%d\t%s\t%s", i, "===>", operations[i]);
        }

        try (Scanner read = new Scanner(System.in)) {
            System.out.print("\n\nOperação: ");
            int entry = read.nextInt();

            execute(entry, operations[entry]);
        }
    }

    public static void execute (int entry, String operation) throws Exception {

        try (Scanner read = new Scanner(System.in)) {
            String arrow = " => ";

            if (entry == 0) {
                System.out.println("\nOk. Obrigado pela atenção.\n");
            } else {
                if (entry == 1) {
                    String str;

                    System.out.println("Insira \'voltar\' se quiser sair da operação.\n");

                    do {
                        System.out.print("String: ");
                        str = read.next();

                        if (str.matches("voltar")) {
                            break;
                        }

                        System.out.println("\n" + operation + arrow + Notation.isItNumeric(str));
                    } while (str != "voltar");
                } else if (entry == 2) {
                    String number;

                    System.out.println("Insira \'voltar\' se quiser sair da operação.\n");

                    do {
                        System.out.print("String: ");
                        number = read.next();

                        System.out.println("\n" + operation + arrow + Notation.formatNumber(number));
                    } while (number != "voltar");
                } else if (entry == 3) {
                    String number;

                    System.out.println("Insira \'voltar\' se quiser sair da operação.\n");

                    do {
                        System.out.print("String: ");
                        number = read.next();

                        System.out.println("\n" + operation + arrow + Notation.isItDecimal(number));
                    } while (number != "voltar");
                } else if (entry == 4) {
                    String number;

                    System.out.println("Insira \'voltar\' se quiser sair da operação.\n");

                    do {
                        System.out.print("String: ");
                        number = read.next();

                        System.out.println("\n" + operation + arrow + Notation.isItFractional(number));
                    } while (number != "voltar");
                } else if (entry == 5) {
                    double decimal;

                    System.out.println("Insira \'0\' se quiser sair da operação.\n");

                    do {
                        System.out.print("Double: ");
                        decimal = read.nextDouble();

                        System.out.println("\n" + operation + arrow + FractionNotation.typeOfFraction(decimal));
                    } while (decimal != 0);
                } else if (entry == 6) {
                    String fraction;

                    System.out.println("Insira \'voltar\' se quiser sair da operação.\n");

                    do {
                        System.out.print("String: ");
                        fraction = read.next();

                        System.out.println("\n" + operation + arrow + FractionNotation.stringToArray(fraction));
                    } while (fraction != "voltar");
                } else if (entry == 7) {
                    String fraction;

                    System.out.println("Insira \'voltar\' se quiser sair da operação.\n");

                    do {
                        System.out.print("String: ");
                        fraction = read.next();

                        System.out.println("\n" + operation + arrow + FractionNotation.simplifyFraction(fraction));
                    } while (fraction != "voltar");
                } else if (entry == 8) {
                    String fraction;

                    System.out.println("Insira \'voltar\' se quiser sair da operação.\n");

                    do {
                        System.out.print("String: ");
                        fraction = read.next();

                        System.out.println("\n" + operation + arrow + FractionNotation.convertToDecimal(fraction));
                    } while (fraction != "voltar");
                } else if (entry == 9) {
                    double decimal;

                    System.out.println("Insira \'0\' se quiser sair da operação.\n");

                    do {
                        System.out.print("Double: ");
                        decimal = read.nextDouble();

                        System.out.println("\n" + operation + arrow + FractionNotation.typeOfFraction(decimal));
                    } while (decimal != 0);
                    FractionNotation.convertToFraction(decimal);
                } else if (entry == 10) {
                    String multiplier, multiplicand;

                    System.out.println("Insira \'voltar\' se quiser sair da operação.\n");

                    do {
                        System.out.print("String: ");
                        multiplier = read.next();

                        System.out.print("String: ");
                        multiplicand = read.next();

                        System.out.println("\n" + operation + arrow + Fraction.multiplication(multiplier, multiplicand));
                    } while (multiplier != "voltar");
                } else if (entry == 11) {
                    String dividend, divisor;

                    System.out.println("Insira \'voltar\' se quiser sair da operação.\n");

                    do {
                        System.out.print("String: ");
                        dividend = read.next();

                        System.out.print("String: ");
                        divisor = read.next();

                        System.out.println("\n" + operation + arrow + Fraction.division(dividend, divisor));
                    } while (dividend != "voltar");
                } else if (entry == 12) {
                    double num;

                    System.out.println("Insira \'0\' se quiser sair da operação.\n");

                    do {
                        System.out.print("Double: ");
                        num = read.nextDouble();

                        System.out.println("\n" + operation + arrow + Calculator.absoluteValue(num));
                    } while (num != 0);
                } else if (entry == 13) {
                    double decimal;
                    int method;

                    System.out.println("Insira \'0\' se quiser sair da operação.\n");

                    do {
                        System.out.print("Double: ");
                        decimal = read.nextDouble();

                        System.out.print("Int: ");
                        method = read.nextInt();

                        System.out.println("\n" + operation + arrow + Rounding.directedRounding(decimal, method));
                    } while (decimal != 0);
                } else if (entry == 14) {
                    double decimal;
                    int method;

                    System.out.println("Insira \'0\' se quiser sair da operação.\n");

                    do {
                        System.out.print("Double: ");
                        decimal = read.nextDouble();

                        System.out.print("Int: ");
                        method = read.nextInt();

                        System.out.println("\n" + operation + arrow + Rounding.roundToNearestInt(decimal, method));
                    } while (decimal != 0);
                } else if (entry == 15) {
                    double decimal;

                    System.out.println("Insira \'0\' se quiser sair da operação.\n");

                    do {
                        System.out.print("Double: ");
                        decimal = read.nextDouble();

                        System.out.println("\n" + operation + arrow + Rounding.roundRandomTieBreaker(decimal));
                    } while (decimal != 0);
                } else if (entry == 16) {
                    double decimal;
                    int digits;

                    System.out.println("Insira \'0\' se quiser sair da operação.\n");

                    do {
                        System.out.print("Double: ");
                        decimal = read.nextDouble();

                        System.out.print("Int: ");
                        digits = read.nextInt();

                        System.out.println("\n" + operation + arrow + Rounding.roundWithPrecision(decimal, digits));
                    } while (decimal != 0);
                } else if (entry == 17) {
                    int x, y;

                    System.out.println("Insira \'0\' se quiser sair da operação.\n");

                    do {
                        System.out.print("Int: ");
                        x = read.nextInt();

                        System.out.print("Int: ");
                        y = read.nextInt();

                        System.out.println("\n" + operation + arrow + Calculator.findGCD(x, y));
                    } while (x != 0);
                } else if (entry == 18) {
                    int x, y;

                    System.out.println("Insira \'0\' se quiser sair da operação.\n");

                    do {
                        System.out.print("Int: ");
                        x = read.nextInt();

                        System.out.print("Int: ");
                        y = read.nextInt();

                        System.out.println("\n" + operation + arrow + Calculator.findLCM(x, y));
                    } while (x != 0);
                }  else if (entry == 19) {
                    double base, exponent;

                    System.out.println("Insira \'0\' se quiser sair da operação.\n");

                    do {
                        System.out.print("Double: ");
                        base = read.nextDouble();

                        System.out.print("Double: ");
                        exponent = read.nextDouble();

                        System.out.println("\n" + operation + arrow + Calculator.toThePowerOf(base, exponent));
                    } while (base != 0);
                } else if (entry == 20) {
                    double a, n;

                    System.out.println("Insira \'0\' se quiser sair da operação.\n");

                    do {
                        System.out.print("Double: ");
                        a = read.nextDouble();

                        System.out.print("Double: ");
                        n = read.nextDouble();

                        System.out.println("\n" + operation + arrow + Calculator.nthRootOf(a, n));
                    } while (a != 0);
                } else {
                    System.out.println("\nOpção inválida. Tente novamente.\n");
                }

                menu();
            }
        }
    }

    public static void main(String[] args) throws Exception {

        //tests
        menu();
    }
}