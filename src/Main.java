import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

public class Main extends JPanel{

	public static final int FRAMEX = 1000, FRAMEY = 1000, BLOCKSPERROW = 8;
	private static final int BLOCKSIDE = FRAMEX / BLOCKSPERROW;
	private static JFrame frame;
	private Block[] grid = new Block[(int)Math.pow(BLOCKSPERROW, 2)];
	private BufferedImage myImage;
	private Graphics myBuffer;
	private Grid grid2;
	private Player player;
	private Timer t;
	private int timeCount;
	private Map map;
	private int roomX, roomY;
	private MapSwitch door;
	private Monster satan;
	private static Main obj;
	private Inventory playerInv;
	private KeyListener kl;

	public boolean randomBoolean(int chance){
		int i = (int)(Math.random() * chance + 1);
		if(i == 1) return true;
		else return false;
	}

	public Main getMain(){
		return this;
	}
	
	public Timer getTimer(){
		return t;
	}

	public Player getPlayer(){
		return player;
	}

	public JFrame getFrame(){
		return frame;
	}

	public void start(){
		frame.setContentPane(this);
//		t.start();
		frame.setVisible(true);
		t.start();
		addKeyListener(kl);
		setFocusable(true);	
//		frame.setFocusableWindowState(true);
	}

	public Main(JFrame frame){
		this.frame = frame;
		myImage = new BufferedImage(FRAMEX, FRAMEY, BufferedImage.TYPE_INT_RGB);
		myBuffer = myImage.getGraphics();
		setLayout(null);

		map = new Map();

		roomX = 2;
		roomY = 2;

		grid2 = map.getRoom(roomX, roomY);

		player = new Player(grid2);

		playerInv = player.getInventory();
		add(playerInv);
		playerInv.setSize(700, 700);
		playerInv.setLocation(FRAMEX - (playerInv.getWidth() + 20), 20);
		playerInv.setVisible(false);

		satan = new Monster(grid2);

		door = new MapSwitch(map.getRoom((int)(Math.random() * map.getColumns()), (int)(Math.random() * map.getRows())));

		//		System.out.println(grid2.getBlock(0,0));
		//		System.out.println(door);

		t = new Timer(100, new Listener());
		t.start();
		timeCount = 0;
		
		kl = new KeyListener();
		addKeyListener(kl);
		setFocusable(true);
	}
	private class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			timeCount++;
			if(player.getHealth() < player.MAXHEALTH)
				player.setHealth(player.getHealth() + 1);
			//			if(satan.getHealth() < satan.MAXHEALTH)
			//				satan.setHealth(satan.getHealth() + 1);
			update();
			if(timeCount % 2 == 0){
				if(player.getGrid() == satan.getGrid()){
					satan.follow(player);
					if(satan.nextTo(player) && timeCount % ((int)(Math.random() * 10 + 1)) == 0)
						satan.interact(player);
				}
			}
			//			System.out.println(player.getXPos() + "," + player.getYPos() + "\t" + satan.getXPos() + "," + satan.getYPos());
			grid2.draw(myBuffer);
			//			satan.draw(myBuffer);

			if(player.getHealth() <= 0){
				myBuffer.setColor(Color.red);
				myBuffer.drawString("You Lose!", FRAMEX / 2, FRAMEY / 2);
				t.stop();
			}
			if(satan.getHealth() <= 0){
				myBuffer.setColor(Color.RED);
				myBuffer.drawString("You Win", FRAMEX / 2, FRAMEY / 2);
				satan.setRoom(map.getRoom((int)(Math.random() * map.getColumns()), (int)(Math.random() * map.getRows())));
				//				satan.setRoom(map.getRoom(2, 1));
				satan.setHealth(satan.MAXHEALTH);
			}
			repaint();
		}
	}

	private boolean wdown = false, adown = false, sdown = false, ddown = false;

	private class KeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			System.out.println("something happened");
			if(e.getKeyCode() == KeyEvent.VK_W){
				wdown = true;
				//				player.up();
			}
			if(e.getKeyCode() == KeyEvent.VK_A){
				adown = true;
				//				player.left();
			}
			if(e.getKeyCode() == KeyEvent.VK_S){
				sdown = true;
				//				player.down();
			}
			if(e.getKeyCode() == KeyEvent.VK_D){
				ddown = true;
				//				player.right();
			}
			if(e.getKeyCode() == KeyEvent.VK_SPACE){
				//				player.setColor(new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255)));
				player.switchPic();
			}
			if(e.getKeyCode() == KeyEvent.VK_R){
				map.reset();
			}
			if(e.getKeyCode() == KeyEvent.VK_SHIFT){
				if(!t.isRunning()){
					player.setHealth(1);
					t.start();
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_E){
//				if(t.isRunning()){
//					//					frame.setContentPane(playerInv);
////					frame.setContentPane(playerInv);
////					t.stop();
////					removeKeyListener(kl);
////					frame.setVisible(true);
////					playerInv.setVisible(true);
//					
//
//				}
//				else{
					//					frame.setContentPane(obj);
//					t.start();
//					playerInv.setVisible(false);
//					getMain().setVisible(true);
//				}
//				if(getMain().isVisible()){
//					getMain().setVisible(false);
//				}
//				else{
//					getMain().setVisible(true);
//				}
				if(playerInv.isVisible())
					playerInv.setVisible(false);
				else
					playerInv.setVisible(true);
			}
		}

		public void keyReleased(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_W){
				wdown = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_A){
				adown = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_S){
				sdown = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_D){
				ddown = false;
			}
		}
	}


	public void update(){
		if(wdown){
			if(player.getYPos() > 0)
				player.up();
			else if(player.changeRoomVertical(map.getRoom(roomX, roomY - 1))){
				roomY--;
				grid2 = map.getRoom(roomX, roomY);

			}
		}
		if(adown){
			if(player.getXPos() > 0)
				player.left();
			else if(player.changeRoomHorizontal(map.getRoom(roomX - 1, roomY))){
				roomX--;
				grid2 = map.getRoom(roomX, roomY);

			}
		}
		if(sdown){
			if(player.getYPos() < grid2.getRows() - 1)
				player.down();
			else if(player.changeRoomVertical(map.getRoom(roomX, roomY + 1))){
				roomY++;
				grid2 = map.getRoom(roomX, roomY);

			}
		}
		if(ddown){
			if(player.getXPos() < grid2.getColumns() - 1)
				player.right();
			else if(player.changeRoomHorizontal(map.getRoom(roomX + 1, roomY))){
				roomX++;
				grid2 = map.getRoom(roomX, roomY);
			}
		}
	}

	public void paintComponent(Graphics g){
		g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
	}

	private void display(JFrame frame){

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Main");
		frame.setLocation(0, 0);
		frame.setSize(FRAMEX, FRAMEY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main m = new Main(frame);
		frame.setContentPane(m);
		frame.setVisible(true);
		
		
	}

}
