package md;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import org.junit.Test;

public class Day01Test {

  private final Day01 day01 = new Day01();

  @Test
  public void shouldAnswerPart1Correctly() {
    final var result = day01.part1(List.of("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet"));

    assertThat("Result should be 142", result == 142);
  }

  @Test
  public void shouldAnswerPart2Correctly() {
    final var result =
        day01.part2(
            List.of(
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen"));

    assertThat("Result should be 281 instead of " + result, result == 281);
  }
}
