package paymentschedule;

import java.util.Scanner;

public class Console {
    private static Scanner scanner = new Scanner(System.in);

    public static double readNumber(String prompt, int min, int max) {
        double value;

        while (true) {
            System.out.print(prompt);

            value = scanner.nextDouble();

            if (min <= value && value <= max)
                break;

            System.out.println("Enter a number between " + min + " and" + max);
        }

        return value;
    }

    public static double readNumber(String prompt) {
        System.out.print(prompt);
        return scanner.nextDouble();
    }
}
