package GUI.panel;

import GUI.InterfaceFrame;
import GUI.utils.Resource;
import logic.Analyzer;
import logic.BooleanExpression;
import logic.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;

public class InterfacePanel extends JPanel {
	private static final Dimension DIMENSION = new Dimension(InterfaceFrame.WIDTH_WINDOW, InterfaceFrame.HEIGHT_WINDOW);

	private Image background;
	private Image booleanEvaluator;
	private Image logicalOperators;
	private Point logicalOperatorsPos;

	private Image firstLine;
	private Image secondLine;

	private static final String NOT_SYMBOL = "\u00AC";
	private static final String AND_SYMBOL = "\u2227 ";
	private static final String OR_SYMBOL = "\u2228 ";
	private static final String IMPLICATION_SYMBOL = "\u21D2 ";
	private static final String DOUBLE_IMPLICATION_SYMBOL = "\u21D4 ";

	private static final Color SHOW_STRING_COLOR = Color.white;
	private static final Font SHOW_STRING_FONT = new Font("Calibri", Font.BOLD, 40);
	private String showString;
	private Point showStringPosition;

	private String dataString;

	private static final Color RESULT_COLOR = Color.green;
	private static final Font RESULT_FONT = new Font("Calibri", Font.BOLD, 40);
	private String result;
	private Point resultPosition;

	private SelectionPanel selectionPanel;

	private JButton a;
	private JButton b;
	private JButton openParenthesis;
	private JButton closedParenthesis;
	private JButton not;
	private JButton and;
	private JButton or;
	private JButton implication;
	private JButton doubleImplication;

	private JButton submit;
	private boolean submitIsPressed;
	private JButton delete;
	private JButton reload;
	private JButton exit;

	public InterfacePanel(SelectionPanel pSelectionPanel) {
		this.setSize(DIMENSION);
		this.setLayout(null);

		this.background = Resource.getImage("../img/blackboardBackground.jpg");
		this.booleanEvaluator = Resource.getImage("../img/BooleanEvaluator.png");
		this.logicalOperators = Resource.getImage("../img/logicalOperators.png");

		this.firstLine = Resource.getImage("../img/line.png");
		this.secondLine = Resource.getImage("../img/line.png");

		this.selectionPanel = pSelectionPanel;

		initString();
		buttonCreator();
		setBtnPosition();
		addMouseListenerToButton();
		addButtonToPanel();
	}

	/**
	 * Inizializzazione delle variabili String per la visualizzazione dell'equazione e il calcolo del risultato
	 */
	private void initString() {
		showString = "";
		showStringPosition = new Point(0, 360);
		dataString = "";
		result = "";
		resultPosition = new Point(0, 360);

		submitIsPressed = false;
	}

	/**
	 * Creazione di tutti i pulsanti
	 */
	private void buttonCreator() {
		this.a = new JButton("A");
		this.a.setBounds(0, 0, 100, 50);

		this.b = new JButton("B");
		this.b.setBounds(0, 0, 100, 50);

		this.openParenthesis = new JButton("(");
		this.openParenthesis.setBounds(0, 0, 50, 50);

		this.closedParenthesis = new JButton(")");
		this.closedParenthesis.setBounds(0, 0, 50, 50);

		this.not = new JButton("NOT");
		this.not.setBounds(0, 0, 117, 50);

		this.and = new JButton("AND");
		this.and.setBounds(0, 0, 117, 50);

		this.or = new JButton("OR");
		this.or.setBounds(0, 0, 116, 50); // E' diverso perché altrmenti i pulsanti non hanno la stessa larghezza di quelli sottostanti

		this.implication = new JButton("IMPLICATION");
		this.implication.setBounds(0, 0, 150, 50);

		this.doubleImplication = new JButton("DOUBLE IMPLICATION");
		this.doubleImplication.setBounds(0, 0, 200, 50);

		this.submit = new JButton("Submit");
		this.submit.setBounds(0, 0, 125, 25);

		this.delete = new JButton("Delete");
		this.delete.setBounds(0, 0, 100, 25);

		this.reload = new JButton("Reload");
		this.reload.setBounds(0, 0, 100, 25);

		this.exit = new JButton("Exit");
		this.exit.setBounds(0, 0, 75, 25);
	}

