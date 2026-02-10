package idv.js.currency;

import java.util.concurrent.Callable;

/**
 * 計算文字中的單詞數量
 * 使用 Callable 介面，可以在多執行緒環境中使用
 */
public class CalWords implements Callable<Integer> {
  private final String text;

  public CalWords(String text) {
    this.text = text;
  }

  /**
   * 計算文字中的單詞數量
   * @return 單詞數量
   */
  @Override
  public Integer call() throws Exception {
    if (text == null || text.trim().isEmpty()) {
      return 0;
    }
    // 使用正則表達式分割，處理多個空白字元
    String[] words = text.trim().split("\\s+");
    return words.length;
  }

  /**
   * 直接計算單詞數量（不需要使用 Callable）
   * @param text 要計算的文字
   * @return 單詞數量
   */
  public static int countWords(String text) {
    if (text == null || text.trim().isEmpty()) {
      return 0;
    }
    String[] words = text.trim().split("\\s+");
    return words.length;
  }

  public String getText() {
    return text;
  }
}
