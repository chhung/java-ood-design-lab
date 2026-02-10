package idv.js.currency;

import java.util.concurrent.Executor;

public class DirectExecutor implements Executor {
  @Override
  public void execute(Runnable command) {
    command.run();
    /*for (int i = 0; i < 5; i++) {
      //command.run();
      System.out.println("I don't care you !");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }*/
  }
}
