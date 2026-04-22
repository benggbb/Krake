
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import Data.*;
//TODO copy code and fully implement Code structure
public class BKrake {
	public static int EXIT_CODE = 0;

	/**
	 * ENTRANCES of Interpreter
	 */
	public static int Start(String input) {

		// CLear COmments
		input = Clear_Comments(input);
		// preProcessor
		// Header
		Ress.Bufferflush();
		input = Algorithm.Cleared(input, "\n\r\t");

		Debug.Print_Log("--------------------------RAW---------------------\n" + input
				+ "\n---------------------------------------------------"

		);

		Code code = convertBlockStructs(Tokenize(input)); //TODO implement block strucutur
		//ArrayList<ArrayList<Integer>> commandList = BineryList(Tokenize( input)); //old structure

		Debug.Print_Log("---------------------Complete Wordlist[BYTEFORMAT]------\n" + code
				+ "\n----------------------------------------------");
		Debug.Print_Log("-------Buffer----------\n" + Ress.iBuffer + "\n-----------------------------");
		Debug.Print_Log("-------cBUFFER---------\n" + Ress.cBuffer+ "\n--------------------------");
		// RUN
		//EXEC(code);
		EXIT_CODE = 0;
				// Bufferflush
		Ress.Bufferflush();
		return EXIT_CODE;

	}

	private static String Clear_Comments(String input) {
		return input; // TODO has to be implemented first;
	}

	private static List<Integer> Tokenize(String code){
		List<Integer> Token = new ArrayList<>();
		String samp = "";

		for (int i = 0; i < code.length(); i++){
			if(Ress.Special_Symbols.indexOf(code.charAt(i) ) != -1 || code.charAt(i) == ' ') { //ist c ein special character
				if (!samp.isEmpty()) {
					Token.add(Data.Token.Tokenize(samp));
					samp = "";
				}
				if(code.charAt(i) != ' ') {
					if (Ress.EqualOperator.indexOf(code.charAt(i)) != -1 && (code.length() - 1) > i && code.charAt(i + 1) == '=') {
						Token.add(Data.Token.Tokenize(code.substring(i, i + 2)));
						i++;
					} else

						Token.add(Data.Token.Tokenize(code.charAt(i) + ""));
				}
			} else samp += code.charAt(i);
		}
		return Token;
	}

	/*private static Code BineryList(List<Integer> block) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
		ArrayList<Integer> newblock = new ArrayList<>();
		HashSet<Integer> bids = new HashSet<>();
		ArrayList<Integer> current_Methode = new ArrayList<>();
		Code code;
		int curly = 0;
		int bracked = 0;


		for (int token : block) {

			if (token == Token.CBRACKEDS) curly++;
			if (token == Token.BCBRACKED) curly --;
			if (token == Token.BRACKED) bracked++;
			if (token == Token.BBRACKED) bracked--;

			// Token zum aktuellen "String" hinzufügen
			if ((token == Token.SIMICOLON || token == Token.BCBRACKED ) && curly == 0 && bracked == 0){
				if (token == Token.BCBRACKED ) current_Methode.add(token);
				ret.add( FormatCode( current_Methode));
				current_Methode = new ArrayList<>();
			} else {
				current_Methode.add(token);
			}
		}
		code = new Code(0, ret, bids);
		return code;
	}*/

	//solits code into its block where every block has its unique code
	//every block is connected to its parent block with a pointer
	private static Code convertBlockStructs(List<Integer> tokens){ //TODO rekursive block gen
		ArrayList<List<Integer>> converted = new ArrayList<>();
		ArrayList<Integer> inner = new ArrayList<>();
		List<Integer> line = new ArrayList<>();
		HashSet<Integer> b_addr = new HashSet<>();
		int curly = 0;
		int bracked = 0;
		int addr = Code.count++;

		for( int token : tokens){

			if (token == Token.CBRACKEDS) curly++;
			if (token == Token.BCBRACKED) curly --;
			if (token == Token.BRACKED) bracked++;
			if (token == Token.BBRACKED) bracked--;

			if(curly == 0){
				if(!inner.isEmpty()){
					Debug.Print_tokenli(line);
					Code innerblock = convertBlockStructs( inner);
					line.add( Token.genBlockPointer(addr));
					Debug.Print_tokenli(line);
					Ress.Blocks.put(addr, innerblock);
					b_addr.add(addr);
					inner = new ArrayList<>();
					converted.add(line);
					line = new ArrayList<>();

				} else{
					line.add(token);
					//merge lines to a block
					if ((token == Token.SIMICOLON || token == Token.BCBRACKED ) && curly == 0 && bracked == 0){
						Debug.Print_tokenli(line);
						converted.add(FormatCode(line));
						line = new ArrayList<Integer>();
					}
				}


			}else{
				inner.add(token);
			}
		}
		return new Code(addr, converted, b_addr);
	}

