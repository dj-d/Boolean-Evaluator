package logic.operators.teminalPackage;

import logic.operators.Terminal;

public class FalseValue extends Terminal
	{
		public FalseValue()
			{
				super(false);
			}

		public boolean interpret()
			{
				return value;
			}
	}
