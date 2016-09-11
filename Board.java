package trominoes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Board is the class for the JFrame
 * displaying the trominoes
 * 
 * @author Glen
 *
 */

public class Board extends JFrame {
	protected int height = 400;
	protected int width = 400;
	protected NiceColour nCol;
	protected Set<Integer> used = new HashSet<Integer>();
	MyCanvas myC;

	public Board() {
		nCol = new NiceColour();
		myC = new MyCanvas();

	}

	public MyCanvas getCanvas() {
		return myC;
	}

	/**
	 * Set the characteristics of the display JFrame
	 * and show it on the screen
	 */
	public void display() {
		setLayout(new BorderLayout());
		
		add(myC, BorderLayout.CENTER);
		
		pack();
		setTitle("Trominoes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300,100);
		setVisible(true);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	/** MyCanvas is an internal class 
	 *  used for the actual drawing
	 * @author Glen
	 *
	 */
	class MyCanvas extends JComponent{
		List<PolyTromino> drawObjects;

		MyCanvas() {
			drawObjects = new ArrayList<PolyTromino>();
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(width, height);
		}

		/**
		 * Add a polyTromino to the ArrayList
		 * @param drawObject
		 */
		public void addToList(PolyTromino drawObject) {
			drawObjects.add(drawObject);
		}

		/**
		 * Draw the Tromino polygons onto the canvas using
		 * 'random' colours
		 * 
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			for (PolyTromino drawO : drawObjects) {
				g.setColor(randomColour());
				g.fillPolygon(drawO.xPoints(), drawO.yPoints(), drawO.nPoints());
			}
		}

	}
	
	
/**
 * Produce a random colour from a set list of 'nice' colours.
 * Maintain a list of colours already used so that colours aren't repeated
 * unless a large number are needed.
 * 
 * @return
 */
	protected Color randomColour() {
		Color col = null;
		//Pick a random number that is less than the list size
		int index = (int) (Math.random() * nCol.numCols());
		col = nCol.pickColour(index);
		// if 'used' gets as big as the colour list, start again
		if (used.size() == nCol.numCols()) {
			used.clear();
		}
		// recurse if the picked colour is already used
		if (used.contains(index))
			col = randomColour();
		else
			// add the picked colour to the used list
			used.add(index);
		return col;
	}

}
