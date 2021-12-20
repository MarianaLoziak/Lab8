package WordSearcher;

public class Rows {
    public int[][] rows;
    public int startIndex;

    public Rows(int[][] lines, int index){
        this.rows = lines;
        this.startIndex = index;
    }
}
