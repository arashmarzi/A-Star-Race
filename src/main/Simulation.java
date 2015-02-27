package main;
import java.util.ArrayList;
import java.util.List;

import algorithm.AStarAlgo;
import world.Coordinate;
import world.Maze;
import world.Player;
import world.Tile;


public class Simulation {
	public static void main(String[] args) {		
		Maze maze1 = createMaze1();
		Maze maze2 = createMaze1();
		Maze maze3 = createMaze1();
		Maze maze4 = createMaze1();
		Maze maze5 = createMaze2();
		Maze maze6 = createMaze2();
		Maze maze7 = createMaze2();
		Maze maze8 = createMaze2();
		
		Player player1 = new Player(0, 0, "player1");
		Player player2 = new Player(0, 6, "player2");
		Player player3 = new Player(6, 0, "player3");
		Player player4 = new Player(6, 6, "player4");
		Player player5 = new Player(0, 0, "player5");
		Player player6 = new Player(0, 8, "player6");
		Player player7 = new Player(8, 0, "player7");
		Player player8 = new Player(8, 8, "player8");
		
		AStarAlgo aStarAlgo1 = new AStarAlgo();
		AStarAlgo aStarAlgo2 = new AStarAlgo();
		AStarAlgo aStarAlgo3 = new AStarAlgo();
		AStarAlgo aStarAlgo4 = new AStarAlgo();
		AStarAlgo aStarAlgo5 = new AStarAlgo();
		AStarAlgo aStarAlgo6 = new AStarAlgo();
		AStarAlgo aStarAlgo7 = new AStarAlgo();
		AStarAlgo aStarAlgo8 = new AStarAlgo();
		
		System.out.println("MAZE-1:\n" + maze1.toString());
		
		/*START RACE FOR PLAYERS IN MAZE-1*/
		
		System.out.println("Starting first race; on your mark, get set, go!");
		
		// start player1
		runRace(aStarAlgo1, player1, maze1);

		// start player2
		runRace(aStarAlgo2, player2, maze2);

		// start player3
		runRace(aStarAlgo3, player3, maze3);
		
		// start player4
		runRace(aStarAlgo4, player4, maze4);
		
		// display winner(s) info
		displayWinner(player1, player2, player3, player4);
		
		/*START RACE FOR PLAYERS IN MAZE-2*/
		
		System.out.println("\nMAZE-2:\n" + maze5.toString());
		
		System.out.println("\nStarting second race; on your mark, get set, go!");
		
		// start player1
		runRace(aStarAlgo5, player5, maze5);

		// start player2
		runRace(aStarAlgo6, player6, maze6);

		// start player3
		runRace(aStarAlgo7, player7, maze7);
		
		// start player4
		runRace(aStarAlgo8, player8, maze8);
		
		// display winner(s) info
		displayWinner(player5, player6, player7, player8);
	}
	
	public static void runRace(AStarAlgo aStarAlgo, Player player, Maze maze){
		// start algorithm for player
		aStarAlgo.start(maze, player, maze.getTiles().get(player.getStartRow()).get(player.getStartCol()), maze.getTiles().get(3).get(3));
		player.setPath(aStarAlgo.getPath());
		player.setPathLength(aStarAlgo.getPathLength());
		System.out.println(player.getName() + " path length=" + player.getPathLength() + " path=" + player.getPath() + "\n");
	}
	
