import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;1
        
    }

    public double getPrice() {
        return price;
    }
}

class User {
    private String name;
    private double balance;
    private List<Stock> portfolio;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.portfolio = new ArrayList<>();
    }

    public List<Stock> getPortfolio() {
        return portfolio;
    }

    public void buyStock(Stock stock) {
        if (balance >= stock.getPrice()) {
            portfolio.add(stock);
            balance -= stock.getPrice();
            System.out.println("Bought: " + stock.getSymbol());
        } else {
            System.out.println("Insufficient balance to buy " + stock.getSymbol());
        }
    }

    public void sellStock(Stock stock) {
        if (portfolio.contains(stock)) {
            portfolio.remove(stock);
            balance += stock.getPrice();
            System.out.println("Sold: " + stock.getSymbol());
        } else {
            System.out.println("You don't own " + stock.getSymbol());
        }
    }

    public void displayPortfolio() {
        System.out.println("Portfolio:");
        for (Stock stock : portfolio) {
            System.out.println(stock.getSymbol() + " - $" + stock.getPrice());
        }
        System.out.println("Balance: $" + balance);
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User("Alice", 1000.00);
        List<Stock> market = new ArrayList<>();
        market.add(new Stock("AAPL", 150.00));
        market.add(new Stock("GOOGL", 2800.00));
        market.add(new Stock("AMZN", 3400.00));

        while (true) {
            System.out.println("\n1. Buy Stock\n2. Sell Stock\n3. View Portfolio\n4. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: // Buy
                    System.out.println("Enter stock symbol to buy:");
                    String buySymbol = scanner.next();
                    for (Stock stock : market) {
                        if (stock.getSymbol().equalsIgnoreCase(buySymbol)) {
                            user.buyStock(stock);
                        }
                    }
                    break;

                case 2: // Sell
                    System.out.println("Enter stock symbol to sell:");
                    String sellSymbol = scanner.next();
                    for (Stock stock : user.getPortfolio()) {
                        if (stock.getSymbol().equalsIgnoreCase(sellSymbol)) {
                            user.sellStock(stock);
                            break;
                        }
                    }
                    break;

                case 3: // Portfolio
                    user.displayPortfolio();
                    break;

                case 4: // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
