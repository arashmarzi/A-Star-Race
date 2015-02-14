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
		String tilesStr = new String();
		
		for(int i = 0; i < tiles.size(); i++) {
			for (int j = 0; j < tiles.get(i).size(); j++){
				tilesStr += "[" + tiles.get(i).get(j).getType() + "]";
			}
			tilesStr += "\n";
		}
		return tilesStr;
	}

}
