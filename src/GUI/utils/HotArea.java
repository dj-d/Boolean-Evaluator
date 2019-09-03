package GUI.utils;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class HotArea extends Rectangle {
	public HotArea(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public HotArea(@NotNull Point p, @NotNull Dimension d) {
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

	@NotNull
	@Override
	public Point getLocation() {
		return super.getLocation();
	}

	@Override
	public void setLocation(@NotNull Point p) {
		super.setLocation(p);
	}

	@Override
	public void setLocation(int x, int y) {
		super.setLocation(x, y);
	}

	@NotNull
	@Override
	public Dimension getSize() {
		return super.getSize();
	}

	@Override
	public void setSize(@NotNull Dimension d) {
		super.setSize(d);
	}

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
	}
}
