package Data;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

public class Utils {

	public static List<Integer> Set(List<Integer> id, List<Integer> value) {
		if (Algorithm.Typeof(value) == 0) // int
			// System.out.println( id);
			System.out.println("[STORING] id " + Token.getID(value.get(0)) + " name " + Algorithm.printlist(id)
					+ " with TOKEN " + Integer.toBinaryString((value.get(0))) + " ON " + Token.getID(id.get(0))
					+ " value " + Algorithm.printlist(value));
		Ress.Num.put(Token.getID(id.get(0)), (double) value.get(0));
		System.out.println("-------Buffer----------\n" + Ress.iBuffer + "\n-----------------------------");
		System.out.println("-------cBUFFER---------\n" + Ress.cBuffer + "\n--------------------------");
		// System.out.println(Ress.Num);
		return value;
	}

	public static List<Integer> Add(List<Integer> left, List<Integer> right) {
		ArrayList<Integer> res = new ArrayList<>();
		System.out.println("[OPERATION ADD]");
		if (Algorithm.Typeof(left) == 0) {
			if (left.size() != right.size() && left.size() != 0)
				throw new RuntimeException( "both participants have to be integers == left :" + Algorithm.printlist(left)+ " right: " + Algorithm.printlist(right));
			System.out.println(Algorithm.GetINTValue(left.get(0)) + Algorithm.GetINTValue(right.get(0)));
			res.add(Utils.cacheValue(Algorithm.GetINTValue(left.get(0)) + Algorithm.GetINTValue(right.get(0))));
		}
		// System.out.println("---[ADD] result: "+
		// Ress.iBuffer.get(Token.getID(res.get(0))) + "------------ [id] " + res);
		return res;
	}

	public static List<Integer> Minus(List<Integer> left, List<Integer> right) {
		ArrayList<Integer> res = new ArrayList<>();
		System.out.println("[OPERATION MINUS]");
		if (Algorithm.Typeof(left) == 0) {
			System.out.println(Algorithm.GetINTValue(left.get(0)) - Algorithm.GetINTValue(right.get(0)));
			res.add(Utils.cacheValue(Algorithm.GetINTValue(left.get(0)) - Algorithm.GetINTValue(right.get(0))));
		}
		// System.out.println("---[ADD] result: "+
		// Ress.iBuffer.get(Token.getID(res.get(0))) + "------------ [id] " + res);
		return res;
	}
	
	public static List<Integer> Mult(List<Integer> left, List<Integer> right) {
		ArrayList<Integer> res = new ArrayList<>();
		System.out.println("[OPERATION Mult]");
		if (Algorithm.Typeof(left) == 0) {
			if (left.size() != right.size() && left.size() != 0)
				throw new RuntimeException( "both participants have to be integers == left :" + Algorithm.printlist(left)+ " right: " + Algorithm.printlist(right));
			System.out.println(Algorithm.GetINTValue(left.get(0)) * Algorithm.GetINTValue(right.get(0)));
			res.add(Utils.cacheValue(Algorithm.GetINTValue(left.get(0)) * Algorithm.GetINTValue(right.get(0))));
		}
		// System.out.println("---[ADD] result: "+
		// Ress.iBuffer.get(Token.getID(res.get(0))) + "------------ [id] " + res);
		return res;
	}

	private static Integer cacheValue(int i) {
		Ress.iBuffer.add(i);
		// System.out.println("-------[CACHE CHANGE]-------\n"+Ress.iBuffer);
		return Token.genINTID(Ress.iBuffer.size() - 1);
	}

	public static List<Integer> DIV(List<Integer> left, List<Integer> right) {
		ArrayList<Integer> res = new ArrayList<>();
		System.out.println("[OPERATION DIV]");
		if (Algorithm.Typeof(left) == 0) {
			if (left.size() != right.size() && left.size() != 0)
				throw new RuntimeException( "both participants have to be integers == left :" + Algorithm.printlist(left)+ " right: " + Algorithm.printlist(right));
			System.out.println(Algorithm.GetINTValue(left.get(0)) / Algorithm.GetINTValue(right.get(0)));
			res.add(Utils.cacheValue(Algorithm.GetINTValue(left.get(0)) / Algorithm.GetINTValue(right.get(0))));
		}
		// System.out.println("---[ADD] result: "+
		// Ress.iBuffer.get(Token.getID(res.get(0))) + "------------ [id] " + res);
		return res;
	}

}
