import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Solver {
	private Queue<ConfigurationNode> queue;
	private HashMap<BitSet,Integer> hashtable;
	private Grid grid;
	
	public Solver () {
		//Initialize
		grid = new Grid(5,4);
		queue = new LinkedList<ConfigurationNode>();
		hashtable = new HashMap<BitSet, Integer>();
		ConfigurationNode winNode = null;
		Eshape[] empties = new Eshape[2];
		int emptyIndex = 0;
		
		
		//Get User Input
		Scanner input = new Scanner(System.in);
		System.out.println("Input the initial configuration as a string of letters with no white space: ");
		String initialConfig = input.nextLine();
		
		//Set Config
		grid.SetConfigString(initialConfig.split("(.)"));
		ConfigurationNode initialNode = new ConfigurationNode(grid.GetConfig(),null, -1, -1, -1);
		
		//Add to queue and hastable
		queue.add(initialNode);
		hashtable.put(initialNode.configuration, 1);
		
		//Start Queue Loop
		exitWhile:
		while(!queue.isEmpty()) {
			//Grab and set next Config
			ConfigurationNode currentNode = queue.poll();
			grid.SetConfig(currentNode.configuration);
			
			//Check Win and Grab Empties
			emptyIndex = 0;
			for(Shape s : grid.shapes) {
				if(s.letter.equals("D")) {
					if(((Dshape)s).isWin()) {
						winNode = currentNode;
						break exitWhile;
					}
				} else if(s.letter.equals("E")) {
					empties[emptyIndex++] = (Eshape)s;
				}
			}
			
			//Do Moves
			for(Eshape e : empties) {
				for(Shape s : e.adjacent()) {
					int x = s.blocks[0].x;
					int y = s.blocks[0].y;
					if(s.MoveUp()) {
						if(!hashtable.containsKey(grid.GetConfig())) {
							ConfigurationNode newNode = new ConfigurationNode(grid.GetConfig(), currentNode, 0, x, y);
							queue.add(newNode);
							hashtable.put(newNode.configuration, 1);
							grid.SetConfig(currentNode.configuration);
						}
					}
					if(s.MoveDown()) {
						if(!hashtable.containsKey(grid.GetConfig())) {
							ConfigurationNode newNode = new ConfigurationNode(grid.GetConfig(), currentNode, 1, x, y);
							queue.add(newNode);
							hashtable.put(newNode.configuration, 1);
							grid.SetConfig(currentNode.configuration);
						}
					}
					if(s.MoveLeft()) {
						if(!hashtable.containsKey(grid.GetConfig())) {
							ConfigurationNode newNode = new ConfigurationNode(grid.GetConfig(), currentNode, 2, x, y);
							queue.add(newNode);
							hashtable.put(newNode.configuration, 1);
							grid.SetConfig(currentNode.configuration);
						}
					}
					if(s.MoveDown()) {
						if(!hashtable.containsKey(grid.GetConfig())) {
							ConfigurationNode newNode = new ConfigurationNode(grid.GetConfig(), currentNode, 3, x, y);
							queue.add(newNode);
							hashtable.put(newNode.configuration, 1);
							grid.SetConfig(currentNode.configuration);
						}
					}
				}
			}
		}
		
		//Traverse
		
		//Print
	}
	
	protected class ConfigurationNode {
		ConfigurationNode previousConfig;
		BitSet configuration;
		int move; // Up = 0, Down = 1, Left = 2, Right = 3
		int pieceX, pieceY;
		
		public ConfigurationNode(BitSet c, ConfigurationNode n, int m, int x, int y) {
			configuration = c;
			previousConfig = n;
			move = m;
			pieceX = x;
			pieceY = y;
		}
	}
}
