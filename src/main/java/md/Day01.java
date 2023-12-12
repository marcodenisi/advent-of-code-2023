package md;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day01 {

  private static final Map<String, Integer> NUMBERS;

  static {
    NUMBERS = new HashMap<>();
    NUMBERS.put("one", 1);
    NUMBERS.put("two", 2);
    NUMBERS.put("three", 3);
    NUMBERS.put("four", 4);
    NUMBERS.put("five", 5);
    NUMBERS.put("six", 6);
    NUMBERS.put("seven", 7);
    NUMBERS.put("eight", 8);
    NUMBERS.put("nine", 9);
  }

  public static void main(String[] args) throws IOException {
    final var resource = Day01.class.getClassLoader().getResource("day01.txt");
    final var lines = Files.readAllLines(Path.of(resource.getPath()));

    final var day01 = new Day01();
    System.out.println(day01.part1(lines));
    System.out.println(day01.part2(lines));
  }

  public int part1(final List<String> lines) {

    return lines.stream()
        .map(
            line -> {
              var initialChar = ' ';
              var finalChar = ' ';
              for (char c : line.toCharArray()) {
                if (Character.isDigit(c)) {
                  if (initialChar == ' ') initialChar = c;
                  finalChar = c;
                }
              }
              var sb = new StringBuilder(2);
              return Integer.valueOf(sb.append(initialChar).append(finalChar).toString());
            })
        .reduce(Integer::sum)
        .orElse(-1);
  }

  public int part2(final List<String> lines) {

    return lines.stream()
        .map(
            line -> {
              final var sb = new StringBuilder();
              for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (Character.isDigit(c)) {
                  sb.append(c);
                } else {
                  for (var entry : NUMBERS.entrySet()) {
                    if (line.indexOf(entry.getKey(), i) == i) sb.append(entry.getValue());
                  }
                }
              }
              return sb.toString();
            })
        .mapToInt(s -> Integer.parseInt(s.charAt(0) + String.valueOf(s.charAt(s.length() - 1))))
        .reduce(Integer::sum)
        .orElse(-1);
  }
}
