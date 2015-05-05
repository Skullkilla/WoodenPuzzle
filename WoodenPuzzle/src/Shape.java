public abstract class Shape {
	public Block[] blocks;
	public Grid grid;
	public String letter;

	public abstract boolean MoveUp();

	public abstract boolean MoveDown();

	public abstract boolean MoveLeft();

	public abstract boolean MoveRight();
	
	public boolean isUpperLeft(Block b) {
		return b.x == blocks[0].x && b.y == blocks[0].y;
	}

}
