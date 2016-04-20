import java.awt.Container;

import javax.swing.JFrame;

public class Window extends JFrame{
	public Window() {
		Field pan = new Field();
		Container cont = getContentPane();
		cont.add(pan);
		setTitle("Игра \"Морской бой\"");
		setBounds(0, 0, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
}
