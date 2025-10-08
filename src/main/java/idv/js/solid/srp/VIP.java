package idv.js.solid.srp;

// 貴賓用戶新增權益
/* 同時,這個類別也符合 LSP 原則, 因為 VIP 類別可以替代 User 類別使用, 而不會破壞程式的正確性
* 這是因為 VIP 類別繼承自 User 類別, 並且沒有改變 User 類別的「行為」
* 例如, 如果有一個方法接受 User 類別的參數, 那麼我們也可以傳入 VIP 類別的物件, 而不會影響方法的行為
* 這樣的設計讓系統更具彈性, 也更容易維護
*/
public class VIP extends User {
  public VIP() {
    this.status = "VIP";
  }

  @Override
  public double getDiscount() {
    return 0.2; // 20% 折扣
  }
}
