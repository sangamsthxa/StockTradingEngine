import java.util.Random;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to the Stock Trading Engine");
        System.out.println("-------------------------------------");
        StockTradingEngine stockTradingEngine = new StockTradingEngine();

        // Threads to add orders
        Thread addOrdersThread = new Thread(() -> {
            for (int i = 0; i <= 1000; i++) {

                OrderType orderType = randomEnum(OrderType.class);
                int tickerSymbol = i % 10;
                int quantity = i % 50 + 1;
                float price = i % 1.5f;
                stockTradingEngine.addOrder(orderType, tickerSymbol, quantity, price);

            }
        });

        // Threads to match orders
        Thread matchOrdersThread = new Thread(() -> {
            while (true) {
                for (int j = 0; j <= 1000; j++) {
                    int tickerSymbol = j % 10;
                    stockTradingEngine.matchOrder(tickerSymbol);
                }
                try {
                    Thread.sleep(10); // Sleep to reduce CPU usage
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // Start both threads
        addOrdersThread.start();
        matchOrdersThread.start();

        // Wait for add orders thread to finish
        try {
            addOrdersThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop matching thread
        matchOrdersThread.interrupt();

    }


    //to generate random order type
    public static <T extends Enum<T>> T randomEnum(Class<T> enumClass) {
        T[] enumConstants = enumClass.getEnumConstants();
        if (enumConstants == null) {
            throw new IllegalArgumentException(enumClass.getSimpleName() + " is not an enum type.");
        }
        int randomIndex = new Random().nextInt(enumConstants.length);
        return enumConstants[randomIndex];
    }
}