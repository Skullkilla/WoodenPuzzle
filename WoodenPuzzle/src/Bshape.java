public class Bshape extends Shape {

	public Bshape(int x, int y, Grid g) {
		this.grid = g;
		blocks = new Block[2];
		blocks[0] = new Block(x, y, this);
		blocks[1] = new Block(x + 1, y, this);
	}

	@Override
	public boolean MoveUp() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean MoveDown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean MoveLeft() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean MoveRight() {
		// TODO Auto-generated method stub
		return false;
	}

}
