package trominoes;

import java.util.Scanner;

/**
 * A tromino is made up of three equal-sized squares joined edge-to-edge.
 * An L-tromino is a tromino in an L-shape.
 * Golomb's tromino theorem: if any square is removed from a 2^n times 2^n chessboard,
 * the remaining board can be completely covered with L-trominoes.
 * 
 * The algorithm to tile larger boards is a divide-and-conquer algorithm,
 * recursively dividing the board into quarters, and solving the 2 x 2 case.
 * 
 * DrawTrominoes includes the main method to run the algorithm for tiling (n x n)
 * boards with n being a power of two
 * 
 * @author Glen
 *
 */
public class DrawTrominoes {
	// The tromino types represent the four different orientations of
	// the L-shape
	
	public enum tromType {
		UL, UR, LL, LR
	}
	// side is the length of the board side (n), e.g. 4 for 4x4; 8 for 8x8
	private int side;
	// 
	private Board board;
	//
	static Scanner reader;

	protected void run() {
		
		// get the side length from the user
		side = askForSide();
		
		// make a Pair for the deficient tile location and ask the user
		// for the coordinates
		
		Pair loc1 = new Pair();
		
		// run the algorithm for the defined board
		Pair offset = new Pair(0, 0);
		System.out.println("==================================================");
		System.out.println("The " + side + " x " + side + " board with deficient tile");
		System.out.println("at " + loc1 + " is tiled like so:");

		board = new Board();
		
		// run the algorithm
		tile(offset, side, loc1);
				
		// display the board
		board.display();
	}

	/**
	 * tile() is the algorithm that does the tiling; 'offset' finds the place in
	 * the larger board; 'side' is the length of a side; 'def' is the deficient
	 * tile location
	 * 
	 * @param side
	 * @param def
	 */
	private void tile(Pair offset, int side, Pair def) {
		// base case, tiling is reduced to a 2 x 2 board
		if (side == 2) {
			Tromino ret = twoByTwo(offset, def);
			PolyTromino polyTrom = makeTromino(ret);
			board.getCanvas().addToList(polyTrom);
			System.out.println(ret);
			
		// divide the board into quarters
		// recursively as necessary to get to the 2 x 2 case
		// tileCentre() is a special case to tile the tromino at
		// the centre that makes the deficient tiles for the quarters
		// that don't have the original deficient tile	
		} else {
			int half = side / 2;
			switch (quarter(side, def)) {
			case 1:
				tileCentre(offset,1,half);
				
				tile(offset, half, def); // 1 qtr
				
				tile(new Pair(offset.x + half, offset.y), 
						half, new Pair(0, half - 1)); // 2 qtr
				
				tile(new Pair(offset.x, offset.y + half), 
						half, new Pair(half - 1, 0)); // 3 qtr
				
				tile(new Pair(offset.x + half, offset.y + half),
						half, new Pair(0, 0)); // 4 qtr	
				
				break;
				
			case 2:
				tileCentre(offset,2,half);
				
				tile(offset, half, new Pair(half - 1, half - 1)); // 1 qtr
				
				tile(new Pair(offset.x + half, offset.y), half,
						new Pair(def.x - half, def.y)); // 2 qtr
				
				tile(new Pair(offset.x, offset.y + half), 
						half, new Pair(half - 1, 0)); // 3 qtr
				
				tile(new Pair(offset.x + half, offset.y + half), 
						half, new Pair(0, 0)); // 4 qtr
				
				break;
				
			case 3:
				tileCentre(offset,3,half);
				
				tile(offset, half, new Pair(half - 1, half - 1)); // 1 qtr
				
				tile(new Pair(offset.x + half, offset.y), half,
						new Pair(0, half - 1)); // 2 qtr
																						
				tile(new Pair(offset.x, offset.y + half), half, 
						new Pair(def.x, def.y - half)); // 3 qtr
																								
				tile(new Pair(offset.x + half, offset.y + half), 
						half, new Pair(0, 0)); // 4 qtr
																						
				break;
				
			case 4:
				tileCentre(offset,4,half);
				
				tile(offset, half, new Pair(half - 1, half - 1)); // 1 qtr
				
				tile(new Pair(offset.x + half, offset.y), half, 
						new Pair(0, half - 1)); // 2 qtr
																						
				tile(new Pair(offset.x, offset.y + half), half, 
						new Pair(half - 1, 0)); // 3 qtr
																						
				tile(new Pair(offset.x + half, offset.y + half), 
						half, new Pair(def.x - half, def.y - half));// 4 qtr

				break;
			default:
			}
		}
	}

