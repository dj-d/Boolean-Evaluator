package logic.operators;

import logic.BooleanExpression;

public abstract class Terminal implements BooleanExpression
	{
		protected boolean value;

		public Terminal(boolean _value)
			{
				this.value = _value;
			}

		/**
		 * Converte il valore in stringa
		 *
		 * @return Il valore in formato stringa
		 */
		public String toString()
			{
				return String.format("%s", value);
			}
	}
