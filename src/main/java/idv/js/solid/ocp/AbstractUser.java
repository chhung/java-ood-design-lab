package idv.js.solid.ocp;

import idv.js.solid.srp.User;

// 基礎用戶抽象化
/*
* OCP 原則: 開放擴展, 封閉修改
* 當我們需要新增用戶類型時, 不需要修改 AbstractUser 類別, 只需新增繼承 AbstractUser 的子類別
* 例如新增一個 Admin 類別, 只需繼承 AbstractUser 並實作相關方法
* 這樣就符合 OCP 原則, 因為我們沒有修改 AbstractUser 類別, 而是透過繼承來擴展功能
* 這樣的設計讓系統更具彈性, 也更容易維護
*
 */
abstract class AbstractUser {
  // 使用 final 確保 user 不會被重新指派, 這是個習慣
  private final User user;

  public AbstractUser(User user) {
    this.user = user;
  }

  public void setUser(User user) {
    // 這裡不允許更改 user 參考, 因為這樣會違反 OCP 原則
    throw new UnsupportedOperationException("Cannot change user reference");
  }

  public String getName() {
    return user.getName();
  }

  public String getEmail() {
    return user.getEmail();
  }

  public String getStatus() {
    return user.getStatus();
  }

  public double getDiscount() {
    return user.getDiscount();
  }
}
