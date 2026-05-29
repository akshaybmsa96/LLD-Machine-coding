package Patterns.behavioral;

import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {
    public static void main(String[] args) {

        Stocks hdfcNSE = new Stocks("HDFC");

        hdfcNSE.attach(new WhatsappObserver());
        hdfcNSE.attach(new DisplayObserver());

        hdfcNSE.setStockPrice(200.12);
        hdfcNSE.setStockPrice(220.21);

        Stocks appleNASDAQ = new Stocks("AAPL");

        appleNASDAQ.attach(new WhatsappObserver());
        appleNASDAQ.attach(new DisplayObserver());

        appleNASDAQ.setStockPrice(1200.12);
        appleNASDAQ.setStockPrice(2220.21);


    }
}

/**
 * Observer lets objects subscribe to events and get notified when something happens.
 * Use it when changes in one object need to trigger updates in other objects.
 */

interface Observer {
    void update(String symbol, Double price);
}

interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObserver();
}

class WhatsappObserver implements Observer{
    public void update(String symbol, Double price) {
        System.out.println("Notified on whatsapp, Stock: " + symbol + " Price: "+ price);

    }
}

class DisplayObserver implements Observer{
    public void update(String symbol, Double price) {
        System.out.println("Notified on Display, Stock: " + symbol + " Price: "+ price);

    }
}

class Stocks implements Subject{

    private final List<Observer> observerList = new ArrayList<>();
    private final String stockName;
    private Double stockPrice;


    public Stocks(String stockName) {
        this.stockName = stockName;
    }

    public void setStockPrice(Double stockPrice) {
        this.stockPrice = stockPrice;
        notifyObserver();
    }

    @Override
    public void attach(Observer observer) {
        observerList.add(observer);

    }

    @Override
    public void detach(Observer observer) {
        observerList.remove(observer);

    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observerList) {
            observer.update(stockName, stockPrice);
        }

    }
}
