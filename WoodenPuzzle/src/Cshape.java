public class Cshape extends Shape {

	public Cshape(int x, int y, Grid g) {
		letter = "C";
		this.grid = g;
		blocks = new Block[2];
		blocks[0] = new Block(x, y, this);
		blocks[1] = new Block(x, y + 1, this);
	}

	public boolean NotOutBounds(int x, int y) {
		if (x < 0 || y < 0 || grid.height < 0 || grid.width < 0)
			return false;
		return true;
	}

	@Override
	public boolean MoveUp() {
		int x = blocks[0].x;
		int y = blocks[0].y;
		if (NotOutBounds(x, y - 1)
				&& grid.grid[x][y - 1].parent.letter.equals("E")
				&& grid.grid[x + 1][y - 1].parent.letter.equals("E")) {
			Eshape tmp = (Eshape) grid.grid[x][y - 1].parent;
			Eshape tmp2 = (Eshape) grid.grid[x + 1][y - 1].parent;
			blocks[0].y -= 1;
			blocks[1].y -= 1;
			grid.grid[x][y - 1] = blocks[0];
			grid.grid[x + 1][y - 1] = blocks[1];
			grid.grid[x][y] = tmp.blocks[0];
			grid.grid[x + 1][y] = tmp.blocks[1];
			tmp.blocks[0].y = y;
			tmp2.blocks[0].y = y;
			return true;
		}
		return false;
	}

	@Override
	public boolean MoveDown() {
		int x = blocks[0].x;
		int y = blocks[0].y;
		if (NotOutBounds(x, y + 1)
				&& grid.grid[x][y + 1].parent.letter.equals("E")
				&& grid.grid[x + 1][y + 1].parent.letter.equals("E")) {
			Eshape tmp = (Eshape) grid.grid[x][y + 1].parent;
			Eshape tmp2 = (Eshape) grid.grid[x + 1][y + 1].parent;
			blocks[0].y += 1;
			blocks[1].y += 1;
			grid.grid[x][y + 1] = blocks[0];
			grid.grid[x + 1][y + 1] = blocks[1];
			grid.grid[x][y] = tmp.blocks[0];
			grid.grid[x + 1][y] = tmp2.blocks[0];
			tmp.blocks[0].y = y;
			tmp2.blocks[0].y = y;
			return true;
		}
		return false;
	}

	@Override
	public boolean MoveLeft() {
		int x = blocks[0].x;
		int y = blocks[0].y;
		if (NotOutBounds(x - 1, y)
				&& grid.grid[x - 1][y].parent.letter.equals("E")) {
			Eshape tmp = (Eshape) grid.grid[x - 1][y].parent;
			blocks[0].x -= 1;
			grid.grid[x - 1][y] = blocks[0];
			grid.grid[x][y] = tmp.blocks[0];
			tmp.blocks[0].x = x;
			return true;
		}
		return false;
	}

	@Override
	public boolean MoveRight() {
		int x = blocks[0].x;
		int y = blocks[0].y;
		if (NotOutBounds(x + 1, y)
				&& grid.grid[x + 1][y].parent.letter.equals("E")) {
			Eshape tmp = (Eshape) grid.grid[x + 1][y].parent;
			blocks[0].x += 1;
			grid.grid[x + 1][y] = blocks[0];
			grid.grid[x][y] = tmp.blocks[0];
			tmp.blocks[0].x = x;
			return true;
		}
		return false;

	}
}
