package md;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Day03Test {

  private Day03 day03 = new Day03();

  @Test
  public void shouldAddUpParts() {
    final char[][] matrix = {
      "467..114..".toCharArray(),
      "...*......".toCharArray(),
      "..35..633.".toCharArray(),
      "......#...".toCharArray(),
      "617*......".toCharArray(),
      ".....+.58.".toCharArray(),
      "..592.....".toCharArray(),
      "......755.".toCharArray(),
      "...$.*....".toCharArray(),
      ".664.598..".toCharArray()
    };

    final var result = day03.part1AndPrintPart2(matrix);
    assertThat("found " + result, result == 4361);
  }

  @Test
  public void shouldAddUpPartsWithNumbersAtRowEnd() {
    final char[][] matrix = {
      "467..114..".toCharArray(),
      "...*......".toCharArray(),
      "..35...633".toCharArray(),
      ".......#..".toCharArray(),
      "617*......".toCharArray(),
      ".....+.58.".toCharArray(),
      "..592.....".toCharArray(),
      "......755.".toCharArray(),
      "...$.*....".toCharArray(),
      ".664.598..".toCharArray()
    };

    final var result = day03.part1AndPrintPart2(matrix);
    assertThat("found " + result, result == 4361);
  }
}
