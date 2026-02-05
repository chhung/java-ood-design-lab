package idv.js.console;

import idv.js.generic.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 銀行系統互動介面
 * 提供選單讓使用者進行帳戶操作
 */
public class Screen {
  private List<Account> accounts;
  private Scanner scanner;
  private int currentAccountIndex;

  public Screen() {
    this.accounts = new ArrayList<>();
    this.scanner = new Scanner(System.in);
    this.currentAccountIndex = -1;
  }

  /**
   * 啟動銀行系統
   */
  public void start() {
    System.out.println("====================================");
    System.out.println("    歡迎使用銀行管理系統");
    System.out.println("====================================\n");

    boolean running = true;
    while (running) {
      showMenu();
      int choice = getChoice();

      switch (choice) {
        case 1:
          showBalance();
          break;
        case 2:
          createAccount();
          break;
        case 3:
          deposit();
          break;
        case 4:
          withdraw();
          break;
        case 5:
          running = false;
          exit();
          break;
        default:
          System.out.println("❌ 無效的選項，請輸入 1-5\n");
      }
    }
  }

  /**
   * 顯示主選單
   */
  private void showMenu() {
    System.out.println("====================================");
    System.out.println("請選擇功能：");
    System.out.println("1. 顯示帳戶餘額");
    System.out.println("2. 新開帳戶");
    System.out.println("3. 存入金額");
    System.out.println("4. 領出金額");
    System.out.println("5. 退出系統");
    System.out.println("====================================");
    System.out.print("請輸入選項 (1-5): ");
  }

  /**
   * 取得使用者選擇
   */
  private int getChoice() {
    try {
      int choice = scanner.nextInt();
      scanner.nextLine(); // 清除換行符號
      return choice;
    } catch (Exception e) {
      scanner.nextLine(); // 清除錯誤輸入
      return -1;
    }
  }

  /**
   * 1. 顯示帳戶餘額
   */
  private void showBalance() {
    System.out.println("\n--- 顯示帳戶餘額 ---");

    if (accounts.isEmpty()) {
      System.out.println("❌ 目前沒有任何帳戶，請先新開帳戶");
      System.out.println();
      return;
    }

    System.out.println("所有帳戶列表：");
    for (int i = 0; i < accounts.size(); i++) {
      Account account = accounts.get(i);
      String status = (i == currentAccountIndex) ? " [當前]" : "";
      System.out.printf("%d. 帳戶 #%d - 餘額: $%.2f%s\n",
          i + 1, i + 1, account.getBalance(), status);
    }

    double totalBalance = calculateTotalBalance();
    System.out.printf("\n總餘額: $%.2f\n", totalBalance);
    System.out.println();
  }

  /**
   * 2. 新開帳戶
   */
  private void createAccount() {
    System.out.println("\n--- 新開帳戶 ---");
    System.out.print("請輸入初始存款金額 (輸入 0 表示不存款): $");

    try {
      double initialDeposit = scanner.nextDouble();
      scanner.nextLine(); // 清除換行符號

      if (initialDeposit < 0) {
        System.out.println("❌ 初始金額不能為負數");
        System.out.println();
        return;
      }

      Account newAccount = new Account(initialDeposit);
      accounts.add(newAccount);
      currentAccountIndex = accounts.size() - 1;

      System.out.printf("✅ 帳戶 #%d 開戶成功！初始餘額: $%.2f\n",
          accounts.size(), initialDeposit);
      System.out.println();
    } catch (Exception e) {
      scanner.nextLine(); // 清除錯誤輸入
      System.out.println("❌ 輸入格式錯誤，請輸入有效的數字");
      System.out.println();
    }
  }

