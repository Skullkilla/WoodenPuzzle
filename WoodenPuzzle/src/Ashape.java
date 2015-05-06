import java.util.*;

public class Ashape extends Shape {

	public Ashape(int x, int y, Grid g) {
		this.grid = g;
		blocks = new Block[1];
		blocks[0] = new Block(x, y, this);
		letter = "A";
	}
	
	public boolean NotOutOfBounds(int x, int y){
		if (x < 0 || y<0 || x>grid.width || y>grid.height)
			return false;
		 return true;
			
	}

	public boolean MoveUp() {
		int x = blocks[0].x;
		int y = blocks[0].y;
		if ( NotOutOfBounds(x,y-1) && grid.grid[x][y-1].parent.letter.equals("E")){
			Eshape tmp = (Eshape)grid.grid[x][y-1].parent;
			blocks[0].y -= 1;
			tmp.blocks[0].y = y;
			grid.grid[x][y-1] = blocks[0];
			grid.grid[x][y] = tmp.blocks[0];
			return true;
		}
		
		return false;
	}

	public boolean MoveDown() {
		int x = blocks[0].x;
		int y = blocks[0].y;
		if (NotOutOfBounds(x,y+1) && grid.grid[x][y+1].parent.letter.equals("E")){
			Eshape tmp = (Eshape)grid.grid[x][y+1].parent;
			blocks[0].y += 1;
			tmp.blocks[0].y = y;
			grid.grid[x][y+1] = blocks[0];
			grid.grid[x][y] = tmp.blocks[0];
			return true;
		}
				
		return false;
	}

	public boolean MoveLeft() {
		int x = blocks[0].x;
		int y = blocks[0].y;
		if (NotOutOfBounds(x-1,y) && grid.grid[x-1][y].parent.letter.equals("E")){
			Eshape tmp = (Eshape)grid.grid[x-1][y].parent;
			blocks[0].x -= 1;
			tmp.blocks[0].x = x;
			grid.grid[x-1][y]= blocks[0];
			grid.grid[x][y]=tmp.blocks[0];
			return true;
		}
		
		return false;
	}

	public boolean MoveRight() {
		int x = blocks[0].x;
		int y = blocks[0].y;
		if (NotOutOfBounds(x+1,y) && grid.grid[x+1][y].parent.letter.equals("E")){
			Eshape tmp = (Eshape)grid.grid[x+1][y].parent;
			blocks[0].x += 1;
			tmp.blocks[0].x = x;
			grid.grid[x+1][y]= blocks[0];
			grid.grid[x][y]=tmp.blocks[0];
			return true;
		}
		
		return false;
	}

}
