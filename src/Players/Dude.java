package Players;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.ImageIcon;

public class Dude {
	int x, y, dirX, dirY, nx, nx2, posX, bulletDirX = 1;
	Image ninga;

	ImageIcon runToRight = new ImageIcon("src\\Players\\Samurai\\IMG\\Samurai_right.png");
	ImageIcon runToLeft = new ImageIcon("src\\Players\\Samurai\\IMG\\Samurai_left.png");
	ImageIcon standToRight = new ImageIcon("src\\Players\\Samurai\\IMG\\Samurai_standing_right.png");
	ImageIcon standToLeft = new ImageIcon("src\\Players\\Samurai\\IMG\\Samurai_standing_left.png");
	ImageIcon attackToRight = new ImageIcon("src\\Players\\Samurai\\IMG\\Samurai_attack_right.png");
	ImageIcon attackToLeft = new ImageIcon("src\\Players\\Samurai\\IMG\\Samurai_attack_left.png");
	
	int ammo = 100;
	static ArrayList<Bullet> bullets;// Holds number of bullets on screen

	public Dude() {
		x = 100;
		y = 172;
		posX = 150;
		nx = 0;
		nx2 = 685;
		ninga = standToRight.getImage();
		ninga = ninga.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
		bullets = new ArrayList<Bullet>();// j
	}
	
	// Method to run when fired
	public void fire()
	{	    	    
		System.out.println(bullets.size() + " " + ammo);
		if (ammo > 0) {
			if(this.bulletDirX == 1) {
				ninga = attackToRight.getImage();
				ninga = ninga.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
			}
			else {
				ninga = attackToLeft.getImage();
				ninga = ninga.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
			}
			ammo--;
			Bullet b = new Bullet((posX + 100), y + 30, bulletDirX);
			bullets.add(b);
		}
	}

	public void move() {
		if (dirX == 1) {
			if (x < 1400) {
				x = x + dirX;
				nx2 = nx2 + dirX;
				nx = nx + dirX;
				if (posX < 150)
					posX += dirX;
			} else if (posX < 540)
				posX += dirX;
		} else {
			if (x > 100) {
				x = x + dirX;
				nx2 = nx2 + dirX;
				nx = nx + dirX;
				if (posX > 395)
					posX += dirX;
			} else if (posX > -50)
				posX += dirX;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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

	public int getPosX() {
		return posX;
	}

	public int getAmmo() {
		return ammo;
	}
	
	public Image getImage() {
		return ninga;
	}

	

	public static ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			dirX = -1;
			bulletDirX = -1;
			ninga = runToLeft.getImage();
			ninga = ninga.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
		}

		else if (key == KeyEvent.VK_RIGHT) {
			dirX = 1;
			bulletDirX = 1;
			ninga = runToRight.getImage();
			ninga = ninga.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
		}

		else if (key == KeyEvent.VK_UP) {
			dirY = 1;
		}
		
		else if (key == KeyEvent.VK_SPACE)
        {
            fire();
        }
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dirX = 0;
			bulletDirX = -1;
			ninga = standToLeft.getImage();
			ninga = ninga.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
		}

		else if (key == KeyEvent.VK_RIGHT) {
			dirX = 0;
			bulletDirX = 1;
			ninga = standToRight.getImage();
			ninga = ninga.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
		}

		else if (key == KeyEvent.VK_UP) {
			dirY = 0;
		}
	}
}