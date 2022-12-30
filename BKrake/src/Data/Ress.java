package Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Integer;

public class Ress {
	static public ArrayList<Integer> iBuffer = new ArrayList<>();
	static public ArrayList<Integer> cBuffer = new ArrayList<>();
	static final public String Special_Symbols = ".,!\"\'=()/[]{}%-+*&|<>?\n�", ComentStart = "//", ComentEnd = "\n";
	static final public char SYMbol = '\'';
	static final public String EqualOperator = "/*-+|^><=%!&~";
	static final public String[] MultiCharOperator = new String[] { "==", "||", "&&", "+=", "-=" };
	static final String True = "True", False = "False";
	static public final String Cleared_Symbols = "\n\t ";
	static public final String[] Basic_Var_Types = new String[] { "int", "float", "char", "bit", "def" };
	static public final String[] dependeny = new String[] { ".", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
	static public HashMap<Integer, Double> Num = new HashMap<>();
	static public ArrayList<Boolean> Bool = new ArrayList<Boolean>();
	static public ArrayList<int[]> MethodeContent = new ArrayList<>();
	static public final String[] BasicFunktions = new String[] { "Print", "Add", "Sub", "Pro", "Div", "Mod", "Delete",
			"Adds", "Subs", "Divs", "Pros", "Mods" };
	static public final String[] BasicStructures = new String[] { "while" };

	/**
	 * flushes all ram data meaning the cache of the programm
	 */
	public static void Bufferflush() {
		Num = new HashMap<>();
		Bool = new ArrayList<Boolean>();
		MethodeContent = new ArrayList<>();
		iBuffer = new ArrayList<>();
		cBuffer = new ArrayList<>();
		Token.ivarc = 0;
		Token.cvarc = 1;
		cBuffer.add(0);
	}
}