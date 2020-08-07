import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Scanner;

public class PaymentSchedule {
    // Constants
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

        System.out.println("Mortgage");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + mortgageString);

        System.out.println();

        System.out.println("Payment Schedule");
        System.out.println("----------------");

        // Create a method that subtracts monthly payments until 0
        PrintPaymentSchedule(principal, annualInterestRate, periodInYears);
    }

    private static double CalculateBalance(double L, double c, int n, int p) {
        // Do the balance calculation
        /*
            B = L[(1+c)^n - (1+c)^p]/[(1+c)^n - 1]

            - B = remaining loan balance
            - L = principal or loan amount
            - c = monthly interest rate
            - n = number of payments
            - p = number of payments we have made
        */
        double balance = L * ((Math.pow(1 + c, n) - Math.pow(1 + c, p)) / (Math.pow(1 + c, n) - 1));

        return RoundDouble(balance, 2);
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

    private static void PrintPaymentSchedule(double principal, float annualInterestRate, byte periodInYears) {
        double balance = principal;
        int paymentsMade = 0;
        double c = GetMonthlyInterestRateFromAnnual(annualInterestRate);
        int n = GetMonthsToPayOff(periodInYears);

        while(balance > 0) {
            paymentsMade += 1;

            balance = CalculateBalance(principal, c, n, paymentsMade);

            String principalRemainderString = NumberFormat.getCurrencyInstance().format(balance);

            System.out.println(principalRemainderString);
        }
    }

    private static double GetMonthlyInterestRateFromAnnual(float annualInterestRate) {
        double actualPercent = (annualInterestRate / PERCENT_DIVISOR);

        return (actualPercent / MONTHS_IN_A_YEAR);
    }

    private static int GetMonthsToPayOff(byte periodInYears) {
        return (periodInYears * MONTHS_IN_A_YEAR);
    }

    private static double RoundDouble(double doubleToRound, int places) {
        BigDecimal bd = new BigDecimal(Double.toString(doubleToRound));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
