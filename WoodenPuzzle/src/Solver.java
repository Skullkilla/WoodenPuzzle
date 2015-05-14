import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * B B A D C A E A E B B A - 116 moves
 * A B B C E A B B D A E A - 7 moves
 * E B D B A E B C A B A A - 9 moves
 */

public class Solver {
	private Queue<ConfigurationNode> queue;
	private HashMap<Configuration, Integer> hashtable;
	private Grid grid;

	public Solver() {
		// Initialize
		grid = new Grid(5, 4);
		queue = new LinkedList<ConfigurationNode>();
		hashtable = new HashMap<Configuration, Integer>();
		ConfigurationNode winNode = null;
		Eshape[] empties = new Eshape[2];
		int emptyIndex = 0;

		// Get User Input
		Scanner input = new Scanner(System.in);
		System.out
				.println("Input the initial configuration as a string of letters separated by a space: ");
		String initialConfig = input.nextLine();
		
		long timer = System.currentTimeMillis();
		// Set Config
		grid.SetConfigString(initialConfig.split(" "));
		ConfigurationNode initialNode = new ConfigurationNode(grid.GetConfig(),
				null, -1, -1, -1);

		// Add to queue and hashtable
		queue.add(initialNode);
		hashtable.put(new Configuration(initialNode.configuration), 1);

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
					//System.out.println("WIn CHECK MOTHER FUCKER");
					//grid.PrintGrid();
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
			
			// Do Moves
			for (Eshape e : empties) {
				if (e != null) {
					//grid.PrintGrid();
					grid.SetConfig(currentNode.configuration);
					Shape[] adjacent = e.adjacent();
					for (Shape s : adjacent) {
						if (s != null) {
							int x = s.blocks[0].x;
							int y = s.blocks[0].y;
							if (s.MoveUp()) {
								Configuration newCon = new Configuration(grid.GetConfig());
								if (!hashtable.containsKey(newCon)) {
									ConfigurationNode newNode = new ConfigurationNode(
											newCon.configuration, currentNode, 0,
											x, y);
									queue.add(newNode);
									hashtable.put(newCon, 1);
								}
							}
							grid.SetConfig(currentNode.configuration);
							s = grid.grid[x][y].parent;
							if (s.MoveDown()) {
								Configuration newCon = new Configuration(grid.GetConfig());
								if (!hashtable.containsKey(newCon)) {
									ConfigurationNode newNode = new ConfigurationNode(
											newCon.configuration, currentNode, 1,
											x, y);
									queue.add(newNode);
									hashtable.put(newCon, 1);
								}
							}
							grid.SetConfig(currentNode.configuration);
							s = grid.grid[x][y].parent;
							if (s.MoveLeft()) {
								Configuration newCon = new Configuration(grid.GetConfig());
								if (!hashtable.containsKey(newCon)) {
									ConfigurationNode newNode = new ConfigurationNode(
											newCon.configuration, currentNode, 2,
											x, y);
									queue.add(newNode);
									hashtable.put(newCon, 1);
								}
							}
							grid.SetConfig(currentNode.configuration);
							s = grid.grid[x][y].parent;
							if (s.MoveRight()) {
								Configuration newCon = new Configuration(grid.GetConfig());
								if (!hashtable.containsKey(newCon)) {
									ConfigurationNode newNode = new ConfigurationNode(
											newCon.configuration, currentNode, 3,
											x, y);
									queue.add(newNode);
									hashtable.put(newCon, 1);
								}
							}
							grid.SetConfig(currentNode.configuration);
						}
					}
				}
			}
		}
		
		long totalTime = System.currentTimeMillis() - timer;

		// Traverse
		ArrayList<String> list = new ArrayList<String>();
		ConfigurationNode current = winNode;
		while (current != null) {
			grid.SetConfig(current.configuration);
			grid.PrintGrid();
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
		
		System.out.println("Total Time: "+totalTime);
	}

	protected class ConfigurationNode {
		ConfigurationNode previousConfig;
		Boolean[] configuration;
		int move; // Up = 0, Down = 1, Left = 2, Right = 3
		int pieceX, pieceY;

		public ConfigurationNode(Boolean[] c, ConfigurationNode n, int m, int x,
				int y) {
			configuration = c;
			previousConfig = n;
			move = m;
			pieceX = x;
			pieceY = y;
		}
	}
	
	protected class Configuration implements Comparable<Configuration>{
		Boolean[] configuration;
		
		public Configuration(Boolean[] c) {
			configuration = c;
		}
		
		@Override
		public int compareTo(Configuration o) {
			if(o.configuration.length != configuration.length)
				return -1;
			for(int i = 0; i < configuration.length; i++) {
				if(o.configuration[i].booleanValue() != configuration[i].booleanValue())
					return -1;
			}
			return 0;
		}
		
		@Override
		public boolean equals(Object o) {
			return compareTo((Configuration)o) == 0;
		}
		
		@Override
		public int hashCode() {
	        long hashVal = 17;
	           
	        for(int i=configuration.length-1; i >= 0; i--) 
	             hashVal = hashVal * 23 + configuration[i].hashCode(); 
	        hashVal &= 0x000000007FFFFFF;     
	        return (int) (hashVal);
		}
	}

	public static void main(String args[]) {
		new Solver();
	}
}
