package world;

public class Tile {
	private Coordinate coord;
	private String type;
	private int id;
	private int hValue;
	private int gValue;
	private int fValue;
	private Tile parent;

	public Tile(Coordinate coord, String type, int id) {
		this.coord = new Coordinate(coord.getRow(), coord.getCol());
		this.type = type;
		this.id = id;
		this.hValue = 0;
		this.gValue = 0;
		this.fValue = 0;
	}
	
	public Tile(Coordinate coord, int id) {
		this.coord = new Coordinate(coord.getRow(), coord.getCol());
		this.id = id;
		this.hValue = 0;
		this.gValue = 0;
		this.fValue = 0;
	}

	public Coordinate getCoord() {
		return coord;
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
	
	public int getRow() {
		return coord.getRow();
	}
	
	public int getCol() {
		return coord.getCol();
	}
	
	public int getHValue() {
		return hValue;
	}

	public void setHValue(int hValue) {
		this.hValue = hValue;
	}

	public int getGValue() {
		return gValue;
	}

	public void setGValue(int gValue) {
		this.gValue = gValue;
	}

	public int getFValue() {
		return fValue;
	}

	public void setFValue(int fValue) {
		this.fValue = fValue;
	}
	
	public Tile getParent() {
		return parent;
	}
	
	public void setParent(Tile parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "[coord=" + coord + ", type=" + type + ", id=" + id + "]";
	}

}