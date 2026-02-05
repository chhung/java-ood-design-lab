package idv.js.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Account 類別使用範例
 * 展示如何使用 deposit 方法存入金額
 */
public class AccountDemo {
  public static void main(String[] args) {
    System.out.println("=== Account 存款示範 ===\n");

    // 1. 建立普通帳戶
    Account account = new Account();
    System.out.println("1. 普通帳戶");
    System.out.println("初始餘額: " + account.getBalance());

    // 存入金額
    account.deposit(500.0);
    System.out.println("存入 500 後的餘額: " + account.getBalance());

    account.deposit(300.0);
    System.out.println("再存入 300 後的餘額: " + account.getBalance());
    System.out.println();

    // 2. 建立儲蓄帳戶
    SavingsAccount savings = new SavingsAccount();
    System.out.println("2. 儲蓄帳戶");
    System.out.println("初始餘額: " + savings.getBalance());

    savings.deposit(2000.0);
    System.out.println("存入 2000 後的餘額: " + savings.getBalance());
    System.out.println();

    // 3. 建立貸款帳戶（負債）
    LoanAccount loan = new LoanAccount();
    System.out.println("3. 貸款帳戶");
    System.out.println("初始餘額: " + loan.getBalance() + " (負債)");

    loan.deposit(1000.0);
    System.out.println("還款 1000 後的餘額: " + loan.getBalance());

    loan.deposit(2000.0);
    System.out.println("再還款 2000 後的餘額: " + loan.getBalance());
    System.out.println();

    // 4. 測試異常情況
    System.out.println("4. 測試錯誤情況");
    try {
      account.deposit(0);
    } catch (IllegalArgumentException e) {
      System.out.println("錯誤: " + e.getMessage());
    }

    try {
      account.deposit(-100);
    } catch (IllegalArgumentException e) {
      System.out.println("錯誤: " + e.getMessage());
    }
    System.out.println();

    // 5. 使用 sumBalances 計算總餘額
    System.out.println("5. 計算所有帳戶總餘額");
    List<Account> accounts = new ArrayList<>();
    accounts.add(account);      // 餘額: 800
    accounts.add(savings);      // 餘額: 3000
    accounts.add(loan);         // 餘額: -2000

    double totalBalance = AccountUtily.sumBalances(accounts);
    System.out.println("所有帳戶總餘額: " + totalBalance);
    System.out.println();

    // 6. 使用萬用字元 (Wildcard) - 只計算儲蓄帳戶
    System.out.println("6. 只計算儲蓄帳戶總餘額");
    List<SavingsAccount> savingsAccounts = new ArrayList<>();
    savingsAccounts.add(new SavingsAccount(2000.0));
    savingsAccounts.add(new SavingsAccount(3000.0));
    savingsAccounts.add(new SavingsAccount(5000.0));

    double savingsTotal = AccountUtily.sumBalances(savingsAccounts);
    System.out.println("所有儲蓄帳戶總餘額: " + savingsTotal);

    // 加入特定帳戶到 List 中
    List<Object> savingsAccounts2 = new ArrayList<>();
    AccountUtily.addSavingAccount(savingsAccounts2, new SavingsAccount(4000.0));
    //System.out.println("加入一個新的儲蓄帳戶後的總餘額: " + AccountUtily.sumBalances(savingsAccounts2));

  }
}