	/*private static Code BineryList(Code block) { //TODO broken methode obsolete
		ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
		ArrayList<Integer> current_Methode = new ArrayList<>();
		int curly = 0;
		int bracked = 0;

		for (int token : block.) {

			if (token == Token.CBRACKEDS) curly++;
			if (token == Token.BCBRACKED) curly --;
			if (token == Token.BRACKED) bracked++;
			if (token == Token.BBRACKED) bracked--;

			// Token zum aktuellen "String" hinzufügen
			if ((token == Token.SIMICOLON || token == Token.BCBRACKED ) && curly == 0 && bracked == 0){
				if (token == Token.BCBRACKED ) current_Methode.add(token);
				ret.add( FormatCode( current_Methode));
				current_Methode = new ArrayList<>();
			} else {
				current_Methode.add(token);
			}
		}

		return ret;
	}*/

	/*private static ArrayList<ArrayList<Integer>> BineryList(List<Integer> block) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
		ArrayList<Integer> current_Methode = new ArrayList<>();
		int curly = 0;
		int bracked = 0;

		for (int token : block) {

			if (token == Token.CBRACKEDS) curly++;
			if (token == Token.BCBRACKED) curly --;
			if (token == Token.BRACKED) bracked++;
			if (token == Token.BBRACKED) bracked--;

			// Token zum aktuellen "String" hinzufügen
			if ((token == Token.SIMICOLON || token == Token.BCBRACKED ) && curly == 0 && bracked == 0){
				if (token == Token.BCBRACKED ) current_Methode.add(token);
				ret.add( FormatCode( current_Methode));
				current_Methode = new ArrayList<>();
			} else {
				current_Methode.add(token);
			}
		}

		return ret;
	}*/

	/*private static ArrayList<ArrayList<Integer>> BineryList(String input) { // obsolete

		ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
		String current_Methode = new String();

		int front_index = 0;
		int back_index = 0;

		for (char c : input.toCharArray()) {

			if (c == '{') front_index++;
			if (c == '}') back_index++;

			if ((c == ';' || c == '}') && front_index - back_index == 0) {

				if (c == '}') current_Methode += c;

				ret.add(FormatCode(current_Methode));
				current_Methode = new String();
			} else {
				current_Methode += c;
			}

		}
		return ret;
	}*/

