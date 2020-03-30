package MainClasses;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Players.Player;
import Players.Hunter.Hunter;
import Players.Mage.Mage;
import Players.Pirate.Pirate;
import Players.Samurai.Samurai;
import Players.Thief.Thief;
import Players.Warrior.Warrior;

public class ChooseCharacter extends StateManager implements ActionListener {
	private GamePanel gsm;
	private BufferedImage[] imgArr;
	private String[] heroes = { "Warrior", "Mage", "Samurai", "Hunter", "Thief", "Pirate" };
	private JButton[] buttonsArr;
	private Player[] PlayersArr;
	
	private JPanel ChooseCharacterScreen;
	private JPanel options;
	private JPanel body;
	private JLabel description;
	private JButton startGameBtn;
	public ChooseCharacter(GamePanel gsm) {
		super(gsm);
		this.gsm = gsm;
	}
	
	@Override
	public JPanel GetContent() {
		loadImages();
		
		this.ChooseCharacterScreen = new JPanel();
		this.ChooseCharacterScreen.setSize(this.gsm.getWidth(),this.gsm.getHeight());
		this.ChooseCharacterScreen.setLayout(new GridLayout(1,2));
		this.options = new JPanel();
		this.options.setBackground(Color.black);
		this.options.setLayout(new GridLayout(this.heroes.length + 2,1));
		JLabel header = new JLabel("Choose your hero", SwingConstants.CENTER);
		header.setForeground(Color.red);
		header.setFont(new Font("Courier", Font.BOLD, 40));
		this.options.add(header, BorderLayout.NORTH);
		
		createOptionsButtons();
		
		this.ChooseCharacterScreen.add(this.options);
		this.body = new JPanel();
		this.body.setBackground(Color.black);
		this.body.setLayout(new GridLayout(3,1));
		this.description = new JLabel();
		this.body.add(this.description);
		this.body.add(img);
		this.startGameBtn = new JButton("Start Game");
		this.startGameBtn.setBackground(Color.yellow);
		this.startGameBtn.setForeground(Color.black);
		this.startGameBtn.setFont(new Font("Courier", Font.BOLD, 40));
		this.startGameBtn.addActionListener(this);
		this.body.add(this.startGameBtn);
		this.ChooseCharacterScreen.add(this.body);
		return this.ChooseCharacterScreen;
	}

	// create characters buttons
	private void createOptionsButtons() {
		buttonsArr = new JButton[this.heroes.length];
		for (int i = 0; i < this.heroes.length; i++) {
			this.buttonsArr[i] = new JButton(this.heroes[i], new ImageIcon(((new ImageIcon(this.imgArr[i])).getImage()).getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			this.buttonsArr[i].setBackground(Color.black);
			this.buttonsArr[i].setForeground(Color.white);
			this.buttonsArr[i].setFont(new Font("Courier", Font.BOLD, 30));
			this.buttonsArr[i].setBorder(BorderFactory.createMatteBorder(0, 10, 0, 0, Color.red));
			this.buttonsArr[i].addActionListener(this);
			this.options.add(buttonsArr[i]);
		}
		
		PlayersArr = new Player[this.heroes.length];
		for (int i = 0; i < this.heroes.length; i++) {
			switch (heroes[i]) {
			case "Warrior": 
				this.PlayersArr[i] = new Warrior(this.imgArr[i], this.heroes[i]);
				break;
			case "Mage":
				this.PlayersArr[i] = new Mage(this.imgArr[i], this.heroes[i]);
				break;
			case "Samurai":
				this.PlayersArr[i] = new Samurai(this.imgArr[i], this.heroes[i]);
				break;
			case "Hunter":
				this.PlayersArr[i] = new Hunter(this.imgArr[i], this.heroes[i]);
				break;
			case "Thief":
				this.PlayersArr[i] = new Thief(this.imgArr[i], this.heroes[i]);
				break;
			case "Pirate":
				this.PlayersArr[i] = new Pirate(this.imgArr[i], this.heroes[i]);
				break;
			}
		}
	}

	private JButton prev = new JButton();
	int chosenHero = 0;
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		JButton b = (JButton)source;
		for (int i = 0; i < this.heroes.length; i++) {
		    if (b == this.buttonsArr[i]) {
		    	chosenHero = i;
		    	this.description.setText(
						  "<html>"
						+ "<body style='color:white' 'Clibiri'>"
						+ "<h1>" + this.PlayersArr[i].toString() + "</h1>"
						+ "</body>"
						+ "</html>");
		    	showImage(i);    	
	
		    	this.buttonsArr[i].setBackground(Color.white);
		    	this.buttonsArr[i].setForeground(Color.black);
		    	this.buttonsArr[i].setBorder(BorderFactory.createMatteBorder(0, 10, 0, 0, Color.black));
		    	this.buttonsArr[i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 10, Color.red));
		    	
				if (!this.buttonsArr[i].getText().equals(prev.getText())) {
					prev.setBackground(Color.black);
					prev.setForeground(Color.white);
					prev.setBorder(BorderFactory.createMatteBorder(0, 10, 0, 0, Color.red));
					prev = this.buttonsArr[i];
				}
		    }
		}
		if (b == this.startGameBtn) 
			this.PlayersArr[chosenHero].startGame();
	}
	JLabel img = new JLabel();
	private void showImage(int i) {
		img.setIcon(new ImageIcon(((new ImageIcon(this.imgArr[i])).getImage()).getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
	}
	
	// initialize the imgArr
	private void loadImages() {
		try {
			this.imgArr = new BufferedImage[this.heroes.length];
			for (int i = 0; i < this.heroes.length; i++) 
				this.imgArr[i] = ImageIO.read(new File("src\\Images\\" + this.heroes[i] + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