	// determine the winner(s) and print results
	public static void displayWinner(Player a, Player b, Player c, Player d){
		Player winner = a;
		ArrayList<Player> tiedPlayers = new ArrayList<Player>();
		
		// perform comparison of players, looking for player with lowest path length;
		// account for ties, but clear list of ties if new lowest path is found
		if(winner.getPathLength() > b.getPathLength()) {
			winner = b;
			tiedPlayers.clear();
		} else if(winner.getPathLength() == b.getPathLength()) {
			tiedPlayers.add(b);
		} else if(winner.getPathLength() > c.getPathLength()) {
			winner = c;
			tiedPlayers.clear();
		} else if(winner.getPathLength() == c.getPathLength()) {
			tiedPlayers.add(c);
		} else if(winner.getPathLength() > d.getPathLength()) {
			winner = d;
			tiedPlayers.clear();
		} else if(winner.getPathLength() == d.getPathLength()) {
			tiedPlayers.add(d);
		}
		
		if(tiedPlayers.size() > 0) {
			System.out.print("There is a tie between " + winner.getName());
			for(int i = 0; i < tiedPlayers.size(); i++) { 
				System.out.print(", " + tiedPlayers.get(i).getName());
			}
			System.out.println("\nEach with path length of " + winner.getPathLength() + "; the following paths were taken:");
			System.out.println(winner.getName() + "=" + winner.getPath());
			for(int i = 0; i < tiedPlayers.size(); i++) { 
				System.out.println(tiedPlayers.get(i).getName() + "=" + tiedPlayers.get(i).getPath());
			}
		} else {
			System.out.println("Race winner is " + winner.getName() + " with path length of " + winner.getPathLength() + " along the path " + winner.getPath());
		}
		
	}
	
	public static Maze createMaze1(){
		int id = 0;
		int width = 7;
		int length = 7;
		ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>(width);
		ArrayList<Tile> row1;
		ArrayList<Tile> row2;
		ArrayList<Tile> row3;
		ArrayList<Tile> row4;
		ArrayList<Tile> row5;
		ArrayList<Tile> row6;
		ArrayList<Tile> row7;
		
		Maze maze1 = new Maze(tiles);
		
		// initialize tiles, without giving each tile content
		for(int i = 0; i < width; i++) {
			tiles.add(new ArrayList<Tile>(length));
			for(int j = 0; j < length; j++) {
				tiles.get(i).add(new Tile(new Coordinate(i,j), id));
				id++;
			}	
		}
		
		row1 = tiles.get(0);
		for(int i = 0; i < row1.size(); i++) {
			switch(i){
			case 0:
				row1.get(i).setType("e");
				break;
			case 1:
				row1.get(i).setType("e");
				break;
			case 2:
				row1.get(i).setType("e");
				break;
			case 3:
				row1.get(i).setType("o");
				break;
			case 4:
				row1.get(i).setType("e");
				break;
			case 5:
				row1.get(i).setType("e");
				break;
			case 6:
				row1.get(i).setType("e");
				break;
			}
		}
		
		row2 = tiles.get(1);
		for(int i = 0; i < row2.size(); i++) {
			switch(i){
			case 0:
				row2.get(i).setType("e");
				break;
			case 1:
				row2.get(i).setType("o");
				break;
			case 2:
				row2.get(i).setType("e");
				break;
			case 3:
				row2.get(i).setType("e");
				break;
			case 4:
				row2.get(i).setType("o");
				break;
			case 5:
				row2.get(i).setType("e");
				break;
			case 6:
				row2.get(i).setType("e");
				break;
			}
		}
		
		row3 = tiles.get(2);
		for(int i = 0; i < row3.size(); i++) {
			switch(i){
			case 0:
				row3.get(i).setType("e");
				break;
			case 1:
				row3.get(i).setType("o");
				break;
			case 2:
				row3.get(i).setType("e");
				break;
			case 3:
				row3.get(i).setType("e");
				break;
			case 4:
				row3.get(i).setType("o");
				break;
			case 5:
				row3.get(i).setType("e");
				break;
			case 6:
				row3.get(i).setType("e");
				break;
			}
		}
		
		row4 = tiles.get(3);
		for(int i = 0; i < row4.size(); i++) {
			switch(i){
			case 0:
				row4.get(i).setType("e");
				break;
			case 1:
				row4.get(i).setType("o");
				break;
			case 2:
				row4.get(i).setType("o");
				break;
			case 3:
				row4.get(i).setType("h");
				break;
			case 4:
				row4.get(i).setType("o");
				break;
			case 5:
				row4.get(i).setType("e");
				break;
			case 6:
				row4.get(i).setType("e");
				break;
			}
		}
		
		row5 = tiles.get(4);
		for(int i = 0; i < row5.size(); i++) {
			switch(i){
			case 0:
				row5.get(i).setType("e");
				break;
			case 1:
				row5.get(i).setType("e");
				break;
			case 2:
				row5.get(i).setType("o");
				break;
			case 3:
				row5.get(i).setType("e");
				break;
			case 4:
				row5.get(i).setType("e");
				break;
			case 5:
				row5.get(i).setType("e");
				break;
			case 6:
				row5.get(i).setType("e");
				break;
			}
		}
		
		row6 = tiles.get(5);
		for(int i = 0; i < row6.size(); i++) {
			switch(i){
			case 0:
				row6.get(i).setType("e");
				break;
			case 1:
				row6.get(i).setType("e");
				break;
			case 2:
				row6.get(i).setType("o");
				break;
			case 3:
				row6.get(i).setType("o");
				break;
			case 4:
				row6.get(i).setType("o");
				break;
			case 5:
				row6.get(i).setType("e");
				break;
			case 6:
				row6.get(i).setType("e");
				break;
			}
		}
		
		row7 = tiles.get(6);
		for(int i = 0; i < row7.size(); i++) {
			switch(i){
			case 0:
				row7.get(i).setType("e");
				break;
			case 1:
				row7.get(i).setType("e");
				break;
			case 2:
				row7.get(i).setType("e");
				break;
			case 3:
				row7.get(i).setType("e");
				break;
			case 4:
				row7.get(i).setType("e");
				break;
			case 5:
				row7.get(i).setType("e");
				break;
			case 6:
				row7.get(i).setType("e");
				break;
			}
		}
		
		return maze1;	
	}
	
