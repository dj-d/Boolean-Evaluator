package logic.operators.nonTerminalPackage;

import logic.operators.NonTerminal;

public class DoubleImplication extends NonTerminal
	{
		public boolean interpret()
			{
				return (!(left.interpret()) || right.interpret()) && (!(right.interpret()) || left.interpret());
			}

		public String toString()
			{
				return String.format("(%s < %s)", left, right);
			}
	}
