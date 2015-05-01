
public class Grid {
	public Block[][] grid;
	public int width, height;
	public HuffmanTree tree;
	public Shape[] shapes = {new Ashape(4,0,this), new Ashape(3,1,this), new Ashape(3,2,this), new Ashape(4,3,this),
							 new Bshape(0,0,this), new Bshape(2,0,this), new Bshape(0,3,this), new Bshape(2,3,this),
							 new Cshape(2,1,this), new Dshape(0,1,this), new Eshape(4,1,this), new Eshape(4,2,this)};	
	
	
	public Grid(int width, int height){
		this.width = width;
		this.height = height;
		
		for(Shape s : shapes) {
			for(Block b : s.blocks) {
				grid[b.x][b.y] = b;
			}
		}
	}
	
	
	public int GetConfig(){
		return 0;
	}
	
	
	public void SetConfig(int code){
	
	}
	

			

}
