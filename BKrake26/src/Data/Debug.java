package Data;

import java.util.ArrayList;
import java.util.List;

public class Debug {
    static boolean debug = true ;
    public static void Print_Log(Object ... value) {
        if (debug) {
            for (Object wert : value) {
                System.out.print(wert + " "); // Druckt alles mit Leerzeichen dazwischen
            }
            System.out.println();
        }
    }
    public static void Print_tokenli(List<Integer> t) {
        if (debug) {
            System.out.print(Algorithm.Tokenli_toString(t));
        }
    }
    public static void Print_Log_Flat(Object ... value) {
        if (debug) {
            for (Object wert : value) {
                System.out.print(wert + " "); // Druckt alles mit Leerzeichen dazwischen
            }
        }
    }
}
