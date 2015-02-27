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
	
	private String path;
	private int pathLength;
	
	public AStarAlgo() {
		this.openSet = new ArrayList<Tile>();
		this.closeSet = new ArrayList<Tile>();
		this.isGoalFound = false;
		this.path = new String();
	}

	// correct inputs later
	public void start(Maze maze1, Player player, Tile start, Tile goal) {
		System.out.println(player.getName() + " to get from " + start.getCoord() + " to " + goal.getCoord());
		tiles = maze1.getTiles();
		
		openSet.add(start);
		
		// loop through A* steps until goal is reached
		while(!isGoalFound) {
			Tile current = getMinFValue();
			
			// there are no more members of the openSet to examine
			if(current == null) {
				break;
			}
			
		//	System.out.println(current.getId() + " has lowest F value " + current.getFValue());
		
			// if the current tile is the goal
			if(current.getType() == home) {
				
			}
			
			// get frontier of neighbours of current tile
			ArrayList<Tile> frontier = getFrontier(current);
			
			// loop through frontier and calculate heuristic for each tile
			for(int i = 0; i < frontier.size(); i++) {
	//			System.out.println(frontier.get(i));
				
				// associate frontier tile to parent tile
				frontier.get(i).setParent(current);

				if(frontier.get(i).getType() == empty) {
					// calculate neighbour's fValue
					calcHeuristic(frontier.get(i), goal);

					// set parent of neighbour
					frontier.get(i).setParent(current); 
					
					// add neighbour to openSet
					openSet.add(frontier.get(i));
				} else if (frontier.get(i).getType() == home) {
//					System.out.println(frontier.get(i).getCoord() + " is the goal");

					// set parent of neighbour
					frontier.get(i).setParent(current); 
					
					// trigger termination flag
					isGoalFound = true;
					
					// reconstruct path
					path = constructPath(frontier.get(i));
					
					// determine length of path
					pathLength = getPathLength(frontier.get(i));
				} else if (frontier.get(i).getType() == obstacle) {
					// do not need to calculate anything, just skip
				}	
			}
		}
		
	//	System.out.println("Path is \n" + path);
	//	System.out.println("Path length is " + pathLength);
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
					
	//				System.out.println("checking tile " + checkTile.getId() + " on left");
					
					// check to see if collision occurred with obstacle
					if (checkTile.getType() == obstacle) {	
	//					System.out.println("Obstacle was found at " + checkTile.getId());
						
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
					
		//			System.out.println("checking tile " + checkTile.getId() + " on right");
					
					// check to see if collision occurred with obstacle
					if (checkTile.getType() == obstacle) {	
//						System.out.println("Obstacle was found at " + checkTile.getId());
						
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
					
	//				System.out.println("checking tile " + checkTile.getId() + " below");
					
					if (checkTile.getType() == obstacle) {	
		//				System.out.println("Obstacle was found at " + checkTile.getId());
						
						numCollisions++;
					} else if (checkTile.getType() == home) {
	//					System.out.println("Home reached");
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
					
		//			System.out.println("checking tile " + checkTile.getId() + " above");
					
					if (checkTile.getType() == obstacle) {	
			//			System.out.println("Obstacle was found at " + checkTile.getId());
						
						numCollisions++;
					} else if (checkTile.getType() == home) {
	//					System.out.println("Home reached");
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

	// Manhattan distance between tiles used for h calculation
	private int[] calcManhattanDistance(Tile a, Tile b) {		
		int[] mDistance = new int[2];
		mDistance[0] = a.getCoord().getCol() - b.getCoord().getCol(); // distance between columns
		mDistance[1] = a.getCoord().getRow() - b.getCoord().getRow(); // distance between rows
		
		//System.out.println("calculated Man Dist: " + mDistance[0] + " " + mDistance[1]);
		
		return mDistance;
	}
	
	// return specific tile, based on row/col from maze
	private Tile getTile(int row, int col) {
		Tile tile = null;
		if(tiles != null) {
			tile = tiles.get(row).get(col); 
		}
		return tile;
	}
	
	// return frontier tiles of given tile
	private ArrayList<Tile> getFrontier(Tile tile) {
		ArrayList<Tile> frontier = new ArrayList<Tile>();
		
		// ensure neighbour is not out of bounds
		if(tile.getRow() - 1 >= 0) { 
			// get north neighbour if it's not in closeSet or openSet
			Tile neighbour = getTile(tile.getRow() - 1, tile.getCol());
			if(!closeSetContains(neighbour) && !openSetContains(neighbour)) {
				frontier.add(neighbour);	
			}
		}
		
		// ensure neighbour is not out of bounds
		if(tile.getCol() + 1 < tiles.get(tile.getRow()).size()) { 
			// get east neighbour if it's not in closeSet or openSet
			Tile neighbour = getTile(tile.getRow(), tile.getCol() + 1);
			if(!closeSetContains(neighbour) && !openSetContains(neighbour)) {
				frontier.add(neighbour);
			}
		}
		
		// ensure neighbour is not out of bounds
		if(tile.getRow() + 1 < tiles.size()) { 
			//  get south neighbour if it's not in closeSet or openSet
			Tile neighbour = getTile(tile.getRow() + 1, tile.getCol());
			if(!closeSetContains(neighbour) && !openSetContains(neighbour)) {
				frontier.add(neighbour);
			}
		}
		
		// ensure neighbour is not out of bounds
		if(tile.getCol() - 1 >= 0) { 
			// get west neighbour if it's not in closeSet or openSet
			Tile neighbour = getTile(tile.getRow(), tile.getCol() - 1);
			if(!closeSetContains(neighbour) && !openSetContains(neighbour)){
				frontier.add(neighbour);
			}
		}
		
		return frontier;
	}
	
	// return tile with lowest fValue in the openSet
	private Tile getMinFValue() {
		Tile lowest = null;
		int indexLowest = -1;
		
		if(openSet.size() > 0) {
			lowest = openSet.get(0);
			indexLowest = 0;
			
			// perform basic linear search
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
			
			// add lowest fValue tile to closeSet
			closeSet.add(openSet.remove(indexLowest));
		}
		return lowest;
	}

	// determine if given tile is in closeSet; has been visited
	private boolean closeSetContains(Tile tile) {
		boolean doesCloseSetContain = false;
		
		// perform linear search through closeSet
		for(int i = 0; i < closeSet.size(); i++) {
			if(closeSet.get(i).getId() == tile.getId()) {
				doesCloseSetContain = true;
				break;
			}
		}
		
		return doesCloseSetContain;
	}
	
	// determine if given tile is in closeSet; has been visited
	private boolean openSetContains(Tile tile) {
		boolean doesOpenSetContain = false;

		// perform linear search through openSet
		for(int i = 0; i < openSet.size(); i++) {
			if(closeSet.get(i).getId() == tile.getId()) {
				doesOpenSetContain = true;
				break;
			}
		}
		
		return doesOpenSetContain;
	}
	
	// returns length of path from start to end tile
	private int getPathLength(Tile tile) {
		int length = 0;
		Tile current = tile;
		
		// loop until reached null, or start tile's non-existent parent
		while(current != null) {
			// increment length
			length++;
			
			// iterate to current's parent tile
			current = current.getParent();
		}
		
		return length;
	}
	
	// create string of path from start to end tile
	private String constructPath(Tile tile) {
		String result = new String();
		Tile current = tile;
		ArrayList<String> reversedPath = new ArrayList<String>(); // tiles from end to start
		
		// loop until reached null, or start tile's non-existent parent
		while(current != null) {
			// add current's id to reversedPath
			reversedPath.add(String.valueOf(current.getId()));
		//	System.out.println(String.valueOf(current.getId()));
			
			// iterate to current's parent tile
			current = current.getParent();
		}
		
		// create path from start to end tile using reversePath
		for(int i = reversedPath.size() - 1; i >= 0; i--) {
			if(i != 0) {
				result += reversedPath.get(i) + "=>";
			} else {
				result += reversedPath.get(i);
			}
		}
		
		return result;
	}

	// return path
	public String getPath() {
		return path;
	}

	// return pathLength
	public int getPathLength() {
		return pathLength;
	}
}
