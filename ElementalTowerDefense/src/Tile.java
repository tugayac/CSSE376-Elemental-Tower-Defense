
public class Tile {
	private boolean move;
	
	public Tile(boolean b) {
		this.move = b;
	}

	public boolean getMove() {
		return move;
	}

	public boolean getPlace() {
		return !move;
	}

}
