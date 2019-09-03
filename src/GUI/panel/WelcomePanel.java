package GUI.panel;

import GUI.InterfaceFrame;
import GUI.utils.HotArea;
import GUI.utils.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WelcomePanel extends JPanel {
	private static final Dimension DIMENSION = new Dimension(InterfaceFrame.WIDTH_WINDOW, InterfaceFrame.HEIGHT_WINDOW);

	private HotArea hotArea;

	private Image background;
	private Image booleanEvaluator;
	private Image startImage;

	private int startXPos;
	private int startYPos;

	public WelcomePanel() {
		this.setSize(DIMENSION);
		this.setLayout(null);

		this.background = Resource.getImage("../img/blackboardBackground.jpg");
		this.booleanEvaluator = Resource.getImage("../img/BooleanEvaluatorLungo.png");

		this.startImage = Resource.getImage("../img/start.png");
		this.startXPos = (DIMENSION.width / 2) - (this.startImage.getWidth(this) / 2);
		this.startYPos = (DIMENSION.height / 2) - (this.startImage.getHeight(this) / 2);

		this.hotArea = new HotArea(startXPos, startYPos, this.startImage.getWidth(this), this.startImage.getHeight(this));

		this.addMouseListener(new MouseListener());
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(this.background, 0, 0, getParent());
		g.drawImage(this.booleanEvaluator, 0, 10, getParent());

		g.drawImage(this.startImage, startXPos, startYPos, getParent());
	}

	private class MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (hotArea.contains(e.getPoint())) {
				InterfaceFrame.showPanel(InterfaceFrame.SELECTION_PANEL);
			}
		}
	}
}