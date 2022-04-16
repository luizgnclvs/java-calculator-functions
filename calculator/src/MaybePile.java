public class MaybePile {

    public static String[] fractionDivider(String fraction) {

        int index = countOccurences(fraction, 0);

        String [] numbers = new String [index];

        for (int i = 0; i < numbers.length; i++) {
            if (i == numbers.length - 1) {
                numbers[i] = fraction;
            } else {
                numbers[i] = fraction.substring(0, fraction.indexOf("/"));
                fraction = fraction.substring(fraction.indexOf("/") + 1);
            }
        }

        String [] fractions = new String [2];

        int j = 0;

        for (int i = 0; i < fractions.length; i++) {
            fractions[i] = numbers[j] + "/" + numbers[j + 1];
            j += 2;
        }

        return fractions;
    }

    public static int countOccurences(String str, int index) {
        if (index >= str.length()) {
            return 0;
        }

        int count;
        if (str.charAt(index) == '/') {
            count = 1;
        } else {
             count = 0;
        }
        return count + countOccurences(str, index + 1);
    }
}
