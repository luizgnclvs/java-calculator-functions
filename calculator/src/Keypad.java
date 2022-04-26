import java.util.Scanner;

public class Keypad {

    public static void menu () throws Exception {

        String [] classes = new String [] {"Sair", "\'Notation\'", "\'FractionNotation\'", "\'Fraction\'", "\'Rounding\'", "\'Calculator\'"};

        System.out.printf("\n%s \n%s" , "Olá, gostaria fazer alguma operação matemática?", "Insira o número correspondente à classe de operações desejada.\n");

        for (int i = 0; i < classes.length; i++) {
            System.out.printf("\n\t%d\t%s\t%s", i, "===>", classes[i]);
        }

        try (Scanner read = new Scanner(System.in)) {
            System.out.print("\n\nClasse: ");
            int entry = read.nextInt();

            for (int i = 0; i < 1; i++) {

                if (entry == 0) {
                    System.out.println("\nOk. Obrigado pela atenção.");
                } else if (entry > 0 && entry < classes.length) {
                    execute(entry);
                } else {
                    System.out.print("\n\nOpção inválida! Tente novamente: ");
                    entry = read.nextInt();

                    i--;
                }
            }
        }
    }

    public static String [] notation () {

        String [] notation = new String [] {"Sair", "\'isItNumeric\'", "\'formatNumber\'", "\'isItDecimal\'", "\'isItFractional\'"};

        return notation;
    }

    public static String [] fractionNotation () {

        String [] fractionNotation = new String [] {"Sair", "\'typeOfFraction\'", "\'stringToArray\'", "\'simplifyFraction\'", "\'convertToDecimal\'", "\'convertToFraction\'", "\'equivalentFractions\'"};    

        return fractionNotation;
    }

    public static String [] fraction () {

        String [] fraction = new String [] {"Sair", "\'multiplication\'", "\'division\'", "\'additionOrSubtraction\'"};

        return fraction;
    }

    public static String [] rounding () {

        String [] rounding = new String [] {"Sair", "\'directedRounding\'", "\'roundToNearestInt\'", "\'roundRandomTieBreaker\'", "\'roundWithPrecision\'"};

        return rounding;
    }

    public static String [] calculator () {

        String [] calculator = new String [] {"Sair", "\'absoluteValue\'", "\'findGCD\'", "\'findLCM\'", "\'toThePowerOf\'", "\'nthRootOf\'"};

        return calculator;
    }

