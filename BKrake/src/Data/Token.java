package Data;

import java.nio.CharBuffer;

public class Token {

	public static int ivarc = 0;
	public static int cvarc = 0;

	static public final String Alphabet = "absdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXZ_";
	static public final String specialChars = ".,!\\\"\\'=()/[]{}%-+*&|<>?\\n�~^";
	static public final String keywords[] = { "if", "else", "for", "foreach" };

	static public final int i_ID = 0b01111111; // 0
	static public final int a_ID = 0b01110000; // 2

	static public final int CODE_SIZE = 8;
	static public final int MEMS_IZE = 32;

	static public final int i_ID_build = i_ID << (MEMS_IZE - CODE_SIZE);
	static public final int a_ID_build = a_ID << (MEMS_IZE - CODE_SIZE);

//-----------------KEYWORDS-----------------
	static public final int IF = 0 << (MEMS_IZE - CODE_SIZE);
	static public final int ELSE = 1 << (MEMS_IZE - CODE_SIZE);
	static public final int FOR = 2 << (MEMS_IZE - CODE_SIZE);
	static public final int FOREACH = 3 << (MEMS_IZE - CODE_SIZE);
	static public final int DEF = 4 << (MEMS_IZE - CODE_SIZE);
	static public final int INT = 5 << (MEMS_IZE - CODE_SIZE);
	static public final int CHAR = 6 << (MEMS_IZE - CODE_SIZE);
	static public final int BOOL = 7 << (MEMS_IZE - CODE_SIZE);
	static public final int STR = 8 << (MEMS_IZE - CODE_SIZE);
	static public final int FLOAT = 9 << (MEMS_IZE - CODE_SIZE);
	static public final int VOID = 10 << (MEMS_IZE - CODE_SIZE);
	static public final int RETURN = 11 << (MEMS_IZE - CODE_SIZE);
//---------------------SINGLECHAR OPERATOR
	static public final int PLUS = 0b10000000 << (MEMS_IZE - CODE_SIZE);
	static public final int MINUS = (0b10000000 + 1) << (MEMS_IZE - CODE_SIZE);
	static public final int MULT = (0b10000000 + 2) << (MEMS_IZE - CODE_SIZE);
	static public final int DIV = (0b10000000 + 3) << (MEMS_IZE - CODE_SIZE);
	static public final int MOD = (0b10000000 + 4) << (MEMS_IZE - CODE_SIZE);
	static public final int OR = (0b10000000 + 16) << (MEMS_IZE - CODE_SIZE);
	static public final int AND = (0b10000000 + 17) << (MEMS_IZE - CODE_SIZE);
	static public final int XOR = (0b10000000 + 18) << (MEMS_IZE - CODE_SIZE);
	static public final int NOT = (0b10000000 + 19) << (MEMS_IZE - CODE_SIZE);
	static public final int EQUALS = (0b10000000 + 5) << (MEMS_IZE - CODE_SIZE);
	static public final int ADDEQUALS = (0b10000000 + 6) << (MEMS_IZE - CODE_SIZE);
	static public final int SUBEQUALS = (0b10000000 + 7) << (MEMS_IZE - CODE_SIZE);
	static public final int MULTEQUALS = (0b10000000 + 8) << (MEMS_IZE - CODE_SIZE);
	static public final int DIVEQUALS = (0b10000000 + 9) << (MEMS_IZE - CODE_SIZE);
	static public final int MODEQUALS = (0b10000000 + 10) << (MEMS_IZE - CODE_SIZE);
	static public final int ANDEQUALS = (0b10000000 + 11) << (MEMS_IZE - CODE_SIZE);
	static public final int OREQUALS = (0b10000000 + 12) << (MEMS_IZE - CODE_SIZE);
	static public final int XOREQUALS = (0b10000000 + 13) << (MEMS_IZE - CODE_SIZE);
	static public final int LEFTEQUALS = (0b10000000 + 14) << (MEMS_IZE - CODE_SIZE);
	static public final int RIGHTEQUALS = (0b10000000 + 15) << (MEMS_IZE - CODE_SIZE);
	static public final int COMPARE = (0b10000000 + 20) << (MEMS_IZE - CODE_SIZE);
	// -------------STRUCTURED OPERATOR------------------
	static public final int DOT = (0b10000000 + 21) << (MEMS_IZE - CODE_SIZE); // .
	static public final int COMMA = (0b10000000 + 22) << (MEMS_IZE - CODE_SIZE); // ,
	static public final int BACKSLASH = (0b10000000 + 23) << (MEMS_IZE - CODE_SIZE);
	static public final int QUOTE = (0b10000000 + 24) << (MEMS_IZE - CODE_SIZE);
	static public final int DQUOTE = (0b10000000 + 25) << (MEMS_IZE - CODE_SIZE);
	static public final int BRACKED = (0b10000000 + 26) << (MEMS_IZE - CODE_SIZE);
	static public final int BBRACKED = (0b10000000 + 27) << (MEMS_IZE - CODE_SIZE);
	static public final int SBRACKED = (0b10000000 + 28) << (MEMS_IZE - CODE_SIZE);
	static public final int BSBRACKED = (0b10000000 + 29) << (MEMS_IZE - CODE_SIZE);
	static public final int CBRACKEDS = (0b10000000 + 30) << (MEMS_IZE - CODE_SIZE);
	static public final int BCBRACKED = (0b10000000 + 31) << (MEMS_IZE - CODE_SIZE);
	static public final int SMALER = (0b10000000 + 32) << (MEMS_IZE - CODE_SIZE);
	static public final int BIGGER = (0b10000000 + 33) << (MEMS_IZE - CODE_SIZE);
	static public final int QUESTION = (0b10000000 + 34) << (MEMS_IZE - CODE_SIZE);

