package md;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.Set;
import org.junit.Test;

public class Day04Test {

  private Day04 day04 = new Day04();

  @Test
  public void shouldCountCorrectly() {

    final var cards =
        List.of(new Card(Set.of(41, 48, 83, 86, 17), List.of(83, 86, 6, 31, 17, 9, 48, 53)));

    final var result = day04.part1(cards);
    assertThat("found " + result, result == 8);
  }

  @Test
  public void shouldComputeCopies() {
    final var cards =
        List.of(
            new Card(Set.of(41, 48, 83, 86, 17), List.of(83, 86, 6, 31, 17, 9, 48, 53)),
            new Card(Set.of(13, 32, 20, 16, 61), List.of(61, 30, 68, 82, 17, 32, 24, 19)),
            new Card(Set.of(1, 21, 53, 59, 44), List.of(69, 82, 63, 72, 16, 21, 14, 1)),
            new Card(Set.of(41, 92, 73, 84, 69), List.of(59, 84, 76, 51, 58, 5, 54, 83)),
            new Card(Set.of(87, 83, 26, 28, 32), List.of(88, 30, 70, 12, 93, 22, 82, 36)),
            new Card(Set.of(31, 18, 13, 56, 72), List.of(74, 77, 10, 23, 35, 67, 36, 11)));

    final var result = day04.part2(cards);
    assertThat("found " + result, result == 30);
  }
}
