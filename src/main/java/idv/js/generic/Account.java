package idv.js.generic;

import java.util.List;

public class Account {
  protected double balance;

  public Account() {
    this.balance = 0.0;
  }

  public Account(double initialBalance) {
    this.balance = initialBalance;
  }

  public double getBalance() {
    return balance;
  }

  /**
   * 存入金額
   * @param amount 存入的金額（必須大於 0）
   * @throws IllegalArgumentException 如果金額小於或等於 0
   */
  public void deposit(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("存入金額必須大於 0");
    }
    balance += amount;
  }
}

class SavingsAccount extends Account {
  public SavingsAccount() {
    super(1000.0); // 初始餘額為 1000
  }

  public SavingsAccount(double initialBalance) {
    super(initialBalance);
  }
}

class LoanAccount extends Account {
  public LoanAccount() {
    super(-5000.0); // 初始餘額為 -5000（負債）
  }

  public LoanAccount(double initialBalance) {
    super(initialBalance);
  }
}