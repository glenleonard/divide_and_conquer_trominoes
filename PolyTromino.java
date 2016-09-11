package trominoes;

/**
 * PolyTromino creates the polygon shape of a tromino (L-shaped) in terms of
 * the x and y location of each corner point
 * 
 * @author Glen
 *
 */
public class PolyTromino {
	// xPoints and yPoints are at the 6 corners of the L-shaped tromino
	//
	private int[] xPoints = new int[6];
	private int[] yPoints = new int[6];

	// Constructor creates a new tromino polygon
	public PolyTromino(int x1, int x2, int x3, int x4, int x5, int x6, int y1, int y2, int y3, int y4, int y5, int y6) {

		xPoints[0] = x1;
		xPoints[1] = x2;
		xPoints[2] = x3;
		xPoints[3] = x4;
		xPoints[4] = x5;
		xPoints[5] = x6;

		yPoints[0] = y1;
		yPoints[1] = y2;
		yPoints[2] = y3;
		yPoints[3] = y4;
		yPoints[4] = y5;
		yPoints[5] = y6;

	}

	public int[] xPoints() {
		return xPoints;
	}

	public int[] yPoints() {
		return yPoints;
	}

	public int nPoints() {
		return 6;
	}

}
