
public abstract class Player{
    protected String username;
    protected Ship[] ships=new Ship[5];
    protected Grid grid;
    public Player(String user) {
        this.username=user;
        this.grid=new Grid();
        ships[0]=new Aircraft();
        ships[1]=new Battleship();
        ships[2]=new Cruiser();
        ships[3]=new Submarine();
        ships[4]=new Destroyer();

    }
    public String getUsername() {
        return this.username;
    }
    public Grid getGrid() {
        return this.grid;
    }
   
    public Ship[] getShips() {
        return this.ships;
    }

    public abstract void placeShips() throws AlreadyTakenSquareException;

    public abstract Square guess();

    

    public boolean hasLost() {
        for (int i=0;i<5;i++) {
            if (this.ships[i].getIsSunk()==false) {
                return false;
            }
        }
        return true;
    }


   public void reset(){
         this.grid=new Grid();
            ships[0]=new Aircraft();
            ships[1]=new Battleship();
            ships[2]=new Cruiser();
            ships[3]=new Submarine();
            ships[4]=new Destroyer();
   }


}