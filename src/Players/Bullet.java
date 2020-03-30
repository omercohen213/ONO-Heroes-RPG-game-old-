package Players;

import java.awt.*;
import javax.swing.*;

public class Bullet {
	int x, y; // Controls the CURRENT location of THIS bullet

	Image img;

	boolean visible; // sets weather THIS bullet is visible or not
	int dir; // the direction

	public Bullet(int startX, int startY, int dir) {
		ImageIcon newBullet;
		this.dir = dir;
		if (this.dir == 1)
			newBullet = new ImageIcon("src\\Players\\" + Player.getClassName() + "\\IMG\\shot_right.png");
		else
			newBullet = new ImageIcon("src\\Players\\" + Player.getClassName() + "\\IMG\\shot_left.png");
		img = newBullet.getImage();
		img = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		x = startX;
		y = startY;
		visible = true;
	}

	public int getX() {
		return x;
	}

	public boolean getVisible() {
		return visible;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return img;
	}

	// Just like the move class in Dude class...
	public void move() {
		if (this.dir == 1) {
			x = x + 2;

			if (x > 630)
				visible = false;
		} else {
			x = x - 2;
			// if x > board width Make the bullet invisible
			if (x < 0)
				visible = false;
		}
	}
}