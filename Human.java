import java.util.Scanner;

public class Human extends Player {

  private Scanner Input = new Scanner(System.in);

  public Human(String user) {
    super(user);
  }

  public void placeShips() throws AlreadyTakenSquareException {
    try{
      for (int i = 0; i < 5; i++) {
        Ship currentShip =super.getShips()[i];
        System.out.println(
          "Place your " +
          currentShip.getClass().getName() +
          " (size=" +
          currentShip.getSize() +
          ")"
        );
        System.out.println(
          "Enter where you want to place it like the following format (A1)"
        );
        String place = Input.next();
        char letter = checkLetterInBounds(place);
  
        //handling wrong letter input
  
        letter = Character.toUpperCase(letter);
  
        //handling wrong nb input
  
        int nb = checkNbInBounds(place);
        System.out.println(
          "Enter the direction you want to place the ship in (N,S,E,W)"
        );
        char direction = Input.next().charAt(0);
        direction = Character.toUpperCase(direction);
  
        //handling wrong direction input
  
        while (
          direction != 'N' &&
          direction != 'S' &&
          direction != 'E' &&
          direction != 'W'
        ) {
          System.out.println("Wrong direction, please enter a valid direction");
          direction = Input.next().charAt(0);
          direction = Character.toUpperCase(direction);
        }
        
          placeShipHelper(currentShip, letter, nb, direction);
        } 
  
        System.out.println("Here is your grid");
        super.getGrid().printGrid();
      

    }  catch (IndexOutOfBoundsException a) {
      System.out.println(
        "Wrong placement of the Ship,we will reset the board and you will have to place it again"
      );
      this.reset();
      throw a;
    }catch (AlreadyTakenSquareException a) {
      System.out.println(
        "Already taken square for another Ship, the board will be reset please try to add valid values next time"
      );

      this.reset();
      throw a;
      
    }
    
  }

  private void placeShipHelper2(Ship currentShip, char letter, int nb)
    throws AlreadyTakenSquareException {
    Square a = this.getGrid().getSquareAt(letter, nb);
    if (a.getShip() != null) {
      throw new AlreadyTakenSquareException("Already taken Square");
    } else {
      a.setShip(currentShip);
    }
  }

  private void placeShipHelper(
    Ship currentShip,
    char letter,
    int nb,
    char direction
  ) throws AlreadyTakenSquareException {
    int count = 0;
    while (count != currentShip.getSize()) {
      switch (direction) {
        case 'N':
          try {
            placeShipHelper2(currentShip, letter, nb);
            nb--;
            count++;
          } catch (IndexOutOfBoundsException e) {
            throw e;
          } catch (AlreadyTakenSquareException e) {
            throw e;
          }
          break;
        case 'E':
          try {
            placeShipHelper2(currentShip, letter, nb);
            letter = (char) ((int) letter + 1);

            count++;
          } catch (IndexOutOfBoundsException e) {
            throw e;
          } catch (AlreadyTakenSquareException e) {
            throw e;
          }
          break;
        case 'W':
          try {
            placeShipHelper2(currentShip, letter, nb);
            letter = (char) ((int) letter - 1);
            count++;
          } catch (IndexOutOfBoundsException e) {
            throw e;
          } catch (AlreadyTakenSquareException e) {
            throw e;
          }
          break;
        case 'S':
          try {
            placeShipHelper2(currentShip, letter, nb);
            nb++;
            count++;
          } catch (IndexOutOfBoundsException e) {
            throw e;
          } catch (AlreadyTakenSquareException e) {
            throw e;
          }
          break;
        default:
          System.out.println("something wrong occured!!");
      }
    }
  }

  public Square guess() {
    System.out.println(
      "Enter the square you want to guess like the following format (A1)"
    );
    String place = Input.next();
    char letter = checkLetterInBounds(place);
    int nb = checkNbInBounds(place);
    while (this.getGrid().getSquareAt(letter, nb).isGuessed()) {
      System.out.println(
        "You already guessed this square, please enter a valid input"
      );
      place = Input.next();
      letter = checkLetterInBounds(place);
      nb = checkNbInBounds(place);
    }

    this.getGrid().getSquareAt(letter, nb).setGuessed(true);
    return this.getGrid().getSquareAt(letter, nb);
  }

  public char checkLetterInBounds(String place) {
    char letter = place.charAt(0);
    while (letter > 'J' || letter < 'A') {
      System.out.println("Wrong Letter, please enter a valid letter");
      place = Input.next();
      letter = place.charAt(0);
    }
    return letter;
  }

  public int checkNbInBounds(String place) {
    int nb;
    try {
      nb = Math.abs(Integer.parseInt(place.substring(1)));
      while (nb > 10 || nb < 1) {
        System.out.println("Wrong number, please enter a valid number");
        nb = Input.nextInt();
      }
      return nb;
    }catch(IndexOutOfBoundsException e){
      System.out.println("Wrong number, please enter a valid number");
      nb = Input.nextInt();
      return checkNbInBounds("A" + place);
    }
     catch (NumberFormatException e) {
      System.out.println("Wrong number, please enter a valid number");
      place = Input.next();
    if(place.length()==1){
      return checkNbInBounds("A" + place);
    }else{
      return checkNbInBounds(place);
    }
    }
  }

  public void closeScanner() {
    this.Input.close();
  }
}
