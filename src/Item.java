
public abstract class Item extends Block{
	private Grid grid;
	private int xPos, yPos;
	
	public Item(Grid grid){
		super(Block.Fill.Item);
		this.grid = grid;
		xPos = 0;
		yPos = 0;
		setWidth(grid.getBlockWidth());
		setHeight(grid.getBlockHeight());
		setX(xPos * getWidth());
		setY(yPos * getHeight());
	}
	
	public int getXPos(){
		return xPos;
	}
	
	public int getYPos(){
		return yPos;
	}
	
	public void setXPos(int xPos){
		this.xPos = xPos;
	}
	
	public void setYPos(int yPos){
		this.yPos = yPos;
	}
	
	public void setGrid(Grid grid){
		this.grid = grid;
	}
	
	public Grid getGrid(){
		return grid;
	}
	
	public abstract void interact(Player p);
}
