import java.util.Date;

public class CreditCard {
    private String number = "";
    private Double balance = 0.0;
    private String cardHolder = "";
    private Date expirationDate = new Date();
    private Boolean hasCreditLine = false;
    private Double creditLimit = 1500.0;


    public CreditCard(String number, Double balance, String cardHolder, Date expirationDate, Boolean hasCreditLine, Double creditLimit){
        this.number = number;
        this.balance = balance;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
        this.hasCreditLine = hasCreditLine;
        this.creditLimit = creditLimit;
    }

    public boolean isExpired() {
        if(expirationDate.compareTo(new Date())>0) {
            return false;
        }

        return true;
    }
    public boolean withdrawATM(int amount) {
        if(!isExpired() && amount<this.balance) {
            this.balance-=amount;
            return true;
        }

        return false;
    }

    public boolean transferFrom(double amount) {
        if(!isExpired() && amount<this.balance) {
            this.balance-=amount;
            return true;
        }

        return false;
    }

    public boolean transferTo(double amount) {
        if(!isExpired()) {
            this.balance+=amount;
            return true;
        }

        return false;
    }

    public boolean credit(double amount) {
        if(hasCreditLine && amount<this.creditLimit && !isExpired()) {
            this.balance += amount;
            return true;
        }
        return false;
    }
}
