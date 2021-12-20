package WordSearcher;



public class StripThread extends Thread{
    public Result result;
    public int[] rowA;
    public int[][] rowsA;
    public int[][] columnsB;

    public int indexRow;
    public int indexCol;

    public int stripsCount;


    public StripThread(int[][]row, int indexRow, int[][] col, int indexCol, Result res, int stripsCount){
        this.rowsA = row;
        this.columnsB = col;
        this.indexRow = indexRow;
        this.indexCol = indexCol;
        this.stripsCount = stripsCount;
        result = res;
    }

    public StripThread(int[][] row, int indexRow, int[][] col, Result res){
        this.rowsA = row;
        this.columnsB = col;
        this.indexRow = indexRow;
        result = res;
    }

    public void moveB(int[][] columns){
        for (int i = 0; i < columns.length-1; i++) {
            int[]temp = columns[i];
            columns[i]=columns[i+1];
            columns[i+1]=temp;
        }
    }
    @Override
    public void run(){

        /*for (int j = stripsCount - 1; j >= 0 ; j--) {
            int index = indexRow-j;
            for (int i = 0; i< rowsA[0].length; i++){
                result.multiply(rowsA[j], indexRow-j, columnsB[i], index);
                if (index ==0)
                    index=rowsA[0].length-1;
                else index--;
            }
            moveB(columnsB);

        }*/

        for (int i = 0; i < rowsA.length; i++) {
            for (int j = 0; j < rowsA[0].length; j++) {
                //result.multiply(rowsA[i], indexRow+i, columnsB[j], j);
                int element = 0;
                for(int k = 0; k< rowsA[i].length; k++){
                    element+=rowsA[i][k]*columnsB[j][k];
                }
                result.result[indexRow+i][j]=element;
            }
        }

    }

}
