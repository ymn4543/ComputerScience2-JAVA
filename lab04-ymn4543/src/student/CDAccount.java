package student;

import bank.AccountType;
import edu.rit.cs.Currency;

import java.util.concurrent.Callable;

public class CDAccount extends BankAccount {
    /** The minimum amount that must be kept in the account at all times;
     * interest is earned only on the amount over the minimum. */
    private static final Currency MINIMUM_BALANCE = new Currency(1000,0);;
    /** The annual interest rate for balances greater than the minimum - currently 0.6%. */
    private static double MONTHLY_INTEREST_RATE = 0.006/12;

    /**
     * A constructor for a CDAccount object. It accepts the amount of money
     * deposited when the account is created and the owner's name.
     * @param newMoney The amount of money deposited when the account is opened.
     * @param owner The owner of this account
     */
    public CDAccount(Currency newMoney, String owner){
        super(newMoney,owner,AccountType.DEBIT);
    }

    /**
     * Calculates the interest and updates the balance for this account.
     * With CD accounts interest is only earned on everything over
     * the minimum amount. Interest earned is added to the current balance.
     */
    public void endOfMonth(){
        if(getCurrentBalance().compareTo(MINIMUM_BALANCE) == 1 ){
            Currency gettingInterest = getCurrentBalance().subtract(MINIMUM_BALANCE);
            Currency interestEarned = gettingInterest.multiply(MONTHLY_INTEREST_RATE);
            setInterestAccrued(interestEarned);
            addInterest(interestEarned);
        }
    }

    /**
     * Returns the account type ("CD")
     * @return "CD"
     */
    public String getAccountType(){
        return "CD";
    }

    @Override
    /**
     * overrides BankAccount's toString method
     * @return string representation of account with owner, balance, and type.
     */
    public String toString(){
        return  getOwnerName() +" " + getCurrentBalance() + " " + getAccountType();
    }

}