	private static List<Integer> FormatCode(List <Integer> tokenlist) {
		// (already Cleared before) current_Methode = Algorithm.Cleared(current_Methode,
		// Data.Cleared_Symbols);
		Debug.Print_Log("---------NEW LINE---------\n" + Algorithm.Tokenli_toString(tokenlist) + "\n--------------------");
			try {

			boolean found = false;
			ArrayList<Integer> header = new ArrayList<>();
			for (int i = 0; i < tokenlist.size()-1; i++) {
				// Finding Methodes
				/* Entrance and Condition */

				if (tokenlist.get(i).equals(Token.DEF)) {
					found = true;
					int Type = tokenlist.get(i);
					int Name = Token.getID(tokenlist.get(i + 1));
					ArrayList<Integer> content = Algorithm.get_Methode_Block(tokenlist, i + 2);

					while (Ress.MethodeContent.size() <= Name) {
						Ress.MethodeContent.add(content); // Platzhalter
					}
					Ress.Methode_Name.add(Name);

					Debug.Print_Log("SAVING in NAMES:" + Name);
					Debug.Print_Log(
							"[HEADER] " + Token.PrintToken(Type) + " " + Token.PrintToken(tokenlist.get(i + 1)) + "\n[Content]" + Algorithm.Tokenli_toString(content) + "\n");
					header.add(Type);
					header.add(Name);
					// i += content.size(); not relevant
					return header;

				}
			}
			if (!found)
				return tokenlist;

		} catch (Exception e) {

			return tokenlist;

		}
			Debug.Print_Log("[[FATAL ERROR this shouldnt be here");
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
			Debug.Print_Log(e.getStackTrace());
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

	private static List<Integer> EXEC(ArrayList<ArrayList<Integer>> commandList) {

		List<Integer> ret_value = new ArrayList<>();
		for (ArrayList<Integer> snippet : commandList) {

			Debug.Print_Log("--------[EXEC CODE]:\n" + Algorithm.Tokenli_toString(snippet) + "\n--------------");
			ret_value = Do(snippet);
			Debug.Print_Log("[RETURN VALUE] " + Algorithm.Tokenli_toString(ret_value));
		}

		return ret_value;
	}
	private static List<Integer> EXEC(Code code) {

		List<Integer> ret_value = new ArrayList<>();
		for (List<Integer> snippet : code.lines()) {

			Debug.Print_Log("--------[EXEC CODE]:\n" + Algorithm.Tokenli_toString(snippet) + "\n--------------");
			ret_value = Do(snippet);
			Debug.Print_Log("[RETURN VALUE] " + Algorithm.Tokenli_toString(ret_value));
		}

		return ret_value;
	}

	// recursive execution of a singe line of code
	public static List<Integer> Do(List<Integer> snippet) {
		if (snippet.isEmpty()) return snippet;

		Debug.Print_Log("\n\n--------------------------[NEW ITERATION]\n"+ Algorithm.Tokenli_toString( snippet));

		//skipping methode Definition
		if(snippet.getFirst() == Token.DEF){
			Debug.Print_Log("[NOT EXEC: " + Token.getTokenId(snippet.getFirst()) + Token.PrintToken(snippet.get(1)) + ":" + snippet.get(1) + "]");

			List<Integer> zero = new ArrayList<>();
			zero.add(0);
			return zero;}


		int prior_count = 100;
		int pos = -1;
		int front_bracket = 0;
		int back_bracket = 0;
		int c_BRACKED_BLOCKS =0;
		//check if we got a depth of brackets (...)

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
			//count brackets
			if ( snippet.get(i) == Token.BRACKED) front_bracket++;
			if ( snippet.get(i) == Token.BBRACKED) back_bracket++;
			//depth tracking

			if (front_bracket != back_bracket) continue;
			else if(snippet.get(i) == Token.BBRACKED) c_BRACKED_BLOCKS++;

			//
			//start processing operators // temporary change recursive isnt working with break;
			if ( snippet.get(i) == Token.NOT ) {
				if (14 <= prior_count) { prior_count = 14; pos = i;}
				//else break;
			}

			if ( snippet.get(i) >= Token.MULT && snippet.get(i) <= Token.MOD) {
				if (14 <= prior_count) { prior_count = 14; pos = i;}
				//else break;
			}
			
			if ( snippet.get(i) == Token.PLUS || snippet.get(i) == Token.MINUS) {
				if (13 <= prior_count) { prior_count = 13; pos = i;}
				//else break;
			}
			
			if ( snippet.get(i) >= Token.EQUALS && snippet.get(i) <= Token.RIGHTEQUALS) {
				if (2 <= prior_count) { prior_count = 2; pos = i;}
				//else break;
			}
			
			if ( snippet.get(i) == Token.EQUALS ) {
				if (3 <= prior_count) { prior_count = 3; pos = i;}
				//else break;
			}
			
			if ( snippet.get(i) >= Token.EQUALS && snippet.get(i) <= Token.RIGHTEQUALS) {
				if (2 <= prior_count) { prior_count = 2; pos = i;}
				//else break;
			}
			
			//if (pos == -1)return snippet;

		}
		if(front_bracket != back_bracket) throw new RuntimeException("Block of Brackets unfinished (..<)> or <(>..)");
		//take brackets away
		if(c_BRACKED_BLOCKS == 1 && snippet.getFirst() == Token.BRACKED && snippet.getLast() == Token.BBRACKED){
			Debug.Print_Log("[Found Bracket Block (..)]\n\t" + Algorithm.Tokenli_toString(snippet));

			return Do(snippet.subList(1,snippet.size()-1));

		}
		Debug.Print_Log("\n[DOING] OPERator at INDEX: " + pos +" <<PRIORITY>> " + prior_count);
		
		if(pos == -1) {
			Debug.Print_Log(Algorithm.Tokenli_toString(snippet));
			return Methode_Call(snippet);
		}
		
			
		if (snippet.get(pos) == Token.EQUALS) { 
			// operator =
			Debug.Print_Log("\n[TRYING SETTING] "+ Algorithm.Tokenli_toString(snippet.subList(0, pos)) + " WITH " + Algorithm.Tokenli_toString(snippet.subList(pos+1, snippet.size())));
			return Utils.Set(Do(snippet.subList(0, pos)), Do(snippet.subList(pos+1, snippet.size())));
		}
		if (snippet.get(pos) == Token.PLUS) {
			//  plus
			Debug.Print_Log(" \n[TRYING ADDING] "+ Algorithm.Tokenli_toString(snippet.subList(0, pos)) + " WITH " + Algorithm.Tokenli_toString(snippet.subList(pos+1, snippet.size())));
			return Utils.Add(Do(snippet.subList(0, pos)), Do(snippet.subList(pos+1, snippet.size())));
		}
		
		if (snippet.get(pos) == Token.MINUS) {
			// minus
			Debug.Print_Log(" \n[TRYING SUBRATING] "+ Algorithm.Tokenli_toString(snippet.subList(0, pos)) + " WITH " + Algorithm.Tokenli_toString(snippet.subList(pos+1, snippet.size())));
			return Utils.Minus(Do(snippet.subList(0, pos)), Do(snippet.subList(pos+1, snippet.size())));
		}
		
		if (snippet.get(pos) == Token.MULT) {
			// mult
			Debug.Print_Log(" \n[TRYING MULTIPLICATE] "+ Algorithm.Tokenli_toString(snippet.subList(0, pos)) + " WITH " + Algorithm.Tokenli_toString(snippet.subList(pos+1, snippet.size())));
			return Utils.Mult(Do(snippet.subList(0, pos)), Do(snippet.subList(pos+1, snippet.size())));
		}
		if (snippet.get(pos) == Token.DIV) {
			// div
			Debug.Print_Log(" \n[TRYING MULTIPLICATE] "+ Algorithm.Tokenli_toString(snippet.subList(0, pos)) + " WITH " + Algorithm.Tokenli_toString(snippet.subList(pos+1, snippet.size())));
			return Utils.DIV(Do(snippet.subList(0, pos)), Do(snippet.subList(pos+1, snippet.size())));
		}
		
		ArrayList<Integer> err =  new ArrayList<Integer>();
		err.add(0);
		return err;
	}
	public static List<Integer> Methode_Call(List<Integer> snippet) {

		if (snippet.size() > 1 && snippet.get(1) == Token.BRACKED && Token.getTokenId(snippet.get(0)) == Token.a_ID && Algorithm.is_Methode(Token.getID(snippet.get(0)))) {

			Debug.Print_Log("[EXECUTING  METHODE<<" + Token.PrintToken( snippet.get(0)) + ">>]\n");

			/*return EXEC(BineryList(
					Ress.MethodeContent.get(
							Token.getID(
									snippet.get(0))).subList(1, Ress.MethodeContent.get(Token.getID(snippet.get(0))).size() - 1))
			);*/
		}
			return snippet;
	}
}


/*

	private static ArrayList<ArrayList<Integer>> BineryList(List<Integer> block){
		ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
		ArrayList<Integer> currentCommand = new ArrayList<>();

		int front_index = 0;
		int back_index = 0;

		for (int c : block) {
			if (c == Token.CBRACKEDS) front_index++;
			if (c == Token.BCBRACKED) back_index++;


			currentCommand.add(c);

			if ((c == 0 || c == Token.BCBRACKED) && front_index - back_index == 0) {

				ret.add(currentCommand);
				currentCommand.clear();
			} else {
				currentCommand.add(c);
			}

		}
		return ret;

	}
 */