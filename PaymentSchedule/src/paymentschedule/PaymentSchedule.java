package paymentschedule;


public class PaymentSchedule {
    // Constants
    private static final int THOUSAND = 1000;
    private static final int MILLION = 1000000;
    private static final int MAX_INTEREST_RATE = 30;

    public static void main(String[] args) {
        int principal = (int) Console.readNumber("Principal ($1K - $1M): ", THOUSAND, MILLION);
        float annualInterestRate = (float) Console.readNumber("Annual Interest Rate: ", 0, MAX_INTEREST_RATE);
        byte periodInYears = (byte) Console.readNumber("Period (years): ");

        MortgageCalculator calculator = new MortgageCalculator(principal, annualInterestRate, periodInYears);
        MortgageReport report = new MortgageReport(calculator, principal);

        report.printMortgage();
        report.printPaymentSchedule();
    }
}
