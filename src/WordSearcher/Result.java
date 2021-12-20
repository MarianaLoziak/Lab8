package WordSearcher;

import java.io.Serializable;

public class Result implements Serializable {
    public int[][] result;

    public Result(int size0, int size1){
        result = new int [size0][size1];
    }


    public  void multiply(int[] row, int indexRow, int[] col, int indexCol){

        int element = 0;
        for(int i = 0; i< row.length; i++){
            element+=row[i]*col[i];
        }
        result[indexRow][indexCol]=element;
    }

    public void multiplyBasic(int[][] a, int[][] b, int indexRow, int indexCol ){
        int size = a.length;
        int rowStart = indexRow*size;
        int colStart = indexCol*size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    result[i+rowStart][j+colStart]+=a[i][k]*b[k][j];
                }
            }
        }
    }

    public void print(){
        for (int i=0; i < result.length; i++){
            for(int j = 0; j < result[0].length; j++){
                System.out.print( result[i][j]+" ");
            }
            System.out.println();
        }
    }

}
