package idv.js.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Result 類別使用範例
 * 展示如何使用通用數據包裝器來包裝不同類型的 API 回傳結果
 *
 * <h2>執行步驟：</h2>
 * <h3>方法 1: 使用命令列 (從專案根目錄)</h3>
 * <pre>
 * # Windows PowerShell
 * cd C:\Data\code\gotogoogle\java-ood-design-lab\src\main\java
 * javac idv/js/generic/Result.java idv/js/generic/ResultDemo.java
 * java idv.js.generic.ResultDemo
 * </pre>
 *
 * <h3>方法 2: 使用命令列 (從 src/main/java 目錄)</h3>
 * <pre>
 * # 編譯
 * javac idv/js/generic/Result.java idv/js/generic/ResultDemo.java
 *
 * # 執行
 * java idv.js.generic.ResultDemo
 * </pre>
 *
 * <h3>方法 3: 使用 IntelliJ IDEA</h3>
 * <pre>
 * 1. 在編輯器中打開 ResultDemo.java
 * 2. 找到 main 方法
 * 3. 點擊行號旁邊的綠色執行按鈕 (▶)
 * 4. 選擇 "Run 'ResultDemo.main()'"
 * 5. 查看輸出視窗的執行結果
 * </pre>
 *
 * <h3>方法 4: 使用 Maven (如果專案有配置)</h3>
 * <pre>
 * cd C:\Data\code\gotogoogle\java-ood-design-lab
 * mvn clean compile
 * mvn exec:java -Dexec.mainClass="idv.js.generic.ResultDemo"
 * </pre>
 */
public class ResultDemo {

  // 模擬 User 類別
  static class User {
    private String name;
    private int age;

    public User(String name, int age) {
      this.name = name;
      this.age = age;
    }

    @Override
    public String toString() {
      return "User{name='" + name + "', age=" + age + "}";
    }
  }

  // 模擬 Product 類別
  static class Product {
    private String productName;
    private double price;

    public Product(String productName, double price) {
      this.productName = productName;
      this.price = price;
    }

    @Override
    public String toString() {
      return "Product{name='" + productName + "', price=" + price + "}";
    }
  }

  public static void main(String[] args) {
    System.out.println("=== 通用數據包裝器 (Generic Wrapper) 範例 ===\n");

    // 範例 1: 包裝 User 物件
    User user = new User("張三", 25);
    Result<User> userResult = new Result<>("查詢用戶成功", user);
    System.out.println("範例 1 - 用戶 API");
    System.out.println("訊息: " + userResult.getMessage());
    System.out.println("資料: " + userResult.getData());
    System.out.println();

    // 範例 2: 包裝 Product 物件
    Product product = new Product("筆記型電腦", 35000.0);
    Result<Product> productResult = new Result<>("取得商品資訊成功", product);
    System.out.println("範例 2 - 商品 API");
    System.out.println("訊息: " + productResult.getMessage());
    System.out.println("資料: " + productResult.getData());
    System.out.println();

    // 範例 3: 包裝 List 集合
    List<String> orders = new ArrayList<>();
    orders.add("訂單001");
    orders.add("訂單002");
    orders.add("訂單003");
    Result<List<String>> ordersResult = new Result<>("訂單列表查詢成功", orders);
    System.out.println("範例 3 - 訂單列表 API");
    System.out.println("訊息: " + ordersResult.getMessage());
    System.out.println("資料: " + ordersResult.getData());
    System.out.println();

    // 範例 4: 包裝簡單類型 (Integer)
    Result<Integer> countResult = new Result<>("商品總數查詢成功", 150);
    System.out.println("範例 4 - 統計 API");
    System.out.println("訊息: " + countResult.getMessage());
    System.out.println("資料: " + countResult.getData());
    System.out.println();

    // 範例 5: 包裝錯誤情況 (data 為 null)
    Result<User> errorResult = new Result<>("用戶不存在", null);
    System.out.println("範例 5 - 錯誤情況");
    System.out.println("訊息: " + errorResult.getMessage());
    System.out.println("資料: " + errorResult.getData());
    System.out.println();

    // 範例 6: 模擬實際 API 方法
    Result<User> apiResponse1 = getUserById("123");
    System.out.println("範例 6 - API 方法回傳");
    System.out.println("訊息: " + apiResponse1.getMessage());
    System.out.println("資料: " + apiResponse1.getData());
    System.out.println();

    Result<User> apiResponse2 = getUserById("999");
    System.out.println("範例 7 - API 方法回傳 (找不到)");
    System.out.println("訊息: " + apiResponse2.getMessage());
    System.out.println("資料: " + apiResponse2.getData());
  }

  /**
   * 模擬 API 方法：根據 ID 查詢用戶
   * 回傳統一的 Result 格式
   */
  public static Result<User> getUserById(String id) {
    if ("123".equals(id)) {
      User user = new User("李四", 30);
      return new Result<>("查詢成功", user);
    } else {
      return new Result<>("用戶不存在", null);
    }
  }
}
