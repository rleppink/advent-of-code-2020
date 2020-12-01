package helpers;

import java.util.function.Supplier;

public class Utils {
  /**
   * @param <T> The type of result that is produced by the given function `f`
   * @param f A function that produces a result when called
   * @param prefix A prefix to print in front of the result for identification
   */
  public static <T> void printResultWithExecutionTime(Supplier<T> f, String prefix) {
    long startTime = System.nanoTime();
    T result = f.get();
    long endTime = System.nanoTime();
    
    System.out.println(prefix + result);
    System.out.println("\t...in " + ((endTime - startTime) / (float)1000000) + "ms\n");
  }
}
