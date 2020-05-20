package Players;

import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class PlayerSprite {
	private int dirX, dirY; // player movement directions
	private int firstImagePos, secondImagePos;
	private int shotDirX = 1; // shot direction; starts right as the player starts standing right
	private double x; // player position after passing the second image ending line at x=1400
	private double posX, y; // player position on the screen
	private Player player;
	private Image currentPlayerImg;

	private BufferedImage[] stanceImages = new BufferedImage[6];
	private String[] stance = { "move_right", "move_left", "standing_right", "standing_left", "attack_right",
			"attack_left" };
	private BufferedImage moveRight, moveLeft, standRight, standLeft, attackRight, attackLeft;

	private int ammo;
	private int manaCost = 2; // this is temporary for all players for only fire attack
	private ArrayList<Shot> Shots;// Holds number of Shots on screen

	private boolean[] keys = new boolean[500];
	private boolean runningLeft = false, runningRight = false;

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

	public boolean isRunningLeft() {
		return runningLeft;
	}

	public boolean[] getKeys() {
		return keys;
	}

	public boolean isRunningRight() {
		return runningRight;
	}
	
	public void setRunningLeft(boolean runningLeft) {
		this.runningLeft = runningLeft;
	}

	public void setRunningRight(boolean runningRight) {
		this.runningRight = runningRight;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SHIFT) {
			keys[key] = true;
			if (keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D] || keys[KeyEvent.VK_A]) {
				if (player.getMana() >= manaCost) {
					if (dirX > 0) {
						dirX = 4;
						runningRight = true;
						}
					 else {
						dirX = -4;
						runningLeft = true;	
					}
					// player.setMana(player.getMana() - manaCost);
				}
			}
		} else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			keys[key] = true;
			currentPlayerImg = moveLeft;
			currentPlayerImg = currentPlayerImg.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
			shotDirX = -1;
			if (keys[KeyEvent.VK_SHIFT]) {
				if (player.getMana() >= manaCost) {
					dirX = -4;
					runningRight = false;
					// player.setMana(player.getMana() - manaCost);
				}
			} else
				dirX = -1;
		}

		else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D ) { 
			keys[key] = true;
			currentPlayerImg = moveRight;
			currentPlayerImg = currentPlayerImg.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
			shotDirX = 1;
			if (keys[KeyEvent.VK_SHIFT]) {
				if (player.getMana() >= manaCost) {
					dirX = 4;
					runningLeft = false;
					// player.setMana(player.getMana() - manaCost);
				}
			} else
				dirX = 1;

		}

		else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) { 
			dirY = 1;
			
		}

		else if (key == KeyEvent.VK_SPACE) {
			fire(); // fire shot
		}
	}


	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT|| key == KeyEvent.VK_A) {
			keys[key] = false;
			dirX = 0;
			shotDirX = -1;
			currentPlayerImg = standLeft;
			currentPlayerImg = currentPlayerImg.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
			runningLeft = false;
		}

		else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {  
			keys[key] = false;
			dirX = 0;
			shotDirX = 1;
			currentPlayerImg = standRight;
			currentPlayerImg = currentPlayerImg.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
			runningRight = false;
		}

		else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {  
			dirY = 0;
		}

		else if (key == KeyEvent.VK_SHIFT) {
			keys[key] = false;
			if (keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D] || keys[KeyEvent.VK_A]) {
				// keys[key] = false;
				if (dirX > 0) {
					dirX = 1;
					runningRight = false;
				} else {
					dirX = -1;
					runningLeft = false;
				}
			}
		}
	}
}
