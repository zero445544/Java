import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {
    /*
        Constant used to calculate monthly interest rate and
        and month to pay off the load.
     */
    private static final byte MONTHS_IN_A_YEAR = 12;
    private static final byte PERCENT_DIVISOR = 100;
    private static final int THOUSAND = 1000;
    private static final int MILLION = 1000000;
    private static final int MAX_INTEREST_RATE = 30;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int principal;

        while(true) {
            System.out.print("Principal ($1K - $1M): ");

            principal= scanner.nextInt();

            if (THOUSAND <= principal && principal <= MILLION)
                break;

            System.out.println("Enter a number between 1,000 and 1,000,000");
        }

        float annualInterestRate;

        while(true) {
            System.out.print("Annual Interest Rate: ");

            annualInterestRate = scanner.nextFloat();

            if (annualInterestRate > 0 && annualInterestRate <= MAX_INTEREST_RATE)
                break;

            System.out.println("Enter a value greater than 0 and less than or equal to ");
        }


        System.out.print("Period (years): ");
        byte periodInYears = scanner.nextByte();

        double mortgage = CalculateMortgage(principal, annualInterestRate, periodInYears);

        String mortgageString = NumberFormat.getCurrencyInstance().format(mortgage);

        System.out.println("Mortgage: " + mortgageString);
    }

    private static double CalculateMortgage(int principal, float annualInterestRate, byte periodInYears) {
        // Do the mortgage calculation
        /*
                  r(1 + r)^n
            M = P---------------
                  (1 + r)^n - 1
            - M = monthly payment or mortgage (What we need to figure out.)
            - P = principal
            - r = monthly interest rate
            - n = months to pay on the loan
         */
        // Name the variable to match the formula for simplicity.
        double r = GetMonthlyInterestRateFromAnnual(annualInterestRate);

        int n = GetMonthsToPayOff(periodInYears);

        return principal * (r * Math.pow(1 + r, n) / (Math.pow(1 + r, n) - 1));
    }

    private static double GetMonthlyInterestRateFromAnnual(float annualInterestRate) {
        double actualPercent = (annualInterestRate / PERCENT_DIVISOR);

        return (actualPercent / MONTHS_IN_A_YEAR);
    }

    private static int GetMonthsToPayOff(byte periodInYears) {
        return (periodInYears * MONTHS_IN_A_YEAR);
    }
}
