package GUI.panel;

import GUI.InterfaceFrame;
import GUI.utils.Resource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectionPanel extends JPanel {
	private static final Dimension DIMENSION = new Dimension(InterfaceFrame.WIDTH_WINDOW, InterfaceFrame.HEIGHT_WINDOW);

	private static final Color LETTERS_COLOR = Color.white;
	private static final Font LETTERS_FONT = new Font("Calibri", Font.BOLD, 40);

	private Image background;
	private Image booleanEvaluator;

	private Image firstLine;
	private Image secondLine;
	private Image thirdLine;

	private boolean a;
	private boolean aIsPressed;
	private JButton aTrueButton;
	private JButton aFalseButton;

	private boolean b;
	private boolean bIsPressed;
	private JButton bTrueButton;
	private JButton bFalseButton;

	private JButton submit;
	private JButton reload;
	private JButton exit;

	public SelectionPanel() {
		this.setSize(DIMENSION);
		this.setLayout(null);

		this.background = Resource.getImage("../img/blackboardBackground.jpg");
		this.booleanEvaluator = Resource.getImage("../img/BooleanEvaluatorLungo.png");

		this.firstLine = Resource.getImage("../img/line.png");
		this.secondLine = Resource.getImage("../img/line.png");
		this.thirdLine = Resource.getImage("../img/line.png");

		this.aIsPressed = false;
		this.bIsPressed = false;

		buttonCreator();
		addButtonToPanel();
		addMouseListenerToButton();
	}

	private void buttonCreator() {
		this.aTrueButton = new JButton("TRUE");
		this.aTrueButton.setBounds(600, 250, 100, 50);

		this.aFalseButton = new JButton("FALSE");
		this.aFalseButton.setBounds(750, 250, 100, 50);

		this.bTrueButton = new JButton("TRUE");
		this.bTrueButton.setBounds(600, 350, 100, 50);

		this.bFalseButton = new JButton("FALSE");
		this.bFalseButton.setBounds(750, 350, 100, 50);

		this.submit = new JButton("Submit");
		this.submit.setBounds(578, 440, 125, 25);

		this.reload = new JButton("Reload");
		this.reload.setBounds(590, 470, 100, 25);

		this.exit = new JButton("Exit");
		this.exit.setBounds(603, 500, 75, 25);
	}

	private void addButtonToPanel() {
		this.add(aTrueButton);
		this.add(aFalseButton);
		this.add(bTrueButton);
		this.add(bFalseButton);
		this.add(submit);
		this.add(reload);
		this.add(exit);
	}

	private void removeButtonToPanel(JButton btn) {
		this.remove(btn);
	}

	private void addMouseListenerToButton() {
		this.aTrueButton.addMouseListener(new MouseListener());
		this.aFalseButton.addMouseListener(new MouseListener());
		this.bTrueButton.addMouseListener(new MouseListener());
		this.bFalseButton.addMouseListener(new MouseListener());
		this.submit.addMouseListener(new MouseListener());
		this.reload.addMouseListener(new MouseListener());
		this.exit.addMouseListener(new MouseListener());
	}

	public boolean isA() {
		return a;
	}

	private void setA(boolean a) {
		this.a = a;
	}

	public boolean isB() {
		return b;
	}

	private void setB(boolean b) {
		this.b = b;
	}

	private void resetData() {
		addButtonToPanel();
		aIsPressed = false;
		bIsPressed = false;
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(this.background, 0, 0, getParent());
		g.drawImage(this.booleanEvaluator, 0, 10, getParent());

		g.drawImage(this.firstLine, 0, 220, getParent());
		g.drawImage(this.secondLine, 0, 320, getParent());
		g.drawImage(this.thirdLine, 0, 420, getParent());

		g.setColor(LETTERS_COLOR);
		g.setFont(LETTERS_FONT);
		g.drawString("SCEGLI I VALORI DA ATTRIBUIRE:", 300, 200);

		g.setColor(LETTERS_COLOR);
		g.setFont(LETTERS_FONT);
		g.drawString("A:", 500, 290);

		g.setColor(LETTERS_COLOR);
		g.setFont(LETTERS_FONT);
		g.drawString("B:", 500, 390);
	}

	private class MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			JButton btn = (JButton) e.getSource();

			if (btn.equals(aTrueButton)) {
				setA(true);
				removeButtonToPanel(aFalseButton);
				aIsPressed = true;

				repaint();
			} else if (btn.equals(aFalseButton)) {
				setA(false);
				removeButtonToPanel(aTrueButton);
				aIsPressed = true;

				repaint();
			} else if (btn.equals(bTrueButton)) {
				setB(true);
				removeButtonToPanel(bFalseButton);
				bIsPressed = true;

				repaint();
			} else if (btn.equals(bFalseButton)) {
				setB(false);
				removeButtonToPanel(bTrueButton);
				bIsPressed = true;

				repaint();
			} else if (btn.equals(submit) && aIsPressed && bIsPressed) {
				resetData();

				InterfaceFrame.showPanel(InterfaceFrame.INTERFACE_PANEL);
			} else if (btn.equals(reload)) {
				resetData();

				repaint();
			} else if (btn.equals(exit)) {
				resetData();

				InterfaceFrame.showPanel(InterfaceFrame.WELCOME_PANEL);
			}
		}
	}
}
