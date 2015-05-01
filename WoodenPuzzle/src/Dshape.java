public class Dshape extends Shape {

	public Dshape(int x, int y, Grid g) {
		this.grid = g;
		blocks = new Block[4];
		blocks[0] = new Block(x, y, this);
		blocks[1] = new Block(x + 1, y, this);
		blocks[2] = new Block(x, y + 1, this);
		blocks[3] = new Block(x + 1, y + 1, this);

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
