package Players;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import javax.swing.ImageIcon;

public class Dude {
	int dirX, dirY, nx, nx2, bulletDirX = 1;
	double posX, x, y;
	private Player player;
	Image ninja;
	ImageIcon runToRight;
	ImageIcon runToLeft;
	ImageIcon standToRight;
	ImageIcon standToLeft;
	ImageIcon attackToRight;
	ImageIcon attackToLeft;
	int ammo = 100;
	static ArrayList<Bullet> bullets;// Holds number of bullets on screen

	public Dude(Player player) {
		this.player = player;
		System.out.print(player.getClassName());
		runToRight = new ImageIcon("src\\Players\\" + player.getClassName() + "\\IMG\\move_right.png");
		runToLeft = new ImageIcon("src\\Players\\" + player.getClassName() + "\\IMG\\move_left.png");
		standToRight = new ImageIcon("src\\Players\\" + player.getClassName() + "\\IMG\\standing_right.png");
		standToLeft = new ImageIcon("src\\Players\\" + player.getClassName() + "\\IMG\\standing_left.png");
		attackToRight = new ImageIcon("src\\Players\\" + player.getClassName() + "\\IMG\\attack_right.png");
		attackToLeft = new ImageIcon("src\\Players\\" + player.getClassName() + "\\IMG\\attack_left.png");

		x = 100;
		y = 650;
		posX = 150;
		nx = 0;
		nx2 = 685;
		ninja = standToRight.getImage();
		ninja = ninja.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
		bullets = new ArrayList<Bullet>();// j
	}

	// Method to run when fired
	public void fire() {
		System.out.println(bullets.size() + " " + ammo);

		if (ammo > 0) {
			if (this.bulletDirX == 1) {
				ninja = attackToRight.getImage();
				ninja = ninja.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
			} else {
				ninja = attackToLeft.getImage();
				ninja = ninja.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
			}
			ammo--;
			Random r = new Random();
			//boolean rndDir=r.nextBoolean();
			double rndDir = r.nextDouble()* (r.nextBoolean() ? -1 : 1);
			Bullet b = new Bullet((posX + 100), (y + 30), bulletDirX, player, rndDir,this);
			bullets.add(b);
		}
	}

	public void move() {
		if (dirX > 0) {
			if (x < 1400) {
				x = x + dirX;
				nx2 = nx2 + dirX;
				nx = nx + dirX;
				if (posX < 150)
					posX += dirX;
			} else if (posX < 820)
				posX += dirX;
		} else if (dirX < 0) {
			if (x > 100) {
				x = x + dirX;
				nx2 = nx2 + dirX;
				nx = nx + dirX;
				if (posX > 540)
					posX += dirX;
			} else if (posX > -50)
				posX += dirX;
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getDirX() {
		return dirX;
	}

	public int getDirY() {
		return dirY;
	}

	public int getNx() {
		return nx;
	}

	public void setNx(int nx) {
		this.nx = nx;
	}

	public int getNx2() {
		return nx2;
	}

	public void setNx2(int nx2) {
		this.nx2 = nx2;
	}

	public double getPosX() {
		return posX;
	}

	public int getAmmo() {
		return ammo;
	}

	public Image getImage() {
		return ninja;
	}

	public static ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			dirX = -1;
			bulletDirX = -1;
			// ninja = runToLeft.getImage();
			// ninja = ninja.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
		}

		else if (key == KeyEvent.VK_RIGHT) {
			dirX = 1;
			bulletDirX = 1;
			// ninja = runToRight.getImage();
			// ninja = ninja.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
		}

		else if (key == KeyEvent.VK_UP) {
			dirY = 1;
		}

		else if (key == KeyEvent.VK_SPACE) {
			fire();
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dirX = 0;
			bulletDirX = -1;
			ninja = standToLeft.getImage();
			ninja = ninja.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
		}

		else if (key == KeyEvent.VK_RIGHT) {
			dirX = 0;
			bulletDirX = 1;
			ninja = standToRight.getImage();
			ninja = ninja.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
		}

		else if (key == KeyEvent.VK_UP) {
			dirY = 0;
		}
	}

}