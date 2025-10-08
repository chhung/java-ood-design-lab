package idv.js.solid.dip;

/*
 * 依賴反轉原則 (Dependency Inversion Principle, DIP)
 * 高層模組不應該依賴低層模組, 兩者都應該依賴抽象
 * 抽象不應該依賴細節, 細節應該依賴抽象
 * 這裡定義了一個抽象的資料存取介面, 讓高層模組 (例如服務層) 可以依賴這個介面, 而不是具體的資料存取實作
 * 這樣的設計讓系統更具彈性, 也更容易維護
 */
public interface DataAccess {
  void save(Object object);
  Object findById(String id);
}
