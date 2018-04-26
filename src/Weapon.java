import java.awt.Graphics;

public abstract class Weapon extends Item {

	private int damage;
	
	public Weapon(Grid grid) {
		super(grid);
		damage = 1;
	}

	@Override
	public void interact(Player p){
		p.interact(this);
	}

	public int getDamage(){
		return damage;
	}
	
	protected void setDamage(int damage){	
		this.damage = damage;
	}
	
	public abstract void draw(Graphics g);
}
