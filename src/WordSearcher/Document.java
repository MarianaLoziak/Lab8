package WordSearcher;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Document implements Serializable {

    public List<String> getLines() {
        return lines;
    }

    private final List<String> lines;

    public Document(List<String> lines) {
        this.lines = lines;
    }

    static  Document fromFile(File file) throws IOException {
        List<String> lines = new LinkedList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line = reader.readLine();
            while(line!=null){
                lines.add(line);
                line = reader.readLine();
            }
        }
        return new Document(lines);
    }
}
