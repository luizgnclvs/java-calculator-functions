import java.util.Random;
import java.util.Scanner;

public class Rounding {

    public static int directedRounding (double decimal, int method) {
    //these four methods are called directed rounding, as the displacements from the original number to the rounded value are all directed toward or away from the same limiting value {0, +∞, or −∞}

        if (method == 0) {
            //rounding toward negative infinity {−∞} (or take the 'floor')
            return roundDown(decimal);
        } else if (method == 1) {
            //rounding toward positive infinitty {+∞} (or take the 'ceiling')
            return roundUp(decimal);
        } else if (method == 2) {
            //rounding away from infinity {+∞ and −∞} (or 'truncate')
            return roundTowardZero(decimal);
        } else if (method == 3) {
            //rounding away from zero {0}
            return roundTowardInfinity(decimal);
        } else {
            try (Scanner read = new Scanner(System.in)) {
                System.out.print("\n\nO método escolhido é inválido! Selecione outro: ");
                method = read.nextInt();

                return directedRounding(decimal, method);
            }
        }
    }

    public static int roundDown (double decimal) {

        if (decimal < 0) {
            return roundUp(Calculator.absoluteValue(decimal)) * -1;
        } else {
            int i = 0;

            while (true) {
                if (i < decimal && (i + 1) > decimal) {
                    break;
                } else {
                    i++;
                }
            }

            return i;
        }
    }

    public static int roundUp (double decimal) {

        if (decimal < 0) {
            return roundDown(Calculator.absoluteValue(decimal)) * -1;
        } else {
            return roundDown(Calculator.absoluteValue(decimal)) + 1;
        }
    }

    public static int roundTowardZero (double decimal) {

        if (decimal < 0) {
            return roundUp(decimal);
        } else {
            return roundDown(decimal);
        }
    }

    public static int roundTowardInfinity (double decimal) {

        if (decimal < 0) {
            return roundDown(decimal);
        } else {
            return roundUp(decimal);
        }
    }

    public static int roundToNearestInt (double decimal, int method) {
        //rounding a number to the nearest integer requires some tie-breaking rule for those cases when it is exactly half-way between two integers — i.e. when its fraction part is exactly {0.5}

        if (method == 0) {
            //rounding half toward negative infinity {-∞}
            return roundHalfDown(decimal);
        } else if (method == 1) {
            //rounding half toward positive infinity {+∞}
            return roundHalfUp(decimal);
        } else if (method == 2) {
            //rounding half away from infinity {+∞, -∞}
            return roundHalfTowardZero(decimal);
        } else if (method == 3) {
            //rounding half away from zero {0}
            return roundHalfTowardInfinity(decimal);
        } else if (method == 4) {
            //rounds to the nearest even integer
            return roundHalfToEven(decimal);
        } else if (method == 5) {
            //rounds to the nearest odd integer
            return roundHalfToOdd(decimal);
        } else {
            try (Scanner read = new Scanner(System.in)) {
                System.out.print("\n\nO método escolhido é inválido! Selecione outro: ");
                method = read.nextInt();

                return roundToNearestInt(decimal, method);
            }
        }
    }

    public static int roundHalfDown (double decimal) {

        double decimalPoint = Calculator.absoluteValue(decimal % roundTowardZero(decimal));

        if (decimalPoint > 0.5) {
            return roundUp(decimal);
        } else {
            return roundDown(decimal);
        }
    }

    public static int roundHalfUp (double decimal) {

        double decimalPoint = Calculator.absoluteValue(decimal % roundTowardZero(decimal));

        if (decimalPoint < 0.5) {
            return roundDown(decimal);
        } else {
            return roundUp(decimal);
        }
    }

    public static int roundHalfTowardZero (double decimal) {

        if (decimal < 0) {
            return roundHalfUp(decimal);
        } else {
            return roundHalfDown(decimal);
        }
    }

    public static int roundHalfTowardInfinity (double decimal) {

        if (decimal < 0) {
            return roundHalfDown(decimal);
        } else {
            return roundHalfUp(decimal);
        }
    }

    public static int roundHalfToEven (double decimal) {

        double decimalPoint = Calculator.absoluteValue(decimal % roundTowardZero(decimal));
        double integer = Calculator.absoluteValue(decimal) - decimalPoint;

        if (integer % 2 == 0) {
            if (decimal < 0) {
                return roundHalfUp(decimal);
            } else {
                return roundHalfDown(decimal);
            }
        } else {
            if (decimal < 0) {
                return roundHalfDown(decimal);
            } else {
                return roundHalfUp(decimal);
            }
        }
    }

    public static int roundHalfToOdd (double decimal) {

        double decimalPoint = Calculator.absoluteValue(decimal % roundTowardZero(decimal));
        double integer = Calculator.absoluteValue(decimal) - decimalPoint;

        if (integer % 2 == 0) {
            if (decimal < 0) {
                return roundHalfDown(decimal);
            } else {
                return roundHalfUp(decimal);
            }
        } else {
            if (decimal < 0) {
                return roundHalfUp(decimal);
            } else {
                return roundHalfDown(decimal);
            }
        }
    }

    public static int roundRandomTieBreaker (double decimal) {

        Random r = new Random();

        int method = r.nextInt(3);
        int upOrDown = r.nextInt(2);

        if (method == 0) {
            if (upOrDown == 0) {
                return roundHalfDown(decimal);
            } else {
                return roundHalfUp(decimal);
            }
        } else if (method == 1) {
            if (upOrDown == 0) {
                return roundHalfTowardInfinity(decimal);
            } else {
                return roundHalfTowardZero(decimal);
            }
        } else {
            if (upOrDown == 0) {
                return roundHalfToEven(decimal);
            } else {
                return roundHalfToOdd(decimal);
            }
        }
    }

    public static double roundWithPrecision (double decimal, int digits) throws Exception {

        if (digits < 0) {
            throw new Exception("Índice inválido.");
        }

        if (digits == 0) {
            return roundRandomTieBreaker(decimal);
        } else {
            return roundRandomTieBreaker(decimal * Calculator.toThePowerOf(10.0, digits)) / Calculator.toThePowerOf(10.0, digits);
        }
    }
}