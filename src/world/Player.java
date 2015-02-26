package world;

public class Player {
	private int startRow; // player's starting row position
	private int startCol; // player's starting column position
	private String name; // player's name
	private int pathLength; // player's score ==> length of his path to goal
	private String path; // path player takes
	
	public Player(int startRow, int startCol, String name) {
		this.startRow = startRow;
		this.startCol = startCol;
		this.name = name;
		this.pathLength = 0;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getStartCol() {
		return startCol;
	}

	public String getName() {
		return name;
	}

	public int getPathLength() {
		return pathLength;
	}

	public void setPathLength(int pathLength) {
		this.pathLength = pathLength;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Player [startRow=" + startRow + ", startCol=" + startCol
				+ ", name=" + name + ", pathLength=" + pathLength + ", path=" + path +"]";
	}	
}