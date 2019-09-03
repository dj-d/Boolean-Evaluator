package logic;

import logic.operators.nonTerminalPackage.*;
import logic.operators.teminalPackage.*;

public class Parser
	{
		private Analyzer analyzer;
		private int symbol;
		private BooleanExpression expression;

		private final TrueValue _true = new TrueValue();
		private final FalseValue _false = new FalseValue();

		public Parser(Analyzer _analyzer)
			{
				this.analyzer = _analyzer;
			}

		private void notCalculator()
			{
				symbol = analyzer.nextSymbol();

				if (symbol == Analyzer.TRUE)
					{
						expression = _true;
						symbol = analyzer.nextSymbol();
					}
				else if (symbol == Analyzer.FALSE)
					{
						expression = _false;
						symbol = analyzer.nextSymbol();
					}
				else if (symbol == Analyzer.NOT)
					{
						Not not = new Not();

						notCalculator();

						not.setChild(expression);
						expression = not;
					}
				else if (symbol == Analyzer.LEFT)
					{
						orCalculator();
						symbol = analyzer.nextSymbol();
					}
				else
					{
						throw new RuntimeException("Expression Malformed");
					}
			}

		private void andCalculator()
			{
				notCalculator();

				while (symbol == Analyzer.AND)
					{
						And and = new And();
						and.setLeft(expression);

						notCalculator();

						and.setRight(expression);
						expression = and;
					}
			}

		private void orCalculator()
			{
				andCalculator();

				while (symbol == Analyzer.OR)
					{
						Or or = new Or();
						or.setLeft(expression);

						andCalculator();

						or.setRight(expression);
						expression = or;
					}
			}

		private void implicationCalculator()
			{
				orCalculator();

				while (symbol == Analyzer.IMPLICATION)
					{
						Implication implication = new Implication();
						implication.setLeft(expression);

						orCalculator();

						implication.setRight(expression);
						expression = implication;
					}
			}

		private void doubleImplicationCalculator()
			{
				implicationCalculator();

				while(symbol == Analyzer.DOUBLE_IMPLICATION)
					{
						DoubleImplication doubleImplication = new DoubleImplication();
						doubleImplication.setLeft(expression);

						implicationCalculator();

						doubleImplication.setRight(expression);
						expression = doubleImplication;
					}
			}

		public BooleanExpression build()
			{
				doubleImplicationCalculator();
				return expression;
			}
	}
