import java.awt.Graphics;

import javax.swing.ImageIcon;

public abstract class Item extends Block{
	private Grid grid;
	private int xPos, yPos;
	private ImageIcon pic;
	
	public Item(Grid grid){
		super(Block.Fill.Item);
		this.grid = grid;
		xPos = 0;
		yPos = 0;
		setWidth(grid.getBlockWidth());
		setHeight(grid.getBlockHeight());
		setX(xPos * getWidth());
		setY(yPos * getHeight());
		pic = new ImageIcon("icons/Trapdoor.png");
	}
	
	public Item(Grid grid, int x, int y){
		super(Block.Fill.Item);
		this.grid = grid;
		xPos = x;
		yPos = y;
		setWidth(grid.getBlockWidth());
		setHeight(grid.getBlockHeight());
		setX(xPos * getWidth());
		setY(yPos * getHeight());
		pic = new ImageIcon("icons/Trapdoor.png");
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
	
	public ImageIcon getPic(){
		return pic;
	}
	
	public void setPic(ImageIcon pic){
		this.pic = pic;
	}
	
	public abstract void interact(Player p);
	
	public abstract void draw(Graphics g);
	
	public String toString(){
		return "item";
	}
}
