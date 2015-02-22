package world;

public class Player {
	private int startRow; // player's starting row position
	private int startCol; // player's starting column position
	private String name; // player's name
	private int score; // player's score ==> length of his path to home

	public Player(int startRow, int startCol, String name) {
		this.startRow = startRow;
		this.startCol = startCol;
		this.name = name;
		this.score = 0;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Player [startRow=" + startRow + ", startCol=" + startCol
				+ ", name=" + name + ", score=" + score + "]";
	}
	
	
}