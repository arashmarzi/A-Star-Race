
public class Simulation {
	public static void main(String[] args) {
		String [][] maze1 = { 
				{"e", "e", "e", "o", "e", "e", "e"},
				{"e", "o", "e", "e", "o", "e", "e"},
				{"e", "o", "e", "e", "o", "e", "e"},
				{"e", "o", "o", "h", "o", "e", "e"},
				{"e", "e", "o", "e", "e", "e", "e"},
				{"e", "e", "o", "o", "o", "e", "e"},
				{"e", "e", "e", "e", "e", "e", "e"},
		};
		
		Player player1 = new Player(0,0,"player1");
		AStarAlgo aStarAlgo = new AStarAlgo();
		aStarAlgo.start(maze1, player1);
		
		
	}
}
