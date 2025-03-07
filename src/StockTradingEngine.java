

public class StockTradingEngine {

    private static final int MAX_TICKERS = 1024;
    private static final int MAX_ORDERS_PER_TICKER = 1000;

    //order books for each ticker
    private static final Order[][] buyOrders = new Order[MAX_TICKERS][MAX_ORDERS_PER_TICKER];
    private static final Order[][] sellOrders = new Order[MAX_TICKERS][MAX_ORDERS_PER_TICKER];

    // counters to track the next available slot in order books
    private static final int[] buyIndex = new int[MAX_TICKERS];
    private static final int[] sellIndex = new int[MAX_TICKERS];

    //add buy order and sell order to their respective 2-D array
    public static synchronized void addOrder(OrderType orderType,int tickerSymbol, int quantity, float price) {
        //checks the valid boundaries for the ticker symbol
        if(tickerSymbol < 0 || tickerSymbol >= MAX_TICKERS) {
            return;
        }

        Order order = new Order(orderType, tickerSymbol, quantity, price);
        // for buy order
        if(order.getOrderType() == OrderType.Buy){
            int idx=buyIndex[tickerSymbol]++;
            if(idx < MAX_ORDERS_PER_TICKER) {
                buyOrders[tickerSymbol][idx] = order;
            }
        }
        //for sell order
        else{
            int idx=sellIndex[tickerSymbol]++;
            if(idx < MAX_ORDERS_PER_TICKER) {
                sellOrders[tickerSymbol][idx] = order;
            }
        }
        System.out.println("Order Added Successfully"+ order.toString());

    }

    //to match buy order and sell order
    public static synchronized void matchOrder(int tickerSymbol) {
        //gets buy count for the given tickerSymbol
        int buyCount = buyIndex[tickerSymbol];
        //gets sell count for the given tickerSymbol
        int sellCount = sellIndex[tickerSymbol];
        for(int i = 0; i < buyCount; i++) {
            Order buyOrder = buyOrders[tickerSymbol][i];
            //checks for active and valid buy order
            if(buyOrder == null || !buyOrder.isActive() )
                continue;
            for(int j = 0; j < sellCount; j++) {
                Order sellOrder = sellOrders[tickerSymbol][j];
                //checks for active and valid sell and buy order
                if(sellOrder == null || !sellOrder.isActive() || !buyOrder.isActive() )
                    continue;

                //compares the price of the buy and sell order
                if(buyOrder.getPrice() >= sellOrder.getPrice()) {
                    int quantity = Math.min(buyOrder.getQuantity(),sellOrder.getQuantity());
                    System.out.println("Match Found for Ticker " + tickerSymbol + ": Buy " + quantity + " @ $" + sellOrder.getPrice());
                    buyOrder.setQuantity(buyOrder.getQuantity()-quantity);
                    sellOrder.setQuantity(sellOrder.getQuantity()-quantity);

                    if(buyOrder.getQuantity() == 0) {
                        //if quantity of order is zero then set active to false
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
