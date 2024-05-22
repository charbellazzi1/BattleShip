import java.lang.Thread;
import java.util.Scanner;

public class GameController {

  private Player player1;
  private Player player2;
  private Player currentPlayer;
  private Scanner Input = new Scanner(System.in);

  public GameController() {
    int choice = 0;
    do {
      System.out.println(
        "do you want to play against a computer or another player? (1 for computer, 2 for player)"
      );
      try{
        choice = Input.nextInt();
      }catch(Exception e){
        while(!Input.hasNextInt()){
          System.out.println("Please enter a number");
          Input.next();
        }
      }
       
      
    } while (choice != 1 && choice != 2);
    if (choice == 1) {
      System.out.println("Enter your name");
      String name = Input.next();
      while (name.length() > 10 || name.length() < 1) {
        if (name.length() < 1) System.out.println(
          "The name is too short, please enter a name with more than 1 character"
        ); else System.out.println(
          "The name is too long, please enter a name with less than 10 characters"
        );
        name = Input.next();
      }
      player1 = new Human(name);
      player2 = new AI();
    } else {
      System.out.println("Enter the name of the first player");
      String name1 = Input.next();
      System.out.println("Enter the name of the second player");
      String name2 = Input.next();
      player1 = new Human(name1);
      player2 = new Human(name2);
    }
  }

  public void nextTurn() {
    if (currentPlayer == player1) {
      currentPlayer = player2;
    } else {
      currentPlayer = player1;
    }
  }

  public void startGame()
    throws AlreadyTakenSquareException, IndexOutOfBoundsException {
      try{

      
    System.out.println("Welcome to Battleship");
    Thread.sleep(500);
    System.out.println("Here is the grid for " + player1.getUsername());
    player1.getGrid().printGrid();
    System.out.println(
      "now " + player1.getUsername() + " will place his ships"
    );
    try{
      player1.placeShips();
    }catch(AlreadyTakenSquareException e){
    player1.placeShips();
    }catch(IndexOutOfBoundsException e){
      player1.placeShips();
    }
    
      System.out.println(
        "The other player will now place his ships, please wait 4 seconds"
      );
      Thread.sleep(4000);
    
    System.out.println(
      "Now " + player2.getUsername() + " will place his ships"
    );
    System.out.println("Here is the grid for " + player2.getUsername());
    player2.getGrid().printGrid();
    player2.placeShips();
    System.out.println("The ships have been placed");

    int num = (int) (Math.random() * 2 + 1);
    if (num == 1) {
      currentPlayer = player1;
    } else {
      currentPlayer = player2;
    }
    System.out.println("The game will now start");
      Thread.sleep(1000);
    
    System.out.println("The first player to sink all the ships wins");
      Thread.sleep(1000);
    
    
    while (!player1.hasLost() && !player2.hasLost()) {
      System.out.println("It's " + currentPlayer.getUsername() + "'s turn");
      if (currentPlayer instanceof AI) {
        System.out.println("The computer is thinking...");
        currentPlayer.getGrid().printGrid();
      } else {
        System.out.println(
          "Here is your grid, The square hitted in the other grid will be marked with a shady square "
        );
        Thread.sleep(500);
        currentPlayer.getGrid().printGrid();
        Thread.sleep(500);
      }

      Square guess = currentPlayer.guess();
      hit(guess);
      nextTurn();
      try {
        Thread.sleep(1500);
      } catch (InterruptedException e) {
        System.out.println("Error");
      }
    }
    if (player1.hasLost()) {
      System.out.println("The winner is " + player2.getUsername());
    } else {
      System.out.println("The winner is " + player1.getUsername());
    }
    System.out.println("Thank you for playing");
    player1.reset();
    player2.reset();
    currentPlayer = null;
    System.out.println("Press 1 to restart the game or any key to quit");
    int choice = Input.nextInt();
    if (choice == 1) {
      startGame();
    }
  }catch(InterruptedException e){
    System.out.println("The thread was interrupted");
}
    }

  public void hit(Square guess) {
    Square a;
    if (currentPlayer == player1) {
       a= player2.getGrid().getSquareAt(guess.getLetter(), guess.getNb());
    } else {
      a = player1.getGrid().getSquareAt(guess.getLetter(), guess.getNb());
    }
    if (a.getShip() != null) {
      a.getShip().increaseCounter();
      System.out.println("You hit a ship");
      if (a.getShip().getIsSunk()) {
        System.out.println("You sunk a ship");
      }
    } else {
      System.out.println("You missed");
    }
  }
}
