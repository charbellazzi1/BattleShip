public class AI extends Player {

  public AI() {
    super("AI");
  }

  public void placeShips() throws AlreadyTakenSquareException {
    double ran = Math.floor(Math.random() * 2);
    if (ran == 0) {

      this.getGrid().getSquareAt('A', 1).setShip(super.getShips()[0]);
      this.getGrid().getSquareAt('A', 2).setShip(super.getShips()[0]);
      this.getGrid().getSquareAt('A', 3).setShip(super.getShips()[0]);
      this.getGrid().getSquareAt('A', 4).setShip(super.getShips()[0]);
      this.getGrid().getSquareAt('A', 5).setShip(super.getShips()[0]);

      this.getGrid().getSquareAt('B', 1).setShip(super.getShips()[1]);
      this.getGrid().getSquareAt('B', 2).setShip(super.getShips()[1]);
      this.getGrid().getSquareAt('B', 3).setShip(super.getShips()[1]);
      this.getGrid().getSquareAt('B', 4).setShip(super.getShips()[1]);

      this.getGrid().getSquareAt('C', 1).setShip(super.getShips()[2]);
      this.getGrid().getSquareAt('C', 2).setShip(super.getShips()[2]);
      this.getGrid().getSquareAt('C', 3).setShip(super.getShips()[2]);

      this.getGrid().getSquareAt('D', 1).setShip(super.getShips()[3]);
      this.getGrid().getSquareAt('D', 2).setShip(super.getShips()[3]);
      this.getGrid().getSquareAt('D', 3).setShip(super.getShips()[3]);

      this.getGrid().getSquareAt('E', 1).setShip(super.getShips()[4]);
      this.getGrid().getSquareAt('E', 2).setShip(super.getShips()[4]);
    } else {
      this.getGrid().getSquareAt('A', 1).setShip(super.getShips()[0]);
      this.getGrid().getSquareAt('B', 1).setShip(super.getShips()[0]);
      this.getGrid().getSquareAt('C', 1).setShip(super.getShips()[0]);
      this.getGrid().getSquareAt('D', 1).setShip(super.getShips()[0]);
      this.getGrid().getSquareAt('E', 1).setShip(super.getShips()[0]);

      this.getGrid().getSquareAt('A', 2).setShip(super.getShips()[1]);
      this.getGrid().getSquareAt('B', 2).setShip(super.getShips()[1]);
      this.getGrid().getSquareAt('C', 2).setShip(super.getShips()[1]);
      this.getGrid().getSquareAt('D', 2).setShip(super.getShips()[1]);

      this.getGrid().getSquareAt('A', 3).setShip(super.getShips()[2]);
      this.getGrid().getSquareAt('B', 3).setShip(super.getShips()[2]);
      this.getGrid().getSquareAt('C', 3).setShip(super.getShips()[2]);

      this.getGrid().getSquareAt('A', 4).setShip(super.getShips()[3]);
      this.getGrid().getSquareAt('B', 4).setShip(super.getShips()[3]);
      this.getGrid().getSquareAt('C', 4).setShip(super.getShips()[3]);

      this.getGrid().getSquareAt('A', 5).setShip(super.getShips()[4]);
      this.getGrid().getSquareAt('B', 5).setShip(super.getShips()[4]);
    }
    
  }

  public Square guess() {
    try {
      int nb = (int) (Math.random() * 10);
      char letter = (char) ((int) (Math.random() * 10) + 65);
      Square guess = super.getGrid().getSquareAt(letter, nb);
      if (guess.isGuessed()) {
        return this.guess();
      }
       guess.setGuessed(true);
      return guess;
    } catch (IndexOutOfBoundsException e) {
      return this.guess();
    }
  }
}
