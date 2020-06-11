package student;

import bank.AccountType;
import edu.rit.cs.Currency;

public class CreditCard extends BankAccount {
    /** Limit on credit card, determined in constructor  */
    private Currency creditLimit;
    /** Monthly interest rate of credit card, determined in constructor */
    private double monthlyInterestRate;

    /**
     * A constructor for a CreditCard object. Internally, the
     * credit limit becomes the initial balance. This is done so
     * that exceeding the credit limit throws the same exception as
     * going below $0 in a debit account.
     * @param creditLimit the maximum the owner may borrow
     * @param InterestRate the annual interest rate on this loan account
     * @param owner the account owner's name
     */
    public CreditCard(Currency creditLimit, double InterestRate, String owner){
        super(creditLimit,owner,AccountType.CREDIT);
        this.creditLimit = creditLimit;
        this.monthlyInterestRate = InterestRate;

    }

    /**
     * public void endOfMonth()
     * Calculates the interest and updates the balance for this account.
     * Credit card accounts get charged interest on the entire current balance.
     */
    public void endOfMonth(){
        Currency x = getCurrentBalance();
        setInterestAccrued(x.multiply(monthlyInterestRate/NUM_PERIODS_PER_YEAR));
        addInterest(getInterest());

    }
    /**
     * Returns the account type ("CC")
     * @return "CC"
     */
    public String getAccountType(){
        return "CC";
    }

    /**
     * Returns the current amount owed, which is not the same as
     * the balance
     * @return amount owed on CC
     */
    public Currency getCurrentBalance(){
        return super.getCurrentBalance().subtract(creditLimit);

    }
}