  /**
   * 3. 存入金額
   */
  private void deposit() {
    System.out.println("\n--- 存入金額 ---");

    if (accounts.isEmpty()) {
      System.out.println("❌ 目前沒有任何帳戶，請先新開帳戶");
      System.out.println();
      return;
    }

    // 選擇帳戶
    int accountIndex = selectAccount();
    if (accountIndex == -1) {
      return;
    }

    // 輸入存款金額
    System.out.print("請輸入存款金額: $");
    try {
      double amount = scanner.nextDouble();
      scanner.nextLine(); // 清除換行符號

      Account account = accounts.get(accountIndex);
      double balanceBefore = account.getBalance();

      account.deposit(amount);

      System.out.printf("✅ 存款成功！\n");
      System.out.printf("   存款前餘額: $%.2f\n", balanceBefore);
      System.out.printf("   存款金額: $%.2f\n", amount);
      System.out.printf("   存款後餘額: $%.2f\n", account.getBalance());
      System.out.println();
    } catch (IllegalArgumentException e) {
      System.out.println("❌ " + e.getMessage());
      System.out.println();
    } catch (Exception e) {
      scanner.nextLine(); // 清除錯誤輸入
      System.out.println("❌ 輸入格式錯誤，請輸入有效的數字");
      System.out.println();
    }
  }

  /**
   * 4. 領出金額
   */
  private void withdraw() {
    System.out.println("\n--- 領出金額 ---");

    if (accounts.isEmpty()) {
      System.out.println("❌ 目前沒有任何帳戶，請先新開帳戶");
      System.out.println();
      return;
    }

    // 選擇帳戶
    int accountIndex = selectAccount();
    if (accountIndex == -1) {
      return;
    }

    // 輸入提款金額
    System.out.print("請輸入提款金額: $");
    try {
      double amount = scanner.nextDouble();
      scanner.nextLine(); // 清除換行符號

      if (amount <= 0) {
        System.out.println("❌ 提款金額必須大於 0");
        System.out.println();
        return;
      }

      Account account = accounts.get(accountIndex);
      double balanceBefore = account.getBalance();

      if (amount > balanceBefore) {
        System.out.printf("❌ 餘額不足！目前餘額: $%.2f\n", balanceBefore);
        System.out.println();
        return;
      }

      account.withdraw(amount);

      System.out.printf("✅ 提款成功！\n");
      System.out.printf("   提款前餘額: $%.2f\n", balanceBefore);
      System.out.printf("   提款金額: $%.2f\n", amount);
      System.out.printf("   提款後餘額: $%.2f\n", account.getBalance());
      System.out.println();
    } catch (Exception e) {
      scanner.nextLine(); // 清除錯誤輸入
      System.out.println("❌ 輸入格式錯誤，請輸入有效的數字");
      System.out.println();
    }
  }

  /**
   * 5. 退出系統
   */
  private void exit() {
    System.out.println("\n====================================");
    System.out.println("感謝使用銀行管理系統，再見！");
    System.out.println("====================================");
    scanner.close();
  }

  /**
   * 選擇帳戶
   * @return 帳戶索引，-1 表示取消
   */
  private int selectAccount() {
    if (accounts.size() == 1) {
      currentAccountIndex = 0;
      return 0;
    }

    System.out.println("請選擇帳戶：");
    for (int i = 0; i < accounts.size(); i++) {
      Account account = accounts.get(i);
      String status = (i == currentAccountIndex) ? " [當前]" : "";
      System.out.printf("%d. 帳戶 #%d - 餘額: $%.2f%s\n",
          i + 1, i + 1, account.getBalance(), status);
    }
    System.out.print("請輸入帳戶編號 (1-" + accounts.size() + "): ");

    try {
      int choice = scanner.nextInt();
      scanner.nextLine(); // 清除換行符號

      if (choice < 1 || choice > accounts.size()) {
        System.out.println("❌ 無效的帳戶編號");
        System.out.println();
        return -1;
      }

      currentAccountIndex = choice - 1;
      return currentAccountIndex;
    } catch (Exception e) {
      scanner.nextLine(); // 清除錯誤輸入
      System.out.println("❌ 輸入格式錯誤");
      System.out.println();
      return -1;
    }
  }

  /**
   * 計算所有帳戶總餘額
   */
  private double calculateTotalBalance() {
    double total = 0.0;
    for (Account account : accounts) {
      total += account.getBalance();
    }
    return total;
  }

  /**
   * 主程式入口
   */
  public static void main(String[] args) {
    Screen screen = new Screen();
    screen.start();
  }
}
