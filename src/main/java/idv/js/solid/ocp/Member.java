package idv.js.solid.ocp;

import idv.js.solid.srp.User;

/*
 * 具體的會員類別
 * 當需要新增會員類型時, 只需新增繼承 AbstractUser 的子類別
 * 例如新增一個 PremiumMember 類別, 只需繼承 AbstractUser 並實作相關方法
 * 這樣就符合 OCP 原則, 因為我們沒有修改 AbstractUser 類別, 而是透過繼承來擴展功能
 * 這樣的設計讓系統更具彈性, 也更容易維護
 */
public class Member extends AbstractUser {
  public Member(User user) {
    super(user);
  }

}
