package Players;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public abstract class Player {
	private String name;
	public static String className;
	private int hp;
	private int strength;
	private int mana;
	private int speed;
	private int xp;
	private int level;
	private BufferedImage img;

	public Player(int hp, int strength, int mana, int speed, String name, String className, BufferedImage img) {
		this.xp = 0;
		this.level = 1;
		this.hp = hp;
		this.strength = strength;
		this.mana = mana;
		this.speed = speed;
		this.name = name;
		Player.className = className;
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public static String getClassName() {
		return className;
	}

	
}
