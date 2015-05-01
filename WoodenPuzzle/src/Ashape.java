public class Ashape extends Shape {

	public Ashape(int x, int y, Grid g) {
		this.grid = g;
		blocks = new Block[1];
		blocks[0] = new Block(x, y, this);
	}

	public boolean MoveUp() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean MoveDown() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean MoveLeft() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean MoveRight() {
		// TODO Auto-generated method stub
		return false;
	}

}