    public static void execute (int entry) throws Exception {

        String [] operations;

        if (entry > 0 && entry < 6) {
            if (entry == 1) {
                operations = notation();
            } else if (entry == 2) {
                operations = fractionNotation();
            } else if (entry == 3) {
                operations = fraction();
            } else if (entry == 4) {
                operations = rounding();
            } else {
                operations = calculator();
            }

            System.out.println("\nQual operação deseja realizar?\n");

            try (Scanner read = new Scanner(System.in)) {
                for (int i = 0; i < 1; i++) {

                    for (int j = 0; j < operations.length; j++) {
                        System.out.printf("\t%d\t%s\t%s\n", j, "===>", operations[j]);
                    }

                    System.out.print("\nOperação: ");
                    int ops = read.nextInt();

                    //sair
                    if (ops == 0) {
                        System.out.println("\nOk. Obrigado pela atenção.");

                        menu();
                    } else if (ops > 0 && ops < 7) {
                        if (ops == 1) {
                            if (entry == 1) {
                                String str = read.next();
                                System.out.println(Notation.isItNumeric(str));
                            } else if (entry == 2) {
                                double decimal = read.nextDouble();
                                System.out.println(FractionNotation.typeOfFraction(decimal).length + "\n");

                                for (int j = 0; j < FractionNotation.typeOfFraction(decimal).length; j++) {
                                    System.out.print(FractionNotation.typeOfFraction(decimal)[j] + "    ");
                                }

                                System.out.print("\n");
                            } else if (entry == 3) {
                                String multiplier = read.next();
                                String multiplicand = read.next();
                                System.out.println(Fraction.multiplication(multiplier, multiplicand));
                            } else if (entry == 4) {
                                double decimal = read.nextDouble();
                                int method = read.nextInt();
                                System.out.println(Rounding.directedRounding(decimal, method));
                            } else {
                                double num = read.nextDouble();
                                System.out.println(Calculator.absoluteValue(num));
                            }
                        } else if (ops == 2) {
                            if (entry == 1) {
                                String number = read.next();
                                System.out.println(Notation.formatNumber(number));
                            } else if (entry == 2) {
                                String fraction = read.next();

                                for (int j = 0; j < FractionNotation.stringToArray(fraction).length; j++) {
                                    System.out.print(FractionNotation.stringToArray(fraction)[j] + "    ");
                                }

                                System.out.print("\n");
                            } else if (entry == 3) {
                                String dividend = read.next();
                                String divisor = read.next();
                                System.out.println(Fraction.division(dividend, divisor));
                            } else if (entry == 4) {
                                double decimal = read.nextDouble();
                                int method = read.nextInt();
                                System.out.println(Rounding.roundToNearestInt(decimal, method));
                            } else {
                                int x = read.nextInt();
                                int y = read.nextInt();
                                System.out.println(Calculator.findGCD(x, y));
                            }
                        } else if (ops == 3) {
                            if (entry == 1) {
                                String number = read.next();
                                System.out.println(Notation.isItDecimal(number));
                            } else if (entry == 2) {
                                String fraction = read.next();
                                System.out.println(FractionNotation.simplifyFraction(fraction));
                            } else if (entry == 3) {
                                String term1 = read.next();
                                String term2 = read.next();
                                System.out.println(Fraction.additionOrSubtraction(term1, term2));
                            } else if (entry == 4) {
                                double decimal = read.nextDouble();
                                System.out.println(Rounding.roundRandomTieBreaker(decimal));
                            } else {
                                int x = read.nextInt();
                                int y = read.nextInt();
                                System.out.println(Calculator.findLCM(x, y));
                            }
                        } else if (ops == 4) {
                            if (entry == 1) {
                                String number = read.next();
                                System.out.println(Notation.isItFractional(number));
                            } else if (entry == 2) {
                                String fraction = read.next();
                                System.out.println(FractionNotation.convertToDecimal(fraction));
                            } else if (entry == 3) {
                                System.out.print("\n\nOpção inválida! Tente novamente: ");
                                ops = read.nextInt();
    
                                i--;
                            } else if (entry == 4) {
                                double decimal = read.nextDouble();
                                int digits = read.nextInt();
                                System.out.println(Rounding.roundWithPrecision(decimal, digits));
                            } else {
                                double base = read.nextDouble();
                                double exponent = read.nextDouble();
                                System.out.println(Calculator.toThePowerOf(base, exponent));
                            }
                        } else if (ops == 5) {
                            if (entry == 1) {
                                System.out.print("\n\nOpção inválida! Tente novamente: ");
                                ops = read.nextInt();
    
                                i--;
                            } else if (entry == 2) {
                                double decimal = read.nextDouble();
                                System.out.println(FractionNotation.convertToFraction(decimal));
                            } else if (entry == 3) {
                                System.out.print("\n\nOpção inválida! Tente novamente: ");
                                ops = read.nextInt();
    
                                i--;
                            } else if (entry == 4) {
                                System.out.print("\n\nOpção inválida! Tente novamente: ");
                                ops = read.nextInt();
    
                                i--;
                            } else {
                                double a = read.nextDouble();
                                double n = read.nextDouble();
                                System.out.println(Calculator.nthRootOf(a, n));
                            }

                            i--;
                        } else if (ops == 6) {
                            if (entry == 2) {
                                String fraction1 = read.next();
                                String fraction2 = read.next();

                                for (int j = 0; j < FractionNotation.equivalentFractions(fraction1, fraction2).length; j++) {
                                    System.out.print(FractionNotation.equivalentFractions(fraction1, fraction2)[j] + "    ");
                                }
                            } else {
                                System.out.print("\n\nOpção inválida! Tente novamente: ");
                                ops = read.nextInt();
    
                                i--;
                            }
                        } else {
                            System.out.print("\n\nOpção inválida! Tente novamente: ");
                            entry = read.nextInt();

                            i--;
                        }

                        System.out.print("\n");
                        i--;
                    }
                }
            }
        } else {
            System.out.println("\nOcorreu algum tipo de erro. Tente novamente.\n");
        }
    }

    public static void main(String[] args) throws Exception {

        menu();
    }
}