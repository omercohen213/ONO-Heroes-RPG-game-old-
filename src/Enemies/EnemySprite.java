package Enemies;

import Players.Player;
import Players.Shot;

import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import Enemies.Enemy;

public class EnemySprite {
    private double dirX=0.1;
    private int firstImagePos, secondImagePos;
    private double x; // player position after passing the second image ending line at x=1400
    private double posX, y; // player position on the screen
    private Enemy enemy;
    private Image currentEnemyImg;

    private BufferedImage[] stanceImages = new BufferedImage[2];
    private String[] stance = { "move_right", "move_left"};
    private BufferedImage moveRight, moveLeft;
    private boolean[] keys = new boolean[500];

    public EnemySprite(Enemy enemy) {
        this.enemy = enemy;
        loadImages();// loads the player sprite images
        x = 150;
        y = 650;
        posX = 150;
        firstImagePos = 0;
        secondImagePos = 685;
        currentEnemyImg = moveRight;
        currentEnemyImg = currentEnemyImg.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH);
        System.out.printf(" x: " + x + " posX: " + posX);
    }

    private void loadImages() {
        try {
            for (int i = 0; i < this.stanceImages.length; i++)
                stanceImages[i] = ImageIO
                        .read(new File("src\\Enemies\\" + enemy.getClassName() + "\\IMG\\" + stance[i] + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        moveRight = stanceImages[0];
        moveLeft = stanceImages[1];
    }

    public void move() {
//    	if (x < 200) 
//    		x += dirX;  
//    	if (x > 200) 
//             x -= dirX;   	   	
    	
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

    public double getDirX() {
        return dirX;
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

    public Image getImage() {
        return currentEnemyImg;
    }


    public boolean[] getKeys() {
        return keys;
    }

}
