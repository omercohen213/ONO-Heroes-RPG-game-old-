package Players;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class gameScreen extends JPanel implements ActionListener, Runnable {
	Dude p;
	public Image img;
	Timer time;
	Thread animator;

	boolean a = false;
	boolean done2 = false;

	public static void startGame(Player player) {
		JFrame Gameframe = new JFrame();
		Gameframe.add(new gameScreen(player));
		Gameframe.setTitle("2-D Test Game");
		Gameframe.setSize(1000, 1000);
		Gameframe.setResizable(false);
		Gameframe.setVisible(true);
		Gameframe.setLocationRelativeTo(null);

	}

	public gameScreen(Player player) {
		
		p = new Dude(player);
		addKeyListener(new AL());
		setFocusable(true);
		ImageIcon i = new ImageIcon("src\\Images\\Background_desert.jpg");
		img = i.getImage();
		img = img.getScaledInstance(1200, 1000, java.awt.Image.SCALE_SMOOTH);
		time = new Timer(2, this);
		time.start();
	}

	public void actionPerformed(ActionEvent e) {
		ArrayList<Bullet> bullets = Dude.getBullets();
        for (int i = 0; i < bullets.size(); i++) {
                //This is how to get a current element in an arrayList
                //similar to x[2] in a normal array
                 Bullet m = (Bullet) bullets.get(i);//draw:
                 if (m.getVisible() == true) {
                         m.move();
                 }
                 else bullets.remove(i);
        }

        p.move();
        repaint();
	}

	public void paint(Graphics g) {
		if (p.getDirY() == 1 && done2 == false) {
			done2 = true;
			animator = new Thread(this);
			animator.start();
		}

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		//System.out.println(p.getX());

		if ((p.getX() - 590) % 2400 == 0)
			p.setNx(0);

		g2d.drawImage(img, 685 - p.getNx2(), 0, null);
		if (p.getX() > 590) {
			g2d.drawImage(img, 685 - p.getNx(), 0, null);
		}
		g2d.drawImage(p.getImage(), (int)p.getPosX(), (int)p.getY(), null);

		if (p.getDirX() < 0) {
			g2d.drawImage(img, 685 - p.getNx2(), 0, null);
			g2d.drawImage(p.getImage(), (int)p.getPosX(), (int)p.getY(), null);
		}
		/******************************************************/
		ArrayList<Bullet> bullets = Dude.getBullets();
        for (int i = 0; i < bullets.size(); i++) {
            //This is how to get a current element in an arrayList
             Bullet m = (Bullet) bullets.get(i);//draw:
            g2d.drawImage(m.getImage(), (int)m.getX(), (int)m.getY(), null);

        }
        g2d.setFont(new Font("SanSerif", Font.BOLD, 24));
        g2d.setColor(Color.BLUE);
        g2d.drawString("Ammo left: " + p.getAmmo(), 0, 50);
	}

	private class AL extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			p.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			p.keyPressed(e);
		}
	}

	boolean h = false;
	boolean done = false;

	public void cycle() {
		if (h == false)
			p.setY(p.getY() - 2);
		if (p.getY() == 550)
			h = true;
		if (h == true && p.getY() <= 650) {
			p.setY(p.getY() + 2);
			if (p.getY() == 650) {
				done = true;
			}
		}
	}

	public void run() {

		long beforeTime, timeDiff, sleep;

		beforeTime = System.currentTimeMillis();

		while (done == false) {

			cycle();

			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = 10 - timeDiff;

			if (sleep < 0)
				sleep = 2;
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}

			beforeTime = System.currentTimeMillis();
		}
		done = false;
		h = false;
		done2 = false;
	}

}