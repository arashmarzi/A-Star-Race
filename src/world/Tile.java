package world;

public class Tile {
	private Coordinate coord;
	private Tile up;
	private Tile down;
	private Tile left;
	private Tile right;
	private String type;
	private int id;
	private int hValue;
	private int gValue;
	private int fValue;

	public Tile(Coordinate coord, String type, int id) {
		this.coord = new Coordinate(coord.getX(), coord.getY());
		this.type = type;
		this.id = id;
		this.hValue = 0;
		this.gValue = 0;
		this.fValue = 0;
	}
	
	public Tile(Coordinate coord, int id) {
		this.coord = new Coordinate(coord.getX(), coord.getY());
		this.id = id;
		this.hValue = 0;
		this.gValue = 0;
		this.fValue = 0;
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

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "[coord=" + coord + ", type=" + type + ", id=" + id + "]";
	}

}