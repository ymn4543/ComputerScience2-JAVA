/*
 * BankAccount.java
 */

package student;

import bank.AccountType;
import bank.InsufficientFunds;
import edu.rit.cs.Currency;

/**
 * The BankAccount class represents a bank account.
 * This abstract class includes a method to calculate interest
 * on the account.  Since account types use different
 * calculations for calculating interest, the method is
 * abstract; subclasses must implement this method for
 * the specific account type.
 *
 * @author Youssef Naguib </ymn4543@rit.edu>
 */

public abstract class BankAccount {

    /**
     * A handy conversion constant, since interest gets compounded often
     * but interest rates are published as annual rates. Here we set
     * the period to be a month.
     */
    public static final int NUM_PERIODS_PER_YEAR = 12;

    /**
     * Is this an account that holds a negative or positive balance?
     */
    public final AccountType type;

    // DECLARE YOUR PRIVATE VARIABLES HERE.
    private Currency currentBalance;
    private Currency interestAccrued;
    private String ownerName;



    // USING THE JAVADOC, FILL IN THE ACTUAL CODE.
    /**
     * A constructor for a BankAccount object. It accepts the amount
     * of money deposited (the balance) when the account is created.
     *
     * @param newMoney  The amount of money deposited when the account
     *                  is opened.
     * @param ownerName The owner of this account
     * @param type credit or debit account (affects balance reporting)
     */
    public BankAccount(Currency newMoney, String ownerName, AccountType type ) {
        this.currentBalance = newMoney;
        this.ownerName = ownerName;
        this.type = type;
        interestAccrued = new Currency(0,0);
    }

    /**
     * Fetch the account holder.
     * @return this account's owner's name
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * The account's monthly period is over.
     * Calculate the interest and update the balance for this account.
     * The rules for earning interest
     * are different for every kind of account.
     */
    public abstract void endOfMonth();

    /**
     * Put more money into the account. (deposit or payment)
     * @param addition the amount of money to be added
     */
    public void credit( Currency addition ) {
        currentBalance = currentBalance.add(addition);
    }

    /**
     * Remove some money from the account. (withdrawal or charge)
     * If this would cause the true internal balance to be negative,
     * @param deduction the amount of money to be taken out
     * @throws InsufficientFunds if the deduction would case the internal
     *             balance of the account to be negative. (Balance is not
     *             changed in that case.)
     */
    public void debit( Currency deduction ) {
        if(currentBalance.compareTo(deduction) == -1){
            throw new InsufficientFunds();
        }
        else {
            currentBalance = currentBalance.subtract(deduction);
        }
    }


    /**
     * Return the two character String identifier for the account.
     *
     * @return The two character String identifier
     */
    public abstract String getAccountType();

    /**
     * Store the interest Accrued. This information is used by the
     * report generator in its monthly report.
     *
     * @param interest the calculated interest amount
     */
    protected void setInterestAccrued( Currency interest ) {
        interestAccrued = interest;
    }

    /**
     * Print a statement for this month.
     * @see java.io.PrintStream#printf(String, Object...)
     */
    public void printStatement() {
        System.out.println(getAccountType()+ " " + getOwnerName() + " Interest Accrued: " + getInterest() +
                " Current Balance: " + getCurrentBalance()
        );
    }


    /**
     * Return the current balance, as a quantity useful to the account owner.
     *
     * @return the current balance
     */
    public Currency getCurrentBalance() {
        return currentBalance;
    }


    /**
     * Return the current interest Accrued
     *
     * @return the current interest Accrued
     */
    public Currency getInterest() {
        return interestAccrued;
    }

    /**
     * Add the current interest amount to the current balance
     *
     * @param newInterestAccrued the total amount of interest Accrued.
     */
    protected void addInterest( Currency newInterestAccrued ) {
        currentBalance = currentBalance.add(newInterestAccrued);
    }

    /**
     * A printable version of this account
     *
     * @return a string containing
     *             the owner name a space, and the current balance
     */
    @Override
    public String toString() {
        return  getOwnerName() + " "+ getCurrentBalance();
    }

} // BankAccount
