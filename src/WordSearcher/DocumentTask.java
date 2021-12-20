package WordSearcher;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DocumentTask extends RecursiveTask<List<String>> {

    private final Document document;

    public DocumentTask(Document document) {
        super();
        this.document = document;
    }

    @Override
    public List<String> compute(){
        return WordSearcher.getWords(document);
    }

}