	public static String PrintToken(int token) {
		switch ((int) token) {
		case IF:
			return "IF";

		case ELSE:
			return "ELSE";
		case FOR:
			return "FOR";
		case DEF:
			return "DEF";
		case INT:
			return "INT";
		case CHAR:
			return "CHAR";
		case BOOL:
			return "BOOL";
		case STR:
			return "STR";
		case FLOAT:
			return "FLOAT";
		case VOID:
			return "VOID";
		case RETURN:
			return "RETURN";
		case PLUS:
			return "PLUS";
		case MINUS:
			return "MINUS";
		case MULT:
			return "MULT";
		case DIV:
			return "DIV";
		case MOD:
			return "MOD";
		case EQUALS:
			return "EQUALS";
		case OR:
			return "OR";
		case AND:
			return "AND";
		case XOR:
			return "XOR";

		case NOT:
			return "NOT";
		case COMPARE:
			return "COMPARE";
		case DOT:
			return "DOT";
		case COMMA:
			return "COMMA";
		case BACKSLASH:
			return "BACKSLASH";
		case DQUOTE:
			return "\"";
		case QUOTE:
			return "\'";
		case BRACKED:
			return "(";
		case BBRACKED:
			return ")";
		case SBRACKED:
			return "[";
		case BSBRACKED:
			return "]";
		case CBRACKEDS:
			return "{";
		case BCBRACKED:
			return "}";
		case SMALER:
			return "SMALER";
		case BIGGER:
			return "BIGGER";
		case QUESTION:
			return "?";

		default:
			// System.out.println("[TOKEN] "+ token + " int "+ Integer.toBinaryString(token)
			// + " id " + Integer.toBinaryString( (token >>( MEMS_IZE-CODE_SIZE))));
			if (token >> (MEMS_IZE - CODE_SIZE) == i_ID && Ress.iBuffer.size() > (token - (i_ID_build))) {
				return Ress.iBuffer.get(token - (i_ID_build)) + "";
			}
			if (token >> (MEMS_IZE - CODE_SIZE) == a_ID && Ress.cBuffer.size() > (token - a_ID_build)) {

				String ret = "";
				int id = token - a_ID_build;
				for (int i = id;; i++) {
					if (Ress.cBuffer.get(i) != 0)
						ret += (char) (int) Ress.cBuffer.get(i);
					else
						return ret;
				}
			}
			if (token > a_ID_build)
				return "[BAD CODE]";
			return "[NOT AWAILABLE YET]";

		}
	}

	public static int Tokenize(String word) {
		switch (word) {
		case "if":
			return IF;

		case "else":
			return ELSE;
		case "for":
			return FOR;
		case "def":
			return DEF;
		case "int":
			return INT;
		case "char":
			return CHAR;
		case "bool":
			return BOOL;
		case "str":
			return STR;
		case "float":
			return FLOAT;
		case "void":
			return VOID;
		case "return":
			return RETURN;
		case "+":
			return PLUS;
		case "-":
			return MINUS;
		case "*":
			return MULT;
		case "/":
			return DIV;
		case "%":
			return MOD;
		case "=":
			return EQUALS;
		case "|":
			return OR;
		case "&":
			return AND;
		case "^":
			return XOR;
		case "~":
		case "!":
			return NOT;
		case "==":
			return COMPARE;
		case "+=":
			return ADDEQUALS;
		case "-=":
			return SUBEQUALS;
		case "*=":
			return MULTEQUALS;
		case "/=":
			return DIVEQUALS;
		case "%=":
			return MODEQUALS;
		case "&=":
			return ANDEQUALS;
		case "|=":
			return OREQUALS;
		case "^=":
			return XOREQUALS;
		case "<<=":
			return LEFTEQUALS;
		case ">>=":
			return RIGHTEQUALS;
		case ".":
			return DOT;
		case ",":
			return COMMA;
		case "\\":
			return BACKSLASH;
		case "\"":
			return DQUOTE;
		case "\'":
			return QUOTE;
		case "(":
			return BRACKED;
		case ")":
			return BBRACKED;
		case "[":
			return SBRACKED;
		case "]":
			return BSBRACKED;
		case "{":
			return CBRACKEDS;
		case "}":
			return BCBRACKED;
		case "<":
			return SMALER;
		case ">":
			return BIGGER;
		case "?":
			return QUESTION;
		case ";":
			return 0;
		default:
			int ret = 0;
			if (Algorithm.isDouble(word)) {
				Ress.iBuffer.add(Integer.parseInt(word));
				ret = (i_ID << (MEMS_IZE - CODE_SIZE)) + ivarc;

				ivarc++;
			} else {
				// optimize 
				
				//iterats throw buffer to check for 0`s
				for (int i = 0; i < Ress.cBuffer.size(); i++) {
					if( Ress.cBuffer.get(i) == 0)
						//if 0 found check for whole word
						for (int j = 0; j < word.length(); j++) {
							if (Ress.cBuffer.size() > (i +j +1) && Ress.cBuffer.get(i + j + 1) == (int) word.charAt(j)) {
								if(j == word.length()-1)
									return a_ID_build + i +1;
							}else
								break;
							
						}
				}
				for (char c : word.toCharArray()) {
					Ress.cBuffer.add((int) (c));
				}
				Ress.cBuffer.add(0);
				ret = a_ID_build + cvarc;
				cvarc += word.length() + 1;
			}
			return ret;

		}
	}
	
	public static int getID(int id) {
		return id >> (MEMS_IZE-CODE_SIZE) == i_ID ? id - i_ID_build: id - a_ID_build;
	}

	public static Integer genINTID(int id) {
		return i_ID_build + id;
	}
}
