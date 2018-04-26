import java.awt.Graphics;

public class Fist extends Weapon {

	public Fist(Grid grid) {
		super(grid);
		setDamage(5);
	}

	@Override
	public void interact(Player p) {
		// TODO Auto-generated method stub

	}

	public void draw(Graphics g){
		g.drawRect(10, 10, 10, 10);
	}

	

}
