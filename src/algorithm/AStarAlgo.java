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
	private final String obstacle = "o";
	private final String empty = "e";
	private final String home = "h";

	private ArrayList<Tile> openSet;
	private ArrayList<Tile> closeSet;

	private List<ArrayList<Tile>> tiles;

	private boolean isGoalFound;
	
	public AStarAlgo() {
		this.openSet = new ArrayList<Tile>();
		this.closeSet = new ArrayList<Tile>();
		this.isGoalFound = false;
	}

	// correct inputs later
	public void start(Maze maze1, Player player1, Tile start, Tile goal) {
		System.out.println("Starting Algorithm to get from " + start.getCoord() + " to " + goal.getCoord() + "\n");
		tiles = maze1.getTiles();
		
		openSet.add(start);
		
		while(!isGoalFound) {
			Tile current = getMinFValue();
			
			// there are no more members of the openSet to examine
			if(current == null) {
				break;
			}
			
			System.out.println(current.getId() + " has lowest F value " + current.getFValue());
		
			// if the current tile is the goal
			if(current.getType() == home) {
				isGoalFound = true;
				
				// reconstruct path and return
			}
			
			// get frontier of neighbours of current tile
			Tile[] frontier = getFrontier(current);
			
			
			for(int i = 0; i < frontier.length; i++) {
				if(frontier[i] != null && !closeSetContains(frontier[i])) {
					System.out.println(frontier[i]);
				}
				
			}
			
			
		}
		
		// calculate h, g, and f function values for each tile
		//System.out.println("calculating h, g, f values\n");
		
	/*	for (int i = 0; i < tiles.size(); i++) {
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
		}*/

	}

	private void calcHeuristic(Tile tile, Tile goal) {
		int[] mDistance = calcManhattanDistance(tile, goal);
		int gValue = 0; 
		int hValue = 0;
		int fValue = 0;
		int numCollisions = 0;

		
		// if goal is to left of current tile ==> current (4) - goal (2) > 0
		if(mDistance[0] > 0) {
			// move along row, starting at farthest tile, to find collisions
			for(int i = mDistance[0]; i > 0; i--) {
				// ensure tile checking has not gone out of bounds
				if(tile.getCol() - i >= 0) {
					Tile checkTile = getTile(tile.getRow(), tile.getCol() - i);
					
					System.out.println("checking tile " + checkTile.getId() + " on left");
					
					if (checkTile.getType() == obstacle) {	
						System.out.println("Obstacle was found at " + checkTile.getId());
						
						numCollisions++;
					}
				}
			}
		} 
		
		// if goal is to right of current tile ==> current (2) - goal (4) < 0
		else if (mDistance[0] < 0) {
			// move along row, starting at farthest tile, to find collisions
			for(int i = mDistance[0]; i < 0; i++) {
				// ensure tile checking has not gone out of bounds
				if(tile.getCol() - i < tiles.get(tile.getRow()).size()) {
					Tile checkTile = getTile(tile.getRow(), tile.getCol() - i);
					
					System.out.println("checking tile " + checkTile.getId() + " on right");
					
					if (checkTile.getType() == obstacle) {	
						System.out.println("Obstacle was found at " + checkTile.getId());
						
						numCollisions++;
					}
				}
			}
		}
		
		// if goal is below current tile ==> current tile (2) - goal (4) < 0
		if(mDistance[1] < 0) {
			// move along col, starting at farthest tile, to find collisions
			for(int i = mDistance[1]; i < 0; i++) {
				// ensure tile checking has not gone out of bounds
				if(tile.getRow() - i < tiles.size()) {
					Tile checkTile = getTile(tile.getRow() - i, tile.getCol() - mDistance[0]);
					
					System.out.println("checking tile " + checkTile.getId() + " below");
					
					if (checkTile.getType() == obstacle) {	
						System.out.println("Obstacle was found at " + checkTile.getId());
						
						numCollisions++;
					} else if (checkTile.getType() == home) {
						System.out.println("Home reached");
					}
				}
			}
		} 
		
		// if goal is above current tile ==> current tile (4) - goal (2) > 0
		else if(mDistance[1] > 0) {
			// move along col, starting at farthest tile, to find collisions
			for(int i = mDistance[1]; i > 0; i--) {
				// ensure tile checking has not gone out of bounds
				if(tile.getRow() -i >= 0) {
					Tile checkTile = getTile(tile.getRow() - i, tile.getCol() - mDistance[0]);
					
					System.out.println("checking tile " + checkTile.getId() + " above");
					
					if (checkTile.getType() == obstacle) {	
						System.out.println("Obstacle was found at " + checkTile.getId());
						
						numCollisions++;
					} else if (checkTile.getType() == home) {
						System.out.println("Home reached");
					}
				}
			}
		}
		
		hValue = numCollisions + Math.abs(mDistance[0]) + Math.abs(mDistance[1]);
		gValue = tile.getParent().getGValue() + 1;
		fValue = hValue + gValue;
		
		tile.setHValue(hValue);
		tile.setGValue(gValue);
		tile.setFValue(fValue); 

	}

	private int[] calcManhattanDistance(Tile a, Tile b) {		
		int[] mDistance = new int[2];
		mDistance[0] = a.getCoord().getCol() - b.getCoord().getCol(); 
		mDistance[1] = a.getCoord().getRow() - b.getCoord().getRow();
		
		System.out.println("calculated Man Dist: " + mDistance[0] + " " + mDistance[1]);
		
		return mDistance;
	}
	
	private Tile getTile(int row, int col) {
		Tile tile = null;
		if(tiles != null) {
			tile = tiles.get(row).get(col); 
		}
		return tile;
	}
	
	private Tile[] getFrontier(Tile tile) {
		Tile[] frontier = new Tile[4];
		
		// get north neighbour
		if(tile.getRow() - 1 >= 0) {
			frontier[0] = getTile(tile.getRow() - 1, tile.getCol());
		} else {
			frontier[0] = null;
		}
		
		// get east neighbour
		if(tile.getCol() + 1 < tiles.get(tile.getRow()).size()) {
			frontier[1] = getTile(tile.getRow(), tile.getCol() + 1);
		} else {
			frontier[1] = null;
		}
		
		//  get south neighbour
		if(tile.getRow() + 1 < tiles.size()) {
			frontier[2] = getTile(tile.getRow() + 1, tile.getCol());
		} else {
			frontier[2] = null;
		}
		
		// get west neighbour
		if(tile.getCol() - 1 >= 0) {
			frontier[3] = getTile(tile.getRow(), tile.getCol() - 1);
		} else {
			frontier[3] = null;
		}
		
		return frontier;
	}
	
	private Tile getMinFValue() {
		Tile lowest = null;
		int indexLowest = -1;
		
		if(openSet.size() > 0) {
			lowest = openSet.get(0);
			indexLowest = 0;
			
			
			for(int i = 1; i < openSet.size(); i++) {
				if(lowest.getFValue() > openSet.get(i).getFValue()) {
					lowest = openSet.get(i);
					indexLowest = i;
				} else if(lowest.getFValue() == openSet.get(i).getFValue()) {
					if(lowest.getHValue() > openSet.get(i).getHValue()) {
						lowest = openSet.get(i);
						indexLowest = i;
					} else if(lowest.getHValue() == openSet.get(i).getHValue()){
						if(lowest.getGValue() > openSet.get(i).getGValue()) {
							lowest = openSet.get(i);
							indexLowest = i;
						}
					}
				}
			}
			
			closeSet.add(openSet.remove(indexLowest));
		}
		return lowest;
	}

	private boolean closeSetContains(Tile tile) {
		boolean doesCloseSetContain = false;
		
		for(int i = 0; i < closeSet.size(); i++) {
			if(closeSet.get(i).getId() == tile.getId()) {
				doesCloseSetContain = true;
				break;
			}
		}
		
		return doesCloseSetContain;
	}
}