	/**
	 * makeTromino() makes the graphical object PolyTromino, which is a Polygon
	 * in an L shape. The points of the Polygon are offset according to its
	 * location on the board
	 * 
	 * @param ret
	 * @return
	 */
	private PolyTromino makeTromino(Tromino ret) {
		PolyTromino polyT = null;

		// a multiplication factor to size the polygons to suit the board
		int xF = (board.getWidth() - 10) / side;
		int yF = (board.getHeight() - 30) / side;

		int offX = ret.getPoint().getX();
		int offY = side - ret.getPoint().getY();
		// get the points for the polygon depending on its type (orientation of the L-shape)
		switch (ret.type) {
		case UL:
			polyT = new PolyTromino(offX * xF, (offX + 1) * xF, (offX + 1) * xF, (offX - 1) * xF, (offX - 1) * xF,
					offX * xF, offY * yF, offY * yF, (offY - 1) * yF, (offY - 1) * yF, (offY + 1) * yF,
					(offY + 1) * yF);
			break;
		case UR:
			polyT = new PolyTromino(offX * xF, offX * xF, (offX + 1) * xF, (offX + 1) * xF, (offX - 1) * xF,
					(offX - 1) * xF, offY * yF, (offY + 1) * yF, (offY + 1) * yF, (offY - 1) * yF, (offY - 1) * yF,
					offY * yF);
			break;
		case LL:
			polyT = new PolyTromino(offX * xF, offX * xF, (offX - 1) * xF, (offX - 1) * xF, (offX + 1) * xF,
					(offX + 1) * xF, offY * yF, (offY - 1) * yF, (offY - 1) * yF, (offY + 1) * yF, (offY + 1) * yF,
					offY * yF);
			break;
		case LR:
			polyT = new PolyTromino(offX * xF, (offX - 1) * xF, (offX - 1) * xF, (offX + 1) * xF, (offX + 1) * xF,
					offX * xF, offY * yF, offY * yF, (offY + 1) * yF, (offY + 1) * yF, (offY - 1) * yF,
					(offY - 1) * yF);
			break;
		default:
		}
		return polyT;
	}

	/**
	 * get the quadrant of the board that the deficient tile is located in
	 * 
	 * @param side
	 * @param def
	 * @return
	 */
	private int quarter(int side, Pair def) {
		if (def.x < side / 2 && def.y < side / 2)
			return 1;
		else if (def.x < side && def.y < side / 2)
			return 2;
		else if (def.x < side / 2 && def.y < side)
			return 3;
		else if (def.x < side && def.y < side)
			return 4;
		else
			return -1;
	}

	/**
	 * Get the Tromino for the two by two board
	 * offset is the amount to offset to get the location in the original board
	 * 
	 * loc is the location of the deficient tile
	 *  
	 * @param offset
	 * @param side
	 * @param loc
	 * @return
	 */
	private Tromino twoByTwo(Pair offset, Pair loc) {
		Tromino ret = null;

		Pair realLoc = new Pair(offset.x + 1, offset.y + 1);

		if (loc.x == 0 && loc.y == 0) {

			ret = new Tromino(realLoc, tromType.UR);

		} else if (loc.x == 1 && loc.y == 0) {

			ret = new Tromino(realLoc, tromType.UL);

		} else if (loc.x == 0 && loc.y == 1) {

			ret = new Tromino(realLoc, tromType.LR);

		} else if (loc.x == 1 && loc.y == 1) {

			ret = new Tromino(realLoc, tromType.LL);

		}
		return ret;
	}
	
	/**
	 * provide the tromino tile for the centre of quadrants
	 * where that tromino is used for the deficient tiles of
	 * the quadrants where the original deficient tile is
	 * not located
	 * 
	 * @param offset
	 * @param qtr
	 * @param half
	 */
	private void tileCentre(Pair offset, int qtr, int half){
		Tromino cen = null;
		Pair loc = new Pair(offset.getX() + half, offset.getY() + half);
		
		switch(qtr){
		case 1:
			cen = new Tromino(loc, tromType.UR);
			break;
		case 2:
			cen = new Tromino(loc, tromType.UL);
			break;
		case 3:
			cen = new Tromino(loc, tromType.LR);
			break;
		case 4:
			cen = new Tromino(loc, tromType.LL);
			break;
			default:
		}
		PolyTromino polyTrom = makeTromino(cen);
		board.getCanvas().addToList(polyTrom);
		System.out.println(cen);
	}

	/**
	 * Ask the user for the side length of the board
	 * 
	 * @return
	 */
	
	private int askForSide() {
		reader = new Scanner(System.in);
		int side = 0;
		System.out.println("Enter the side length (a power of 2):");
		side = reader.nextInt();
		if (!testTwoPower(side)) {
			System.out.println("Side length is not a power of 2");
			System.exit(0);
		}

		return side;
	}

	/**
	 * testTwoPower checks if the length of side entered is a power of 2
	 * 
	 * @param side
	 * @return
	 */
	private boolean testTwoPower(int side) {
		long n = (long) side;
		if (n == 0)
			return false;
		else
			return (n & (n - 1)) == 0;
	}

	////////////////////////////////////////////////////////////////////////
	/**
	 * Pair class is used to store and use a point (x, y) in a grid
	 * 
	 * @author Glen
	 *
	 */
	public class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;

		}

		public Pair() {
			System.out.println("Details of the deficient tile:");
			System.out.println("Enter the x location:");
			this.x = reader.nextInt();
			System.out.println("Enter the y location:");
			this.y = reader.nextInt();
			reader.close();
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}

	/////////////////////////////////////////////////////////////////////
	/**
	 * Tromino class
	 * is the tromino identified by a location and a type
	 * UL, UR, LL or LR
	 * 
	 */

	public class Tromino {
		Pair point;
		tromType type;

		public Tromino(Pair point, tromType type) {
			this.point = point;
			this.type = type;
		}

		public String toString() {
			return type + " " + point.toString();
		}

		public Pair getPoint() {
			return point;
		}

		public tromType getType() {
			return type;
		}

	}

	/**
	 * main() method to run the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		DrawTrominoes start = new DrawTrominoes();
		start.run();

	}

}
