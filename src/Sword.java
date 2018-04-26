import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;


public class Sword extends Weapon {

	ImageIcon pic;
	
	public Sword(Grid grid) {
		super(grid);
		setDamage((int)(Math.random() * 14 + 7));
		
		if(getDamage() <= 9)
			pic = (new ImageIcon(Sword.class.getResource("swordWood.png")));
		else if(getDamage() <= 12)
			pic = (new ImageIcon(Sword.class.getResource("swordStone.png")));
		else if(getDamage() <= 15)
			pic = (new ImageIcon(Sword.class.getResource("swordIron.png")));
		else if(getDamage() <= 18)
			pic = (new ImageIcon(Sword.class.getResource("swordGold.png")));
		else
			pic = (new ImageIcon(Sword.class.getResource("swordDiamond.png")));
		setPic(pic);
//		pic = new ImageIcon("swordStone.png");
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(107, 69, 56));
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.drawImage(getPic().getImage(), getX(), getY(), getWidth(), getHeight(), null);
		
	}
	
	public String toString(){
		return "sword";
	}

}
