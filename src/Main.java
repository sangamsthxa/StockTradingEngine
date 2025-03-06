import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Welcome to the Stock Trading Engine");
        StockTradingEngine stockTradingEngine = new StockTradingEngine();
        for (int i = 0; i <= 1000; i++) {

            OrderType orderType = randomEnum(OrderType.class);
            int tickerSymbol = i%10;
            int quantity = i % 50 +1;
            float price = i % 1.5f;
            stockTradingEngine.addOrder(orderType, tickerSymbol, quantity, price);

            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.

        }

        for (int j = 0; j <= 1000; j++) {
            int tickerSymbol = j%10;
            stockTradingEngine.matchOrder(tickerSymbol);

            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.

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