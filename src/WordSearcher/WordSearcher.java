package WordSearcher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class WordSearcher {

    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public static String[] wordsIn(String line){
        return line.trim().split("(\\s|\\p{Punct})+");
    }

    public static List<String> getWords(Document document){
        List<String> words = new ArrayList<>();
        for (String line:document.getLines()) {
            for (String word:wordsIn(line) ) {
                if (!words.contains(word))
                    words.add(word);
            }

        }

        return words;
    }

    public List<String> compareParallel(Folder folder){
        return  forkJoinPool.invoke(new FolderTask(folder));
    }
}
