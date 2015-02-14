package main;
import java.util.ArrayList;
import java.util.List;

import world.Coordinate;
import world.Maze;
import world.Tile;


public class Simulation {
	public static void main(String[] args) {
	/*	String [][] maze1 = { 
				{"e", "e", "e", "o", "e", "e", "e"},
				{"e", "o", "e", "e", "o", "e", "e"},
				{"e", "o", "e", "e", "o", "e", "e"},
				{"e", "o", "o", "h", "o", "e", "e"},
				{"e", "e", "o", "e", "e", "e", "e"},
				{"e", "e", "o", "o", "o", "e", "e"},
				{"e", "e", "e", "e", "e", "e", "e"},
		};*/
		
		@SuppressWarnings("unused")
		Maze maze1 = createMaze1();
		
		//Player player1 = new Player(0,0,"player1");
		//AStarAlgo aStarAlgo = new AStarAlgo();
		//aStarAlgo.start(maze1, player1);	
		
	}
	
	public static Maze createMaze1(){
		int width = 7;
		int length = 7;
		List<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>(7);
		Maze maze1 = new Maze(tiles);
		
		// initialize tiles, without giving each tile content
		for(int i = 0; i < width; i++) {
			tiles.add(new ArrayList<Tile>(7));
			for(int j = 0; j < length; j++) {
				tiles.get(i).add(new Tile(new Coordinate(i,j)));
				System.out.println("tile: " + tiles.get(i).get(j).getCoord());
			}	
		}
		
		
		
	/*	for(int i = 0; i < row1.size(); i++) {
			switch(i){
			case 0:
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			}
		}
		
		ArrayList<Tile> row2 = new ArrayList<>(7);
		ArrayList<Tile> row3 = new ArrayList<>(7);
		ArrayList<Tile> row4 = new ArrayList<>(7);
		ArrayList<Tile> row5 = new ArrayList<>(7);
		ArrayList<Tile> row6 = new ArrayList<>(7);
		ArrayList<Tile> row7 = new ArrayList<>(7);
		
		*/
		return maze1;
		
	}
}
