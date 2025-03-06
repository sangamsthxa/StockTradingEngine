import java.nio.file.FileSystemNotFoundException;

public class StockTradingEngine {

    private static final int MAX_TICKERS = 1024;
    private static final int MAX_ORDERS_PER_TICKER = 1000;

    //order books for each ticker
    private static final Order[][] buyOrders = new Order[MAX_TICKERS][MAX_ORDERS_PER_TICKER];
    private static final Order[][] sellOrders = new Order[MAX_TICKERS][MAX_ORDERS_PER_TICKER];

    // counters to track the next available slot in order books
    private static final int[] buyIndex = new int[MAX_TICKERS];
    private static final int[] sellIndex = new int[MAX_TICKERS];

    public static synchronized void addOrder(OrderType orderType,int tickerSymbol, int quantity, float price) {
        if(tickerSymbol < 0 || tickerSymbol >= MAX_TICKERS) {
            return;
        }

        Order order = new Order(orderType, tickerSymbol, quantity, price);
        if(order.getOrderType() == OrderType.Buy){
            int idx=buyIndex[tickerSymbol]++;
            if(idx < MAX_ORDERS_PER_TICKER) {
                buyOrders[tickerSymbol][idx] = order;
            }
        }
        else{
            int idx=sellIndex[tickerSymbol]++;
            if(idx < MAX_ORDERS_PER_TICKER) {
                sellOrders[tickerSymbol][idx] = order;
            }
        }

    }

    public static synchronized void matchOrder(int tickerSymbol) {
        int buyCount = buyIndex[tickerSymbol];
        int sellCount = sellIndex[tickerSymbol];
        for(int i = 0; i < buyCount; i++) {
            Order buyOrder = buyOrders[tickerSymbol][i];
            if(buyOrder == null || !buyOrder.isActive() )
                continue;
            for(int j = 0; j < sellCount; j++) {
                Order sellOrder = sellOrders[tickerSymbol][j];
                if(sellOrder == null || !sellOrder.isActive() || !buyOrder.isActive() )
                    continue;
                if(buyOrder.getPrice() >= sellOrder.getPrice()) {
                    int quantity = Math.min(buyOrder.getQuantity(),sellOrder.getQuantity());

                    System.out.println("Match Found for Ticker " + tickerSymbol + ": Buy " + quantity + " @ $" + sellOrder.getPrice());
                    buyOrder.setQuantity(buyOrder.getQuantity()-quantity);
                    sellOrder.setQuantity(sellOrder.getQuantity()-quantity);
                    if(buyOrder.getQuantity() == 0) {
                        buyOrder.setActive(false);
                    }
                    if(sellOrder.getQuantity() == 0) {
                        sellOrder.setActive(false);
                    }

                }
            }
        }

    }




}
