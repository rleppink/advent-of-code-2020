package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import helpers.Utils;

public class Main {

  public static void main(String[] args) throws IOException {
    List<ParsedLine> inputs = 
      Files
        .lines(Path.of("inputs/day2"))
        .map(Main::parseLine)
        .collect(Collectors.toList());
    
    Utils.printResultWithExecutionTime(() -> Part1(inputs), "Part 1; ");
    Utils.printResultWithExecutionTime(() -> Part2(inputs), "Part 2; ");
  }

  public static Integer Part1(List<ParsedLine> inputs) {
    int result = 0;
    for (ParsedLine cur : inputs) {
      int charCount = Utils.countChars(cur.password, cur.letter);
      if (charCount >= cur.low && charCount <= cur.high)
        result++;
    }
    
    return result;
  }
  
  public static Integer Part2(List<ParsedLine> inputs) {
    int result = 0;
    for (ParsedLine cur : inputs) {
      if (cur.password.charAt(cur.low - 1) == cur.letter ^ 
          cur.password.charAt(cur.high - 1) == cur.letter)
        result++;
    }
    
    return result;
  }
  
  public static ParsedLine parseLine(String line) {
    Pattern p = Pattern.compile(
      "(?<low>\\d+)-(?<high>\\d+) (?<letter>\\w): (?<password>\\w+)");
    Matcher m = p.matcher(line);
    
    if (!m.matches())
      // This would be a programming bug, let me know as soon as possible.
      throw new RuntimeException("Could not parse line: " + line);
    
    return new ParsedLine(
        Integer.parseInt(m.group("low")),
        Integer.parseInt(m.group("high")),
        m.group("letter").charAt(0),
        m.group("password"));
  }
  
  static class ParsedLine {
    public int low;
    public int high;
    public char letter;
    public String password;

    public ParsedLine(int low, int high, char letter, String password) {
      super();
      this.low = low;
      this.high = high;
      this.letter = letter;
      this.password = password;
    }
  }
}
