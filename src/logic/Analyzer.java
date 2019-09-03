package logic;

import java.io.*;

public class Analyzer
	{
		private StreamTokenizer input;

		private int symbol = NONE;
		private static final int EOL = -3;
		private static final int EOF = -2;
		private static final int INVALID = -1;

		private static final int NONE = 0;

		public static final int OR = 1;
		public static final int AND = 2;
		public static final int NOT = 3;
		public static final int IMPLICATION = 4;
		public static final int DOUBLE_IMPLICATION = 5;

		public static final int TRUE = 6;
		public static final int FALSE = 7;

		public static final int LEFT = 8;
		public static final int RIGHT = 9;

		private static final String TRUE_STRING = "true";
		private static final String FALSE_STRING = "false";

		public Analyzer(InputStream in)
			{
				Reader reader = new BufferedReader(new InputStreamReader(in));
				input = new StreamTokenizer(reader);

				input.resetSyntax();
				input.wordChars('a', 'z');
				input.wordChars('A', 'Z');
				input.whitespaceChars('\u0000', ' ');
				input.whitespaceChars('\n', '\t');

				input.ordinaryChar('(');
				input.ordinaryChar(')');
				input.ordinaryChar('|');
				input.ordinaryChar('&');
				input.ordinaryChar('!');
				input.ordinaryChar('>');
				input.ordinaryChar('<');
			}

		public int nextSymbol()
			{
				try
					{
						switch (input.nextToken())
							{
								case StreamTokenizer.TT_EOL:
									symbol = EOL;
									break;

								case StreamTokenizer.TT_EOF:
									symbol = EOF;
									break;

								case StreamTokenizer.TT_WORD:
								{
									if (input.sval.equalsIgnoreCase(TRUE_STRING))
										{
											symbol = TRUE;
										}
									else if (input.sval.equalsIgnoreCase(FALSE_STRING))
										{
											symbol = FALSE;
										}
									break;
								}

								case '(':
									symbol = LEFT;
									break;

								case ')':
									symbol = RIGHT;
									break;

								case '&':
									symbol = AND;
									break;

								case '|':
									symbol = OR;
									break;

								case '!':
									symbol = NOT;
									break;

								case '>':
									symbol = IMPLICATION;
									break;

								case '<':
									symbol = DOUBLE_IMPLICATION;
									break;

								default:
									symbol = INVALID;
							}
					}
				catch (IOException e)
					{
						symbol = EOF;
					}
				return symbol;
			}

		public String toString()
			{
				return input.toString();
			}
	}