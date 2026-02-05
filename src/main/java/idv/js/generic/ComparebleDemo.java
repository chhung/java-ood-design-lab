package idv.js.generic;

import java.util.Comparator;

/**
 * 使用策略模式 (Strategy Pattern) 來實現多種比較方式
 * 符合 SOLID 原則：
 * - SRP: User 類別只負責儲存資料，比較邏輯由獨立的 Comparator 負責
 * - OCP: 新增比較方式時不需要修改 User 類別，只需新增 Comparator
 * - DIP: 依賴抽象的 Comparator 介面，而非具體實作
 */
public class ComparebleDemo {
  public static void main(String[] args) {

    User user1 = new User("Alice", 30, UserRole.GUEST, 5000.0);
    User user2 = new User("Bob", 25, UserRole.ADMIN, 3000.0);
    User user3 = new User("Charlie", 35, UserRole.USER, 8000.0);

    // 使用不同的比較策略
    System.out.println("=== 比較 Role ===");
    User higherRoleUser = Utility.compareAndReturnLarger(user1, user2, new UserRoleComparator());
    System.out.println("User with higher role: " + higherRoleUser);

    System.out.println("\n=== 比較購買金額 ===");
    User higherPurchaseUser = Utility.compareAndReturnLarger(user1, user3, new UserPurchaseAmountComparator());
    System.out.println("User with higher purchase amount: " + higherPurchaseUser);

    System.out.println("\n=== 比較年齡 ===");
    User olderUser = Utility.compareAndReturnLarger(user1, user3, new UserAgeComparator());
    System.out.println("Older user: " + olderUser);

  }
}

/**
 * User 類別 - 只負責儲存資料 (符合 SRP)
 * 不再實作 Comparable，避免綁定單一比較邏輯
 */
class User {
  private String name;
  private int age;
  private UserRole role;
  private double purchaseAmount; // 購買金額

  public User(String name, int age, UserRole role, double purchaseAmount) {
    this.name = name;
    this.age = age;
    this.role = role;
    this.purchaseAmount = purchaseAmount;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public UserRole getRole() {
    return role;
  }

  public double getPurchaseAmount() {
    return purchaseAmount;
  }

  @Override
  public String toString() {
    return "User{name='" + name + "', age=" + age + ", role=" + role + ", purchaseAmount=" + purchaseAmount + "}";
  }
}

/**
 * 比較策略 1: 依照 Role 比較
 */
class UserRoleComparator implements Comparator<User> {
  @Override
  public int compare(User u1, User u2) {
    return u1.getRole().ordinal() - u2.getRole().ordinal();
  }
}

/**
 * 比較策略 2: 依照購買金額比較
 */
class UserPurchaseAmountComparator implements Comparator<User> {
  @Override
  public int compare(User u1, User u2) {
    return Double.compare(u1.getPurchaseAmount(), u2.getPurchaseAmount());
  }
}

/**
 * 比較策略 3: 依照年齡比較
 */
class UserAgeComparator implements Comparator<User> {
  @Override
  public int compare(User u1, User u2) {
    return Integer.compare(u1.getAge(), u2.getAge());
  }
}

enum UserRole {
  GUEST,
  USER,
  ADMIN
}
