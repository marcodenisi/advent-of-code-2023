package md;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiPredicate;

public class Day03 {

  private static final List<Point> MOVES =
      List.of(
          new Point(-1, 0),
          new Point(-1, 1),
          new Point(-1, -1),
          new Point(0, -1),
          new Point(0, 1),
          new Point(1, 0),
          new Point(1, 1),
          new Point(1, -1));

  public static void main(String[] args) throws IOException {
    final var resource = Day03.class.getClassLoader().getResource("day03.txt");
    final var lines =
        Files.readAllLines(Path.of(resource.getPath())).stream()
            .map(String::toCharArray)
            .toList()
            .toArray(new char[0][0]);

    final var day03 = new Day03();
    System.out.println(day03.part1AndPrintPart2(lines));
  }

  public int part1AndPrintPart2(final char[][] matrix) {

    final var inBoundaries =
        (BiPredicate<Integer, Integer>)
            (r, c) -> r >= 0 && r < matrix.length && c >= 0 && c < matrix[r].length;

    var gearSet = new HashSet<String>();
    var asteriskMap = new HashMap<String, Integer>();
    var counter = 0;
    var currentNumber = 0;
    var addNumberToCounter = false;

    // part 2: keep track of potential gears
    Point lastAsteriskSeen = null;

    for (int r = 0; r < matrix.length; r++) {

      // check if we have leftovers (numbers finishing at the end of the line)
      if (currentNumber != 0 && addNumberToCounter) {
        counter += currentNumber;

        // part 2: add the current number to the asterisk map and check if it's a gear
        checkIfThereIsAGear(gearSet, asteriskMap, currentNumber, lastAsteriskSeen);
      }

      currentNumber = 0;
      addNumberToCounter = false;

      // part 2: reset the last seen asterisk, if any
      lastAsteriskSeen = null;

      for (int c = 0; c < matrix[r].length; c++) {
        var currentChar = matrix[r][c];

        if (Character.isDigit(currentChar)) {
          currentNumber = currentNumber * 10 + Integer.parseInt(String.valueOf(matrix[r][c]));
          // we need to check if it has signs around it

          // if we already know that this number should be added, just continue with the matrix scan
          if (addNumberToCounter) continue;

          for (var move : MOVES) {
            var newRow = r + move.row();
            var newCol = c + move.col();
            if (!inBoundaries.test(newRow, newCol)) continue;

            if (!Character.isDigit(matrix[newRow][newCol]) && matrix[newRow][newCol] != '.') {
              addNumberToCounter = true;

              // part 2: check if it's an asterisk
              if (matrix[newRow][newCol] == '*') {
                lastAsteriskSeen = new Point(newRow, newCol);
              }
            }
          }

        } else {
          if (currentNumber != 0) {
            // we found the end of a number, let's see if we should add it to the counter
            if (addNumberToCounter) {
              counter += currentNumber;

              // part 2: add the current number to the asterisk map and check if it's a gear
              checkIfThereIsAGear(gearSet, asteriskMap, currentNumber, lastAsteriskSeen);
            }
            // then finally reset the number and the flag
            currentNumber = 0;
            addNumberToCounter = false;

            // part 2: reset the last seen asterisk, if any
            lastAsteriskSeen = null;
          }
        }
      }
    }

    var gearRatios = 0;
    for (var gear : gearSet) {
      gearRatios += asteriskMap.get(gear);
    }
    System.out.println("Gear Ratios: " + gearRatios);

    return counter;
  }

  private static void checkIfThereIsAGear(HashSet<String> gearSet, HashMap<String, Integer> asteriskMap,
      int currentNumber, Point lastAsteriskSeen) {
    if (lastAsteriskSeen != null) {
      if (asteriskMap.containsKey(lastAsteriskSeen.toString())) {
        gearSet.add(lastAsteriskSeen.toString());
      }
      asteriskMap.put(
          lastAsteriskSeen.toString(),
          asteriskMap.getOrDefault(lastAsteriskSeen.toString(), 1) * currentNumber);
    }
  }
}

record Point(int row, int col) {

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer();
    sb.append("row=").append(row);
    sb.append(", col=").append(col);
    return sb.toString();
  }
}
