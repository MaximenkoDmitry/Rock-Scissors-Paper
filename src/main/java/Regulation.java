import com.github.freva.asciitable.AsciiTable;

public class Regulation {
    public static void show(String[] moves){
        String[][] data = new String[moves.length][moves.length+1];
        String[] header = new String[moves.length+1];
        header[0] = "PC/User";
        for (int i = 0; i < moves.length; i++){
            header[i+1] = moves[i];
            data[i][0] = moves[i];
        }
        for (int i = 0; i < moves.length; i++)
            for (int  j= 1; j <= moves.length; j++){
                Winner winner = new Winner(i,j-1,moves.length);
                data[i][j] = winner.findWinner();
            }
        System.out.println(AsciiTable.getTable(header, data));
    }
}
