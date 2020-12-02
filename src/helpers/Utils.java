package helpers;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class Utils {
  /**
   * Execute and time the given function f, print its result, and the execution
   * time.
   * 
   * @param <T> The type of result that is produced by the given function `f`
   * @param f A function that produces a result when called
   * @param prefix A prefix to print in front of the result for identification
   */
  public static <T> T printResultWithExecutionTime(Supplier<T> f, String prefix) {
    long startTime = System.nanoTime();
    T result = f.get();
    long endTime = System.nanoTime();
    
    System.out.println(prefix + result);
    System.out.println("\t...in " + ((endTime - startTime) / (float)1000000) + "ms\n");
    
    return result;
  }
  
  /**
   * Fold the given list of T's with the accumulator function for TRes. 
   * 
   * Why is this not in the standard library.
   * 
   * NOTE: This is probably faster in a loop? I don't think Java does tail
   * recursion? Might kill the stack on a big list.
   * 
   * @param <T> The type of list items
   * @param <TRes> The result type
   * @param cs The list of items to fold
   * @param acc The accumulating result
   * @param f The accumulator function
   * @return The accumulated TRes result
   */
  public static <T, TRes> TRes reduceRec(List<T> cs, TRes acc, BiFunction<T, TRes, TRes> f) {
    if (cs.isEmpty())
      return acc;

    return reduceRec(
        cs.subList(1, cs.size()),
        f.apply(cs.get(0), acc),
        f);
  }
  /**
   * Fold the given list of T's with the accumulator function for TRes. 
   * Loop version. Should be better.
   * 
   * Why is this not in the standard library.
   * 
   * @param <T> The type of list items
   * @param <TRes> The result type
   * @param cs The list of items to fold
   * @param acc The accumulating result
   * @param f The accumulator function
   * @return The accumulated TRes result
   */
  public static <T, TRes> TRes reduce(List<T> cs, TRes acc, BiFunction<T, TRes, TRes> f) {
    for (T c : cs) {
      acc = f.apply(c, acc);
    }
    
    return acc;
  }

  /**
   * Count the amount of chars c in string s.
   * 
   * @param s The string to count chars in
   * @param c The char to count
   * @return The amount of times the char c was found in string s
   */
  public static int countChars(String s, char c) {
    int count = 0;
    for (int i = 0; i < s.length(); i++)
      if (s.charAt(i) == c) 
        count += 1;
    
    return count;
  }
}
