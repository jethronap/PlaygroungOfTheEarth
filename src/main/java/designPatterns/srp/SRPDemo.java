package designPatterns.srp;

public class SRPDemo {
    public static void main(String[] args) throws Exception {

        Diary diary = new Diary();
        diary.addEntry("i over slept today");
        diary.addEntry("i was watching the moon all night");
        System.out.println(diary);

        Persistence persistence = new Persistence();
        String filename = "src/main/java/designPatterns/srp/diary.txt";
        persistence.saveToFile(diary, filename, true);

        Runtime.getRuntime().exec("atom " + filename);
    }
}
