import javax.swing.*;
import java.awt.*;

public class Block {
//	private boolean filled;
	private int myX, myY, myWidth, myHeight;
	public enum Fill{
		True,
		False,
		Item
	}
	private Fill filled;
	
	public Block(){
		filled = Fill.False;
		myX = 0;
		myY = 0;
		myWidth = 50;
		myHeight = 50;
	}
	
	public Block(boolean filled){
		this.filled = (filled) ? Fill.True : Fill.False;
		myX = 0;
		myY = 0;
		myWidth = 50;
		myHeight = 50;
	}
	
	public Block(Fill filled){
		this.filled = filled;
		myX = 0;
		myY = 0;
		myWidth = 50;
		myHeight = 50;
	}
	
	public Block(int x, int y, int width, int height, boolean filled){
		myX = x;
		myY = y;
		myWidth = width;
		myHeight = height;
		this.filled = filled ? Fill.True : Fill.False;
	}
	
	public Block(int x, int y, int width, int height, Fill fill){
		myX = x;
		myY = y;
		myWidth = width;
		myHeight = height;
		this.filled = fill;
	}
	
	public void fill(boolean fill){
		filled = fill ? Fill.True : Fill.False;
	}
	
	public void fill(Fill fill){
		filled = fill;
	}
	
	public boolean isFilled(){
		return filled == Fill.True;
	}
	
	public Fill getFill(){
		return filled;
	}
	
	public void setX(int x){
		myX = x;
	}
	
	public int getX(){
		return myX;
	}
	
	public void setY(int y){
		myY = y;
	}
	
	public int getY(){
		return myY;
	}
	
	public void setWidth(int width){
		myWidth = width;
	}
	
	public int getWidth(){
		return myWidth;
	}
	
	public void setHeight(int height){
		myHeight = height;
	}
	
	public int getHeight(){
		return myHeight;
	}
	
	public void interact(Player p){
		
	}
	
	public void draw(Graphics myBuffer){
		if(filled == Fill.True){
			myBuffer.drawImage(new ImageIcon(Block.class.getResource("/rock.JPG")).getImage(), myX, myY, myWidth, myHeight, null);
		}
		else{
//			myBuffer.setColor(Color.LIGHT_GRAY.brighter());
			myBuffer.setColor(new Color(107, 69, 56));
			myBuffer.fillRect(myX, myY, myWidth, myHeight);
//			myBuffer.setColor(Color.black);
//			myBuffer.drawRect(myX, myY, myWidth, myHeight);
		}
//		myBuffer.fillRect(myX, myY, myWidth, myHeight);
//		myBuffer.setColor(Color.black);
//		myBuffer.drawRect(myX, myY, myWidth, myHeight);
	}
	
	public Block getThis(){
		return this;
	}
	
	public boolean isPlayer(){
		return false;
	}
}
