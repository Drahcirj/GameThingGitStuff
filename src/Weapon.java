import java.awt.Graphics;

import javax.swing.ImageIcon;

public abstract class Weapon extends Item {

	private int damage;
	private boolean pickedUp;
	
	public Weapon(Grid grid) {
		super(grid, (int)(Math.random() * (grid.getColumns() - 2) + 1), (int)(Math.random() * (grid.getRows() - 2) + 1));
		damage = 1;
		pickedUp = false;
		grid.setBlock(getXPos(), getYPos(), this);
		setPic(new ImageIcon("icons/rock.JPG"));
	}
	
	public Weapon(Grid grid, int x, int y){
		super(grid, x, y);
	}

	@Override
	public void interact(Player p){
		if(p.getInventory().add(this)){
			getGrid().setBlock(getXPos(), getYPos(), new Block(getX(), getY(), getWidth(), getHeight(), false));
		}
	}

	public int getDamage(){
		return damage;
	}
	
	public boolean inInventory(){
		return pickedUp;
	}
	
	protected void setDamage(int damage){	
		this.damage = damage;
	}
	
	public abstract void draw(Graphics g);
	
	public String toString(){
		return this.getClass().getSimpleName();
	}
}
