import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Inventory extends JPanel {
	private int x, y;
	private Player p;
	private Item[] items = new Item[12];
	private int slot;
	private JPanel itemPanel, playerPanel;
	private JButton[] buttons = new JButton[12];
	private JButton exit;

	public Inventory(Player p){
		x = 0;
		y = 0;
		this.p = p;
		slot = 0;
		setLayout(new GridLayout(1,2));

		playerPanel = new JPanel(new FlowLayout());
		playerPanel.add(new JLabel(p.getPic()));

		exit = new JButton("Exit");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("something");
//				main.getFrame().setContentPane(main);
//				main.getFrame().setVisible(true);
//				main.getTimer().start();
//				main.start();
			}
		});
//		exit.addActionListener(new ExitListener());
//		playerPanel.add(exit);

		add(playerPanel);

		itemPanel = new JPanel(new GridLayout(4,3));
		for(int i = 0; i < buttons.length; i++){
			buttons[i] = new JButton("Empty");
			buttons[i].setBackground(Color.WHITE);
			buttons[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					System.out.println("stuff");
//					main.getFrame().setContentPane(main);
//					main.getFrame().setVisible(true);
//					main.getTimer().start();
					
				}
			});
		}
		for(JButton button : buttons){
			itemPanel.add(button);
		}
		add(itemPanel);
//		addKeyListener(new KeyAdapter(){
//			public void keyPressed(KeyEvent e){
//				if(e.getKeyCode() == KeyEvent.VK_E){
//					System.out.println("something");
//					main.getFrame().setContentPane(main);
//					main.getFrame().setVisible(true);
//				}
//			}
//		});
	}

	private class ExitListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("something");
			//main.getFrame().setContentPane(main);
			//main.getFrame().setVisible(true);
		}
	}

	public void add(Item i){
		items[slot++] = i;
	}

	public Item getItem(int slot){
		return items[slot];
	}

	public Item removeItem(int slot){
		Item i = items[slot];
		items[slot] = null;
		return i;
	}

	public void display(){

	}
}
