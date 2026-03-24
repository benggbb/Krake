
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Data.Algorithm;
import Data.Ress;
import Data.Token;
import Data.Utils;

public class BKrake {
	public static int EXIT_CODE = 0;

	/**
	 * ENTRANCES of Interpreter
	 */
	public static int Start(String input) {

		// CLear COmments

		// preProcessor
		// Header
		Ress.Bufferflush();
		input = Algorithm.Cleared(input, "\n\r\t");

		System.out.println("--------------------------RAW---------------------\n" + input
				+ "\n---------------------------------------------------"

		);

		ArrayList<ArrayList<Integer>> commandList = BineryList(input);

		System.out.println("---------------------Complete Wordlist[BYTEFORMAT]------\n" + commandList
				+ "\n----------------------------------------------");
		System.out.println("-------Buffer----------\n" + Ress.iBuffer + "\n-----------------------------");
		System.out.println("-------cBUFFER---------\n" + Ress.cBuffer+ "\n--------------------------");
		// RUN
		EXIT_CODE = EXEC(commandList);
		// Bufferflush
		Ress.Bufferflush();
		return EXIT_CODE;

	}

	private static ArrayList<ArrayList<Integer>> BineryList(String input) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
		String current_Methode = new String();
		int front_index = 0;
		int back_index = 0;
		for (char c : input.toCharArray()) {
			if (c == '{')
				front_index++;
			if (c == '}')
				back_index++;
			if ((c == ';' || c == '}') && front_index - back_index == 0) {
				if (c == '}')
					current_Methode += c;
				ret.add(FormatCode(current_Methode));
				current_Methode = new String();
			} else {
				current_Methode += c;
			}

		}
		return ret;
	}

	private static ArrayList<Integer> FormatCode(String current_Methode) {
		// (already Cleared before) current_Methode = Algorithm.Cleared(current_Methode,
		// Data.Cleared_Symbols);
		boolean found = false;
		ArrayList<Integer> Wordlist = Algorithm.Create_TokenList(current_Methode);
		System.out.println("---------NEW LINE---------\n" + Algorithm.printlist(Wordlist) + "\n--------------------");
		try {

			ArrayList<Integer> header = new ArrayList<>();
			for (int i = 0; i < Wordlist.size(); i++) {
				/* Entrance and Condition */ if (Wordlist.get(i).equals(Token.DEF) && !found) {
					found = true;
					int Type = Wordlist.get(i);
					int Name = Wordlist.get(i + 1);
					ArrayList<Integer> content = Algorithm.get_Methode_Block(Wordlist, i + 2);
					// Ress.MethodeContent.add(Name, Algorithm.get_Block(Wordlist));
					System.out.println(
							"[HEADER] " + Type + " " + Name + "\n[Content]" + Algorithm.printlist(content) + "\n");
					header.add(Type);
					header.add(Name);
					// i += content.size(); not relevant
					return header;

				}
				if (!found)
					return Wordlist;
			}
		} catch (Exception e) {

			return Wordlist;

		}
		return null;

	}

	public static String Fopen(String path) {
		String ret = "";
		try {
			File f = new File(path);
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				ret += s.nextLine();
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return "";
		}
		return ret;

	}

	public static void main(String[] args) {
		if (args.length == 0)
			new Gui();
		else {
			Start(Fopen(args[args.length - 1]));
		}
	}

	private static int EXEC(ArrayList<ArrayList<Integer>> commandList) {
		for (ArrayList<Integer> snippet : commandList) {
			System.out.println("--------[EXEC CODE]:\n" + Algorithm.printlist(snippet) + "\n--------------");
			System.out.println(Algorithm.printlist(Do(snippet)));
		}

		return 0;
	}

	// recursive execution
	public static List<Integer> Do(List<Integer> snippet) {
		System.out.println("\n\n--------------------------\n[STEP]"+ Algorithm.printlist( snippet));
		int prior_count = 100;
		int pos = -1;
		for (int i = 0; i < snippet.size(); i++) {
			/* ++ -- () [] . 	PR 16
			 * ! 				PR 15
			 *  x/%				PR 14
			 *  +-				PR 13
			 *  << >>			PR 12
			 *  < <= > >=		PR 11
			 *  == !=			PR 10 
			 *  & 				PR 9
			 *  ^				PR 8
			 *  |				PR 7
			 *  &&				PR 6
			 *  ||				PR 5
			 *  ?:				PR 4
			 *  =				PR 3
			 *  += -= any=		PR 2
			 *  ,				PR 1
			 *   			 
			 */
			if ( snippet.get(i) == Token.NOT ) {
				if (15 < prior_count) { prior_count = 15; pos = i;}
				else break;
			}

			if ( snippet.get(i) >= Token.MULT && snippet.get(i) <= Token.MOD) {
				if (14 < prior_count) { prior_count = 14; pos = i;}
				else break;
			}
			
			if ( snippet.get(i) == Token.PLUS || snippet.get(i) == Token.MINUS) {
				if (13 < prior_count) { prior_count = 13; pos = i;}
				else break;
			}
			
			if ( snippet.get(i) >= Token.EQUALS && snippet.get(i) <= Token.RIGHTEQUALS) {
				if (2 < prior_count) { prior_count = 2; pos = i;}
				else break;
			}
			
			if ( snippet.get(i) == Token.EQUALS ) {
				if (3 < prior_count) { prior_count = 3; pos = i;}
				else break;
			}
			
			if ( snippet.get(i) >= Token.EQUALS && snippet.get(i) <= Token.RIGHTEQUALS) {
				if (2 < prior_count) { prior_count = 2; pos = i;}
				else break;
			}
			
			//if (pos == -1)return snippet;

		}
		System.out.println("\n[DOING] operator pos: " + pos +" pri count " + prior_count);
		
		if(pos == -1) { 
			System.out.println(Algorithm.printlist(snippet));
			return snippet; }
		
			
		if (snippet.get(pos) == Token.EQUALS) { 
			// operator =
			System.out.println("\n[TRYING SETTING] "+ Algorithm.printlist(snippet.subList(0, pos)) + " WITH " + Algorithm.printlist(snippet.subList(pos+1, snippet.size())));
			return Utils.Set(Do(snippet.subList(0, pos)), Do(snippet.subList(pos+1, snippet.size())));
		}
		if (snippet.get(pos) == Token.PLUS) {
			//  plus
			System.out.println(" \n[TRYING ADDING] "+ Algorithm.printlist(snippet.subList(0, pos)) + " WITH " + Algorithm.printlist(snippet.subList(pos+1, snippet.size())));
			return Utils.Add(Do(snippet.subList(0, pos)), Do(snippet.subList(pos+1, snippet.size())));
		}
		
		if (snippet.get(pos) == Token.MINUS) {
			// minus
			System.out.println(" \n[TRYING SUBRATING] "+ Algorithm.printlist(snippet.subList(0, pos)) + " WITH " + Algorithm.printlist(snippet.subList(pos+1, snippet.size())));
			return Utils.Minus(Do(snippet.subList(0, pos)), Do(snippet.subList(pos+1, snippet.size())));
		}
		
		if (snippet.get(pos) == Token.MULT) {
			// mult
			System.out.println(" \n[TRYING MULTIPLICATE] "+ Algorithm.printlist(snippet.subList(0, pos)) + " WITH " + Algorithm.printlist(snippet.subList(pos+1, snippet.size())));
			return Utils.Mult(Do(snippet.subList(0, pos)), Do(snippet.subList(pos+1, snippet.size())));
		}
		if (snippet.get(pos) == Token.DIV) {
			// div
			System.out.println(" \n[TRYING MULTIPLICATE] "+ Algorithm.printlist(snippet.subList(0, pos)) + " WITH " + Algorithm.printlist(snippet.subList(pos+1, snippet.size())));
			return Utils.DIV(Do(snippet.subList(0, pos)), Do(snippet.subList(pos+1, snippet.size())));
		}
		
		ArrayList<Integer> err =  new ArrayList<Integer>();
		err.add(0);
		return err;
	}
}
