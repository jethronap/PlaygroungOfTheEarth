package designPatterns.principles.srp;

public class SRPDemo {
    public static void main(String[] args) throws Exception {

        Diary diary = new Diary();
        diary.addEntry("i over slept today");
        diary.addEntry("i was watching the moon all night");
        System.out.println(diary);

        Persistence persistence = new Persistence();
        // the following will produce a file name diary.txt if missing
        String filename = "src/main/java/designPatterns/srp/diary.txt";
        persistence.saveToFile(diary, filename, true);

        // opens diary file with Atom application
        Runtime.getRuntime().exec("atom " + filename);
    }
}
