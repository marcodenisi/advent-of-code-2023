package md;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day02 {

  private static final int MAX_RED = 12;
  private static final int MAX_GREEN = 13;
  private static final int MAX_BLUE = 14;

  public static void main(String[] args) throws IOException {
    final var resource = Day02.class.getClassLoader().getResource("day02.txt");
    final var lines = Files.readAllLines(Path.of(resource.getPath()));
    final var games = lines.stream().map(Day02::parseGame).toList();

    final var day02 = new Day02();
    System.out.println(day02.part1(games));
    System.out.println(day02.part2(games));
  }

  private static Game parseGame(final String line) {

    final var parts = line.split(":");
    final var gameId = Integer.parseInt(parts[0].split("\\b")[2]);

    final var revealList = new ArrayList<Reveal>();
    final var reveals = parts[1].split(";");
    for (final var reveal : reveals) {
      var red = 0;
      var green = 0;
      var blue = 0;

      final var pieces = reveal.split(",");
      for (final var piece : pieces) {
        if (piece.contains("green")) {
          green = Integer.parseInt(piece.split("\\b")[1]);
        }
        if (piece.contains("red")) {
          red = Integer.parseInt(piece.split("\\b")[1]);
        }
        if (piece.contains("blue")) {
          blue = Integer.parseInt(piece.split("\\b")[1]);
        }
      }

      revealList.add(new Reveal(red, green, blue));
    }

    return new Game(gameId, revealList);
  }

  public int part1(final List<Game> games) {

    return games.stream()
        .filter(
            game ->
                game.reveals().stream()
                    .allMatch(
                        reveal ->
                            reveal.blue() <= MAX_BLUE
                                && reveal.red() <= MAX_RED
                                && reveal.green() <= MAX_GREEN))
        .map(Game::id)
        .reduce(Integer::sum)
        .orElse(-1);
  }

  public int part2(final List<Game> games) {

    return games.stream()
        .mapToInt(
            game -> {
              var maxRed = 0;
              var maxGreen = 0;
              var maxBlue = 0;

              for (Reveal reveal : game.reveals()) {
                if (reveal.green() >= maxGreen) maxGreen = reveal.green();
                if (reveal.blue() >= maxBlue) maxBlue = reveal.blue();
                if (reveal.red() >= maxRed) maxRed = reveal.red();
              }

              return maxRed * maxGreen * maxBlue;
            })
        .reduce(Integer::sum)
        .orElse(-1);
  }
}

record Game(int id, List<Reveal> reveals) {}

record Reveal(int red, int green, int blue) {}
