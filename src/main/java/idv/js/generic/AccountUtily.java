package idv.js.generic;

import java.util.List;

public class AccountUtily {

  public static double sumBalances(List<? extends Account> accounts) {
    double sum = 0.0;
    for (Account account : accounts) {
      sum += account.getBalance();
    }
    return sum;
  }

  public static void addSavingAccount(List<? super SavingsAccount> list, SavingsAccount account) {
    list.add(account);
  }
}
