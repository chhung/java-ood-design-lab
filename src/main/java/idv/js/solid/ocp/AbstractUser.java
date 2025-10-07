package idv.js.solid.ocp;

import idv.js.solid.srp.User;

// 基礎用戶抽象化
abstract class AbstractUser {
  // 使用 final 確保 user 不會被重新指派, 這是個習慣
  private final User user;

  public AbstractUser(User user) {
    this.user = user;
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
