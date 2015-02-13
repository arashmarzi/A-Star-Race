package world;

public class Tile {
	private Coordinate coord;
	private Tile up;
	private Tile down;
	private Tile left;
	private Tile right;
	private String type;

	public Tile(int x, int y, String type) {
		this.coord = new Coordinate(x, y);
		this.type = type;
	}

	public Coordinate getCoord() {
		return coord;
	}

	public Tile getUp() {
		return up;
	}

	public Tile getDown() {
		return down;
	}

	public Tile getLeft() {
		return left;
	}

	public Tile getRight() {
		return right;
	}
	
	public void setUp(Tile up) {
		this.up = up;
	}

	public void setDown(Tile down) {
		this.down = down;
	}

	public void setLeft(Tile left) {
		this.left = left;
	}

	public void setRight(Tile right) {
		this.right = right;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "[coord=" + coord + ", type=" + type + "]";
	}

}