public class Square {

  private char letter;
  private int nb;
  private Ship ship;
  private boolean guessed;

  public Square(char letter, int nb) {
    this.letter = letter;
    this.nb = nb;
    this.ship = null;
    this.guessed = false;
  }

  public char getLetter() {
    return this.letter;
  }

  public int getNb() {
    return this.nb;
  }

  public Ship getShip() {
    if (this.ship == null) {
      return null;
    }
    return this.ship;
  }

  public void setShip(Ship ship) throws AlreadyTakenSquareException {
    if (this.getShip() == null) this.ship = ship; else {
      throw new AlreadyTakenSquareException("This Square is Already taken");
    }
  }

  public String toString() {
    String value = "*";
    if (this.ship != null) {
      switch (this.ship.getClass().getName()) {
        case "Aircraft":
          value = "A";
          break;
        case "Battleship":
          value = "B";
          break;
        case "Cruiser":
          value = "C";
          break;
        case "Submarine":
          value = "S";
          break;
        case "Destroyer":
          value = "D";
          break;
      }
    }
    if (this.guessed) value = "\033[1;30m" + value + "\033[0m";

    return value;
  }

  public void setGuessed(boolean a) {
    this.guessed = a;
  }

  public boolean isGuessed() {
    return this.guessed;
  }
}
