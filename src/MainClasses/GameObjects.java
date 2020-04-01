package MainClasses;

import javax.swing.Renderer;
import Players.gameScreen;

public abstract class GameObjects {

	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	protected String objName;
	protected int posX, posY;
	protected int width ,height;
	
	public abstract void update (gameScreen gs, float dt);
	public abstract void render (gameScreen gs, Renderer r);
	
}
