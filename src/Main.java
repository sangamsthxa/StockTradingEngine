import java.util.Random;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to the Stock Trading Engine");
        System.out.println("-------------------------------------");
        StockTradingEngine stockTradingEngine = new StockTradingEngine();
        // Generate 1,024 unique ticker symbols
        String[] tickerSymbols = generateTickerSymbols(1024);
        Thread[] addOrderThreads = new Thread[tickerSymbols.length];
        for (int i = 0; i < 1024; i++) {
            int finalI = i;
            addOrderThreads[i] = new Thread(() -> {
                OrderType orderType = randomEnum(OrderType.class);
                String tickerSymbol = tickerSymbols[finalI % tickerSymbols.length];
                int quantity = finalI % 50 + 1;
                float price = finalI % 1.5f;
                stockTradingEngine.addOrder(orderType, tickerSymbol, quantity, price);
            });
        }

        // Threads to match orders
        Thread matchOrdersThread = new Thread(() -> {
            while (true) {
                for (String tickerSymbol : tickerSymbols) {
                   // int tickerSymbol = j % 10;
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
        matchOrdersThread.start();
        for (Thread thread : addOrderThreads) {
            thread.start();
        }

        // Wait for add orders thread to finish
        try {
            for (Thread thread : addOrderThreads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop matching thread
        matchOrdersThread.interrupt();

    }

    // Generate 1,024 unique ticker symbols like AAA, AAB, AAC, ..., ZZZ
    public static String[] generateTickerSymbols(int count) {
        String[] symbols = new String[count];
        int index = 0;
        for (char i = 'A'; i <= 'Z'; i++) {
            for (char j = 'A'; j <= 'Z'; j++) {
                for (char k = 'A'; k <= 'Z'; k++) {
                    if (index < count) {
                        symbols[index++] = "" + i + j + k;
                    } else {
                        return symbols;
                    }
                }
            }
        }
        return symbols;
    }


    //Generate random order type
    public static <T extends Enum<T>> T randomEnum(Class<T> enumClass) {
        T[] enumConstants = enumClass.getEnumConstants();
        if (enumConstants == null) {
            throw new IllegalArgumentException(enumClass.getSimpleName() + " is not an enum type.");
        }
        int randomIndex = new Random().nextInt(enumConstants.length);
        return enumConstants[randomIndex];
    }


}