import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Main");
		frame.setLocation(0, 0);
		frame.setSize(Main.FRAMEX, Main.FRAMEY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main obj = new Main(frame);
		frame.setContentPane(new Inventory(new Player(new Grid()), obj));
		frame.setVisible(true);
	}

}
