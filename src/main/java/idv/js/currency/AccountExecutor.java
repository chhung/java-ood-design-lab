package idv.js.currency;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountExecutor {

  public void executeTask() {
    // 建立一個單執行緒的執行器
    ExecutorService executor = Executors.newSingleThreadExecutor();

    // 提交任務執行
    executor.submit(() -> {
      System.out.println("test");
    });

    // 關閉執行器
    executor.shutdown();
  }

  public static void main(String[] args) {
    //AccountExecutor accountExecutor = new AccountExecutor();
    //accountExecutor.executeTask();

    Runnable task = () -> {
      for (int i = 0; i < 7; i++) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "=> " + i);
      }
    };

    ExecutorService pool = Executors.newFixedThreadPool(3);
    for  (int i = 0; i < 6; i++) {
      pool.submit(task);

    }
    pool.shutdown();


    /*DirectExecutor directExecutor = new DirectExecutor();
    directExecutor.execute(() -> {
      for (int i = 0; i < 7; i++) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("test");
      }
    });*/

    for (int i = 0; i < 5; i++) {
      try {
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }
}
