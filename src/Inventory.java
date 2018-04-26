import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Inventory extends JPanel {
	private int x, y;
	private Player p;
	//	private Item[] items = new Item[12];
	private List<Item> items;
	private int slot;
	private JPanel itemPanel, playerPanel;
	private JButton[] buttons = new JButton[12];
	private JButton weapon;
	private int weaponIndex;
	private JLabel floorLabel;

	public Inventory(Player p){
		x = 0;
		y = 0;
		this.p = p;
		slot = 0;
		setLayout(new GridLayout(1,2));

		playerPanel = new JPanel(new FlowLayout());
		playerPanel.add(new JLabel(p.getPic()));

		items = new ArrayList<Item>();

		weapon = new JButton("Weapon");
		weapon.setBackground(Color.white);
		weapon.addActionListener(new WeaponListener());
		weapon.setFocusable(false);
		playerPanel.add(weapon);

		weaponIndex = -1;

		floorLabel = new JLabel("Floor: " + p.getGrid().getMap().getFloor());
		playerPanel.add(floorLabel);

		add(playerPanel);

		itemPanel = new JPanel(new GridLayout(4,3));
		for(int i = 0; i < buttons.length; i++){
			buttons[i] = new JButton("Empty");
			buttons[i].setBackground(Color.WHITE);
			int a = i;
			buttons[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//					System.out.println("stuff");
					if(weapon.getIcon() == null){
						weapon.setIcon(buttons[a].getIcon());
						try{
							p.setWeapon((Weapon)items.get(a));
							buttons[a].setBackground(Color.yellow);
							weaponIndex = a;
						} catch(IndexOutOfBoundsException f){

						}
						
					}
				}
			});
			buttons[i].setFocusable(false);
		}
		for(JButton button : buttons){
			itemPanel.add(button);
		}
		add(itemPanel);
		setFocusable(false);

	}

	private class WeaponListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			p.setWeapon(null);
			weapon.setIcon(null);
			buttons[weaponIndex].setBackground(Color.white);
			floorLabel.setText("Floor: " + p.getGrid().getMap().getFloor());
		}

	}


	public boolean add(Item i){
		//		items[slot++] = i;
		if(items.size() < 12){
			items.add(i);
			buttons[items.size() - 1].setIcon(i.getPic());
			return true;
		}
		else
			return false;
	}

	public Item getItem(int slot){
		//		return items[slot];
		return items.get(slot);
	}

	public Item removeItem(int slot){
		//		Item i = items[slot];
		//		items[slot] = null;
		//		return i;
		Item i = items.remove(slot);
		for(int x = 0; x < items.size(); x++){
			try{
				buttons[x].setIcon(i.getPic());
			} catch(IndexOutOfBoundsException e){
				System.out.println(e.getStackTrace());
				buttons[x].setIcon(null);
			}
		}

		return i;
	}

}
