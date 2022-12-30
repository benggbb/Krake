package Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static List<Integer> Set(List<Integer> id ,List<Integer> value) {
		if (Algorithm.Typeof(value) == 0 ) //int
			System.out.println( id);
			Ress.Num.put(Token.getID(id.get(0)) ,(double) value.get(0));
		return value;
	}

	public static List<Integer> Add(List<Integer> left, List<Integer> right) {
		ArrayList<Integer> res = new ArrayList<>();
		if(Algorithm.Typeof(left ) == 0) {
			 res.add( Utils.cacheValue(Algorithm.GetINTValue(left.get(0)) + Algorithm.GetINTValue( right.get(0))));
		}
		System.out.println("---[ADD] result: "+ Ress.iBuffer.get(Token.getID(res.get(0))) +  "------------ [id] " + res);
		return res;
	}

	private static Integer cacheValue(int i) {
		Ress.iBuffer.add(i);
		System.out.println("-------[CACHE CHANGE]-------\n"+Ress.iBuffer);
		return Token.genINTID(Ress.iBuffer.size()-1);
	}

}
