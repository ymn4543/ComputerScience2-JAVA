public class BankAccount {


    public final static double INTEREST_RATE = 2;
    private int account;
    private double balance;


    //constructor
    public BankAccount(int account, double balance){
        this.account = account;
        this.balance = balance;
    }
    public BankAccount(int account){
        this.account = account;
    }

    //getters

    public double getBalance() {
        return this.balance;
    }

    //setters

    public double deposit(double amount){
        this.balance += amount;
        return 0;

    }
    public boolean withdraw(double amount){
        if(amount<= balance){
            this.balance-=amount;
            return true;
        }
        return false;
    }
    public void applyInterest(){
        this.balance *= INTEREST_RATE;

    }

    public String toString(){
        return " ";
    }


}
