import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Monster extends Player{
	public Monster(Grid grid){
		super(grid, (int)(Math.random() * (grid.getColumns() - 2) + 1), (int)(Math.random() * (grid.getRows() - 2) + 1));
		
	}
	
	public void follow(Player p){
		if(getXPos() < p.getXPos()){
			right();
		}
		if(getXPos() > p.getXPos()){
			left();
		}
		if(getYPos() < p.getYPos()){
			down();
		}
		if(getYPos() > p.getYPos()){
			up();
		}
	}
	
	public boolean nextTo(Player p){
		if(getXPos() == p.getXPos()){
			if(getYPos() - 1 == p.getYPos())
				return true;
			else if(getYPos() + 1 == p.getYPos())
				return true;
			else return false;
		}
		else if(getYPos() == p.getYPos()){
			if(getXPos() - 1 == p.getXPos())
				return true;
			else if(getXPos() == p.getXPos())
				return true;
			else return false;
		}
		else return false;
	}
	
	public void interact(Player p){
		p.setHealth(p.getHealth() - 15);
	}
	
	public void draw(Graphics myBuffer){
		myBuffer.drawImage(new ImageIcon(Monster.class.getResource("Satan.png")).getImage(), getX(), getY(), getWidth(), getHeight(), null);
		drawHealth(myBuffer);
	}
	
	public Monster getThis(){
		return this;
	}
	
}
