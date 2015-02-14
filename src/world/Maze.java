package world;

import java.util.ArrayList;
import java.util.List;

public class Maze {

	private List<ArrayList<Tile>> tiles;

	public Maze(List<ArrayList<Tile>> tiles2) {
		this.tiles = tiles2;
	}

	public List<ArrayList<Tile>> getTiles() {
		return tiles;
	}

	@Override
	public String toString() {
		return "Maze [tiles=" + tiles + "]";
	}

}
