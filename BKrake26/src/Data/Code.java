package Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Code {
    public static int count = 0;
    int addr;
    ArrayList <List<Integer>>  lines;
    HashSet<Integer> b_ids;

    public Code(int addr, ArrayList<List<Integer>>  lines, HashSet<Integer> b_ids) {
        this.addr = addr;
        this.lines = lines;
        this.b_ids = b_ids;
    }

    public List<Integer> getline(int i){
        return lines.get(i);
    }

    public ArrayList <List<Integer>>  lines(){
        return lines;
    }
    public String toString(){
        Debug.Print_Log(b_ids);
        String prnt = "NEW BLOCK with addr: "+ addr + "\n";
        for(int i = 0; i < lines.size(); i++){
            prnt += lines.get(i) + "\n";
        }
        for (int p: b_ids){
            prnt += Ress.Blocks.get(p);
        }
        return prnt;
    }


}
