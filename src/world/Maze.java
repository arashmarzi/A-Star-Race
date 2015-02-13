package world;

import java.util.ArrayList;

public class Maze {

	private ArrayList<ArrayList<Tile>> tiles;

	public Maze() {
		tiles = new ArrayList<ArrayList<Tile>>();
	}

	public ArrayList<ArrayList<Tile>> getTiles() {
		return tiles;
	}

	@Override
	public String toString() {
		return "Maze [tiles=" + tiles + "]";
	}

}
