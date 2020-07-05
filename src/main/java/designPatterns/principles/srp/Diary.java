package designPatterns.principles.srp;

import java.util.ArrayList;
import java.util.List;

/**
 * this is a class to keep all your intimate thoughts
 */
public class Diary {

    private final List<String> entriesToDiary = new ArrayList<>();
    private int count = 0;

    public void addEntry(String text) {
        entriesToDiary.add("" + (++count) + ":" + text);
    }

    public void removeEntry(int index) {
        entriesToDiary.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entriesToDiary);
    }

}
