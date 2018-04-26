import java.awt.Graphics;

public class Fist extends Weapon {

	public Fist(Grid grid) {
		super(grid);
		setDamage(5);
	}

	public void draw(Graphics g){
		g.drawRect(10, 10, 10, 10);
	}

	

}
