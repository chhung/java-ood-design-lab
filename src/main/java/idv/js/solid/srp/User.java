package idv.js.solid.srp;

// 用戶自身相關功能，如獲取用戶信息
public class User {
  protected String status; // 預設為普通用戶

  public User() {
    this.status = "regular";
  }

  public String getStatus() {
    return status;
  }

  public String getName() {
    return "John Doe";
  }

  public String getEmail() {
    return "john.doe@mail.com";
  }

  public double getDiscount() {
    return 0.0; // 普通用戶無折扣
  }
}
