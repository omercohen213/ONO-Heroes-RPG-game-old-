package Enemies;

public class Enemy {
	private String name;
	private int hp;
	private int strength;
	private int mana;
	private int speed;
	private int level;
	
	public Enemy(int hp, int strength, int mana, int speed, int level, String name) {
		super();
		this.hp = hp;
		this.strength = strength;
		this.mana = mana;
		this.speed = speed;
		this.level = level;
		this.name = name;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
