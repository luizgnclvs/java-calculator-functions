public class Circle {

    public static double pi (int accuracy) throws Exception {

        final double pi = 3.14159265359;

        return Rounding.roundWithPrecision(pi, accuracy);
    }

    public static double findDiameterFromArea (double area, int accuracy) throws Exception {

        return Calculator.nthRootOf(area / pi(accuracy) / 4.0, 2);
    }

    public static double areaWithRadius (double radius, int accuracy) throws Exception {

        return pi(accuracy) * radius * radius;
    }

    public static double areaWithDiameter (double diameter, int accuracy) throws Exception {

        return pi(accuracy) / 4.0 * diameter * diameter;
    }

    public static double circumferenceWithRadius (double radius, int accuracy) throws Exception {

        return 2.0 * pi(accuracy) * radius;
    }

    public static double circumferenceWithDiameter (double diameter, int accuracy) throws Exception {

        return pi(accuracy) * diameter;
    }
}