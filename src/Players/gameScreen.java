package Players;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class gameScreen extends JPanel implements ActionListener, Runnable {
	Dude p;
	public Image img;
	Timer time;
	int v = 172;
	Thread animator;

	boolean a = false;
	boolean done2 = false;

	public gameScreen() {
		p = new Dude();
		addKeyListener(new AL());
		setFocusable(true);
		ImageIcon i = new ImageIcon("src\\Players\\Samurai\\IMG\\Background_desert.jpg");
		img = i.getImage();
		img = img.getScaledInstance(1200, 365, java.awt.Image.SCALE_SMOOTH);

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
		g2d.drawImage(p.getImage(), p.getPosX(), v, null);

		if (p.getDirX() == -1) {
			g2d.drawImage(img, 685 - p.getNx2(), 0, null);
			g2d.drawImage(p.getImage(), p.getPosX(), v, null);
		}
		/******************************************************/
		ArrayList<Bullet> bullets = Dude.getBullets();
        for (int i = 0; i < bullets.size(); i++) {
            //This is how to get a current element in an arrayList
             Bullet m = (Bullet) bullets.get(i);//draw:
            g2d.drawImage(m.getImage(), m.getX(), m.getY(), null);

        }
        g2d.setFont(new Font("SanSerif", Font.BOLD, 24));
        g2d.setColor(Color.BLUE);
        g2d.drawString("Ammo left: " + p.getAmmo(), 500, 50);
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
			v-=2;
		if (v == 100)
			h = true;
		if (h == true && v <= 172) {
			v+=2;
			if (v == 172) {
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