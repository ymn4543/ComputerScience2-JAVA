package student;

import bank.AccountType;
import edu.rit.cs.Currency;

public class CheckingAccount extends BankAccount{
    /** Is this account paid interest?*/
    private boolean bonus;
    /** The interest rate for a bonus account - currently 0.1% per year */
    private static double BONUS_MONTHLY_RATE = 0.001/NUM_PERIODS_PER_YEAR;
    /** The account type for this account ("CI"  or "CN") */
    private String chkAcctType;
    /** The minimum balance to earn interest.*/
    private static Currency PREMIUM_CHECKING_MINIMUM_BALANCE = new Currency(500,0);


    /**
     * A constructor for a CheckingAccount object. It accepts the amount
     * of money deposited when the account is created.
     * @param newMoney The amount of money deposited when the account is opened.
     * @param owner The owner of the account
     * @param bonus boolean indicating if this account is paid interest
     */
    public CheckingAccount(Currency newMoney, String owner, boolean bonus){
        super(newMoney, owner,AccountType.DEBIT);
        this.bonus = bonus;
        if(bonus){
            chkAcctType = "CI";
        }
        else{
            chkAcctType = "CN";
        }
    }

    /**
     * Calculates the interest and updates the balance for the account.
     * Checking accounts earn interest only if they are a "bonus" account.
     * Otherwise the interest is 0 and the balance is unchanged.
     */
    public void endOfMonth(){
        if(bonus && getCurrentBalance().compareTo(PREMIUM_CHECKING_MINIMUM_BALANCE) == 1){
            Currency intEarned = getCurrentBalance().multiply(BONUS_MONTHLY_RATE);
            setInterestAccrued( intEarned );
            addInterest( intEarned );
        }
    }

    /**
     * Returns the account type ("CN" or "CI")
     * @return chkAcctType
     */
    public String getAccountType(){
        return chkAcctType;
    }

    @Override
    /**
     * overrides BankAccount's toString method
     * @return string representation of account with owner, balance, and type.
     */
    public String toString(){
        return  getOwnerName() + " "+ getCurrentBalance() +" "+ getAccountType();
    }

}
