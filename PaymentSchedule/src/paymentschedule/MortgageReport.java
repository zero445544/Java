package paymentschedule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class MortgageReport {
    private int principal;
    private MortgageCalculator mortgageCalculator;


    public MortgageReport(MortgageCalculator calculator, int principal) {
        this.mortgageCalculator = calculator;
        this.principal = principal;
    }

    public void printMortgage() {
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
        System.out.println("Mortgage");
        System.out.println("--------");

        double mortgage = mortgageCalculator.calculateMortgage();

        System.out.println("Monthly Payments: " + NumberFormat.getCurrencyInstance().format(mortgage));
    }

    public void printPaymentSchedule() {
        double balance = this.principal;

        System.out.println();
        System.out.println("Payment Schedule");
        System.out.println("----------------");

        while(balance > 0) {
            balance = mortgageCalculator.calculateBalance();

            String principalRemainderString = NumberFormat.getCurrencyInstance().format(balance);

            System.out.println(principalRemainderString);
        }
    }
}
