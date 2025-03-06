import java.util.Random;

public class DummyMain {

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Welcome to the Stock Trading Engine");
        Random random = new Random();
        StockTradingEngine stockTradingEngine = new StockTradingEngine();
        for (int i = 0; i < 1024; i++) {

            OrderType orderType1 = OrderType.Buy;
            int tickerSymbol = i;
            int quantity = i % 100 +1;
            float price = i % 5f;
            stockTradingEngine.addOrder(orderType1, tickerSymbol, quantity, price);
            OrderType orderType2 = OrderType.Sell;
            int tickerSymbol1 = i;
            int quantity1 = i % quantity +1;
            float price1 = i % 4f+1;
            stockTradingEngine.addOrder(orderType2, tickerSymbol1, quantity1, price1);


            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.

        }

        for (int j = 0; j < 1024; j++) {
            int tickerSymbol = j;
            stockTradingEngine.matchOrder(tickerSymbol);
        }
    }

    public static <T extends Enum<T>> T randomEnum(Class<T> enumClass) {
        T[] enumConstants = enumClass.getEnumConstants();
        if (enumConstants == null) {
            throw new IllegalArgumentException(enumClass.getSimpleName() + " is not an enum type.");
        }
        int randomIndex = new Random().nextInt(enumConstants.length);
        return enumConstants[randomIndex];
    }
}
