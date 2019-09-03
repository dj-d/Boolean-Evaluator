package GUI;

import GUI.panel.*;

import javax.swing.*;
import java.awt.*;

public class InterfaceFrame extends JFrame {
	public static final int HEIGHT_WINDOW = 720;
	public static final int WIDTH_WINDOW = 1280;
	public static final Dimension DIMENSION = new Dimension(WIDTH_WINDOW, HEIGHT_WINDOW);
	public static final boolean RESIZABLE = false;

	public static final int WELCOME_PANEL = 1;
	public static final int SELECTION_PANEL = 2;
	public static final int INTERFACE_PANEL = 32;

	private static final String PROGRAM_NAME = "Boolean Evaluator";

	private static WelcomePanel welcomePanel;
	private static SelectionPanel selectionPanel;
	private static InterfacePanel interfacePanel;

	public InterfaceFrame() {
		this.setSize(DIMENSION);
		this.setResizable(RESIZABLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setTitle(PROGRAM_NAME);

		this.setLocationRelativeTo(null);
		this.setLayout(null);

		InterfaceFrame.welcomePanel = new WelcomePanel();
		this.getContentPane().add(welcomePanel);

		InterfaceFrame.selectionPanel = new SelectionPanel();
		this.getContentPane().add(selectionPanel);

		InterfaceFrame.interfacePanel = new InterfacePanel(selectionPanel);
		this.getContentPane().add(interfacePanel);


		InterfaceFrame.welcomePanel.setFocusable(true);
		InterfaceFrame.selectionPanel.setFocusable(true);
		InterfaceFrame.interfacePanel.setFocusable(true);

		InterfaceFrame.showPanel(InterfaceFrame.WELCOME_PANEL);
	}

	public static void showPanel(int panelToShow) {
		switch (panelToShow) {
			case WELCOME_PANEL:
				InterfaceFrame.welcomePanel.setVisible(true);
				InterfaceFrame.selectionPanel.setVisible(false);
				InterfaceFrame.interfacePanel.setVisible(false);

				InterfaceFrame.welcomePanel.requestFocusInWindow();
				break;

			case SELECTION_PANEL:
				InterfaceFrame.welcomePanel.setVisible(false);
				InterfaceFrame.selectionPanel.setVisible(true);
				InterfaceFrame.interfacePanel.setVisible(false);

				InterfaceFrame.selectionPanel.requestFocusInWindow();
				break;

			case INTERFACE_PANEL:
				InterfaceFrame.welcomePanel.setVisible(false);
				InterfaceFrame.selectionPanel.setVisible(false);
				InterfaceFrame.interfacePanel.setVisible(true);

				InterfaceFrame.interfacePanel.requestFocusInWindow();
				break;
		}
	}
}
