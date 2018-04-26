import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Player extends Block{
	private Grid grid;
	private int xPos, yPos;
	private Color playerColor;
	private ImageIcon pic;
	private String image;
	private int health;
	public final int MAXHEALTH = 100;
	private Weapon weapon;
	private Inventory inv;
	// Color(107, 69, 56)

	public Player(Grid grid){
		super(true);
		this.grid = grid;
		xPos = 0;
		yPos = 0;
		setWidth(grid.getBlockWidth());
		setHeight(grid.getBlockHeight());
		setX(xPos * getWidth());
		setY(yPos * getHeight());
		fill(Block.Fill.Item);
		grid.setBlock(xPos, yPos, this);
		playerColor = Color.red;
		image = "Isaac.png";
		pic = new ImageIcon(Player.class.getResource(image));
		health = MAXHEALTH;
		//		weapon = new Fist(grid);
		weapon = null;
		inv = new Inventory(this);
	}

	public Player(Grid grid, int xPos, int yPos){
		super(true);
		this.grid = grid;
		this.xPos = xPos;
		this.yPos = yPos;
		setWidth(grid.getBlockWidth());
		setHeight(grid.getBlockHeight());
		setX(xPos * getWidth());
		setY(yPos * getHeight());
		fill(Block.Fill.Item);
		grid.setBlock(xPos, yPos, this);
		playerColor = Color.red;
		image = "Isaac.png";
		pic = new ImageIcon(Player.class.getResource(image));
		health = MAXHEALTH;
		//		weapon = new Fist(grid);
		weapon = null;
		inv = new Inventory(this);
	}

	public int getXPos(){
		return xPos;
	}

	public int getYPos(){
		return yPos;
	}

	public Grid getGrid(){
		return grid;
	}

	public void setColor(Color c){
		playerColor = c;
	}

	public ImageIcon getPic(){
		return pic;
	}

	public Weapon getWeapon(){
		return weapon;
	}

	public Inventory getInventory(){
		return inv;
	}

	public void up(){
		if(grid.getBlock(xPos, yPos - 1).getFill() == Block.Fill.False){
			grid.swapBlock(xPos, yPos, xPos, yPos - 1);
			yPos--;
			setY(yPos * getHeight());
		}
		else if(grid.getBlock(xPos, yPos - 1).getFill() == Block.Fill.Item){
			interact(grid.getBlock(xPos, yPos - 1));
		}
	}

	public void down(){
		if(grid.getBlock(xPos, yPos + 1).getFill() == Block.Fill.False){
			grid.swapBlock(xPos, yPos, xPos, yPos + 1);
			yPos++;
			setY(yPos * getHeight());
		}
		else if(grid.getBlock(xPos, yPos + 1).getFill() == Block.Fill.Item){
			interact(grid.getBlock(xPos, yPos + 1));
		}
	}

	public void left(){
		if(grid.getBlock(xPos - 1, yPos).getFill() == Block.Fill.False){
			grid.swapBlock(xPos, yPos, xPos - 1, yPos);
			xPos--;
			setX(xPos * getWidth());
		}
		else if(grid.getBlock(xPos - 1, yPos).getFill() == Block.Fill.Item){
			interact(grid.getBlock(xPos - 1, yPos));
		}
	}

	public void right(){
		if(grid.getBlock(xPos + 1, yPos).getFill() == Block.Fill.False){
			grid.swapBlock(xPos, yPos, xPos + 1, yPos);
			xPos++;
			setX(xPos * getWidth());
		}
		else if(grid.getBlock(xPos + 1, yPos).getFill() == Block.Fill.Item){
			interact(grid.getBlock(xPos + 1, yPos));
		}
	}

	public void interact(Block i){
		if(i.isPlayer()){
			Player p = (Player)i;
			try{
				p.setHealth(p.getHealth() - weapon.getDamage());
			} catch(NullPointerException e){
				p.setHealth(p.getHealth() - 5);
			}
		}
		else
			i.interact(this);
	}

	public int getHealth(){
		return health;
	}

	public void setHealth(int h){
		health = h;
	}

	protected void drawHealth(Graphics myBuffer){
		int healthIncrement = getWidth() / MAXHEALTH;
		myBuffer.setColor(Color.RED);
		myBuffer.fillRect(getX(), getY() - 5, getWidth() - ((MAXHEALTH - health) * healthIncrement), 7);
	}

	public boolean changeRoomVertical(Grid dest){
		if(!dest.getBlock(xPos, (yPos == 0) ? dest.getRows() - 1 : 0).isFilled()){
			grid.setBlock(xPos, yPos, new Block(getX(), getY(), getWidth(), getHeight(), false));
			if(yPos == 0){
				yPos = dest.getRows() - 1;
			}
			else{
				yPos = 0;
			}
			setY(yPos * getHeight());
			dest.setBlock(xPos, yPos, this);
			grid = dest;
			return true;
		}
		else return false;
	}

	public boolean changeRoomHorizontal(Grid dest){
		if(!dest.getBlock((xPos == 0) ? dest.getColumns() - 1 : 0, yPos).isFilled()){
			grid.setBlock(xPos, yPos, new Block(getX(), getY(), getWidth(), getHeight(), false));
			if(xPos == 0){
				xPos = dest.getColumns() - 1;
			}
			else{
				xPos = 0;
			}
			setX(xPos * getWidth());
			dest.setBlock(xPos, yPos, this);
			grid = dest;
			return true;
		}
		else return false;
	}

	public void draw(Graphics myBuffer){
		myBuffer.drawImage(pic.getImage(), getX(), getY(), getWidth(), getHeight(), null);
		try{
			myBuffer.drawImage(weapon.getPic().getImage(), getX() + getWidth() - 50, getY() + 50, 50, 50, null);
		} catch(NullPointerException e){
			
		}
		drawHealth(myBuffer);
	}

	public void switchPic(){
		if(image.equals("Isaac.png")){
			image = "Azazel.png";
		}
		else{
			image = "Isaac.png";
		}
		pic = new ImageIcon(Player.class.getResource(image));
	}

	public String toString(){
		return "player";
	}

	public boolean isPlayer(){
		return true;
	}

	public void setRoom(Grid dest){
		int xdest = (int)(Math.random() * (dest.getColumns() - 2) + 1);
		int ydest = (int)(Math.random() * (dest.getRows() - 2) + 1);
		grid.setBlock(xPos, yPos, new Block(getX(), getY(), getWidth(), getHeight(), false));
		xPos = xdest;
		yPos = ydest;
		setX(xPos * getWidth());
		setY(yPos * getHeight());
		dest.setBlock(xdest, ydest, this);
		grid = dest;
	}

	public void setWeapon(Weapon w){
		weapon = w;
	}
}
