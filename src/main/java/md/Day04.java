package md;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day04 {

  public static void main(String[] args) throws IOException {
    final var resource = Day04.class.getClassLoader().getResource("day04.txt");
    final var lines =
        Files.readAllLines(Path.of(resource.getPath())).stream()
            .map(
                str -> {
                  var parts = str.split(":");
                  var card = parts[1].split("\\|");
                  var winning =
                      Arrays.stream(card[0].split("\\b"))
                          .map(String::strip)
                          .filter(s -> !s.isEmpty())
                          .map(Integer::parseInt)
                          .collect(Collectors.toSet());
                  var numbers =
                      Arrays.stream(card[1].split("\\b"))
                          .map(String::strip)
                          .filter(s -> !s.isEmpty())
                          .map(Integer::parseInt)
                          .toList();

                  return new Card(winning, numbers);
                })
            .toList();

    final var day04 = new Day04();
    System.out.println(day04.part1(lines));
    System.out.println(day04.part2(lines));
  }

  public int part1(final List<Card> cards) {

    return cards.stream()
        .mapToInt(
            card -> {
              var counter = -1;
              for (var number : card.numbers()) {
                if (card.winning().contains(number)) {
                  counter++;
                }
              }

              return (int) Math.pow(2, counter);
            })
        .reduce(Integer::sum)
        .orElse(-1);
  }

  public int part2(final List<Card> cards) {
    final var cardInstances = new int[cards.size()];

    // populate scratchcards map with the original card
    for (int i = 0; i < cards.size(); i++) cardInstances[i] = 1;
    var counter = cards.size();

    for (int idx = 0; idx < cards.size(); idx++) {
      var card = cards.get(idx);

      // calculate number of matches
      var matches = 0;
      for (var number : card.numbers()) {
        if (card.winning().contains(number)) {
          matches++;
        }
      }

      // increase number of copies of following cards
      for (int i = 1; i <= matches; i++) {
        cardInstances[idx + i] += cardInstances[idx];

        // increase the total counter as well
        counter += cardInstances[idx];
      }
    }

    return counter;
  }
}

record Card(Set<Integer> winning, List<Integer> numbers) {}
