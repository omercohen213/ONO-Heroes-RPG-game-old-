package Players.Samurai;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Players.Player;
import Players.GameScreen;

public class Samurai extends Player {
private String skill;
	
	public Samurai(BufferedImage img, String name) {
		super(100, 60, 100, 3, "omercohen213","Samurai", img);
		this.skill = "speed";	
	}
	

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	@Override
	public String toString() {
		String about = 
				"<html>"
				+ "<body style=\"font-family:'Courier';\">"
				+ "<h2 style=\"align:'center'; color: yellow;\">\"Samurai, THE INSTRUMENT OF REVENGE!\"</h2>"
				+ "<p>"
				+ "Unknown to many, the Samurai isn’t one of evil nature, but rather, like many<br />"
				+ "other heroes, his purpose in life is to stop the ever present evil that<br />"
				+ "threatens the very existence of the world. Sadly, The path of a Samurai is a lonely<br />"
				+ "one. Always observing from his dark abode, the Samurai keeps an eye on<br />"
				+ "evil, waiting for the right moment to strike before disappearing once against into<br />"
				+ "the shadows.<br />"
				+ "</p>"
				
				+ "<ul>"
					+ "<li>hp: " + getHp() + "</li>"
					+ "<li>strength: " + getStrength() + "</li>"
					+ "<li>mana: " + getMana() + "</li>"
					+ "<li>speed: " + getSpeed() + "</li>"
					+ "<li>skill: " + this.skill + "</li>"
					+ "<li>xp: " + getXp() + "</li>"
					+ "<li>level: " + getLevel() + "</li>"
				+ "</ul>"
				+ "</body>"
				+ "</html>";
		return about;
	}
}
