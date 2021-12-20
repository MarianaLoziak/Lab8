package WordSearcher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Matrix implements Serializable {
    private final int[][] matrix;

    public Matrix (){
        matrix = new int[1][1];
    }
    public Matrix(int size){

        matrix = new int [size][size];
        //FillAlong();

    }

    public int[][] getMatrix(){
        return  matrix;
    }

    public void fill(){
        for (int i=0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                matrix[i][j]= new Random().nextInt(10);
            }
        }
    }

    public void FillAlong(){
        for (int i=0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                matrix[i][j]= matrix.length*i+j+1;
            }
        }
    }


    public void FillOne(){
        for (int i=0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                matrix[i][j]=1;
            }
        }
    }
    public int getColNumb(){
        return  matrix[0].length;
    }

    public int getRowNumb(){
        return  matrix.length;
    }

    public int[] getRow(int indexRow){
        int[] row = new int[matrix.length];

        for (int i = 0; i < matrix[0].length; i++){
            row[i]=matrix[indexRow][i];
        }

        return  row;
    }

    public int[] getColumn(int indexCol){
        int[] column = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++){
            column[i]=matrix[i][indexCol];
        }

        return  column;
    }

    public void print(){
        for (int i=0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                System.out.print( matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void startStrippedThread(ArrayList<Rows> rows, int[][]col, Result result){
        ArrayList<StripThread> threads = new ArrayList<StripThread>();
        for (Rows row:rows) {
            StripThread stripThread = new StripThread(row.rows, row.startIndex, col, result);
            threads.add(stripThread);
            stripThread.start();
        }

        for (StripThread thread:threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static ArrayList<Rows> divideLines(Matrix A, int threadN){
        int stripsCount = A.getRowNumb()/threadN;
        ArrayList<Rows> rows =new ArrayList<Rows>();
        for (int i = 0; i < threadN; i++) {
            int[][] lines = new int[stripsCount][A.getColNumb()];
            for (int j = 0; j < stripsCount; j++) {
                lines[j]=A.getRow(i*stripsCount+j);
            }

            Rows rowsThread = new Rows(lines, i*stripsCount);
            rows.add(rowsThread);
        }
        return rows;
    }

    public static int[][] transponateB(Matrix B){
        int[][]cols = new int[B.getRowNumb()][B.getColNumb()];
        for (int i = 0; i < B.getRowNumb(); i++) {
            for (int j = 0; j < B.getColNumb(); j++) {
                cols[i][j]=B.getMatrix()[j][i];
            }
        }

        return cols;
    }




}
