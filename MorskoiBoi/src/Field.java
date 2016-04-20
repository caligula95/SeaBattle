import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;

public class Field extends JPanel {

	private Timer tmDraw;
	private Image fon, paluba, ubit, ranen, end1, end2, bomba;
	private JButton start, quit;
	private Game myGame;
	private int mX, mY;
	
	public Field() {
		addMouseListener(new myMouse1());
		addMouseMotionListener(new myMouse2());
		setFocusable(true); 
		// ������� ������ ����� ����
		myGame = new Game();
		myGame.start();
		try {
			fon = ImageIO.read(new File("java\\fon.png"));
			paluba = ImageIO.read(new File("java\\paluba.png"));
			ranen = ImageIO.read(new File("java\\ranen.png"));
			ubit = ImageIO.read(new File("java\\ubit.png"));
			end1 = ImageIO.read(new File("java\\end1.png"));
			end2 = ImageIO.read(new File("java\\end2.png"));
			bomba = ImageIO.read(new File("java\\bomba.png"));
		} catch (Exception ex) {
		}
		// �������, ����������� � ��������� ������
		// ��� ��������� �������� ����
		tmDraw = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				repaint();
			}
		});
		tmDraw.start();
		setLayout(null);
		start = new JButton();
		start.setText("����� ����");
		start.setForeground(Color.BLUE);
		start.setFont(new Font("serif", 0, 30));
		start.setBounds(130, 450, 200, 80);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myGame.start();
			}
		});
		add(start);

		quit = new JButton();
		quit.setText("�����");
		quit.setForeground(Color.RED);
		quit.setFont(new Font("serif", 0, 30));
		quit.setBounds(530, 450, 200, 80);
		quit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		add(quit);
	}


	public void paintComponent(Graphics gr) {
		// �������� �������� ����
		super.paintComponent(gr);
		gr.drawImage(fon, 0, 0, 900, 600, null);
		gr.setFont(new Font("serif", 3, 40));
		gr.setColor(Color.BLUE);
		gr.drawString("���������", 150, 50);
		gr.drawString("�����", 590, 50);
		
		// ��������� ������� ����� ����������
		// � ������ �� ��������� ��������
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				// ������� ���� ����������
				if (myGame.masComputer[i][j] != 0) {
					// ���� ��� �������� ������ �������
					if ((myGame.masComputer[i][j] >= 8) && (myGame.masComputer[i][j] <= 11)) {
						gr.drawImage(ranen, 100 + j * 30, 100 + i * 30, 30, 30, null);
					}
					// ���� ��� ������ ��������� ��������� �������
					else if (myGame.masComputer[i][j] >= 15) {
						gr.drawImage(ubit, 100 + j * 30, 100 + i * 30, 30, 30, null);
					}
					// ���� ��� �������
					if (myGame.masComputer[i][j] >= 5) {
						gr.drawImage(bomba, 100 + j * 30, 100 + i * 30, 30, 30, null);
					}
				}
				// ������� ���� ������
				if (myGame.masPlayer[i][j] != 0) {
					// ���� ��� ������ �������
					if ((myGame.masPlayer[i][j] >= 1) && (myGame.masPlayer[i][j] <= 4)) {
						gr.drawImage(paluba, 500 + j * 30, 100 + i * 30, 30, 30, null);
					}
					// ���� ��� �������� ������ �������
					else if ((myGame.masPlayer[i][j] >= 8) && (myGame.masPlayer[i][j] <= 11)) {
						gr.drawImage(ranen, 500 + j * 30, 100 + i * 30, 30, 30, null);
					}
					// ���� ��� ������ ��������� ��������� �������
					else if (myGame.masPlayer[i][j] >= 15) {
						gr.drawImage(ubit, 500 + j * 30, 100 + i * 30, 30, 30, null);
					}
					// ���� ��� �������
					if (myGame.masPlayer[i][j] >= 5) {
						gr.drawImage(bomba, 500 + j * 30, 100 + i * 30, 30, 30, null);
					}
				}
			}
		}
		gr.setColor(Color.RED); // ������� ����
		// ���� ������ ���� ������ �������� ���� ����������
		if ((mX > 100) && (mY > 100) && (mX < 400) && (mY < 400)) {
			// ���� �� ����� ���� � ��� ������
			if ((myGame.endGame == 0) && (myGame.compTurn == false)) {
				// ��������� ����� ������ � �������
				int i = (mY - 100) / 30;
				// ��������� ����� �������� � ������ � �������
				int j = (mX - 100) / 30;
				// ���� ������ �������� ��� ��������
				if (myGame.masComputer[i][j] <= 4)
					// ������ ������� � ��������
					gr.fillRect(100 + j * 30, 100 + i * 30, 30, 30);
			}
		}
		// ��������� ����� �������� ���� �� ����� �����
		gr.setColor(Color.BLUE);
		for (int i = 0; i <= 10; i++) {
			// ��������� ����� ����� �������� ���� ����������
			gr.drawLine(100 + i * 30, 100, 100 + i * 30, 400);
			gr.drawLine(100, 100 + i * 30, 400, 100 + i * 30);
			// ��������� ����� ����� �������� ���� ��������
			gr.drawLine(500 + i * 30, 100, 500 + i * 30, 400);
			gr.drawLine(500, 100 + i * 30, 800, 100 + i * 30);
		}

		gr.setFont(new Font("serif", 0, 20));
		gr.setColor(Color.RED);
		// �������� ���� � ���� ����� � ������ �� ������� �����
		for (int i = 1; i <= 10; i++) {
			// ����� ����
			gr.drawString("" + i, 73, 93 + i * 30);
			gr.drawString("" + i, 473, 93 + i * 30);
			// ����� ����
			gr.drawString("" + (char) ('A' + i - 1), 78 + i * 30, 93);
			gr.drawString("" + (char) ('A' + i - 1), 478 + i * 30, 93);
		}
		// ����� ����������� ����� ���� - ��� ��������� ����
		if (myGame.endGame == 1) // ���� ������� �����
		{
			gr.drawImage(end1, 300, 200, 300, 100, null);
		} else if (myGame.endGame == 2) // ���� ������� ���������
		{
			gr.drawImage(end2, 300, 200, 300, 100, null);
		}
	}
	public class myMouse1 implements MouseListener {
		public void mouseClicked(MouseEvent e) {
		}

		// ��� ������� ������ ����
		public void mousePressed(MouseEvent e) {
			// ���� ������� ��������� ������� ����� �������� ����
			if ((e.getButton() == 1) && (e.getClickCount() == 1)) {
				// �������� ������� ���������� ������� ����
				mX = e.getX();
				mY = e.getY();
				// ���� ������ ���� ������ �������� ���� ����������
				if ((mX > 100) && (mY > 100) && (mX < 400) && (mY < 400)) {
					// ���� �� ����� ���� � ��� ������
					if ((myGame.endGame == 0) && (myGame.compTurn == false)) {
						// ��������� ����� ������ � �������
						int i = (mY - 100) / 30;
						// ��������� ����� �������� � ������ � �������
						int j = (mX - 100) / 30;
						// ���� ������ �������� ��� ��������
						if (myGame.masComputer[i][j] <= 4)
							// ���������� �������
							myGame.playerShot(i, j);
					}
				}
			}
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
	}

	public class myMouse2 implements MouseMotionListener {
		public void mouseDragged(MouseEvent e) {
		}

		// ��� ����������� ������� ����
		public void mouseMoved(MouseEvent e) {
			// �������� ���������� �������
			mX = e.getX();
			mY = e.getY();
			// ���� ������ � ������� ���� ������
			if ((mX >= 100) && (mY >= 100) && (mX <= 400) && (mY <= 400))
				setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			else
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}

}
