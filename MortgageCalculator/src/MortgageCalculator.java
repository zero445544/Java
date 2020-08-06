import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalculator {
    /*
        Constant used to calculate monthly interest rate and
        and month to pay off the load.
     */
    protected static final byte MONTHS_IN_A_YEAR = 12;
    protected static final byte PERCENT_DIVISOR = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Principal: ");
        int principal = scanner.nextInt();

        System.out.print("Annual Interest Rate: ");
        float annualInterestRate = scanner.nextFloat();

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