	/**
	 * Definisce la posizione di tutti i pulsanti
	 */
	private void setBtnPosition() {
		logicalOperatorsPos = findPositionForOperators(this.or, true, 390, false, 0);

		Point orPos = findPositionForOperators(this.or, true, 0, true, 50);
		this.or.setLocation(orPos);

		Point andPos = findPositionForOperators(this.and, true, this.or.getWidth(), true, 50);
		this.and.setLocation(andPos);

		Point notPos = findPositionForOperators(this.not, true, this.or.getWidth() + this.and.getWidth(), true, 50);
		this.not.setLocation(notPos);

		Point doubleImplicationPos = findPositionForOperators(this.doubleImplication, true, 0, true, this.or.getHeight() + 50);
		this.doubleImplication.setLocation(doubleImplicationPos);

		Point implicationPos = findPositionForOperators(this.implication, true, this.doubleImplication.getWidth(), true, this.or.getHeight() + 50);
		this.implication.setLocation(implicationPos);

		Point openParenthesisPos = findPositionForOperators(this.openParenthesis, false, this.implication.getX(), true, 50);
		this.openParenthesis.setLocation(openParenthesisPos);

		Point closedParenthesisPos = findPositionForOperators(this.closedParenthesis, false, this.implication.getX(), true, this.openParenthesis.getHeight() + 50);
		this.closedParenthesis.setLocation(closedParenthesisPos);

		Point trueValuePos = findPositionForOperators(this.a, false, this.openParenthesis.getX(), true, 50);
		this.a.setLocation(trueValuePos);

		Point falseValuePos = findPositionForOperators(this.b, false, this.openParenthesis.getX(), true, this.a.getHeight() + 50);
		this.b.setLocation(falseValuePos);

		Point submitPos = findPositionForOperators(this.submit, false, DIMENSION.width / 2 + this.submit.getWidth() / 2, true, 0);
		this.submit.setLocation(submitPos.x, submitPos.y + 360);

		Point deletePos = findPositionForOperators(this.delete, false, DIMENSION.width / 2 + this.delete.getWidth() / 2, true, 0);
		this.delete.setLocation(deletePos.x, deletePos.y + submitPos.y + 360);
		this.reload.setLocation(deletePos.x, deletePos.y + submitPos.y + 360);

		Point exitPos = findPositionForOperators(this.exit, false, DIMENSION.width / 2 + this.exit.getWidth() / 2, true, 0);
		this.exit.setLocation(exitPos.x, exitPos.y + submitPos.y + deletePos.y + 360);
	}

	/**
	 * Aggiunge graficamente i pulsanti al pannello
	 */
	private void addButtonToPanel() {
		this.add(this.a);
		this.add(this.b);
		this.add(this.openParenthesis);
		this.add(this.closedParenthesis);
		this.add(this.not);
		this.add(this.and);
		this.add(this.or);
		this.add(this.implication);
		this.add(this.doubleImplication);
		this.add(this.submit);
		this.add(this.delete);
		this.add(this.reload);
		this.add(this.exit);
	}

	/**
	 * Rimuove graficamente i pulsanti dal pannello
	 */
	private void removeButtonToPanel() {
		this.remove(this.a);
		this.remove(this.b);
		this.remove(this.openParenthesis);
		this.remove(this.closedParenthesis);
		this.remove(this.not);
		this.remove(this.and);
		this.remove(this.or);
		this.remove(this.implication);
		this.remove(this.doubleImplication);
		this.remove(this.submit);
		this.remove(this.delete);
	}

	/**
	 * Aggiunge il MouseListener ad ogni pulsante
	 */
	private void addMouseListenerToButton() {
		this.a.addMouseListener(new MouseListener());
		this.b.addMouseListener(new MouseListener());
		this.openParenthesis.addMouseListener(new MouseListener());
		this.closedParenthesis.addMouseListener(new MouseListener());
		this.not.addMouseListener(new MouseListener());
		this.and.addMouseListener(new MouseListener());
		this.or.addMouseListener(new MouseListener());
		this.implication.addMouseListener(new MouseListener());
		this.doubleImplication.addMouseListener(new MouseListener());
		this.submit.addMouseListener(new MouseListener());
		this.delete.addMouseListener(new MouseListener());
		this.reload.addMouseListener(new MouseListener());
		this.exit.addMouseListener(new MouseListener());
	}

