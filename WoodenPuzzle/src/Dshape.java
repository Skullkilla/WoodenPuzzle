public class Dshape extends Shape {

	public Dshape(int x, int y, Grid g) {
		letter = "D";
		this.grid = g;
		blocks = new Block[4];
		blocks[0] = new Block(x, y, this);
		blocks[1] = new Block(x + 1, y, this);
		blocks[2] = new Block(x, y + 1, this);
		blocks[3] = new Block(x + 1, y + 1, this);

	}
	
	public boolean NotOutOfBounds(int x, int y){
		if (x < 0 || y<0 || x>=grid.width || y>=grid.height)
			return false;
		 return true;
	}
//comment
	@Override
	public boolean MoveUp() {
		int x1 = blocks[0].x;
		int y1= blocks[0].y;
		int x2 = blocks[1].x;
		int y2 = blocks[1].y;
		if ( NotOutOfBounds(x1,y1-1) 
				&& grid.grid[x1][y1-1].parent.letter.equals("E") 
				&& NotOutOfBounds(x2,y2-1) 
				&& grid.grid[x2][y2-1].parent.letter.equals("E")){
			
			Eshape tmp1= (Eshape)grid.grid[x1][y1-1].parent;
			Eshape tmp2 = (Eshape)grid.grid[x2][y2-1].parent;
			blocks[0].y -= 1;
			blocks[1].y -= 1;
			blocks[2].y -= 1;
			blocks[3].y -= 1;
			tmp1.blocks[0].y = y1+1;
			tmp2.blocks[0].y = y2+1;
			grid.grid[x1][y1-1] = blocks[0];
			grid.grid[x2][y1-1] = blocks[1];
			grid.grid[x1][y1]   = blocks[2];
			grid.grid[x2][y2]   = blocks[3];
			grid.grid[x1][y1+1] = tmp1.blocks[0];
			grid.grid[x2][y2+1] = tmp2.blocks[0];
			return true;
		}
		
		return false;
	}

	@Override
	public boolean MoveDown() {
		int x1 = blocks[2].x;
		int y1 = blocks[2].y;
		int x2 = blocks[3].x;
		int y2 = blocks[3].y;
		if ( NotOutOfBounds(x1,y1+1) 
				&& grid.grid[x1][y1+1].parent.letter.equals("E") 
				&& NotOutOfBounds(x2,y2+1) 
				&& grid.grid[x2][y2+1].parent.letter.equals("E")){
			
			Eshape tmp1= (Eshape)grid.grid[x1][y1+1].parent;
			Eshape tmp2 = (Eshape)grid.grid[x2][y2+1].parent;
			blocks[0].y += 1;
			blocks[1].y += 1;
			blocks[2].y += 1;
			blocks[3].y += 1;
			tmp1.blocks[0].y = y1-1;
			tmp2.blocks[0].y = y2-1;
			grid.grid[x1][y1+1] = blocks[0];
			grid.grid[x2][y1+1] = blocks[1];
			grid.grid[x1][y1]   = blocks[2];
			grid.grid[x2][y2]   = blocks[3];
			grid.grid[x1][y1-1] = tmp1.blocks[0];
			grid.grid[x2][y1-1] = tmp2.blocks[0];
			return true;
		}
		
		return false;
	}

	@Override
	public boolean MoveLeft() {
		int x1 = blocks[0].x;
		int y1= blocks[0].y;
		int x2 = blocks[2].x;
		int y2 = blocks[2].y;
		if ( NotOutOfBounds(x1-1,y1) 
				&& grid.grid[x1-1][y1].parent.letter.equals("E") 
				&& NotOutOfBounds(x2-1,y2) 
				&& grid.grid[x2-1][y2].parent.letter.equals("E")){
			
			Eshape tmp1= (Eshape)grid.grid[x1-1][y1].parent;
			Eshape tmp2 = (Eshape)grid.grid[x2-1][y2].parent;
			blocks[0].x -= 1;
			blocks[1].x -= 1;
			blocks[2].x -= 1;
			blocks[3].x -= 1;
			tmp1.blocks[0].x = x1+1;
			tmp2.blocks[0].x = x2+1;
			grid.grid[x1-1][y1] = blocks[0];
			grid.grid[x1][y1] = blocks[1];
			grid.grid[x2-1][y2] = blocks[2];
			grid.grid[x2][y2] = blocks[3];
			grid.grid[x1+1][y1] = tmp1.blocks[0];
			grid.grid[x2+1][y2] = tmp2.blocks[0];
			return true;
		}
		
		return false;
	}

	@Override
	public boolean MoveRight() {
		int x1 = blocks[1].x;
		int y1= blocks[1].y;
		int x2 = blocks[3].x;
		int y2 = blocks[3].y;
		if ( NotOutOfBounds(x1+1,y1) 
				&& grid.grid[x1+1][y1].parent.letter.equals("E") 
				&& NotOutOfBounds(x2+1,y2) 
				&& grid.grid[x2+1][y2].parent.letter.equals("E")){
			
			Eshape tmp1= (Eshape)grid.grid[x1+1][y1].parent;
			Eshape tmp2 = (Eshape)grid.grid[x2+1][y2].parent;
			blocks[0].x += 1;
			blocks[1].x += 1;
			blocks[2].x += 1;
			blocks[3].x += 1;
			tmp1.blocks[0].x = x1-1;
			tmp2.blocks[0].x = x2-1;
			grid.grid[x1][y1]   = blocks[0];
			grid.grid[x1+1][y1] = blocks[1];
			grid.grid[x2][y2]   = blocks[2];
			grid.grid[x2+1][y2] = blocks[3];
			grid.grid[x1-1][y1] = tmp1.blocks[0];
			grid.grid[x2-1][y2] = tmp2.blocks[0];
			return true;
		}
		return false;
	}
	
	//THIS NEEDS IMPLEMENTATION
	public boolean isWin() {
		if (blocks[0].x == 3 && blocks[0].y == 1) {
			return true;
		}
		return false;
	}

}
