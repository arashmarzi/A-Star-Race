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
		System.out.println("MAZE:\n" + maze1.toString());
		
		Player player1 = new Player(0, 0, "player1");
		System.out.println("created player: " + player1.toString());
		
		Player player2 = new Player(0, 6, "player2");
		System.out.println("created player: " + player2.toString());
		
		Player player3 = new Player(6, 0, "player3");
		System.out.println("created player: " + player3.toString());
		
		Player player4 = new Player(6, 6, "player4");
		System.out.println("created player: " + player4.toString());
		
		// start algorithm for player1
		AStarAlgo aStarAlgo1 = new AStarAlgo();
		aStarAlgo1.start(maze1, player1, maze1.getTiles().get(player1.getStartRow()).get(player1.getStartCol()), maze1.getTiles().get(3).get(3));
		player1.setPath(aStarAlgo1.getPath());
		player1.setPathLength(aStarAlgo1.getPathLength());
		System.out.println(player1);
	
		// recreate maze, removes parent tiles and heuristic values
		maze1 = createMaze1();
		
		// start algorithm for player2
		AStarAlgo aStarAlgo2 = new AStarAlgo();
		aStarAlgo2.start(maze1, player2, maze1.getTiles().get(player2.getStartRow()).get(player2.getStartCol()), maze1.getTiles().get(3).get(3));
		player2.setPath(aStarAlgo2.getPath());
		player2.setPathLength(aStarAlgo2.getPathLength());
		System.out.println(player2);
		
		// recreate map, removes parent tiles and heuristic values
		maze1 = createMaze1();
		
		// start algorithm for player2
		AStarAlgo aStarAlgo3 = new AStarAlgo();
		aStarAlgo3.start(maze1, player3, maze1.getTiles().get(player3.getStartRow()).get(player3.getStartCol()), maze1.getTiles().get(3).get(3));
		player3.setPath(aStarAlgo3.getPath());
		player3.setPathLength(aStarAlgo3.getPathLength());
		System.out.println(player3);
		
		// recreate map, removes parent tiles and heuristic values
		maze1 = createMaze1();
		
		// start algorithm for player4
		AStarAlgo aStarAlgo4 = new AStarAlgo();
		aStarAlgo4.start(maze1, player4, maze1.getTiles().get(player4.getStartRow()).get(player4.getStartCol()), maze1.getTiles().get(3).get(3));
		player4.setPath(aStarAlgo4.getPath());
		player4.setPathLength(aStarAlgo4.getPathLength());
		System.out.println(player4);
		
		// recreate map, removes parent tiles and heuristic values
		maze1 = createMaze1();
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
}
