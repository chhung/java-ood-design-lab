package idv.js.generic;

import java.util.ArrayList;
import java.util.List;

public class WordCountTransformer implements Transformer<String, Integer> {
  @Override
  public Integer transform(String from) {
    if (from == null || from.isEmpty()) {
      return 0;
    }
    String[] words = from.trim().split("\\s+");
    return words.length;
  }

  public static <F, T> List<T> batchTransform(List<F> inputList, Transformer<? super F, T> transformer) {
    List<T> output = new ArrayList<T>();

    for (F input : inputList) {
      output.add(transformer.transform(input));
    }

    return output;
  }

  public static void main(String[] args) {
    List<String> sentences = List.of(
      "Hello world",
      "Java generics are powerful",
      "Transformer pattern example",
      "",
      "   Leading and trailing spaces   "
    );

    Transformer<String, Integer> wordCountTransformer = new WordCountTransformer();
    List<Integer> wordCounts = batchTransform(sentences, wordCountTransformer);

    System.out.println("句子: " + sentences);
    System.out.println("單詞數量: " + wordCounts);
  }
}
