import java.util.Arrays;
import java.util.BitSet;

public class Grid {
	public Block[][] grid;
	public int width, height;
	public HuffmanTree<String> tree;
	public Shape[] shapes = { new Ashape(4, 0, this), new Ashape(3, 1, this),
			new Ashape(3, 2, this), new Ashape(4, 3, this),
			new Bshape(0, 0, this), new Bshape(2, 0, this),
			new Bshape(0, 3, this), new Bshape(2, 3, this),
			new Cshape(2, 1, this), new Dshape(0, 1, this),
			new Eshape(4, 1, this), new Eshape(4, 2, this) };

	public Grid(int width, int height) {
		this.width = width;
		this.height = height;

		SetupGrid();

		tree = new HuffmanTree<String>(String.class, GetConfigString().split(
				" "));
	}

	private String GetConfigString() {
		String config = "";
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (grid[x][y] != null
						&& grid[x][y].parent.isUpperLeft(grid[x][y])) {
					config += grid[x][y].parent.letter + " ";
				}
			}
		}
		return config;
	}

	public BitSet GetConfig() {
		return tree.encode(GetConfigString().split(" "));
	}

	public void SetConfig(BitSet code) {
		String[] shapeString = tree.decode(code);
		SetConfigString(shapeString);
	}

	public void SetConfigString(String[] shapeString) {
		shapes = new Shape[shapeString.length];

		grid = new Block[width][height];
		int shapeIndex = 0;
		
		for (String letter : shapeString) {
			//System.out.println(letter);
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					if (grid[x][y] == null) {
						if (letter.equals("A"))
							shapes[shapeIndex] = new Ashape(x, y, this);
						else if (letter.equals("B"))
							shapes[shapeIndex] = new Bshape(x, y, this);
						else if (letter.equals("C"))
							shapes[shapeIndex] = new Cshape(x, y, this);
						else if (letter.equals("D"))
							shapes[shapeIndex] = new Dshape(x, y, this);
						else if (letter.equals("E"))
							shapes[shapeIndex] = new Eshape(x, y, this);

						for (Block b : shapes[shapeIndex].blocks) {
							grid[b.x][b.y] = b;
						}
						x = height;
						y = width;
						shapeIndex++;
					}
				}
			}
			//PrintGrid();
		}
		
		//PrintGrid();

	}

	public void PrintGrid() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(grid[j][i] + " ");
			}
			System.out.print("\n");
		}
		
		System.out.println("---------");
	}

	private void SetupGrid() {
		grid = new Block[width][height];

		for (Shape s : shapes) {
			for (Block b : s.blocks) {
				grid[b.x][b.y] = b;
			}
		}
	}
}
