import WordSearcher.Folder;
import WordSearcher.Matrix;
import WordSearcher.Result;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        boolean serverData = true;
        int matrixSize = 8;

        Socket socket = new Socket("127.0.0.1",8000);
        String message = "send data";
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        long m = System.currentTimeMillis();
        if (serverData){
            objectOutputStream.writeObject(message);
        } else {
            Matrix A = new Matrix(matrixSize);
            A.FillOne();
            Matrix B = new Matrix(matrixSize);
            B.FillOne();
            //Folder folder = Folder.fromDirectory(new File("Books"));
            objectOutputStream.writeObject(A);
            objectOutputStream.writeObject(B);
        }

        Object object = objectInputStream.readObject();
        //List<String> commonWords = (List<String>) object;
        Result r = (Result) object;
/*        for (var word : commonWords) {
            System.out.println(word);
        }*/
        r.print();
        System.out.println("Client done");
        System.out.println("Stripped time = " + (double) (System.nanoTime() - m)/1000000000);




    }

}
