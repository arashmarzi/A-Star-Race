package world;

import java.util.ArrayList;
import java.util.List;

public class Maze {

	private List<ArrayList<Tile>> tiles;

	public Maze(List<ArrayList<Tile>> tiles) {
		this.tiles = tiles;
	}

	public List<ArrayList<Tile>> getTiles() {
		return tiles;
	}

	@Override
	public String toString() {
		String tilesStr = new String();
		
		for(int i = 0; i < tiles.size(); i++) {
			for (int j = 0; j < tiles.get(i).size(); j++){
				tilesStr += "[  " + tiles.get(i).get(j).getType() + " - ";
				if(tiles.get(i).get(j).getId() < 10) {
					tilesStr += "0" + tiles.get(i).get(j).getId() + "  ]";
				} else {
					tilesStr += tiles.get(i).get(j).getId() + "  ]";
				}
			}
			tilesStr += "\n";
		}
		return tilesStr;
	}

}
