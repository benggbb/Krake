package Data;

public class Debug {
    public static void Print_Log(Object ... value) {
        if (true) {
            for (Object wert : value) {
                System.out.print(wert + " "); // Druckt alles mit Leerzeichen dazwischen
            }
            System.out.println();
        }
    }
    public static void Print_Log_Flat(Object ... value) {
        if (true) {
            for (Object wert : value) {
                System.out.print(wert + " "); // Druckt alles mit Leerzeichen dazwischen
            }
        }
    }
}
