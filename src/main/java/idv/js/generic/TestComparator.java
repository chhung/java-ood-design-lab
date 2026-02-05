package idv.js.generic;

import java.util.Comparator;

public class TestComparator {
  public static void main(String[] args) {
    System.out.println("開始測試...");

    User user1 = new User("Alice", 30, UserRole.GUEST, 5000.0);
    User user2 = new User("Bob", 25, UserRole.ADMIN, 3000.0);
    User user3 = new User("Charlie", 35, UserRole.USER, 8000.0);

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
