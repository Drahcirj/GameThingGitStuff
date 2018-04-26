import java.awt.Color;
import java.awt.Graphics;

public class Grid {
	
	private int gridXSize;
	private int gridYSize;
	private int blocksPerRow;
	private int blocksPerColumn;
	private Block[][] grid;
	private int blockWidth, blockHeight;
	private Map map;
	private int xPos, yPos;
	
	public Grid(){
		gridXSize = 1000;
		gridYSize = 1000;
		blocksPerRow = 8;
		blocksPerColumn = 8;
		blockWidth = gridXSize / blocksPerRow;
		blockHeight = gridYSize / blocksPerColumn;
		grid = new Block[blocksPerColumn][blocksPerRow];
		for(int yPosition = 0; yPosition < grid.length; yPosition++){
			for(int xPosition = 0; xPosition < grid[yPosition].length; xPosition++){
				grid[yPosition][xPosition] = new Block(xPosition * blockWidth, yPosition * blockHeight, blockWidth, blockHeight, randomBoolean(10));
			}
		}
		xPos = 0;
		yPos = 0;
		map = null;
	}
	
	public Grid(Map map, int xPos, int yPos){
		gridXSize = 1000;
		gridYSize = 1000;
		blocksPerRow = 8;
		blocksPerColumn = 8;
		blockWidth = gridXSize / blocksPerRow;
		blockHeight = gridYSize / blocksPerColumn;
		grid = new Block[blocksPerColumn][blocksPerRow];
		for(int yPosition = 0; yPosition < grid.length; yPosition++){
			for(int xPosition = 0; xPosition < grid[yPosition].length; xPosition++){
				grid[yPosition][xPosition] = new Block(xPosition * blockWidth, yPosition * blockHeight, blockWidth, blockHeight, randomBoolean(10));
			}
		}
		this.map = map;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	private boolean randomBoolean(int chance){
		int i = (int)(Math.random() * chance + 1);
		if(i == 1) return true;
		else return false;
	}
	
	public void reset(){
		for(Block[] row : grid){
			for(Block block : row){
				if(block.getFill() == Block.Fill.Item);
				else block.fill(randomBoolean(10));
			}
		}
	}
	
	public int getXSize(){
		return gridXSize;
	}
	
	public int getYSize(){
		return gridYSize;
	}
	
	public int getRows(){
		return blocksPerColumn;
	}
	
	public int getColumns(){
		return blocksPerRow;
	}
	
	public int getBlockWidth(){
		return blockWidth;
	}
	
	public int getBlockHeight(){
		return blockHeight;
	}
	
	public int getXPos(){
		return xPos;
	}
	
	public int getYPos(){
		return yPos;
	}
	
	public Map getMap(){
		return map;
	}
	
	public void setXPos(int xPos){
		this.xPos = xPos;
	}
	
	public void setYPos(int yPos){
		this.yPos = yPos;
	}
	
	public void setBlock(int xcoord, int ycoord, Block block){
		grid[ycoord][xcoord] = block;
	}
	
	public Block getBlock(int x, int y){
		return grid[y][x];
	}
	
	public void swapBlock(int x1, int y1, int x2, int y2){
		Block temp = grid[y2][x2];
		
		grid[y2][x2].setX(grid[y1][x1].getX());
		grid[y2][x2].setY(grid[y1][x1].getY());
		grid[y2][x2] = grid[y1][x1];
		
		grid[y1][x1].setX(temp.getX());
		grid[y1][x1].setY(temp.getY());
		grid[y1][x1] = temp;
	}
	
	public void draw(Graphics myBuffer){
		myBuffer.setColor(Color.white);
		myBuffer.fillRect(0, 0, gridXSize, gridYSize);
		for(Block[] row : grid){
			for(Block block : row){
				block.draw(myBuffer);
			}
		}
	}

}
