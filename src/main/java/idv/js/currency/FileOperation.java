package idv.js.currency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FileOperation {
  private final String filePath;

  public FileOperation(String filePath) {
    this.filePath = filePath;
  }

  /**
   * 開啟並讀取檔案內容
   * @return 檔案的所有行內容
   * @throws IOException 當檔案無法讀取時拋出異常
   */
  public List<String> openFile() throws IOException {
    List<String> lines = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }
    }

    return lines;
  }

  /**
   * 計算檔案中的總單詞數量
   * @return 總單詞數量
   * @throws IOException 當檔案無法讀取時拋出異常
   */
  public int countWordsInFile() throws IOException {
    List<String> lines = openFile();
    int totalWords = 0;

    for (String line : lines) {
      totalWords += CalWords.countWords(line);
    }

    return totalWords;
  }

  /**
   * 使用多執行緒計算檔案中的總單詞數量
   * 逐行讀取並立即提交給執行緒池，避免大檔案造成記憶體問題
   * @return 總單詞數量
   * @throws IOException 當檔案無法讀取時拋出異常
   * @throws ExecutionException 當執行緒執行失敗時拋出異常
   * @throws InterruptedException 當執行緒被中斷時拋出異常
   */
  public int countWordsInFileWithExecutor() throws IOException, ExecutionException, InterruptedException {
    ExecutorService executor = Executors.newFixedThreadPool(4);
    List<Future<Integer>> futures = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      // 逐行讀取，每讀一行就立即提交給執行緒處理
      while ((line = reader.readLine()) != null) {
        CalWords task = new CalWords(line);
        Future<Integer> future = executor.submit(task);
        futures.add(future);
      }

      // 收集所有結果
      int totalWords = 0;
      for (Future<Integer> future : futures) {
        totalWords += future.get();
      }

      return totalWords;
    } finally {
      executor.shutdown();
    }
  }

  /**
   * 使用多執行緒批次計算檔案中的總單詞數量
   * 逐行讀取，批次提交並收集結果，避免 futures List 過大
   * @param batchSize 批次大小，每處理這麼多行就收集一次結果
   * @return 總單詞數量
   * @throws IOException 當檔案無法讀取時拋出異常
   * @throws ExecutionException 當執行緒執行失敗時拋出異常
   * @throws InterruptedException 當執行緒被中斷時拋出異常
   */
  public int countWordsInFileWithBatch(int batchSize) throws IOException, ExecutionException, InterruptedException {
    ExecutorService executor = Executors.newFixedThreadPool(4);
    List<Future<Integer>> futures = new ArrayList<>();
    int totalWords = 0;

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      int count = 0;

      // 逐行讀取
      while ((line = reader.readLine()) != null) {
        CalWords task = new CalWords(line);
        Future<Integer> future = executor.submit(task);
        futures.add(future);
        count++;

        // 達到批次大小時，收集結果並清空 futures
        if (count >= batchSize) {
          for (Future<Integer> future1 : futures) {
            totalWords += future1.get();
          }
          futures.clear();
          count = 0;
        }
      }

      // 處理剩餘的任務
      for (Future<Integer> future : futures) {
        totalWords += future.get();
      }

      return totalWords;
    } finally {
      executor.shutdown();
    }
  }

  public static void main(String[] args) {
    String filePath = "C:/Data/tmp/acc.txt"; // 替換為實際檔案路徑
    FileOperation fileOperation = new FileOperation(filePath);

    try {
      // 讀取檔案內容
      List<String> fileContents = fileOperation.openFile();
      System.out.println("檔案內容:");
      for (String line : fileContents) {
        System.out.println(line);
      }

      System.out.println("\n--- 單詞計數 ---");

      // 計算單詞數量（單執行緒）
      int wordCount = fileOperation.countWordsInFile();
      System.out.println("總單詞數量（單執行緒）: " + wordCount);

      // 計算單詞數量（多執行緒，逐行處理）
      int wordCountMultiThread = fileOperation.countWordsInFileWithExecutor();
      System.out.println("總單詞數量（多執行緒）: " + wordCountMultiThread);

      // 計算單詞數量（多執行緒，批次處理，適合超大檔案）
      int wordCountBatch = fileOperation.countWordsInFileWithBatch(1000);
      System.out.println("總單詞數量（批次處理）: " + wordCountBatch);

    } catch (IOException e) {
      System.err.println("無法讀取檔案: " + e.getMessage());
    } catch (ExecutionException | InterruptedException e) {
      System.err.println("執行緒執行失敗: " + e.getMessage());
    }
  }


}
