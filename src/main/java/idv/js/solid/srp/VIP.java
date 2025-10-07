package idv.js.solid.srp;

// 貴賓用戶新增權益
public class VIP extends User {
  public VIP() {
    this.status = "VIP";
  }

  @Override
  public double getDiscount() {
    return 0.2; // 20% 折扣
  }
}
