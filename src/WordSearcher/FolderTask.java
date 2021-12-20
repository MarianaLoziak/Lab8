package WordSearcher;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderTask extends RecursiveTask<List<String>> {

    private final Folder folder;

    public FolderTask(Folder folder) {
        super();
        this.folder = folder;
    }

    @Override
    protected List<String> compute() {

        List<String> common = new ArrayList<>();
        List<RecursiveTask<List<String>>> tasks = new LinkedList<>();

        for(Folder subfolder: folder.getSubFolders()){
            FolderTask task = new FolderTask(subfolder);
            tasks.add(task);
            task.fork();
        }

        for (Document document : folder.getDocuments()) {
            DocumentTask task = new DocumentTask(document);
            tasks.add(task);
            task.fork();
        }

        common = tasks.get(0).join();
        for (int i = 1; i < tasks.size() ; i++) {
            common.retainAll(tasks.get(i).join());
        }
        return common;
    }
}