	public static Maze createMaze2() {
		int id = 0;
		int width = 9;
		int length = 9;
		ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>(width);
		ArrayList<Tile> row1;
		ArrayList<Tile> row2;
		ArrayList<Tile> row3;
		ArrayList<Tile> row4;
		ArrayList<Tile> row5;
		ArrayList<Tile> row6;
		ArrayList<Tile> row7;
		ArrayList<Tile> row8;
		ArrayList<Tile> row9;
		
		Maze maze2 = new Maze(tiles);
		
		/* NOTE: void/missing tiles, like some of the tiles of the last 2 columns,
		 * will be represented by obstacle tiles*/
		
		// initialize tiles, without giving each tile content
		for(int i = 0; i < width; i++) {
			tiles.add(new ArrayList<Tile>(length));
			for(int j = 0; j < length; j++) {
				tiles.get(i).add(new Tile(new Coordinate(i,j), id));
				id++;
			}	
		}
		
		row1 = tiles.get(0);
		for(int i = 0; i < row1.size(); i++) {
			switch(i){
			case 0:
				row1.get(i).setType("e");
				break;
			case 1:
				row1.get(i).setType("e");
				break;
			case 2:
				row1.get(i).setType("e");
				break;
			case 3:
				row1.get(i).setType("e");
				break;
			case 4:
				row1.get(i).setType("o");
				break;
			case 5:
				row1.get(i).setType("e");
				break;
			case 6:
				row1.get(i).setType("e");
				break;
			case 7:
				row1.get(i).setType("e");
				break;
			case 8:
				row1.get(i).setType("e");
				break;
			}
		}
		
		row2 = tiles.get(1);
		for(int i = 0; i < row2.size(); i++) {
			switch(i){
			case 0:
				row2.get(i).setType("e");
				break;
			case 1:
				row2.get(i).setType("o");
				break;
			case 2:
				row2.get(i).setType("e");
				break;
			case 3:
				row2.get(i).setType("e");
				break;
			case 4:
				row2.get(i).setType("o");
				break;
			case 5:
				row2.get(i).setType("e");
				break;
			case 6:
				row2.get(i).setType("e");
				break;
			case 7:
				row2.get(i).setType("e");
				break;
			case 8:
				row2.get(i).setType("e");
				break;
			}
		}
		
		row3 = tiles.get(2);
		for(int i = 0; i < row3.size(); i++) {
			switch(i){
			case 0:
				row3.get(i).setType("e");
				break;
			case 1:
				row3.get(i).setType("o");
				break;
			case 2:
				row3.get(i).setType("e");
				break;
			case 3:
				row3.get(i).setType("e");
				break;
			case 4:
				row3.get(i).setType("o");
				break;
			case 5:
				row3.get(i).setType("e");
				break;
			case 6:
				row3.get(i).setType("e");
				break;
			case 7:
				row3.get(i).setType("o");
				break;
			case 8:
				row3.get(i).setType("o");
				break;
			}
		}
		
		row4 = tiles.get(3);
		for(int i = 0; i < row4.size(); i++) {
			switch(i){
			case 0:
				row4.get(i).setType("e");
				break;
			case 1:
				row4.get(i).setType("o");
				break;
			case 2:
				row4.get(i).setType("o");
				break;
			case 3:
				row4.get(i).setType("h");
				break;
			case 4:
				row4.get(i).setType("o");
				break;
			case 5:
				row4.get(i).setType("e");
				break;
			case 6:
				row4.get(i).setType("e");
				break;
			case 7:
				row4.get(i).setType("o");
				break;
			case 8:
				row4.get(i).setType("o");
				break;
			}
		}
		
		row5 = tiles.get(4);
		for(int i = 0; i < row5.size(); i++) {
			switch(i){
			case 0:
				row5.get(i).setType("e");
				break;
			case 1:
				row5.get(i).setType("e");
				break;
			case 2:
				row5.get(i).setType("o");
				break;
			case 3:
				row5.get(i).setType("e");
				break;
			case 4:
				row5.get(i).setType("e");
				break;
			case 5:
				row5.get(i).setType("e");
				break;
			case 6:
				row5.get(i).setType("e");
				break;
			case 7:
				row5.get(i).setType("o");
				break;
			case 8:
				row5.get(i).setType("o");
				break;
			}
		}
		
		row6 = tiles.get(5);
		for(int i = 0; i < row6.size(); i++) {
			switch(i){
			case 0:
				row6.get(i).setType("e");
				break;
			case 1:
				row6.get(i).setType("e");
				break;
			case 2:
				row6.get(i).setType("o");
				break;
			case 3:
				row6.get(i).setType("o");
				break;
			case 4:
				row6.get(i).setType("o");
				break;
			case 5:
				row6.get(i).setType("e");
				break;
			case 6:
				row6.get(i).setType("e");
				break;
			case 7:
				row6.get(i).setType("o");
				break;
			case 8:
				row6.get(i).setType("o");
				break;
			}
		}
		
		row7 = tiles.get(6);
		for(int i = 0; i < row7.size(); i++) {
			switch(i){
			case 0:
				row7.get(i).setType("e");
				break;
			case 1:
				row7.get(i).setType("e");
				break;
			case 2:
				row7.get(i).setType("e");
				break;
			case 3:
				row7.get(i).setType("e");
				break;
			case 4:
				row7.get(i).setType("e");
				break;
			case 5:
				row7.get(i).setType("o");
				break;
			case 6:
				row7.get(i).setType("e");
				break;
			case 7:
				row7.get(i).setType("o");
				break;
			case 8:
				row7.get(i).setType("o");
				break;
			}
		}
		
		row8 = tiles.get(7);
		for(int i = 0; i < row8.size(); i++) {
			switch(i){
			case 0:
				row8.get(i).setType("e");
				break;
			case 1:
				row8.get(i).setType("e");
				break;
			case 2:
				row8.get(i).setType("e");
				break;
			case 3:
				row8.get(i).setType("e");
				break;
			case 4:
				row8.get(i).setType("e");
				break;
			case 5:
				row8.get(i).setType("e");
				break;
			case 6:
				row8.get(i).setType("e");
				break;
			case 7:
				row8.get(i).setType("e");
				break;
			case 8:
				row8.get(i).setType("e");
				break;
			}
		}
		
		row9 = tiles.get(8);
		for(int i = 0; i < row9.size(); i++) {
			switch(i){
			case 0:
				row9.get(i).setType("e");
				break;
			case 1:
				row9.get(i).setType("e");
				break;
			case 2:
				row9.get(i).setType("e");
				break;
			case 3:
				row9.get(i).setType("e");
				break;
			case 4:
				row9.get(i).setType("e");
				break;
			case 5:
				row9.get(i).setType("e");
				break;
			case 6:
				row9.get(i).setType("e");
				break;
			case 7:
				row9.get(i).setType("e");
				break;
			case 8:
				row9.get(i).setType("e");
				break;
			}
		}
		
		return maze2;
	}
}
