import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GraphicMenu extends JPanel{
	
	private int x, y, width, height;
	
	public GraphicMenu(){
		x = 0;
		y = 0;
		width = 50;
		height = 50;
		add(new JButton("Something"));
	}
	
	public void draw(Graphics g){
		
	}
}
