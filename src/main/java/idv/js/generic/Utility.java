package idv.js.generic;

import java.util.Comparator;

/**
 * 工具類別 - 提供比較方法
 * 支援兩種比較方式：
 * 1. 使用 Comparable 介面 (適合單一、固定的比較邏輯)
 * 2. 使用 Comparator 策略 (適合多種、可變的比較邏輯) - 符合 SOLID
 */
public class Utility {

  /**
   * 使用 Comparable 介面的比較方法
   * 適合類別本身已經定義了自然排序 (natural ordering)
   */
  public static <T extends Comparable<T>> T compareAndReturnLarger(T a, T b) {
    if (a.compareTo(b) > 0) {
      return a;
    } else {
      return b;
    }
  }

  /**
   * 使用 Comparator 策略的比較方法
   * 符合 SOLID 原則：
   * - OCP: 不需要修改此方法就能新增比較策略
   * - DIP: 依賴抽象的 Comparator 介面，而非具體實作
   * - SRP: 比較邏輯由外部 Comparator 負責
   */
  public static <T> T compareAndReturnLarger(T a, T b, Comparator<T> comparator) {
    if (comparator.compare(a, b) > 0) {
      return a;
    } else {
      return b;
    }
  }
}
