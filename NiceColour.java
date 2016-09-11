package trominoes;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * These are some 'nice' colours to be used for drawing
 * 
 * @author Glen
 *
 */
public class NiceColour {
private List<Color> colours = new ArrayList<Color>();

public NiceColour(){
	colours.add(new Color(0, 153, 0));
	colours.add(new Color(0, 153, 153));
	colours.add(new Color(0, 153, 255));
	colours.add(new Color(0, 255, 0));
	colours.add(new Color(0, 255, 153));
	colours.add(new Color(0, 255, 255));
	colours.add(new Color(51, 51, 204));
	colours.add(new Color(51, 102, 51));
	colours.add(new Color(51, 102, 102));
	colours.add(new Color(51, 102, 255));
	colours.add(new Color(51, 153, 51));
	colours.add(new Color(51, 153, 153));
	colours.add(new Color(51, 204, 51));
	colours.add(new Color(51, 204, 102));
	colours.add(new Color(51, 204, 153));
	colours.add(new Color(51, 204, 204));
	colours.add(new Color(51, 204, 255));
	colours.add(new Color(51, 255, 255));
	colours.add(new Color(102, 102, 0));
	colours.add(new Color(102, 102, 51));
	colours.add(new Color(102, 102, 153));
	colours.add(new Color(102, 102, 255));
	colours.add(new Color(102, 140, 255));
	colours.add(new Color(102, 153, 51));
	colours.add(new Color(102, 153, 153));
	colours.add(new Color(102, 153, 204));
	colours.add(new Color(102, 153, 255));
	colours.add(new Color(102, 179, 255));
	colours.add(new Color(102, 255, 102));
	colours.add(new Color(102, 255, 140));
	colours.add(new Color(102, 255, 179));
	colours.add(new Color(102, 255, 217));
	colours.add(new Color(102, 255, 255));
	colours.add(new Color(140, 102, 255));
	colours.add(new Color(140, 255, 102));
	colours.add(new Color(153, 51, 102));
	colours.add(new Color(153, 51, 153));
	colours.add(new Color(153, 51, 255));
	colours.add(new Color(153, 102, 51));
	colours.add(new Color(153, 102, 102));
	colours.add(new Color(153, 102, 153));
	colours.add(new Color(153, 102, 255));
	colours.add(new Color(153, 153, 51));
	colours.add(new Color(153, 153, 102));
	colours.add(new Color(153, 153, 204));
	colours.add(new Color(153, 153, 255));
	colours.add(new Color(153, 204, 204));
	colours.add(new Color(153, 204, 255));
	colours.add(new Color(179, 102, 255));
	colours.add(new Color(204, 51, 153));
	colours.add(new Color(204, 102, 51));
	colours.add(new Color(204, 102, 102));
	colours.add(new Color(204, 102, 255));
	colours.add(new Color(204, 153, 51));
	colours.add(new Color(204, 153, 255));
	colours.add(new Color(204, 204, 0));
	colours.add(new Color(204, 255, 102));
	colours.add(new Color(204, 255, 204));
	colours.add(new Color(217, 255, 102));
	colours.add(new Color(230, 153, 0));
	colours.add(new Color(230, 153, 51));
	colours.add(new Color(230, 190, 0));
	colours.add(new Color(230, 230, 0));
	colours.add(new Color(255, 51, 0));
	colours.add(new Color(255, 51, 153));
	colours.add(new Color(255, 80, 80));
	colours.add(new Color(255, 102, 51));
	colours.add(new Color(255, 102, 102));
	colours.add(new Color(255, 102, 140));
	colours.add(new Color(255, 102, 179));
	colours.add(new Color(255, 102, 255));
	colours.add(new Color(255, 153, 51));
	colours.add(new Color(255, 153, 51));
	colours.add(new Color(255, 153, 255));
	colours.add(new Color(255, 153, 255));
	colours.add(new Color(255, 168, 128));
	colours.add(new Color(255, 179, 102));
	colours.add(new Color(255, 179, 217));
	colours.add(new Color(255, 200, 10));
	colours.add(new Color(255, 217, 51));
	colours.add(new Color(255, 217, 102));
	colours.add(new Color(255, 255, 153));
	colours.add(new Color(255, 255, 10));
	colours.add(new Color(255, 255, 80));
	colours.add(new Color(255, 255, 102));
	colours.add(new Color(255, 255, 153));
	colours.add(new Color(255, 255, 204));
		
}

/**
 * Return the size of the ArrayList of colours
 * @return
 */
public int numCols(){
	return colours.size();
}


/**
 * Get a colour from the list
 * @param index
 * @return
 */
public Color pickColour(int index){
	return colours.get(index);
}



}
