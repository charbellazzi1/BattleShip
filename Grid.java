public class Grid {

  private Square[][] grid = new Square[10][10];

  public Grid() {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        this.grid[i][j] = new Square((char) (j + 65),i + 1 );
      }
    }
  }

  public Square getSquareAt(char letter, int nb) {
    try {
      return this.grid[nb - 1][(int) letter - 65];
    } catch (IndexOutOfBoundsException e) {
      throw new IndexOutOfBoundsException("Out of bounds: " + letter + nb);
    }
  }

  public void printGrid() {
    System.out.print(" ");
    for (int i = 0; i < 10; i++) {
      System.out.print("   " + (char) (i + 65));
    }
    System.out.println();
    for (int i = 0; i < 10; i++) {
      System.out.println("  -----------------------------------------");
      if (i + 1 < 10) System.out.print(i + 1 + " |"); else System.out.print(
        i + 1 + "|"
      );
      for (int j = 0; j < 10; j++) {
        System.out.print(" " + this.grid[i][j].toString() + " |");
      }
      System.out.println();
    }
  }
}
