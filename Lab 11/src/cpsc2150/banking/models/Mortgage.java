package cpsc2150.banking.models;

/**
 * Mortgage is the extension of AbsMortgage and is the implementation for the IMortgage interface
 *
 * @invariant
 *  MONTHS = #MONTHS
 *
 * @correspondence
 *  Payment = getPayment AND
*   Rate = getRate AND
*   Customer = customer AND
*   DebtToIncomeRatio = debtToIncome AND
*   Principal = getPrincipal AND
*   NumberOfPayments = numOfPayments AND
*   PercentDown = percentDown
 */

public class Mortgage extends AbsMortgage implements IMortgage{
    public static final int MONTHS = 12;
    private double downPayment;
    private int years;
    private int numOfPayments;
    private double percentDown;
    private double debtToIncome;
    private double principal;
    private double aprRate = BASERATE;
    private ICustomer customer;

    /**
     *
     * @param hC is the cost of the house for the customer
     * @param dPay is the customer's down payment for the house
     * @param yrs is the number of years the loan is for
     * @param c is the customer object that is passed, it contains all the information pertaining to the customer
     *
     * @pre
     *  c != null AND
     *  hC >= 0 AND
     *  dPay >= 0 AND
     *  yrs >= 0
     *
     * @post
     * customer = c AND
     * years = yrs AND
     * downPayment = dPay AND
     * houseCost = hC AND
     * percentDown = downPayment/houseCost
     * numOfPayments = MONTHS * years
     * debtToIncome = [debt payments divided by the customer's income all in the same period of time]
     */
    public Mortgage(double hC, double dPay, int yrs, ICustomer c){
        double debtPayment;
        customer = c;
        downPayment = dPay;
        years = yrs;
        numOfPayments = MONTHS * years;
        percentDown = downPayment/hC;
        principal = hC - downPayment;
        debtPayment = customer.getMonthlyDebtPayments() + getPayment();
        debtToIncome = (debtPayment)/((customer.getIncome() / MONTHS));
    }
    public boolean loanApproved(){
        if(getRate() < RATETOOHIGH && percentDown >= MIN_PERCENT_DOWN && debtToIncome <= DTOITOOHIGH){
            return true;
        }
        return false;
    }

    public double getPayment(){
        double monthlyInterest = (getRate() / MONTHS);
        double numerator = monthlyInterest * principal;
        double totalPayments = -(numOfPayments);
        double denominator = (1 - Math.pow(1 + monthlyInterest, totalPayments));
        return numerator / denominator;
    }

    public double getRate(){
        aprRate = BASERATE;
        int credit = customer.getCreditScore();

        if(years < MAX_YEARS){
            aprRate += GOODRATEADD;
        }else{
            aprRate += NORMALRATEADD;
        }

        if(percentDown < PREFERRED_PERCENT_DOWN){
            aprRate += GOODRATEADD;
        }

        if(credit < BADCREDIT){
            aprRate += VERYBADRATEADD;
        }else if(credit >= BADCREDIT && credit < FAIRCREDIT){
            aprRate += BADRATEADD;
        }else if(credit >= FAIRCREDIT && credit < GOODCREDIT){
            aprRate += NORMALRATEADD;
        }else if(credit >= GOODCREDIT && credit < GREATCREDIT){
            aprRate += GOODRATEADD;
        }else if(credit >= GREATCREDIT && credit <= 850){
            aprRate += 0;
        }

        return aprRate;
    }

    public double getPrincipal(){
        return principal;
    }

    public int getYears(){
        return years;
    }

}
