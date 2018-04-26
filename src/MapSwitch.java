import java.awt.Graphics;
import javax.swing.ImageIcon;

public class MapSwitch extends Item{
	
	private ImageIcon pic = new ImageIcon(MapSwitch.class.getResource("Trapdoor.png"));
	
	public MapSwitch(Grid grid){
		super(grid);
		setXPos((int)(Math.random() * (getGrid().getColumns() - 2) + 1));
		setX(getXPos() * getWidth());
		setYPos((int)(Math.random() * (getGrid().getRows() - 2) + 1));
		setY(getYPos() * getHeight());
		grid.setBlock(getXPos(), getYPos(), this);
	}
	
	public void interact(Player p){
		p.getGrid().getMap().reset();
		p.getGrid().getMap().nextFloor();
		int xPos = getXPos();
		int yPos = getYPos();
		int x = getX();
		int y = getY();
		int width = getWidth();
		int height = getHeight();
//		getGrid().setBlock(getXPos(), getYPos(), new Block(getX(), getY(), getWidth(), getHeight(), false));
		setGrid(getGrid().getMap().getRoom((int)(Math.random() * (getGrid().getMap().getColumns())), (int)(Math.random() * (getGrid().getMap().getRows()))));
		setXPos((int)(Math.random() * (getGrid().getColumns() - 2) + 1));
		setX(getXPos() * getWidth());
		setYPos((int)(Math.random() * (getGrid().getRows() - 2) + 1));
		setY(getYPos() * getHeight());
		getGrid().setBlock(getXPos(), getYPos(), this);
		p.getGrid().setBlock(xPos, yPos, new Block(x, y, width, height, false));

	}
	
	public void draw(Graphics myBuffer){
		myBuffer.drawImage(pic.getImage(), getX(), getY(), getWidth(), getHeight(), null);
	}
}
