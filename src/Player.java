public class Player {
	private int startRow; // player's starting row position 
	private int startCol; // player's starting column position
	private String name;
	
	private int numVertSqurs;
	private int numHorzSqurs;
	private int numObstSqurs;
	
	
	public Player(int startRow, int startCol, String name) {
		this.startRow = startRow;
		this.startCol = startCol;
		this.name = name;
		this.numVertSqurs = 0;
		this.numHorzSqurs = 0;
		this.numObstSqurs = 0;
		
	}

	public int getStartX() {
		return startRow;
	}

	public int getStartY() {
		return startCol;
	}

	public String getName() {
		return name;
	}
	
	

	
}
