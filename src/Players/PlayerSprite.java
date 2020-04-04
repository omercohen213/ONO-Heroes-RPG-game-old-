package Players;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class PlayerSprite {
	private int dirX, dirY; // player movement directions
	private int firstImagePos, secondImagePos;
	private int shotDirX = 1; // shot direction; starts right as the player starts standing right
	private double x; // player position after passing the second image ending line at x=1400
	private double posX, y; // player position on the screen
	private Player player;
	private Image currentPlayerImg;
	private boolean sprint;

	private BufferedImage[] stanceImages = new BufferedImage[6];
	private String[] stance = { "move_right", "move_left", "standing_right", "standing_left", "attack_right",
			"attack_left" };
	private BufferedImage moveRight, moveLeft, standRight, standLeft, attackRight, attackLeft;

	private int ammo;
	private int manaCost = 2; // this is temporary for all players for only fire attack
	private ArrayList<Shot> Shots;// Holds number of Shots on screen

	public PlayerSprite(Player player) {
		this.player = player;
		loadImages();// loads the player sprite images

		x = 100;
		y = 650;
		posX = 150;
		firstImagePos = 0;
		secondImagePos = 685;
		currentPlayerImg = standRight;
		currentPlayerImg = currentPlayerImg.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
		Shots = new ArrayList<Shot>();// j
		ammo = player.getMana() / manaCost;
		sprint = false;
	}

	private void loadImages() {
		try {
			for (int i = 0; i < this.stanceImages.length; i++)
				stanceImages[i] = ImageIO
						.read(new File("src\\Players\\" + player.getClassName() + "\\IMG\\" + stance[i] + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		moveRight = stanceImages[0];
		moveLeft = stanceImages[1];
		standRight = stanceImages[2];
		standLeft = stanceImages[3];
		attackRight = stanceImages[4];
		attackLeft = stanceImages[5];
	}

	public void move() {
		if (dirX > 0) {// if player is moving right (dirX=1)
			if (x < 1400) {
				x += dirX;
				firstImagePos += dirX;
				secondImagePos += dirX;
				if (posX < 150)
					posX += dirX;
			} else if (posX < 820)
				posX += dirX;
		} else if (dirX < 0) { // if player is moving left (dirX=-1)
			if (x > 100) {
				x += dirX;
				firstImagePos += dirX;
				secondImagePos += dirX;
				if (posX > 540)
					posX += dirX;
			} else if (posX > -50)
				posX += dirX;
		}
	}

	public void fire() { // called when pressing space
		System.out.printf(" x: " + x + " posX: " + posX);

		if (ammo > 0) {
			// chooses which image to use according to player's direction
			if (this.shotDirX == 1) {
				currentPlayerImg = attackRight;
				currentPlayerImg = currentPlayerImg.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
			} else {
				currentPlayerImg = attackLeft;
				currentPlayerImg = currentPlayerImg.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
			}
			player.setMana(player.getMana() - manaCost);
			ammo--;

			// randomize a number and a boolean to determine whether the shot goes up or
			// down and how fast
			Random r = new Random();
			double rndDirY = r.nextDouble() * (r.nextBoolean() ? -1 : 1);
			Shot shot = new Shot((posX + 100), (y + 30), shotDirX, player, rndDirY, this);
			Shots.add(shot);
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

	public int getFirstImagePos() {
		return firstImagePos;
	}

	public void setFirstImagePos(int firstImagePos) {
		this.firstImagePos = firstImagePos;
	}

	public int getSecondImagePos() {
		return secondImagePos;
	}

	public void setSecondImagePos(int secondImagePos) {
		this.secondImagePos = secondImagePos;
	}

	public double getPosX() {
		return posX;
	}

	public int getAmmo() {
		return ammo;
	}

	public Image getImage() {
		return currentPlayerImg;
	}

	public ArrayList<Shot> getShots() {
		return Shots;
	}


	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			dirX = -1;
			shotDirX = -1;
			currentPlayerImg = moveLeft;
			currentPlayerImg = currentPlayerImg.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
			if(sprint) {
				if (player.getMana() >= manaCost) {
					dirX = 5*dirX;
					player.setMana(player.getMana() - manaCost);
				}
			}
		}

		else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
				dirX = 1;
				shotDirX = 1;
				currentPlayerImg = moveRight;
				currentPlayerImg = currentPlayerImg.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
				if(sprint) {
					if (player.getMana() >= manaCost) {
						dirX = 5*dirX;
						player.setMana(player.getMana() - manaCost);
					}
				}
						
		}

		else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			dirY = 1;
		}

		else if (key == KeyEvent.VK_SPACE) {
			fire(); // fire shot
		}
		
		else if (key == KeyEvent.VK_SHIFT) {
			sprint = true;
        }
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			dirX = 0;
			shotDirX = -1;
			currentPlayerImg = standLeft;
			currentPlayerImg = currentPlayerImg.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
		}

		else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			dirX = 0;
			shotDirX = 1;
			currentPlayerImg = standRight;
			currentPlayerImg = currentPlayerImg.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
		}

		else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			dirY = 0;
		}
		else if (key == KeyEvent.VK_SHIFT) {
			sprint = false;
        }
	}

}