# 📈 Stock Trading Engine

A **high-performance**, **lock-free** stock trading engine implemented in **Java**. This system efficiently handles **concurrent buy and sell orders** using **lock-free synchronization**. Ideal for **real-time trading platforms**.

---

##  **Key Features**

1. **Lock-Free Synchronization:**
   - Uses `synchronized` blocks to manage concurrency control for adding and matching orders.
   - Prevents deadlocks and minimizes context switching.

2. **Efficient Order Matching:**
   - Matches buy and sell orders based on price and availability.
   - Ensures real-time execution with a time complexity of **O(n)**.

3. **Supports High Concurrency:**
   - Designed to handle multiple threads for order placement and matching.
   - Suitable for multi-core systems.

4. **Fixed-Size Arrays:**
   - Uses fixed-size arrays for order books.
   - Avoids dynamic data structures like `HashMap` or `ArrayList` as per requirements.

5. **Real-Time Performance:**
   - Implements non-blocking data access for low-latency performance.

---

## **Project Structure**

```
📦 StockTradingEngine
 ┣ 📜 StockTradingEngine.java       # Core trading engine logic
 ┣ 📜 Order.java                    # Order class definition
 ┣ 📜 OrderType.java                # Enum for order types (Buy/Sell)
 ┣ 📜 Main.java                     # Main class for testing
 ┣ 📜 README.md                     # This file
```

---

## **Installation and Setup**

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/StockTradingEngine.git
   cd StockTradingEngine
   ```

2. **Compile the Java files:**
   ```bash
   javac *.java
   ```

3. **Run the application:**
   ```bash
   java Main
   ```

---

## **Sample Output**

```

![Output](screenshots/output1.png)
![Output](screenshots/output2.png)

```


