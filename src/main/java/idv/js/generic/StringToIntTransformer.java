package idv.js.generic;

import java.util.ArrayList;
import java.util.List;

public class StringToIntTransformer implements Transformer<String, Integer> {
  @Override
  public Integer transform(String from) {
    try {
      return Integer.parseInt(from);
    } catch (NumberFormatException e) {
      System.out.println("無法將字串轉換為整數: " + from);
      return null;
    }
  }

  public static <F, T> List<T> processTransform(List<F> fromList, Transformer<F, T> transformer) {
    List<T> toList = new ArrayList<>();

    for (F from : fromList) {
      toList.add(transformer.transform(from));
    }
    return toList;
  }

  public static void main(String[] args) {
    List<String> stringList = List.of("10", "20", "30", "abc", "40");
    Transformer<String, Integer> transformer = new StringToIntTransformer();
    List<Integer> intList = processTransform(stringList, transformer);

    System.out.println("轉換結果: " + intList);
  }
}
