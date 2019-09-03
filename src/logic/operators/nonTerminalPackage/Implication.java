package logic.operators.nonTerminalPackage;

import logic.operators.NonTerminal;

public class Implication extends NonTerminal
	{
		public boolean interpret()
			{
				return !(left.interpret()) || right.interpret();
			}

		public String toString()
			{
				return String.format("(%s > %s)", left, right);
			}
	}