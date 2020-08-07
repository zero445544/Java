package paymentschedule;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MortgageCalculator {
    private final byte MONTHS_IN_A_YEAR = 12;
    private final byte PERCENT_DIVISOR = 100;

    private int paymentsMade = 0;
    private int principal;
    private float annualInterestRate;
    private byte periodInYears;

    public MortgageCalculator(int principal, float annualInterestRate, byte periodInYears) {
        this.principal = principal;
        this.annualInterestRate = annualInterestRate;
        this.periodInYears = periodInYears;
    }

    public double calculateMortgage() {
        double r = getMonthlyInterestRateFromAnnual(annualInterestRate);

        int n = getMonthsToPayOff(periodInYears);

        return principal * (r * Math.pow(1 + r, n) / (Math.pow(1 + r, n) - 1));
    }

    public double calculateBalance() {
        // Do the balance calculation
        /*
            B = L[(1+c)^n - (1+c)^p]/[(1+c)^n - 1]

            - B = remaining loan balance
            - L = principal or loan amount
            - c = monthly interest rate
            - n = number of payments
            - p = number of payments we have made
        */
        ++paymentsMade;

        double c = getMonthlyInterestRateFromAnnual(annualInterestRate);
        int n = getMonthsToPayOff(periodInYears);

        double balance = principal * ((Math.pow(1 + c, n) - Math.pow(1 + c, paymentsMade)) / (Math.pow(1 + c, n) - 1));

        return RoundDouble(balance, 2);
    }

    private int getMonthsToPayOff(byte periodInYears) {
        return (periodInYears * MONTHS_IN_A_YEAR);
    }

    private double getMonthlyInterestRateFromAnnual(float annualInterestRate) {
        double actualPercent = (annualInterestRate / PERCENT_DIVISOR);

        return (actualPercent / MONTHS_IN_A_YEAR);
    }

    private double RoundDouble(double doubleToRound, int places) {
        BigDecimal bd = new BigDecimal(Double.toString(doubleToRound));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
