package idv.js.solid.isp;

/*
 * 介面隔離原則 (Interface Segregation Principle, ISP)
 * 客戶端不應該被迫依賴它們不使用的方法
 * 這裡將會員的不同權益拆分成不同的介面, 讓不同類型的會員只需實作它們需要的介面
 * 例如, RegularMember 只需實作 Member 介面, 而 PremiumMember 可以實作更多的介面來獲得額外的權益
 * 這樣的設計讓系統更具彈性, 也更容易維護
 */
public interface Member {
  double getDiscount();
  String getName();
  String getEmail();
}
