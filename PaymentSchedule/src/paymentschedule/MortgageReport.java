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
        System.out.println("Mortgage");
        System.out.println("--------");

        double mortgage = mortgageCalculator.calculateMortgage();

        System.out.println("Monthly Payments: " + getCurrencyFormat(mortgage));
    }

    public void printPaymentSchedule() {
        double balance = this.principal;

        System.out.println();
        System.out.println("Payment Schedule");
        System.out.println("----------------");

        while(balance > 0) {
            balance = mortgageCalculator.calculateBalance();

            System.out.println(getCurrencyFormat(balance));
        }
    }

    private String getCurrencyFormat(double value)
    {
        return NumberFormat.getCurrencyInstance().format(value);
    }
}
