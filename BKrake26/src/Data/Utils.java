package Data;

import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static List<Integer> Set(List<Integer> id, List<Integer> value) {
		if (Algorithm.Typeof(value) == 0) // int
			// Debug.Print_Log( id);
			Debug.Print_Log("[STORING] id " + Token.getID(value.get(0)) + " name " + Algorithm.Tokenli_toString(id)
					+ " with TOKEN " + Integer.toBinaryString((value.get(0))) + " ON " + Token.getID(id.get(0))
					+ " value " + Algorithm.Tokenli_toString(value));
		Ress.Num.put(Token.getID(id.get(0)), (double) unpack_Var(value));
		Debug.Print_Log("\n-------Buffer----------\n" + Ress.iBuffer + "\n-----------------------------");
		Debug.Print_Log("-------cBUFFER---------\n" + Ress.cBuffer + "\n--------------------------");
		// Debug.Print_Log(Ress.Num);
		return value;
	}

	public static List<Integer> Add(List<Integer> left, List<Integer> right) {
		ArrayList<Integer> res = new ArrayList<>();
		Debug.Print_Log("[OPERATION ADD]");
		// just checking if parameters are integers

		//if (Algorithm.Typeof(left) == 0) {  Statement isolates int from variable
			if (left.size() != right.size() && !left.isEmpty())
				throw new RuntimeException( "both participants have to be integers == left :" + Algorithm.Tokenli_toString(left)+ " right: " + Algorithm.Tokenli_toString(right));
			Debug.Print_Log(Algorithm.GetINTValue(unpack_Var(left)) + Algorithm.GetINTValue(unpack_Var(right)));
			res.add(Utils.cacheValue(Algorithm.GetINTValue(unpack_Var(left)) + Algorithm.GetINTValue(unpack_Var(right))));
		//}
		// Debug.Print_Log("---[ADD] result: "+
		// Ress.iBuffer.get(Token.getID(res.get(0))) + "------------ [id] " + res);
		return res;
	}

	public static List<Integer> Minus(List<Integer> left, List<Integer> right) {
		ArrayList<Integer> res = new ArrayList<>();
		Debug.Print_Log("[OPERATION MINUS]");
		//if (Algorithm.Typeof(left) == 0) {
			Debug.Print_Log(Algorithm.GetINTValue(unpack_Var(left)) - Algorithm.GetINTValue(unpack_Var(right)));
			res.add(Utils.cacheValue(Algorithm.GetINTValue(unpack_Var(left)) - Algorithm.GetINTValue(unpack_Var(right))));
		//}
		// Debug.Print_Log("---[ADD] result: "+
		// Ress.iBuffer.get(Token.getID(res.get(0))) + "------------ [id] " + res);
		return res;
	}
	
	public static List<Integer> Mult(List<Integer> left, List<Integer> right) {
		ArrayList<Integer> res = new ArrayList<>();
		Debug.Print_Log("[OPERATION Mult]");
		//if (Algorithm.Typeof(left) == 0) {
			if (left.size() != right.size() && left.size() != 0)
				throw new RuntimeException( "both participants have to be integers == left :" + Algorithm.Tokenli_toString(left)+ " right: " + Algorithm.Tokenli_toString(right));
			Debug.Print_Log(Algorithm.GetINTValue(unpack_Var(left)) * Algorithm.GetINTValue(unpack_Var(right)));
			res.add(Utils.cacheValue(Algorithm.GetINTValue(unpack_Var(left)) * Algorithm.GetINTValue(unpack_Var(right))));
		//}
		// Debug.Print_Log("---[ADD] result: "+
		// Ress.iBuffer.get(Token.getID(res.get(0))) + "------------ [id] " + res);
		return res;
	}

	private static Integer cacheValue(int i) {
		Ress.iBuffer.add(i);
		// Debug.Print_Log("-------[CACHE CHANGE]-------\n"+Ress.iBuffer);
		return Token.genINTID(Ress.iBuffer.size() - 1);
	}

	public static List<Integer> DIV(List<Integer> left, List<Integer> right) {
		ArrayList<Integer> res = new ArrayList<>();
		Debug.Print_Log("[OPERATION DIV]");
		//if (Algorithm.Typeof(left) == 0) {
			if (left.size() != right.size() && left.size() != 0)
				throw new RuntimeException( "both participants have to be integers == left :" + Algorithm.Tokenli_toString(left)+ " right: " + Algorithm.Tokenli_toString(right));
			Debug.Print_Log(Algorithm.GetINTValue(unpack_Var(left)) / Algorithm.GetINTValue(unpack_Var(right)));
			res.add(Utils.cacheValue(Algorithm.GetINTValue(unpack_Var(left)) / Algorithm.GetINTValue(unpack_Var(right))));
		//}
		// Debug.Print_Log("---[ADD] result: "+
		// Ress.iBuffer.get(Token.getID(res.get(0))) + "------------ [id] " + res);
		return res;
	}

	//unpacks a value if its a variable otherwise return
	public static int unpack_Var(List<Integer> element){
		if (Algorithm.Typeof(element) == 0) return element.getFirst();
		else {return  (int) (double)(Ress.Num.get( Token.getID(element.getFirst()))); }// kein plan warum ich hier int nutze
	}

}
