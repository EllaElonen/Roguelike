package game;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.color.*;
import java.awt.font.*;

public class ControlPanel extends JPanel {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 623;

	public static BufferedImage candy;
	public static BufferedImage player;
	public static BufferedImage pumpkin;
	public static BufferedImage background;
	public static BufferedImage ghost0;
	public static BufferedImage ghost1;

	public static BufferedImage wall;// går inte att döpa till "rougelike-wall"
	private Wall w = new Wall();
	private Ghost ghostA = new Ghost(100, 200);
	private Ghost ghostB = new Ghost(300, 350);
	private Ghost ghostC = new Ghost(400, 200);
	private Candy candyA = new Candy(100, 50);
	private Candy candyB = new Candy(500, 50);
	private Pumpkin pumpkinA= new Pumpkin (100,400);
	private Pumpkin pumpkinB= new Pumpkin (400,450);

	private Player p = new Player();
	private List<Item> items = new ArrayList<Item>();

	public ControlPanel() {
	}

	static {// initialize static resources->pictures
		try {
			candy = ImageIO.read(ControlPanel.class.getResource("candy.png"));
			player = ImageIO.read(ControlPanel.class.getResource("player.png"));
			pumpkin = ImageIO.read(ControlPanel.class.getResource("pumpkin.png"));
			wall = ImageIO.read(ControlPanel.class.getResource("wall.png"));
			background = ImageIO.read(ControlPanel.class.getResource("background.jpeg"));
			ghost0 = ImageIO.read(ControlPanel.class.getResource("ghost0.png"));
			ghost1 = ImageIO.read(ControlPanel.class.getResource("ghost1.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void stepAction() {
		for (Item i : items) {
			i.step();
		}
	}

	private void createItems() {
		items.add(ghostA);
		items.add(ghostB);
		items.add(ghostC);
		items.add(candyA);
		items.add(candyB);
		items.add(pumpkinA);
		items.add(pumpkinB);
	}

	private void action() {
		createItems();
		MouseAdapter l = new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				// hero moves together with mouse
				int x = e.getX();
				int y = e.getY();
				p.moveTo(x, y);

			}
		};
		this.addMouseListener(l);
		this.addMouseMotionListener(l);

		Timer timer = new Timer();
		int interval = 10;
		timer.schedule(new TimerTask() {
			public void run() {
				// enterAction();
				stepAction();
				// outOfBoundsAction();
				repaint();
			}
		}, interval, interval);
	}

	private void paintWalls(Graphics g) {
		for (int i = 0; i < 12; i++) {
			g.drawImage(w.image, i * 50, 0, null);
		}
		for (int i = 0; i < 11; i++) {
			g.drawImage(w.image, 0, i * 50, null);
		}
		for (int i = 0; i < 12; i++) {
			g.drawImage(w.image, i * 50, 550, null);
		}
		for (int i = 0; i < 12; i++) {
			g.drawImage(w.image, 550, i * 50, null);
		}
		for (int i = 1; i < 4; i++) {
			g.drawImage(w.image, 250, i * 50, null);
		}
		for (int i = 0; i < 12; i++) {
			if (i != 2 && i != 8) {
				g.drawImage(w.image, i * 50, 200, null);
			}
		}

	}

	private void paintGhosts(Graphics g) {
		for (Item i : items) {
			g.drawImage(i.image, i.x, i.y, null);
		}
	}

	public void paintPlayer(Graphics g) {
		g.drawImage(p.image, p.x, p.y, null);
	}

	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
		paintWalls(g);
		paintGhosts(g);
		paintPlayer(g);
	}

	class KeyLyss extends KeyAdapter {
		public void keyPressed(KeyEvent kev) {
			System.out.println("yes");
			switch (kev.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				p.moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
				p.moveLeft();
				break;
			case KeyEvent.VK_UP:
				p.moveUp();
				break;
			case KeyEvent.VK_DOWN:
				p.moveDown();
				break;
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Rougelike");

		ControlPanel game = new ControlPanel();
		frame.add(game);
		frame.setSize(WIDTH, HEIGHT);
		frame.setAlwaysOnTop(true);// zhongdian
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.action();
	}

}
