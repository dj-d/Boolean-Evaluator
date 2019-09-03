package GUI.utils;

import java.awt.*;

public class ActionButton {
	public static final String OPEN_PARENTHESIS = "OPEN_PARENTHESIS";
	public static final String CLOSED_PARENTHESIS = "CLOSED_PARENTHESIS";
	public static final String NOT = "NOT";
	public static final String AND = "AND";
	public static final String OR = "OR";
	public static final String IMPLICATION = "IMPLICATION";
	public static final String DOUBLE_IMPLICATION = "DOUBLE_IMPLICATION";

	private Image img;
	private HotArea hotArea;
	private Point hotAreaPos;

	public ActionButton(String pathImg, int xPosHotArea, int yPosHotArea) {
		setImg(pathImg);
		setHotArea(xPosHotArea, yPosHotArea);
	}

	public Image getImg() {
		return this.img;
	}

	private void setImg(String pathImg) {
		this.img = Resource.getImage(pathImg);
	}

	public HotArea getHotArea() {
		return this.hotArea;
	}

	private void setHotArea(int xPos, int yPos) {
		this.hotArea = new HotArea(xPos, yPos, this.img.getWidth(null), this.img.getHeight(null));
	}

	public void setHotAreaPosition(int xPos, int yPos) {
		this.hotArea.setLocation(xPos, yPos);
	}

	public void setHotAreaPosition(Point p) {
		this.hotArea.setLocation(p);
	}

	public Point getHotAreaPos() {
		return hotAreaPos;
	}

	public void setHotAreaPos(Point hotAreaPos) {
		// TODO...
	}
}
