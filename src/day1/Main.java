package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import helpers.Utils;

public class Main {
  public static void main(String[] args) throws IOException {
    Integer[] inputs = 
      Files
        .lines(Path.of("inputs/day1"))
        .map(l -> Integer.parseInt(l))
        .toArray(Integer[]::new);
    
    Utils.printResultWithExecutionTime(() -> Part1(inputs), "Part 1; ");
    Utils.printResultWithExecutionTime(() -> Part2(inputs), "Part 2; ");
  }

  /**
   * It's not pretty but it still runs in less than a millisecond on my machine,
   * so no need for optimization.
   */
  public static Optional<Integer> Part1(Integer[] inputs) {
    for (int i = 0; i < inputs.length; i++)
      for (int j = i; j < inputs.length; j++)
        if (inputs[i] + inputs[j] == 2020)
          return Optional.of(inputs[i] * inputs[j]);
    
    return Optional.empty();
  }

  /**
   * It's not pretty but it still runs in 8ms on my machine, so no need for
   * optimization.
   */
  public static Optional<Integer> Part2(Integer[] inputs) {
    for (int i = 0; i < inputs.length; i++)
      for (int j = i; j < inputs.length; j++)
        for (int k = j; k < inputs.length; k++)
          if (inputs[i] + inputs[j] + inputs[k] == 2020)
            return Optional.of(inputs[i] * inputs[j] * inputs[k]);
            
    return Optional.empty();
  }
}