package Data;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;

public class Algorithm {

	public static ArrayList<Integer> Create_TokenList(String command) {
		command += ' ';
		// Used Vars---------------------------------------------
		ArrayList<Integer> ret = new ArrayList<Integer>();
		String current_Word = new String();
		char[] chars = command.toCharArray();
		for (int i = 0; i < chars.length; i++) {

			if ((/* IMPORTANT */chars[i] == ' '/* IMPORTANT */)
					&& Cleared(current_Word, Ress.Cleared_Symbols).length() > 0) 
			{
				ret.add(Token.Tokenize(Cleared(current_Word, Ress.Cleared_Symbols)));
				current_Word = new String();
			}
			else 
			{
				if (Ress.Special_Symbols.contains(chars[i] + "")) {

					if (Cleared(current_Word, Ress.Cleared_Symbols).length() > 0) {
						// System.out.println("-"+Cleared(current_Word) + "-");
						ret.add(Token.Tokenize(Cleared(current_Word, Ress.Cleared_Symbols)));
					}
					if (Ress.EqualOperator.contains(chars[i] + "") && chars[i + 1] == '=') {

						ret.add(Token.Tokenize(chars[i] + "" + chars[i + 1]));
						i++;
					} else

						ret.add(Token.Tokenize(chars[i] + ""));
					current_Word = new String();
				} else
					current_Word += chars[i];
			}

		}
		return ret;
	}

	private static boolean contains(String Charsequens, char c) {
		return Charsequens.contains(c + "");
	}

	public static String Cleared(String Input, String cleared) {
		String ret = new String();
		try {

			for (char c : Input.toCharArray()) {
				if (!cleared.contains(c + ""))
					ret += c;
			}
		} catch (Exception e) {
			System.out.println("[ERR]" + e);
		}
		return ret;
	}

	public static String printlist(List<Integer> wordlist) {
		String Code = "[ ";
		for (Integer token : wordlist) {
			Code += Token.PrintToken(token) + " , ";
		}
		return Code +" ]";
	}

	public static ArrayList<Integer> get_Methode_Block(ArrayList<Integer> wordlist, int index) {
		ArrayList<Integer> block = new ArrayList<>();

		int bi = 0, fi = 0;
		for (int i = index; i < wordlist.size(); i++) {

			if (wordlist.get(i) == Token.CBRACKEDS)
				fi++;
			if (wordlist.get(i) == Token.BCBRACKED)
				bi++;
			if (fi != 0) {
				block.add(wordlist.get(i));
				if (bi == fi)
					return block;
			}
		}
		return null;
	}

	public static void Calc(ArrayList<Integer> snippet, int i) {

	}

	public static boolean isDouble (String seq) {
		try {
			Double.parseDouble(seq);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static int Typeof(List<Integer> id) {
		return  (id.get(0) >> (Token.MEMS_IZE - Token.CODE_SIZE)) == Token.a_ID ? 1 : 0;
			

	}

	public static int GetINTValue(int id) {
		return Ress.iBuffer.get(Token.getID(id));
	}

}
