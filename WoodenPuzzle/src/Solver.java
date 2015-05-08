import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solver {
	private Queue<ConfigurationNode> queue;
	private HashMap<BitSet, Integer> hashtable;
	private Grid grid;

	public Solver() {
		// Initialize
		grid = new Grid(5, 4);
		queue = new LinkedList<ConfigurationNode>();
		hashtable = new HashMap<BitSet, Integer>();
		ConfigurationNode winNode = null;
		Eshape[] empties = new Eshape[2];
		int emptyIndex = 0;

		// Get User Input
		Scanner input = new Scanner(System.in);
		System.out
				.println("Input the initial configuration as a string of letters with no white space: ");
		String initialConfig = input.nextLine();
		

		// Set Config
		grid.SetConfigString(initialConfig.split(" "));
		ConfigurationNode initialNode = new ConfigurationNode(grid.GetConfig(),
				null, -1, -1, -1);

		// Add to queue and hashtable
		queue.add(initialNode);
		hashtable.put(initialNode.configuration, 1);
		
		grid.PrintGrid();

		// Start Queue Loop
		while (!queue.isEmpty()) {
			// Grab and set next Config
			ConfigurationNode currentNode = queue.poll();
			grid.SetConfig(currentNode.configuration);

			// Check Win and Grab Empties
			emptyIndex = 0;
			empties = new Eshape[2];
			for (Shape s : grid.shapes) {
				if (s.letter.equals("D")) {
					if (((Dshape) s).isWin()) {
						winNode = currentNode;
						queue.clear();
					}
				} else if (s.letter.equals("E")) {
					empties[emptyIndex++] = (Eshape) s;
				}
			}
			
			if(winNode != null)
				break;
			
			System.out.println("New CONFIG FROM QUEUEEUEUEUE");

			// Do Moves
			for (Eshape e : empties) {
				if (e != null) {
					//grid.PrintGrid();
					for (Shape s : e.adjacent()) {
						if (s != null) {
							//System.out.println(s.letter);
							int x = s.blocks[0].x;
							int y = s.blocks[0].y;
							grid.SetConfig(currentNode.configuration);
							if (s.MoveUp()) {
								System.out.println("Move Up - "+s.letter);
								if (!hashtable.containsKey(grid.GetConfig())) {
									//System.out.println("Succes");
									ConfigurationNode newNode = new ConfigurationNode(
											grid.GetConfig(), currentNode, 0,
											x, y);
									queue.add(newNode);
									hashtable.put(newNode.configuration, 1);
									if(s.letter.equals("D"))
										grid.PrintGrid();
								}
								grid.SetConfig(currentNode.configuration);
								if(s.letter.equals("D"))
									grid.PrintGrid();
							}
							if (s.MoveDown()) {
								System.out.println("Move Down - "+s.letter);
								if (!hashtable.containsKey(grid.GetConfig())) {
									//System.out.println("Success");
									ConfigurationNode newNode = new ConfigurationNode(
											grid.GetConfig(), currentNode, 1,
											x, y);
									queue.add(newNode);
									hashtable.put(newNode.configuration, 1);
									if(s.letter.equals("D"))
										grid.PrintGrid();
								}
								grid.SetConfig(currentNode.configuration);
								if(s.letter.equals("D"))
									grid.PrintGrid();
							}
							if (s.MoveLeft()) {
								System.out.println("Move Left - "+s.letter);
								if (!hashtable.containsKey(grid.GetConfig())) {
									//System.out.println("Success");
									ConfigurationNode newNode = new ConfigurationNode(
											grid.GetConfig(), currentNode, 2,
											x, y);
									queue.add(newNode);
									hashtable.put(newNode.configuration, 1);
									if(s.letter.equals("D"))
										grid.PrintGrid();
								}
								grid.SetConfig(currentNode.configuration);
								if(s.letter.equals("D"))
									grid.PrintGrid();
							}
							if (s.MoveRight()) {
								System.out.println("Move Right - "+s.letter);
								if (!hashtable.containsKey(grid.GetConfig())) {
									//System.out.println("Success");
									ConfigurationNode newNode = new ConfigurationNode(
											grid.GetConfig(), currentNode, 3,
											x, y);
									queue.add(newNode);
									hashtable.put(newNode.configuration, 1);
									if(s.letter.equals("D"))
										grid.PrintGrid();
								}
								grid.SetConfig(currentNode.configuration);
								if(s.letter.equals("D"))
									grid.PrintGrid();
							}
						}
					}
				}
			}
		}

		// Traverse
		ArrayList<String> list = new ArrayList<String>();
		ConfigurationNode current = winNode;
		while (current != null) {
			switch (current.move) {
			case 0:
				list.add("Move Piece at (" + current.pieceX + ","
						+ current.pieceY + ")one unit Up");
				break;

			case 1:
				list.add("Move Piece at (" + current.pieceX + ","
						+ current.pieceY + ")one unit Down");
				break;

			case 2:
				list.add("Move Piece at (" + current.pieceX + ","
						+ current.pieceY + ")one unit Left");
				break;

			case 3:
				list.add("Move Piece at (" + current.pieceX + ","
						+ current.pieceY + ")one unit Right");
				break;
			}
			current = current.previousConfig;

		}
		// Print
		for (int i = list.size() - 1; i >= 0; i--)
			System.out.println(list.get(i));

		System.out.println(list.size());
	}

	protected class ConfigurationNode {
		ConfigurationNode previousConfig;
		BitSet configuration;
		int move; // Up = 0, Down = 1, Left = 2, Right = 3
		int pieceX, pieceY;

		public ConfigurationNode(BitSet c, ConfigurationNode n, int m, int x,
				int y) {
			configuration = c;
			previousConfig = n;
			move = m;
			pieceX = x;
			pieceY = y;
		}
	}

	public static void main(String args[]) {
		new Solver();
	}
}
