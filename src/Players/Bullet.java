package Players;

import java.awt.*;
import java.util.Random;

import javax.swing.*;

public class Bullet{
	double x, y; // Controls the CURRENT location of THIS bullet
	double distance;
	double rndDir;
	Image img;
	Dude dude;
	boolean visible; // sets weather THIS bullet is visible or not
	int dir; // the direction

	public Bullet(double startX, double startY, int dir, Player player,double rndDir,Dude dude) {
		ImageIcon newBullet;
		this.dir = dir;
		if (this.dir == 1)
			newBullet = new ImageIcon("src\\Players\\" + player.getClassName() + "\\IMG\\shot_right.png");
		else
			newBullet = new ImageIcon("src\\Players\\" + player.getClassName() + "\\IMG\\shot_left.png");
		img = newBullet.getImage();
		img = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		x = startX;
		y = startY;
		distance=0;
		visible = true;
		this.rndDir=rndDir;
		this.dude=dude;
		//System.out.print(rndDir);
		System.out.print(dude.getY());
	}

	public double getX() {
		return x;
	}

	public boolean getVisible() {
		return visible;
	}

	public double getY() {
		return y;
	}

	public Image getImage() {
		return img;
	}

	// Just like the move class in Dude class...
	public void move() {
		if (this.dir == 1) {
			x = x + 2;						
			y+=rndDir;
			distance +=2;		
			if (distance > 500 || y>720 || y<dude.getY()-650+ 620)
				visible = false;
			
		} else {
			x = x - 2;
			y+=rndDir;
			distance +=2;
			if (distance > 500 || y>720 || y<dude.getY()-650+ 620)
				visible = false;
		}
	}
}