public abstract class Ship {
    final protected int size;
    protected boolean isSunk;
    protected int counter;
    public Ship(int size) {
        this.size = size;
        this.isSunk = false;
        this.counter = 0;
    }
    public int getSize() {
        return this.size;
    }
    public void increaseCounter() {
        this.counter++;
        if (this.counter == this.size) {
            this.isSunk = true;
        }
    }
    public boolean getIsSunk() {
        return this.isSunk;
    }
    
}
