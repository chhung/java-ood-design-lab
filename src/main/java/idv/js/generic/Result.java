package idv.js.generic;

/**
 * 通用數據包裝器 (Generic Wrapper)
 * 用於包裝 API 回傳結果，提供統一的回傳格式
 * 包含訊息 (message) 和資料 (data)
 * 資料的型別可以根據不同的 API 而改變
 *
 * @param <T> 資料的型別
 */
public class Result<T> {
  private final String message;
  private final T data;

  /**
   * 建構子
   *
   * @param message 訊息
   * @param data    資料
   */
  public Result(String message, T data) {
    this.message = message;
    this.data = data;
  }

  /**
   * 取得訊息
   *
   * @return 訊息
   */
  public String getMessage() {
    return message;
  }

  /**
   * 取得資料
   *
   * @return 資料
   */
  public T getData() {
    return data;
  }
}
