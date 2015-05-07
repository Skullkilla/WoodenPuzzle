//Empty Shape


//Author: Garrison Price
public class Eshape extends Shape {

	public Eshape(int x, int y, Grid g) {
		this.grid = g;
		blocks = new Block[1];
		blocks[0] = new Block(x, y, this);
		letter = "E";
	}
	
	//Eshape has no need to move so none of these will ever return true
	@Override
	public boolean MoveUp() {
		return false;
	}

	@Override
	public boolean MoveDown() {
		return false;
	}

	@Override
	public boolean MoveLeft() {
		return false;
	}

	@Override
	public boolean MoveRight() {
		return false;
	}
	
	//Returns all shapes directly adjacent
	public Shape[] adjacent() {
		Shape[] shapes = new Shape[4]; // Max is 4 shapes
		int shapeIndex = 0;
		
		
		if(blocks[0].y-1 >= 0)
			shapes[shapeIndex++] = grid.grid[blocks[0].x][blocks[0].y-1].parent; // Up
		if(blocks[0].y+1 < grid.height)
			shapes[shapeIndex++] = grid.grid[blocks[0].x][blocks[0].y+1].parent; // Down
		if(blocks[0].x-1 >= 0)
			shapes[shapeIndex++] = grid.grid[blocks[0].x-1][blocks[0].y].parent; // Left
		if(blocks[0].x+1 < grid.width)
			shapes[shapeIndex++] = grid.grid[blocks[0].x+1][blocks[0].y].parent; // Right
		
		//System.arraycopy(shapes, 0, shapes, 0, shapeIndex);
		
		return shapes;
	}
}
