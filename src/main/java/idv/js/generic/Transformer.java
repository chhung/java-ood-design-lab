package idv.js.generic;

public interface Transformer<F, T> {
  T transform(F from);
}
