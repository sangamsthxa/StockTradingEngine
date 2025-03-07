
public class Order {
    private OrderType orderType; //Buy or Sell
    private int tickerSymbol;
    private int quantity;
    private float price;
    private boolean active; // lock-free synchronization

    //Default Constructor
    public Order() {
    }

    //Parameterized Constructor
    public Order(OrderType orderType, int tickerSymbol, int quantity, float price) {
        this.orderType = orderType;
        this.tickerSymbol = tickerSymbol;
        this.quantity = quantity;
        this.price = price;
        this.active = true;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public int getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(int tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderType=" + orderType +
                ", tickerSymbol=" + tickerSymbol +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
