package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import world.Maze;
import world.Player;
import world.Tile;

public class AStarAlgo {
	private int vertSquares;
	private int horzSquares;
	private int numObstacles;

	private final String obstacle = "o";
	private final String empty = "e";
	private final String home = "h";

	private Map<Integer, Tile> openSet;
	private ArrayList<Tile> closeSet;
	private Map<Integer, Tile> cameFrom;
	private Integer cameFromInt;

	private List<ArrayList<Tile>> tiles;

	public AStarAlgo() {
		this.vertSquares = 0;
		this.horzSquares = 0;
		this.numObstacles = 0;

		this.openSet = new HashMap<Integer, Tile>();
		this.closeSet = new ArrayList<Tile>();
		this.cameFrom = new HashMap<Integer, Tile>();

		this.cameFromInt = 0;
	}

	// correct inputs later
	public void start(Maze maze1, Player player1, Tile start, Tile goal) {
		System.out.println("Starting Algorithm to get from " + start.getCoord() + " to " + goal.getCoord());
		tiles = maze1.getTiles();

		// calculate h, g, and f function values for each tile
		System.out.println("calculating h, g, f values\n");
		for (int i = 0; i < tiles.size(); i++) {
			for (int j = 0; j < tiles.get(i).size(); j++) {
				Tile currentTile = tiles.get(i).get(j);
				
				System.out.println("tile " + currentTile.getId());
				if (currentTile.getType() == empty) {
					calcHeuristic(currentTile, goal);
				} else if (currentTile.getType() == home) {
					System.out.println(currentTile.getCoord() + " is the goal");
				} else {
					System.out.println(currentTile.getCoord() + " is an obstacle");
				}
				
				System.out.println();
			}
		}

	}

	private void calcHeuristic(Tile tile, Tile goal) {
		int[] mDistance = calcManhattanDistance(tile, goal);
		int gValue = 0; 
		int hValue = 0;
		int fValue = 0;
		int numCollisions = 0;

		// if goal is to left of current tile
		if (mDistance[0] > 0) {
			// move along the row of current tile to find collisions
			for (int i = 1; i <= Math.abs(mDistance[0]); i++) {
				// ensure tile checking has not gone out of bounds
				if (tile.getCoord().getCol() - i >= 0) {
					Tile checkTile = tiles.get(tile.getCoord().getRow()).get(tile.getCoord().getCol() - i);
					System.out.println("checking tile " + checkTile.getCoord() + " on left");
					if (checkTile.getType() == obstacle) {
						numCollisions++;
					}
				} else {
					System.out.println("No more tiles to check, out of bounds");
				}

			}
		} else { // goal is to right of current tile
			// move along the row of current tile to find collisions
			for (int i = 1; i <= Math.abs(mDistance[0]); i++) {
				// ensure tile checking has not gone out of bounds
				if (tile.getCoord().getCol() + i < tiles.get(tile.getCoord().getRow()).size()) {
					Tile checkTile = tiles.get(tile.getCoord().getRow()).get(tile.getCoord().getCol() + i);
					System.out.println("checking tile " + checkTile.getCoord() + " on right");
					if (checkTile.getType() == obstacle) {
						numCollisions++;
					}
				} else {
					System.out.println("No more tiles to check, out of bounds");
				}

			}
		}

		// if goal is below current tile
		if (mDistance[1] < 0) {
			// move along the row of current tile to find collisions
			for (int i = 1; i <= Math.abs(mDistance[1]); i++) {
				// ensure tile checking has not gone out of bounds
				if (tile.getCoord().getRow() + i < tiles.size()) {
					Tile checkTile = tiles.get(tile.getCoord().getRow() + i).get(tile.getCoord().getCol() + Math.abs(mDistance[0]));
					System.out.println("checking tile " + checkTile.getCoord() + " below");
					if (checkTile.getType() == obstacle) {
						numCollisions++;
					}
				} else {
					System.out.println("No more tiles to check, out of bounds");
				}

			}
		} else { // goal is above current tile
			// move along the col of current tile to find collisions
			for (int i = 1; i <= Math.abs(mDistance[1]); i++) {
				// ensure tile checking has not gone out of bounds
				if (tile.getCoord().getRow() - i >= 0) {
					Tile checkTile = tiles.get(tile.getCoord().getRow() - i).get(tile.getCoord().getCol() + Math.abs(mDistance[0]));
					System.out.println("checking tile " + checkTile.getCoord() + " above");
					if (checkTile.getType() == obstacle) {
						numCollisions++;
					}
				} else {
					System.out.println("No more tiles to check, out of bounds");
				}

			}
		}
		//return vertSquares + horzSquares + numObstacles;

	}

	private int[] calcManhattanDistance(Tile a, Tile b) {		
		int[] mDistance = new int[2];
		mDistance[0] = a.getCoord().getCol() - b.getCoord().getCol(); 
		mDistance[1] = a.getCoord().getRow() - b.getCoord().getRow();
		
		System.out.println("calculated Man Dist");
		
		return mDistance;
	}

}
