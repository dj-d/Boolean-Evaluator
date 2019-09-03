package GUI.utils;

import java.awt.*;

public class HotArea extends Rectangle {
	public HotArea(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public HotArea(Point p, Dimension d) {
		super(p, d);
	}

	@Override
	public double getX() {
		return super.getX();
	}

	@Override
	public double getY() {
		return super.getY();
	}

	@Override
	public double getWidth() {
		return super.getWidth();
	}

	@Override
	public double getHeight() {
		return super.getHeight();
	}

	@Override
	public Point getLocation() {
		return super.getLocation();
	}

	@Override
	public void setLocation(Point p) {
		super.setLocation(p);
	}

	@Override
	public void setLocation(int x, int y) {
		super.setLocation(x, y);
	}

	@Override
	public Dimension getSize() {
		return super.getSize();
	}

	@Override
	public void setSize(Dimension d) {
		super.setSize(d);
	}

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
	}
}
