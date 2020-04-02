package Players;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import com.sun.javafx.geom.RoundRectangle2D;

public class GameScreen extends JPanel implements ActionListener, Runnable {
	private PlayerSprite PlayerSprite;
	private Image bgImg; // background image
	private Timer time; 
	private Thread animation;
	private Player player;
	private boolean stopAnimation = false;

	public static void startGame(Player player) {
		JFrame Gameframe = new JFrame();
		Gameframe.add(new GameScreen(player));
		Gameframe.setTitle("2-D Test Game");
		Gameframe.setSize(1000, 1000);
		Gameframe.setResizable(false);
		Gameframe.setVisible(true);
		Gameframe.setLocationRelativeTo(null);
	}

	public GameScreen(Player player) {
		this.player = player;
		PlayerSprite = new PlayerSprite(player);
		addKeyListener(new AL());
		setFocusable(true);
		ImageIcon i = new ImageIcon("src\\Images\\Background_desert.jpg");
		bgImg = i.getImage();
		bgImg = bgImg.getScaledInstance(1200, 1000, java.awt.Image.SCALE_SMOOTH);
		time = new Timer(3, this); // sets the delay between each animation
		time.start();
	}

	public void actionPerformed(ActionEvent e) {
		ArrayList<Shot> Shots = PlayerSprite.getShots();
        for (int i = 0; i < Shots.size(); i++) {
                
                 Shot shot = (Shot) Shots.get(i);//This is how to get a current element in an arrayList
                 if (shot.getVisible() == true) {
                         shot.move();
                 }
                 else Shots.remove(i);
        }
        PlayerSprite.move();
        repaint();
	}

	public void paint(Graphics g) {
		if (PlayerSprite.getDirY() == 1 && stopAnimation == false) {			
			animation = new Thread(this);
			animation.start();
			stopAnimation = true;
		}

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		if ((PlayerSprite.getX() - 590) % 2400 == 0)
			PlayerSprite.setFirstImagePos(0);

		g2d.drawImage(bgImg, 685 - PlayerSprite.getSecondImagePos(), 0, null);
		if (PlayerSprite.getX() > 590) {
			g2d.drawImage(bgImg, 685 - PlayerSprite.getSecondImagePos(), 0, null);
		}
		g2d.drawImage(PlayerSprite.getImage(), (int)PlayerSprite.getPosX(), (int)PlayerSprite.getY(), null);

		if (PlayerSprite.getDirX() < 0) {
			g2d.drawImage(bgImg, 685 - PlayerSprite.getSecondImagePos(), 0, null);
			g2d.drawImage(PlayerSprite.getImage(), (int)PlayerSprite.getPosX(), (int)PlayerSprite.getY(), null);
		}
		/******************************************************/
		ArrayList<Shot> Shots = PlayerSprite.getShots();
        for (int i = 0; i < Shots.size(); i++) {         
             Shot shot = (Shot) Shots.get(i);//This is how to get a current element in an arrayList
            g2d.drawImage(shot.getImage(), (int)shot.getX(), (int)shot.getY(), null);
        }
        
        g2d.setFont(new Font("SanSerif", Font.BOLD, 24));
        g2d.setColor(Color.black);
        g2d.drawString("Ammo left: " + PlayerSprite.getAmmo(), 800, 50);
        
        
       //g.draw(new RoundRectangle2D.Double(x, y, w, h, 50, 50));
        
        g.drawString("HP: ", 5, 25);
        g.setColor(Color.gray);
        g.fillRect(50, 0, 150, 40);
        
        g.setColor(Color.green);
        g.fillRect(50, 0, player.getHp(), 40);
        
        g.setColor(Color.white);
        g.drawRect(50, 0, player.getHp(), 40);
        
        g.setColor(Color.black);
       
        // **********fix length*************
       
        g.drawString("Mana:", 5, 75);
        g.setColor(Color.gray);
        g.fillRect(75, 50, 150, 40);
        
        g.setColor(Color.blue);
        g.fillRect(75, 50, player.getMana()*2, 40);
        
        g.setColor(Color.white);
        g.drawRect(75, 50, player.getMana()*2, 40);
        
        g2d.setFont(new Font("SanSerif", Font.BOLD, 24));
        g2d.setColor(Color.black);
        g2d.drawString(player.getName(), (int)PlayerSprite.getPosX(), (int)PlayerSprite.getY()-20);
        
        
        

	}

	private class AL extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			PlayerSprite.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			PlayerSprite.keyPressed(e);
		}
	}

	boolean isJumping = true;
	boolean stoppedJumping = false;

	public void cycle() {
		if (isJumping)
			PlayerSprite.setY(PlayerSprite.getY() - 2);
		if (PlayerSprite.getY() == 550)
			isJumping = false;
		if (!isJumping && PlayerSprite.getY() <= 650) {
			PlayerSprite.setY(PlayerSprite.getY() + 2);
			if (PlayerSprite.getY() == 650) {
				stoppedJumping = true;
			}
		}
	}

	public void run() {

		long beforeTime, timeDiff, sleep;

		beforeTime = System.currentTimeMillis();

		while (!stoppedJumping) {
			cycle();

			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = 10 - timeDiff;

			if (sleep < 0)
				sleep = 2;
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				System.out.println("interrupted");
			}

			beforeTime = System.currentTimeMillis();
		}
		stoppedJumping = false; // set to false so player can jump again after falling
		isJumping = true; // set to true so player can jump again after falling
		stopAnimation = false; // set to false so animation can start again after the end of the loop
	}

}