	/**
	 * Ricerca dove posizionare il pulsante in base ai dati in input
	 *
	 * @param btn             Il bottone di cui si vuole impostare la posizione
	 * @param enableLeftStart Abilitare o meno la partenza dal lato destro della schermata
	 * @param leftShift       Spostamento verso sinistra supplementare
	 * @param enableDownShift Abilitare o meno lo spostamento verso il basso supplementare
	 * @param downShift       Spostamento verso il basso supplementare
	 * @return La posizione del pulsante
	 */
	private Point findPositionForOperators(JButton btn, boolean enableLeftStart, int leftShift, boolean enableDownShift, int downShift) {
		Point point = new Point();
		int defaultLeftShift = 80;
		int defaultDownShift = 10;

		if (enableLeftStart) {
			point.x = DIMENSION.width - btn.getWidth() - leftShift - defaultLeftShift;
		} else {
			point.x = leftShift - btn.getWidth() - defaultDownShift;
		}

		if (enableDownShift) {
			point.y = btn.getHeight() + defaultDownShift + downShift;
		} else {
			point.y = defaultDownShift;
		}

		return point;
	}

	/**
	 * Posiziona la stringa al centro dello schermo
	 *
	 * @param textWidth La lunghezza della stringa
	 * @return La posizione sull'asse x della scritta
	 */
	private int findPositionForText(int textWidth) {
		int x;

		x = (DIMENSION.width / 2) - textWidth - 30;

		return x;
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(this.background, 0, 0, getParent());
		g.drawImage(this.booleanEvaluator, 130, 50, getParent());

		if (!submitIsPressed) {
			g.drawImage(this.logicalOperators, logicalOperatorsPos.x, logicalOperatorsPos.y + 30, getParent());
		}

		g.drawImage(this.firstLine, 0, 300, getParent());
		g.drawImage(this.secondLine, 0, 380, getParent());

		if (!submitIsPressed) {
			showStringPosition.x = findPositionForText(g.getFontMetrics().stringWidth(showString));

			g.setColor(SHOW_STRING_COLOR);
			g.setFont(SHOW_STRING_FONT);
			g.drawString(showString, showStringPosition.x, showStringPosition.y);
		} else {
			resultPosition.x = findPositionForText(g.getFontMetrics().stringWidth(result));

			g.setColor(RESULT_COLOR);
			g.setFont(RESULT_FONT);
			g.drawString(result, resultPosition.x, resultPosition.y);
		}

		// TODO: Inserire il valore delle lettere nei pulsanti
		g.setColor(SHOW_STRING_COLOR);
		g.setFont(SHOW_STRING_FONT);
		g.drawString("A: " + selectionPanel.isA(), 200, 250);
		g.drawString("B: " + selectionPanel.isB(), 400, 250);
	}

	/**
	 * Calcola il risultato dell'espressione
	 *
	 * @param text L'espressione logica da calcolare
	 * @return Il risultato dell'operazione: true o false
	 */
	private String resultCalculator(String text) {
		Analyzer analyzer = new Analyzer(new ByteArrayInputStream(text.getBytes()));
		Parser parser = new Parser(analyzer);
		BooleanExpression booleanExpression = parser.build();

		return (String.format("%s", booleanExpression.interpret()));
	}

	private class MouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			JButton btn = (JButton) e.getSource();

			if (btn.equals(a)) {
				showString += "A ";
				dataString += String.valueOf(selectionPanel.isA()) + " ";

				repaint();
			} else if (btn.equals(b)) {
				showString += "B ";
				dataString += String.valueOf(selectionPanel.isB()) + " ";

				repaint();
			} else if (btn.equals(openParenthesis)) {
				showString += " (";
				dataString += " (";

				repaint();
			} else if (btn.equals(closedParenthesis)) {
				showString += ") ";
				dataString += ") ";

				repaint();
			} else if (btn.equals(not)) {
				showString += NOT_SYMBOL;
				dataString += "!";

				repaint();
			} else if (btn.equals(and)) {
				showString += AND_SYMBOL;
				dataString += "& ";
				repaint();
			} else if (btn.equals(or)) {
				showString += OR_SYMBOL;
				dataString += "| ";

				repaint();
			} else if (btn.equals(implication)) {
				showString += IMPLICATION_SYMBOL;
				dataString += "> ";

				repaint();
			} else if (btn.equals(doubleImplication)) {
				showString += DOUBLE_IMPLICATION_SYMBOL;
				dataString += "< ";

				repaint();
			} else if (btn.equals(submit)) {
				result = resultCalculator(dataString);
				submitIsPressed = true;
				removeButtonToPanel();

				repaint();
			} else if (btn.equals(delete) && (!dataString.isEmpty())) {
				initString();

				repaint();
			} else if (btn.equals(reload) && submitIsPressed) {
				initString();
				addButtonToPanel();

				repaint();
			} else if (btn.equals(exit)) {
				initString();
				addButtonToPanel();
				InterfaceFrame.showPanel(InterfaceFrame.WELCOME_PANEL);
			}
		}
	}
}