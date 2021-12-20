import WordSearcher.WordSearcher;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import WordSearcher.*;

public class Server {

    private static WordSearcher wordSearcher = new WordSearcher();
    private static Folder folder;
    private static List<String> commonWords;

    public static void main(String[] args) throws IOException {
        int portNumber = 8000;
        boolean serverData = true;

        try{
            ServerSocket serverSocket = new ServerSocket(portNumber);

            System.out.println("Connecting");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connected");
            InputStream inputStream = clientSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            OutputStream outputStream = clientSocket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            int matrixSize = 4;
            int threadN = 4;

            Matrix A = new Matrix();
            Matrix B = new Matrix();

            if(serverData){
                A.FillAlong();
                B.FillAlong();
                Object o = new Object();
                String message = (String) objectInputStream.readObject();
                System.out.println(message);
                //folder = Folder.fromDirectory(new File("F:\\KPI 4-1\\Технології паралельного та розподіленого обчислення\\Lab8\\Books"));
            } else {
                //folder = (Folder)objectInputStream.readObject();
                A = (Matrix)objectInputStream.readObject();
                B = (Matrix)objectInputStream.readObject();
            }

            Result resultStripped = new Result(A.getRowNumb(), B.getColNumb());

            var lines = Matrix.divideLines(A,threadN);
            var columns = Matrix.transponateB(B);
            Matrix.startStrippedThread(lines, columns, resultStripped);
            //commonWords = wordSearcher.compareParallel(folder);
            A.print();
            B.print();
            System.out.println("done");
            resultStripped.print();
            //objectOutputStream.writeObject(commonWords);
            objectOutputStream.writeObject(resultStripped);



        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
