package Patterns.behavioral;

import java.math.BigDecimal;

public class Strategy {
    public static void main(String[] args) {

        PaymentStrategy creditCard = new CreditCardPayment("123-456-7876");
        PaymentStrategy upi = new UPIPayment("9763487676@icici");
        PaymentStrategy netBanking = new NetBanking("876487323234");
        ShoppingCart cart = new ShoppingCart();
        cart.setPaymentType(creditCard);
        cart.checkout(BigDecimal.valueOf(100));


        cart.setPaymentType(upi);
        cart.checkout(BigDecimal.valueOf(600));

        cart.setPaymentType(netBanking);
        cart.checkout(BigDecimal.valueOf(900));





    }
}

/**
 * Behavioral patterns control how objects interact and distribute responsibilities.
 * They're about the flow of control and communication between objects.
 *
 * Strategy replaces conditional logic with polymorphism.
 * Use it when you have different ways of doing the same thing and you want to swap them at runtime.
 */

interface PaymentStrategy {
    void pay(BigDecimal amount);
}

class CreditCardPayment implements PaymentStrategy {
    private final String creditCardNumber;

    public CreditCardPayment(String cardNumber){
        creditCardNumber = cardNumber;
    }

    public void pay(BigDecimal amount) {
        System.out.println("Paid amount = " + amount+ " - Using card - "+ creditCardNumber);
    }
}

class UPIPayment implements PaymentStrategy {
    private final String upiId;

    public UPIPayment(String upiId){
        this.upiId = upiId;
    }

    public void pay(BigDecimal amount) {
        System.out.println("Paid amount = " + amount+ " - Using UPI - "+ upiId);
    }
}

class NetBanking implements PaymentStrategy {
    private final String bankAccount;

    public NetBanking(String bankAccount){
        this.bankAccount = bankAccount;
    }

    public void pay(BigDecimal amount) {
        System.out.println("Paid amount = " + amount+ " - Using Bank account - "+ bankAccount);
    }
}


class ShoppingCart {
    private PaymentStrategy paymentType;


    public void setPaymentType(PaymentStrategy paymentType){
        this.paymentType = paymentType;
    }

    public void checkout(BigDecimal amount){
        paymentType.pay(amount);
    }
}
