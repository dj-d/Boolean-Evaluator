package logic.operators.teminalPackage;

import logic.operators.Terminal;

public class TrueValue extends Terminal
	{
		public TrueValue()
			{
				super(true);
			}

		public boolean interpret()
			{
				return value;
			}
	}
